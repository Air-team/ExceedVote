package GUI;

import java.util.ArrayList;
import java.util.List;


/**
 * List of all teams
 * 
 * @author Prisa Dumrongsiri
 *
 */
public class TeamList {
	
	/** List of type Team, which keeps all the participate teams. */
	private List<Team> list;
	
	public TeamList()
	{
		list = new ArrayList<Team>();
	}
	
	/** 
	 * @return listTeam which contain all team that exist
	 */
	public List getTeam()
	{
		return list;
	}
	
	/**
	 * Add Team.
	 * @param team is object of class Team.
	 * @return true if team is added.
	 */
	public boolean addTeam(Team team)
	{
		if(!list.contains(team))
		{
			list.add(team);
			return true;
		}
		return false;
	}
	
	/** 
	 * To vote for a team.
	 * @param teamName is the team name that the voter want to vote 
	 */
	public void setBallot(String teamName)
	{
		for(int i=0;i<list.size();i++) 
		{
			if(((Team)(list.get(i))).getName().equals(teamName)) 
			{ 
				list.get(i).setScore(1);
			}
		}
	}
}
