package exceedvote.air.model;

public class Register {
	private String userName;
	private String password;
	private String firstName;
	private String lastName;
	private String type;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

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
