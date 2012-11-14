package exceedvote.air.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import exceedvote.air.model.BallotBox;
import exceedvote.air.model.Team;
import exceedvote.air.model.TeamDescription;
import exceedvote.air.model.TeamList;
import exceedvote.air.model.VoteTopic;


/**
 * The test class testBallotBox.
 * @author Team AIR
 * @version 2555-10-23
 */
public class BallotBoxTest {
	/**
	 * Default constructor for test class testBallotBox
	 */
	public BallotBoxTest() {
	}

	/**
	 * Sets up the test fixture.
	 * 
	 * Called before every test case method.
	 */
	@Before
	public void setUp() {
	}

	/**
	 * Tears down the test fixture.
	 * 
	 * Called after every test case method.
	 */
	@After
	public void tearDown() {
	}
	
	/**
	 * Create team and try to add to available team and not available team
	 */
	@Test
	public void testBallotBox() {

		VoteTopic beauti = new VoteTopic("Beautiful UI");
		  VoteTopic goodFunc = new VoteTopic("Good Function");
		  VoteTopic noBug = new VoteTopic("No Bug");
		  VoteTopic presentation = new VoteTopic("Presentation");
		     
		  List<VoteTopic> topic = new ArrayList<VoteTopic>();
		  topic.add(beauti);
		  topic.add(goodFunc);
		  topic.add(noBug);
		  topic.add(presentation);
		
		TeamList teamList1 = new TeamList();
		TeamDescription airTeam = new TeamDescription("Hi");
		Team team1 = new Team("air", airTeam,topic);
		teamList1.addTeam(team1);
		assertEquals("air", team1.getName());
		BallotBox ballotBo1 = new BallotBox(teamList1);
		assertEquals(true, ballotBo1.putBallot("air","Good Function"));
		assertEquals(false, ballotBo1.putBallot("anyTeamThatDidntAdd","Good Function"));
	}
}
