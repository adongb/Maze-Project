package StackPD;

public interface Stack<E> {
	
public int size();
    
	public boolean isEmpty();
	
	public void push(E element);

	public E top();

	public E pop();


}
