package exceedvote.air.persistence.jpa;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import org.apache.log4j.Logger;
import exceedvote.air.model.Ballot;
import exceedvote.air.model.Voter;
import exceedvote.air.persistence.BallotDao;

/**
 * Ballot Data Access Object is a DAO for ballot objects. This class represents 
 * the interface of the database that related to ballot objects.
 * 
 * @author Air Team
 */
public class BallotDaoJpa implements BallotDao {

	private EntityManager em;
	private static Logger logger;

	public BallotDaoJpa(EntityManager em) {
		super();
		this.em = em;
	}

	/**
	 * Save ballot to the persistent storage.
	 * @param ballot is object represents the vote.
	 */
	@Override
	public void save(Ballot ballot) {
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.persist(ballot);
			tx.commit();
		} catch (IllegalStateException ex) {
			getLogger().error("Error saving topic " + ballot, ex);
			if (tx.isActive())
				tx.rollback();
			// should rethrow exception so the application knows
			// that save failed
			throw ex;
		}

	}

	/**
	 * Find all the ballot and return a list of all ballots.
	 * @return a list of all ballots that saved on the persistent storage.
	 */
	@Override
	public List<Ballot> findAll() {
		String queryStatement = "SELECT vt FROM Ballot vt";
		return em.createQuery(queryStatement).getResultList();
	}

	/**
	 * Find a ballot by search for teamname, topic and voter from a list of all ballots.
	 * @param teamName - The name of the participated team.
	 * @param topic - The vote topic that that the user vote.
	 * @param voter - An object represents the user that relate to the ballot.
	 * @param allBallot - A list of all ballot for searching.
	 * @return a ballot from this user that match with the teamname and topic.
	 */
	@Override
	public Ballot findSingle(String teamName, String topic, Voter voter,
			List<Ballot> allBallot) {
		for (int i = 0; i < allBallot.size(); i++) {
			Ballot bl = allBallot.get(i);
			if ((bl.getTopic().equals(topic))
					&& (bl.getTeamName().equals(teamName))
					&& (bl.getVoter().equals(voter))) {
				return bl;
			}
		}
		return null;
	}

	/**
	 * Delete a given ballot from the persistent storage.
	 * @param ballot - A ballot that you want to delete.
	 * @return false if the ballot doesn't contain in the persistent storage or it's null.
	 */
	@Override
	public boolean deleteBallot(Ballot ballot) {
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.remove(ballot);
			tx.commit();
			return true;
			// System.out.printf("Delete Success");
		} catch (Exception ex) {
			getLogger().error("Error Delete Ballot " + ex);
			if (tx.isActive()) {
				tx.rollback();
			}
			return false;
		}
	}
<<<<<<< HEAD

	/**
	 * Get the Logger of BallotDaoJpa class.
	 * @return new Logger if it is null else return Logger.
	 */
=======
	
	@Override
	public List<ArrayList>  history(Voter voter){
		List<Ballot> allBallot = this.findAll();
		
		List<ArrayList> his = new ArrayList<ArrayList>();
		for (int i=0;i<allBallot.size();i++) {
			Ballot bl = allBallot.get(i);
			if (  (bl.getVoter().equals(voter) )){				
				ArrayList<String> info = new ArrayList<String>();
				info.add(bl.getTeamName());
				info.add(bl.getTopic());
				info.add(bl.getTime());
				his.add(info);
			}
		}
		
		return his;
	}
>>>>>>> Implement login
	private static Logger getLogger() {
		if (logger == null)
			logger = Logger.getLogger(BallotDaoJpa.class);
		return logger;
	}

}
