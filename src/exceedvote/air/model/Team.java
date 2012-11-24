package exceedvote.air.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import exceedvote.air.persistence.DaoFactory;
import exceedvote.air.persistence.VoteTopicDao;
import exceedvote.air.persistence.jpa.TeamDaoJpa;

/**
 * Team represents the competitor in the Exceed camp.
 * 
 * @author Air Team
 * @version 2012.11.20
 */
@Entity
public class Team implements Serializable {
	// instance variables - replace the example below with your own
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String name;
	private int score;
	private VoteTopic topic;

	/**
	 * Team constructor.
	 */
	private Team() {
		super();
	}

	/**
	 * Initialize Team that have name and description.
	 * 
	 * @param name
	 *            - Team name.
	 * @param td
	 *            - Team description.
	 */
	public Team(String name, TeamDescription td) {
		this();
		this.name = name;
	}

	/**
	 * Return the score of this team.
	 * 
	 * @param topicName
	 *            - name of the vote topic.
	 * @return the scores of this team.
	 */
	public int getScore(String topicName) {
		return this.score;
	}

	/**
	 * Return the name of this team.
	 * 
	 * @return String represents name of this team.
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Set the score to this team by topic.
	 * 
	 * @param score
	 *            - score received.
	 * @param topicName
	 *            - topic that the user vote to this team.
	 */
	public void setScore(int score, String topicName) {
		VoteTopicDao dao2 = DaoFactory.getInstance().getVoteTopicDao();
		TeamDao daoTeam = DaoFactory.getInstance().getTeamDao();
		daoTeam.save(this);
		this.topic = dao2.find(topicName);
		this.score = score;
	
		
	}

}