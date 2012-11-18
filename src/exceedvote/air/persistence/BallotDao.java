package exceedvote.air.persistence;

import java.util.List;

import exceedvote.air.model.Ballot;

public interface BallotDao {
	public void save(Ballot ballot);

	public abstract List<Ballot> findAll();

	public void remove(Ballot ballot);
}
