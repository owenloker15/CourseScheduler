/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.EmptyStackException;

import org.junit.Test;

/**
 * Tests the LinkedStack class
 * 
 * @author Daniel Nolting
 * @author Calli Hooks
 * @author Owen Loker
 *
 */
public class LinkedStackTest {

	/**
	 * Tests the LinkedStack constructor
	 */
	@Test
	public void testLinkedStack() {
		LinkedStack<String> stack = new LinkedStack<String>(15);
		assertEquals(0, stack.size());
	}

	/**
	 * Tests the push method
	 */
	@Test
	public void testPush() {
		LinkedStack<String> stack = new LinkedStack<String>(15);
		stack.push("apple");
		assertEquals(1, stack.size());
		assertFalse(stack.isEmpty());

		stack.push("banana");
		assertEquals(2, stack.size());

		stack.push("orange");
		assertEquals(3, stack.size());
	}

	/**
	 * Tests the pop method
	 */
	@Test
	public void testPop() {
		LinkedStack<String> stack = new LinkedStack<String>(15);
		stack.push("apple");
		stack.push("banana");
		stack.push("orange");

		assertEquals("orange", stack.pop());
		assertEquals(2, stack.size());

		assertEquals("banana", stack.pop());
		assertEquals(1, stack.size());

		assertEquals("apple", stack.pop());
		assertEquals(0, stack.size());

		try {
			stack.pop();
			fail();
		} catch (EmptyStackException e) {
			assertEquals(0, stack.size());
			assertTrue(stack.isEmpty());
		}
	}

	/**
	 * Tests the setCapacity method
	 */
	@Test
	public void testSetCapacity() {
		LinkedStack<String> stack = new LinkedStack<String>(15);
		//Capacity cannot be negative
		try {
			stack.setCapacity(-1);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(0, stack.size());
		}
	}

}
