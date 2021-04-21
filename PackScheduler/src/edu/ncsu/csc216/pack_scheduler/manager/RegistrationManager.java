package edu.ncsu.csc216.pack_scheduler.manager;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;

import edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.course.roll.CourseRoll;
import edu.ncsu.csc216.pack_scheduler.directory.FacultyDirectory;
import edu.ncsu.csc216.pack_scheduler.directory.StudentDirectory;
import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.user.User;
import edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule;

/**
 * RegistrationManager handles a StudentDirectory and a CourseCatalog and allows
 * a Registrar and a Student to be logged in in order to manage schedules.
 * 
 * @author Daniel Nolting, Nick Bluzen, Kelsey Hanser
 *
 */
public class RegistrationManager {

	/** A singleton instance of RegistrationManager */
	private static RegistrationManager instance;
	/** A course catalog to be used for the manager */
	private CourseCatalog courseCatalog;
	/** A directory of student objects managed by the manager */
	private StudentDirectory studentDirectory;
	/** A directory of faculty objects managed by the manager */
	private FacultyDirectory facultyDirectory;
	/** The registrar currently logged in to the manager */
	private User registrar;
	/** The current logged in user */
	private User currentUser;
	/** Hashing algorithm */
	private static final String HASH_ALGORITHM = "SHA-256";
	/** Location for properties file */
	private static final String PROP_FILE = "registrar.properties";

	/**
	 * Constructs the RegistrationManager object. Creates a new courseCatalog,
	 * studentDirectory, and facultyDirectory
	 */
	private RegistrationManager() {
		createRegistrar();
		courseCatalog = new CourseCatalog();
		studentDirectory = new StudentDirectory();
		facultyDirectory = new FacultyDirectory();
	}

	/**
	 * Creates a Registrar given the properties file
	 */
	private void createRegistrar() {
		Properties prop = new Properties();

		try (InputStream input = new FileInputStream(PROP_FILE)) {
			prop.load(input);

			String hashPW = hashPW(prop.getProperty("pw"));

			registrar = new Registrar(prop.getProperty("first"), prop.getProperty("last"), prop.getProperty("id"),
					prop.getProperty("email"), hashPW);
		} catch (IOException e) {
			throw new IllegalArgumentException("Cannot create registrar.");
		}
	}

	/**
	 * Hashes the password of the user
	 * 
	 * @param pw the password to hash
	 * @return String the hashed password
	 */
	private String hashPW(String pw) {
		try {
			MessageDigest digest1 = MessageDigest.getInstance(HASH_ALGORITHM);
			digest1.update(pw.getBytes());
			return new String(digest1.digest());
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException("Cannot hash password");
		}
	}

	/**
	 * Returns the current singleton instance of RegistrationManager. Creates a new
	 * instance if instance is null
	 * 
	 * @return instance
	 */
	public static RegistrationManager getInstance() {
		if (instance == null) {
			instance = new RegistrationManager();
		}
		return instance;
	}

	/**
	 * Getter for courseCatalog
	 * 
	 * @return courseCatalog
	 */
	public CourseCatalog getCourseCatalog() {
		return courseCatalog;
	}

	/**
	 * Getter for studentDirectoy
	 * 
	 * @return studentDirectory
	 */
	public StudentDirectory getStudentDirectory() {
		return studentDirectory;
	}

	/**
	 * Getter for facultyDirectory
	 * 
	 * @return facultyDirectory
	 */
	public FacultyDirectory getFacultyDirectory() {
		return facultyDirectory;
	}

