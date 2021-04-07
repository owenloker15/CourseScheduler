package edu.ncsu.csc216.pack_scheduler.user;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule;

/**
 * Tests the Course class.
 * 
 * Note that test methods for all getters have been omitted. They will be tested
 * through other methods.
 * 
 * @author Daniel Nolting
 * @author Ki Miller
 * @author Sean McKone
 */
public class StudentTest {
	
	/** Student first name */
	private static final String FIRSTNAME = "FirstName";
	/** Student last name */
	private static final String LASTNAME = "LastName";
	/** Student id */
	private static final String ID = "id";
	/** Student email */
	private static final String EMAIL = "email@ncsu.edu";
	/** Student password */
	private static final String PASSWORD  = "hashedPassword";
	/** Student credit amount */
	private static final int MAXCREDITS = 18;

	/**
	 * Tests the constructor with all parameters
	 * Tests setID (can only be tested through constructor)
	 */
	@Test
	public void testStudentStringStringStringStringStringInt() {
		
		// Invalid test
		User s1 = null; //Initialize a student reference to null
		try {
		    s1 = new Student(null, LASTNAME, ID, EMAIL, PASSWORD, MAXCREDITS);
		    //Note that for testing purposes, the password doesn't need to be hashedpassword
		    fail(); //If we reach this point a Student was constructed when it shouldn't have been!
		} catch (IllegalArgumentException e) {
		    //We should get here if the expected IllegalArgumentException is thrown, but that's not enough
		    //for the test.  We also need to make sure that the reference s is still null!
		    assertNull(s1);
		}
		
		// Valid test
		Student s2 = null;
		try {
			
			s2 = new Student(FIRSTNAME, LASTNAME, ID, EMAIL, PASSWORD, MAXCREDITS);
			
			assertEquals(FIRSTNAME, s2.getFirstName());
			assertEquals(LASTNAME, s2.getLastName());
			assertEquals(ID, s2.getId());
			assertEquals(EMAIL, s2.getEmail());
			assertEquals(PASSWORD, s2.getPassword());
			assertEquals(MAXCREDITS, s2.getMaxCredits());
			
		} catch (IllegalArgumentException e) {
			
			fail();
		}
		
	}
	/**
	 * Tests the constructor with five parameters
	 * Tests setID (can only be tested through constructor)
	 */
	@Test
	public void testStudentStringStringStringStringString() {
		
		// Invalid test
		User s1 = null; //Initialize a student reference to null
		try {
		    s1 = new Student(FIRSTNAME, LASTNAME, null, EMAIL, PASSWORD);
		    //Note that for testing purposes, the password doesn't need to be hashedpassword
		    fail(); //If we reach this point a Student was constructed when it shouldn't have been!
		} catch (IllegalArgumentException e) {
		    //We should get here if the expected IllegalArgumentException is thrown, but that's not enough
		    //for the test.  We also need to make sure that the reference s is still null!
		    assertNull(s1);
		}
		try {
		    s1 = new Student(FIRSTNAME, LASTNAME, "", EMAIL, PASSWORD);
		    fail();
		} catch (IllegalArgumentException e) {
		    assertNull(s1);
		}
		
		// Valid test
		User s2 = null;
		try {
			
			s2 = new Student(FIRSTNAME, LASTNAME, ID, EMAIL, PASSWORD);
			
			assertEquals(FIRSTNAME, s2.getFirstName());
			assertEquals(LASTNAME, s2.getLastName());
			assertEquals(ID, s2.getId());
			assertEquals(EMAIL, s2.getEmail());
			assertEquals(PASSWORD, s2.getPassword());
			
		} catch (IllegalArgumentException e) {	
			fail();
		}
		
	}
	

