package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractSequentialList;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class LinkedList<E> extends AbstractSequentialList<E>{

	private ListNode front;
	private ListNode back;
	private int size;
	
	public LinkedList() {
		front = new ListNode(null);
		back = new ListNode(null);
		front.next = back;
		back.prev = front;
		size = 0;
	}
	
	
	@Override
	public ListIterator<E> listIterator(int index) {
		return new LinkedListIterator(index);
	}

	@Override
	public int size() {
		return size;
	}
	
	
	
	
	@Override
	public void add(int index, E element) {
		if (contains(element)) {
			throw new IllegalArgumentException();
		}
		super.add(index, element);
	}


	@Override
	public E set(int index, E element) {
		if (contains(element)) {
			throw new IllegalArgumentException();
		}
		return super.set(index, element);
	}




	private class LinkedListIterator implements ListIterator<E> {

		ListNode previous;
		ListNode next;
		int previousIndex;
		int nextIndex;
		ListNode lastRetrieved;
		
		public LinkedListIterator(int index) { 
			  if (index < 0 || index > size) {
				  throw new IndexOutOfBoundsException();
			  }
			  ListNode current = front;
			  for (int i = 0; i < index; i++) {
				  current = current.next;
			  }
			  next = current.next;
			  previous = current;
			  previousIndex = index - 1;
			  nextIndex = index;
			  lastRetrieved = null;
		}
		
		@Override
		public boolean hasNext() {
			
			return nextIndex < size;
		}

		@Override
		public E next() {
			if (!hasNext() || next.data == null) {
				throw new NoSuchElementException();
			}
			E returnData = next.data;
			lastRetrieved = next;
			next = next.next;
			previous = next.prev;
			nextIndex++;
			previousIndex++;
			return returnData;
			
		}

		@Override
		public boolean hasPrevious() {
			return previousIndex > 0;
		}

		@Override
		public E previous() {
			if (!hasPrevious() || previous.data == null) {
				throw new NoSuchElementException();
			}
			E returnData = previous.data;
			lastRetrieved = previous;
			next = next.prev;
			previous = next;
			nextIndex++;
			previousIndex++;
			return returnData;
			
		}

		@Override
		public int nextIndex() {
			return nextIndex;
		}

		@Override
		public int previousIndex() {
			return previousIndex;
		}

		@Override
		public void remove() {
			if (lastRetrieved == null) {
				throw new IllegalStateException();
			}
			lastRetrieved.next.prev = previous;
			lastRetrieved.prev.next = next;
			size--;
			
		}

		@Override
		public void set(E e) {
			if (lastRetrieved == null) {
				throw new IllegalStateException();
			}
			if (e == null) {
				throw new NullPointerException();
			}
			LinkedList.this.set(nextIndex, e);
		}

		@Override
		public void add(E e) {
			if (e == null) {
				throw new NullPointerException();
			}
			lastRetrieved = null;
			if (contains(e)) {
				throw new IllegalArgumentException();
			}
			size++;
		}
		
	}
	
	private class ListNode {
		
		public E data;
		public ListNode next;
		public ListNode prev;
		
		public ListNode(E data) {
			this(data, null, null);
		}
		
		public ListNode(E data, ListNode next, ListNode prev) {
			this.data = data;
			this.next = next;
			this.prev = next;
		}
	}
	
	
}
