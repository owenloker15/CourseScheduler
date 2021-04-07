package edu.ncsu.csc216.pack_scheduler.util;

import java.util.EmptyStackException;
import java.util.NoSuchElementException;

public class ArrayQueue<E> implements Queue<E>{

	private ArrayList<E> list;
	
	private int capacity;
	
	public ArrayQueue(int capacity) {
		list = new ArrayList<E>();
		setCapacity(capacity);
	}
	
	@Override
	public void enqueue(E element) {
		if(list.size() == capacity) {
			throw new IllegalArgumentException();
		}
		list.add(list.size() - 1, element);
	}

	@Override
	public E dequeue() {
		if(isEmpty()) {
			throw new NoSuchElementException();
		}
		return list.remove(0);
	}

	@Override
	public boolean isEmpty() {
		return (list.size() == 0);
	}

	@Override
	public int size() {
		return list.size();
	}

	@Override
	public void setCapacity(int capacity) {
		if (capacity < 0 || capacity < list.size()) {
			throw new IllegalArgumentException();
		}
		this.capacity = capacity;
		
	}
	
	

}
