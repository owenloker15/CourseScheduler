package edu.ncsu.csc216.pack_scheduler.course.roll;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;
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
	/** The Course associated with the CourseRoll */
	private Course c;
	/** A test CourseRoll */
	private CourseRoll roll;

	
	/**
	 * Resets the Course and the CourseRoll
	 */
	@Before
	public void setUp() {
		c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
		roll = c.getCourseRoll();
	}
	
	
	
	
	/**
	 * Test constructor
	 */
	@Test
	public void testCourseRoll() {
		
		assertEquals(10, roll.getEnrollmentCap());
		
		try {
			roll.setEnrollmentCap(9);
		}
		catch (IllegalArgumentException e) {
			assertEquals(10, roll.getEnrollmentCap());
		}
		
		try {
			roll.setEnrollmentCap(251);
		}
		catch (IllegalArgumentException e) {
			assertEquals(10, roll.getEnrollmentCap());
		}
		
		roll.setEnrollmentCap(150);
		assertEquals(150, roll.getEnrollmentCap());
		
	}
	
	/**
	 * Tests enroll()
	 */
	@Test
	public void testEnroll() {
		
		Student s = new Student(FIRSTNAME, LASTNAME, ID, EMAIL, PASSWORD, MAXCREDITS);
		
		try {
			roll.enroll(s);
			assertEquals(9, roll.getOpenSeats());
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		try {
			roll.enroll(s);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(9, roll.getOpenSeats());
		}
		
		try {
			roll.enroll(null);
			fail();
		} catch(IllegalArgumentException e) {
			assertEquals(9, roll.getOpenSeats());
		}
		
		try {
			roll.enroll(new Student("Apple", "Blueberry", "ablue", EMAIL, PASSWORD, MAXCREDITS));
			assertEquals(8, roll.getOpenSeats());
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		try {
			roll.enroll(new Student("Cantalope", "Dragonfruit", "cdrag", EMAIL, PASSWORD, MAXCREDITS));
			assertEquals(7, roll.getOpenSeats());
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		try {
			roll.enroll(new Student("Grape", "Grapefruit", "ggrape", EMAIL, PASSWORD, MAXCREDITS));
			assertEquals(6, roll.getOpenSeats());
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		try {
			roll.enroll(new Student("Lemon", "Lime", "llime", EMAIL, PASSWORD, MAXCREDITS));
			assertEquals(5, roll.getOpenSeats());
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		try {
			roll.enroll(new Student("Mango", "Melon", "mmelon", EMAIL, PASSWORD, MAXCREDITS));
			assertEquals(4, roll.getOpenSeats());
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		try {
			roll.enroll(new Student("Orange", "Pear", "opear", EMAIL, PASSWORD, MAXCREDITS));
			assertEquals(3, roll.getOpenSeats());
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		try {
			roll.enroll(new Student("Strawberry", "Watermelon", "swater", EMAIL, PASSWORD, MAXCREDITS));
			assertEquals(2, roll.getOpenSeats());
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		try {
			roll.enroll(new Student("Apricot", "Blackberry", "ablac", EMAIL, PASSWORD, MAXCREDITS));
			assertEquals(1, roll.getOpenSeats());
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		try {
			roll.enroll(new Student("Jim", "Bob", "jbob", EMAIL, PASSWORD, MAXCREDITS));
			assertEquals(0, roll.getOpenSeats());
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		try {
			roll.enroll(new Student("Barack", "Obama", "bobam", EMAIL, PASSWORD, MAXCREDITS));
			assertEquals(0, roll.getOpenSeats());
			assertEquals(1, roll.getNumberOnWaitlist());
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		// Test dropping from the waitlist
		try {
			roll.drop(new Student("Barack", "Obama", "bobam", EMAIL, PASSWORD, MAXCREDITS));
			assertEquals(0, roll.getOpenSeats());
			assertEquals(0, roll.getNumberOnWaitlist());
		} catch (IllegalArgumentException e) {
			fail();
		}
	}
	
	/**
	 * Tests drop()
	 */
	@Test
	public void testDrop() {
	
		Student s = new Student("Barack", "Obama", "bobam", EMAIL, PASSWORD, MAXCREDITS);
		
		roll.enroll(s);
		assertEquals(9, roll.getOpenSeats());
		
		try {
			roll.drop(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(9, roll.getOpenSeats());
		}
		
		try {
			roll.drop(s);
			assertEquals(10, roll.getOpenSeats());
		} catch (IllegalArgumentException e) {
			fail();
		}
		
	}
	
	/**
	 * Tests drop and removeStudentFromWaitlist()
	 */
	@Test
	public void testRemoveStudentFromWaitlist() {
		Student s1 = new Student(FIRSTNAME, LASTNAME, ID, EMAIL, PASSWORD, MAXCREDITS);
		roll.enroll(s1);
		Student s2 = new Student("Barack", "Obama", "bobam", EMAIL, PASSWORD, MAXCREDITS);
		roll.enroll(s2);
		Student s3 = new Student("Jim", "Bob", "jbob", EMAIL, PASSWORD, MAXCREDITS);
		roll.enroll(s3);
		Student s4 = new Student("John", "Doe", "jdoe", EMAIL, PASSWORD, MAXCREDITS);
		roll.enroll(s4);
		Student s5 = new Student("Jane", "Doe", "jdoe2", EMAIL, PASSWORD, MAXCREDITS);
		roll.enroll(s5);
		Student s6 = new Student("Jimmy", "John", "jjohn", EMAIL, PASSWORD, MAXCREDITS);
		roll.enroll(s6);
		Student s7 = new Student("First", "Last", "flast", EMAIL, PASSWORD, MAXCREDITS);
		roll.enroll(s7);
		Student s8 = new Student("Joe", "Smith", "jsmith", EMAIL, PASSWORD, MAXCREDITS);
		roll.enroll(s8);
		Student s9 = new Student("Sue", "Miller", "smiller", EMAIL, PASSWORD, MAXCREDITS);
		roll.enroll(s9);
		Student s10 = new Student("James", "Jones", "jjones", EMAIL, PASSWORD, MAXCREDITS);
		roll.enroll(s10);
		
		Student s11 = new Student("a", "miller", "amiller", EMAIL, PASSWORD, MAXCREDITS);
		roll.enroll(s11);
		Student s12 = new Student("b", "miller", "bmiller", EMAIL, PASSWORD, MAXCREDITS);
		roll.enroll(s12);
		Student s13 = new Student("c", "miller", "cmiller", EMAIL, PASSWORD, MAXCREDITS);
		roll.enroll(s13);
		
		assertEquals(3, roll.getNumberOnWaitlist());
		
		roll.drop(s12);
		assertEquals(2, roll.getNumberOnWaitlist());
		roll.drop(s13);
		assertEquals(1, roll.getNumberOnWaitlist());
	}
	
}
