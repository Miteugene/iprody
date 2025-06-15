package org.example.lesson28;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.*;

public class AsyncEchoClient {

    private static final String HOST = "localhost";
    private static final int PORT = 7;
    private static final String STOP_WORD = "/end";

    private static final Queue<String> outgoingMessages = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        SocketChannel clientChannel = SocketChannel.open();
        clientChannel.configureBlocking(false);
        clientChannel.connect(new InetSocketAddress(HOST, PORT));

        Selector selector = Selector.open();
        clientChannel.register(selector, SelectionKey.OP_CONNECT);

        // Тред для отправки сообщений из консоли
        Thread consoleThread = new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                String msg = scanner.nextLine().trim();
                synchronized (outgoingMessages) {
                    outgoingMessages.add(msg);
                }
                selector.wakeup();

                if (msg.equals(STOP_WORD)) {
                    break;
                }
            }
        });
        consoleThread.start();

        // Основной поток обработки
        while (true) {
            selector.select();

            Iterator<SelectionKey> keys = selector.selectedKeys().iterator();
            while (keys.hasNext()) {
                SelectionKey key = keys.next();
                keys.remove();

                if (!key.isValid()) continue;

                if (key.isConnectable()) {
                    finishConnect(key);
                } else if (key.isReadable()) {
                    handleRead(key);
                } else if (key.isWritable()) {
                    handleWrite(key);
                }
            }

            synchronized (outgoingMessages) {
                if (!outgoingMessages.isEmpty()) {
                    clientChannel.register(selector, SelectionKey.OP_WRITE | SelectionKey.OP_READ);
                }
            }
        }
    }

    private static void finishConnect(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();
        if (channel.isConnectionPending()) {
            channel.finishConnect();
        }
        System.out.println("Connected to server.");
        channel.register(key.selector(), SelectionKey.OP_READ);
    }

    private static void handleRead(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        int bytesRead = channel.read(buffer);
        if (bytesRead == -1) {
            channel.close();
            return;
        }

        buffer.flip();
        byte[] data = new byte[buffer.limit()];
        buffer.get(data);

        String msg = new String(data).trim();
        System.out.println("Server: " + msg);

        if (msg.contains(STOP_WORD)) {
            System.exit(0);
        }

        if (!msg.startsWith("Echo: ")) {
            synchronized (outgoingMessages) {
                outgoingMessages.add("Echo: " + msg);
            }
            key.selector().wakeup();
        }
    }


    private static void handleWrite(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();
        String msg;
        synchronized (outgoingMessages) {
            msg = outgoingMessages.poll();
        }

        if (msg != null) {
            ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());
            channel.write(buffer);
            if (msg.equals(STOP_WORD)) {
                System.out.println("Disconnecting...");
                channel.close();
                System.exit(0);
            }
        }

        synchronized (outgoingMessages) {
            if (outgoingMessages.isEmpty()) {
                channel.register(key.selector(), SelectionKey.OP_READ);
            }
        }
    }
}
