package exceedvote.air.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * TeamDescription represents the description of a team.
 * 
 * @author Air Team
 * @version 2012.11.20
 */
@Entity
public class TeamDescription implements Serializable {

	/** the information of a team. */
	private String info;
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	/**
	 * Initialize TeamDescription with information.
	 * 
	 * @param info
	 *            - the information of a team.
	 */
	public TeamDescription(String info) {
		this();
		this.info = info;
	}
	
	public TeamDescription() {
		super();
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