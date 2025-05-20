package org.example.lesson20;

import org.example.lesson20.contracts.ObjectStorage;
import org.example.lesson20.exceptions.ObjectNotFoundException;
import org.example.lesson20.exceptions.WrongStorageLocationException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class FileStorage implements ObjectStorage<File> {

    private File directory;

    public FileStorage(String storageLocation) throws WrongStorageLocationException, IOException {
        Path storagePath = Path.of(storageLocation);

        if (Files.exists(storagePath) && !Files.isDirectory(storagePath)) {
            throw new WrongStorageLocationException(storageLocation);
        }

        if (Files.notExists(storagePath)) {
            Files.createDirectories(storagePath);
        }

        this.directory = storagePath.toFile();
    }

    @Override
    public void put(String namespace, String name, File object) {
        Path dirPath = Path.of(directory.getAbsolutePath(), namespace);

        try {
            if (Files.notExists(dirPath)) {
                Files.createDirectories(dirPath);
            }

            Path filePath = dirPath.resolve(name);

            Files.copy(object.toPath(), filePath, StandardCopyOption.REPLACE_EXISTING);

            System.out.println("File saved: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public File get(String namespace, String name) throws ObjectNotFoundException {
        Path path = Path.of(directory.getAbsolutePath(), namespace, name);
        File file = path.toFile();

        if (!file.exists()) {
            throw new ObjectNotFoundException(namespace, name);
        }

        return file;
    }
}
