package exceedvote.air.persistence;

import exceedvote.air.persistence.jpa.DaoFactoryJpa;

/**
 * Abstract factory for instantiating a concrete DAO factory and specifying
 * required methods for DAO factory.
 * 
 * @author Air Team
 */
public abstract class DaoFactory {

	/** this is the single concrete factory */
	private static DaoFactory factory;

	/**
	 * Get the instance of this DaoFactory.
	 * @return a new DaoFactory if DaoFactory hasn't been created
	 * return this DaoFactory object.
	 */
	public static DaoFactory getInstance() {
		if (factory == null)
			factory = new DaoFactoryJpa();
		return factory;
	}

	/**
	 * Get an instance of the DAO for VoteTopics.
	 * @return a new VoteTopicDao if VoteTopicDao hasn't been created 
	 * else return this VoteTopicDao object.
	 */
	public abstract VoteTopicDao getVoteTopicDao();

	/**
	 * Get an instance of the DAO for Voter.
	 * @return a new VoterDao if VoterDao hasn't been created 
	 * else return this VoterDao object.	
	 */
	public abstract VoterDao getVoterDao();

	/**
	 * Get an instance of the DAO for Ballot.
	 * @return a new BallotDao if BallotDao hasn't been created 
	 * else return this BallotDao object.	
	 */
	public abstract BallotDao getBallotDao();

	/**
	 * Get an instance of the DAO for Team.
	 * @return a new TeamDao if TeamDao hasn't been created 
	 * else return this TeamDao object.	
	 */
	public abstract TeamDao getTeamDao();
}
