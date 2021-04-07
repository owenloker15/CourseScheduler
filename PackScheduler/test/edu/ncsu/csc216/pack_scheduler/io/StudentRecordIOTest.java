package edu.ncsu.csc216.pack_scheduler.io;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc217.collections.list.SortedList;

/**
 * Tests StudentRecordIO.
 * 
 * @author Daniel Nolting
 * @author Ki Miller
 * @author Sean McKone
 */
public class StudentRecordIOTest {

	/** The expected number of students in student_records.txt */
	private final static int EXPECTED_STUDENT_COUNT = 10;
	/** Expected string representation of student no. 0 in sorted student_records.txt */
	private String validStudent0 = "Demetrius,Austin,daustin,Curabitur.egestas.nunc@placeratorcilacus.co.uk,pw,18";
	/** Expected string representation of student no. 1 in sorted student_records.txt */
	private String validStudent1 = "Lane,Berg,lberg,sociis@non.org,pw,14";
	/** Expected string representation of student no. 2 in sorted student_records.txt */
	private String validStudent2 = "Raymond,Brennan,rbrennan,litora.torquent@pellentesquemassalobortis.ca,pw,12";
	/** Expected string representation of student no. 3 in sorted student_records.txt */
	private String validStudent3 = "Emerald,Frost,efrost,adipiscing@acipsumPhasellus.edu,pw,3";
	/** Expected string representation of student no. 4 in sorted student_records.txt */
	private String validStudent4 = "Shannon,Hansen,shansen,convallis.est.vitae@arcu.ca,pw,14"; 
	/** Expected string representation of student no. 5 in sorted student_records.txt */
	private String validStudent5 = "Althea,Hicks,ahicks,Phasellus.dapibus@luctusfelis.com,pw,11";
	/** Expected string representation of student no. 6 in sorted student_records.txt */
	private String validStudent6 = "Zahir,King,zking,orci.Donec@ametmassaQuisque.com,pw,15";
	/** Expected string representation of student no. 7 in sorted student_records.txt */
	private String validStudent7 = "Dylan,Nolan,dnolan,placerat.Cras.dictum@dictum.net,pw,5";
	/** Expected string representation of student no. 8 in sorted student_records.txt */
	private String validStudent8 = "Cassandra,Schwartz,cschwartz,semper@imperdietornare.co.uk,pw,4";
	/** Expected string representation of student no. 9 in sorted student_records.txt */
	private String validStudent9 = "Griffith,Stone,gstone,porta@magnamalesuadavel.net,pw,17";

	/** Array of every expected string representation of the students from student_records.txt */
	private String[] validStudents = {validStudent0, validStudent1, validStudent2, validStudent3, validStudent4, validStudent5,
	        validStudent6, validStudent7, validStudent8, validStudent9};
	
	

	/** Placeholder to store the hash algorithm output */
	private String hashPW;
	/** We hash passwords using SHA-256 */
	private static final String HASH_ALGORITHM = "SHA-256";

	/**
	 * Runs before each test, ensuring that the student passwords are properly hashed.
	 */
	@Before
	public void setUp() {
	    try {
	        String password = "pw";
	        MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
	        digest.update(password.getBytes());
	        hashPW = new String(digest.digest());
	        
	        for (int i = 0; i < validStudents.length; i++) {
	            validStudents[i] = validStudents[i].replace(",pw,", "," + hashPW + ",");
	        }
	    } catch (NoSuchAlgorithmException e) {
	        fail("Unable to create hash during setup");
	    }
	}
	
	/**
	 * Performs a series of checks to see if two files of student records are identical
	 * @param expFile the file containing the expected student records
	 * @param actFile the file containing the student records which were actually written
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
	 * Tests readStudentRecords()
	 */
	@Test
	public void testReadStudentRecords() {
		// Check that files that don't exist throw exceptions
		SortedList<Student> readStudents = null;
		try {
			readStudents = StudentRecordIO.readStudentRecords("test-files/nonreal_file.txt");
			fail("readStudentRecords failed to detect that the input file does not exist.");
		} catch (FileNotFoundException e) {
			assertEquals(null, readStudents);
		}
		
		// Check reading from a real file
		try {
			readStudents = StudentRecordIO.readStudentRecords("test-files/student_records.txt");
		} catch (FileNotFoundException e) {
			fail("The student_records.txt file could not be found in the test-files directory.");
		}
		
		// Check that the student count is correct
		assertEquals(EXPECTED_STUDENT_COUNT, readStudents.size());
		
		// Check that each Student has all its data from its string
		for (int i = 0; i < EXPECTED_STUDENT_COUNT; i++) {
			assertEquals(validStudents[i].toString(), readStudents.get(i).toString());
		}
		
		// Check empty file handling
		try {
			readStudents = StudentRecordIO.readStudentRecords("test-files/invalid_student_records.txt");
		} catch (FileNotFoundException e) {
			fail("The invalid_student_records.txt file could not be found in the test-files directory.");
		}
		assertEquals(0, readStudents.size());
	}
	
	/**
	 * Tests writeStudentRecords(), specifically ensuring that it throws an exception when attempting to write somewhere it does not have permission
	 */
	@Test
	public void testWriteStudentRecordsNoPermissions() {
	    SortedList<Student> students = new SortedList<>();
	    students.add(new Student("Zahir", "King", "zking", "orci.Donec@ametmassaQuisque.com", hashPW, 15));
	    //Assumption that you are using a hash of "pw" stored in hashPW
	    
	    try {
	        StudentRecordIO.writeStudentRecords("/home/sesmith5/actual_student_records.txt", students);
	        fail("Attempted to write to a directory location that doesn't exist or without the appropriate permissions and the write happened.");
	    } catch (IOException e) {
	        assertEquals("/home/sesmith5/actual_student_records.txt (Permission denied)", e.getMessage());
	        //The actual error message on Jenkins!
	    }
	    
	}


	/**
	 * Tests writeStudentRecords()
	 */
	@Test
	public void testWriteStudentRecords() {
		SortedList<Student> students = new SortedList<>();
	    students.add(new Student("Zahir", "King", "zking", "orci.Donec@ametmassaQuisque.com", hashPW, 15));
	    try {
			StudentRecordIO.writeStudentRecords("test-files/actual_student_records.txt", students);
		} catch (IOException e) {
			fail("Could not write to the test-files directory, error message: " + e.getMessage());
		}
	    checkFiles("test-files/expected_student_records.txt", "test-files/actual_student_records.txt");
	}

}
