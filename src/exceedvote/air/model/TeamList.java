package exceedvote.air.model;

import java.util.ArrayList;
import java.util.List;


/**
 * TeamList represents the teams. TeamList contains the teams that can be voted in List. 
 * @author Prisa Dumrongsiri
 */
public class TeamList {

	/** List of type Team, which keeps all the participated teams. */
	private List<Team> list;

	/**
	 * TeamList constructor initializes the list of type Team.
	 */
	public TeamList()
	{
		list = new ArrayList<Team>();
	}

	/** 
	 * Get all the Teams.
	 * @return list The List which contains all teams that exist.
	 */
	public List<Team> getTeam()
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