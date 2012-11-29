package exceedvote.air.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import exceedvote.air.persistence.DaoFactory;
import exceedvote.air.persistence.TeamDao;
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
	@OneToOne(cascade = CascadeType.PERSIST)
	private TeamDescription teamdes;

	
	
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
		this.teamdes = td;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public void saveInfo(String name,TeamDescription td){
		Team team = new Team(name,td);
		TeamDao dao = DaoFactory.getInstance().getTeamDao();
		dao.save(team);
	}
	
	

}