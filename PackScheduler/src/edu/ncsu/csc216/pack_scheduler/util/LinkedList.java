package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractSequentialList;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * The LinkedList class inherits from AbstractSequentialList. It has two inner
 * classes: ListNode and LinkedListIterator. LinkedList.add() and
 * LinkedList.set() provide custom behaviors that do not allow duplicates.
 * 
 * @author Daniel Nolting
 * @author Calli Hooks
 * @author Owen Loker
 *
 * @param <E> the data type in the node
 */
public class LinkedList<E> extends AbstractSequentialList<E> {

	/** Front ListNode */
	private ListNode front;
	/** Back ListNode */
	private ListNode back;
	/** size of the list */
	private int size;

	/**
	 * Constructs the LinkedList object. Front and back nodes are constructed and
	 * set to null, and the size is 0.
	 */
	public LinkedList() {
		front = new ListNode(null);
		back = new ListNode(null);
		front.next = back;
		back.prev = front;
		size = 0;
	}

	/**
	 * Returns a ListIterator that is positioned such that a call to
	 * ListIterator.next() will return the element at given index
	 * 
	 * @param index the index that a call to next() will return the element of
	 * @return ListIterator that is positioned such that a call to next() will
	 *         return the element at given index
	 */
	@Override
	public ListIterator<E> listIterator(int index) {
		return new LinkedListIterator(index);
	}

	/**
	 * Returns the size of the LinkedList
	 * 
	 * @return size the size of the LinkedList
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Adds the given element at the given index
	 * 
	 * @param index   the index the element will be added at
	 * @param element the element to be added
	 */
//	@Override
//	public void add(int index, E element) {
//		if (contains(element)) {
//			throw new IllegalArgumentException();
//		}
//		super.add(index, element);
//	}

	/**
	 * Sets the given element at the given index
	 * 
	 * @param index   the index the element to be set
	 * @param element the element to be set
	 */
	@Override
	public E set(int index, E element) {
		if (contains(element)) {
			throw new IllegalArgumentException();
		}
		return super.set(index, element);
	}

	/**
	 * The private inner class LinkedListIterator implements the ListIterator
	 * interface. The LinkedListIterator represents the current location of the
	 * LinkedListIterator in the LinkedList and is always between two ListNodes.
	 * 
	 * @author Daniel Nolting
	 * @author Calli Hooks
	 * @author Owen Loker
	 *
	 */
	private class LinkedListIterator implements ListIterator<E> {

		/** The ListNode that would be returned on a call to previous() */
		ListNode previous;
		/** the ListNode that would be returned on a call to next() */
		ListNode next;
		/** The index that would be returned on a call to previousIndex() */
		int previousIndex;
		/** he index that would be returned on a call to nextIndex() */
		int nextIndex;
		/** The ListNode returned on the last call to either previous() or next() */
		ListNode lastRetrieved;

		/**
		 * Constructs the LinkedListIterator object that lies between 2 nodes in a
		 * LinkedList. It can identify if there are next or previous elements, and what
		 * they are.
		 * 
		 * @param index the index to position the iterator
		 */
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

		/**
		 * Returns true if the LinkedList has a next element, else returns false
		 * 
		 * @return boolean representing whether the LinkedList has a next element
		 */
		@Override
		public boolean hasNext() {
			return nextIndex < size;
		}

		/**
		 * Returns the next element in the LinkedList
		 * 
		 * @return E the next element in the LinkedList
		 */
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

		/**
		 * Returns true if the LinkedList has a previous element, else returns false
		 * 
		 * @return boolean representing whether the LinkedList has a previous element
		 */
		@Override
		public boolean hasPrevious() {
			return previousIndex > 0;
		}

		/**
		 * Returns the previous element in the LinkedList
		 * 
		 * @return E the previous element in the LinkedList
		 */
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

		/**
		 * Returns the nextIndex of the LinkedList
		 * 
		 * @return E the nextIndex of the LinkedList
		 */
		@Override
		public int nextIndex() {
			return nextIndex;
		}

		/**
		 * Returns the previousIndex of the LinkedList
		 * 
		 * @return E the previousIndex of the LinkedList
		 */
		@Override
		public int previousIndex() {
			return previousIndex;
		}

		/**
		 * Removes the lastRetrieved ListNode and decrements the size
		 */
		@Override
		public void remove() {
			if (lastRetrieved == null) {
				throw new IllegalStateException();
			}
			lastRetrieved.next.prev = previous;
			lastRetrieved.prev.next = next;
			size--;

		}

		/**
		 * Modifies the element returned by the last call to previous() or next()
		 * 
		 * @param e the lastRetrieved element
		 */
		@Override
		public void set(E e) {
			if (lastRetrieved == null) {
				throw new IllegalStateException();
			}
			if (e == null) {
				throw new NullPointerException();
			}

			// TODO set the element

		}

		/**
		 * Inserts the element before the element that would be returned by next()
		 * 
		 * @param e the element to be added
		 */
		@Override
		public void add(E e) {
			if (e == null) {
				throw new NullPointerException();
			}
			
			ListNode n = null;
			if (previousIndex == -1) {
			    n = new ListNode(e);
			} else {
				n = new ListNode(e, previous, next);
			}
			
			previous = n.prev;
			
			next = n;
			
			lastRetrieved = null;
			
			size++;
			
		}

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
		/** The previous node in the list */
		public ListNode prev;

		/**
		 * Constucts the ListNode object given the data for the node. Sets next and
		 * previous as null.
		 * 
		 * @param data the ListNode should contain
		 */
		public ListNode(E data) {
			this(data, null, null);
		}

		/**
		 * Constructs the ListNode object given the data for the node, the next node,
		 * and the previous node.
		 * 
		 * @param data the ListNode should contain
		 * @param next the next node in the list
		 * @param prev the previous node in the list
		 */
		public ListNode(E data, ListNode prev, ListNode next) {
			this.data = data;
			this.next = next;
			this.prev = next;
		}
	}

}
