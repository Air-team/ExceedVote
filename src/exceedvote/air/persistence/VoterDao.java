package exceedvote.air.persistence;

import java.util.List;
import exceedvote.air.model.Voter;

/**
 * Voter Data Access Object is a DAO for Voter objects. This class represents 
 * the interface of the database that related to Voter objects.
 * 
 * @author Air Team
 */
public interface VoterDao {


	/**
	 * Save Voter to the persistent storage.
	 * @param Voter is object represents the user that has right to vote.
	 * @return 
	 */
	public boolean save(Voter voter);

	/**
	 * Find all the Voters and return a list of all Voters.
	 * @return a list of all Voters that saved on the persistent storage.
	 */
	public abstract List<Voter> findAll();

	/**
	 * Find the Voter in the persistent storage by ID.
	 * @param id - The integer represents the Voter. One ID related to only one Voter.
	 * @return Voter object that represents the registered user.
	 */
	public Voter find(Integer id);

	public Voter findSingle(String username, String password);

	public Voter findbyName(String username);

	public boolean remove(Voter voter);

}
