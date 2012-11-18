package exceedvote.air.persistence.jpa;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import exceed.air.persistence.DaoFactory;
import exceed.air.persistence.TeamDao;
import exceed.air.persistence.VoteTopicDao;
import exceedvote.air.model.Team;
import exceedvote.air.model.VoteTopic;

/**
 * Persistence operations for VoteTopic objects.
 * @author Negisu
 *
 */
public class TeamDaoJpa implements TeamDao {
	private EntityManager em;
	private static Logger logger;
	
	public TeamDaoJpa(EntityManager em) {
		super();
		this.em = em;
	}
	
	@Override
	public List<Team> findAll(){
		String queryStatement = "SELECT vt FROM VoteTopic vt";
		return em.createQuery(queryStatement).getResultList();
	}
	
	/* (non-Javadoc)
	 * @see exceedvote.air.persistence.jpa.VoteTopicDao#save(exceedvote.air.model.VoteTopic)
	 */
	@Override
	public void save(Team team) {
		EntityTransaction tx = em.getTransaction();
		try {
			
			tx.begin();
			em.persist(team);
			tx.commit();
		} catch (IllegalStateException ex) {
			getLogger().error("Error saving topic "+team, ex);
			if (tx.isActive()) tx.rollback();
			// should rethrow exception so the application knows
			// that save failed
			throw ex;
		}
	}
	
	private static Logger getLogger() {
		if (logger == null) logger = Logger.getLogger(VoteTopicDaoJpa.class);
		return logger;
	}
	

	
	
}