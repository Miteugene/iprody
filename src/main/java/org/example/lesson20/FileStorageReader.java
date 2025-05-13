package org.example.lesson20;

import org.example.lesson20.contracts.ObjectStorageReader;
import org.example.lesson20.exceptions.ObjectNotFoundException;

import java.io.File;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.nio.ByteBuffer;

public class FileStorageReader implements ObjectStorageReader {

    private FileStorage fileStorage;

    public FileStorageReader(FileStorage fileStorage) {
        this.fileStorage = fileStorage;
    }

    @Override
    public byte[] read(String namespace, String name) throws ObjectNotFoundException {
        File file = fileStorage.get(namespace, name);

        if (!file.exists()) {
            throw new ObjectNotFoundException(namespace, name);
        }

        byte[] content = new byte[0];

        try {
            content = Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return content;
    }

    @Override
    public List<byte[]> read(String namespace, String name, int chunkSize) throws ObjectNotFoundException {
        File file = fileStorage.get(namespace, name);

        if (!file.exists()) {
            throw new ObjectNotFoundException(namespace, name);
        }

        List<byte[]> chunks = new ArrayList<>();

        try (FileChannel fileChannel = FileChannel.open(file.toPath(), StandardOpenOption.READ)) {
            ByteBuffer buffer = ByteBuffer.allocate(chunkSize);
            while (fileChannel.read(buffer) > 0) {
                buffer.flip();

                byte[] chunk = new byte[buffer.remaining()];
                buffer.get(chunk);
                chunks.add(chunk);

                buffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return chunks;
    }
}
