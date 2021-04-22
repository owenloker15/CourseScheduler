package edu.ncsu.csc216.pack_scheduler.util;

/**
 * The LinkedListRecursive class creates a LinkedList using recursion. Elements
 * can be added, removed, set, or gotten. It has a inner class ListNode.
 * 
 * @author Daniel Nolting
 * @author Calli Hooks
 * @author Owen Loker
 *
 * @param <E> generic parameter type E
 */
public class LinkedListRecursive<E> {
	/** front ListNode */
	private ListNode front;
	/** size of LinkedList */
	private int size;

	/**
	 * Constructs LinkedListRecursive object by setting the front to null and size
	 * to 0
	 */
	public LinkedListRecursive() {
		front = null;
		size = 0;
	}

	/**
	 * Returns true if the list has no elements, else returns false
	 * 
	 * @return boolean representing whether the list is empty or not
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Returns size of list
	 * 
	 * @return the size field
	 */
	public int size() {
		return size;
	}

	/**
	 * Adds an element to the end of the list. Checks that the element isn’t already
	 * in the list and handles the special case of adding a node to an empty list.
	 * If the list is not empty, then the public method transfers the flow of
	 * control to the private add() method in the ListNode inner class
	 * 
	 * @param element element to be added to the list
	 * @return boolean representing whether the element was added
	 * @throws IllegalArgumentException if the element is already in the list
	 */
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

	/**
	 * Adds an element to the end of the list. Checks that the element isn’t already
	 * in the list and handles the special case of adding a node to the front of the
	 * list. Then the public method transfers the flow of control to the private
	 * add() method in the ListNode inner class.
	 * 
	 * @param idx     the index of the LinkedRecursiveList to add to element
	 * @param element the element to add at the given index
	 * @throws NullPointerException      if the element is null
	 * @throws IllegalArgumentException  if the element is already in the list
	 * @throws IndexOutOfBoundsException if the index is invalid
	 */
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
		} else {
			front.add(idx - 1, element);
		}

	}

	/**
	 * Gets an element at an index. Handles bounds checking on the index, then
	 * transfers the flow of control to the private get() method in the ListNode
	 * inner class.
	 * 
	 * @param idx the index of the element to return
	 * @return E data in the ListNode
	 * @throws IndexOutOfBoundsException if the index is invalid
	 */
	public E get(int idx) {
		if (idx < 0 || idx >= size) {
			throw new IndexOutOfBoundsException();
		}
		return front.get(idx);
	}

	/**
	 * Removes the given element and returns true if successfully removed. Checks if
	 * the element is null, if the list is empty, and handles the special case of
	 * removing the first node in the list. Then transfers the flow of control to
	 * the private remove() method in the ListNode inner class.
	 * 
	 * @param element the element to be removed
	 * @return boolean representing whether the element was removed
	 * @throws NullPointerException if the element is null
	 */
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

	/**
	 * Removes the given element and returns true if successfully removed. Handles
	 * bounds checking on the index and the special case of removing the first node
	 * in the list. Then transfers the flow of control to the private remove()
	 * method in the ListNode inner class.
	 * 
	 * @param idx the index of the element to be removed
	 * @return E data of the removed ListNode
	 * @throws IllegalArgumentException  if the list is empty
	 * @throws IndexOutOfBoundsException if the index is invalid
	 */
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

	/**
	 * Sets an element at an index. Handles bounds checking on the index, then
	 * transfers the flow of control to the private set() method in the ListNode
	 * inner class.
	 * 
	 * @param idx     the index of the ListNode to change the data of
	 * @param element the data to set in the ListNode
	 * @return E data previously in the ListNode
	 * @throws IllegalArgumentException  if the list is empty
	 * @throws IndexOutOfBoundsException if the index is invalid
	 */
	public E set(int idx, E element) {
		if (isEmpty()) {
			throw new IllegalArgumentException();
		}
		if (idx < 0 || idx >= size) {
			throw new IndexOutOfBoundsException();
		}

		return front.set(idx, element);
	}

	/**
	 * Checks if an element already exists in the list. This will be used when
	 * adding to ensure that we have no duplicates. Handles the special case of an
	 * empty list. If the list is not empty, then the method transfers the flow of
	 * control to the private contains() method in the ListNode inner class. Returns
	 * true if the element is in the list, else returns false.
	 * 
	 * @param element the element to check if it already exists in the list
	 * @return boolean representing whether the given element is already in the list
	 */
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
		 * 
		 * @param data the ListNode should contain
		 * @param next the next node in the list
		 */
		public ListNode(E data, ListNode next) {
			this.data = data;
			this.next = next;
		}

		/**
		 * Completes the recursion to add to element to the end of the list.
		 * 
		 * @param element the element to add to the list
		 * @return boolean representing whether the element was added to the list
		 */
		private boolean add(E element) {
			if (next == null) {
				next = new ListNode(element, null);
				size++;
				return true;
			} else {
				return next.add(element);
			}
		}

		/**
		 * Completes the recursion to add to element to the appropriate location.
		 * 
		 * @param idx     the index to add the element
		 * @param element the element to add at the given index
		 */
		private void add(int idx, E element) {
			if (idx == 0) {
				next = new ListNode(element, next);
				size++;
			} else {
				next.add(idx - 1, element);
			}
		}

		/**
		 * Completes the recursion to get to element at the appropriate location.
		 * 
		 * @param idx the index of the element to return
		 * @return E the data of the element at the given index
		 */
		private E get(int idx) {
			if (idx == 0) {
				return data;
			} else {
				return next.get(idx - 1);
			}
		}

		/**
		 * Completes the recursion to remove to element at the appropriate location.
		 * 
		 * @param element the element to remove
		 * @return boolean representing whether the element was removedF
		 */
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

		/**
		 * Completes the recursion to remove to element at the appropriate location.
		 * 
		 * @param idx the index of the element to remove
		 * @return E data of the removed ListNode
		 */
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

		/**
		 * Completes the recursion to set to element at the appropriate location.
		 * 
		 * @param idx     the index of the element to set
		 * @param element the data of that the element should be changed to
		 * @return E data previously in the ListNode
		 */
		private E set(int idx, E element) {
			if (idx == 0) {
				E returnValue = data;
				data = element;
				return returnValue;
			} else {
				return next.set(idx - 1, element);
			}
		}

		/**
		 * Completes the recursion to check the elements in the list. Returns true if
		 * the list contains the given element, else returns false.
		 * 
		 * @param element the element to be checked if it exists in the list
		 * @return boolean representing whether the element is already in the list
		 */
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
