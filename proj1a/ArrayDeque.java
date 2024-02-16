public class ArrayDeque<T> {
    private class Node {
        private int nextFirst;
        private int nextLast;

        public Node(int f, int l) {
            nextFirst = f;
            nextLast = l;
        }
    }

    private int size;
    private T[] a;
    private Node sentinel;

    public ArrayDeque() {
        size = 0;
        a = (T[]) new Object[8];
        sentinel = new Node(4, 5);
    }

    private void resize(int capacity) {
        T[] newA = (T[]) new Object[capacity];
        int startPos = (capacity - size) / 2;
        int cur = startPos;

        for (int i = 0; i < size; i++) {
            newA[cur++] = a[(sentinel.nextFirst + i + 1) % a.length];
        }
        sentinel.nextFirst = startPos - 1;
        sentinel.nextLast = startPos + size;

        a = newA;
    }

    public void addFirst(T item) {
        if (size >= a.length) {
            resize(size + size / 4 * 3);
        }
        a[sentinel.nextFirst] = item;
        sentinel.nextFirst -= 1;
        if (sentinel.nextFirst < 0) {
            sentinel.nextFirst = a.length - 1;
        }
        size++;
    }

    public void addLast(T item) {
        if (size >= a.length) {
            resize(size + size / 4 * 3);
        }

        a[sentinel.nextLast] = item;
        sentinel.nextLast = (sentinel.nextLast + 1) % a.length;
        size++;
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        sentinel.nextFirst = (sentinel.nextFirst + 1) % a.length;
        size--;
        T temp = a[sentinel.nextFirst];
        if (size <= a.length / 4 && a.length >= 16) {
            resize(size + size / 4 * 3);
        }
        return temp;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        sentinel.nextLast = (sentinel.nextLast - 1);
        if (sentinel.nextLast < 0) {
            sentinel.nextLast = a.length - 1;
        }
        size--;
        T temp = a[sentinel.nextLast];
        if (size <= a.length / 4 && a.length >= 16) {
            resize(size + size / 4 * 3);
        }
        return temp;
    }

    public T get(int index) {
        if (index >= size) {
            return null;
        }

        int pos = (sentinel.nextFirst + index + 1) % a.length;
        return a[pos];
    }

    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(a[(sentinel.nextFirst + i + 1) % a.length] + " ");
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0 ? true : false;
    }
}

