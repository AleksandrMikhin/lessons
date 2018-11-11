package lesson9.storage;

class Container<T> {
    T element;

    Container next;

    public Container(T element) {
        this.element = element;
    }
}