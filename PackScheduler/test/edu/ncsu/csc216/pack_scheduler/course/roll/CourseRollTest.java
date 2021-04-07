package edu.ncsu.csc216.pack_scheduler.course.roll;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.user.Student;

/**
 * Test class for CourseRoll that tests all methods
 * @author Kelsey Hanser, Daniel Nolting, Nick Bleuzen
 *
 */
public class CourseRollTest { 
	
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
	 * Test constructor
	 */
	@Test
	public void testCourseRoll() {
		
		CourseRoll courseRoll = new CourseRoll(100);
		assertEquals(100, courseRoll.getEnrollmentCap());
		
		try {
			courseRoll.setEnrollmentCap(9);
		}
		catch (IllegalArgumentException e) {
			assertEquals(100, courseRoll.getEnrollmentCap());
		}
		
		try {
			courseRoll.setEnrollmentCap(251);
		}
		catch (IllegalArgumentException e) {
			assertEquals(100, courseRoll.getEnrollmentCap());
		}
		
		courseRoll.setEnrollmentCap(150);
		assertEquals(150, courseRoll.getEnrollmentCap());
		
	}
	
	/**
	 * Tests enroll()
	 */
	@Test
	public void testEnroll() {
		
		Student s = new Student(FIRSTNAME, LASTNAME, ID, EMAIL, PASSWORD, MAXCREDITS);
		CourseRoll courseRoll = new CourseRoll(10);
		
		try {
			courseRoll.enroll(s);
			assertEquals(9, courseRoll.getOpenSeats());
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		try {
			courseRoll.enroll(s);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(9, courseRoll.getOpenSeats());
		}
		
		try {
			courseRoll.enroll(null);
			fail();
		} catch(IllegalArgumentException e) {
			assertEquals(9, courseRoll.getOpenSeats());
		}
		
		try {
			courseRoll.enroll(new Student("Apple", "Blueberry", "ablue", EMAIL, PASSWORD, MAXCREDITS));
			assertEquals(8, courseRoll.getOpenSeats());
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		try {
			courseRoll.enroll(new Student("Cantalope", "Dragonfruit", "cdrag", EMAIL, PASSWORD, MAXCREDITS));
			assertEquals(7, courseRoll.getOpenSeats());
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		try {
			courseRoll.enroll(new Student("Grape", "Grapefruit", "ggrape", EMAIL, PASSWORD, MAXCREDITS));
			assertEquals(6, courseRoll.getOpenSeats());
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		try {
			courseRoll.enroll(new Student("Lemon", "Lime", "llime", EMAIL, PASSWORD, MAXCREDITS));
			assertEquals(5, courseRoll.getOpenSeats());
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		try {
			courseRoll.enroll(new Student("Mango", "Melon", "mmelon", EMAIL, PASSWORD, MAXCREDITS));
			assertEquals(4, courseRoll.getOpenSeats());
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		try {
			courseRoll.enroll(new Student("Orange", "Pear", "opear", EMAIL, PASSWORD, MAXCREDITS));
			assertEquals(3, courseRoll.getOpenSeats());
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		try {
			courseRoll.enroll(new Student("Strawberry", "Watermelon", "swater", EMAIL, PASSWORD, MAXCREDITS));
			assertEquals(2, courseRoll.getOpenSeats());
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		try {
			courseRoll.enroll(new Student("Apricot", "Blackberry", "ablac", EMAIL, PASSWORD, MAXCREDITS));
			assertEquals(1, courseRoll.getOpenSeats());
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		try {
			courseRoll.enroll(new Student("Jim", "Bob", "jbob", EMAIL, PASSWORD, MAXCREDITS));
			assertEquals(0, courseRoll.getOpenSeats());
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		try {
			courseRoll.enroll(new Student("Barack", "Obama", "bobam", EMAIL, PASSWORD, MAXCREDITS));
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(0, courseRoll.getOpenSeats());
		}
	}
	
	/**
	 * Tests drop()
	 */
	@Test
	public void testDrop() {
		CourseRoll courseRoll = new CourseRoll(10);
	
		Student s = new Student("Barack", "Obama", "bobam", EMAIL, PASSWORD, MAXCREDITS);
		
		courseRoll.enroll(s);
		assertEquals(9, courseRoll.getOpenSeats());
		
		try {
			courseRoll.drop(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(9, courseRoll.getOpenSeats());
		}
		
		try {
			courseRoll.drop(s);
			assertEquals(10, courseRoll.getOpenSeats());
		} catch (IllegalArgumentException e) {
			fail();
		}
		
	}
	
	

}
