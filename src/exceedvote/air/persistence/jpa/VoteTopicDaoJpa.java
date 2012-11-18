package exceedvote.air.persistence.jpa;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import org.apache.log4j.Logger;
import exceedvote.air.model.VoteTopic;
import exceedvote.air.persistence.VoteTopicDao;

/**
 * Persistence operations for VoteTopic objects.
 * @author Negisu
 *
 */
public class VoteTopicDaoJpa implements VoteTopicDao {
	private EntityManager em;
	private static Logger logger;
	
	public VoteTopicDaoJpa(EntityManager em) {
		super();
		this.em = em;
	}
	
	@Override
	public List<VoteTopic> findAll(){
		String queryStatement = "SELECT vt FROM VoteTopic vt";
		return em.createQuery(queryStatement).getResultList();
	}
	
	/* (non-Javadoc)
	 * @see exceedvote.air.persistence.jpa.VoteTopicDao#save(exceedvote.air.model.VoteTopic)
	 */
	@Override
	public void save(VoteTopic topic) {
		EntityTransaction tx = em.getTransaction();
	
		try {
			tx.begin();
			em.persist(topic);
			tx.commit();
		} catch (IllegalStateException ex) {
			getLogger().error("Error saving topic "+topic, ex);
			if (tx.isActive()) tx.rollback();
			// should rethrow exception so the application knows
			// that save failed
			throw ex;
		}
	}
	@Override
	public VoteTopic find(String title){
		return  em.find(VoteTopic.class, title);
	}
	private static Logger getLogger() {
		if (logger == null) logger = Logger.getLogger(VoteTopicDaoJpa.class);
		return logger;
	}

	
}