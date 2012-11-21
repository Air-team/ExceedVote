package exceedvote.air.persistence.jpa;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import org.apache.log4j.Logger;
import exceedvote.air.model.Team;

import exceedvote.air.persistence.TeamDao;

/**
 * Team Data Access Object is a DAO for Team objects. This class represents 
 * the interface of the database that related to Team objects.
 * 
 * @author Air Team
 */
public class TeamDaoJpa implements TeamDao {
	private EntityManager em;
	private static Logger logger;

	public TeamDaoJpa(EntityManager em) {
		super();
		this.em = em;
	}

	/**
	 * Save Team to the persistent storage.
	 * @param Team is object represents the competitor that can be voted.
	 */
	@Override
	public void save(Team team) {
		EntityTransaction tx = em.getTransaction();
		try {

			tx.begin();
			em.persist(team);
			tx.commit();
		} catch (IllegalStateException ex) {
			getLogger().error("Error saving topic " + team, ex);
			if (tx.isActive())
				tx.rollback();
			// should rethrow exception so the application knows
			// that save failed
			throw ex;
		}
	}

	/**
	 * Find all the Teams and return a list of all Teams.
	 * @return a list of all Teams that saved on the persistent storage.
	 */
	@Override
	public List<Team> findAll() {
		String queryStatement = "SELECT vt FROM VoteTopic vt";
		return em.createQuery(queryStatement).getResultList();
	}

	/**
	 * Get the Logger of VoteTopicDaoJpa class.
	 * @return new Logger if it is null else return Logger.
	 */
	// At "VoteTopicDaoJpa.class" should it be "TeamDaoJpa.class"
	private static Logger getLogger() {
		if (logger == null)
			logger = Logger.getLogger(VoteTopicDaoJpa.class);
		return logger;
	}

}