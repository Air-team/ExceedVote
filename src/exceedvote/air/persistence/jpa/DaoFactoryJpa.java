package exceedvote.air.persistence.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;

import exceed.air.persistence.BallotDao;
import exceed.air.persistence.DaoFactory;
import exceed.air.persistence.TeamDao;
import exceed.air.persistence.VoteTopicDao;
import exceed.air.persistence.VoterDao;
/**
 * Factory for DAO that use JPA.
 * @author Negisu
 *
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
	
	@Override
	public VoterDao getVoterDao() {
		if(em!=null)
		if (voterDao == null) voterDao = new VoterDaoJpa(em);
		return voterDao;
	}
	
	@Override
	public BallotDao getBallotDao() {
		if (ballotDao == null) ballotDao = new BallotDaoJpa(em);
		return ballotDao;
	}



	@Override
	public TeamDao getTeamDao() {
		if (teamDao == null) teamDao = new TeamDaoJpa(em);
		return teamDao;
	}

}
