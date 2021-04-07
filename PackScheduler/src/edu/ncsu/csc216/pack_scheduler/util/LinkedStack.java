package edu.ncsu.csc216.pack_scheduler.util;

import java.util.EmptyStackException;

/**
 * 
 * @author owenloker
 *
 */
public class LinkedStack<E> implements Stack<E> {
	
	private int capacity;
	
	private LinkedAbstractList<E> list;
	
	public LinkedStack(int capacity) {
		list = new LinkedAbstractList<E>(capacity);
		setCapacity(capacity);
	}
	
	@Override
	public void push(E element) {
		if(list.size() == capacity) {
			throw new IllegalArgumentException();
		}
		list.add(0, element);
	}

	@Override
	public E pop() {
		if(isEmpty()) {
			throw new EmptyStackException();
		}
		return list.remove(0);
	}

	@Override
	public boolean isEmpty() {
		if(list.size() == 0) {
			return true;
		}
		return false;
	}

	@Override
	public int size() {
		return list.size();
	}

	@Override
	public void setCapacity(int capacity) {
		if(capacity < 0 || capacity < list.size() ) {
			throw new IllegalArgumentException();
		}
		this.capacity = capacity;
	}

}
