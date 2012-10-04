package GUI;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class TeamListTest {
	
	@Test
	public void testAddOneTeam() {
		Team team1 = new Team("iSelf", new TeamDescription("diary"));
		TeamList tlist = new TeamList();
		assertEquals(true, tlist.addTeam(team1));
	}
	
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
	
	@Test
	public void testAddSameTeam() {
		Team team1 = new Team("iSelf", new TeamDescription("diary"));
		Team team2 = new Team("Poo Poo jet", new TeamDescription("game"));
		TeamList tlist = new TeamList();
		
		assertEquals(true, tlist.addTeam(team1));
		assertEquals(true, tlist.addTeam(team2));
		assertEquals(false, tlist.addTeam(team2));
	}

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

