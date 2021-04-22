/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;


/**
 * Tests the Faculty class
 * 
 * @author Daniel Nolting
 * @author Calli Hooks
 * @author Owen Loker
 */
public class FacultyTest {

	/** Faculty first name */
	private static final String FIRSTNAME = "FirstName";
	/** Faculty last name */
	private static final String LASTNAME = "LastName";
	/** Faculty id */
	private static final String ID = "id";
	/** Faculty email */
	private static final String EMAIL = "email@ncsu.edu";
	/** Faculty password */
	private static final String PASSWORD = "hashedPassword";
	/** Faculty credit amount */
	private static final int MAXCOURSES = 1;

	/**
	 * Tests the Faculty Constructor
	 */
	@Test
	public void testFacultyConstructor() {
		User f = null;
		try {
			f = new Faculty(null, LASTNAME, ID, EMAIL, PASSWORD, MAXCOURSES);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(f);
		}

		Faculty s2 = null;
		try {

			s2 = new Faculty(FIRSTNAME, LASTNAME, ID, EMAIL, PASSWORD, MAXCOURSES);

			assertEquals(FIRSTNAME, s2.getFirstName());
			assertEquals(LASTNAME, s2.getLastName());
			assertEquals(ID, s2.getId());
			assertEquals(EMAIL, s2.getEmail());
			assertEquals(PASSWORD, s2.getPassword());
			assertEquals(MAXCOURSES, s2.getMaxCourses());

		} catch (IllegalArgumentException e) {
			fail();
		}
	}

	/**
	 * Tests setEmail()
	 */
	@Test
	public void testSetEmail() {
		// Construct a valid Faculty
		User f = new Faculty(FIRSTNAME, LASTNAME, ID, EMAIL, PASSWORD, MAXCOURSES);

		// Null string test
		try {
			f.setEmail(null);
			fail(); // We don't want to reach this point - an exception should be thrown!
		} catch (IllegalArgumentException e) {
			// We've caught the exception, now we need to make sure that the field didn't
			// change
			assertEquals(EMAIL, f.getEmail());
		}

		// Empty string test
		try {
			f.setEmail("");
			fail(); // We don't want to reach this point - an exception should be thrown!
		} catch (IllegalArgumentException e) {
			// We've caught the exception, now we need to make sure that the field didn't
			// change
			assertEquals(EMAIL, f.getEmail());
		}

		// No '@' character test
		try {
			f.setEmail("joencsu.edu");
			fail(); // We don't want to reach this point - an exception should be thrown!
		} catch (IllegalArgumentException e) {
			// We've caught the exception, now we need to make sure that the field didn't
			// change
			assertEquals(EMAIL, f.getEmail());
		}

		// No '.' character test
		try {
			f.setEmail("joe@ncsuedu");
			fail(); // We don't want to reach this point - an exception should be thrown!
		} catch (IllegalArgumentException e) {
			// We've caught the exception, now we need to make sure that the field didn't
			// change
			assertEquals(EMAIL, f.getEmail());
		}

		// '.' character before '@' character test
		try {
			f.setEmail("joe.ncsu@edu");
			fail(); // We don't want to reach this point - an exception should be thrown!
		} catch (IllegalArgumentException e) {
			// We've caught the exception, now we need to make sure that the field didn't
			// change
			assertEquals(EMAIL, f.getEmail());
		}

		// Valid email test
		try {
			f.setEmail("joe@ncsu.edu");
			assertEquals("joe@ncsu.edu", f.getEmail());
		} catch (IllegalArgumentException e) {
			fail(); // We don't want to reach this point - an exception should be thrown!
		}

	}