	/**
	 * Attempts to log in user with the given id and password
	 * 
	 * @param id       the id of the user
	 * @param password the password of the user
	 * @return true if the user was logged in successfully
	 * @throws IllegalArgumentException if id or password is null or if a user with
	 *                                  the given id does not exist
	 */
	public boolean login(String id, String password) {

		if (id == null || password == null) { throw new IllegalArgumentException("Invalid ID or password."); }

		if (currentUser != null) { return false; }

		if (registrar.getId().equals(id)) {
			MessageDigest digest;

			try {

				digest = MessageDigest.getInstance(HASH_ALGORITHM);
				digest.update(password.getBytes());
				String localHashPW = new String(digest.digest());
				if (registrar.getPassword().equals(localHashPW)) {
					currentUser = registrar;
					return true;
				} else {
					return false;
				}
			}
			catch (NoSuchAlgorithmException e) { throw new IllegalArgumentException(); } 
			catch (NullPointerException e) { throw new IllegalArgumentException("User doesn't exist."); }

		}

		Student s = studentDirectory.getStudentById(id);
		Faculty f = facultyDirectory.getFacultyById(id);
		
		if (f == null && s == null) {
			throw new IllegalArgumentException("User doesn't exist.");
		} else if (f == null) {
			try {
				MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
				digest = MessageDigest.getInstance(HASH_ALGORITHM);
				digest.update(password.getBytes());
				String localHashPW = new String(digest.digest());
				if (s.getPassword().equals(localHashPW)) {
					currentUser = s;
					return true;
				}
			} catch (NoSuchAlgorithmException e) { throw new IllegalArgumentException();
			} catch (NullPointerException e) { throw new IllegalArgumentException("User doesn't exist."); }
		} else if (s == null) {
			try {
				MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
				digest = MessageDigest.getInstance(HASH_ALGORITHM);
				digest.update(password.getBytes());
				String localHashPW = new String(digest.digest());
				if (f.getPassword().equals(localHashPW)) {
					currentUser = f;
					return true;
				}
			} catch (NoSuchAlgorithmException e) { throw new IllegalArgumentException();
			} catch (NullPointerException e) { throw new IllegalArgumentException("User doesn't exist."); }
		}
		return false;
	}

	/**
	 * Sets the currentUser to null
	 */
	public void logout() {
		currentUser = null;
	}

	/**
	 * Returns the current user
	 * 
	 * @return currentUser
	 */
	public User getCurrentUser() {
		return currentUser;
	}

	/**
	 * Resets the courseCatalog and studentDirectory
	 */
	public void clearData() {
		courseCatalog.newCourseCatalog();
		studentDirectory.newStudentDirectory();
		facultyDirectory.newFacultyDirectory();
	}

	private static class Registrar extends User {
		/**
		 * Create a registrar user.
		 * 
		 * @param firstName the first name of the registrar
		 * @param lastName  the last name of the registrar
		 * @param id        the id of the registrar
		 * @param email     the email of the registrar
		 * @param hashPW    the hashed password of the registrar
		 */
		public Registrar(String firstName, String lastName, String id, String email, String hashPW) {
			super(firstName, lastName, id, email, hashPW);
		}
	}

	/**
	 * Returns true if the logged in student can enroll in the given course.
	 * 
	 * @param c Course to enroll in
	 * @return true if enrolled
	 */
	public boolean enrollStudentInCourse(Course c) {
		if (!(currentUser instanceof Student)) {
			throw new IllegalArgumentException("Illegal Action");
		}
		try {
			Student s = (Student) currentUser;
			Schedule schedule = s.getSchedule();
			CourseRoll roll = c.getCourseRoll();

			if (s.canAdd(c) && roll.canEnroll(s)) {
				schedule.addCourseToSchedule(c);
				roll.enroll(s);
				return true;
			}

		} catch (IllegalArgumentException e) {
			return false;
		}
		return false;
	}

	/**
	 * Returns true if the logged in student can drop the given course.
	 * 
	 * @param c Course to drop
	 * @return true if dropped
	 */
	public boolean dropStudentFromCourse(Course c) {
		if (!(currentUser instanceof Student)) {
			throw new IllegalArgumentException("Illegal Action");
		}
		try {
			Student s = (Student) currentUser;
			c.getCourseRoll().drop(s);
			return s.getSchedule().removeCourseFromSchedule(c);
		} catch (IllegalArgumentException e) {
			return false;
		}
	}

	/**
	 * Resets the logged in student's schedule by dropping them from every course
	 * and then resetting the schedule.
	 */
	public void resetSchedule() {
		if (!(currentUser instanceof Student)) {
			throw new IllegalArgumentException("Illegal Action");
		}
		try {
			Student s = (Student) currentUser;
			Schedule schedule = s.getSchedule();
			String[][] scheduleArray = schedule.getScheduledCourses();
			for (int i = 0; i < scheduleArray.length; i++) {
				Course c = courseCatalog.getCourseFromCatalog(scheduleArray[i][0], scheduleArray[i][1]);
				c.getCourseRoll().drop(s);
			}
			schedule.resetSchedule();
		} catch (IllegalArgumentException e) {
			// do nothing
		}
	}
}