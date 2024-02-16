import java.util.LinkedList;

public class LinkedListDeque<T> {

    private class node{
        T item;
        node prev;
        node next;
        public node(T item, node prev, node next){
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    private int size;
    private node headSentinel;
    private node tailSentinel;

    //  Initialize the empty queue
    public LinkedListDeque(){
        this.size = 0;
        this.headSentinel = new node((T)"null", null ,null);
        this.tailSentinel = new node((T)"null", null, null);
    }

    public void addFirst(T item){
        node curr = new node(item, headSentinel, headSentinel.next);
        if(headSentinel.next != null){
            headSentinel.next.prev = curr;
        }
        else{
            curr.next = tailSentinel;
            tailSentinel.prev = curr;
        }
        headSentinel.next = curr;

        size++;
    }

    public void addLast(T item){
        node curr = new node(item, tailSentinel.prev, tailSentinel);
        if(tailSentinel.prev != null){
            tailSentinel.prev.next = curr;
        }
        else{
            curr.prev = headSentinel;
            headSentinel.next = curr;
        }
        tailSentinel.prev = curr;

        size++;
    }

    public void removeFirst(){
        headSentinel.next = headSentinel.next.next;
        if(headSentinel.next != null){
            headSentinel.next.prev = headSentinel;
        }
        else{
            tailSentinel.prev = null;
        }

        size--;
    }

    public void removeLast(){
        tailSentinel.prev = tailSentinel.prev.prev;
        if(tailSentinel.prev != null){
            tailSentinel.prev.next = tailSentinel;
        }
        else{
            headSentinel.next = null;
        }

        size--;
    }

    public T get(int index){
        if(index>size) return (T)"null";

        node curr = headSentinel.next;
        while(index>0){
            curr = curr.next;
            index--;
        }

        return curr.item;
    }

    public T getRecursive(int index, node curr){
        if(index==0) return curr.item;

        index--;
        return getRecursive(index, curr.next);
    }

    public T getRecursive(int index){
        if(index>size) return (T)"null";

        return getRecursive(index, headSentinel.next);
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size==0 ? true : false;
    }

    public void printDeque(){
        node curr = headSentinel.next;

        while(curr != null){
            System.out.println(curr.item+" ");
            curr = curr.next;
        }
    }
}
