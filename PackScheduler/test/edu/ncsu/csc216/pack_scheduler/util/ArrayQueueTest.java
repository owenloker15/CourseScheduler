/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.NoSuchElementException;

import org.junit.Test;

/**
 * Tests the ArrayQueue class
 * 
 * @author Daniel Nolting
 * @author Calli Hooks
 * @author Owen Loker
 *
 */
public class ArrayQueueTest {

	/**
	 * Tests the ArrayQueue constructor
	 */
	@Test
	public void testArrayQueue() {
		ArrayQueue<String> queue = new ArrayQueue<String>(15);
		assertEquals(0, queue.size());
	}

	/**
	 * Tests the enqueue method
	 */
	@Test
	public void testEnqueue() {
		ArrayQueue<String> queue = new ArrayQueue<String>(15);
		queue.enqueue("apple");
		assertEquals(1, queue.size());
		assertFalse(queue.isEmpty());

		queue.enqueue("banana");
		assertEquals(2, queue.size());

		queue.enqueue("orange");
		assertEquals(3, queue.size());
	}

	/**
	 * Tests the dequeue method
	 */
	@Test
	public void testDequeue() {
		ArrayQueue<String> queue = new ArrayQueue<String>(15);
		queue.enqueue("apple");
		queue.enqueue("banana");
		queue.enqueue("orange");
		
		assertEquals("apple", queue.dequeue());
		assertEquals(2, queue.size());

		assertEquals("banana", queue.dequeue());
		assertEquals(1, queue.size());

		assertEquals("orange", queue.dequeue());
		assertEquals(0, queue.size());

		try {
			queue.dequeue();
			fail();
		} catch (NoSuchElementException e) {
			assertEquals(0, queue.size());
			assertTrue(queue.isEmpty());
		}
	}

	/**
	 * Tests the setCapacity method
	 */
	@Test
	public void testSetCapacity() {
		ArrayQueue<String> queue = new ArrayQueue<String>(15);
		//Capacity cannot be negative
		try {
			queue.setCapacity(-1);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(0, queue.size());
		}
	}

}

