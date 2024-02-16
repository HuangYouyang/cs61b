public class ArrayDeque<T> {
    private class Node {
        private int prev;
        private int next;

        public Node(int p, int n) {
            prev = p;
            next = n;
        }
    }

    private int size;
    private T[] a;
    private Node sentinel;

    public ArrayDeque() {
        size = 0;
        a = (T[]) new Object[8];
        sentinel = new Node(7, 0);
    }

    private void resize(int capacity) {
        T[] newA = (T[]) new Object[capacity];

        if (sentinel.prev < sentinel.next) {
            System.arraycopy(a, sentinel.prev + 1, newA, 0, sentinel.next - sentinel.prev - 1);
            sentinel.prev = capacity - 1;
            sentinel.next = 0;
        } else {
            System.arraycopy(a, 0, newA, 0, sentinel.next); // left part
            System.arraycopy(a, sentinel.next, newA, capacity - (size - sentinel.next), size - sentinel.next); // right part
            sentinel.prev = capacity - (size - sentinel.next) - 1;
        }

        a = newA;
    }

    public void addFirst(T item) {
        if (size >= a.length) {
            resize(size + size / 4);
        } else if (size <= a.length / 4 && a.length >= 16) {
            resize(size + size / 4);
        }

        a[sentinel.prev] = item;
        sentinel.prev = sentinel.prev - 1;
        if (sentinel.prev < 0)
            sentinel.prev = a.length - 1;

        size++;
    }

    public void addLast(T item) {
        if (size >= a.length) {
            resize(size + size / 4);
        } else if (size <= a.length / 4 && a.length >= 16) {
            resize(size + size / 4);
        }

        a[sentinel.next] = item;
        sentinel.next = (sentinel.next + 1) % a.length;

        size++;
    }

    public T removeFirst() {
        if (size == 0) return null;

        sentinel.prev = (sentinel.prev + 1) % a.length;

        size--;
        return a[sentinel.prev];
    }

    public T removeLast() {
        if (size == 0) return null;

        sentinel.next = (sentinel.next - 1);
        if (sentinel.next < 0) {
            sentinel.next = a.length - 1;
        }

        size--;
        return a[sentinel.next];
    }

    public T get(int index) {
        if (index >= size) return null;

        // right part
        int rightSize = a.length - sentinel.prev - 1;

        if (rightSize >= index + 1 || sentinel.next > sentinel.prev) {
            return a[sentinel.prev + index + 1];
        } else {
            return a[index - rightSize];
        }
    }

    public void printDeque() {
        int num = size;

        while (num > 0) {
            num--;
            sentinel.prev = (sentinel.prev + 1) % a.length;

            System.out.println(a[sentinel.prev]);
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0 ? true : false;
    }
}

