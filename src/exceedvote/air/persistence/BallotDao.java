package exceedvote.air.persistence;

import java.util.ArrayList;
import java.util.List;

import exceedvote.air.model.Ballot;
import exceedvote.air.model.Voter;

/**
 * Ballot Data Access Object is a DAO for ballot objects. This class represents 
 * the interface of the database that related to ballot objects.
 * 
 * @author Air Team
 */
public interface BallotDao {
	
	/**
	 * Save ballot to the persistent storage.
	 * @param ballot is object represents the vote.
	 */
	public void save(Ballot ballot);

	/**
	 * Find all the ballots and return a list of all ballots.
	 * @return a list of all ballots that saved on the persistent storage.
	 */
	public abstract List<Ballot> findAll();

	/**
	 * Find a ballot by search for teamname, topic and voter from a list of all ballots.
	 * @param teamName - The name of the participated team.
	 * @param topic - The vote topic that that the user vote.
	 * @param voter - An object represents the user that relate to the ballot.
	 * @param allBallot - A list of all ballot for searching.
	 * @return a ballot from this user that match with the teamname and topic.
	 */
	public Ballot findSingle(String teamName, String topic, Voter voter,
			List<Ballot> allBallot);


	/**
	 * Delete a given ballot from the persistent storage.
	 * @param ballot - A ballot that you want to delete.
	 * @return false if the ballot doesn't contain in the persistent storage or it's null.
	 */
	public boolean deleteBallot(Ballot ballot);

	public List<ArrayList> history(Voter voter);

}
