package edu.ncsu.csc216.pack_scheduler.util;

import java.util.EmptyStackException;

/**
 * The ArrayStack class implements the Stack interface and allows users to add,
 * remove, and set the capacity of the stack. There are additional methods to
 * check the size and whether the stack is empty or not. This stack is
 * implemented as a ArrayList.
 * 
 * @author Daniel Nolting
 * @author Calli Hooks
 * @author Owen Loker
 * 
 * @param <E> generic type E
 */
public class ArrayStack<E> implements Stack<E> {

	/** Stack is implemented as an ArrayList */
	private ArrayList<E> list;

	/** Capacity of the stack */
	private int capacity;

	/**
	 * Constructs the ArrayStack object given the capacity and sets the capacity
	 * 
	 * @param capacity the capacity of the stack
	 */
	public ArrayStack(int capacity) {
		list = new ArrayList<E>();
		setCapacity(capacity);
	}

	/**
	 * Adds the element to the top of the stack.
	 * 
	 * @param element element to add
	 * @throws IllegalArgumentException if there is no room (capacity has been
	 *                                  reached)
	 */
	@Override
	public void push(E element) {
		if (list.size() == capacity) {
			throw new IllegalArgumentException();
		}
		list.add(element);
	}

	/**
	 * Removes the element to the top of the stack.
	 * 
	 * @return element element that was removed
	 * @throws EmptyStackException if stack is empty
	 */
	@Override
	public E pop() {
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		return list.remove(list.size() - 1);
	}

	/**
	 * Returns true if stack has no elements, else returns false
	 * 
	 * @return true if stack has no elements
	 */
	@Override
	public boolean isEmpty() {
		if (list.size() == 0) {
			return true;
		}
		return false;
	}

	/**
	 * Returns number of elements in stack
	 * 
	 * @return int number of elements in stack
	 */
	@Override
	public int size() {
		return list.size();
	}

	/**
	 * Sets the capacity of the stack
	 * 
	 * @param capacity the capacity of the stack
	 */
	@Override
	public void setCapacity(int capacity) {
		if (capacity < 0 || capacity < list.size()) {
			throw new IllegalArgumentException();
		}
		this.capacity = capacity;
	}

}
