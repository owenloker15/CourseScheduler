package edu.ncsu.csc216.pack_scheduler.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.io.IOException;
import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc217.collections.list.SortedList;

/**
 * Handles the reading and writing of individual students to files.
 * @author Daniel Nolting
 * @author Ki Miller
 * @author Sean McKone
 */
public class StudentRecordIO {
	
	/**
	 * Reads in a specified file and creates an ArrayList of Student objects from file. Throws a FileNotFoundException if file cannot
	 * be read. Any invalid Students are ignored.
	 * @param fileName the file to read
	 * @return an ArrayList of Student objects created from file
	 * @throws FileNotFoundException if file cannot be read
	 */
	public static SortedList<Student> readStudentRecords(String fileName) throws FileNotFoundException {
		
		// Read file in as a FileInputStream so it can be read as a series of bytes instead of characters.
		Scanner fileReader = new Scanner(new FileInputStream(fileName));
		
		// Construct SortedList to contain students read in from file.
		SortedList<Student> students = new SortedList<>();
		
		while (fileReader.hasNextLine()) {
			
			try { // Attempt to create a student from the line and add it to students.
				
				Student student = processStudent(fileReader.nextLine());
				students.add(student);
				
				
			} catch (IllegalArgumentException e) {
				// Pass over line since it is invalid.
			}
			
		}
		fileReader.close();
		return students;
		
	}
	
	/**
	 * Helper method that parses an individual line and returns a Student object constructed from 
	 * the parameters specified in the line. Throws an IllegalArgumentException if the line cannot be read
	 * or if the parameters are invalid.
	 * @param line the line to create a Student object out of
	 * @return a Student object created from the parameters specified in line
	 * @throws IllegalArgumentException if line cannot be read or if any parameters are invalid
	 */
	private static Student processStudent(String line) {
		
		Scanner lineReader = new Scanner(line);
		lineReader.useDelimiter(",");
		
		Student student;
		
		try {
			
			String firstName = lineReader.next();
			String lastName = lineReader.next();
			String id = lineReader.next();
			String email = lineReader.next();
			String password = lineReader.next();
			int maxCredits = lineReader.nextInt();
			
			student = new Student(firstName, lastName, id, email, password, maxCredits);
			
			
			
		} catch (NoSuchElementException | IllegalArgumentException e) {
			
			lineReader.close();
			throw new IllegalArgumentException();
			
		}
		
		lineReader.close();
		return student;
		
		
	}
	/**
	 * Writes a list of Student records to the specified file
	 * 
	 * @param fileName input file name
	 * @param studentDirectory an ArrayList of Student objects
	 * @throws IOException if the file cannot be written to
	 */
	public static void writeStudentRecords(String fileName, SortedList<Student> studentDirectory) throws IOException {
		// attempt to write to the specified file
		PrintStream fileWriter = new PrintStream(new File(fileName));
		for(int i = 0; i < studentDirectory.size(); i++) {
			fileWriter.println(studentDirectory.get(i).toString());
		}
		fileWriter.close();
		
	}
	
	

}
