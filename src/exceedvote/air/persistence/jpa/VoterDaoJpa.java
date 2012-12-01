package exceedvote.air.persistence.jpa;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import org.apache.log4j.Logger;
import exceedvote.air.model.Voter;
import exceedvote.air.persistence.VoterDao;

/**
 * Voter Data Access Object is a DAO for Voter objects. This class represents
 * the interface of the database that related to Voter objects.
 * 
 * @author Air Team
 */
public class VoterDaoJpa implements VoterDao {
	private EntityManager em;
	private static Logger logger;

	public VoterDaoJpa(EntityManager em) {
		super();
		this.em = em;

	}

	/**
	 * Save Voter to the persistent storage.
	 * 
	 * @param Voter
	 *            is object represents the user that has right to vote.
	 */
	@Override
	public boolean save(Voter voter) {
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.persist(voter);
			tx.commit();
		} catch (IllegalStateException ex) {
			getLogger().error("Error saving topic " + voter, ex);
			if (tx.isActive())
				tx.rollback();
			// should rethrow exception so the application knows
			// that save failed
			throw ex;

		}
		return true;
	}

	/**
	 * Find user in database
	 */
	@Override
	public Voter findSingle(String username, String password) {
		List<Voter> allVoters = findAll();
		for (int i = 0; i < allVoters.size(); i++) {
			Voter voter = allVoters.get(i);
			if (voter.getName().equals(username)
					&& voter.getPassword().equals(password)) {
				return voter;
			}
		}
		return null;
	}
	
	@Override
	public Voter findbyName(String username) {
		List<Voter> allVoters = findAll();
		for (int i = 0; i < allVoters.size(); i++) {
			Voter voter = allVoters.get(i);
			if (voter.getName().equals(username)) {
				return voter;
			}
		}
		return null;
	}
	
	/**
	 * Delete the voter out of persistence
	 * @param voter that want to delete
	 */
	@Override
	public boolean remove(Voter voter){
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.remove(voter);
			tx.commit();
			 System.out.printf("Delete Success");
			return true;
			
		} catch (Exception ex) {
			getLogger().error("Error Delete Voter " + ex);
			if (tx.isActive()) {
				tx.rollback();
			}
			return false;
		}
	}

	/**
	 * Find all the Voters and return a list of all Voters.
	 * 
	 * @return a list of all Voters that saved on the persistent storage.
	 */
	@Override
	public List<Voter> findAll() {
		String queryStatement = "SELECT vt FROM Voter vt";
		return em.createQuery(queryStatement).getResultList();
	}

	/**
	 * Find the Voter in the persistent storage by ID.
	 * 
	 * @param id
	 *            - The integer represents the Voter. One ID related to only one
	 *            Voter.
	 * @return Voter object that represents the registered user.
	 */
	@Override
	public Voter find(Integer id) {
		return em.find(Voter.class, id);
	}

	/**
	 * Get the Logger of VoterDaoJpa class.
	 * 
	 * @return new Logger if it is null else return Logger.
	 */
	private static Logger getLogger() {
		if (logger == null)
			logger = Logger.getLogger(VoterDaoJpa.class);
		return logger;
	}

}