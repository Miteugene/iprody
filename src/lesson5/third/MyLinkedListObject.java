package lesson5.third;

class MyLinkedListObject {
    private Object userObject;
    private MyLinkedListObject nextObject = null;
    private MyLinkedListObject prevObject = null;

    public MyLinkedListObject(Object userObject) {
        this.userObject = userObject;
    }

    public Object getUserObject() {
        return userObject;
    }

    public void setUserObject(Object userObject) {
        this.userObject = userObject;
    }

    public MyLinkedListObject getNextObject() {
        return nextObject;
    }

    public void setNextObject(MyLinkedListObject nextObject) {
        this.nextObject = nextObject;
    }

    public MyLinkedListObject getPrevObject() {
        return prevObject;
    }

    public void setPrevObject(MyLinkedListObject prevObject) {
        this.prevObject = prevObject;
    }
}
