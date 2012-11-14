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
	private String ballotLeft;

	/**
	 * Constructor for objects of class Voter.
	 * @param name is the name of the voter.
	 * @param type is a kind of the voter such "STUDENT" or "TEACHER".(for now)
	 * @param ballot is the ballot that the voter has.
	 * @param bb is ballot box.
	 * @param teamList is the team list.
	 */
	public Voter(String name, String type,String ballotLeft, Ballot ballot) {
		this.name = name;
		this.type = type;
		this.ballot = ballot;
		this.ballotLeft = ballotLeft;
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

	public String getballotLeft(){
        return ballotLeft;
    }

	/**
	 * Get the Ballot by specify the type of the voter.
	 * @return ballot that has value.
	 */
	public Ballot getBallot() {
		ballot.setValue(type);
		return this.ballot;
	}

	
	
}