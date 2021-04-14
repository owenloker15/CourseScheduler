/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.user;

/**
 * @author owenloker
 *
 */
public class Faculty extends User {
	
	public static final int MIN_COURSES = 1;
	
	public static final int MAX_COURSES = 3;
	
	private int maxCourses;
	
	public Faculty(String firstName, String lastName, String id, String email, String hashPW, int maxCourses) {
		super(firstName, lastName, id, email, hashPW);
		setMaxCourses(maxCourses);
	}
	
	/**
	 * @return the maxCourses
	 */
	public int getMaxCourses() {
		return maxCourses;
	}
	
	/**
	 * @param maxCourses the maxCourses to set
	 */
	public void setMaxCourses(int maxCourses) {
		if (maxCourses < MIN_COURSES || maxCourses > MAX_COURSES) {
			throw new IllegalArgumentException();
		}
		this.maxCourses = maxCourses;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + maxCourses;
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
		Faculty other = (Faculty) obj;
		if (maxCourses != other.maxCourses)
			return false;
		return true;
	}

	@Override
	public String toString() {
		String facultyInfo = getFirstName() + "," + getLastName() + "," + getId() + "," + getEmail() + "," + getPassword() + "," + maxCourses;
		return facultyInfo;
	}
	
}
