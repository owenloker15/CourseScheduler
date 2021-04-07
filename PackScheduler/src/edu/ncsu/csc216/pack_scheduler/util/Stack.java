package edu.ncsu.csc216.pack_scheduler.util;

public interface Stack<E> {
	
	/**
	 * Adds the element to the top of the stack.
	 * @param element element to add
	 * @throws IllegalArgumentException if there is no room (capacity has been reached)
	 */
	void push(E element);
	
	E pop();
	
	boolean isEmpty();
	
	int size();
	
	void setCapacity(int capacity);

}
