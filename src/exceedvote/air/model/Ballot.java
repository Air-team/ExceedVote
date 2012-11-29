package exceedvote.air.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import exceedvote.air.persistence.BallotDao;
import exceedvote.air.persistence.DaoFactory;
import exceedvote.air.persistence.TeamDao;

/**
 * Ballot represent the right that each user have in their hands to vote for the
 * team. Each ballot represent one right.
 * 
 * @author Air Team
 * @version 2012.11.20
 */
@Entity
public class Ballot implements Serializable {

	private static final long serialVersionUID = 1L;
	private static Ballot ballot;
	
	
	
   

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String teamName;
	private String topic;
	private Committee committee;
	private Voter voter;


	@OneToOne(cascade = CascadeType.PERSIST)
	private Team team;

	/** The List of teams to vote */
	private List<Team> list;
	/** Log4j for tracking the action of this class */
	@Transient
	private Tracking track = new Tracking();


	
	/**
	 * Ballot Constructor that receive the list of team.
	 * 
	 * @param teamList is a list of all team in the competition.
	 */

	/** Log for tracking the action of this class */
	
	private String time = "";

	public Ballot() {
		super();
		
	}


	/**
	 * Return the Ballot ID. 
	 * @return an Integer represent the ID.
	 */

	public static Ballot getInstance(){
		return ballot;
	}
	
	public void setInstance(Ballot ballot){
		this.ballot = ballot;
	}

	public Integer getId() {
		return this.id;
	}

	/**
	 * Return the team name. 
	 * @return String represent team name.
	 */
	public String getTeamName() {
		return teamName;
	}

	/**
	 * Set the team name. 
	 * @param teamName is a String represent team name.
	 */
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	/**
	 * Return the list of all team . 
	 * @return List of Team represent all the team.
	 */
	public List<Team> getTeam() {
		TeamDao teamDao = DaoFactory.getInstance().getTeamDao();
		return teamDao.findAll();
	}

	/**
	 * Return the the Vote topic. 
	 * @return String represent the vote topic.
	 */
	public String getTopic() {
		return topic;
	}

	/**
	 * Set the vote topic. 
	 * @param topic is the topic for vote.
	 */
	public void setTopic(String topic) {
		this.topic = topic;
	}

	/**
	 * Set the Ballot ID. 
	 * @param id is an Integer represent the ID.
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Set the voter that has this ballot.
	 * @param voter - Voter object that has name and type.
	 */
	public void setVoter(Voter voter) {
		this.voter = voter;
	}

	/**
	 * Return the voter the has this ballot.
	 * @return Voter object that has name and type.
	 */
	public Voter getVoter() {
		return voter;
	}
	
	public void setCommittee(Committee committee){
		this.committee = committee;
	}
	
	public Committee getCommittee(){
		return committee;
	}

	/**
	 * Put the Ballot into the BallotBox and set the score to the team. 
	 * @param teamName - is name of the team that the user wants to vote.
	 * @param typeTeam - the team type.
	 * @param voter - the person who vote.
	 * @return true if successfully put the ballot.
	 */
	public boolean putBallot(String teamName, String typeTeam, Voter voter) {
		list = getTeam();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getName().equals(teamName)) {
				Ballot ballot = new Ballot();
				Date date = new Date();
				String time = date.toLocaleString();
				ballot.setTime(time);
				ballot.setTeamName(teamName);
				ballot.setTopic(typeTeam);
				ballot.setVoter(voter);

				this.voter = voter;
				int value = voter.getballotLeft();

				ballot.voter = voter;
				this.team = list.get(i);
				int score = team.getScore(typeTeam);
				score++;
				team.setScore(score, typeTeam);
				BallotDao dao = DaoFactory.getInstance().getBallotDao();
				dao.save(ballot);
				voter.setballotLeft(value - 1);
				track.addLogVote(teamName, typeTeam);
				return true;
			}
		}
		return false;
	}

	public boolean putBallot(String teamName, String typeTeam, Committee committee) {
		list = getTeam();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getName().equals(teamName)) {
				Ballot ballot = new Ballot();
				Date date = new Date();
				String time = date.toLocaleString();
				ballot.setTime(time);
				ballot.setTeamName(teamName);
				ballot.setTopic(typeTeam);
				ballot.setCommittee(committee);

				this.committee = committee;
				int value = committee.getballotLeft();

				ballot.committee = committee;
				this.team = list.get(i);
				int score = team.getScore(typeTeam);
				score++;
				team.setScore(score, typeTeam);
				BallotDao dao = DaoFactory.getInstance().getBallotDao();
				dao.save(ballot);
				committee.setballotLeft(value - 1);
				track.addLogVote(teamName, typeTeam);
				return true;
			}
		}
		return false;
	}

	
	
	public void setTime(String time){
		this.time = time;
	}
	
	public String getTime(){
		return time;
	}
	


	/**
	 * To give the ballot back for the revoting.
	 * 
	 * @param teamName - is name of the team that the user voted.
	 * @param typeTeam - the team type.
	 * @param voter - the person who vote.
	 * @return true if the user can get the ballot back.
	 */
	public boolean returnBallot(String teamName, String typeTeam, Voter voter) {
		BallotDao dao = DaoFactory.getInstance().getBallotDao();
		List<Ballot> allBallot = dao.findAll();
		Ballot bb = dao.findSingle(teamName, typeTeam, voter, allBallot);

		// if(bb==null) System.out.println("aaa");
		if (bb != null) {
			this.voter = voter;
			int value = voter.getballotLeft();
			int score = team.getScore(typeTeam);
			score--;
			team.setScore(score, typeTeam);
			voter.setballotLeft(value + 1);
			dao.deleteBallot(bb);
			track.addLogRevote(teamName, typeTeam);
			return true;
		} else
			return false;
		}
	
	public boolean returnBallot(String teamName, String typeTeam, Committee committee) {
		BallotDao dao = DaoFactory.getInstance().getBallotDao();
		List<Ballot> allBallot = dao.findAll();
		Ballot bb = dao.findSingle(teamName, typeTeam, committee, allBallot);

		// if(bb==null) System.out.println("aaa");
		if (bb != null) {
			this.committee = committee;
			int value = committee.getballotLeft();
			int score = team.getScore(typeTeam);
			score--;
			team.setScore(score, typeTeam);
			committee.setballotLeft(value + 1);
			dao.deleteBallot(bb);
			track.addLogRevote(teamName, typeTeam);
			return true;
		} else
			return false;
		}
	
	

}
