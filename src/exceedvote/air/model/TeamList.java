package exceedvote.air.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


/**
 * TeamList represents the teams. TeamList contains the teams that can be voted in List. 
 * @author Prisa Dumrongsiri
 */
@Entity
public class TeamList implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	/** List of type Team, which keeps all the participated teams. */
	private List<Team> listTeam;
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