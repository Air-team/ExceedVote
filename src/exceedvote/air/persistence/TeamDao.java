package exceedvote.air.persistence;

import java.util.List;

import exceedvote.air.model.Team;

/**
 * Team Data Access Object is a DAO for Team objects. This class represents 
 * the interface of the database that related to Team objects.
 * 
 * @author Air Team
 */
public interface TeamDao {

	/**
	 * Save Team to the persistent storage.
	 * @param Team is object represents the competitor that can be voted.
	 */
	public void save(Team team);
<<<<<<< HEAD
=======
	public abstract List<Team> findAll();
	public Team findSingle(String teamName);
>>>>>>> Implement login

	/**
	 * Find all the Teams and return a list of all Teams.
	 * @return a list of all Teams that saved on the persistent storage.
	 */
	public abstract List<Team> findAll();

}