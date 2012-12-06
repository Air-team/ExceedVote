package exceedvote.air.persistence;

import java.util.List;

import exceedvote.air.model.Time;

/**
 * Interface Time data access object is about time that set for close voting system
 * @author AirTeam
 *
 */
public interface TimeDao {
	/**
	 * set new time in the database
	 * @param time that committee want set close vote
	 */
	public void save(Time time);
	/**
	 * Find all Time information
	 * @return List of all time that save in database
	 */
	public List<Time> find();

}
 