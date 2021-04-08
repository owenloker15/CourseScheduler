package edu.ncsu.csc216.pack_scheduler.util;

/**
 * The Queue interface defines the methods needed for a Queue that may be
 * implemented as an ArrayList or a LinkedList. The methods can add, remove, and
 * set the Capacity of the queue. There are additional methods to check the size
 * and whether the stack is empty or not.
 * 
 * @author Daniel Nolting
 * @author Calli Hooks
 * @author Owen Loker
 *
 * @param <E> generic type E
 */
public interface Queue<E> {

	/**
	 * Places given element at back of queue
	 * 
	 * @param element element to add
	 * @throws IllegalArgumentException if there is no room (capacity has been
	 *                                  reached)
	 */
	void enqueue(E element);

	/**
	 * Removes value from front of queue and returns it
	 * 
	 * @return element element that was removed
	 * @throws NoSuchElementException if the queue is empty
	 */
	E dequeue();

	/**
	 * Returns true if the queue has no elements, else returns false
	 * 
	 * @return true if the queue has no elements
	 */
	boolean isEmpty();

	/**
	 * Returns the number of elements in the queue
	 * 
	 * @return int the number of elements in the queue
	 */
	int size();

	/**
	 * Sets the capacity of the queue
	 * 
	 * @param capacity the capacity of the queue
	 */
	void setCapacity(int capacity);
}
