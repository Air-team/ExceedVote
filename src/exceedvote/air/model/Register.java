package exceedvote.air.model;

/**
 * 
 * @author Air Team
 */
public class Register {
	private String userName;
	private String password;
	private String firstName;
	private String lastName;
	private String type;

	/**
	 * 
	 * @return
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * 
	 * @param userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * 
	 * @return
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 
	 * @return
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * 
	 * @param firstName
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * 
	 * @return
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * 
	 * @param lastName
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * 
	 * @param type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 
	 * @return
	 */
	public String getType() {
		return type;
	}

	/**
	 * Add the Voter to the database if the voter hadn't registered. 
	 * @param name is the username that the voter use for login.
	 * @param pass is the password that the voter use for login.
	 * @param type is the type of the voter such as "STUDENT" or "TEACHER".
	 * @return true if the voter is added to the database, false if the voter with the same name has already registered.
	 */
	public boolean addVoter(String name, String pass, String type) {
		Voter voter = new Voter(name, pass, type);
		Login login = new Login(name, pass);
		if (login.hasVoter())
			return false;
		else {
			voter.saveInfo(voter);
			return true;
		}

	}

}
