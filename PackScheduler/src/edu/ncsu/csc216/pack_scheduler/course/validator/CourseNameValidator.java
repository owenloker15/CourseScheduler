package edu.ncsu.csc216.pack_scheduler.course.validator;

/**
 * Class which checks the validity of a Course name with the isValid() method 
 * @author Daniel Nolting, Nick Bleuzen, Kelsey Hanser
 *
 */
public class CourseNameValidator {

	/** Boolean flag for whether or not the FSM ends in a valid state */
	private boolean validEndState;
	/** Number of letters in the title the validator is checking */
	private int letterCount;
	/** Number of digits in the title the validator is checking */
	private int digitCount;
	/** Current state of the FSM */
	private State currentState;
	/** Private instance of LetterState */
	private final State stateLetter = new LetterState();
	/** Private instance of SuffixState */
	private final State stateSuffix = new SuffixState();
	/** Private instance of InitialState */
	private final State stateInitial = new InitialState();
	/** Private instance of NumberState */
	private final State stateNumber = new NumberState();
	
	/** Constructs a new CourseNameValidator resetting all fields */
	public CourseNameValidator() {
		currentState = stateInitial;
		letterCount = 0;
		digitCount = 0;
		validEndState = false;
	}
	
	/** 
	 * Checks a title of a Course to see if it is valid (Contains only letters and digits, starts with 3-4
	 * letters followed by 3 digits and an optional one letter suffix)
	 * @param courseName the name of the Course to check
	 * @return true if the name is valid
	 * @throws InvalidTransitionException if the name is invalid
	 */
	public boolean isValid(String courseName) throws InvalidTransitionException{
		letterCount = 0;
		digitCount = 0;
		validEndState = false;
		currentState = stateInitial;
		
		for(int i = 0; i < courseName.length(); i++) {
			if(Character.isLetter(courseName.charAt(i))) {
				currentState.onLetter();
			} else if(Character.isDigit(courseName.charAt(i))){
				currentState.onDigit();
			} else {
				currentState.onOther();
			}
		}
		return validEndState;
	}
	
	
	private abstract class State {
		public abstract void onLetter() throws InvalidTransitionException;
		public abstract void onDigit() throws InvalidTransitionException;
		public void onOther() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name can only contain letters and digits.");
		}
		
	}
	
	private class LetterState extends State {
		/** Max number of letters a Course name can have before the digits */
		private static final int MAX_PREFIX_LETTERS = 4;
		private LetterState() {
			
		}
		public void onLetter() throws InvalidTransitionException{
			letterCount++;
			if (letterCount > MAX_PREFIX_LETTERS) {
				throw new InvalidTransitionException("Course name cannot start with more than 4 letters.");
			}
		}
		public void onDigit() throws InvalidTransitionException{
			currentState = stateNumber;
			digitCount++;
		}
	}
	
	private class SuffixState extends State {
		private SuffixState() {
			
		}
		public void onLetter() throws InvalidTransitionException{
			letterCount++; 
			if(letterCount > 1) {
				throw new InvalidTransitionException("Course name can only have a 1 letter suffix.");
			}
			
		}
		public void onDigit() throws InvalidTransitionException{
			throw new InvalidTransitionException("Course name cannot contain digits after the suffix.");
		}
	}
	
	private class InitialState extends State {
		private InitialState() {
			
		}
		public void onLetter() throws InvalidTransitionException{
			letterCount++;
			currentState = stateLetter;
		}
		public void onDigit() throws InvalidTransitionException{
			throw new InvalidTransitionException("Course name must start with a letter.");
		}
	}
	
	private class NumberState extends State {
		/** Number of digits a Course name can contain */
		private static final int COURSE_NUMBER_LENGTH = 3;
		private NumberState() {
			
		}
		public void onLetter() throws InvalidTransitionException {
			letterCount = 1;
			if (digitCount == COURSE_NUMBER_LENGTH) {
				currentState = stateSuffix;
			}
			else {
				throw new InvalidTransitionException("Course name must have 3 digits.");
			}
		}
		public void onDigit() throws InvalidTransitionException {
			digitCount++;
			if(digitCount == COURSE_NUMBER_LENGTH) {
				validEndState = true;
			}
			if (digitCount > COURSE_NUMBER_LENGTH) {
				throw new InvalidTransitionException("Course name can only have 3 digits.");
			}
		}
	}
}
