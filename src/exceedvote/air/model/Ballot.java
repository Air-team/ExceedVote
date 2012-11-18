package exceedvote.air.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import exceed.air.persistence.BallotDao;
import exceed.air.persistence.DaoFactory;

/**
 * Entity implementation class for Entity: Ballot
 *
 */
@Entity
public class Ballot implements Serializable {

	
	private static final long serialVersionUID = 1L;

	public Ballot() {
		super();
	}
	
	
   
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String teamName;
	private String topic;
	
	
	private Voter voter;
	private int value;
	
	
	/** TeamList */
	@OneToOne(cascade=CascadeType.PERSIST)
	private TeamList teamList;
	private Team team;
	
	/** The List of teams to vote */
	private List<Team> list;
	/** Log4j */
	@OneToOne(cascade=CascadeType.PERSIST)
	private Tracking track = new Tracking();
	/** Log for tracking the action of this class */
	

	
	public Ballot(TeamList teamList) {
		this();
		
		this.teamList = teamList;
		list = teamList.getTeam();
		value = 0;
		
	}   
	

	public Integer getId() {
		return this.id;
	}

	public String getTeamName() {
		return teamName;
	}
	
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	
	public String getTopic() {
		return topic;
	}
	
	public void setTopic(String topic) {
		this.topic = topic;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * Get the vote of the ballot.
	 * @return value is the vote of the ballot.
	 */
	public int getValue() {
		return this.value;
	}
	
	/**
	 * Put the Ballot into the BallotBox. And set the vote, that the user vote, to the team.
	 * @param teamName is name of the team that the user wants to vote.
	 */
	public boolean putBallot(String teamName,String typeTeam,Voter voter) {
		
		for (int i = 0; i < list.size(); i++) {
			
			if ( list.get(i).getName().equals(teamName) ) {
				
				this.voter = voter;
				int value  = voter.getballotLeft();
				this.team = list.get(i);
				int score = team.getScore(typeTeam);
				score++;
				team.setScore(score, typeTeam);
				BallotDao dao = DaoFactory.getInstance().getBallotDao();
				dao.save(this);
				voter.setballotLeft(value-1);
				track.addLog(teamName,typeTeam);
				return true;
			}
		}		
		return false;
	}
	
	public String[] getTeamNames()
    {
        return teamList.getTeamNames();
    }
   
}
