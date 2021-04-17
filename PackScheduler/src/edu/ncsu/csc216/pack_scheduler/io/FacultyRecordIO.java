/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.util.LinkedList;

/**
 * Handles the reading and writing of individual faculty members to files.
 * 
 * @author Daniel Nolting
 * @author Calli Hooks
 * @author Owen Loker
 */
public class FacultyRecordIO {
	
	/**
	 * Reads in a specified file and creates an LinkedList of Faculty objects from file. Throws a FileNotFoundException if file cannot
	 * be read. Any invalid Faculty are ignored.
	 * @param fileName the file to read
	 * @return an LinkedList of Faculty objects created from file
	 * @throws FileNotFoundException if file cannot be read
	 */
	public static LinkedList<Faculty> readFacultyRecords(String fileName) throws FileNotFoundException {
		
		// Read file in as a FileInputStream so it can be read as a series of bytes instead of characters.
		Scanner fileReader = new Scanner(new FileInputStream(fileName));
		
		// Construct LinkedList to contain Faculty read in from file.
		LinkedList<Faculty> facultyList = new LinkedList<>();
		
		while (fileReader.hasNextLine()) {
			
			try { // Attempt to create a Faculty from the line and add it to Faculty list.
				
				Faculty faculty = processFaculty(fileReader.nextLine());
				facultyList.add(faculty);
				
				
			} catch (IllegalArgumentException e) {
				// Pass over line since it is invalid.
			}
			
		}
		fileReader.close();
		return facultyList;
		
	}
	
	/**
	 * Helper method that parses an individual line and returns a Faculty object constructed from 
	 * the parameters specified in the line. Throws an IllegalArgumentException if the line cannot be read
	 * or if the parameters are invalid.
	 * @param line the line to create a Faculty object out of
	 * @return a Faculty object created from the parameters specified in line
	 * @throws IllegalArgumentException if line cannot be read or if any parameters are invalid
	 */
	private static Faculty processFaculty(String line) {
		
		Scanner lineReader = new Scanner(line);
		lineReader.useDelimiter(",");
		
		Faculty faculty;
		
		try {
			
			String firstName = lineReader.next();
			String lastName = lineReader.next();
			String id = lineReader.next();
			String email = lineReader.next();
			String password = lineReader.next();
			int maxCredits = lineReader.nextInt();
			
			faculty = new Faculty(firstName, lastName, id, email, password, maxCredits);
			
			
			
		} catch (NoSuchElementException | IllegalArgumentException e) {
			
			lineReader.close();
			throw new IllegalArgumentException();
			
		}
		
		lineReader.close();
		return faculty;
		
		
	}
	/**
	 * Writes a list of Faculty records to the specified file
	 * 
	 * @param fileName input file name
	 * @param facultyDirectory an LinkedList of Faculty objects
	 * @throws IOException if the file cannot be written to
	 */
	public static void writeFacultyRecords(String fileName, LinkedList<Faculty> facultyDirectory) throws IOException {
		// attempt to write to the specified file
		PrintStream fileWriter = new PrintStream(new File(fileName));
		for(int i = 0; i < facultyDirectory.size(); i++) {
			fileWriter.println(facultyDirectory.get(i).toString());
		}
		fileWriter.close();
		
	}
	
	

}
