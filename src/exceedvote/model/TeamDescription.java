package exceedvote.air.model;

/**
 * @author Air Team
 */
public class TeamDescription {
	private String info;

	/**
	 * Constructor for objects of class TeamDescription
	 */
	public TeamDescription(String info) {
		this.info = info;
	}

	/**
	 * Set the information of each team.
	 * @param info String represents the information of team.
	 */
	public void setInfo(String info) {
		this.info = info;
	}

	/**
	 * Get the information of each team.
	 * @return info String represents the information of team.
	 */
	public String getInfo() {
		return this.info;
	}
}