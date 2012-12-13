package exceedvote.air.model;

import exceedvote.air.persistence.CommitteeDao;
import exceedvote.air.persistence.DaoFactory;
import exceedvote.air.persistence.VoterDao;

public class Login {

	private Voter voter;
	private Committee committee;
	private String username;
	private String password;

	/**
	 * Initialize Login with username and password.
	 * @param username is a String that the user use for login.
	 * @param password is a String that the user use as login password.
	 */
	public Login(String username, String password) {
		this.username = username;
		this.password = password;
	}

	/**
	 * To check whether the person who login is the administer or not.
	 * @return true if the person is administer.
	 */
	public boolean isAdmin() {
		if (username.equals("root") && password.equals("root"))
			return true;
		return false;
	}

	/**
	 * Return the Voter that logging in.
	 * @return Object Voter.
	 */
	public Voter getVoter() {
		return voter;
	}

	/**
	 * To check if the voter has already registered or not.
	 * @return true if the voter has already registered.
	 */
	public boolean hasVoter() {
		VoterDao dao = DaoFactory.getInstance().getVoterDao();
		voter = dao.findSingle(username, password);
		if (voter == null)
			return false;
		return true;
	}

	/**
	 * Return the Committee that logging in.
	 * @return Object Committee.
	 */
	public Committee getCommittee() {
		return committee;
	}

	/**
	 * To check if the committee has already promoted or not.
	 * @return true if the committee has already promoted.
	 */
	public boolean hasCommittee() {
		CommitteeDao dao = DaoFactory.getInstance().getCommitteeDao();
		committee = dao.findSingle(username, password);
		// System.out.println(committee.getName());
		if (committee == null)
			return false;
		return true;
	}
}
