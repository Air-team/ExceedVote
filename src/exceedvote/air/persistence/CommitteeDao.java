package exceedvote.air.persistence;

import java.util.List;
import exceedvote.air.model.Committee;

/**
 * Committee Data Access Object is a DAO for committee objects. This class represents 
 * the interface of the database that related to committee objects.
 * 
 * @author Air Team
 */
public interface CommitteeDao {
	/**
	 * Save committee in the database
	 * @param committee is the person want to save in the database
	 * @return true if can save
	 */
	public boolean saveCom(Committee committee);
	
	
	/**
	 * Find all Committee in the database
	 * @return List all of Committee
	 */
	public List<Committee> findAll();
	
	/**
	 * Find committee by name
	 * @param name is identity of that committee
	 * @return Committee that match with name
	 */
	public Committee findbyName(String name);
	
	/**
	 * Remove committee out of database
	 * @param committee
	 * @return true if can remove it
	 */
	public void remove(Committee committee);
	
	/**
	 * Find committee by name and password
	 * @param username is name or identity of this committee
	 * @param password is security code of this committee
	 * @return Committee that match with name and password
	 */
	public Committee findSingle(String username, String password);
}
