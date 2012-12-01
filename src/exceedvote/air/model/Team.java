package exceedvote.air.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import exceedvote.air.persistence.BallotDao;
import exceedvote.air.persistence.DaoFactory;
import exceedvote.air.persistence.TeamDao;
import exceedvote.air.persistence.VoteTopicDao;

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
	private Map<String, Integer> map = new HashMap<String, Integer>();
	
	/**
	 * Team constructor.
	 */
	public Team() {
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
		
		List<VoteTopic> topic = new ArrayList<VoteTopic>();

		VoteTopicDao dao2 = DaoFactory.getInstance().getVoteTopicDao();
		topic = dao2.findAll();
		
		for(int i=0;i<topic.size();i++){
			map.put(topic.get(i).getTitle(), 0);
		}


	}

	/**
	 * Return the score of this team..
	 * @return the scores of this team.
	 */
	public int getScore(String topicName) {
		return  map.get(topicName);
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
		
		map.put(topicName, score);
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

	public void saveInfo(String name, TeamDescription td) {
		Team team = new Team(name, td);
		TeamDao dao = DaoFactory.getInstance().getTeamDao();

		dao.save(team);
	}

	public static Team getTeam(String name) {
		TeamDao dao = DaoFactory.getInstance().getTeamDao();
//		System.out.println(name);
//		System.out.println(dao.findSingle(name));
		return dao.findSingle(name);
	}

	public TeamDescription getTeamDescription() {
		return teamdes;
	}
	

	public int totalScore(){
		int sum = 0;
		for (Map.Entry<String, Integer> entry : map.entrySet()) {
		   sum += entry.getValue();
		}
		return sum;
	}
	
	

}