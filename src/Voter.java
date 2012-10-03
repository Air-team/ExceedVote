import java.util.List;

/**
 * Voter can vote in the limit quota.
 *
 * @author Busarat Jum
 *
 */
public class Voter {
	
	/** Name of voter */
	private String name;
	/** The ballot that the voter get from the registration */
	private Ballot ballot;
	/** The teamlist object for getting teams */
	private TeamList teamList;
	/** The list of Team */
	private List<Team> list;
	
	/** 
	 * Voter constructor.
	 * @param name of the voter. 
	 */
	public Voter(String name)
	{
		this.name = name;
		this.teamList = new TeamList();
		this.list = teamList.getTeam();
	}

	/**  
	 * Get name of the voter.
	 * @return name is the name of voter.
	 */
	public String getName()
	{
		return name;
	}
	
	/**  
	 * Return a list of all team that gets from teamlist.
	 * @return list is the list of all participate team.
	 */
	public List showTeam()
	{
		return list;
	}
	
	
}
