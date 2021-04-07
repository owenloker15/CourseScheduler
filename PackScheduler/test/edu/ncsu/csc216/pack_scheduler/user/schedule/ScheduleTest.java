package edu.ncsu.csc216.pack_scheduler.user.schedule;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog;
import edu.ncsu.csc216.pack_scheduler.course.Course;

/**
 * Test class for Schedule
 * @author Daniel Nolting, Kelsey Hanser, Nick Bleuzen
 *
 */
public class ScheduleTest {

	/**
	 * Tests constructor
	 */
	@Test
	public void testSchedule() {
		Schedule s = new Schedule();
		assertEquals("My Schedule", s.getTitle());
		assertEquals(0, s.getScheduledCourses().length);
	}
	
	/**
	 * Tests remove course from schedule
	 */
	@Test
	public void testRemoveCourseFromSchedule() {
		
		Schedule s = new Schedule();
		CourseCatalog catalog = new CourseCatalog();
		catalog.loadCoursesFromFile("test-files/course_records.txt");
		Course c1 = catalog.getCourseFromCatalog("CSC116", "001");
		Course c2 = catalog.getCourseFromCatalog("CSC216", "001");
		Course c3 = catalog.getCourseFromCatalog("CSC230", "001");
		
		s.addCourseToSchedule(c1);
		s.addCourseToSchedule(c2);
		s.addCourseToSchedule(c3);
		
		assertTrue(s.removeCourseFromSchedule(c1));
		assertFalse(s.removeCourseFromSchedule(catalog.getCourseFromCatalog("CSC216", "002")));
		
	}
	
	/**
	 * Tests the add course to schedule function
	 */
	@Test 
	public void testAddCourseToSchedule() {
		Schedule s = new Schedule();
		CourseCatalog catalog = new CourseCatalog();
		catalog.loadCoursesFromFile("test-files/course_records.txt");
		Course c1 = catalog.getCourseFromCatalog("CSC116", "001");
		Course c2 = catalog.getCourseFromCatalog("CSC226", "001");
		Course c3 = catalog.getCourseFromCatalog("CSC216", "001");
		Course c4 = catalog.getCourseFromCatalog("CSC216", "002");
		String[] c1Display = c1.getShortDisplayArray();
		
		try {
			assertTrue(s.addCourseToSchedule(c1));
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		try {
			s.addCourseToSchedule(c1);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("You are already enrolled in CSC116", e.getMessage());
		}
		
		try {
			s.addCourseToSchedule(c2);
		} catch (IllegalArgumentException e) {
			assertEquals("The course cannot be added due to a conflict.", e.getMessage());
		}
		
		try {
			assertTrue(s.addCourseToSchedule(c3));
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		try {
			s.addCourseToSchedule(c4);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("You are already enrolled in CSC216", e.getMessage());
		}
		
		assertEquals(c1Display[0], s.getScheduledCourses()[0][0]);
		assertEquals(c1Display[1], s.getScheduledCourses()[0][1]);
		assertEquals(c1Display[2], s.getScheduledCourses()[0][2]);
		assertEquals(c1Display[3], s.getScheduledCourses()[0][3]);
		assertEquals(c1Display[4], s.getScheduledCourses()[0][4]);
		
		s.resetSchedule();
		assertEquals("My Schedule", s.getTitle());
		assertEquals(0, s.getScheduledCourses().length);
		
	}
	
	/**
	 * Tests the set title function
	 */
	@Test
	public void testSetTitle() {
		Schedule s = new Schedule();
		assertEquals("My Schedule", s.getTitle());
		assertEquals(0, s.getScheduledCourses().length);
		
		s.setTitle("New Title");
		assertEquals("New Title", s.getTitle());
		
		try {
			s.setTitle(null);
			fail();
		} catch(IllegalArgumentException e) {
			assertEquals("Title cannot be null.", e.getMessage());
		}
		
		s.resetSchedule();
		assertEquals("My Schedule", s.getTitle());
		assertEquals(0, s.getScheduledCourses().length);

	}
	
	/**
	 * Tests getScheduledCredits()
	 */
	@Test
	public void getScheduledCredits() {
		Schedule schedule = new Schedule();
		CourseCatalog catalog = new CourseCatalog();
		catalog.loadCoursesFromFile("test-files/course_records.txt");
		Course c1 = catalog.getCourseFromCatalog("CSC116", "001");
		Course c3 = catalog.getCourseFromCatalog("CSC216", "001");
		
		assertTrue(schedule.addCourseToSchedule(c1));
		assertTrue(schedule.addCourseToSchedule(c3));
		
		assertEquals(6, schedule.getScheduleCredits());
		
	}
	
	/**
	 * Tests canAdd()
	 */
	@Test
	public void canAdd() {
		Schedule s = new Schedule();
		CourseCatalog catalog = new CourseCatalog();
		catalog.loadCoursesFromFile("test-files/course_records.txt");
		Course c1 = catalog.getCourseFromCatalog("CSC116", "001");
		Course c2 = catalog.getCourseFromCatalog("CSC226", "001");
		Course c3 = catalog.getCourseFromCatalog("CSC216", "001");
		
		assertTrue(s.canAdd(c1));
		assertTrue(s.addCourseToSchedule(c1));
		assertFalse(s.canAdd(c2));
		assertTrue(s.canAdd(c3));
		assertTrue(s.addCourseToSchedule(c3));
	}

}
