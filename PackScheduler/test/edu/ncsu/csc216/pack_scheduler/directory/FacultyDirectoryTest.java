/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.directory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests the FacultyDirectory class
 * 
 * @author Daniel Nolting
 * @author Calli Hooks
 * @author Owen Loker
 */
public class FacultyDirectoryTest {
	
	/** Valid course records */
	private final String validTestFile = "test-files/faculty_records.txt";
	/** Course record file that does not exist */
	private final String nonrealTestFile = "test-files/nonreal_Faculty_records.txt";
	/** Test first name */
	private static final String FIRST_NAME = "Fac";
	/** Test last name */
	private static final String LAST_NAME = "ulty";
	/** Test id */
	private static final String ID = "fulty";
	/** Test email */
	private static final String EMAIL = "fulty@ncsu.edu";
	/** Test password */
	private static final String PASSWORD = "pw";
	/** Test max credits */
	private static final int MAX_COURSES = 2;
	
	/**
	 * Resets course_records.txt for use in other tests.
	 * @throws Exception if something fails during setup.
	 */
	@Before
	public void setUp() throws Exception {		
		//Reset Faculty_records.txt so that it's fine for other needed tests
		Path sourcePath = FileSystems.getDefault().getPath("test-files", "expected_full_faculty_records.txt");
		Path destinationPath = FileSystems.getDefault().getPath("test-files", "faculty_records.txt");
		try {
			Files.deleteIfExists(destinationPath);
			Files.copy(sourcePath, destinationPath);
		} catch (IOException e) {
			fail("Unable to reset files");
		}
	}

	/**
	 * Tests FacultyDirectory().
	 */
	@Test
	public void testFacultyDirectory() {
		//Test that the FacultyDirectory is initialized to an empty list
		FacultyDirectory fd = new FacultyDirectory();
		assertFalse(fd.removeFaculty("sesmith5"));
		assertEquals(0, fd.getFacultyDirectory().length);
		
		
		
	}

	/**
	 * Tests FacultyDirectory.testNewFacultyDirectory().
	 */
	@Test
	public void testNewFacultyDirectory() {
		//Test that if there are Faculty in the directory, they 
		//are removed after calling newFacultyDirectory().
		FacultyDirectory fd = new FacultyDirectory();
		
		fd.loadFacultyFromFile(validTestFile);
		assertEquals(10, fd.getFacultyDirectory().length);
		
		fd.newFacultyDirectory();
		assertEquals(0, fd.getFacultyDirectory().length);
	}

	/**
	 * Tests FacultyDirectory.loadFacultysFromFile().
	 */
	@Test
	public void testLoadFacultysFromFile() {
		FacultyDirectory fd = new FacultyDirectory();
				
		//Test valid file
		fd.loadFacultyFromFile(validTestFile);
		assertEquals(10, fd.getFacultyDirectory().length);
		
		//Test file that does not exist
		FacultyDirectory fd2 = new FacultyDirectory();
		try {
			fd2.loadFacultyFromFile(nonrealTestFile);
			fail("loadFacultysFromFile did not recognize that the Faculty file does not exist");
		} catch (IllegalArgumentException e) {
			assertEquals(fd2.getFacultyDirectory().length, 0);
		}
	}

