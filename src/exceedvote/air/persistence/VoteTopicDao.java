package exceedvote.air.persistence;

import java.util.List;

import exceedvote.air.model.VoteTopic;

/**
 * VoteTopic Data Access Object is a DAO for VoteTopic objects. This class represents 
 * the interface of the database that related to VoteTopic objects.
 * 
 * @author Air Team
 */
public interface VoteTopicDao {

	/**
	 * Save VoteTopic to the persistent storage.
	 * @param VoteTopic is object represents the topic for voting.
	 */
	public void save(VoteTopic topic);

	/**
	 * Find all the VoteTopic and return a list of all VoteTopics.
	 * @return a list of all VoteTopics that saved on the persistent storage.
	 */
	public abstract List<VoteTopic> findAll();

	/**
	 * Find the VoteTopic in the persistent storage by name.
	 * @param name - The String represents the name of the VoteTopic.
	 * @return VoteTopic object that represents the topic for judging in the competition.
	 */
	public abstract VoteTopic find(String name);

	
	public boolean findTilte(String title);

	public boolean remove(VoteTopic topic);

}