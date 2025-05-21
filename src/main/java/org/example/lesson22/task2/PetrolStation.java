package org.example.lesson22.task2;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class PetrolStation {
    private double totalFuelAmount;
    Semaphore smp;

    private Random random = new Random();
    ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public PetrolStation(double totalFuelAmount, int carCapacity) {
        this.totalFuelAmount = totalFuelAmount;
        this.smp = new Semaphore(carCapacity);
    }

    public void doTank(double needFuelAmount) {
        String threadName = Thread.currentThread().getName();

        try {
            System.out.println(threadName + " arrived at the petrol station. Waiting in the queue...");

            // Ждем очереди на заправку
            smp.acquire();

            // Узнаем есть ли нужное количество топлива на заправке
            readWriteLock.readLock().lock();
            System.out.println(threadName + " is checking fuel amount");
            if (totalFuelAmount < needFuelAmount) {
                throw new NotEnoughFuelException();
            }
            readWriteLock.readLock().unlock();

            // Заправляемся
            readWriteLock.writeLock().lock();
            totalFuelAmount -= needFuelAmount;
            readWriteLock.writeLock().unlock();
            System.out.println(threadName + " is being refueled...");
            Thread.sleep(random.nextInt(3000, 10000));

        } catch (InterruptedException|NotEnoughFuelException e) {
            e.printStackTrace();
        } finally {
            // Уезжаем из заправки
            System.out.println(threadName + " drove away");
            smp.release();
        }
    }
}
