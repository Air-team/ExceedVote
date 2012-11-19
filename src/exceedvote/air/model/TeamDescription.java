package exceedvote.air.model;

/**
 * TeamDescription represents the description of a team.
 * 
 * @author Air Team
 * @version 2012.11.20
 */
public class TeamDescription {

	/** the information of a team. */
	private String info;

	/**
	 * Initialize TeamDescription with information.
	 * 
	 * @param info
	 *            - the information of a team.
	 */
	public TeamDescription(String info) {
		this.info = info;
	}

	/**
	 * Set the information of a team.
	 * 
	 * @param info
	 *            - String represents the information of a team.
	 */
	public void setInfo(String info) {
		this.info = info;
	}

	/**
	 * Get the information of a team.
	 * 
	 * @return String represents the information of team.
	 */
	public String getInfo() {
		return this.info;
	}
}