import java.io.PrintStream;
import java.util.NoSuchElementException;

public class DoubleQueueImpl<T> implements DoubleQueue<T>{
    public Node<T> head;
    public Node<T> tail;
    public int counter;


    public DoubleQueueImpl(){
        this.head = null;
        this.tail = null;
    }

    @Override
    public boolean isEmpty(){
        return head==null;
    }

    @Override
    public void put(T item){
        if(isEmpty()){
            this.head = new Node<>(head,item);
            this.tail = this.head; 
        }
        else{
            tail.next = new Node<>(null,item);
            tail = tail.next;
        }
        counter++;
    }

    @Override
    public T get() throws NoSuchElementException{
        if(isEmpty())
            throw new NoSuchElementException("Empty queue!");
        T temp = head.value;
        this.head = head.next;
        counter--;
        return temp; 
    }

    @Override
    public T peek() throws NoSuchElementException{
        if(isEmpty())
            throw new NoSuchElementException("Empty queue!");
        return head.value;
    }

    @Override
    public void printQueue(PrintStream stream){
        for(Node<T> x=head;x!=null;x=x.next){
            stream.println(x.value);
        }
    }

    @Override
    public int size(){
        return counter;
    }
}