	/**
	 * Tests setPassword()
	 */
	@Test
	public void testSetPassword() {
		// Construct a valid Faculty
		User s = new Faculty(FIRSTNAME, LASTNAME, ID, EMAIL, PASSWORD, MAXCOURSES);

		// Null string test
		try {
			s.setPassword(null);
			fail(); // We don't want to reach this point - an exception should be thrown!
		} catch (IllegalArgumentException e) {
			// We've caught the exception, now we need to make sure that the field didn't
			// change
			assertEquals(PASSWORD, s.getPassword());
		}

		// Empty string test
		try {
			s.setPassword("");
			fail(); // We don't want to reach this point - an exception should be thrown!
		} catch (IllegalArgumentException e) {
			// We've caught the exception, now we need to make sure that the field didn't
			// change
			assertEquals(PASSWORD, s.getPassword());
		}

		// Valid password test
		try {
			s.setPassword("newpassword");
			assertEquals("newpassword", s.getPassword());
		} catch (IllegalArgumentException e) {
			fail(); // We don't want to reach this point - an exception should be thrown!
		}

	}

	/**
	 * Tests setMaxCourses()
	 */
	@Test
	public void testsetMaxCourses() {
		// Construct a valid Faculty
		Faculty s = new Faculty(FIRSTNAME, LASTNAME, ID, EMAIL, PASSWORD, MAXCOURSES);

		// Too small MAXCOURSES
		try {
			s.setMaxCourses(0);
			fail(); // We don't want to reach this point - an exception should be thrown!
		} catch (IllegalArgumentException e) {
			// We've caught the exception, now we need to make sure that the field didn't
			// change
			assertEquals(MAXCOURSES, s.getMaxCourses());
		}

		// Too large MAXCOURSES
		try {
			s.setMaxCourses(4);
			fail(); // We don't want to reach this point - an exception should be thrown!
		} catch (IllegalArgumentException e) {
			// We've caught the exception, now we need to make sure that the field didn't
			// change
			assertEquals(MAXCOURSES, s.getMaxCourses());
		}

		// Valid MAXCOURSES test
		try {
			s.setMaxCourses(2);
			assertEquals(2, s.getMaxCourses());
		} catch (IllegalArgumentException e) {
			fail(); // We don't want to reach this point - an exception should be thrown!
		}

	}

	/**
	 * Tests setFirstName()
	 */
	@Test
	public void testSetFirstName() {
		// Construct a valid Faculty
		User s = new Faculty(FIRSTNAME, LASTNAME, ID, EMAIL, PASSWORD, MAXCOURSES);

		// Null string test
		try {
			s.setFirstName(null);
			fail(); // We don't want to reach this point - an exception should be thrown!
		} catch (IllegalArgumentException e) {
			// We've caught the exception, now we need to make sure that the field didn't
			// change
			assertEquals(FIRSTNAME, s.getFirstName());
		}

		// Empty string test
		try {
			s.setFirstName("");
			fail(); // We don't want to reach this point - an exception should be thrown!
		} catch (IllegalArgumentException e) {
			// We've caught the exception, now we need to make sure that the field didn't
			// change
			assertEquals(FIRSTNAME, s.getFirstName());
		}

		// Valid firstName test
		try {
			s.setFirstName("Joe");
			assertEquals("Joe", s.getFirstName());
		} catch (IllegalArgumentException e) {
			fail(); // We don't want to reach this point - an exception should be thrown!
		}

	}

	/**
	 * Tests setLastName()
	 */
	@Test
	public void testSetLastName() {
		// Construct a valid Faculty
		User s = new Faculty(FIRSTNAME, LASTNAME, ID, EMAIL, PASSWORD, MAXCOURSES);

		// Null string test
		try {
			s.setLastName(null);
			fail(); // We don't want to reach this point - an exception should be thrown!
		} catch (IllegalArgumentException e) {
			// We've caught the exception, now we need to make sure that the field didn't
			// change
			assertEquals(LASTNAME, s.getLastName());
		}

		// Empty string test
		try {
			s.setLastName("");
			fail(); // We don't want to reach this point - an exception should be thrown!
		} catch (IllegalArgumentException e) {
			// We've caught the exception, now we need to make sure that the field didn't
			// change
			assertEquals(LASTNAME, s.getLastName());
		}

		// Valid name test
		try {
			s.setLastName("Faculty");
			assertEquals("Faculty", s.getLastName());
		} catch (IllegalArgumentException e) {
			fail(); // We don't want to reach this point - an exception should be thrown!
		}

	}

