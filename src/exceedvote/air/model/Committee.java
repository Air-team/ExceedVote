package exceedvote.air.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import exceedvote.air.persistence.BallotDao;
import exceedvote.air.persistence.CommitteeDao;
import exceedvote.air.persistence.DaoFactory;
import exceedvote.air.persistence.TeamDao;
import exceedvote.air.persistence.VoteTopicDao;
import exceedvote.air.persistence.VoterDao;

/**
 * Entity implementation class for Entity: Committee
 *
 */
@Entity
public class Committee implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	
	
	private String username="";
	private String password="";
	private String type = "";
	private int amountOfBallot = 0;
 	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
		
	}

	private String teamName;
	private String topicName;
	@Temporal( TemporalType.DATE ) 
	private Date date;
	

	public Committee() {
		super();
	}
	
	
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return username;
	}

	public void setName(String name) {
		this.username = name;
	}
	
	public void setballotLeft(int value) {
		this.amountOfBallot = value;
		CommitteeDao dao = DaoFactory.getInstance().getCommitteeDao();
		dao.saveCom(this);
	}

	public int getballotLeft() {
		return amountOfBallot;
	}
	
	public void saveInfo(Committee committee){
		CommitteeDao dao = DaoFactory.getInstance().getCommitteeDao();
		dao.saveCom(committee);
	}
	
	public List<ArrayList> history(){
		BallotDao dao = DaoFactory.getInstance().getBallotDao();
		return dao.historyCom(this);
	}
	
	public List<Team> getTeam() {
		TeamDao teamDao = DaoFactory.getInstance().getTeamDao();
		return teamDao.findAll();
	}

	public boolean setTeam(String teamName) {
		this.teamName = teamName;
		TeamDescription durianDes = new TeamDescription("name: Durian");
		Team team = new Team(teamName, durianDes);
		TeamDao teamDao = DaoFactory.getInstance().getTeamDao();
		if (teamDao.findSingle(teamName) != null)
			return false;
		else {
			teamDao.save(team);
			return true;
		}
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getTopicName() {
		return topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}

	public List<VoteTopic> getTopic() {
		VoteTopicDao dao = DaoFactory.getInstance().getVoteTopicDao();
		return 	dao.findAll();
	}

	public boolean setTopic(String topicName) {
		this.topicName = topicName;
		VoteTopic voteTopic = new VoteTopic(topicName);
		VoteTopicDao dao = DaoFactory.getInstance().getVoteTopicDao();
		if (dao.findTilte(topicName) != false)
			return false;
		else {
			dao.save(voteTopic);
			return true;
		}
	}
	
	public boolean deleteTeam(String teamName){
		TeamDao teamDao = DaoFactory.getInstance().getTeamDao();
		if (teamDao.findSingle(teamName).getName().equals(teamName)){
			teamDao.remove(teamDao.findSingle(teamName));
			return true;
		}	
		else {
			
			return false;
		}
	}
	
	public boolean deleteTopic(String topicName){
		VoteTopicDao topicDao = DaoFactory.getInstance().getVoteTopicDao();
		if (topicDao.find(topicName).getTitle().equals(topicName)){
			topicDao.remove(topicDao.find(topicName));
			return true;
		}	
		else {
			
			return false;
		}
	}
	
	public void setTime(String day, String month, String year, String hours,
			String mins, String seconds) {
		int d = 0, m = 0, y = 0, h, min = 0, second = 0;
		if (month.equalsIgnoreCase("jan"))
			m = 0;
		else if (month.equalsIgnoreCase("feb"))
			m = 1;
		else if (month.equalsIgnoreCase("mar"))
			m = 2;
		else if (month.equalsIgnoreCase("apr"))
			m = 3;
		else if (month.equalsIgnoreCase("may"))
			m = 4;
		else if (month.equalsIgnoreCase("june"))
			m = 5;
		else if (month.equalsIgnoreCase("july"))
			m = 6;
		else if (month.equalsIgnoreCase("aug"))
			m = 7;
		else if (month.equalsIgnoreCase("sept"))
			m = 8;
		else if (month.equalsIgnoreCase("oct"))
			m = 9;
		else if (month.equalsIgnoreCase("nov"))
			m = 10;
		else if (month.equalsIgnoreCase("dec"))
			m = 11;

		d = Integer.parseInt(day);
		y = Integer.parseInt(year);
		h = Integer.parseInt(hours);
		min = Integer.parseInt(mins);
		second = Integer.parseInt(seconds);
		date = new Date(y - 1900, m, d, h, min, second);
	
	}

	public long getTime(){
		return date.getTime();
	}

	public int getScore(String typeTeam) {
	
		return 0;
	}
		
   
}
