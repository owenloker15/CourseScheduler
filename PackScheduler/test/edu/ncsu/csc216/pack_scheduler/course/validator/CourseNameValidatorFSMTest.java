package edu.ncsu.csc216.pack_scheduler.course.validator;

import static org.junit.Assert.*;

import org.junit.Test;

/** 
 * Test class for CourseNameValidatorFSM
 * @author Nick Bleuzen, Daniel Nolting, Kelsey Hanser
 *
 */
public class CourseNameValidatorFSMTest {
	
	/** Private instance of CourseNameValidator */
	private static final CourseNameValidatorFSM VALIDATOR = new CourseNameValidatorFSM();

	/** Tests state L */
	@Test
	public void stateL() {
		try {
			VALIDATOR.isValid("C11111");
		} catch (InvalidTransitionException e) {
			assertEquals("Course name can only have 3 digits.", e.getMessage());
		}
		try {
			VALIDATOR.isValid("C1#111");
		} catch (InvalidTransitionException e) {
			assertEquals("Course name can only contain letters and digits.", e.getMessage());
		}
		try {
			VALIDATOR.isValid("111111");
		} catch (InvalidTransitionException e) {
			assertEquals("Course name must start with a letter.", e.getMessage());
		}
	}
	
	/** Tests state LL */
	@Test
	public void stateLL() {
		try {
			VALIDATOR.isValid("CS1111");
		} catch (InvalidTransitionException e) {
			assertEquals("Course name can only have 3 digits.", e.getMessage());
		}
		try {
			assertTrue(VALIDATOR.isValid("CSC216"));
		} catch (InvalidTransitionException e) {
			fail();
		}
	}
	
	/** Tests state LLL */
	@Test
	public void stateLLL() {
		try {
			VALIDATOR.isValid("CSC1211");
		} catch (InvalidTransitionException e) {
			assertEquals("Course name can only have 3 digits.", e.getMessage());
		}
		try {
			assertTrue(VALIDATOR.isValid("CSC216"));
		} catch (InvalidTransitionException e) {
			fail();
		}
	}
	
	/** Tests state LLLL */
	@Test
	public void stateLLLL() {
		try {
			VALIDATOR.isValid("CSCAB11");
		} catch (InvalidTransitionException e) {
			assertEquals("Course name cannot start with more than 4 letters.", e.getMessage());
		}
		try {
			assertTrue(VALIDATOR.isValid("CSCA216"));
		} catch (InvalidTransitionException e) {
			fail();
		}
	}
	
	/** Tests state D */
	@Test 
	public void stateD() {
		try {
			VALIDATOR.isValid("CSCA1A1");
		} catch (InvalidTransitionException e) {
			assertEquals("Course name must have 3 digits.", e.getMessage());
		}
		try {
			assertTrue(VALIDATOR.isValid("CSCA216"));
		} catch (InvalidTransitionException e) {
			fail();
		}
	}
	
	/** Tests state DD */
	@Test 
	public void stateDD() {
		try {
			VALIDATOR.isValid("CSCA11A");
		} catch (InvalidTransitionException e) {
			assertEquals("Course name must have 3 digits.", e.getMessage());
		}
		try {
			assertTrue(VALIDATOR.isValid("CSCA216"));
		} catch (InvalidTransitionException e) {
			fail();
		}
	}
	
	/** Tests state DDD */
	@Test 
	public void stateDDD() {
		try {
			VALIDATOR.isValid("CSCA1112");
		} catch (InvalidTransitionException e) {
			assertEquals("Course name can only have 3 digits.", e.getMessage());
		}
		try {
			assertTrue(VALIDATOR.isValid("CSCA216"));
		} catch (InvalidTransitionException e) {
			fail();
		}
	}
	
	/** Tests state suffix */
	@Test
	public void stateSuffix() {
		try {
			VALIDATOR.isValid("CSCA111EB");
		} catch (InvalidTransitionException e) {
			assertEquals("Course name can only have a 1 letter suffix.", e.getMessage());
		}
		try {
			VALIDATOR.isValid("CSCA111E1");
		} catch (InvalidTransitionException e) {
			assertEquals("Course name cannot contain digits after the suffix.", e.getMessage());
		}
		try {
			assertTrue(VALIDATOR.isValid("CSCA111E"));
		} catch (InvalidTransitionException e) {
			fail();
		}
		
	}

}
