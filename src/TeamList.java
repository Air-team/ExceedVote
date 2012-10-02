import java.util.ArrayList;
import java.util.List;

/**
 * List of all teams
 */

/**
 * @author Prisa Dumrongsiri
 *
 */
public class TeamList {
	
	private List<Team> list;
	private Team team;
	
	public TeamList(){
		list = new ArrayList<Team>();
	
	}
	
	
	/** 
	 * @return listTeam which contain all team that exist
	 */
	public List getTeam(){
		return list;
	}
	
	public void addTeam(Team team){
		 list.add(team);
	}
	
	
	/** 
	 * 
	 * @param teamName is the team name that the voter want to vote 
	 */
	public void setBallot(String teamName){
		try{
		for(int i=0;i<list.size();i++) {
			if( ( (Team)(list.get(i)) ).getName().equals(teamName)) { 
				list.get(i).setScore(1);
			}
		}
		} catch(NullPointerException ne)
		{
			
			System.out.println("setballot");
		}
		
	}
	
	
	
	
	
	
}
