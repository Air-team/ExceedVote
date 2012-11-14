package exceedvote.air.persistence.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;

import exceedvote.air.persistence.DaoFactory;
import exceedvote.air.persistence.VoteTopicDao;
/**
 * Factory for DAO that use JPA.
 * @author Negisu
 *
 */
public class DaoFactoryJpa extends DaoFactory {
	private static final String PERSISTENCE_UNIT = "exceedvote.air";
	private static Logger logger = Logger.getLogger(DaoFactory.class); 
	private EntityManagerFactory emf;
	private EntityManager em;
	private VoteTopicDao voteTopicDao;
	
	
	public DaoFactoryJpa() {
		try {
			emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
			em = emf.createEntityManager();
		} catch (Exception e) {
			logger.fatal("Error initializing JPA", e);
			//e.printStackTrace();
		}
	}


	@Override
	public VoteTopicDao getVoteTopicDao() {
		if (voteTopicDao == null) voteTopicDao = new VoteTopicDaoJpa(em);
		return voteTopicDao;
	}

}
