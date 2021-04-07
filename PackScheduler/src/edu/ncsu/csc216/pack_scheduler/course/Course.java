/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course;

import edu.ncsu.csc216.pack_scheduler.course.roll.CourseRoll;
import edu.ncsu.csc216.pack_scheduler.course.validator.CourseNameValidator;
import edu.ncsu.csc216.pack_scheduler.course.validator.InvalidTransitionException;

/**
 * Represents a course that a student would enroll in with WolfScheduler
 * 
 * @author Sean McKone
 */
public class Course extends Activity implements Comparable<Course> {
	
	/** Course's name */
	private String name;
	/** Course's section */
	private String section;
	/** Course's credit hours */
	private int credits;
	/** Course's instructor */
	private String instructorId;
	/** The validator for the Course's name */
	private CourseNameValidator validator;
	/** Course section length */
	private static final int SECTION_LENGTH = 3;
	/** Maximum Course credits */
	private static final int MAX_CREDITS = 5;
	/** Minimum Course credits */
	private static final int MIN_CREDITS = 1;
	/** Courseroll of students*/
	private CourseRoll roll;
	/**
	 * Constructs a Course object with values for all fields.
	 * @param name name of Course
	 * @param title title of Course
	 * @param section section of Course
	 * @param credits credit hours for Course
	 * @param instructorId instructor's unity id
	 * @param enrollmentCap enrollmentCap for course
	 * @param meetingDays meeting days for Course as series of chars
	 * @param startTime start time for Course
	 * @param endTime end time for Course
	 */
	public Course(String name, String title, String section, int credits, String instructorId, int enrollmentCap, String meetingDays,
            int startTime, int endTime) {
        super(title, meetingDays, startTime, endTime);
        this.validator = new CourseNameValidator();
        setName(name);
        setSection(section);
        setCredits(credits);
        setInstructorId(instructorId);
        this.roll = new CourseRoll(enrollmentCap);
      
    }
	/**
	 * Creates a Course with the given name, title, section, credits, instructorID, and meetingDays for
	 * courses that are arranged.
	 * @param name name of Course
	 * @param title title of Course
	 * @param section section of Course
	 * @param credits credit hours for Course
	 * @param instructorId instructor's unity id
	 * @param enrollmentCap enrollmentCap for course
	 * @param meetingDays meeting days for Course as series of chars
	 */
	public Course(String name, String title, String section, int credits, String instructorId, int enrollmentCap, String meetingDays) {
		this(name, title, section, credits, instructorId, enrollmentCap, meetingDays, 0, 0);
	}
	
	/**
	 * Returns the Course's name.
	 * @return name of Course
	 */
	public String getName() {
		return name;
	}
	/**
	 * Sets the Course's name.  If the name is null, has a length less than 5 or more than 8,
	 * does not contain a space between letter characters and number characters, has less than 1
	 * or more than 4 letter characters, and not exactly three trailing digit characters, an
	 * IllegalArgumentException is thrown.
	 * @param name the name to set
	 * @throws IllegalArgumentException if the name parameter is invalid
	 */
	private void setName(String name) throws IllegalArgumentException {
		if (name == null) {
			throw new IllegalArgumentException("Name cannot be null.");
		}
		try {
			 if (validator.isValid(name)) {
				 this.name = name;
			 } else {
				 throw new IllegalArgumentException("Invalid name");
			 }
			
		} catch (InvalidTransitionException e) {
			 throw new IllegalArgumentException("Invalid name");
		}
	}
	
		
		
	/**
	 * Returns the Course's section.
	 * @return Course section
	 */
	public String getSection() {
		return section;
	}
	/**
	 * Sets the Course's section.
	 * @param section the section to set
	 * @throws IllegalArgumentException if the section parameter is invalid
	 */
	public void setSection(String section) {
		//Check that section is not null or not equal to SECTION_LENGTH
		if(section == null || section.length() != SECTION_LENGTH) {
			throw new IllegalArgumentException("Invalid section.");
		}
		//Check that all characters in section are digits
		for(int i = 0; i < section.length(); ++i) {
			if(!Character.isDigit(section.charAt(i))) {
				throw new IllegalArgumentException("Section should be three digits.");
			}
		}
		
		this.section = section;
	}
	
	/**
	 * Returns the Course's credits.
	 * @return the credits
	 */
	public int getCredits() {
		return credits;
	}
	
	/**
	 * Sets the Course's credits.
	 * @param credits number of credits of Course
	 * @throws IllegalArgumentException if the credits parameter is invalid
	 */
	public void setCredits(int credits) {
		if(credits < MIN_CREDITS || credits > MAX_CREDITS) {
			throw new IllegalArgumentException("Credits should be between 1 and 5, inclusive.");
		}
		
		this.credits = credits;
	}
	
	/**
	 * Returns the Course's course roll
	 * @return roll for Course
	 */
	public CourseRoll getCourseRoll() {
		return roll;
	}
	
