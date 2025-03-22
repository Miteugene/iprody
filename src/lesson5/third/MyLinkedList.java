package lesson5.third;

public class MyLinkedList {
    private MyLinkedListObject firstObject = null;
    private MyLinkedListObject lastObject = null;
    public int length = 0;

    public MyLinkedList() {
    }

    public MyLinkedList(int length) {
        for (int i = 0; i < length; i++) {
            pushLast(null);
        }
    }

    public Object get(int index) {
        if (index > length || index < 0) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        MyLinkedListObject myListObject = firstObject;

        for (int i = 0; i < index; i++) {
            myListObject = myListObject.getNextObject();
        }

        return myListObject.getUserObject();
    }

    public void put(int index, Object object) {
        if (index > length || index < 0) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        MyLinkedListObject myListObject = firstObject;

        for (int i = 0; i < index; i++) {
            myListObject = myListObject.getNextObject();
        }

        myListObject.setUserObject(object);
    }

    public void pushLast(Object object) {
        MyLinkedListObject newListObject = new MyLinkedListObject(object);

        if (lastObject != null) {
            newListObject.setPrevObject(lastObject);
            lastObject.setNextObject(newListObject);
        }

        if (firstObject == null) {
            firstObject = newListObject;
        }

        lastObject = newListObject;
        length++;
    }

    public void pushFirst(Object object) {
        MyLinkedListObject newListObject = new MyLinkedListObject(object);

        if (firstObject != null) {
            newListObject.setNextObject(firstObject);
            firstObject.setPrevObject(newListObject);
        }

        if (lastObject == null) {
            lastObject = newListObject;
        }

        firstObject = newListObject;
        length++;
    }

    public Object pullFirst() {
        if (firstObject == null) {
            return null; // Я специально сделал возврат null, если у нас нет объектов
        }

        Object userObject = firstObject.getUserObject();

        if (firstObject.getNextObject() != null) {
            firstObject = firstObject.getNextObject();
            firstObject.setPrevObject(null);
        }

        length--;

        if (length == 0) {
            lastObject = null;
            firstObject = null;
        }

        return userObject;
    }

    public Object pullLast() {
        if (lastObject == null) {
            return null; // Я специально сделал возврат null, если у нас нет объектов
        }

        Object userObject = lastObject.getUserObject();

        if (lastObject.getPrevObject() != null) {
            lastObject = lastObject.getPrevObject();
            lastObject.setNextObject(null);
        }

        length--;

        if (length == 0) {
            lastObject = null;
            firstObject = null;
        }

        return userObject;
    }

    // Ну верну я не адрес, как в обычных коллекциях, а строковое представление массива
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append('[');

        MyLinkedListObject myListObject = firstObject;

        for (int i = 0; i < length; i++) {
            Object userObject = myListObject.getUserObject();
            stringBuilder.append(userObject == null ? "null" : userObject.toString());

            myListObject = myListObject.getNextObject();

            if (i < length - 1) {
                stringBuilder.append(", ");
            }
        }

        stringBuilder.append(']');

        return stringBuilder.toString();
    }
}
