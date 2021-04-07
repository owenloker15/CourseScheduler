package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractList;

/**
 * Custom ArrayList for PackSchedule which prevents null elements 
 * from being added.
 * @author Daniel Nolting, Kelsey Hanser, Nick Bleuzen
 *
 * @param <E> the custom type of the ArrayList
 */
public class ArrayList<E> extends AbstractList<E> {
	
	/** The initial size of the ArrayList */
	private static final int INIT_SIZE = 10;
	/** The underlying list that makes up the ArrayList */
	private E[] list;
	/** The current size of the ArrayList */
	private int size;
	
	/** Constructs an ArrayList of INIT_SIZE */
	@SuppressWarnings("unchecked")
	public ArrayList() {
		list = (E[]) new Object[INIT_SIZE];
		size = 0;
	}
	
	@Override
	public int size() {
		return size;
	}
	
	@Override
	public void add(int idx, E element) {
		
		if (element == null) {
			throw new NullPointerException();
		}
		
		if(idx < 0 || idx > size) {
			throw new IndexOutOfBoundsException();
		}
		if(size == list.length) {
			growArray();
		}
		for(int i = 0; i < size; i++) {
			if(list[i].equals(element)) {
				throw new IllegalArgumentException();
			}
		}
		for(int i = size; i > idx; i--) {
			list[i] = list[i - 1];
		}
		list[idx] = element;
		size++;
	}

	@Override
	public E get(int index) {
		if(index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		return list[index];
	}
	
	@Override
	public E remove(int idx) {
		if(idx < 0 || idx >= size) {
			throw new IndexOutOfBoundsException();
		}
		E element = list[idx];
		for(int i = idx; i < size; i++) {
			list[i] = list[i + 1];
		}
		list[size - 1] = null;
		size--;
		return element;
	}
	
	@Override
	public E set(int idx, E element) {
		if (element == null) {
			throw new NullPointerException();
		}
		if(idx < 0 || idx >= size) {
			throw new IndexOutOfBoundsException();
		}
		for(int i = 0; i < size; i++) {
			if(list[i].equals(element)) {
				throw new IllegalArgumentException();
			}
		}
		E oldElement = list[idx];
		list[idx] = element;
		return oldElement;
	}
	
	@SuppressWarnings("unchecked")
	private void growArray() {
		E[] temp = (E[]) new Object[list.length * 2];
		for(int i = 0; i < list.length; i++) {
			temp[i] = list[i];
		}
		list = temp;
	}

}
