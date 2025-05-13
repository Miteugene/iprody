package org.example.lesson20.contracts;

import org.example.lesson20.exceptions.ObjectNotFoundException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface ObjectStorageReader {
    byte[] read(String namespace, String name) throws IOException, ObjectNotFoundException;
    List<byte[]> read(String namespace, String name, int chunkSize) throws ObjectNotFoundException;
}
