package org.example.lesson25;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class EchoClient {
    private static final String STOP_WORD = "/end";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try (Socket socket = new Socket("localhost", 7)) {
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());

            System.out.println("Write '" + STOP_WORD + "' in console to exit");

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
