package exceedvote.air.persistence.jpa;

import java.util.List;

import javax.persistence.EntityTransaction;

import org.apache.log4j.Logger;

import exceedvote.air.model.Committee;
import exceedvote.air.persistence.CommitteeDao;
import javax.persistence.EntityManager;

/**
 * Committee Data Access Object is a DAO for committee objects.
 * @author Air Team
 */

public class CommitteeDaoJpa implements CommitteeDao {
	private EntityManager em;
	private static Logger logger;

	public CommitteeDaoJpa(EntityManager em) {
		super();
		this.em = em;
	}
	/**
	 * Save committee in the database
	 * @param committee is the person want to save in the database
	 * @return true if can save
	 */
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
	
	/**
	 * Find committee by name
	 * @param name is identity of that committee
	 * @return Committee that match with name
	 */
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
	

	/**
	 * Find all Committee in the database
	 * @return List all of Committee
	 */
	@Override
	public List<Committee> findAll() {
		String queryStatement = "SELECT c FROM Committee c";
		return em.createQuery(queryStatement).getResultList();
	}
	
	/**
	 * Find committee by name and password
	 * @param username is name or identity of this committee
	 * @param password is security code of this committee
	 * @return Committee that match with name and password
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
	
	/**
	 * Remove committee out of the database
	 * @param committee is the person out of the database
	 */
	@Override
	public void remove(Committee committee){
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.remove(committee);
			tx.commit();
			 System.out.printf("Delete Success");
			
		} catch (Exception ex) {
			getLogger().error("Error Delete committee " + ex);
			if (tx.isActive()) {
				tx.rollback();
			}
			
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
