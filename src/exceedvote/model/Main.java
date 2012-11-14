package exceedvote.air.model;

import java.util.ArrayList;
import java.util.List;

import exceedvote.air.ui.LoginUI;
import exceedvote.air.ui.VoteTypeUI;
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

		  VoteTopic beauti = new VoteTopic("Beautiful UI");
		  VoteTopic goodFunc = new VoteTopic("Good Function");
		  VoteTopic noBug = new VoteTopic("No Bug");
		  VoteTopic presentation = new VoteTopic("Presentation");
		     
		  List<VoteTopic> topic = new ArrayList<VoteTopic>();
		  topic.add(beauti);
		  topic.add(goodFunc);
		  topic.add(noBug);
		  topic.add(presentation);
		
		 // a test team
        TeamDescription durianDes = new TeamDescription("name: Durian");
		TeamDescription amazeDes = new TeamDescription("name: Amaze");
		TeamDescription desTest1 = new TeamDescription("name: OOP");
		TeamDescription desTest2 = new TeamDescription("name: PANDA");
		

		Team durian = new Team("Durian", durianDes,topic);
		Team amaze = new Team("Amaze", amazeDes,topic);
		Team test1 = new Team("OOP", amazeDes,topic);
		Team test2 = new Team("PANDA", amazeDes,topic);
		TeamList list = new TeamList();
		list.addTeam(durian);
		list.addTeam(amaze);
		list.addTeam(test1);
		list.addTeam(test2);
		
	   
	     
	   
		Ballot ballot = new Ballot();
		BallotBox ballotbox = new BallotBox(list);
		
		Voter voter = new Voter("Air", "STUDENT","1", ballot);
   
        
		VoteUI voteUI = new VoteUI(voter, ballotbox);
		
		VoteTypeUI voteTypeUI = new VoteTypeUI(voteUI);
		voteTypeUI.run();
		System.out.println(amaze.getScore("Presentation"));
//		LoginUI loginUI = new LoginUI();
//		loginUI.run();
	}
}