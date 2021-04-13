package edu.ncsu.csc216.pack_scheduler.util;

import java.util.NoSuchElementException;

/**
 * The LinkedQueue class defines the methods needed for a Queue. The methods can add, remove, and
 * set the Capacity of the queue. There are additional methods to check the size
 * and whether the stack is empty or not. The queue is implemented as a LinkedList;.
 * 
 * @author Daniel Nolting
 * @author Calli Hooks
 * @author Owen Loker
 *
 * @param <E> generic type E
 */
public class LinkedQueue<E> implements Queue<E> {
	
	/** Queue is implemented as a ArrayList */
	private LinkedAbstractList<E> list;

	/** Capacity of the queue */
	private int capacity;

	/**
	 * Constructs the ArrayQueue object given the capacity and sets the capacity
	 * 
	 * @param capacity the capacity of the queue
	 */
	public LinkedQueue(int capacity) {
		list = new LinkedAbstractList<E>(capacity);
		setCapacity(capacity);
	}

	/**
	 * Places given element at back of queue
	 * 
	 * @param element element to add
	 * @throws IllegalArgumentException if there is no room (capacity has been
	 *                                  reached)
	 */
	@Override
	public void enqueue(E element) {
		if (list.size() == capacity) {
			throw new IllegalArgumentException();
		}
		if (isEmpty()) {
			list.add(0, element);
		} else {
			list.add(list.size(), element);
		} 
	}

	/**
	 * Removes value from front of queue and returns it
	 * 
	 * @return element element that was removed
	 * @throws NoSuchElementException if the queue is empty
	 */
	@Override
	public E dequeue() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		return list.remove(0);
	}

	/**
	 * Returns true if the queue has no elements, else returns false
	 * 
	 * @return true if the queue has no elements
	 */
	@Override
	public boolean isEmpty() {
		return list.size() == 0;
	}

	/**
	 * Returns the number of elements in the queue
	 * 
	 * @return int the number of elements in the queue
	 */
	@Override
	public int size() {
		return list.size();
	}

	/**
	 * Sets the capacity of the queue
	 * 
	 * @param capacity the capacity of the queue
	 */
	@Override
	public void setCapacity(int capacity) {
		if (capacity < 0 || capacity < list.size()) {
			throw new IllegalArgumentException();
		}
		this.capacity = capacity;

	}
	
	/** Checks to see if the queue contains the given element
	 * 
	 * @param element the element to search for in the queue
	 * @return true if the element is in the queue, false otherwise
	 */
	public boolean contains(E element) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).equals(element)) {
				return true;
			}
		}
		return false;
	}

}
