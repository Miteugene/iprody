package org.example.lesson20;

import com.github.javafaker.Faker;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;

public class Lesson20 {
    public static void main(String[] args) {
        Faker faker = new Faker();
        String filePath = "./example.txt";
        String data = faker.lorem().sentence(100);
        String namespace = "test-namespace";
        String objectName = "object-name";

        try (
            RandomAccessFile raFile = new RandomAccessFile(filePath, "rw");
            FileChannel channel = raFile.getChannel()
        ) {
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            buffer.put(data.getBytes());
            buffer.flip();
            while (buffer.hasRemaining()) {
                channel.write(buffer);
            }

            File file = new File(filePath);

            FileStorage fileStorage = new FileStorage(".");
            fileStorage.put(namespace, objectName, file);

            FileStorageReader fileStorageReader = new FileStorageReader(fileStorage);

            System.out.println("Read all: \n" + new String(fileStorageReader.read(namespace, objectName)));

            System.out.println("Read chunks:");
            List<byte[]> chunks = fileStorageReader.read(namespace, objectName, 10);
            chunks.forEach(chunk -> System.out.print(new String(chunk)));


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
