package org.example.lesson25;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class EchoServer {
    private static final String STOP_WORD = "/end";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try (ServerSocket serverSocket = new ServerSocket(7)) {
            System.out.println("Server started");

            Socket accept = serverSocket.accept();
            System.out.println("Client connected");

            System.out.println("Write '" + STOP_WORD + "' in console to exit");

            DataInputStream dataInputStream = new DataInputStream(accept.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(accept.getOutputStream());


            // Тред для отправки сообщений из консоли
            Thread senderThread = new Thread(() -> {
                try {
                    while(true) {
                        String consoleText = scanner.nextLine().trim();

                        if(consoleText.equals(STOP_WORD)) {
                            break;
                        }

                        dataOutputStream.writeUTF(consoleText);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

            // Тред для получения сообщений
            Thread receiverThread = new Thread(() -> {
                try {
                    while(true) {
                        String data = dataInputStream.readUTF();

                        System.out.println("Received: " + data);

                        if (!data.startsWith("Echo: ")) {
                            dataOutputStream.writeUTF("Echo: " + data);
                        }

                        if(data.contains(STOP_WORD)) {
                            break;
                        }
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

            senderThread.start();
            receiverThread.start();

            senderThread.join();
            receiverThread.join();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
