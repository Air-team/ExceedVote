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
	 * @param name is Team name.
	 * @param td is Team description.
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
	

	// this method is never used. do we have to keep this? ถ้าจะดูว่ามันถูกเรียกด้วย เมดตอดอื่นๆมั้ยให้ คลิกขวาที่ชื่อเมดตอดแล้วเลือก open call hierarchy.
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
	 * @param score is the received score.
	 * @param topicName is the topic that the user vote for this team.
	 */
	public void setScore(int score, String topicName) {
		VoteTopicDao dao2 = DaoFactory.getInstance().getVoteTopicDao();
		TeamDao daoTeam = DaoFactory.getInstance().getTeamDao();		
		
		map.put(topicName, score);
		daoTeam.save(this);
		this.topic = dao2.find(topicName);
		this.score = score;

	}

	// this method is never used. do we have to keep this?
	public Integer getId() {
		return id;
	}

	// this method is never used. do we have to keep this?
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Save the TeamDescription to the database.
	 * @param name is the team name.
	 * @param td is the team description of this team.
	 */
	public void saveInfo(String name, TeamDescription td) {
		Team team = new Team(name, td);
		TeamDao dao = DaoFactory.getInstance().getTeamDao();
		dao.save(team);
	}

	/**
	 * Return the specific team from the database.
	 * @param name is the specific team's name.
	 * @return The specific Team object.
	 */
	public static Team getTeam(String name) {
		TeamDao dao = DaoFactory.getInstance().getTeamDao();
		return dao.findSingle(name);
	}

	/**
	 * Return the TeamDescription of this team.
	 * @return TeamDescription object.
	 */
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
	
	/**
	 * Return the Map that keep topic(as key) and score(as value) of each topic.
	 * @return Map that contains topics and values.
	 */
	public Map<String, Integer> getMap() {
		return map;
	}

	// this method is never used. do we have to keep this? this related to method refreshmap that i suggest to delete.
	public void setMap(Map<String, Integer> map) {
		this.map = map;
	}

	/**
	 * Return the list of the of scores and the topics that belongs to the team.
	 * @param name is the Team's name.
	 * @return the list of scores and topics.
	 */
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