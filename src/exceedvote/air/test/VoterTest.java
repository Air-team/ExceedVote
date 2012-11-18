package exceedvote.air.test;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Test;

import exceedvote.air.model.Voter;

public class VoterTest {

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
	Voter voter1 = new Voter("AIR","STUDENT");
	Voter voter2 = new Voter("EE","TEACHER");
	@Test
	public void testSetValue() {
		
		
		assertEquals(true, voter1.getType().equals("STUDENT"));
		assertEquals(true, voter2.getType().equals("TEACHER"));
		assertEquals(false, voter1.getType().equals("Janitor"));
		assertEquals(5,voter1.getballotLeft());
	}

}
