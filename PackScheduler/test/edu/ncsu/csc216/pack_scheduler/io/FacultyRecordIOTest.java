/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.io;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.util.LinkedList;

/**
 * Tests the FacultyRecordIO class
 * 
 * @author Daniel Nolting
 * @author Calli Hooks
 * @author Owen Loker
 */
public class FacultyRecordIOTest {
	/** The expected number of facultyList in faculty_records.txt */
	private final static int EXPECTED_FACULTY_COUNT = 3;
	/** Expected string representation of Faculty no. 0 in sorted faculty_records.txt */
	private String validFaculty0 = "Ashley,Witt,awitt,mollis@Fuscealiquetmagna.net,pw,2";
	/** Expected string representation of Faculty no. 1 in sorted faculty_records.txt */
	private String validFaculty1 = "Fiona,Meadows,fmeadow,pharetra.sed@et.org,pw,3";
	/** Expected string representation of Faculty no. 2 in sorted faculty_records.txt */
	private String validFaculty2 = "Brent,Brewer,bbrewer,sem.semper@orcisem.co.uk,pw,1";
	

	/** Array of every expected string representation of the facultyList from faculty_records.txt */
	private String[] validfacultyList = {validFaculty0, validFaculty1, validFaculty2};
	
	
	/** Placeholder to store the hash algorithm output */
	private String hashPW;
	/** We hash passwords using SHA-256 */
	private static final String HASH_ALGORITHM = "SHA-256";

	/**
	 * Runs before each test, ensuring that the Faculty passwords are properly hashed.
	 */
	@Before
	public void setUp() {
	    try {
	        String password = "pw";
	        MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
	        digest.update(password.getBytes());
	        hashPW = new String(digest.digest());
	        
	        for (int i = 0; i < validfacultyList.length; i++) {
	            validfacultyList[i] = validfacultyList[i].replace(",pw,", "," + hashPW + ",");
	        }
	    } catch (NoSuchAlgorithmException e) {
	        fail("Unable to create hash during setup");
	    }
	}
	
	/**
	 * Performs a series of checks to see if two files of Faculty records are identical
	 * @param expFile the file containing the expected Faculty records
	 * @param actFile the file containing the Faculty records which were actually written
	 */
	private void checkFiles(String expFile, String actFile) {
	    try {
	        Scanner expScanner = new Scanner(new FileInputStream(expFile));
	        Scanner actScanner = new Scanner(new FileInputStream(actFile));
	        
	        while (expScanner.hasNextLine()  && actScanner.hasNextLine()) {
	            String exp = expScanner.nextLine();
	            String act = actScanner.nextLine();
	            assertEquals("Expected: " + exp + " Actual: " + act, exp, act);
	        }
	        if (expScanner.hasNextLine()) {
	            fail("The expected results expect another line " + expScanner.nextLine());
	        }
	        if (actScanner.hasNextLine()) {
	            fail("The actual results has an extra, unexpected line: " + actScanner.nextLine());
	        }
	        
	        expScanner.close();
	        actScanner.close();
	    } catch (IOException e) {
	        fail("Error reading files.");
	    }
	}


	/**
	 * Tests readFacultyRecords()
	 */
	@Test
	public void testReadFacultyRecords() {
		// Check that files that don't exist throw exceptions
		LinkedList<Faculty> readfacultyList = null;
		try {
			readfacultyList = FacultyRecordIO.readFacultyRecords("test-files/nonreal_file.txt");
			fail("readFacultyRecords failed to detect that the input file does not exist.");
		} catch (FileNotFoundException e) {
			assertEquals(null, readfacultyList);
		}
		
		// Check reading from a real file
		try {
			readfacultyList = FacultyRecordIO.readFacultyRecords("test-files/faculty_records.txt");
		} catch (FileNotFoundException e) {
			fail("The faculty_records.txt file could not be found in the test-files directory.");
		}
		
		// Check that the Faculty count is correct
		assertEquals(EXPECTED_FACULTY_COUNT, readfacultyList.size());
		
		// Check that each Faculty has all its data from its string
		for (int i = 0; i < EXPECTED_FACULTY_COUNT; i++) {
			assertEquals(validfacultyList[i].toString(), readfacultyList.get(i).toString());
		}
		
		// Check empty file handling
		try {
			readfacultyList = FacultyRecordIO.readFacultyRecords("test-files/invalid_Faculty_records.txt");
		} catch (FileNotFoundException e) {
			fail("The invalid_faculty_records.txt file could not be found in the test-files directory.");
		}
		assertEquals(0, readfacultyList.size());
	}
	
//	/**
//	 * Tests writeFacultyRecords()
//	 */
//	@Test
//	public void testWriteFacultyRecordsNoPermissions() {
//	    LinkedList<Faculty> facultyList = new LinkedList<>();
//	    facultyList.add(new Faculty("Ashley", "Witt", "awitt", "mollis@Fuscealiquetmagna.net", hashPW, 2));	    
//	    try {
//	        FacultyRecordIO.writeFacultyRecords("/home/sesmith5/actual_faculty_records.txt", facultyList);
//	        fail("Attempted to write to a directory location that doesn't exist or without the appropriate permissions and the write happened.");
//	    } catch (IOException e) {
//	        assertEquals("/home/sesmith5/actual_faculty_records.txt (Permission denied)", e.getMessage());
//	    }
//	    
//	}


	/**
	 * Tests writeFacultyRecords()
	 */
	@Test
	public void testWriteFacultyRecords() {
		LinkedList<Faculty> facultyList = new LinkedList<>();
	    facultyList.add(new Faculty("Ashley", "Witt", "awitt", "mollis@Fuscealiquetmagna.net", hashPW, 2));
	    facultyList.add(new Faculty("Fiona", "Meadows", "fmeadow", "pharetra.sed@et.org", hashPW, 3));
	    facultyList.add(new Faculty("Brent", "Brewer", "bbrewer", "sem.semper@orcisem.co.uk", hashPW, 1));

	    try {
			FacultyRecordIO.writeFacultyRecords("test-files/actual_faculty_records.txt", facultyList);
		} catch (IOException e) {
			fail("Could not write to the test-files directory, error message: " + e.getMessage());
		}
	    checkFiles("test-files/expected_faculty_records.txt", "test-files/actual_faculty_records.txt");
	}

}
