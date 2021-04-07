/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.catalog;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;

/**
 * Tests the CourseCatalog class
 * 
 * @author Daniel Nolting
 * @author Ki Miller
 * @author Sean McKone
 *
 */
public class CourseCatalogTest {

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog#CourseCatalog()}.
	 */
	@Test
	public void testCourseCatalog() {
		CourseCatalog catalog = new CourseCatalog();
		assertEquals(catalog.getCourseCatalog().length, 0);
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog#newCourseCatalog()}.
	 */
	@Test
	public void testNewCourseCatalog() {
		CourseCatalog catalog = new CourseCatalog();
		catalog.loadCoursesFromFile("test-files/course_records.txt");
		assertEquals(catalog.getCourseCatalog().length, 13);
		catalog.newCourseCatalog();
		assertEquals(catalog.getCourseCatalog().length, 0);
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog#loadCoursesFromFile(java.lang.String)}.
	 */
	@Test
	public void testLoadCoursesFromFile() {
		CourseCatalog catalog = new CourseCatalog();
		
		try {
			catalog.loadCoursesFromFile("does_not_exist.txt");
			fail("No error when reading from a file that does not exist");
		} catch (IllegalArgumentException e) {
			assertEquals(catalog.getCourseCatalog().length, 0);
			assertEquals(e.getMessage(), "File not found");
		}
		
		catalog.loadCoursesFromFile("test-files/course_records.txt");
		assertEquals(catalog.getCourseCatalog().length, 13);
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog#addCourseToCatalog(java.lang.String, java.lang.String, java.lang.String, int, java.lang.String, java.lang.String, int, int)}.
	 */
	@Test
	public void testAddCourseToCatalog() {
		CourseCatalog catalog = new CourseCatalog();
		assertTrue(catalog.addCourseToCatalog("CSC111", "Title 1", "223", 1, "abcdef", 10, "W", 1500, 1650));
		assertEquals(catalog.getCourseCatalog().length, 1);
		assertFalse(catalog.addCourseToCatalog("CSC111", "Title 1", "223", 1, "abcdef", 10, "W", 1500, 1650));
		assertEquals(catalog.getCourseCatalog().length, 1);
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog#removeCourseFromCatalog(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testRemoveCourseFromCatalog() {
		CourseCatalog catalog = new CourseCatalog();
		assertFalse(catalog.removeCourseFromCatalog("CSC111", "223"));
		assertEquals(catalog.getCourseCatalog().length, 0);
		assertTrue(catalog.addCourseToCatalog("CSC111", "Title 1", "223", 1, "abcdef", 10, "W", 1500, 1650));
		assertEquals(catalog.getCourseCatalog().length, 1);
		assertTrue(catalog.removeCourseFromCatalog("CSC111", "223"));
		assertEquals(catalog.getCourseCatalog().length, 0);
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog#getCourseFromCatalog(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testGetCourseFromCatalog() {
		CourseCatalog catalog = new CourseCatalog();
		catalog.loadCoursesFromFile("test-files/course_records.txt");
		Course c = catalog.getCourseFromCatalog("NotInCatalog", "000");
		assertEquals(c, null);
		c = catalog.getCourseFromCatalog("CSC230", "001");
		Course expected = new Course("CSC230", "C and Software Tools", "001", 3, "dbsturgi", 10, "MW");
		expected.setMeetingDaysAndTime("MW", 1145, 1300);
		assertEquals(c, expected);
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog#getCourseCatalog()}.
	 */
	@Test
	public void testGetCourseCatalog() {
		CourseCatalog catalog = new CourseCatalog();
		assertTrue(catalog.addCourseToCatalog("CSC111", "Title 1", "223", 1, "abcdef", 10, "MW", 1500, 1650));
		String[][] expected = {{"CSC111", "223", "Title 1", "MW 3:00PM-4:50PM"}};
		for (int i = 0; i < expected.length; i++) {
			for (int j = 0; j < expected[i].length; j++) {
				assertEquals(expected[i][j], catalog.getCourseCatalog()[i][j]);
			}
		}
	}
	
	/**
	 * Helper method to compare two files for the same contents
	 * @param expFile expected output
	 * @param actFile actual output
	 */
	private void checkFiles(String expFile, String actFile) {
		try (Scanner expScanner = new Scanner(new FileInputStream(expFile));
			 Scanner actScanner = new Scanner(new FileInputStream(actFile));) {
			
			while (expScanner.hasNextLine()) {
				assertEquals(expScanner.nextLine(), actScanner.nextLine());
			}
			
			expScanner.close();
			actScanner.close();
		} catch (IOException e) {
			fail("Error reading files.");
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog#saveCourseCatalog(java.lang.String)}.
	 */
	@Test
	public void testSaveCourseCatalog() {
		CourseCatalog catalog = new CourseCatalog();
		catalog.loadCoursesFromFile("test-files/course_records.txt");
		catalog.saveCourseCatalog("test-files/saved_course_records.txt");
		checkFiles("test-files/expected_saved_courses.txt", "test-files/saved_course_records.txt");
		try {
			catalog.saveCourseCatalog("/home/sesmith5/saved_courses.txt");
			fail("Saved course catalog to an invalid location without throwing an exception.");
		} catch (IllegalArgumentException e) {
			assertEquals("/home/sesmith5/saved_courses.txt (Permission denied)", e.getMessage());
		}
	}

}