	/**
	 * Tests setEquals()
	 */
	@Test
	public void testEqualsObject() {

		// Declare Faculty objects to test equality
		User s1 = new Faculty(FIRSTNAME, LASTNAME, ID, EMAIL, PASSWORD, MAXCOURSES);
		User s2 = new Faculty(FIRSTNAME, LASTNAME, ID, EMAIL, PASSWORD, MAXCOURSES);
		User s3 = new Faculty("Jim", LASTNAME, ID, EMAIL, PASSWORD, MAXCOURSES);
		User s4 = new Faculty(FIRSTNAME, "Bob", ID, EMAIL, PASSWORD, MAXCOURSES);
		User s5 = new Faculty(FIRSTNAME, LASTNAME, "65094", EMAIL, PASSWORD, MAXCOURSES);
		User s6 = new Faculty(FIRSTNAME, LASTNAME, "65094", EMAIL, PASSWORD, MAXCOURSES);
		User s7 = new Faculty(FIRSTNAME, LASTNAME, "65094", EMAIL, PASSWORD, 2);
		User s8 = new Faculty(FIRSTNAME, LASTNAME, "65094", "hello@example.com", PASSWORD, MAXCOURSES);

		// Check self-equality and class differences
		assertTrue(s1.equals(s1));
		assertFalse(s1.equals(new Object()));

		// Make sure that the Faculty objects with identical instance variables are
		// equal.
		assertTrue(s1.equals(s2));
		assertTrue(s5.equals(s6));

		// Ensure that Faculty objects with differing instance variables are not equal.
		assertFalse(s1.equals(s3));
		assertFalse(s1.equals(s4));
		assertFalse(s6.equals(s2));
		assertFalse(s6.equals(s7));
		assertFalse(s6.equals(s8));
	}

	/**
	 * Tests toString()
	 */
	@Test
	public void testToString() {

		// Create new Faculty
		User s = new Faculty(FIRSTNAME, LASTNAME, ID, EMAIL, PASSWORD, MAXCOURSES);

		// Test to make sure that toString returns parameters in comma separated list.
		assertEquals(FIRSTNAME + "," + LASTNAME + "," + ID + "," + EMAIL + "," + PASSWORD + "," + MAXCOURSES,
				s.toString());

	}

	/**
	 * Tests setHashCode()
	 */
	@Test
	public void testHashCode() {

		// Declare Faculty objects to test equality in hashcode
		User s1 = new Faculty(FIRSTNAME, LASTNAME, ID, EMAIL, PASSWORD, MAXCOURSES);
		User s2 = new Faculty(FIRSTNAME, LASTNAME, ID, EMAIL, PASSWORD, MAXCOURSES);
		User s3 = new Faculty("Jim", "Bob", ID, EMAIL, PASSWORD, MAXCOURSES);
		User s4 = new Faculty(FIRSTNAME, LASTNAME, "65094", EMAIL, PASSWORD, MAXCOURSES);
		User s5 = new Faculty(FIRSTNAME, LASTNAME, "65094", EMAIL, PASSWORD, MAXCOURSES);

		// Make sure that the Faculty objects with identical instance variables have
		// same hash codes.
		assertEquals(s1.hashCode(), s2.hashCode());
		assertEquals(s4.hashCode(), s5.hashCode());

		// Ensure that Faculty objects with differing instance variables have different
		// hash codes.
		assertNotEquals(s1.hashCode(), s3.hashCode());
		assertNotEquals(s5.hashCode(), s2.hashCode());

	}

	/**
	 * Tests get schedule method
	 * 
	 */
	@Test
	public void testGetSchedule() {
		Faculty f1 = new Faculty("Ashely", "Witt", "awitt", "mollis@Fuscealiquetmagna.net", "pw", 2);
		assertEquals(0, f1.getSchedule().getNumScheduledCourses());
	}

	/**
	 * Tests isOverloaded method
	 */
	@Test
	public void testIsOverloaded() {
		Faculty f = new Faculty(FIRSTNAME, LASTNAME, ID, EMAIL, PASSWORD, MAXCOURSES);
		assertFalse(f.isOverloaded());
	}
}
