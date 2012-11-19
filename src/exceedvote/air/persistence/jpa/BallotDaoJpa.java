package exceedvote.air.persistence.jpa;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import org.apache.log4j.Logger;
import exceedvote.air.model.Ballot;
import exceedvote.air.model.Voter;
import exceedvote.air.persistence.BallotDao;


public class BallotDaoJpa implements BallotDao {

	private EntityManager em;
	private static Logger logger;
	
	public BallotDaoJpa(EntityManager em) {
		super();
		this.em = em;
	}
	
	@Override
	public void save(Ballot ballot) {
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.persist(ballot);
			tx.commit();
		} catch (IllegalStateException ex) {
			getLogger().error("Error saving topic "+ballot, ex);
			if (tx.isActive()) tx.rollback();
			// should rethrow exception so the application knows
			// that save failed
			throw ex;
		}
		
	}
	
	@Override
	public List<Ballot> findAll(){
		String queryStatement = "SELECT vt FROM Ballot vt";
		return em.createQuery(queryStatement).getResultList();
	}
	/**
	 * Find Single Ballot.
	 */
	@Override
	public Ballot findSingle(String teamName, String topic, Voter voter, List<Ballot> allBallot){
		for (int i=0;i<allBallot.size();i++) {
			Ballot bl = allBallot.get(i);
			if ( (bl.getTopic().equals(topic)) && (bl.getTeamName().equals(teamName))&& (bl.getVoter().equals(voter) )){return bl;}
		}
		return null;
	}

	/**
	 * Remove Ballot.
	 */
	@Override
	public boolean deleteBallot(Ballot ballot) {
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.remove(ballot);
			tx.commit();
			return true;
			//System.out.printf("Delete Success");
		} catch (Exception ex) {
			getLogger().error("Error Delete Ballot "+ ex);
			if (tx.isActive()) { tx.rollback(); }
			return false;
		}
		
	}
	

	
	private static Logger getLogger() {
		if (logger == null) logger = Logger.getLogger(VoterDaoJpa.class);
		return logger;
	}

}
