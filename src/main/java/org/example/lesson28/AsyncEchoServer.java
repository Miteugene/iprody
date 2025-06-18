package org.example.lesson28;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.*;

public class AsyncEchoServer {
    private static final int PORT = 7;
    private static final String STOP_WORD = "/end";

    private static final Map<SocketChannel, ByteBuffer> clients = new HashMap<>();
    private static final Queue<String> outgoingMessages = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.configureBlocking(false);
        serverChannel.bind(new InetSocketAddress(PORT));

        Selector selector = Selector.open();
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);

        System.out.println("Server started on port " + PORT);
        System.out.println("Write '" + STOP_WORD + "' to shut down");

        // Тред для отправки сообщений из консоли
        Thread consoleThread = new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                String inputMessage = scanner.nextLine().trim();
                synchronized (outgoingMessages) {
                    outgoingMessages.add(inputMessage);
                }
                selector.wakeup();

                if (inputMessage.equals(STOP_WORD)) {
                    break;
                }
            }
        });
        consoleThread.start();

        // Основной поток обработки клиентов
        while (true) {
            selector.select();

            if (!outgoingMessages.isEmpty()) {
                synchronized (outgoingMessages) {
                    String msg = outgoingMessages.poll();
                    if (msg != null) {
                        if (msg.equals(STOP_WORD)) {
                            break;
                        }

                        for (SocketChannel client : clients.keySet()) {
                            ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());
                            clients.put(client, buffer);
                            client.keyFor(selector).interestOps(SelectionKey.OP_WRITE | SelectionKey.OP_READ);
                        }
                    }
                }
            }

            Iterator<SelectionKey> keys = selector.selectedKeys().iterator();
            while (keys.hasNext()) {
                SelectionKey key = keys.next();
                keys.remove();

                if (!key.isValid()) {
                    continue;
                }

                if (key.isAcceptable()) {
                    handleAccept(key, selector);
                } else if (key.isReadable()) {
                    handleRead(key);
                } else if (key.isWritable()) {
                    handleWrite(key);
                }
            }
        }

        selector.close();
        serverChannel.close();
        consoleThread.interrupt();
        System.out.println("Server stopped");
    }

    private static void handleAccept(SelectionKey key, Selector selector) throws IOException {
        ServerSocketChannel server = (ServerSocketChannel) key.channel();
        SocketChannel client = server.accept();
        client.configureBlocking(false);
        client.register(selector, SelectionKey.OP_READ);
        clients.put(client, ByteBuffer.allocate(1024));
        System.out.println("Client connected: " + client.getRemoteAddress());
    }

    private static void handleRead(SelectionKey key) throws IOException {
        SocketChannel client = (SocketChannel) key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        int read = client.read(buffer);
        if (read == -1) {
            clients.remove(client);
            client.close();
            return;
        }

        buffer.flip();
        byte[] data = new byte[buffer.limit()];
        buffer.get(data);
        String msg = new String(data).trim();

        System.out.println("Client says: " + msg);

        if (!msg.startsWith("Echo: ")) {
            ByteBuffer echo = ByteBuffer.wrap(("Echo: " + msg).getBytes());
            clients.put(client, echo);
        }

        key.interestOps(SelectionKey.OP_WRITE | SelectionKey.OP_READ);
    }

    private static void handleWrite(SelectionKey key) throws IOException {
        SocketChannel client = (SocketChannel) key.channel();
        ByteBuffer buffer = clients.get(client);

        if (buffer != null) {
            client.write(buffer);
            if (!buffer.hasRemaining()) {
                clients.put(client, null);
                key.interestOps(SelectionKey.OP_READ);
            }
        }
    }
}
