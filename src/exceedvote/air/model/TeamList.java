package exceedvote.air.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * TeamList represents the list of all teams in the competition. TeamList
 * contains the teams that can be voted.
 * 
 * @author Air Team
 * @version 2012.11.20
 */
@Entity
public class TeamList implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	/** List of type Team, which keeps all the participated teams. */
	private List<Team> listTeam;

	/**
	 * TeamList constructor initializes the list of Team.
	 */
	public TeamList() {
		listTeam = new ArrayList<Team>();
	}

	/**
	 * Return a list of all teams.
	 * 
	 * @return The List which contains all teams in the competition.
	 */
	public List<Team> getTeam() {
		return listTeam;
	}

	/**
	 * Add Team.
	 * 
	 * @param team
	 *            is object of class Team.
	 * @return true if team is added.
	 */
	/**
	 * Add Team to the List
	 * 
	 * @param team
	 *            - Team that is in the competition.
	 * @return true if the Team was added.
	 */
	public boolean addTeam(Team team) {
		if (!listTeam.contains(team)) {
			return listTeam.add(team);
		}
		return false;
	}

	/**
	 * Return a list of team name.
	 * 
	 * @return List of String represents all team names.
	 */
	public String[] getTeamNames() {
		String[] names = new String[listTeam.size()];
		for (int i = 0; i < listTeam.size(); i++) {
			names[i] = listTeam.get(i).getName();
		}
		return names;
	}

}