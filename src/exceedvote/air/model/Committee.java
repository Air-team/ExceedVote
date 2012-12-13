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


/**
 * Committee represents the judge of Exceed Camp project competition.
 * The committee will be promoted by the administer.
 * Committee can add or remove team or vote topic from the competition.
 * Committee can set the time interval for vote.
 */
@Entity
public class Committee implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String username = "";
	private String password = "";
	private String type = "";
	private int amountOfBallot = 0;

	private String teamName;
	private String topicName;
	@Temporal(TemporalType.DATE)
	private Date date;
	
	/**
	 * Initialize committee.
	 */
	public Committee() {
		super();
	}
	
	/**
	 * Return the type of the committee.
	 * @return type of this committee
	 */
	public String getType() {
		return type;
	}

	/**
	 * Set the type of this committee.
	 * @param type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Get the password of this committee.
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Set password of this committee.
	 * @param password is the new password.
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Get the username of this committee.
	 * @return username of this committee.
	 */
	public String getName() {
		return username;
	}

	/**
	 * Set the username of this committee.
	 * @param name is the new username.
	 */
	public void setName(String name) {
		this.username = name;
	}

	/**
	 * Set the left ballot for this committee.
	 * @param value the amount of ballots.
	 */
	public void setballotLeft(int value) {
		this.amountOfBallot = value;
		CommitteeDao dao = DaoFactory.getInstance().getCommitteeDao();
		dao.saveCom(this);
	}

	/**
	 * Return the amount of the left ballots.
	 * @return amount of the left ballots.
	 */
	public int getballotLeft() {
		return amountOfBallot;
	}

	/**
	 * Save the information of this committee to the database.
	 * @param committee is the committee object.
	 */
	public void saveInfo(Committee committee) {
		CommitteeDao dao = DaoFactory.getInstance().getCommitteeDao();
		dao.saveCom(committee);
	}

	/**
	 * Return the list of histories.
	 * @return list of histories.
	 */
	public List<ArrayList> history() {
		BallotDao dao = DaoFactory.getInstance().getBallotDao();
		return dao.historyCom(this);
	}

	/**
	 * Return the List of all Teams.
	 * @return list of all team.
	 */
	public List<Team> getTeam() {
		TeamDao teamDao = DaoFactory.getInstance().getTeamDao();
		return teamDao.findAll();
	}

	/**
	 * Set the team and save to the database.
	 * @param teamName is the team's name
	 * @return true if the team saved to the database.
	 */
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

	/**
	 * Return the team's name.
	 * @return The team name.
	 */
	public String getTeamName() {
		return teamName;
	}

	/**
	 * Set the the team name.
	 * @param teamName is the new team name.
	 */
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	/**
	 * Return the topic name.
	 * @return the topic name.
	 */
	public String getTopicName() {
		return topicName;
	}

	/**
	 * Set the topic name.
	 * @param topicName is the new topic name.
	 */
	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}

	/**
	 * Return list of topics.
	 * @return list of topics.
	 */
	public List<VoteTopic> getTopic() {
		VoteTopicDao dao = DaoFactory.getInstance().getVoteTopicDao();
		return dao.findAll();
	}

	/**
	 * Set the topic and save to the database.
	 * @param topicName is name of topic.
	 * @return true if topic saved to the database.
	 */
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

	/**
	 * Delete the specific team from the database.
	 * @param teamName is name of the team that you want to delete.
	 * @return true if the team is successfully deleted.
	 */
	public boolean deleteTeam(String teamName) {
		TeamDao teamDao = DaoFactory.getInstance().getTeamDao();
		if (teamDao.findSingle(teamName).getName().equals(teamName)) {
			teamDao.remove(teamDao.findSingle(teamName));
			return true;
		} 
		else
			return false;
	}

	/**
	 * Delete the specific topic from the database.
	 * @param topicName is name of the topic that you want to delete.
	 * @return true if the topic is successfully deleted.
	 */
	public boolean deleteTopic(String topicName) {
		VoteTopicDao topicDao = DaoFactory.getInstance().getVoteTopicDao();
		if (topicDao.find(topicName).getTitle().equals(topicName)) {
			topicDao.remove(topicDao.find(topicName));
			return true;
		} else {

			return false;
		}
	}
	
	/**
	 * Set the stop time or the end of voting time.
	 * @param day is the end date.
	 * @param month is the end month.
	 * @param year is the end year.
	 * @param hours is the end hour.
	 * @param mins is the end minute.
	 * @param seconds is the end second.
	 */
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
		Time time = new Time(this);
		time.setTime(date.getTime());
		time.saveTime();
	}

	/**
	 * Return the time.
	 * @return time in millisec.
	 */
	public long getTime() {
		Time time = new Time(this);
		return time.getTime();
	}
}
