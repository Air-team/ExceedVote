package exceedvote.air.model;

import java.util.ArrayList;
import java.util.List;


/**
 * TeamList represents the teams. TeamList contains the teams that can be voted in List. 
 * @author Prisa Dumrongsiri
 */
public class TeamList {

	/** List of type Team, which keeps all the participated teams. */
	private List<Team> listTeam;
	 private Team team;
	/**
	 * TeamList constructor initializes the list of type Team.
	 */
	public TeamList()
	{
		listTeam = new ArrayList<Team>();
	}

	/** 
	 * Get all the Teams.
	 * @return list The List which contains all teams that exist.
	 */
	public List<Team> getTeam()
	{
		return listTeam;
	}

	/**
	 * Add Team.
	 * @param team is object of class Team.
	 * @return true if team is added.
	 */
	public boolean addTeam(Team team)
	{
		if(!listTeam.contains(team))
		{
			listTeam.add(team);
			return true;
		}
		return false;
	}

	/** 
	 * To vote for a team.
	 * @param teamName is the team name that the voter want to vote 
	 */
	public void setBallot(String teamName,String typeTeam)
	{
		for(int i=0;i<listTeam.size();i++) 
		{
			if((listTeam.get(i)).getName().equals(teamName)) 
			{ 
				listTeam.get(i).setScore(1,typeTeam);
			}
		}
	}
	
	public String[] getTeamNames()
    {    
        String[] names = new String[listTeam.size()];
        for(int i = 0 ; i < listTeam.size(); i++)
        {
            names[i] = listTeam.get(i).getName();
        }
        return names;
    }
}