package exceedvote.air.persistence;

import java.util.List;

import exceedvote.air.model.VoteTopic;

public interface VoteTopicDao {

	public void save(VoteTopic topic);

	public abstract List<VoteTopic> findAll();

}