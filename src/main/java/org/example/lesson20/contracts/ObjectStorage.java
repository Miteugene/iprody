package org.example.lesson20.contracts;

import org.example.lesson20.exceptions.ObjectNotFoundException;

import java.io.IOException;

public interface ObjectStorage<T> {
    void put(String namespace, String name, T object);
    T get(String namespace, String name) throws ObjectNotFoundException;
}
