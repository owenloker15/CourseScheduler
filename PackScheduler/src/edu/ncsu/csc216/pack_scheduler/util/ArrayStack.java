package edu.ncsu.csc216.pack_scheduler.util;

import java.util.EmptyStackException;

public class ArrayStack<E> implements Stack<E> {
	
	private ArrayList<E> list;
	
	private int capacity;
	
	public ArrayStack(int capacity) {
		list = new ArrayList<E>();
		setCapacity(capacity);
	}
	
	@Override
	public void push(E element) {
		if(list.size() == capacity) {
			throw new IllegalArgumentException();
		}
		list.add(element);
	}

	@Override
	public E pop() {
		if(isEmpty()) {
			throw new EmptyStackException();
		}
		return list.remove(list.size() - 1);
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