	/**
	 * Tests setEmail()
	 */
	@Test
	public void testSetEmail() {
		//Construct a valid Student
		User s = new Student(FIRSTNAME, LASTNAME, ID, EMAIL, PASSWORD);
		
		//Null string test
		try {
		    s.setEmail(null);
		    fail(); //We don't want to reach this point - an exception should be thrown!
		} catch (IllegalArgumentException e) {
		    //We've caught the exception, now we need to make sure that the field didn't change
		    assertEquals(EMAIL, s.getEmail());
		}
		
		//Empty string test
		try {
		    s.setEmail("");
		    fail(); //We don't want to reach this point - an exception should be thrown!
		} catch (IllegalArgumentException e) {
		    //We've caught the exception, now we need to make sure that the field didn't change
		    assertEquals(EMAIL, s.getEmail());
		}
		
		//No '@' character test
		try {
		    s.setEmail("joencsu.edu");
		    fail(); //We don't want to reach this point - an exception should be thrown!
		} catch (IllegalArgumentException e) {
		    //We've caught the exception, now we need to make sure that the field didn't change
		    assertEquals(EMAIL, s.getEmail());
		}
		
		//No '.' character test
		try {
		    s.setEmail("joe@ncsuedu");
		    fail(); //We don't want to reach this point - an exception should be thrown!
		} catch (IllegalArgumentException e) {
		    //We've caught the exception, now we need to make sure that the field didn't change
		    assertEquals(EMAIL, s.getEmail());
		}
		
		//'.' character before '@' character test
		try {
		    s.setEmail("joe.ncsu@edu");
		    fail(); //We don't want to reach this point - an exception should be thrown!
		} catch (IllegalArgumentException e) {
		    //We've caught the exception, now we need to make sure that the field didn't change
		    assertEquals(EMAIL, s.getEmail());
		}
		
		//Valid email test
		try {
		    s.setEmail("joe@ncsu.edu");
		    assertEquals("joe@ncsu.edu", s.getEmail());
		} catch (IllegalArgumentException e) {
			fail(); //We don't want to reach this point - an exception should be thrown!
		}
		
	}
	/**
	 * Tests setPassword()
	 */
	@Test
	public void testSetPassword() {
		//Construct a valid Student
		User s = new Student(FIRSTNAME, LASTNAME, ID, EMAIL, PASSWORD);
		
		//Null string test
		try {
		    s.setPassword(null);
		    fail(); //We don't want to reach this point - an exception should be thrown!
		} catch (IllegalArgumentException e) {
		    //We've caught the exception, now we need to make sure that the field didn't change
		    assertEquals(PASSWORD, s.getPassword());
		}
		
		//Empty string test
		try {
		    s.setPassword("");
		    fail(); //We don't want to reach this point - an exception should be thrown!
		} catch (IllegalArgumentException e) {
		    //We've caught the exception, now we need to make sure that the field didn't change
		    assertEquals(PASSWORD, s.getPassword());
		}
		
		//Valid password test
		try {
		    s.setPassword("newpassword");
		    assertEquals("newpassword", s.getPassword());
		} catch (IllegalArgumentException e) {
			fail(); //We don't want to reach this point - an exception should be thrown!
		}
		
	}

	/**
	 * Tests setMaxCredits()
	 */
	@Test
	public void testSetMaxCredits() {
		//Construct a valid Student
		Student s = new Student(FIRSTNAME, LASTNAME, ID, EMAIL, PASSWORD, MAXCREDITS);
		
		//Too small maxCredits
		try {
		    s.setMaxCredits(2);
		    fail(); //We don't want to reach this point - an exception should be thrown!
		} catch (IllegalArgumentException e) {
		    //We've caught the exception, now we need to make sure that the field didn't change
		    assertEquals(MAXCREDITS, s.getMaxCredits());
		}
		
		//Too large maxCredits
		try {
		    s.setMaxCredits(19);
		    fail(); //We don't want to reach this point - an exception should be thrown!
		} catch (IllegalArgumentException e) {
		    //We've caught the exception, now we need to make sure that the field didn't change
			assertEquals(MAXCREDITS, s.getMaxCredits());
		}
		
		//Valid maxCredits test
		try {
		    s.setMaxCredits(15);
		    assertEquals(15, s.getMaxCredits());
		} catch (IllegalArgumentException e) {
			fail(); //We don't want to reach this point - an exception should be thrown!
		}
		
	}

