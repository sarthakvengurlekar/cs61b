package deque;

public class LinkedListDeque<T> {

    private class Node{
        private T item;
        private Node next;
        private Node prev;

        public Node(T item, Node prev, Node next){
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    private Node sentinel;
    private int size;

    /*Create an empty LinkedListDeque*/
    public LinkedListDeque(){
        sentinel = new Node(null, null, null);
        size = 0;
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }

    /*Create a deep copy of other Deque*/
    public LinkedListDeque(LinkedListDeque<T> other){
        this();
        for(int i = 0; i < other.size(); i++){
            addLast(other.get(i));
        }
    }

    public void addFirst(T item){
        Node p = new Node(item, sentinel, sentinel.next);
        sentinel.next.prev = p;
        sentinel.next = p;
        size++;
    }

    public void addLast(T item){
        Node p = new Node(item, sentinel.prev, sentinel);
        sentinel.prev.next = p;
        sentinel.prev = p;
        size++;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        Node p = sentinel.next;
        for(int i = 0; i < size; i++){
            System.out.print(p.item + " ");
            p = p.next;
        }
        System.out.println();
    }

    public T removeFirst(){
        if(isEmpty()){
            return null;
        }
        Node p = sentinel.next;
        sentinel.next = p.next;
        p.next.prev = sentinel;
        size--;
        return p.item;
    }

    public T removeLast(){
        if(isEmpty()){
            return null;
        }
        Node p = sentinel.prev;
        sentinel.prev = p.prev;
        p.prev.next = sentinel;
        size--;
        return p.item;

    }

    public T get(int index){
        if(index < 0 || index >= size){
            return null;
        }
        Node p = sentinel.next;
        for(int i = 0; i < index; i++){
            p = p.next;
        }
        return p.item;
    }

    public T getRecursive(int index){
        if(index < 0 || index >= size){
            return null;
        }
        return getRecursiveHelper(sentinel.next, index);
    }

    private T getRecursiveHelper(Node node, int index){
        if(index == 0){
            return node.item;
        }
        return getRecursiveHelper(node.next, index - 1);
    }

    public static void main(String[] args) {

        LinkedListDeque<Integer> L = new LinkedListDeque<>();
        L.addFirst(8);
        L.addFirst(20);
        L.addFirst(57);
        L.addLast(4);

        L.printDeque();

    }
}
