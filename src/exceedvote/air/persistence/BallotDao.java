package exceedvote.air.persistence;

import java.util.List;

import exceedvote.air.model.Ballot;
import exceedvote.air.model.Voter;

public interface BallotDao {
	public void save(Ballot ballot);

	public abstract List<Ballot> findAll();

	public void remove(Ballot ballot);

	public Ballot findSingle(String teamName, String topic, Voter voter,
			List<Ballot> allBallot);
}