	/**
	 * Tests setFirstName()
	 */
	@Test
	public void testSetFirstName() {
		//Construct a valid Student
		User s = new Student(FIRSTNAME, LASTNAME, ID, EMAIL, PASSWORD);
		
		//Null string test
		try {
		    s.setFirstName(null);
		    fail(); //We don't want to reach this point - an exception should be thrown!
		} catch (IllegalArgumentException e) {
		    //We've caught the exception, now we need to make sure that the field didn't change
		    assertEquals(FIRSTNAME, s.getFirstName());
		}
		
		//Empty string test
		try {
		    s.setFirstName("");
		    fail(); //We don't want to reach this point - an exception should be thrown!
		} catch (IllegalArgumentException e) {
		    //We've caught the exception, now we need to make sure that the field didn't change
		    assertEquals(FIRSTNAME, s.getFirstName());
		}
		
		//Valid firstName test
		try {
		    s.setFirstName("Joe");
		    assertEquals("Joe", s.getFirstName());
		} catch (IllegalArgumentException e) {
			fail(); //We don't want to reach this point - an exception should be thrown!
		}
		
	}
	
	/**
	 * Tests setLastName()
	 */
	@Test
	public void testSetLastName() {
		//Construct a valid Student
		User s = new Student(FIRSTNAME, LASTNAME, ID, EMAIL, PASSWORD);
		
		//Null string test
		try {
		    s.setLastName(null);
		    fail(); //We don't want to reach this point - an exception should be thrown!
		} catch (IllegalArgumentException e) {
		    //We've caught the exception, now we need to make sure that the field didn't change
		    assertEquals(LASTNAME, s.getLastName());
		}
		
		//Empty string test
		try {
		    s.setLastName("");
		    fail(); //We don't want to reach this point - an exception should be thrown!
		} catch (IllegalArgumentException e) {
		    //We've caught the exception, now we need to make sure that the field didn't change
		    assertEquals(LASTNAME, s.getLastName());
		}
		
		//Valid name test
		try {
		    s.setLastName("Student");
		    assertEquals("Student", s.getLastName());
		} catch (IllegalArgumentException e) {
			fail(); //We don't want to reach this point - an exception should be thrown!
		}
		
	}
	
	/**
	 * Tests setEquals()
	 */
	@Test
	public void testEqualsObject() {
		
		// Declare Student objects to test equality
		User s1 = new Student(FIRSTNAME, LASTNAME, ID, EMAIL, PASSWORD);
		User s2 = new Student(FIRSTNAME, LASTNAME, ID, EMAIL, PASSWORD);
		User s3 = new Student("Jim", LASTNAME, ID, EMAIL, PASSWORD);
		User s4 = new Student(FIRSTNAME, "Bob", ID, EMAIL, PASSWORD);
		User s5 = new Student(FIRSTNAME, LASTNAME, "65094", EMAIL, PASSWORD);
		User s6 = new Student(FIRSTNAME, LASTNAME, "65094", EMAIL, PASSWORD);
		User s7 = new Student(FIRSTNAME, LASTNAME, "65094", EMAIL, PASSWORD, 15);
		User s8 = new Student(FIRSTNAME, LASTNAME, "65094", "hello@example.com", PASSWORD);
		
		// Check self-equality and class differences
		assertTrue(s1.equals(s1));
		assertFalse(s1.equals(new Object()));
		
		// Make sure that the Student objects with identical instance variables are equal.
		assertTrue(s1.equals(s2));
		assertTrue(s5.equals(s6));
		
		// Ensure that Student objects with differing instance variables are not equal.
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
		
		// Create new Student
		User s = new Student(FIRSTNAME, LASTNAME, ID, EMAIL, PASSWORD, MAXCREDITS);
		
		// Test to make sure that toString returns parameters in comma separated list.
		assertEquals(FIRSTNAME + "," + LASTNAME + "," + ID + "," + EMAIL + "," + PASSWORD + ","  + MAXCREDITS, s.toString());

	}
	
