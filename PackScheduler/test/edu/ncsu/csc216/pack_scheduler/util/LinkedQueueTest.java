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
 * Tests the LinkedQueue class
 * 
 * @author Daniel Nolting
 * @author Calli Hooks
 * @author Owen Loker
 *
 */
public class LinkedQueueTest {

	/**
	 * Tests the LinkedQueue constructor
	 */
	@Test
	public void testLinkedQueue() {
		LinkedQueue<String> queue = new LinkedQueue<String>(15);
		assertEquals(0, queue.size());
	}

	/**
	 * Tests the enqueue method
	 */
	@Test
	public void testEnqueue() {
		LinkedQueue<String> queue = new LinkedQueue<String>(15);
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
		LinkedQueue<String> queue = new LinkedQueue<String>(15);
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
		LinkedQueue<String> queue = new LinkedQueue<String>(15);
		try {
			queue.setCapacity(-1);
		} catch (IllegalArgumentException e) {
			assertEquals(0, queue.size());
		}
	}

}


