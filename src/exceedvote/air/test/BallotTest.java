package exceedvote.air.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import exceedvote.air.model.Ballot;
import exceedvote.air.model.Team;
import exceedvote.air.model.TeamDescription;
import exceedvote.air.model.TeamList;
import exceedvote.air.model.Voter;
import exceedvote.air.persistence.DaoFactory;
import exceedvote.air.persistence.TeamDao;


 /**
 * @author Team AIR
 * @version 2555-10-23
 */
public class BallotTest {
	String topic1 = "No Bug";
	String topic2 = "presentation";
	String Team = "Amaze";
	String TeamF= "FF";
	TeamList list = new TeamList();
	
	Voter voter = new Voter("AIR","STUDENT");
	
	TeamDescription durianDes = new TeamDescription("name: Durian");
	TeamDescription amazeDes = new TeamDescription("name: Amaze");
	TeamDao dao2 = DaoFactory.getInstance().getTeamDao();
	Team durian = new Team("Durian", durianDes);
	Team amaze = new Team("Amaze", amazeDes);

	/**
	 * Check the setBallot method assign correct value or not
	 */
	

	/**
	 * Test the value of each ballot is correct or not
	 */
	
	@Test
	public  void testPutBallot(){
		
		list.addTeam(durian);
		list.addTeam(amaze);
		System.out.println(list.toString());
		Ballot ballotT = new Ballot(list);
//		assertEquals(true, ballotT.putBallot(Team, topic1, voter));
//		assertEquals(true, ballotT.putBallot(Team, topic2, voter));
//		assertEquals(true, ballotT.returnBallot(Team, topic1, voter));
//		assertEquals(false, ballotT.putBallot(TeamF, topic1, voter));
	}
}
