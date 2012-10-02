import java.util.ArrayList;
import java.util.List;

/**
 * List of all teams
 */

/**
 * @author PrisaDumrongsiri
 *
 */
public class TeamList {
	
	private List<Team> listTeam;
	private Team team;
	
	public TeamList(){
		listTeam = new ArrayList<Team>();
	
	}
	
	
	/** 
	 * @return listTeam which contain all team that exist
	 */
	public List getTeam(){
		return listTeam;
	}
	
	public void addTeam(Team team){
		 listTeam.add(team);
	}
	
	
	/** 
	 * 
	 * @param teamName is the team name that the voter want to vote 
	 */
	public void setBallot(String teamName){
		try{
		for(int i=0;i<listTeam.size();i++) {
			if( ( (Team)(listTeam.get(i)) ).getName().equals(teamName)) { 
				listTeam.get(i).setScore(1);
			}
		}
		} catch(NullPointerException ne)
		{
			
			System.out.println("setballot");
		}
		
	}
	
	
	
	
	
	
}
