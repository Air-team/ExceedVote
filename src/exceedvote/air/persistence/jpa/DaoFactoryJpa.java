package exceedvote.air.persistence.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;

import exceedvote.air.persistence.BallotDao;
import exceedvote.air.persistence.DaoFactory;
import exceedvote.air.persistence.TeamDao;
import exceedvote.air.persistence.VoteTopicDao;
import exceedvote.air.persistence.VoterDao;

/**
 * DaoFactoryJpa extends abstract DaoFactory.
 * DaoFactoryJpa is used for getting the Dao objects to access to others Dao classes.
 * @author Air Team
 */
public class DaoFactoryJpa extends DaoFactory {
	private static final String PERSISTENCE_UNIT = "exceedvote";
	private static Logger logger = Logger.getLogger(DaoFactory.class);
	private EntityManagerFactory emf;
	private EntityManager em;
	private VoteTopicDao voteTopicDao;
	private VoterDao voterDao;
	private BallotDao ballotDao;
	private TeamDao teamDao;

	/**
	 * constructor for DaoFactoryJpa.
	 */
	public DaoFactoryJpa() {
		try {
			emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
			em = emf.createEntityManager();
		} catch (Exception e) {
			logger.fatal("Error initializing JPA", e);
			// e.printStackTrace();
		}
	}

	/**
	 * Get an instance of the DAO for VoteTopics.
	 * @return a new VoteTopicDao if VoteTopicDao hasn't been created 
	 * else return this VoteTopicDao object.
	 */
	@Override
	public VoteTopicDao getVoteTopicDao() {
		if (voteTopicDao == null)
			voteTopicDao = new VoteTopicDaoJpa(em);
		return voteTopicDao;
	}

	/**
	 * Get an instance of the DAO for Voter.
	 * @return a new VoterDao if VoterDao hasn't been created 
	 * else return this VoterDao object.	
	 */
	@Override
	public VoterDao getVoterDao() {
		if (em != null)
			if (voterDao == null)
				voterDao = new VoterDaoJpa(em);
		return voterDao;
	}

	/**
	 * Get an instance of the DAO for Ballot.
	 * @return a new BallotDao if BallotDao hasn't been created 
	 * else return this BallotDao object.	
	 */
	@Override
	public BallotDao getBallotDao() {
		if (ballotDao == null)
			ballotDao = new BallotDaoJpa(em);
		return ballotDao;
	}

	/**
	 * Get an instance of the DAO for Team.
	 * @return a new TeamDao if TeamDao hasn't been created 
	 * else return this TeamDao object.	
	 */
	@Override
	public TeamDao getTeamDao() {
		if (teamDao == null)
			teamDao = new TeamDaoJpa(em);
		return teamDao;
	}

}
