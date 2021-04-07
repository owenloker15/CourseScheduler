package edu.ncsu.csc217.collections.list;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests the SortedList class.
 * 
 * @author Daniel Nolting
 * @author Ki Miller
 * @author Sean McKone
 */
public class SortedListTest {

	/**
	 * Tests the constructor and basic functionality.
	 */
	@Test
	public void testSortedList() {
		SortedList<String> list = new SortedList<String>();
		assertEquals(0, list.size());
		assertFalse(list.contains("apple"));
		
		//Test that the list grows by adding at least 11 elements
		//Remember the list's initial capacity is 10
		list.add("apple");
		list.add("banana");
		list.add("orange");
		list.add("dragonfruit");
		list.add("mango");
		list.add("cherry");
		list.add("lemon");
		list.add("grapefruit");
		list.add("raspberry");
		list.add("grape");
		list.add("pomegranate");
		assertEquals(11, list.size());
	}

	/**
	 * Tests add()
	 */
	@Test
	public void testAdd() {
		SortedList<String> list = new SortedList<String>();
		
		list.add("banana");
		assertEquals(1, list.size());
		assertEquals("banana", list.get(0));
		
		//Test adding to the front, middle and back of the list
		list.add("apple");
		assertEquals(2, list.size());
		assertEquals("apple", list.get(0));
		assertEquals("banana", list.get(1));
		
		list.add("orange");
		assertEquals(3, list.size());
		assertEquals("apple", list.get(0));
		assertEquals("banana", list.get(1));
		assertEquals("orange", list.get(2));
		
		list.add("dragonfruit");
		assertEquals(4, list.size());
		assertEquals("apple", list.get(0));
		assertEquals("banana", list.get(1));
		assertEquals("dragonfruit", list.get(2));
		assertEquals("orange", list.get(3));
		
		//Test adding a null element
		try {
			list.add(null);
			fail();
		} catch (NullPointerException e) {
			assertEquals(4, list.size());
			assertEquals("apple", list.get(0));
			assertEquals("banana", list.get(1));
			assertEquals("dragonfruit", list.get(2));
			assertEquals("orange", list.get(3));
		}
		
		//Test adding a duplicate element
		try {
			list.add("apple");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(4, list.size());
			assertEquals("apple", list.get(0));
			assertEquals("banana", list.get(1));
			assertEquals("dragonfruit", list.get(2));
			assertEquals("orange", list.get(3));
		}
	}
	
	/**
	 * Tests get()
	 */
	@Test
	public void testGet() {
		SortedList<String> list = new SortedList<String>();
		
		//Since get() is used throughout the tests to check the
		//contents of the list, we don't need to test main flow functionality
		//here.  Instead this test method should focus on the error 
		//and boundary cases.
		
		//Test getting an element from an empty list
		try {
			list.get(0);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(list.size(), 0);
		}
		
		//Add some elements to the list
		list.add("mango");
		
		//Test getting an element at an index < 0
		try {
			list.get(-1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(list.size(), 1);
		}
		
		//Test getting an element at size
		try {
			list.get(list.size());
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(list.size(), 1);
		}
	}
	
	/**
	 * Tests remove()
	 */
	@Test
	public void testRemove() {
		SortedList<String> list = new SortedList<String>();
		
		//Test removing from an empty list
		try {
			list.remove(0);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(list.size(), 0);
		}
		
		//Add some elements to the list - at least 4
		list.add("apple");
		list.add("banana");
		list.add("orange");
		list.add("dragonfruit");
		list.add("mango");
		
		//Test removing an element at an index < 0
		try {
			list.remove(-1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(list.size(), 5);
		}
		
		//Test removing an element at size
		try {
			list.remove(list.size());
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(list.size(), 5);
		}
		
		//Test removing a middle element
		list.remove(2);
		assertEquals(list.size(), 4);
		assertEquals("apple", list.get(0));
		assertEquals("banana", list.get(1));
		assertEquals("mango", list.get(2));
		assertEquals("orange", list.get(3));
		
		//Test removing the last element
		list.remove(3);
		assertEquals(list.size(), 3);
		assertEquals("apple", list.get(0));
		assertEquals("banana", list.get(1));
		assertEquals("mango", list.get(2));
		
		//Test removing the first element
		list.remove(0);
		assertEquals(list.size(), 2);
		assertEquals("banana", list.get(0));
		assertEquals("mango", list.get(1));
		
		//Test removing the last element
		list.remove(1);
		assertEquals(list.size(), 1);
		assertEquals("banana", list.get(0));
	}
	
	/**
	 * Tests indexOf()
	 */
	@Test
	public void testIndexOf() {
		SortedList<String> list = new SortedList<String>();
		String appleStr = "apple";
		String blueberryStr = "blueberry";
		
		//Test indexOf on an empty list
		assertEquals(list.indexOf(appleStr), -1);
		
		//Add some elements
		list.add(appleStr);
		list.add(blueberryStr);
		
		//Test various calls to indexOf for elements in the list
		//and not in the list
		assertEquals(list.indexOf(appleStr), 0);
		assertEquals(list.indexOf(blueberryStr), 1);
		assertEquals(list.indexOf("orange"), -1);
		
		//Test checking the index of null
		try {
			list.indexOf(null);
			fail();
		} catch (NullPointerException e) {
			assertSame(list.size(), 2);
		}
	}
	
	/**
	 * Tests clear()
	 */
	@Test
	public void testClear() {
		SortedList<String> list = new SortedList<String>();

		//Add some elements
		list.add("apple");
		list.add("banana");
		list.add("orange");
		list.add("dragonfruit");
		list.add("mango");
		
		//Clear the list
		list.clear();
		
		//Test that the list is empty
		assertSame(list.size(), 0);
	}

	/**
	 * Tests isEmpty()
	 */
	@Test
	public void testIsEmpty() {
		SortedList<String> list = new SortedList<String>();
		
		//Test that the list starts empty
		assertTrue(list.isEmpty());
		
		//Add at least one element
		list.add("apple");
		
		//Check that the list is no longer empty
		assertFalse(list.isEmpty());
	}

	/**
	 * Tests contains()
	 */
	@Test
	public void testContains() {
		SortedList<String> list = new SortedList<String>();
		
		//Test the empty list case
		assertFalse(list.contains("apple"));
		
		//Add some elements
		list.add("apple");
		
		//Test some true and false cases
		assertTrue(list.contains("apple"));
		assertFalse(list.contains("blackberry"));
	}
	
	/**
	 * Tests equals()
	 */
	@Test
	public void testEquals() {
		SortedList<String> list1 = new SortedList<String>();
		SortedList<String> list2 = new SortedList<String>();
		SortedList<String> list3 = new SortedList<String>();
		
		//Make two lists the same and one list different
		list1.add("apple");
		list2.add("apple");
		list3.add("banana");
		
		//Test for equality and non-equality
		assertTrue(list1.equals(list2));
		assertFalse(list1.equals(list3));
	}
	
	/**
	 * Tests hashCode()
	 */
	@Test
	public void testHashCode() {
		SortedList<String> list1 = new SortedList<String>();
		SortedList<String> list2 = new SortedList<String>();
		SortedList<String> list3 = new SortedList<String>();
		
		//Make two lists the same and one list different
		list1.add("apple");
		list2.add("apple");
		list3.add("banana");
		
		//Test for the same and different hashCodes
		assertEquals(list1.hashCode(), list2.hashCode());
		assertNotEquals(list2.hashCode(), list3.hashCode());
	}

}
 