package org.example.lesson20.exceptions;

public class ObjectNotFoundException extends Exception {
    public ObjectNotFoundException(String namespace, String name) {
        super(String.format("Object `%s` not found in namespace `%s`", name, namespace));
    }
}