	/**
	 * Returns the Course Instructor's ID.
	 * @return Course instructor's Id
	 */
	public String getInstructorId() {
		return instructorId;
	}
	
	/**
	 * Sets the Course Instructor's ID.
	 * @param instructorId the instructorId to set
	 * @throws IllegalArgumentException if the instructorId parameter is invalid
	 */
	public void setInstructorId(String instructorId) {
		if(instructorId == null || "".equals(instructorId)) {
			throw new IllegalArgumentException("Invalid instructor id.");
		}	
		this.instructorId = instructorId;
	}
	
	/**
	 * Returns a comma separated value String of all Course fields.
	 * @return String representation of Course
	 */
	@Override
	public String toString() {
	    if ("A".equals(getMeetingDays())) {
	        return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + "," + roll.getEnrollmentCap() + ","
	        		+ getMeetingDays();
	    }
	    return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + "," + roll.getEnrollmentCap() + "," +
	    		getMeetingDays() + "," + getStartTime() + "," + getEndTime(); 
	}
	/**
	 * Overridden method that creates hashcode for Course objects
	 * @return hashcode
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + credits;
		result = prime * result + ((instructorId == null) ? 0 : instructorId.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((section == null) ? 0 : section.hashCode());
		return result;
	}
	/**
	 * Overridden method that checks equality of Course objects
	 * @return true if Course objects are equal
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		if (credits != other.credits)
			return false;
		if (instructorId == null) {
			if (other.instructorId != null)
				return false;
		} else if (!instructorId.equals(other.instructorId))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (section == null) {
			if (other.section != null)
				return false;
		} else if (!section.equals(other.section))
			return false;
		return true;
	}
	
	/**
	 * Overridden method that returns a short string array describing a Course
	 * @return short String[] describing a Course
	 */
	@Override
	public String[] getShortDisplayArray() {
		String[] shortDisplayArray = new String[]{getName(), getSection(), getTitle(), getMeetingString(), Integer.toString(roll.getOpenSeats())};
		return shortDisplayArray;
		
	}
	
	/**
	 * Overridden method that returns a long string array describing a Course
	 * @return long String[] describing a Course
	 */
	@Override
	public String[] getLongDisplayArray() {
		String[] longDisplayArray = new String[]{getName(), getSection(), getTitle(), 
				String.valueOf(getCredits()), String.valueOf(getInstructorId()), getMeetingString(), ""};
		return longDisplayArray;
	}
	
	/**
	 * Overridden method that sets the Course meeting days and start/end times
	 * @param meetingDays days when the Course meets
	 * @param startTime time when the Course starts
	 * @param endTime time when the Course ends
	 * @throws IllegalArgumentException if there are duplicate days, days are not MTWHFA, or input time is invalid
	 */
	@Override
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime)  throws IllegalArgumentException{
		if(meetingDays == null || "".equals(meetingDays)){
			throw new IllegalArgumentException("Invalid meeting days.");
		}
		else if("A".equals(meetingDays)) {
			super.setMeetingDaysAndTime(meetingDays, 0, 0);
		}
		else {
			int mCount = 0;
			int tCount = 0;
			int wCount = 0;
			int hCount = 0;
			int fCount = 0;
			
			for(int i = 0; i < meetingDays.length(); ++i) {
				if(meetingDays.charAt(i) == 'M') {
					++mCount;
				}
				else if(meetingDays.charAt(i) == 'T') {
					++tCount;
				}
				else if(meetingDays.charAt(i) == 'W') {
					++wCount;
				}
				else if(meetingDays.charAt(i) == 'H') {
					++hCount;
				}
				else if(meetingDays.charAt(i) == 'F') {
					++fCount;
				}
				else {
					throw new IllegalArgumentException("Invalid meeting days.");
				}
			}
			if(mCount > 1 || tCount > 1 || wCount > 1 || hCount > 1 || fCount > 1) {
				throw new IllegalArgumentException("Invalid meeting days.");
			}
			super.setMeetingDaysAndTime(meetingDays, startTime, endTime);
		
		}
	

	}
	/**
	 * Overridden method to check if input Activity is a duplicate of specified Course
	 * @return true if duplicate
	 */
	@Override
	public boolean isDuplicate(Activity activity) {
		return activity instanceof Course && ((Course)activity).getName().equals(getName()) && ((Course)activity).getSection().equals(getSection());
	}
	@Override
	public int compareTo(Course o) {
		
		if (getName().compareTo(o.getName()) < 0) {
			return -1;
		} else if (getName().compareTo(o.getName()) > 0) {
			return 1;
		
		}
		
		if (getSection().compareTo(o.getSection()) < 0) {
			return -1;
		} else if (getSection().compareTo(o.getSection()) > 0) {
			return 1;
		}
		return 0;
	}
	
	
	
}
