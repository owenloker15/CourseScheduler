/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.directory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import edu.ncsu.csc216.pack_scheduler.io.StudentRecordIO;
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
		try {
			this.facultyDirectory = FacultyRecordIO.readFacultyRecords(fileName);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Unable to read file " + fileName);
		}
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
		else if (!email.contains("@") || !email.contains(".")) {
			throw new IllegalArgumentException("Invalid email");
		}
		else if (email.lastIndexOf(".") < email.indexOf("@")) {
			throw new IllegalArgumentException("Invalid email");
		}
		else if (password == null || "".equals(password)) {
			throw new IllegalArgumentException("Invalid password");
		}
		else if (repeatPassword == null || "".equals(repeatPassword)) {
			throw new IllegalArgumentException("Invalid password");
		}
		else if (maxCourses < Faculty.MIN_COURSES || maxCourses > Faculty.MAX_COURSES) {
			throw new IllegalArgumentException("Invalid max courses");
		}
		else if (!password.equals(repeatPassword)) {
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
		
		Faculty faculty = null;
		for (int i = 0; i < this.facultyDirectory.size(); i++) {
			User s = this.facultyDirectory.get(i);
			if (s.getId().equals(faculty.getId())) {
				throw new IllegalArgumentException("Faculty already in system.");
			}
		}
		return this.facultyDirectory.add(faculty);
	}
	
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
	
	public String [][] getFacultyDirectory() {
		String [][] directory = new String[this.facultyDirectory.size()][3];
		for (int i = 0; i < this.facultyDirectory.size(); i++) {
			User f = this.facultyDirectory.get(i);
			directory[i][0] = f.getFirstName();
			directory[i][1] = f.getLastName();
			directory[i][2] = f.getId();
		}
		return directory;
	}
	
	public void saveFacultyDirectory(String fileName) {
		try {
			FacultyRecordIO.writeFacultyRecords(fileName, facultyDirectory);
		} catch (IOException e) {
			throw new IllegalArgumentException("Unable to write to file " + fileName);
		}
	}
	
	public Faculty getFacultyById(String id) {
		for(int i = 0; i < this.facultyDirectory.size(); i++) {
			if(this.facultyDirectory.get(i).getId().equals(id)) {
				return this.facultyDirectory.get(i);
			}
		}
		return null;
	}
}
