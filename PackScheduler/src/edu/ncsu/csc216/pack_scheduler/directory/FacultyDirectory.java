/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.directory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import edu.ncsu.csc216.pack_scheduler.io.FacultyRecordIO;
import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.user.User;
import edu.ncsu.csc216.pack_scheduler.util.LinkedList;

/**
 * The FacultyDirectory class represents a list of Faculty in the system
 * 
 * @author Daniel Nolting
 * @author Calli Hooks
 * @author Owen Loker
 */
public class FacultyDirectory {

	/** LinkedList of Faculty members */
	private LinkedList<Faculty> facultyDirectory;
	/** Hash Algorithm */
	private static final String HASH_ALGORITHM = "SHA-256";

	/**
	 * Constructs and initializes a new FacultyDirectory
	 */
	public FacultyDirectory() {
		newFacultyDirectory();
	}

	/**
	 * Initializes a new FacultyDirectory
	 */
	public void newFacultyDirectory() {
		this.facultyDirectory = new LinkedList<Faculty>();
	}

	/**
	 * The loadFacultyFromFile method supports the functionality for loading a list
	 * of faculty records from a file
	 * 
	 * @param fileName the name of the file to load
	 * @throws IllegalArgumentException if the readFacultyRecords method throws a
	 *                                  FileNotFoundException
	 */
	public void loadFacultyFromFile(String fileName) {
		try {
			this.facultyDirectory = FacultyRecordIO.readFacultyRecords(fileName);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Unable to read file " + fileName);
		}
	}

	/**
	 * The addFaculty method supports the functionality for adding a Faculty to the
	 * list of faculty. Returns true if the faculty member was successfully added,
	 * else returns false.
	 * 
	 * @param firstName      the first name of the faculty member
	 * @param lastName       the last name of the faculty member
	 * @param id             the id of the faculty member
	 * @param email          the email of the faculty member
	 * @param password       the password of the faculty member
	 * @param repeatPassword the password of the faculty member repeated
	 * @param maxCourses     the maximum number of courses for the faculty member
	 * @return boolean representing whether the faculty member was added
	 * @throws IllegalArgumentException if the firstName, lastName, id, email,
	 *                                  password, repeatedPassword, or maxCourses is
	 *                                  invalid
	 */
	public boolean addFaculty(String firstName, String lastName, String id, String email, String password,
			String repeatPassword, int maxCourses) {
		String hashPW = "";
		String repeatHashPW = "";
		if (firstName == null || "".equals(firstName)) {
			throw new IllegalArgumentException("Invalid first name");
		} else if (lastName == null || "".equals(lastName)) {
			throw new IllegalArgumentException("Invalid last name");
		} else if (id == null || "".equals(id)) {
			throw new IllegalArgumentException("Invalid id");
		} else if (email == null || "".equals(email)) {
			throw new IllegalArgumentException("Invalid email");
		} else if (!email.contains("@") || !email.contains(".")) {
			throw new IllegalArgumentException("Invalid email");
		} else if (email.lastIndexOf(".") < email.indexOf("@")) {
			throw new IllegalArgumentException("Invalid email");
		} else if (password == null || "".equals(password)) {
			throw new IllegalArgumentException("Invalid password");
		} else if (repeatPassword == null || "".equals(repeatPassword)) {
			throw new IllegalArgumentException("Invalid password");
		} else if (maxCourses < Faculty.MIN_COURSES || maxCourses > Faculty.MAX_COURSES) {
			throw new IllegalArgumentException("Invalid max courses");
		} else if (!password.equals(repeatPassword)) {
			throw new IllegalArgumentException("Passwords do not match");
		}
		try {
			MessageDigest digest1 = MessageDigest.getInstance(HASH_ALGORITHM);
			digest1.update(password.getBytes());
			hashPW = new String(digest1.digest());

			MessageDigest digest2 = MessageDigest.getInstance(HASH_ALGORITHM);
			digest2.update(repeatPassword.getBytes());
			repeatHashPW = new String(digest2.digest());
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException("Cannot hash password");
		}

		if (!hashPW.equals(repeatHashPW)) {
			throw new IllegalArgumentException("Passwords do not match");
		}

//		Faculty faculty = null;
//		for (int i = 0; i < this.facultyDirectory.size(); i++) {
//			User f = this.facultyDirectory.get(i);
//			if (f.getId().equals(faculty.getId())) {
//				throw new IllegalArgumentException("Faculty already in system.");
//			}
//		}
		for (int i = 0; i < facultyDirectory.size(); i++) {
			if (facultyDirectory.get(i).getId().equals(id)) {
				throw new IllegalArgumentException("Faculty already in system.");
			}
		}
		return this.facultyDirectory.add(new Faculty(firstName, lastName, id, email, hashPW, maxCourses));
	}

	/**
	 * The removeFaculty method supports the functionality for removing a Faculty
	 * from the list of faculty, given the facultyId. Returns true if the faculty
	 * member was successfully removed, else returns false.
	 * 
	 * @param facultyId the id of the faculty to remove
	 * @return boolean representing whether the faculty member was removed
	 */
	public boolean removeFaculty(String facultyId) {
		for (int i = 0; i < this.facultyDirectory.size(); i++) {
			User s = this.facultyDirectory.get(i);
			if (s.getId().equals(facultyId)) {
				this.facultyDirectory.remove(i);
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns a 2D array, where each row is a Faculty and the columns are the
	 * firstName, lastName, and id.
	 * 
	 * @return 2D String array of Faculty members firstName, lastName, and id
	 */
	public String[][] getFacultyDirectory() {
		String[][] directory = new String[this.facultyDirectory.size()][3];
		for (int i = 0; i < this.facultyDirectory.size(); i++) {
			User f = this.facultyDirectory.get(i);
			directory[i][0] = f.getFirstName();
			directory[i][1] = f.getLastName();
			directory[i][2] = f.getId();
		}
		return directory;
	}

	/**
	 * Saves the facultyDirectory to the given file
	 * 
	 * @param fileName the name of the file to be saved as
	 * @throws IllegalArgumentException if the writeFacultyRecords method throws an
	 *                                  IOException
	 */
	public void saveFacultyDirectory(String fileName) {
		try {
			FacultyRecordIO.writeFacultyRecords(fileName, facultyDirectory);
		} catch (IOException e) {
			throw new IllegalArgumentException("Unable to write to file " + fileName);
		}
	}

	/**
	 * Returns the Faculty member with the given id. If no Faculty member
	 * corresponds to the given id, the method returns null.
	 * 
	 * @param id the id of the Faculty member to be returned
	 * @return the Faculty member with the given id, or null if none is found
	 */
	public Faculty getFacultyById(String id) {
		for (int i = 0; i < this.facultyDirectory.size(); i++) {
			if (this.facultyDirectory.get(i).getId().equals(id)) {
				return this.facultyDirectory.get(i);
			}
		}
		return null;
	}
}
