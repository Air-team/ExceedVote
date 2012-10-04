package GUI;



import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class testBallotBox.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class BallotBoxTest
{
    /**
     * Default constructor for test class testBallotBox
     */
    public BallotBoxTest()
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

    @Test
    public void testBallotBox()
    {
        TeamList teamList1 = new TeamList();
        TeamDescription airTeam = new TeamDescription("Hi");
        Team team1 = new Team("air", airTeam);
        teamList1.addTeam(team1);
        assertEquals("air", team1.getName());
        BallotBox ballotBo1 = new BallotBox(teamList1);
        assertEquals(true, ballotBo1.putBallot("air"));
        assertEquals(false, ballotBo1.putBallot("anyTeamThatDidntAdd"));
    }
}

