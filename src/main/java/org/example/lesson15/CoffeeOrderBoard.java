package org.example.lesson15;

import java.util.*;

/**
 * Судя по описанию задачи мы должны:
 * 1. Иметь очередь
 * 2. Быстро добавлять в очередь
 * 3. Быстро доставать из очереди
 * 4. Быстро доставать из очереди по ключу
 * 5. Исключить в коллекции повторные id (решается автоинкрементом, но видимо и коллекция тоже должна уметь)
 * 6. Уметь выводить очередь в нужном порядке
 *
 * Кажется под это описание походит LinkedHashMap
 */
public class CoffeeOrderBoard {
    private LinkedHashMap<Integer, Order> ordersMap = new LinkedHashMap<>();
    private int orderId = 0;

    public Order add(String customerName) {
        orderId++;
        Order order = new Order(orderId, customerName);
        ordersMap.put(order.getNumber(), order);
        return order;
    }

    public Order deliver() {
        return ordersMap.pollFirstEntry().getValue();
    }

    public Order deliver(int id) {
        return ordersMap.remove(id);
    }

    public void draw() {
        System.out.println(Arrays.toString(ordersMap.values().toArray()));
    }
}
