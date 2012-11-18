package exceedvote.air.persistence.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.apache.log4j.Logger;


import exceedvote.air.model.Voter;

import exceedvote.air.persistence.VoterDao;

/**
 * Persistence operations for VoteTopic objects.
 * @author Negisu
 *
 */
public class VoterDaoJpa implements VoterDao {
	private EntityManager em;
	private static Logger logger;
	
	public VoterDaoJpa(EntityManager em) {
		super();
		this.em = em;
		
	}
	
	@Override
	public void save(Voter voter) {
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.persist(voter);
			tx.commit();
		} catch (IllegalStateException ex) {
			getLogger().error("Error saving topic "+voter, ex);
			if (tx.isActive()) tx.rollback();
			// should rethrow exception so the application knows
			// that save failed
			throw ex;
		}
	}
	
	@Override
	public List<Voter> findAll(){
		String queryStatement = "SELECT vt FROM Voter vt";
		return em.createQuery(queryStatement).getResultList();
	}
	
	private static Logger getLogger() {
		if (logger == null) logger = Logger.getLogger(VoterDaoJpa.class);
		return logger;
	}
	@Override
	public Voter find(Integer id){
		return em.find(Voter.class, id);
	}

}