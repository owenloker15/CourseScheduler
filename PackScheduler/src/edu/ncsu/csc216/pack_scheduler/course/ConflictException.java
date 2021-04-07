/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course;

/**
 * An exception thrown if there is conflicting behavior in the Activity hierarchy
 * @author Sean McKone
 */
public class ConflictException extends Exception {

	/**
	 * Parameterless constructor that tells ConflictException to throw the default message
	 */
	public ConflictException() {
		super("Schedule conflict.");	
	}

	/**
	 * Parameterized constructor that tells ConflictException to throw a specified message
	 * @param message message to throw when exception occurs
	 */
	public ConflictException(String message) {
		super(message);
	}

	/** ID used for serialization. */
	private static final long serialVersionUID = 1L;

}
