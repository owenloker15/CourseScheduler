package edu.ncsu.csc216.pack_scheduler.course.validator;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test class for CourseNameValidator
 * @author Kelsey Hanser, Nick Bleuzen, Daniel Nolting
 *
 */
public class CourseNameValidatorTest {
	
	/** Private instance of CourseNameValidator */
	private final CourseNameValidator validator = new CourseNameValidator();

	
	/** Tests state L */
	@Test
	public void stateL() {
		try {
			validator.isValid("C11111");
		} catch (InvalidTransitionException e) {
			assertEquals("Course name can only have 3 digits.", e.getMessage());
		}
		try {
			validator.isValid("C1#111");
		} catch (InvalidTransitionException e) {
			assertEquals("Course name can only contain letters and digits.", e.getMessage());
		}
		try {
			validator.isValid("111111");
		} catch (InvalidTransitionException e) {
			assertEquals("Course name must start with a letter.", e.getMessage());
		}
	}
	
	/** Tests state LL */
	@Test
	public void stateLL() {
		try {
			validator.isValid("CS1111");
		} catch (InvalidTransitionException e) {
			assertEquals("Course name can only have 3 digits.", e.getMessage());
		}
		try {
			assertTrue(validator.isValid("CSC216"));
		} catch (InvalidTransitionException e) {
			fail();
		}
	}
	
	/** Tests state LLL */
	@Test
	public void stateLLL() {
		try {
			validator.isValid("CSC1211");
		} catch (InvalidTransitionException e) {
			assertEquals("Course name can only have 3 digits.", e.getMessage());
		}
		try {
			assertTrue(validator.isValid("CSC216"));
		} catch (InvalidTransitionException e) {
			fail();
		}
	}
	
	/** Tests state LLLL */
	@Test
	public void stateLLLL() {
		try {
			validator.isValid("CSCAB11");
		} catch (InvalidTransitionException e) {
			assertEquals("Course name cannot start with more than 4 letters.", e.getMessage());
		}
		try {
			assertTrue(validator.isValid("CSCA216"));
		} catch (InvalidTransitionException e) {
			fail();
		}
	}
	
	/** Tests state D */
	@Test 
	public void stateD() {
		try {
			validator.isValid("CSCA1A1");
		} catch (InvalidTransitionException e) {
			assertEquals("Course name must have 3 digits.", e.getMessage());
		}
		try {
			assertTrue(validator.isValid("CSCA216"));
		} catch (InvalidTransitionException e) {
			fail();
		}
	}
	
	/** Tests state DD */
	@Test 
	public void stateDD() {
		try {
			validator.isValid("CSCA11A");
		} catch (InvalidTransitionException e) {
			assertEquals("Course name must have 3 digits.", e.getMessage());
		}
		try {
			assertTrue(validator.isValid("CSCA216"));
		} catch (InvalidTransitionException e) {
			fail();
		}
	}
	
	/** Tests state DD */
	@Test 
	public void stateDDD() {
		try {
			validator.isValid("CSCA1112");
		} catch (InvalidTransitionException e) {
			assertEquals("Course name can only have 3 digits.", e.getMessage());
		}
		try {
			assertTrue(validator.isValid("CSCA216"));
		} catch (InvalidTransitionException e) {
			fail();
		}
	}
	
	/** Tests state suffix */
	@Test
	public void stateSuffix() {
		try {
			validator.isValid("CSCA111EB");
		} catch (InvalidTransitionException e) {
			assertEquals("Course name can only have a 1 letter suffix.", e.getMessage());
		}
		try {
			validator.isValid("CSCA111E1");
		} catch (InvalidTransitionException e) {
			assertEquals("Course name cannot contain digits after the suffix.", e.getMessage());
		}
		try {
			assertTrue(validator.isValid("CSCA111E"));
		} catch (InvalidTransitionException e) {
			fail();
		}
		
	}

}
