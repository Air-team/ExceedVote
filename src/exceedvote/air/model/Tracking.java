package exceedvote.air.model;

import org.apache.log4j.Logger;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Class Tracking is used for track the action of each class.
 * 
 * @author Air Team
 * @version 2012.11.20
 */
@Entity
public class Tracking {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	/** final attribute of Logger */
	final static Logger lg = Logger.getLogger(Ballot.class);

	/**
	 * Constructor for objects of class Tracking.
	 */
	public Tracking() {
		lg.trace("Initialize");
	}

	/**
	 * Add Log for Vote.
	 * 
	 * @param teamName
	 *            - the team's name that has an action.
	 * @param type
	 *            - The VoteTopic
	 */
	public void addLogVote(String teamName, String type) {
		lg.info("vote For " + teamName + " topic: " + type);
	}

	/**
	 * Add Log for ReVote.
	 * 
	 * @param teamName
	 *            - the team's name that has an action.
	 * @param type
	 *            - The VoteTopic
	 */
	public void addLogRevote(String teamName, String type) {
		lg.info("revote For " + teamName + " topic: " + type);
	}
}
