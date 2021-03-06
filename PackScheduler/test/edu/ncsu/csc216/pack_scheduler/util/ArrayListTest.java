package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test class for custom ArrayList
 */
public class ArrayListTest {

	/**
	 * Tests constructor
	 * @author Daniel Nolting, Kelsey Hanser, Nick Bleuzen
	 */
	@Test
	public void testArrayList() {
		ArrayList<String> list = new ArrayList<String>();
		assertEquals(0, list.size());
	}
	/**
	 * Test add function
	 */
	@Test
	public void testAdd() {
		ArrayList<String> list = new ArrayList<String>();
		
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
		
		list.add(10, "orange");
		assertEquals(11, list.size());
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
		assertEquals("orange", list.get(10));
		
		list.add(11, "pomegranite");
		assertEquals(12, list.size());
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
		assertEquals("orange", list.get(10));
		assertEquals("pomegranite", list.get(11));
		
		list.add(9, "lime");
		assertEquals(13, list.size());
		assertEquals("apple", list.get(0));
		assertEquals("cat", list.get(2));
		assertEquals("banana", list.get(1));
		assertEquals("dragonfruit", list.get(3));
		assertEquals("grapefruit", list.get(4));
		assertEquals("honeydew", list.get(5));
		assertEquals("juniper berry", list.get(6));
		assertEquals("kiwi", list.get(7));
		assertEquals("lemon", list.get(8));
		assertEquals("lime", list.get(9));
		assertEquals("mango", list.get(10));
		assertEquals("orange", list.get(11));
		assertEquals("pomegranite", list.get(12));
	}
	
	/**
	 * Tests remove function
	 */
	@Test
	public void testRemove() {
		ArrayList<String> list = new ArrayList<String>();
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
	
	/**
	 * Tests set function
	 */
	@Test
	public void testSet() {
		ArrayList<String> list = new ArrayList<String>();
		list.add(0, "apple");
		list.add(1, "banana");
		list.add(2, "carrot");
		list.add(3, "dog");
		
		assertEquals(4, list.size());
		
		list.set(3, "cat");
		assertEquals("cat", list.get(3));
	}

}
