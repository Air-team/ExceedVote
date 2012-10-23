package exceedvote.air.model;

import java.util.List;

import org.apache.log4j.Logger;

/**
 * Box that you keep the ballot of each team that you want to vote
 * @author PrisaDumrongsiri
 * 
 */
public class BallotBox {

	private TeamList teamList;
	private List<Team> list;
	private Tracking track = new Tracking();
	private Logger log;

	public BallotBox(TeamList teamList) {
		this.teamList = teamList;
		list = teamList.getTeam();
	}

	/**
	 * Put the ballot in the team that you want to vote via ballot box
	 * 
	 * @param teamName
	 *            is the team name that you want to vote
	 */
	public boolean putBallot(String teamName) {

		
			for (int i = 0; i < list.size(); i++) {
				if ( list.get(i).getName().equals(teamName) ) {
					teamList.setBallot(teamName);
					track.addLog(teamName);
					return true;
				}
			}
		
		return false;
	}

}
