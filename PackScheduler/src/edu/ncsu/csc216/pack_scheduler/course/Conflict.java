/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course;

/**
 * Interface that helps to check for conflicting behavior in the Activity hierarchy
 * @author Sean McKone
 */
public interface Conflict {
	/**
	 * Checks for possible conflicting behavior in the provided Activity object
	 * @param possibleConflictingActivity Activity object with possible conflicts
	 * @throws ConflictException if there is conflicting behavior
	 */
	void checkConflict(Activity possibleConflictingActivity) throws ConflictException;
	
}