	/**
	 * Tests setHashCode()
	 */
	@Test
	public void testHashCode() {
		
		// Declare Student objects to test equality in hashcode
		User s1 = new Student(FIRSTNAME, LASTNAME, ID, EMAIL, PASSWORD);
		User s2 = new Student(FIRSTNAME, LASTNAME, ID, EMAIL, PASSWORD);
		User s3 = new Student("Jim", "Bob", ID, EMAIL, PASSWORD);
		User s4 = new Student(FIRSTNAME, LASTNAME, "65094", EMAIL, PASSWORD);
		User s5 = new Student(FIRSTNAME, LASTNAME, "65094", EMAIL, PASSWORD);
		
		// Make sure that the Student objects with identical instance variables have same hash codes.
		assertEquals(s1.hashCode(), s2.hashCode());
		assertEquals(s4.hashCode(), s5.hashCode());
		
		// Ensure that Student objects with differing instance variables have different hash codes.
		assertNotEquals(s1.hashCode(), s3.hashCode());
		assertNotEquals(s5.hashCode(), s2.hashCode());
		
	}
	
	/**
	 * Tests setCompareTo()
	 */
	@Test
	public void testCompareTo() {
		
		// Declare Student objects to compare
		Student s1 = new Student(FIRSTNAME, LASTNAME, ID, EMAIL, PASSWORD);
		Student s2 = new Student(FIRSTNAME, LASTNAME, ID, EMAIL, PASSWORD);
		Student s3 = new Student("Jim", "Bob", ID, EMAIL, PASSWORD);
		Student s4 = new Student("Alice", LASTNAME, "alast2", EMAIL, PASSWORD);
		Student s5 = new Student(FIRSTNAME, LASTNAME, "alast2", EMAIL, PASSWORD);

		// Test comparing null
		try {
			s1.compareTo(null);
			fail();
		} catch (NullPointerException e) {
			assertEquals(s1.getFirstName(), FIRSTNAME);
			assertEquals(s1.getLastName(), LASTNAME);
			assertEquals(s1.getId(), ID);
			assertEquals(s1.getEmail(), EMAIL);
			assertEquals(s1.getPassword(), PASSWORD);	
		}
		
		// Test comparing two different last names
		assertTrue(s1.compareTo(s3) > 0);
		assertTrue(s3.compareTo(s1) < 0);
		
		// Test comparing two different first names
		assertTrue(s1.compareTo(s4) > 0);
		assertTrue(s4.compareTo(s1) < 0);
		
		// Test comparing two different ids
		assertTrue(s2.compareTo(s5) > 0);
		assertTrue(s5.compareTo(s2) < 0);
		
		// Test comparing two equal Students
		assertEquals(0, s1.compareTo(s2));
		assertEquals(0, s2.compareTo(s1));
		
	}
	
	/**
	 * Tests canAdd()
	 */
	@Test
	public void testCanAdd() {
		Student s = new Student(FIRSTNAME, LASTNAME, ID, EMAIL, PASSWORD, 4);
		Schedule sched  = s.getSchedule();
		CourseCatalog catalog = new CourseCatalog();
		catalog.loadCoursesFromFile("test-files/course_records.txt");
		Course c1 = catalog.getCourseFromCatalog("CSC116", "001");
		Course c2 = catalog.getCourseFromCatalog("CSC226", "001");
		Course c3 = catalog.getCourseFromCatalog("CSC216", "001");
		
		assertTrue(s.canAdd(c1));
		assertTrue(sched.addCourseToSchedule(c1));
		assertFalse(s.canAdd(c2));
		assertFalse(s.canAdd(c3));
	}

}
