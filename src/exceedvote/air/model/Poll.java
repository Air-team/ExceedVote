package exceedvote.air.model;

import java.util.ArrayList;
import java.util.List;

import exceedvote.air.persistence.DaoFactory;
import exceedvote.air.persistence.TeamDao;

/**
 * Poll represents the final result that has already processed and made the ranking.
 * 
 * @author Air Team
 */
public class Poll {

	private List<ArrayList> rank;
	private List<Team> TeamList;

	/**
	 * Initialize Poll.
	 */
	public Poll() {
		rank = new ArrayList<ArrayList>();
		TeamList = new ArrayList<Team>();
	}

	/**
	 * Return List of result informations that have already ordered.
	 * @return list of the result informations.
	 */
	public List<ArrayList> totalInfo() {
		TeamDao dao = DaoFactory.getInstance().getTeamDao();
		TeamList = dao.findAll();

		for (int i = 0; i < TeamList.size(); i++) {
			ArrayList info = new ArrayList();
			info.add(TeamList.get(i).getName());
			info.add(TeamList.get(i).totalScore() + "");
			rank.add(info);
		}
		return rank;
	}

}
