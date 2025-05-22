package org.example.lesson22.task1;

import java.util.ArrayList;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ThreadSafeList<T> {
    private final ArrayList<T> arrayList = new ArrayList<>();
    ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public void add(T obj) {
        try {
            readWriteLock.writeLock().lock();
            arrayList.add(obj);
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public T remove(int index) {
        T obj = get(index);

        try {
            readWriteLock.writeLock().lock();
            arrayList.remove(index);
        } finally {
            readWriteLock.writeLock().unlock();
        }

        return obj;
    }

    public T get(int index) {
        T obj;

        try {
            readWriteLock.readLock().lock();
            obj = arrayList.get(index);
        } finally {
            readWriteLock.readLock().unlock();
        }

        return obj;
    }

    public int size() {
        int size;

        try {
            readWriteLock.readLock().lock();
            size = arrayList.size();
        } finally {
            readWriteLock.readLock().unlock();
        }

        return size;
    }
}
