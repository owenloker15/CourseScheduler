package edu.ncsu.csc216.pack_scheduler.util;

/**
 * The Stack interface defines the methods needed for a Stack that may be
 * implemented as an ArrayList or a LinkedList. The methods can add, remove, and
 * set the Capacity of the stack. There are additional methods to check the size
 * and whether the stack is empty or not.
 * 
 * @author Daniel Nolting
 * @author Calli Hooks
 * @author Owen Loker
 *
 * @param <E> generic type E
 */
public interface Stack<E> {

	/**
	 * Adds the element to the top of the stack.
	 * 
	 * @param element element to add
	 * @throws IllegalArgumentException if there is no room (capacity has been
	 *                                  reached)
	 */
	void push(E element);

	/**
	 * Removes the element to the top of the stack.
	 * 
	 * @return element element that was removed
	 * @throws EmptyStackException if stack is empty
	 */
	E pop();

	/**
	 * Returns true if stack has no elements, else returns false
	 * 
	 * @return true if stack has no elements
	 */
	boolean isEmpty();

	/**
	 * Returns number of elements in stack
	 * 
	 * @return int number of elements in stack
	 */
	int size();

	/**
	 * Sets the capacity of the stack
	 * 
	 * @param capacity the capacity of the stack
	 */
	void setCapacity(int capacity);

}
