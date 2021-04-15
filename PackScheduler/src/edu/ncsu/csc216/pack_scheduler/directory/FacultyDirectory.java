/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.directory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.user.User;
import edu.ncsu.csc216.pack_scheduler.util.LinkedList;

/**
 * @author owenloker
 *
 */
public class FacultyDirectory {
	
	private LinkedList<Faculty> facultyDirectory;
	
	private static final String HASH_ALGORITHM = "SHA-256";
	
	public FacultyDirectory() {
		newFacultyDirectory();
	}
	
	public void newFacultyDirectory() {
		this.facultyDirectory = new LinkedList<Faculty>();
	}
	
	public void loadFacultyFromFile(String fileName) {
		
	}
	
	public boolean addFaculty(String firstName, String lastName, String id, String email, String password, String repeatPassword, int maxCourses) {
		String hashPW = "";
		String repeatHashPW = "";
		if (firstName == null || "".equals(firstName)) {
			throw new IllegalArgumentException("Invalid first name");
		}
		else if (lastName == null || "".equals(lastName)) {
			throw new IllegalArgumentException("Invalid last name");
		}
		else if (id == null || "".equals(id)) {
			throw new IllegalArgumentException("Invalid id");
		}
		else if (email == null || "".equals(email)) {
			throw new IllegalArgumentException("Invalid email");
		}
		else if (hashPW == null || "".equals(hashPW)) {
			throw new IllegalArgumentException("Invalid password");
		}
		else if (maxCourses < Faculty.MIN_COURSES || maxCourses > Faculty.MAX_COURSES) {
			throw new IllegalArgumentException("Invalid max courses");
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
		
		Faculty faculty = null;
		for (int i = 0; i < this.facultyDirectory.size(); i++) {
			User s = this.facultyDirectory.get(i);
			if (s.getId().equals(faculty.getId())) {
				throw new IllegalArgumentException("Faculty already in system.");
			}
		}
		return this.facultyDirectory.add(faculty);
	}
	
	public boolean removeFaculty(String s) {
		
	}
	
	public String [][] getFacultyDirectory() {
		
	}
	
	public void saveFactultyDirectory(String fileName) {
		
	}
	
	public Faculty getFacultyById(String id) {
		
	}
}
