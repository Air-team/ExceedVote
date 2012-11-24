package exceedvote.air.persistence.jpa;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import org.apache.log4j.Logger;
import exceedvote.air.model.VoteTopic;
import exceedvote.air.persistence.VoteTopicDao;

/**
 * VoteTopic Data Access Object is a DAO for VoteTopic objects. This class represents 
 * the interface of the database that related to VoteTopic objects.
 * 
 * @author Air Team
 */
public class VoteTopicDaoJpa implements VoteTopicDao {
	private EntityManager em;
	private static Logger logger;

	public VoteTopicDaoJpa(EntityManager em) {
		super();
		this.em = em;
	}

	/**
	 * Find all the VoteTopic and return a list of all VoteTopics.
	 * @return a list of all VoteTopics that saved on the persistent storage.
	 */
	@Override
	public List<VoteTopic> findAll() {
		String queryStatement = "SELECT vt FROM VoteTopic vt";
		return em.createQuery(queryStatement).getResultList();
	}

	/**
	 * Save VoteTopic to the persistent storage.
	 * @param VoteTopic is object represents the topic for voting.
	 */
	@Override
	public void save(VoteTopic topic) {
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			em.persist(topic);
			tx.commit();
		} catch (IllegalStateException ex) {
			getLogger().error("Error saving topic " + topic, ex);
			if (tx.isActive())
				tx.rollback();
			// should rethrow exception so the application knows
			// that save failed
			throw ex;
		}
	}

	/**
	 * Find the VoteTopic in the persistent storage by name.
	 * @param name - The String represents the name of the VoteTopic.
	 * @return VoteTopic object that represents the topic for judging in the competition.
	 */
	@Override
	public VoteTopic find(String title) {
		return em.find(VoteTopic.class, title);
	}
<<<<<<< HEAD

	/**
	 * Get the Logger of VoteTopicDaoJpa class.
	 * @return new Logger if it is null else return Logger.
	 */
=======
	
	@Override
	public boolean findTilte(String title){
		 if( em.find(VoteTopic.class, title) == null ) return false;
		 else return true;
	}
	
>>>>>>> Implement login
	private static Logger getLogger() {
		if (logger == null)
			logger = Logger.getLogger(VoteTopicDaoJpa.class);
		return logger;
	}

}