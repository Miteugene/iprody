package org.example.lesson19;

import java.io.*;

public class FileLoggerConfigurationLoader {
    public FileLoggerConfiguration load(String filePath) {
        FileLoggerConfiguration configuration = null;

        File file = new File(filePath);
        if (!file.exists()) {
            return null;
        }

        try (ObjectInputStream stream = new ObjectInputStream(new FileInputStream(file))) {
            configuration = (FileLoggerConfiguration) stream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return configuration;
    }

    public void save(String filePath, FileLoggerConfiguration configuration) {
        try (ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(filePath))) {
            stream.writeObject(configuration);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
