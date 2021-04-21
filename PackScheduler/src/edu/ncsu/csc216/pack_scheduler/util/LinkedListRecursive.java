package edu.ncsu.csc216.pack_scheduler.util;

public class LinkedListRecursive<E> {
	
	private ListNode front;
	
	private int size;
	
	public LinkedListRecursive() {
		front = null;
		size = 0;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public int size() {
		return size;
	}
	
	public boolean add(E element) {
		if (element == null || contains(element)) {
			throw new IllegalArgumentException();
		}
		if (isEmpty()) {
			front = new ListNode(element, null);
			size++;
			return true;
		} else {
			return front.add(element);
		}
	}
	 
	public void add(int idx, E element) {
		if (element == null) {
			throw new NullPointerException();
		}
		if (contains(element)) {
			throw new IllegalArgumentException();
		}
		
		if (idx == 0) {
			front = new ListNode(element, front);
			size++;
			return;
		} 
		
		if (idx < 0 || idx > size) {
			throw new IndexOutOfBoundsException();
		}
		else {
			front.add(idx - 1, element);
		}

		
	}
	
	public E get(int idx) {
		if (idx < 0 || idx >= size) {
			throw new IndexOutOfBoundsException();
		}
		return front.get(idx);
	}
	
	public boolean remove(E element) {
		if (isEmpty()) {
			return false;
		} 
		if (element == null) {
			throw new NullPointerException();
		}
		if (front.data.equals(element)) {
			front = front.next;
			size--;
			return true;
		} else {
			return front.remove(element);
		}
	}
	
	public E remove(int idx) {
		if (isEmpty()) {
			throw new IllegalArgumentException();
		} 
		if (idx < 0 || idx >= size) {
			throw new IndexOutOfBoundsException();
		}
		if (idx == 0) {
			E returnValue = front.data;
			front = front.next;
			size--;
			return returnValue;
		} else {
			return front.remove(idx);
		}
	}
	
	public E set(int idx, E element) {
		if (isEmpty()) {
			throw new IllegalArgumentException();
		} 
		if (idx < 0 || idx >= size) {
			throw new IndexOutOfBoundsException();
		}
		
		return front.set(idx, element);
	}
	
	public boolean contains(E element) {
		if (element == null) {
			throw new NullPointerException();
		}
		if (isEmpty()) {
			return false;
		}
		return front.contains(element);
	}
	/**
	 * The ListNode class is an inner class of LinkedList. It constructs the Nodes
	 * of the LinkedList.
	 * 
	 * @author Daniel Nolting
	 * @author Calli Hooks
	 * @author Owen Loker
	 *
	 */
	private class ListNode {

		/** The data in the node */
		public E data;
		/** The next node in the list */
		public ListNode next;
	
	
		/**
		 * Constructs the ListNode object given the data for the node and the next node,

		 * 
		 * @param data the ListNode should contain
		 * @param next the next node in the list
		 */
		public ListNode(E data, ListNode next) {
			this.data = data;
			this.next = next;
		}
		
		
		private boolean add(E element) {
			if (next == null) {
				next = new ListNode(element, null);
				size++;
				return true;
			} else {
				return next.add(element);
			}
		}
		
		private void add(int idx, E element) {
			if (idx == 0) {
				next = new ListNode(element, next);
				size++;
			} else {
				next.add(idx - 1, element);
			}
		}
		
		private E get(int idx) {
			if (idx == 0) {
				return data;
			} else {
				return next.get(idx - 1);
			}
		}
		
		private boolean remove(E element) {
			if (next == null) {
				return false;
			} else if (next.data.equals(element)) {
				next = next.next;
				size--;
				return true;
			} else {
				return next.remove(element);
			}
		}
		
		private E remove(int idx) {
			if (idx == 1) {
				E returnValue = next.data;
				next = next.next;
				size--;
				return returnValue;
			} else {
				return next.remove(idx - 1);
			}
		}
		
		private E set(int idx, E element) {
			if (idx == 0) {
				E returnValue = data;
				data = element;
				return returnValue;
			} else {
				return next.set(idx - 1, element);
			}
		}
		
		private boolean contains(E element) {
			if (data == element) {
				return true;
			} else if (next == null) {
				return false;
			} else {
				return next.contains(element);
			}
		}
	}

}
