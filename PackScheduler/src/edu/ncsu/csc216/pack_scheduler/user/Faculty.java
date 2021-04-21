/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.user;

import edu.ncsu.csc216.pack_scheduler.user.schedule.FacultySchedule;
import edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule;

/**
 * The Faculty class creates new Faculty members for the system, given their
 * firstName, lastName, id, email, password, and maxCourses.
 * 
 * @author Daniel Nolting
 * @author Calli Hooks
 * @author Owen Loker
 */
public class Faculty extends User {

	/** Minimum number of courses a Faculty member may have */
	public static final int MIN_COURSES = 1;
	/** Maximum number of courses a Faculty member may have */
	public static final int MAX_COURSES = 3;
	/** Maximum number of courses a given Faculty member has */
	private int maxCourses;
	/**Instance of faculty schedule*/
	private FacultySchedule facultySchedule;

	/**
	 * Creates new Faculty members for the system, given their firstName, lastName,
	 * id, email, password, and maxCourses.
	 * 
	 * @param firstName  the first name of the faculty member
	 * @param lastName   the last name of the faculty member
	 * @param id         the id of the faculty member
	 * @param email      the email of the faculty member
	 * @param hashPW     the password of the faculty member
	 * @param maxCourses the maximum number of courses for the faculty member
	 */
	public Faculty(String firstName, String lastName, String id, String email, String hashPW, int maxCourses) {
		super(firstName, lastName, id, email, hashPW);
		setMaxCourses(maxCourses);
		facultySchedule = new FacultySchedule(id);
	}

	/**
	 * Returns the maximum number of courses a Faculty member may have
	 * 
	 * @return the maxCourses the maximum number of courses the Faculty member has
	 */
	public int getMaxCourses() {
		return maxCourses;
	}

	/**
	 * Sets the maximum number of courses a Faculty member may have
	 * 
	 * @param maxCourses the maximum number of courses a Faculty member has
	 * @throws IllegalArgumentException if the maxCourses is less than the minimum
	 *                                  or greater than the maximum number of
	 *                                  courses a Faculty member may have
	 */
	public void setMaxCourses(int maxCourses) {
		if (maxCourses < MIN_COURSES || maxCourses > MAX_COURSES) {
			throw new IllegalArgumentException();
		}
		this.maxCourses = maxCourses;
	}

	/**
	 * HashCode of the Faculty object
	 * 
	 * @return int the integer HashCode of the Faculty object
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + maxCourses;
		return result;
	}

	/**
	 * Returns true if two Faculty objects are equal, else returns false
	 * 
	 * @param obj the faculty object to compare with
	 * @return boolean representing whether two Faculty objects are equal
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Faculty other = (Faculty) obj;
		return maxCourses == other.maxCourses;
	}

	/**
	 * Returns the String representation of the Faculty member
	 * 
	 * @return String representation of the Faculty member and their given
	 *         information
	 */
	@Override
	public String toString() {
		String facultyInfo = getFirstName() + "," + getLastName() + "," + getId() + "," + getEmail() + ","
				+ getPassword() + "," + maxCourses;
		return facultyInfo;
	}
	
	/** 
	 * Getter for faculty schedule
	 * @return the schedule of the faculty
	 */
	public FacultySchedule getSchedule() {
		return facultySchedule;
	}
	
	/**
	 *  Returns true if the number of schedule courses is greater than the faculty's max courses
	 *  @return boolean if the schedlue is overloaded
	 */
	public boolean isOverloaded() {
		if (facultySchedule.getNumScheduledCourses() > this.getMaxCourses()) { //may need to be final field of MAX_COURSES
			return true;
		}
		return false;
	}
}
