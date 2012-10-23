package exceedvote.air.test;

import static org.junit.Assert.*;

import org.junit.Test;

import exceedvote.air.model.Ballot;


/**
* @author Team AIR
 * @version 2555-10-23
 */
public class BallotTest {
	String typeStudent = "STUDENT";
	String typeTeacher = "TEACHER";
	String typeJanitor = "Janitor";

	Ballot ballotT = new Ballot();
	Ballot ballotS = new Ballot();
	Ballot ballotJ = new Ballot();

	/**
	 * Check the setBallot method assign correct value or not
	 */
	@Test
	public void testSetValue() {
		assertEquals("STUDENT", typeStudent);
		assertEquals(true, typeStudent.equals("STUDENT"));
		assertEquals(true, typeTeacher.equals("TEACHER"));
		assertEquals(false, typeStudent.equals("Janitor"));
		if (typeTeacher.equals("TEACHER")) {
			ballotT.setValue(typeTeacher);
			assertEquals(3, ballotT.getValue());
		}

		if (typeStudent.equals("STUDENT")) {
			ballotS.setValue(typeStudent);
			assertEquals(1, ballotS.getValue());
		}
	}

	/**
	 * Test the value of each ballot is correct or not
	 */
	@Test
	public void testGetValue() {
		if (typeTeacher.equals("TEACHER")) {
			ballotT.setValue(typeTeacher);
		}
		assertEquals(3, ballotT.getValue());

		assertEquals(0, ballotJ.getValue());
		if (typeStudent.equals("STUDENT")) {
			ballotT.setValue(typeStudent);
		}
		assertEquals(1, ballotT.getValue());

	}

	/**
	 * Check reduceBallot method if it's acted the value is decreased or not
	 */
	@Test
	public void testReduceValue() {

		if (typeTeacher.equals("TEACHER")) {
			ballotT.setValue(typeTeacher);
		}
		ballotT.reduceValue(2);
		assertEquals(1, ballotT.getValue());

		ballotJ.reduceValue(1);
		assertEquals(0, ballotJ.getValue());

		if (typeStudent.equals("STUDENT")) {
			ballotS.setValue(typeStudent);
		}
		ballotS.reduceValue(1);
		assertEquals(0, ballotS.getValue());

	}

}
