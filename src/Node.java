public class Node<T>{
    public Node<T> next;
    public T value;
    public Node(Node<T> next, T value){
        this.next = next;
        this.value = value;
    }
}