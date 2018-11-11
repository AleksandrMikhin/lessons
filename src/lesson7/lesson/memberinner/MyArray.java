package lesson7.lesson.memberinner;

import java.util.Iterator;

public class MyArray {
    private Object[] array;

    public MyArray(Object[] array) {
        array = new Object[10];
        for (int i = 0; i < array.length; i++) {
            array[i] = String.valueOf(i);
        }
    }

    public Iterator evenIterator(){
      return new EvenItarator();
    };

    private class EvenItarator implements Iterator {
        private int nextIndex;

        @Override
        public boolean hasNext() {
            return nextIndex < array.length;
        }

        @Override
        public Object next() {
            Object next = array[nextIndex];
            nextIndex += 2;
            return next;
        }
    }
}
