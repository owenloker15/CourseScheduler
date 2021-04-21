package edu.ncsu.csc216.pack_scheduler.user;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule;

/**
 * Represents a single student, holds all information about them and validates any new information as it is added.
 * @author Daniel Nolting
 * @author Ki Miller
 * @author Sean McKone
 */
public class Student extends User implements Comparable<Student> {
	
	/** The student's maximum number of credits */
	private int maxCredits;
	
	/** Default maximum number of credits */
	public final static int MAX_CREDITS = 18;
	
	/** Schedule for a Student's classes */
	public Schedule schedule;

	/**
	 * Constructs a student using the given parameters, setting all properties
	 * @param firstName the student's first name
	 * @param lastName the student's last name
	 * @param id the student's id
	 * @param email the student's email
	 * @param hashPW the student's password (a SHA-256 hash of it)
	 * @param maxCredits the student's maximum number of credits
	 * @throws IllegalArgumentException if any of the parameters are invalid
	 */
	public Student(String firstName, String lastName, String id, String email, String hashPW, int maxCredits) {
		super(firstName, lastName, id, email, hashPW);
		setMaxCredits(maxCredits);
		schedule = new Schedule();
	}
	
	/**
	 * Constructs a student using the given parameters, using the default value for maxCredits
	 * @param firstName the student's first name
	 * @param lastName the student's last name
	 * @param id the student's id
	 * @param email the student's email
	 * @param hashPW the student's password (a SHA-256 hash of it)
	 * @throws IllegalArgumentException if any of the parameters are invalid
	 */
	public Student(String firstName, String lastName, String id, String email, String hashPW) {
		super(firstName, lastName, id, email, hashPW);
		setMaxCredits(MAX_CREDITS);
		schedule = new Schedule();
	}
	
	/**
	 * Get the student's maximum number of credits
	 * @return the maxCredits
	 */
	public int getMaxCredits() {
		return maxCredits;
	}

	/**
	 * Sets the maximum number of credits a student can have, throwing an IllegalArgumentException if the provided value is
	 * below 3 or above 18
	 * @param maxCredits the maxCredits to set
	 * @throws IllegalArgumentException if the maxCredits is invalid
	 */
	public void setMaxCredits(int maxCredits) {
		if (maxCredits < 3 || maxCredits > 18) {
			throw new IllegalArgumentException("Invalid max credits");
		}
		this.maxCredits = maxCredits;
	}



	

	/**
	 * Returns student record information in the format:
	 * firstname, lastname, id, email, password, maximum credits a student can take
	 * @return a String representation of a Student's information
	 */
	@Override
	public String toString() {
		String studentInfo = getFirstName() + "," + getLastName() + "," + getId() + "," + getEmail() + "," + getPassword() + "," + maxCredits;
		return studentInfo;
	}

	/**
	 * Compares two different Student objects based on their last name, then first name, then unity id.
	 * 
	 * @param o the other Student to compare this Student to
	 * @return -1 if any of the other Student's fields are lexicographically greater than this Student, returns 1 if the
	 * reverse is true, and 0 if all three fields are equal between the two Students
	 */
	@Override
	public int compareTo(Student o) {
		
		if (getLastName().compareTo(o.getLastName()) < 0) {
			return -1;
		} else if (getLastName().compareTo(o.getLastName()) > 0) {
			return 1;
		
		}
		
		if (getFirstName().compareTo(o.getFirstName()) < 0) {
			return -1;
		} else if (getFirstName().compareTo(o.getFirstName()) > 0) {
			return 1;
		}
		
		if (getId().compareTo(o.getId()) < 0) {
			return -1;
		} else if (getId().compareTo(o.getId()) > 0) {
			return 1;
		}
		
		return 0;
		
		
	}
	
	/** 
	 * Getter for schedule
	 * @return the schedule of the Student
	 */
	public Schedule getSchedule() {
		return schedule;
	}
	
	/**
	 * Checks if a course can be added to a schedule by making sure that it works in the schedule
	 * and that adding this schedule doesn't exceed max credits for student
	 * @param c Course to be checked
	 * @return true if course can be added, false if not
	 */
	public boolean canAdd(Course c) {
		if(!schedule.canAdd(c)) {
			return false;
		} else {
			return !((c.getCredits() + schedule.getScheduleCredits()) > this.maxCredits);
 		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + maxCredits;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		return maxCredits == other.maxCredits;
			
	}
	
	

}
