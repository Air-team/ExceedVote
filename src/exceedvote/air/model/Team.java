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
import javax.persistence.Transient;

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
	@Transient
	private VoteTopic topic;
	@OneToOne(cascade = CascadeType.PERSIST)
	private TeamDescription teamdes;
	private Map<String, Integer> map;
	
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
		map = new HashMap<String, Integer>();
		VoteTopicDao dao2 = DaoFactory.getInstance().getVoteTopicDao();
		topic = dao2.findAll();
		
		for(int i=0;i<topic.size();i++){
			map.put(topic.get(i).getTitle(), 0);
		}


	}
	
	public void refreshMap(){
		TeamDao teamDao = DaoFactory.getInstance().getTeamDao();
		List<Team> teamList = teamDao.findAll();
		List<VoteTopic> topic = new ArrayList<VoteTopic>();
		VoteTopicDao dao2 = DaoFactory.getInstance().getVoteTopicDao();
		topic = dao2.findAll();
		
		for(int i=0;i<teamList.size();i++){
			Map<String, Integer> mapp = new HashMap<String, Integer>();
			map = teamList.get(i).getMap();
			for(int j=0;j<topic.size();j++){
				int value = 0;
				if(map.get(topic.get(j).getTitle()) == null)  value = 0;
				else value = map.get(topic.get(j).getTitle());
				mapp.put(topic.get(j).getTitle(),value );
			}
			
			teamList.get(i).setMap(mapp);
			teamDao.save(teamList.get(i));
			
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
	

	
	public Map<String, Integer> getMap() {
		return map;
	}

	public void setMap(Map<String, Integer> map) {
		this.map = map;
	}

	public List<ArrayList> getScoreAlltopic(String name){
		TeamDao teamDao = DaoFactory.getInstance().getTeamDao();
		Team team = teamDao.findSingle(name);
		Map<String, Integer> map = team.getMap();
		List <ArrayList> list = new ArrayList<ArrayList>();
		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			ArrayList<String> info = new ArrayList<String>();
			info.add(entry.getKey());
			info.add(entry.getValue()+"");
			list.add(info);
		}
		return list;
	}
	

	
	

}