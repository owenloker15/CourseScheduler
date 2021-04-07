package edu.ncsu.csc216.pack_scheduler.catalog;

import java.io.FileNotFoundException;
import java.io.IOException;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.io.CourseRecordIO;
import edu.ncsu.csc217.collections.list.SortedList;

/**
 * Maintains a list of Courses which can be added/removed from/to and can be loaded/saved from a file
 * 
 * @author Daniel Nolting
 * @author Ki Miller
 * @author Sean McKone
 * 
 */
public class CourseCatalog {

	/** Catalog of courses created from file */
	private SortedList<Course> catalog;
	
	/**
	 * Create a new CourseCatalog
	 */
	public CourseCatalog() {
		newCourseCatalog();
		
	}
	
	/**
	 * Clear the existing catalog
	 */
	public void newCourseCatalog() {
		catalog = new SortedList<Course>();
	}
	
	/**
	 * Load a list of courses into the catalog from a file
	 * @param filename name of the file to load from
	 * @throws IllegalArgumentException if there is an error reading the file
	 */
	public void loadCoursesFromFile(String filename) {
		
		 try {
			 
			 catalog = CourseRecordIO.readCourseRecords(filename);
			 
		 } catch (FileNotFoundException e) {
			 
			 throw new IllegalArgumentException("File not found");
			 
		 }
		
	}
	
	/**
	 * Attempt to add a Course the catalog
	 * @param name the Course's name
	 * @param title the Course's title
	 * @param section the Course's section
	 * @param credits the Course's credits
	 * @param instructorId the Course's instructor id
	 * @param enrollmentCap the enrollment cap of the Course
	 * @param meetingDays the Course's meeting days
	 * @param startTime the Course's starting time
	 * @param endTime the Course's ending time
	 * @return false if the Course is a duplicate, true otherwise
	 */
	public boolean addCourseToCatalog(String name, String title, String section, int credits, String instructorId, int enrollmentCap, String meetingDays,
			int startTime, int endTime) {
		
		Course c = new Course(name, title, section, credits, instructorId, enrollmentCap, meetingDays, startTime, endTime);
		
		
		for (int i = 0; i < catalog.size(); i++) {
			
			if (catalog.get(i).isDuplicate(c)) {
				return false;
			}
			
		}
			
		catalog.add(c);
		
		return true;
		
	}
	
	/**
	 * Attempt to remove a Course from the catalog
	 * @param name the Course's name
	 * @param section the Course's section
	 * @return false if the Course is not in the catalog, true otherwise
	 */
	public boolean removeCourseFromCatalog(String name, String section) {
		
		for (int i = 0; i < catalog.size(); i++) {
			
			if (catalog.get(i).getName().equals(name) && catalog.get(i).getSection().equals(section)) {
				catalog.remove(i);
				return true;
			}
			
		}
		return false;
		
	}

	/**
	 * Gets a Course from the catalog
	 * @param name the Course's name
	 * @param section the Course's section
	 * @return the Course if it could be found in the catalog, null otherwise
	 */
	public Course getCourseFromCatalog(String name, String section) {
		
		for (int i = 0; i < catalog.size(); i++) {
			
			if (catalog.get(i).getName().equals(name) && catalog.get(i).getSection().equals(section)) {
				return catalog.get(i);
			}
			
		}
		
		return null;
	}
	
	/**
	 * Get the full catalog of courses
	 * @return the CourseCatalog in the form of a 2d array; rows are courses and columns are course attributes.
	 * The attributes are the name, section, title, and meeting string of the course
	 */
	public String[][] getCourseCatalog() {
		String[][] catalogStrings = new String[catalog.size()][5];
		for (int i = 0; i < catalog.size(); i++) {
			Course c = catalog.get(i);
			catalogStrings[i] = c.getShortDisplayArray();
		}
		return catalogStrings;
	}
	
	/**
	 * Save the current course catalog to a file
	 * @param filename the file to write to
	 * @throws IllegalArgumentException  if there is an error when writing to the file
	 */
	public void saveCourseCatalog(String filename) {
		try {
			CourseRecordIO.writeCourseRecords(filename, catalog);
		} catch (IOException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}
	
	
		
}



