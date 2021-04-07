/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course;

import static org.junit.Assert.*;

import org.junit.Test;


/**
 * Tests the CheckConflic() method in the Activity class
 * 
 * @author Sean McKone  
 */
public class ActivityTest {

	/**
	 * Tests CheckConflict() with  no-conflict situation
	 */
	@Test
	public void testCheckConflict() {
	    Activity a1 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 10, "MW", 1330, 1445);
	    Activity a2 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 10, "TH", 1330, 1445);
	    try {
	        a1.checkConflict(a2);
	        assertEquals("Incorrect meeting string for this Activity.", "MW 1:30PM-2:45PM", a1.getMeetingString());
	        assertEquals("Incorrect meeting string for possibleConflictingActivity.", "TH 1:30PM-2:45PM", a2.getMeetingString());
	    } catch (ConflictException e) {
	        fail("A ConflictException was thrown when two Activities at the same time on completely distinct days were compared.");
	    }
	}
	
	/**
	 * Tests CheckConfict() with a conflicting time and day situation
	 */
	@Test
	public void testCheckConflictWithConflict() {
	    Activity a1 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 10, "MW", 1330, 1445);
	    Activity a2 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 10, "M", 1330, 1445);
	    try {
	        a1.checkConflict(a2);
	        fail("A ConflictException was NOT thrown when two Activities had a day/time conflict.");
	    } catch (ConflictException e) {
	        assertEquals("Incorrect meeting string for this Activity.", "MW 1:30PM-2:45PM", a1.getMeetingString());
	        assertEquals("Incorrect meeting string for possibleConflictingActivity.", "M 1:30PM-2:45PM", a2.getMeetingString());
	    }
	}

	/**
	 * Tests CheckConfict() commutativity by switching 'this' and parameter 
	 */
	@Test
	public void testCheckConflictCommutativity() {
		// tests a conflicting situation
		// 'this' = a1
		Activity a1 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 10, "MW", 1330, 1445);
	    Activity a2 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 10, "M", 1330, 1445);
	    try {
	    	a1.checkConflict(a2);
	    	fail("A ConflictException was NOT thrown when two Activities had a day/time conflict.");
	    } catch  (ConflictException e) {
	    	assertEquals("Incorrect meeting string for this Activity.", "MW 1:30PM-2:45PM", a1.getMeetingString());
	        assertEquals("Incorrect meeting string for possibleConflictingActivity.", "M 1:30PM-2:45PM", a2.getMeetingString());
	    }
	    
	    // 'this' = a2
	    try {
	    	a2.checkConflict(a1);
	    	fail("A ConflictException was NOT thrown when two Activities had a day/time conflict.");
	    } catch  (ConflictException e) {
	    	assertEquals("Incorrect meeting string for this Activity.", "MW 1:30PM-2:45PM", a1.getMeetingString());
	        assertEquals("Incorrect meeting string for possibleConflictingActivity.", "M 1:30PM-2:45PM", a2.getMeetingString());
	    }
	    
	    // tests a valid situation
	    // 'this' = a3
	    Activity a3 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 10, "MW", 1330, 1445);
	    Activity a4 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 10, "TH", 1330, 1445);
	    try {
	        a3.checkConflict(a4);
	        assertEquals("Incorrect meeting string for this Activity.", "MW 1:30PM-2:45PM", a3.getMeetingString());
	        assertEquals("Incorrect meeting string for possibleConflictingActivity.", "TH 1:30PM-2:45PM", a4.getMeetingString());
	    } catch (ConflictException e) {
	        fail("A ConflictException was thrown when two Activities at the same time on completely distinct days were compared.");
	    }
	    
	    // 'this' = a4
	    try {
	        a4.checkConflict(a3);
	        assertEquals("Incorrect meeting string for this Activity.", "MW 1:30PM-2:45PM", a3.getMeetingString());
	        assertEquals("Incorrect meeting string for possibleConflictingActivity.", "TH 1:30PM-2:45PM", a4.getMeetingString());
	    } catch (ConflictException e) {
	        fail("A ConflictException was thrown when two Activities at the same time on completely distinct days were compared.");
	    }
	}
	
	/**
	 * Tests CheckConfict() with same Activity instance 
	 */
	@Test
	public void testCheckConflictSameInstance() {
		Activity a1 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 10, "MW", 1330, 1445);
		try {
			a1.checkConflict(a1);
			fail("A ConflictException was NOT thrown when two Activities had a day/time conflict.");
		} catch (ConflictException e) {
			assertEquals("Incorrect meeting string for this Activity.", "MW 1:30PM-2:45PM", a1.getMeetingString());
			assertEquals("Incorrect meeting string for possibleConflictingActivity.", "MW 1:30PM-2:45PM", a1.getMeetingString());
		}
	}
	
	/**
	 * Tests CheckConfict() with same-day conflicts
	 */
	@Test
	public void testCheckConflictSameDay() {
		Activity a1 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 10, "MW", 1330, 1445);
	    Activity a2 = new Course("CSC230", "C and Software Tools", "001", 3, "sesmith5", 10, "MW", 1330, 1445);
	    try {
			a1.checkConflict(a2);
			fail("A ConflictException was NOT thrown when two Activities had a day/time conflict.");
		} catch (ConflictException e) {
			assertEquals("Incorrect meeting string for this Activity.", "MW 1:30PM-2:45PM", a1.getMeetingString());
			assertEquals("Incorrect meeting string for possibleConflictingActivity.", "MW 1:30PM-2:45PM", a2.getMeetingString());
		}
	}
	
	/**
	 * Tests CheckConflict() with matching Activity start and end times
	 */
	@Test
	public void testCheckConflictStartEndMatch() {
		Activity a1 = new Course("CSC116", "Introduction to Computing", "001", 3, "sesmith5", 10, "MW", 1215, 1330);
		Activity a2 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 10, "MW", 1330, 1445);
	    Activity a3 = new Course("CSC230", "C and Software Tools", "001", 3, "sesmith5", 10, "MW", 1445, 1600);
	    
	    // Test matching start and end times for a1 and a2
	    try {
			a1.checkConflict(a2);
			fail("A ConflictException was NOT thrown when two Activities had a day/time conflict.");
		} catch (ConflictException e) {
			assertEquals("Incorrect meeting string for this Activity.", "MW 12:15PM-1:30PM", a1.getMeetingString());
			assertEquals("Incorrect meeting string for possibleConflictingActivity.", "MW 1:30PM-2:45PM", a2.getMeetingString());
		}
	    
	    // Test matching end and start times for a2 and a3
	    try {
			a2.checkConflict(a3);
			fail("A ConflictException was NOT thrown when two Activities had a day/time conflict.");
		} catch (ConflictException e) {
			assertEquals("Incorrect meeting string for this Activity.", "MW 1:30PM-2:45PM", a2.getMeetingString());
			assertEquals("Incorrect meeting string for possibleConflictingActivity.", "MW 2:45PM-4:00PM", a3.getMeetingString());
		}
	}
	/**
	 * Tests CheckConflict() with overlapping Activity start and end times
	 */
	@Test
	public void testCheckConflictStartEndOverlap() {
		Activity a1 = new Course("CSC116", "Introduction to Computing", "001", 3, "sesmith5", 10, "MW", 1000, 1300);
		Activity a2 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 10, "MW", 1100, 1200);
	    Activity a3 = new Course("CSC230", "C and Software Tools", "001", 3, "sesmith5", 10, "MW", 1030, 1230);
	    Activity a4 = new Course("CSC316", "Data Structures and Algorithms", "001", 3, "sesmith5", 10, "MW", 1030, 1130);
	    
	    // Test this.startTime < input.startTime && this.endTime > input.endTime
	    try {
			a1.checkConflict(a2);
			fail("A ConflictException was NOT thrown when two Activities had a day/time conflict.");
		} catch (ConflictException e) {
			assertEquals("Incorrect meeting string for this Activity.", "MW 10:00AM-1:00PM", a1.getMeetingString());
			assertEquals("Incorrect meeting string for possibleConflictingActivity.", "MW 11:00AM-12:00PM", a2.getMeetingString());
		}
	    
	    // Test this.startTime > input.startTime && this.endTime < input.endTime
	    try {
			a2.checkConflict(a3);
			fail("A ConflictException was NOT thrown when two Activities had a day/time conflict.");
		} catch (ConflictException e) {
			assertEquals("Incorrect meeting string for this Activity.", "MW 11:00AM-12:00PM", a2.getMeetingString());
			assertEquals("Incorrect meeting string for possibleConflictingActivity.", "MW 10:30AM-12:30PM", a3.getMeetingString());
		}
	    
	    // Test this.startTime > input.startTime && this.endTime > input.endTime
	    try {
			a2.checkConflict(a4);
			fail("A ConflictException was NOT thrown when two Activities had a day/time conflict.");
		} catch (ConflictException e) {
			assertEquals("Incorrect meeting string for this Activity.", "MW 11:00AM-12:00PM", a2.getMeetingString());
			assertEquals("Incorrect meeting string for possibleConflictingActivity.", "MW 10:30AM-11:30AM", a4.getMeetingString());
		}
	    
	    // Test this.startTime < input.startTime && this.endTime < input.endTime
	    try {
			a4.checkConflict(a2);
			fail("A ConflictException was NOT thrown when two Activities had a day/time conflict.");
		} catch (ConflictException e) {
			assertEquals("Incorrect meeting string for this Activity.", "MW 11:00AM-12:00PM", a2.getMeetingString());
			assertEquals("Incorrect meeting string for possibleConflictingActivity.", "MW 10:30AM-11:30AM", a4.getMeetingString());
		}
	    
	}
	
	/**
	 * Tests CheckConflict() with two Arranged courses
	 */
	@Test
	public void testCheckConflictArrangned() {
		Activity a1 = new Course("CSC116", "Introduction to Computing", "001", 3, "sesmith5", 10, "A");
		Activity a2 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 10, "A");
		try {
	        a1.checkConflict(a2);
	        assertEquals("Incorrect meeting string for this Activity.", "Arranged", a1.getMeetingString());
	        assertEquals("Incorrect meeting string for possibleConflictingActivity.", "Arranged", a2.getMeetingString());
	    } catch (ConflictException e) {
	        fail("A ConflictException was thrown when two Arragned Activities were compared.");
	    }
	}
	
}
