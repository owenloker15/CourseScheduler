package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test class for LinkedAbstractList
 * @author Kelsey Hanser, Daniel Nolting, Nick Bleuzen
 *
 */
public class LinkedAbstractListTest {

	/**
	 * Tests constuctor for LinkedAbsractList
	 */
	@Test
	public void testLinkedAbsractList() {
		LinkedAbstractList<String> list = new LinkedAbstractList<String>(10);
		assertEquals(0, list.size());
	}
	
	/**
	 * Tests add method for LinkedAbstractList
	 */
	@Test
	public void testAdd() {
		LinkedAbstractList<String> list = new LinkedAbstractList<String>(10);
		
		list.add(0, "apple");
		assertEquals(1, list.size());
		assertEquals("apple", list.get(0));
		
		list.add(1, "banana");
		assertEquals(2, list.size());
		assertEquals("apple", list.get(0));
		assertEquals("banana", list.get(1));
		
		list.add(2, "cat");
		assertEquals(3, list.size());
		assertEquals("apple", list.get(0));
		assertEquals("cat", list.get(2));
		assertEquals("banana", list.get(1));
		
		list.add(3, "dragonfruit");
		assertEquals(4, list.size());
		assertEquals("apple", list.get(0));
		assertEquals("cat", list.get(2));
		assertEquals("banana", list.get(1));
		assertEquals("dragonfruit", list.get(3));
		
		list.add(4, "grapefruit");
		assertEquals(5, list.size());
		assertEquals("apple", list.get(0));
		assertEquals("cat", list.get(2));
		assertEquals("banana", list.get(1));
		assertEquals("dragonfruit", list.get(3));
		assertEquals("grapefruit", list.get(4));
		
		list.add(5, "honeydew");
		assertEquals(6, list.size());
		assertEquals("apple", list.get(0));
		assertEquals("cat", list.get(2));
		assertEquals("banana", list.get(1));
		assertEquals("dragonfruit", list.get(3));
		assertEquals("grapefruit", list.get(4));
		assertEquals("honeydew", list.get(5));
		
		try {
			list.add(5, "honeydew");
		} catch(IllegalArgumentException e) {
			assertEquals("Element cannot be a duplicate.", e.getMessage());
			assertEquals(6, list.size());
			assertEquals("apple", list.get(0));
			assertEquals("cat", list.get(2));
			assertEquals("banana", list.get(1));
			assertEquals("dragonfruit", list.get(3));
			assertEquals("grapefruit", list.get(4));
			assertEquals("honeydew", list.get(5));
		}
		
		try {
			list.add(5, null);
		} catch(NullPointerException e) {
			assertEquals("Element cannot be null.", e.getMessage());
			assertEquals(6, list.size());
			assertEquals("apple", list.get(0));
			assertEquals("cat", list.get(2));
			assertEquals("banana", list.get(1));
			assertEquals("dragonfruit", list.get(3));
			assertEquals("grapefruit", list.get(4));
			assertEquals("honeydew", list.get(5));
		}
		
		list.add(6, "juniper berry");
		assertEquals(7, list.size());
		assertEquals("apple", list.get(0));
		assertEquals("cat", list.get(2));
		assertEquals("banana", list.get(1));
		assertEquals("dragonfruit", list.get(3));
		assertEquals("grapefruit", list.get(4));
		assertEquals("honeydew", list.get(5));
		assertEquals("juniper berry", list.get(6));
		
		list.add(7, "kiwi");
		assertEquals(8, list.size());
		assertEquals("apple", list.get(0));
		assertEquals("cat", list.get(2));
		assertEquals("banana", list.get(1));
		assertEquals("dragonfruit", list.get(3));
		assertEquals("grapefruit", list.get(4));
		assertEquals("honeydew", list.get(5));
		assertEquals("juniper berry", list.get(6));
		assertEquals("kiwi", list.get(7));
		
//		list.add(7, "mango");
//		assertEquals(9, list.size());
//		assertEquals("apple", list.get(0));
//		assertEquals("cat", list.get(2));
//		assertEquals("banana", list.get(1));
//		assertEquals("dragonfruit", list.get(3));
//		assertEquals("grapefruit", list.get(4));
//		assertEquals("honeydew", list.get(5));
//		assertEquals("juniper berry", list.get(6));
//		assertEquals("kiwi", list.get(7));
//		assertEquals("mango", list.get(8));
		
//		list.add(9, "lemon");
//		assertEquals(10, list.size());
//		assertEquals("apple", list.get(0));
//		assertEquals("cat", list.get(2));
//		assertEquals("banana", list.get(1));
//		assertEquals("dragonfruit", list.get(3));
//		assertEquals("grapefruit", list.get(4));
//		assertEquals("honeydew", list.get(5));
//		assertEquals("juniper berry", list.get(6));
//		assertEquals("kiwi", list.get(7));
//		assertEquals("mango", list.get(8));
//		assertEquals("lemon", list.get(9));
		
		list.add(8, "lemon");
		assertEquals(9, list.size());
		assertEquals("apple", list.get(0));
		assertEquals("cat", list.get(2));
		assertEquals("banana", list.get(1));
		assertEquals("dragonfruit", list.get(3));
		assertEquals("grapefruit", list.get(4));
		assertEquals("honeydew", list.get(5));
		assertEquals("juniper berry", list.get(6));
		assertEquals("kiwi", list.get(7));
		assertEquals("lemon", list.get(8));
		
		list.add(9, "mango");
		assertEquals(10, list.size());
		assertEquals("apple", list.get(0));
		assertEquals("cat", list.get(2));
		assertEquals("banana", list.get(1));
		assertEquals("dragonfruit", list.get(3));
		assertEquals("grapefruit", list.get(4));
		assertEquals("honeydew", list.get(5));
		assertEquals("juniper berry", list.get(6));
		assertEquals("kiwi", list.get(7));
		assertEquals("lemon", list.get(8));
		assertEquals("mango", list.get(9));
		
		
		try {
			list.add(10, "orange");
		} catch(IllegalArgumentException e) {
			assertEquals(10, list.size());
			assertEquals("apple", list.get(0));
			assertEquals("cat", list.get(2));
			assertEquals("banana", list.get(1));
			assertEquals("dragonfruit", list.get(3));
			assertEquals("grapefruit", list.get(4));
			assertEquals("honeydew", list.get(5));
			assertEquals("juniper berry", list.get(6));
			assertEquals("kiwi", list.get(7));
//			assertEquals("mango", list.get(8));
//			assertEquals("lemon", list.get(9));
			assertEquals("lemon", list.get(8));
			assertEquals("mango", list.get(9));
		}
		
		try {
			list.add(-1, "orange");
		} catch(IllegalArgumentException e) {
			assertEquals(10, list.size());
			assertEquals("apple", list.get(0));
			assertEquals("cat", list.get(2));
			assertEquals("banana", list.get(1));
			assertEquals("dragonfruit", list.get(3));
			assertEquals("grapefruit", list.get(4));
			assertEquals("honeydew", list.get(5));
			assertEquals("juniper berry", list.get(6));
			assertEquals("kiwi", list.get(7));
//			assertEquals("mango", list.get(8));
//			assertEquals("lemon", list.get(9));
			assertEquals("lemon", list.get(8));
			assertEquals("mango", list.get(9));
		}
		
		LinkedAbstractList<String> str = new LinkedAbstractList<String>(10);
		
		str.add(0, "apple");
		assertEquals(1, str.size());
		assertEquals("apple", str.get(0));
		
		str.add(0, "orange");
		assertEquals(2, str.size());
		assertEquals("orange", str.get(0));
		assertEquals("apple", str.get(1));
		
		str.add(1, "banana");
		assertEquals(3, str.size());
		assertEquals("orange", str.get(0));
		assertEquals("banana", str.get(1));
		assertEquals("apple", str.get(2));
	}
	
