package exceedvote.air.persistence;

import java.util.List;
import exceedvote.air.model.Committee;


public interface CommitteeDao {
	public boolean saveCom(Committee committee);
	public void removeCom(Committee committee);
	public abstract List<Committee> findAll();
	public Committee findbyName(String name);
	public boolean remove(Committee committee);
	public Committee findSingle(String username, String password);
}
