package exceedvote.air.model;

import java.util.List;

import org.apache.log4j.Logger;
import javax.persistence.Entity;

/**
 * BallotBox collects the ballot, that contains the vote of each user, from the users.
 * @author Prisa Dumrongsiri
 */
//@Entity
public class BallotBox {

	/** TeamList */
	private TeamList teamList;
	/** The List of teams to vote */
	private List<Team> list;
	/** Log4j */
	//TODO do logging in this class
	private Tracking track = new Tracking();
	/** Log for tracking the action of this class */
	private Logger log;

	/**
	 * BallotBox constructor knows the TeamLisT. Initialize the list of all team.
	 * @param teamList
	 */
	public BallotBox(TeamList teamList) {
		this.teamList = teamList;
		list = teamList.getTeam();
	}

	/**
	 * Put the Ballot into the BallotBox. And set the vote, that the user vote, to the team.
	 * @param teamName is name of the team that the user wants to vote.
	 */
	public boolean putBallot(String teamName,String typeTeam) {
		for (int i = 0; i < list.size(); i++) {
			if ( list.get(i).getName().equals(teamName) ) {
				teamList.setBallot(teamName,typeTeam);
				track.addLog(teamName);
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
