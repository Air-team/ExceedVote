package exceedvote.air.model;

import java.util.List;

/**
 * Class Voter represents the user that can vote to the projects. 
 * @author Busarat Jumrusvimonrat.
 */
public class Voter {

	private String name;
	private String type;
	private Ballot ballot;
	private List<Team> list;
	private BallotBox bb;

	/**
	 * Constructor for objects of class Voter.
	 * @param name is the name of the voter.
	 * @param type is a kind of the voter such "STUDENT" or "TEACHER".(for now)
	 * @param ballot is the ballot that the voter has.
	 * @param bb is ballot box.
	 * @param teamList is the team list.
	 */
	public Voter(String name, String type, Ballot ballot, BallotBox bb, TeamList teamList) {
		this.name = name;
		this.type = type;
		this.ballot = ballot;
		this.bb = bb;
		this.list = teamList.getTeam();
	}

	/**
	 * Get the voter name.
	 * @return String name of the voter.
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Get the type of the voter such as "STUDENT" or "TEACHER".
	 * @return
	 */
	public String getType() {
		return this.type;
	}

	/**
	 * Return the list of all team.
	 * @return list of the teams.
	 */
	public List<Team> showTeam() {
		return list;
	}

	/**
	 * Get the Ballot by specify the type of the voter.
	 * @return ballot that has value.
	 */
	public Ballot getBallot() {
		ballot.setValue(type);
		return this.ballot;
	}

	/**
	 * Put the ballot into the BallotBox.
	 * @param nameT is the name of thee team that voter wants to vote.
	 * @return false if cannot vote.
	 */
	public boolean pullBollot(String nameT) {
		return bb.putBallot(nameT);
	}

	/**
	 * Return all the team names in an array of String;
	 * @return array String contains the name of teams.
	 */
	public String[] getTeamNames() {
		String[] names = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			names[i] = list.get(i).getName();
		}
		return names;
	}
}