package exceedvote.air.model;

/**
 * The Ballot class represents the vote from the user. Each ballot contains the vote(s), separated by the kind of user,
 * that the user votes for their favorite them.
 * @author Busarat Jumrusvimonrat
 */
public class Ballot {

	/** The vote of a ballot */
	public int value;
	private Team team;

	/**
	 * Ballot constructor. Initialize value.
	 */
	public Ballot() {
		value = 0;
	}

	/**
	 * Get the vote of the ballot.
	 * @return value is the vote of the ballot.
	 */
	public int getValue() {
		return this.value;
	}

	/**
	 * Set the value(s) of the ballot, separated by the kind of the user.
	 * @param name the kind of the user. There are 2 kind "STUDENT" and "TEACHER".(for now)
	 */
	public void setValue(String name) {
		if (name.equals("STUDENT"))
			this.value = 1;
		else if (name.equals("TEACHER"))
			this.value = 3;
	}

	/**
	 * Reduce the value of the vote in the ballot.
	 * @param value the reducing value.
	 */
	public void reduceValue(int value) {
		if (this.value > 0)
			this.value -= value;
		else
			this.value = 0;
	}
}
