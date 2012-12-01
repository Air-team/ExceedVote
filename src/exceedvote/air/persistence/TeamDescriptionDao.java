package exceedvote.air.persistence;

import exceedvote.air.model.TeamDescription;

public interface TeamDescriptionDao {

	public boolean save(TeamDescription teamDes);

	public TeamDescription find(Integer id);

}
