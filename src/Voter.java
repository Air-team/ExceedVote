import java.util.List;

/**
 * 
 * @author Busarat Jum
 *
 * Voter Class 
 */
public class Voter {
	
	private String name;
	private Ballot ballot;
	private TeamList teamList;
	private List<Team> list;
	
	
	public Voter(String name)
	{
		this.name = name;
		this.teamList = new TeamList();
		this.list = teamList.getTeam();
	}

	public String getName()
	{
		return this.name;
	}
	
	
	public List showTeam(){
		return list;
	}
	
	
}
