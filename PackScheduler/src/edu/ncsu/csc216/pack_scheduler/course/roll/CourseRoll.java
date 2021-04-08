package edu.ncsu.csc216.pack_scheduler.course.roll;

import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.util.ArrayStack;
import edu.ncsu.csc216.pack_scheduler.util.LinkedAbstractList;

/**
 * CourseRoll holds a list of all studnets enrolled in a course and has a
 * parameter for the enrollment cap of the course CourseRoll can enroll and drop
 * students from a Course, check if a student can enroll, and return open seats
 * in course
 * 
 * @author Kelsey Hanser, Daniel Nolting, Bleuzen
 *
 */
public class CourseRoll {

	/** Minimum number of students allowed in a Course */
	private static final int MIN_ENROLLMENT = 10;
	/** Maximum number of students allowed in a Course */
	private static final int MAX_ENROLLMENT = 250;

	/** List of Students enrolled */
	private LinkedAbstractList<Student> roll;
	/** Maximum number of students allowed to enroll */
	private int enrollmentCap;
	/** ArrayStack of Students in the waitlist */
	private ArrayStack<Student> waitlist;

	/**
	 * Constructor for CourseRoll Initializes list to an empty list with capacity of
	 * enrollmentCap and sets enrollmentCap to enrollmentCap
	 * 
	 * @param enrollmentCap maximum number of students that can enroll in course
	 */
	public CourseRoll(int enrollmentCap) {
		roll = new LinkedAbstractList<Student>(enrollmentCap);
		setEnrollmentCap(enrollmentCap);
	}

	/**
	 * Returns enrollmentCap
	 * 
	 * @return enrollmentCap field
	 */
	public int getEnrollmentCap() {
		return enrollmentCap;
	}

	/**
	 * Sets enrollmentCap to enrollmentCap passed in if enrollmentCap is more than
	 * the minimum number of students that can be enrolled in a class, less than the
	 * maximum number of students that can be enrolled in class, and is greater than
	 * the number of students already in the class
	 * 
	 * @param enrollmentCap to be set
	 * @throws IllegalArgumentException if enrollmentCap is not correct
	 */
	public void setEnrollmentCap(int enrollmentCap) {
		if (enrollmentCap < MIN_ENROLLMENT || enrollmentCap > MAX_ENROLLMENT) {
			throw new IllegalArgumentException();
		}
		if (this.roll.size() > enrollmentCap) {
			throw new IllegalArgumentException();
		}

		roll.setCapacity(enrollmentCap);

		this.enrollmentCap = enrollmentCap;
	}

	/**
	 * Returns the number of seats open in the class, which is enrollmentCap minus
	 * the number of Students in the class
	 * 
	 * @return the number of open seats in the class
	 */
	public int getOpenSeats() {
		return enrollmentCap - roll.size();
	}

	/**
	 * Checks if student can enroll in class and if they can, adds them to list of
	 * students in class
	 * 
	 * @param s Student to be enrolled in class
	 * @throws IllegalArgumentException if s is null or can't enroll in class
	 */
	public void enroll(Student s) {
		if (s == null || !canEnroll(s)) {
			throw new IllegalArgumentException();
		}
		roll.add(s);
	}

	/**
	 * Removes student from roll
	 * 
	 * @param s Student to be dropped from class
	 * @throws IllegaArgumentException if s is null
	 */
	public void drop(Student s) {
		if (s == null) {
			throw new IllegalArgumentException();
		}
		try {
			for (int i = 0; i < roll.size(); i++) {
				if (roll.get(i).equals(s)) {
					roll.remove(i);
					return;
				}
			}
		} catch (Exception e) {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Checks if a student can enroll in class by checking if there are open seats
	 * and if the student is already enrolled in class
	 * 
	 * @param s Student to check if they can enroll
	 * @return false if they can't enroll in class true if they can enroll in class
	 */
	public boolean canEnroll(Student s) {
		if (getOpenSeats() == 0) {
			return false;
		}
		for (int i = 0; i < roll.size(); i++) {
			if (roll.get(i).equals(s)) {
				return false;
			}
		}
		return true;
	}

}
