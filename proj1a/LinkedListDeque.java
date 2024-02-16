import java.util.LinkedList;

public class LinkedListDeque<T> {

    private class Node {
        T item;
        Node prev;
        Node next;

        public Node(T item, Node prev, Node next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    private int size;
    private Node headSentinel;
    private Node tailSentinel;

    //  Initialize the empty queue
    public LinkedListDeque() {
        this.size = 0;
        this.headSentinel = new Node((T) "null", null, null);
        this.tailSentinel = new Node((T) "null", null, null);
    }

    public void addFirst(T item) {
        Node curr = new Node(item, headSentinel, headSentinel.next);
        if (headSentinel.next != null) {
            headSentinel.next.prev = curr;
        } else {
            curr.next = tailSentinel;
            tailSentinel.prev = curr;
        }
        headSentinel.next = curr;

        size++;
    }

    public void addLast(T item) {
        Node curr = new Node(item, tailSentinel.prev, tailSentinel);
        if (tailSentinel.prev != null) {
            tailSentinel.prev.next = curr;
        } else {
            curr.prev = headSentinel;
            headSentinel.next = curr;
        }
        tailSentinel.prev = curr;

        size++;
    }

    public T removeFirst() {
        if (size == 0) return null;
        T res = headSentinel.next.item;

        headSentinel.next = headSentinel.next.next;
        if (headSentinel.next != null) {
            headSentinel.next.prev = headSentinel;
        } else {
            tailSentinel.prev = null;
        }

        size--;

        return res;
    }

    public T removeLast() {
        if (size == 0) return null;
        T res = tailSentinel.prev.item;

        tailSentinel.prev = tailSentinel.prev.prev;
        if (tailSentinel.prev != null) {
            tailSentinel.prev.next = tailSentinel;
        } else {
            headSentinel.next = null;
        }

        size--;
        return res;
    }

    public T get(int index) {
        if (index > size) return (T) "null";

        Node curr = headSentinel.next;
        while (index > 0) {
            curr = curr.next;
            index--;
        }

        return curr.item;
    }

    private T getRecursive(int index, Node curr) {
        if (index == 0) return curr.item;

        index--;
        return getRecursive(index, curr.next);
    }

    public T getRecursive(int index) {
        if (index > size) return (T) "null";

        return getRecursive(index, headSentinel.next);
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0 ? true : false;
    }

    public void printDeque() {
        Node curr = headSentinel.next;

        while (curr != null) {
            System.out.println(curr.item + " ");
            curr = curr.next;
        }
    }
}
