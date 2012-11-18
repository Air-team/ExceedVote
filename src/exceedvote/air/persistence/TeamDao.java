package exceedvote.air.persistence;

import java.util.List;

import exceedvote.air.model.Team;

public interface TeamDao {

	public void save(Team team);
	public abstract List<Team> findAll();


}