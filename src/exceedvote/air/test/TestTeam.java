package exceedvote.air.test;



import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import exceedvote.air.model.BallotBox;
import exceedvote.air.model.Team;
import exceedvote.air.model.TeamDescription;
import exceedvote.air.model.TeamList;
/**
 * The test class TestTeam.
 *
 * @author Team AIR
 * @version 2555-10-23
 */
public class TestTeam
{
    /**
     * Default constructor for test class TestTeam
     */
    public TestTeam()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }
    
	/**
	 * Create the team, check the name and try to set and check the score
	 */
    @Test
	public void testTeam() {
		TeamList teamList1 = new TeamList();
		Team team1 = new Team("air", new TeamDescription("Hi"));
		teamList1.addTeam(team1);
		assertEquals("air", team1.getName());
		team1.setScore(1);
		assertEquals(1, team1.getScore());
	}	
}
