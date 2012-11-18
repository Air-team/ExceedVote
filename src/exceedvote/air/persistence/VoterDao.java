package exceedvote.air.persistence;

import java.util.List;
import exceedvote.air.model.Voter;

public interface VoterDao {
	public void save(Voter voter);
	public abstract List<Voter> findAll();
	public Voter find(Integer id);
	
}
