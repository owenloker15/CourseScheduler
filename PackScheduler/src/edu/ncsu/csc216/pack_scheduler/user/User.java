package edu.ncsu.csc216.pack_scheduler.user;

/**
 * An abstract class representing a user in the PackScheduler system. Includes all getters and setters for any inherited User classes.
 * @author Daniel Nolting, Kelsey Hanser, Nick Bluzen
 *
 */
public abstract class User {

	/** The student's first name */
	private String firstName;
	/** The student's last name */
	private String lastName;
	/** The student's id */
	private String id;
	/** The student's email */
	private String email;
	/** The student's password (a SHA-256 hash of it) */
	private String password;

	/**
	 * Generates a User using all fields
	 * @param firstName the first name of the user
	 * @param lastName the last name of the user
	 * @param id the id of the user
	 * @param email the email of the user
	 * @param hashPW the hashed password of the user
	 */
	public User(String firstName, String lastName, String id, String email, String hashPW){
		setFirstName(firstName);
		setLastName(lastName);
		setId(id);
		setEmail(email);
		setPassword(hashPW);
	}

	/**
	 * Gets the student's first name
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Gets the student's last name
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Gets the student's id
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Gets the student's email
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets student's ID
	 * @param id the id to set
	 * @throws IllegalArgumentException if id parameter is equal to null or is an empty string
	 */
	private void setId(String id) {
		if(id == null || "".equals(id)) {
			throw new IllegalArgumentException("Invalid id");
		}
		this.id = id;
	}
	
	/**
	 * Sets the student's email, throwing an IllegalArgumentException if the provided email is null, an empty string,
	 * does not contain "@" or ".", or the index of the last "." is earlier than the index of the first "@"
	 * @param email the email to set
	 * @throws IllegalArgumentException if the email is invalid
	 */
	public void setEmail(String email) {
		if (email == null || "".equals(email) || !email.contains("@") || !email.contains(".") || email.lastIndexOf(".") < email.indexOf("@")) {
			throw new IllegalArgumentException("Invalid email");
		}
		this.email = email;
	}

	/**
	 * Gets the student's password (a SHA-256 hash of it)
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the student's password, throwing an IllegalArgumentException if the provided password is null or an empty string.
	 * @param password the password to set
	 * @throws IllegalArgumentException if the password is invalid
	 */
	public void setPassword(String password) {
		if (password == null || "".equals(password)) {
			throw new IllegalArgumentException("Invalid password");
		}
		this.password = password;
	}

	/**
	 * Sets student's first name
	 * @param firstName the firstName to set
	 * @throws IllegalArgumentException if firstname parameter is equal to null or is an empty string
	 */
	public void setFirstName(String firstName) {
		if(firstName == null || "".equals(firstName)) {
			throw new IllegalArgumentException("Invalid first name");
		}
		this.firstName = firstName;
	}

	/**
	 * Sets student's last name
	 * @param lastName the lastName to set
	 * @throws IllegalArgumentException if lastname parameter is equal to null or is an empty string
	 */
	public void setLastName(String lastName) {
		if(lastName == null || "".equals(lastName)) {
			throw new IllegalArgumentException("Invalid last name");
		}
		this.lastName = lastName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}
	
	
	

}