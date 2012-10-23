package exceedvote.air.model;

import exceedvote.air.ui.VoteUI;

/**
 * Start Vote system
 * @author Air Team
 * @version 2012.10.1
 */
public class Main {

	/**
	 * Initialize the Objects and runs the program.
	 * @param args
	 */
	public static void main(String[] args) {

		// a test team
		TeamDescription durianDes = new TeamDescription("name: Durian");
		TeamDescription amazeDes = new TeamDescription("name: Amaze");
		TeamDescription desTest1 = new TeamDescription("name: OOP");
		TeamDescription desTest2 = new TeamDescription("name: PANDA");

		Team durian = new Team("Durian", durianDes);
		Team amaze = new Team("Amaze", amazeDes);
		Team test1 = new Team("OOP", amazeDes);
		Team test2 = new Team("PANDA", amazeDes);
		TeamList list = new TeamList();
		list.addTeam(durian);
		list.addTeam(amaze);
		list.addTeam(test1);
		list.addTeam(test2);

		Ballot ballot = new Ballot();
		BallotBox ballotbox = new BallotBox(list);
		Voter voter = new Voter("Air", "STUDENT", ballot, ballotbox, list);

		VoteUI voteUI = new VoteUI(voter);
		voteUI.run();
	}
}