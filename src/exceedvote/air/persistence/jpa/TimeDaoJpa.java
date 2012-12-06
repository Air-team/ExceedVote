package exceedvote.air.persistence.jpa;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.apache.log4j.Logger;

import exceedvote.air.persistence.TimeDao;

/**
 *Implementation JPA, Time data access object is about time that set for close voting system
 * @author AirTeam
 *
 */
public class TimeDaoJpa implements TimeDao{
	private EntityManager em;
	private static Logger logger;

	public TimeDaoJpa(EntityManager em) {
		super();
		this.em = em;
	}
	
	/**
	 * set new time in the database
	 * @param time that committee want set close vote
	 */
		@Override
		public void save(exceedvote.air.model.Time time) {
			EntityTransaction tx = em.getTransaction();

			try {
				tx.begin();
				em.persist(time);
				tx.commit();
			} catch (IllegalStateException ex) {
				getLogger().error("Error saving topic " + time, ex);
				if (tx.isActive())
					tx.rollback();
				// should rethrow exception so the application knows
				// that save failed
				throw ex;
			}
		}
		
		/**
		 * Find all Time information
		 * @return List of all time that save in database
		 */
		@Override
		public List<exceedvote.air.model.Time> find() {
			String queryStatement = "SELECT vt FROM Time vt";
			return em.createQuery(queryStatement).getResultList();
		}

		
		/**
		 * Get the Logger of TimeDaoJpa class.
		 * @return new Logger if it is null else return Logger.
		 */
		private static Logger getLogger() {
			if (logger == null)
				logger = Logger.getLogger(TeamDaoJpa.class);
			return logger;
		}

		

		
	
}	
