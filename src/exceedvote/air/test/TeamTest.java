package exceedvote.air.test;



import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import exceedvote.air.model.Team;
import exceedvote.air.model.TeamDescription;
import exceedvote.air.model.VoteTopic;
/**
 * The test class TestTeam.
 *
 * @author Team AIR
 * @version 2555-10-23
 */
public class TeamTest
{
	 List<VoteTopic> topic = new ArrayList<VoteTopic>();
    /**
     * Default constructor for test class TestTeam
     */
    public TeamTest()
    {
    	VoteTopic beauti = new VoteTopic("Beautiful UI");
		  VoteTopic goodFunc = new VoteTopic("Good Function");
		  VoteTopic noBug = new VoteTopic("No Bug");
		  VoteTopic presentation = new VoteTopic("Presentation");
		     
		 
		  topic.add(beauti);
		  topic.add(goodFunc);
		  topic.add(noBug);
		  topic.add(presentation);
		
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

		Team team1 = new Team("air", new TeamDescription("Hi"));
		
		assertEquals("air", team1.getName());
		assertEquals("Hi",team1.getTeamDescription().getInfo());
		
	}	
}
