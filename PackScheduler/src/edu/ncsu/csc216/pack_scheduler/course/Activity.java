package edu.ncsu.csc216.pack_scheduler.course;

/**
 * Abstract class that describes a student activity
 * 
 * @author Sean McKone
 */
public abstract class Activity implements Conflict {

	/**
	 * Checks for schedule conflicts between objects in the Activity hierarchy
	 * @throws ConflictException if there is a schedule conflict
	 */
	@Override
	public void checkConflict(Activity possibleConflictingActivity) throws ConflictException {
		if(isDayConflict(possibleConflictingActivity) && isTimeConflict(possibleConflictingActivity)) {
			throw new ConflictException();
		}
		
	}

	/** Activity's title */
	private String title;
	/** Activity's meeting days */
	private String meetingDays;
	/** Activity's starting time */
	private int startTime;
	/** Activity's ending time */
	private int endTime;
	/** Activity hour maximum */
	private static final int UPPER_HOUR = 23;
	/** Activity minute maximum */
	private static final int UPPER_MINUTE = 59;
	
	/**
	 * Allows construction of class extending abstract Activity class
	 * @param title title of activity
	 * @param meetingDays meeting days of activity
	 * @param startTime start time of activity
	 * @param endTime end time of activity
	 */
	public Activity(String title, String meetingDays, int startTime, int endTime) {
        super();
        setTitle(title);
        setMeetingDaysAndTime(meetingDays, startTime, endTime);
    }

	/**
	 * Returns the Activity's title.
	 * @return title of Activity object
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the Activity's title.
	 * @param title title to set
	 * @throws IllegalArgumentException if the title parameter is invalid
	 */
	public void setTitle(String title) {
		//Check that title is not null or empty
		if(title == null || "".equals(title)) {
			throw new IllegalArgumentException("Invalid title.");
		}
		
		this.title = title;
	}

	/**
	 * Returns the Activity's meeting days.
	 * @return Activity meeting days
	 */
	public String getMeetingDays() {
		return meetingDays;
	}

	/**
	 * Returns the Activity's starting time.
	 * @return Activity start time
	 */
	public int getStartTime() {
		return startTime;
	}

	/**
	 * Returns the Activity's end time.
	 * @return Activity end time
	 */
	public int getEndTime() {
		return endTime;
	}

	/**
	 * Sets the Activity meeting days and start/end times
	 * @param meetingDays days when the Activity meets
	 * @param startTime time when the Activity starts
	 * @param endTime time when the Activity ends
	 * @throws IllegalArgumentException if input time is invalid
	 */
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) throws IllegalArgumentException {
		
			
		int startHour = startTime / 100;
		int startMinute = startTime % 100;
		int endHour = endTime / 100;
		int endMinute = endTime % 100;
		
		if(startHour < 0 || startHour > UPPER_HOUR) {
			throw new IllegalArgumentException("Invalid start time.");
		}
		if(startMinute < 0 || startMinute > UPPER_MINUTE) {
			throw new IllegalArgumentException("Invalid start time.");
		}
		if(endHour < 0 || endHour > UPPER_HOUR) {
			throw new IllegalArgumentException("Invalid end time.");
		}
		if(endMinute < 0 || endMinute > UPPER_MINUTE) {
			throw new IllegalArgumentException("Invalid end time.");
		}
		if(startTime > endTime) {
			throw new IllegalArgumentException("End time cannot be before start time.");
		}
		
		this.meetingDays = meetingDays;
		this.startTime = startTime;
		this.endTime = endTime;
		
	}

	/**
	 * Returns a string that represents a Activity schedule
	 * @return meetingString Activity schedule file
	 */
	public String getMeetingString() {
		String meetingString = "";
		
		if(!"A".equals(getMeetingDays())) {
			meetingString += getMeetingDays();
			meetingString += " ";
			meetingString += getTimeString(getStartTime());
			meetingString += "-";
			meetingString += getTimeString(getEndTime());
		}
		else {
			meetingString += "Arranged";
		}
		
		return meetingString;	
	}

	/**
	 * Helper method - converts military time into into standard time
	 * @param time military time input 
	 * @return standard time output
	 */
	private String getTimeString(int time) {
		int hours = time / 100;
		int minutes = time % 100;
		String returnTime = "";
		boolean pm = false;
		
		if(hours >= 12) {
			hours -= 12;
			pm = true;
		}
		if(hours == 0) {
			hours = 12;
		}
		returnTime += Integer.toString(hours);
		returnTime += ":";
		
		if(minutes < 10) {
			returnTime += "0";
		}
		
		returnTime += Integer.toString(minutes);
		if(pm) {
			returnTime += "PM";
		}
		else {
			returnTime += "AM";
		}
		
		return returnTime;
	}
	
	/**
	 * Helper method - checks for meetingday overlaps between Activity objects
	 * @param activity Activity object to compare 
	 * @return true if there is an overlap
	 */
	private boolean isDayConflict(Activity activity) {
		
		for (int i = 0; i < activity.getMeetingDays().length(); i++){
			if(this.getMeetingDays().indexOf(activity.getMeetingDays().charAt(i)) != -1) {
				return true;
			}
		}
		for (int i = 0; i < this.getMeetingDays().length(); i++){
			if(activity.getMeetingDays().indexOf(this.getMeetingDays().charAt(i)) != -1) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Helper method - checks for starttime and endtime overlaps between Activity objects
	 * @param activity Activity object to compare 
	 * @return true if there is an overlap
	 */
	private boolean isTimeConflict(Activity activity) {
		if(!this.getMeetingDays().equals("A") && !activity.getMeetingDays().equals("A")) {
			
			if(this.getStartTime() == activity.getStartTime()) {
				return true;
			}
			if(this.getEndTime() == activity.getEndTime()) {
				return true;
			}
			if(this.getStartTime() == activity.getEndTime()) {
				return true;
			}
			if(this.getEndTime() == activity.getStartTime()) {
				return true;
			}
			if(this.getStartTime() > activity.getStartTime() && this.getStartTime() < activity.getEndTime()) {
				return true;
			}
			if(this.getEndTime() > activity.getStartTime() && this.getEndTime() < activity.getEndTime()) {
				return true;
			}
			if(activity.getStartTime() > this.getStartTime() && activity.getStartTime() < this.getEndTime()) {
				return true;
			}
			if(activity.getEndTime() > this.getStartTime() && activity.getEndTime() < this.getEndTime()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Overridden hashCode() method for use with abstract Activity class
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + endTime;
		result = prime * result + ((meetingDays == null) ? 0 : meetingDays.hashCode());
		result = prime * result + startTime;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}
	
	/**
	 * Overridden equals() method that checks Activity instance equality
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Activity other = (Activity) obj;
		if (endTime != other.endTime)
			return false;
		if (meetingDays == null) {
			if (other.meetingDays != null)
				return false;
		} else if (!meetingDays.equals(other.meetingDays))
			return false;
		if (startTime != other.startTime)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	
	/**
	 * Abstract method that returns a short string array describing an Activity
	 * @return short String[] describing an Activity
	 */
	public abstract String[] getShortDisplayArray();
	
	/**
	 * Abstract method that returns a long string array describing an Activity
	 * @return long String[] describing an Activity
	 */
	public abstract String[] getLongDisplayArray();
	
	/**
	 * Abstract method that checks Activity object equality
	 * @param activity activity object
	 * @return true if Activity object is duplicate of specified object
	 */
	public abstract boolean isDuplicate(Activity activity);

}