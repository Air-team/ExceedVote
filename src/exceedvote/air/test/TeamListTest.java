package exceedvote.air.test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import exceedvote.air.model.Team;
import exceedvote.air.model.TeamDescription;
import exceedvote.air.model.TeamList;
/**
 * The test class TeamListTest.
 *
 * @author Team AIR
 * @version 2555-10-23
 */

public class TeamListTest {
	/**
	 * Check the if one team is add correctly to list
	 */
	@Test
	public void testAddOneTeam() {
		Team team1 = new Team("iSelf", new TeamDescription("diary"));
		TeamList tlist = new TeamList();
		assertEquals(true, tlist.addTeam(team1));
	}
	/**
	 * Check the if ten team is add correctly to list
	 */
	@Test
	public void testAddTenTeam() {
		Team team1 = new Team("iSelf", new TeamDescription("diary"));
		Team team2 = new Team("Poo Poo", new TeamDescription("game"));
		Team team3 = new Team("Bee", new TeamDescription("game"));
		Team team4 = new Team("Air", new TeamDescription("element"));
		Team team5 = new Team("Ant", new TeamDescription("animal"));
		Team team6 = new Team("Fish", new TeamDescription("animal"));
		Team team7 = new Team("Mad", new TeamDescription("human"));
		Team team8 = new Team("Uncle", new TeamDescription("family"));
		Team team9 = new Team("Love", new TeamDescription("emotion"));
		Team team10 = new Team("JetPack", new TeamDescription("game"));
		TeamList tlist = new TeamList();
		assertEquals(true, tlist.addTeam(team1));
		assertEquals(true, tlist.addTeam(team2));
		assertEquals(true, tlist.addTeam(team3));
		assertEquals(true, tlist.addTeam(team4));
		assertEquals(true, tlist.addTeam(team5));
		assertEquals(true, tlist.addTeam(team6));
		assertEquals(true, tlist.addTeam(team7));
		assertEquals(true, tlist.addTeam(team8));
		assertEquals(true, tlist.addTeam(team9));
		assertEquals(true, tlist.addTeam(team10));
	}
	
	/**
	 * Check if we avoid adding the same team or not
	 */
	@Test
	public void testAddSameTeam() {
		Team team1 = new Team("iSelf", new TeamDescription("diary"));
		Team team2 = new Team("Poo Poo jet", new TeamDescription("game"));
		TeamList tlist = new TeamList();
		
		assertEquals(true, tlist.addTeam(team1));
		assertEquals(true, tlist.addTeam(team2));
		assertEquals(false, tlist.addTeam(team2));
	}
	
	/**
	 * test the size of TeamList
	 */
	@Test
	public void testGetTeam() {
		Team team1 = new Team("iSelf", new TeamDescription("diary"));
		TeamList tlist = new TeamList();
		List<Team> list = new ArrayList<Team>();
		
		assertEquals(true, tlist.addTeam(team1));
		assertEquals(false, tlist.addTeam(team1));
		
		list = tlist.getTeam();
		assertEquals(1, list.size());
	}
	
	/**
	 * try to add ballot and test if it's added
	 */
	@Test
	public void testSetBallot() {
		Team team1 = new Team("iSelf", new TeamDescription("diary"));
		TeamList tlist = new TeamList();
		
		assertEquals(true, tlist.addTeam(team1));
		
		tlist.setBallot("iSelf");
		assertEquals(1, team1.getScore());
		tlist.setBallot("iSelf");
		assertEquals(2, team1.getScore());
		tlist.setBallot("iSelf");
		assertEquals(3, team1.getScore());
	}
}

