package edu.ncsu.csc216.pack_scheduler.course.validator;

import static org.junit.Assert.*;

import org.junit.Test;

/** 
 * Test class for InvalidTransitionException
 * @author Nick Bleuzen, Daniel Nolting, Kelsey Hanser
 *
 */
public class InvalidTransitionExceptionTest {

	/** Tests InvalidTransitionException constructors */
	@Test
	public void testInvalidTransitionException() {
		InvalidTransitionException e = new InvalidTransitionException();
		assertEquals("Invalid FSM Transition.", e.getMessage());
		
		InvalidTransitionException e2 = new InvalidTransitionException("new message");
		assertEquals("new message", e2.getMessage());
	}

}
