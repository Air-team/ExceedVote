package exceed.air.persistence;

import exceedvote.air.persistence.jpa.DaoFactoryJpa;

/**
 * A factory for instantiating a concrete DAO factory
 * and specifying required methods for DAO factory.
 * This is an abstract factory.
 * @author Chuphan Tharacheewin
 *
 */
public abstract class DaoFactory {
	/** this is the single concrete factory */
	private static DaoFactory factory;
	
	/**
	 * 
	 * @return
	 */
	public static DaoFactory getInstance() {
	
		if (factory == null) init();
		return factory;
	}
	
	/** 
	 * initialize a DAO factory.
	 */
	private static void init() {
		//TODO use a configuration parameter to specify which DAO factory
		factory = new DaoFactoryJpa();
	
	}
	
	/**
	 * Get an instance of the DAO for VoteTopics
	 * @return a VoteTopicDao
	 */
	public abstract VoteTopicDao getVoteTopicDao();
	public abstract VoterDao getVoterDao();
	public abstract BallotDao getBallotDao();
	public abstract TeamDao getTeamDao();
}
