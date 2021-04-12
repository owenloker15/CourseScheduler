package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractList;

/**
 * Linked list made of linked nodes that has parameters for number of elements in list,
 * maximum capacity of list, and node for front of list
 * Class has private inner class for linked nodes of list
 * @author Kelsey Hanser, Daniel Nolting, Nick Bleuzen
 *
 * @param <E> type of data stored in list
 */
public class LinkedAbstractList<E> extends AbstractList<E> {
	/** Number of elements in list*/
	private int size;
	/** Maximum number of elements in list*/
	private int capacity;
	/** First node in the list*/
	private ListNode front;
	/** Last node in the list*/
	private ListNode back;
	
	/**
	 * Constructor for LinkedAbstractList
	 * Initializes size to 0, front to null, and capacity to capacity if it is greater than or equal to 0
	 * @param capacity maximum capacity for list
	 * @throws IllegalArgumentException if capacity is less than 0
	 */
	public LinkedAbstractList(int capacity) {
		front = null;
		size = 0;
		if(capacity < size) {
			throw new IllegalArgumentException();
		}
		this.capacity = capacity;
	}
	
	/**
	 * Returns size of list
	 * @returns size of list
	 */
	@Override
	public int size() {
		return size;
	}
	
	/**
	 * Adds element at given index
	 * Cannot add duplicate elements
	 * @param idx index to add element at
	 * @param element to be added
	 * @throws IllegalArgumentException if list is at capacity or element is a duplicate of an element already in list
	 * @throws IndexOutofBounds if idx is less than 0 or greater than size
	 * @throws NullPointerExcepiton if element is null
	 */
	@Override
	public void add(int idx, E element) {
		int sizeOld = size();
		if(size == capacity) {
			throw new IllegalArgumentException();
		}
		if(element == null) {
			throw new NullPointerException("Element cannot be null.");
		}
		if(idx < 0 || idx > size()) {
			throw new IndexOutOfBoundsException();
		}
		
		for (int i = 0; i < idx; i++) {
			if(element.equals(get(i))) {
				throw new IllegalArgumentException("Element cannot be a duplicate.");
			}
		}
		
		ListNode insert = front;
		for (int i = 0; i < idx; i++) {
			insert = insert.next;
		}
		
		ListNode newNode = new ListNode(element, insert);
		if (insert == front) {
			back = front;
			front = newNode;
		}
		else {
			ListNode prior = front;
			for (int i = 0; i < idx - 1; i++) {
				prior = prior.next;
			}
			prior.next = newNode;
			if (idx == sizeOld) {
				back = newNode;
			}
			else {
				ListNode current = front;
				ListNode next = current.next;
				while(next != null) {
					current = next;
					next = current.next;
				}
				this.back = current;
			}
		}
		
//		if(idx == 0) {
//			if(front == null) {
//				front = new ListNode(element);
//			} else {
//				front = new ListNode(element, front);
//			}
//		} else {
//			current = front;
//			for(int i = 0; i < idx - 1; i++) {
//				current = current.next;
//			}
//			current.next = new ListNode(element, current.next);
//		}
		size++;
	}
	
	/**
	 * Removes element at given index and returns removed element
	 * @param idx of element to be removed
	 * @return removed element
	 * @throws IndexOutOfBoundsException if idx is less than 0 or greater than size
	 */
	public E remove(int idx) {
		if(idx < 0 || idx >= size()) {
			throw new IndexOutOfBoundsException();
		}
		
		ListNode removed = front;
		for(int i = 0; i < idx; i++) {
			removed = removed.next;
		}
		E removedData = removed.data;
		
		
		if(idx == 0) {
			front  = front.next;
		} else {
			ListNode prior = front;
			for(int i = 0; i < idx - 1; i++) {
				prior = prior.next;
			}
			ListNode after = removed.next;
			prior.next = after;
		}
		if (size == 0) {
			ListNode current = front;
			ListNode next = current.next;
			while (next != null) {
				current = next;
				next = current.next;
			}
			this.back = current;
		}
		size--;
		return removedData;
	}
	
	/**
	 * Returns element at given index
	 * @param idx of element to be returned
	 * @return data stored in node at given index
	 * @throws IndexOutOfBoundsException if idx is less than 0 or greater than size
	 */
	@Override
	public E get(int idx) {
		if(idx < 0 || idx >= size()) {
			throw new IndexOutOfBoundsException();
		}
		
		ListNode current = front;
		for(int i = 0; i < idx; i++) {
			current = current.next;
		}
		
		return current.data;
	}
	
	/**
	 * Sets element at given index to new data
	 * @param idx index to set element at
	 * @param element to be set
	 * @throws NullPointerException if element is null
	 * @throws IndexOutOfBoundsException if idx is less than 0 or greater than size
	 */
	@Override
	public E set(int idx, E element) {
		if (element == null) {
			throw new NullPointerException();
		}
		if(idx < 0 || idx >= size()) {
			throw new IndexOutOfBoundsException();
		}
		
		ListNode current = front;
		while(current != null) {
			if(current.data.equals(element)) {
				throw new IllegalArgumentException();
			}
			current = current.next;
		}
		
		current = front;
		for(int i = 0; i < idx; i++) {
			current = current.next;
		}
		E removed = current.data;
		current.data = element;
		return removed;
	}
	
	/**
	 * Sets the capacity of the LinkedAbstractList
	 * @param capacity the new capacity to set
	 * @throws IllegalArgumentException if capacity is less than 0 or the current capacity
	 */
	public void setCapacity(int capacity) {
		if (capacity < 0 || capacity < this.capacity) {
			throw new IllegalArgumentException();
		}
		this.capacity = capacity;
	}
	
	/**
	 * Inner class of LinkedAbstractList that represents each element in list
	 * Parameters for data of element and next ListNode in list
	 * @author Kelsey Hanser, Daniel Nolting, Nick Bleuzen
	 *
	 */
	private class ListNode {
		/** Data stored in node*/
		private E data;
		/** Next node in list*/
		private ListNode next;
		
		/**
		 * Constructor for LinkedNode with one parameter
		 * Sets data to data passed in, next to null
		 * @param data of element
		 */
		public ListNode(E data) {
			this(data, null);
		}
		
		/**
		 * Constructor for LinkedNode with one parameter
		 * Sets data to data passed in, next to next passed in
		 * @param data of element
		 * @param next ListNode in list
		 */
		public ListNode(E data, ListNode next) {
			this.data = data;
			this.next = next;
		}
	}
}
