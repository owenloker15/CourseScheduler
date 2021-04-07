package edu.ncsu.csc216.pack_scheduler.course.validator;

/** 
 * Exception thrown by the CourseNameValidator FSM
 * @author Daniel Nolting, Kelsey Hanser, Nick Bleuzen
 *
 */
public class InvalidTransitionException extends Exception {
	
	/** Default serialVersionUID*/
	private static final long serialVersionUID = 1L;

	/** Constructs a new InvalidTransitionException with default message "Invalid FSM Transition" */
	public InvalidTransitionException() {
		this("Invalid FSM Transition.");
	}
	
	/**
	 * Constructs a new InvalidTransitionException with given message
	 * @param message the message of the InvalidTransitionException
	 */
	public InvalidTransitionException(String message) {
		super(message);
	}

}
