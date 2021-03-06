package exceedvote.air.persistence.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import org.apache.log4j.Logger;
import exceedvote.air.model.TeamDescription;
import exceedvote.air.model.Voter;
import exceedvote.air.persistence.TeamDescriptionDao;

public class TeamDescriptionDaoJpa  implements TeamDescriptionDao{
		
	private EntityManager em;
	private static Logger logger;

	public TeamDescriptionDaoJpa(EntityManager em) {
		super();
		this.em = em;
	}

	/**
	 * Save description of this team in database
	 * @param teamDes is the description of this team
	 * @return true if can save
	 */
	@Override
	public boolean save(TeamDescription teamDes) {
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.persist(teamDes);
			tx.commit();
		} catch (IllegalStateException ex) {
			getLogger().error("Error saving topic " + teamDes, ex);
			if (tx.isActive())
				tx.rollback();
			// should rethrow exception so the application knows
			// that save failed
			throw ex;

		}
		return true;
	}
	
	
	/**
	 * Find description by id number
	 * @param id is the identity number of this team
	 * @return TeamDescription that match with this id
	 */
	@Override
	public TeamDescription find(Integer id) {
		return em.find(TeamDescription.class, id);
	}
	
	
	/**
	 * Get the Logger of VoterDaoJpa class.
	 * @return new Logger if it is null else return Logger.
	 */
	private static Logger getLogger() {
		if (logger == null)
			logger = Logger.getLogger(VoterDaoJpa.class);
		return logger;
	}

}
