import java.util.NoSuchElementException;
import java.io.PrintStream;
public class StringStackImpl<T> implements StringStack<T> {

    private Node<T> head;
    private int counter;

    public StringStackImpl(){
        this.head = null;
    }

    @Override
    public boolean isEmpty(){ //isos thelei boolean
        return head==null;
    }

    @Override
    public void push(T item){
        this.head = new Node<>(head, item);
        counter++;
    }

    @Override
    public T pop() throws NoSuchElementException{
        if(isEmpty()){
            throw new NoSuchElementException("Empty stack!");
        }
        T temp = head.value;
        this.head = head.next;
        counter--;
        return temp;
    }

    @Override
    public T peek() throws NoSuchElementException{
        if(isEmpty()){
            throw new NoSuchElementException("Empty stack!");
        }
        return head.value;
    }

    @Override
    public void printStack(PrintStream stream){
        for(Node<T> x=head;x!=null;x=x.next){
            stream.println(x.value);
        }
    }

    @Override
    public int size(){
        return counter;
    }
}