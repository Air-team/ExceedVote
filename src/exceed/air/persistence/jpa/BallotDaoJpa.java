package exceed.air.persistence.jpa;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.apache.log4j.Logger;

import exceed.air.persistence.BallotDao;
import exceed.air.persistence.DaoFactory;
import exceed.air.persistence.VoteTopicDao;
import exceedvote.air.model.Ballot;
import exceedvote.air.model.TeamList;
import exceedvote.air.model.VoteTopic;
import exceedvote.air.model.Voter;

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
	
	private static Logger getLogger() {
		if (logger == null) logger = Logger.getLogger(VoterDaoJpa.class);
		return logger;
	}

}
