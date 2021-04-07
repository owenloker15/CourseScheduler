package edu.ncsu.csc216.pack_scheduler.user.schedule;

import edu.ncsu.csc216.pack_scheduler.course.ConflictException;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.util.ArrayList;

/**
 * A class representing a Student's schedule of classes. ALlows client to 
 * add, remove, and get Courses.
 * @author Daniel Nolting, Kelsey Hanser, Nick Bleuzen
 *
 */
public class Schedule {
	
	/** An ArrayList of courses taken by a Student */
	private ArrayList<Course> schedule;
	/** The title of the Schedule */
	private String title;
	/** Constructs a Schedule with default "My Schedule" title and an empty ArrayList for schedule */
	public Schedule() {
		title = "My Schedule";
		schedule = new ArrayList<Course>();
	}
	
	/**
	 * Returns a 2D array of Courses on the schedule
	 * @return a String[][] containing each Course's name, section, title, and meeting string
	 */
	public String[][] getScheduledCourses() {
		String[][] scheduledCourses = new String[schedule.size()][5];
		for (int i = 0; i < schedule.size(); i++) {
			scheduledCourses[i] = schedule.get(i).getShortDisplayArray();
		}
		return scheduledCourses;
	}
	
	/**
	 * Sets the title of the Schedule
	 * @param title the title to set
	 * @throws IllegalArgumentException if title is null
	 */
	public void setTitle(String title) {
		if(title == null) {
			throw new IllegalArgumentException("Title cannot be null.");
		}
		this.title = title;
	}
	
	/**
	 * Gets the title for the schedule
	 * @return the title of the schedule
	 */
	public String getTitle() {
		return title;
	}


	/**
	 * Adds an existing Course to the Schedule
	 * @param course the course to add
	 * @return true if the Course was successfully added
	 * @throws IllegalArgumentException if the Course is already on the schedule or is conflicting
	 * with another Course
	 */
	public boolean addCourseToSchedule(Course course) {
		
		for (Course c : schedule) {
			if (c.getName().equals(course.getName()) && c.getSection().equals(c.getSection())) {
				throw new IllegalArgumentException("You are already enrolled in " + course.getName());
			}
			
			try {
				c.checkConflict(course);
			} catch (ConflictException e) {
				throw new IllegalArgumentException("The course cannot be added due to a conflict.");
			}
		}
		
		schedule.add(course);
		return true;
	}


	/**
	 * Removes the specified Course from the Schedule
	 * @param course the Course to remove from the schedule
	 * @return true if the Course was successfully removed and false if the
	 * specified Course is not on the Schedule
	 */
	public boolean removeCourseFromSchedule(Course course) {
		for (Course c : schedule) {
			if (c.equals(course)) {
				schedule.remove(c);
				return true;
			}
		}
		return false;
		
	}
	
	/**
	 * Sets the schedule array to an empty ArrayList and sets the title to
	 * "My Schedule"
	 */
	public void resetSchedule() {
		this.schedule = new ArrayList<Course>();
		this.title = "My Schedule";
	}
	
	/**
	 * Goes through list of Courses in schedule and adds up all the credits of the courses
	 * and returns the total credits
	 * If schedule is empty, returns 0
	 * @return total credits of all courses in schedule
	 */
	public int getScheduleCredits() {
		int credits = 0;
		for(int i = 0; i < schedule.size(); i++) {
			credits += schedule.get(i).getCredits();
		}
		return credits;
	}
	
	/**
	 * Checks if a course can be added to schedule by making sure that a course with that
	 * name is not already in schedule and it doesn't conflict with any other course in schedule
	 * @param c Course to be checked
	 * @return true if c can be added, false if not or if c is null
	 */
	public boolean canAdd(Course c) {
		if(c == null) {
			return false;
		}
		for(int i = 0; i < schedule.size(); i++) {
			if (c.getName().equals(schedule.get(i).getName())) {
				return false;
			}
			try {
				c.checkConflict(schedule.get(i));
			} catch(ConflictException e) {
				return false;
			}
		}
		return true;
	}

}
