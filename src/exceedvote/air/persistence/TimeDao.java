package exceedvote.air.persistence;

import java.util.List;

import exceedvote.air.model.Time;


public interface TimeDao {

	public void save(Time time);

	List<Time> find();

	 


}
 