	/**
	 * Tests FacultyDirectory.addFaculty().
	 */
	@Test
	public void testAddFaculty() {
		FacultyDirectory fd = new FacultyDirectory();
		
		//Test invalid Faculty (null pw, null repeat pw, empty pw, empty repeat pw)
		try {
			fd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, null, PASSWORD, MAX_COURSES);
			fail();
		} catch (IllegalArgumentException e) {
			String [][] directory = fd.getFacultyDirectory();
			assertEquals(0, directory.length);
			assertEquals("Invalid password", e.getMessage());
		}
		try {
			fd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, null, MAX_COURSES);
			fail();
		} catch (IllegalArgumentException e) {
			String [][] directory = fd.getFacultyDirectory();
			assertEquals(0, directory.length);
			assertEquals("Invalid password", e.getMessage());
		}
		try {
			fd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, "", PASSWORD, MAX_COURSES);
			fail();
		} catch (IllegalArgumentException e) {
			String [][] directory = fd.getFacultyDirectory();
			assertEquals(0, directory.length);
			assertEquals("Invalid password", e.getMessage());
		}
		try {
			fd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, "", MAX_COURSES);
			fail();
		} catch (IllegalArgumentException e) {
			String [][] directory = fd.getFacultyDirectory();
			assertEquals(0, directory.length);
			assertEquals("Invalid password", e.getMessage());
		}
		
		// Test invalid Faculty (pw mismatch)
		try {
			fd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, "abc", MAX_COURSES);
			fail();
		} catch (IllegalArgumentException e) {
			String [][] directory = fd.getFacultyDirectory();
			assertEquals(0, directory.length);
			assertEquals("Passwords do not match", e.getMessage());
		}
		
		//Test valid Faculty (trigger too few credits fail-safe)
		fd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, PASSWORD, 1);
		
		//Test valid Faculty
		fd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, PASSWORD, MAX_COURSES);
		String [][] facultyDirectory = fd.getFacultyDirectory();
		assertEquals(1, facultyDirectory.length);
		assertEquals(FIRST_NAME, facultyDirectory[0][0]);
		assertEquals(LAST_NAME, facultyDirectory[0][1]);
		assertEquals(ID, facultyDirectory[0][2]);
		
		// Test duplicate Faculty add
		fd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, PASSWORD, MAX_COURSES);
		facultyDirectory = fd.getFacultyDirectory();
		assertEquals(1, facultyDirectory.length);
		
		// Test non-duplicate Faculty add
		fd.addFaculty(FIRST_NAME, LAST_NAME, "abcool5", EMAIL, PASSWORD, PASSWORD, MAX_COURSES);
		facultyDirectory = fd.getFacultyDirectory();
		assertEquals(2, facultyDirectory.length);
		
	}

	/**
	 * Tests FacultyDirectory.removeFaculty().
	 */
	@Test
	public void testRemoveFaculty() {
		FacultyDirectory fd = new FacultyDirectory();
				
		//Add Faculty and remove
		fd.loadFacultyFromFile(validTestFile);
		assertEquals(10, fd.getFacultyDirectory().length);
		assertTrue(fd.removeFaculty("efrost"));
		String [][] facultyDirectory = fd.getFacultyDirectory();
		assertEquals(9, facultyDirectory.length);
		assertEquals("Lane", facultyDirectory[1][0]);
		assertEquals("Berg", facultyDirectory[1][1]);
		assertEquals("lberg", facultyDirectory[1][2]);
	}
	
	/**
	 * Tests saveFacultyDirectory(), specifically ensuring that it throws an exception when attempting to write somewhere it does not have permission
	 */
	@Test
	public void testSaveFacultyDirectoryNoPermissions() {
		FacultyDirectory fd = new FacultyDirectory();
		fd.addFaculty("Zahir", "King", "zking", "orci.Donec@ametmassaQuisque.com", "pw", "pw", 15);

	    try {
	    	fd.saveFacultyDirectory("/home/sesmith5/actual_Faculty_records.txt");
	        fail("Attempted to write to a directory location that doesn't exist or without the appropriate permissions and the write happened.");
	    } catch (IllegalArgumentException e) {
	        assertEquals("Unable to write to file /home/sesmith5/actual_Faculty_records.txt", e.getMessage());
	        //The actual error message on Jenkins!
	    }
	    
	}

	/**
	 * Tests FacultyDirectory.saveFacultyDirectory().
	 */
	@Test
	public void testSaveFacultyDirectory() {
		FacultyDirectory fd = new FacultyDirectory();
		
		//Add a Faculty
		fd.addFaculty("Zahir", "King", "zking", "orci.Donec@ametmassaQuisque.com", "pw", "pw", 2);
		assertEquals(1, fd.getFacultyDirectory().length);
		
		try {
	    	fd.saveFacultyDirectory("test-files/actual_faculty_records.txt");
		} catch (IllegalArgumentException e) {
			fail("Could not write to the test-files directory, error message: " + e.getMessage());
		}
	    checkFiles("test-files/expected_faculty_records.txt", "test-files/actual_faculty_records.txt");
		
	}
	
	/**
	 * Helper method to compare two files for the same contents
	 * @param expFile expected output
	 * @param actFile actual output
	 */
	private void checkFiles(String expFile, String actFile) {
		try {
			Scanner expScanner = new Scanner(new FileInputStream(expFile));
			Scanner actScanner = new Scanner(new FileInputStream(actFile));
			
			while (expScanner.hasNextLine()) {
				assertEquals(expScanner.nextLine(), actScanner.nextLine());
			}
			
			expScanner.close();
			actScanner.close();
		} catch (IOException e) {
			fail("Error reading files.");
		}
	}
}
