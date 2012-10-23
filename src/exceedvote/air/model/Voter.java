package exceedvote.air.model;

import java.util.List;

/**
 * 
 * @author Busarat Jum
 * 
 *         Voter Class
 */
public class Voter {

	private String name;
	private String type;
	private Ballot ballot;
	private List<Team> list;
	private BallotBox ballotBox;

	public Voter(String name, String type, Ballot ballot, BallotBox bb,
			TeamList teamList) {
		this.name = name;
		this.type = type;
		this.ballot = ballot;
		this.ballotBox= bb;
		this.list = teamList.getTeam();
	}

	public String getName() {
		return this.name;
	}

	public String getType() {
		return this.type;
	}

	public List<Team> showTeam() {
		return list;
	}

	public Ballot getBallot() {
		ballot.setValue(type);
		return this.ballot;
	}

	public boolean pullBollot(String nameT) {
		return bb.putBallot(nameT);
	}

	public String[] getTeamNames() {
		String[] names = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			names[i] = list.get(i).getName();
		}
		return names;
	}
}