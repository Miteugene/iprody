package org.example.lesson20.exceptions;

public class WrongStorageLocationException extends Exception {
    public WrongStorageLocationException(String storageLocation) {
        super("The specified file storage path must be a folder. Received: " + storageLocation);
    }
}
