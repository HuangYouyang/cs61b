public class ArrayDeque<T> {
    private class node{
        private int prev;
        private int next;

        public node(int p, int n){
            prev = p;
            next = n;
        }
    }

    private int size;
    private T[] a;
    private node sentinel;

    public ArrayDeque(){
        size = 0;
        a = (T[]) new Object[8];
        sentinel = new node(7, 0);
    }

    public void resize(int capacity){
        T[] newA = (T[]) new Object[capacity];

        System.arraycopy(a, 0, newA, 0, sentinel.next); // left part
        System.arraycopy(a, sentinel.next, newA, capacity-(size-sentinel.next), size-sentinel.next); // right part
        a = newA;

        sentinel.prev = capacity-(size-sentinel.next)-1;
    }

    public void addFirst(T item){
        if(size >= a.length) {
            resize(size + size / 4);
        }

        a[sentinel.prev] = item;
        sentinel.prev = sentinel.prev-1;
        if(sentinel.prev < 0)
            sentinel.prev = a.length;

        size++;
    }

    public void addLast(T item){
        if(size >= a.length){
            resize(size+size/4);
        }

        a[sentinel.next] = item;
        sentinel.next = (sentinel.next+1)%a.length;

        size++;
    }

    public void removeFirst(){
        if(size==0) return;

        sentinel.prev = (sentinel.prev+1) % a.length;

        size--;
    }

    public void removeLast(){
        if(size==0) return;

        sentinel.next = (sentinel.next-1);
        if(sentinel.next < 0){
            sentinel.next = a.length-1;
        }

        size--;
    }

    public T get(int index){
        if(index >= size) return null;

        // right part
        int rightSize = a.length-sentinel.prev-1;
        if(rightSize >= index+1){
            return a[sentinel.prev+index+1];
        }
        else{
            return a[index-rightSize];
        }
    }

    public void printDeque(){
        int num = size;

        while(num>0){
            num--;
            sentinel.prev = (sentinel.prev+1)%a.length;

            System.out.println(a[sentinel.prev]);
        }
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size==0 ? true : false;
    }
}

