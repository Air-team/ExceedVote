package exceedvote.air.persistence;

import exceedvote.air.model.TeamDescription;

public interface TeamDescriptionDao {
	
	/**
	 * Save description of this team in database
	 * @param teamDes is the description of this team
	 * @return true if can save
	 */
	public boolean save(TeamDescription teamDes);

	/**
	 * Find description by id number
	 * @param id is the identity number of this team
	 * @return TeamDescription that match with this id
	 */
	public TeamDescription find(Integer id);

}