	/**
	 * Tests remove method for LinkedAbstractList
	 */
	@Test
	public void testRemove() {
		LinkedAbstractList<String> list = new LinkedAbstractList<String>(10);
		
		list.add(0, "apple");
		list.add(1, "banana");
		list.add(2, "carrot");
		list.add(3, "dog");
		
		assertEquals(4, list.size());
		
		list.remove(2);
		assertEquals(3, list.size());
		assertEquals("apple", list.get(0));
		assertEquals("banana", list.get(1));
		assertEquals("dog", list.get(2));
		
		list.remove(0);
		assertEquals(2, list.size());
		assertEquals("banana", list.get(0));
		assertEquals("dog", list.get(1));
		
		list.remove(1);
		assertEquals(1, list.size());
		assertEquals("banana", list.get(0));
		
		list.remove(0);
		assertEquals(0, list.size());
	}
	
	/***
	 * Tests set method for LinkedAbstractList
	 */
	@Test
	public void testSet() {
		LinkedAbstractList<String> list = new LinkedAbstractList<String>(10);
		
		list.add(0, "apple");
		list.add(1, "banana");
		list.add(2, "carrot");
		list.add(3, "dog");
		
		assertEquals(4, list.size());
		
		list.set(3, "cat");
		assertEquals("cat", list.get(3));
		
		try {
			list.set(3, null);
		}
		catch(NullPointerException e) {
			assertEquals("Element cannot be null.", e.getMessage());
		}
		
		try {
			list.set(-1, "cat");
		}
		catch(IndexOutOfBoundsException e) {
			assertEquals("Invalid index.", e.getMessage());
		}
	}

}
