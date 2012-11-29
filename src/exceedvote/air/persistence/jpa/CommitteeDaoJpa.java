package exceedvote.air.persistence.jpa;

import java.util.List;

import javax.persistence.EntityTransaction;

import org.apache.log4j.Logger;

import exceedvote.air.model.Committee;
import exceedvote.air.persistence.CommitteeDao;
import javax.persistence.EntityManager;



public class CommitteeDaoJpa implements CommitteeDao {
	private EntityManager em;
	private static Logger logger;

	public CommitteeDaoJpa(EntityManager em) {
		super();
		this.em = em;
	}
	
	@Override
	public boolean saveCom(Committee committee) {
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.persist(committee);
			tx.commit();
		} catch (IllegalStateException ex) {
			getLogger().error("Error saving Committee " + committee, ex);
			if (tx.isActive())
				tx.rollback();
			// should rethrow exception so the application knows
			// that save failed
			throw ex;

		}
		return true;
		
	}

	@Override
	public Committee findbyName(String username) {
		List<Committee> allCom = findAll();
		for (int i = 0; i < allCom.size(); i++) {
			Committee com = allCom.get(i);
			if (com.getName().equals(username)) {
				return com;
			}
		}
		return null;
	}
	@Override
	public void removeCom(Committee committee) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Committee> findAll() {
		String queryStatement = "SELECT c FROM Committee c";
		return em.createQuery(queryStatement).getResultList();
	}
	
	/**
	 * Find user in database
	 */
	@Override
	public Committee findSingle(String username, String password) {
		List<Committee> allCommittees = findAll();
		
		for (int i = 0; i < allCommittees.size(); i++) {
			Committee committee = allCommittees.get(i);
			if (committee.getName().equals(username)
					&& committee.getPassword().equals(password)) {
				return committee;
			}
		}
		return null;
	}
	
	@Override
	public boolean remove(Committee committee){
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.remove(committee);
			tx.commit();
			 System.out.printf("Delete Success");
			return true;
			
		} catch (Exception ex) {
			getLogger().error("Error Delete committee " + ex);
			if (tx.isActive()) {
				tx.rollback();
			}
			return false;
		}
	}

	
	/**
	 * Get the Logger of CommitteeDaoJpa class.
	 * 
	 * @return new Logger if it is null else return Logger.
	 */
	private static Logger getLogger() {
		if (logger == null)
			logger = Logger.getLogger(CommitteeDaoJpa.class);
		return logger;
	}


}
