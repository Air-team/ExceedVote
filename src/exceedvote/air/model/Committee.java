package exceedvote.air.model;

import java.util.Calendar;
import java.util.Date;

import exceedvote.air.persistence.DaoFactory;
import exceedvote.air.persistence.TeamDao;
import exceedvote.air.persistence.VoteTopicDao;

public class Committee {
	private String name;
	private String teamName;
	private String topicName;
	// private Calendar calender = Calendar.getInstance();
	private Date date;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTeamName() {
		return teamName;
	}

	public boolean setTeamName(String teamName) {
		this.teamName = teamName;
		TeamDescription durianDes = new TeamDescription("name: Durian");
		Team team = new Team(teamName, durianDes);
		TeamDao teamDao = DaoFactory.getInstance().getTeamDao();
		if (teamDao.findSingle(teamName) == null)
			return false;
		else {
			teamDao.save(team);
			return true;
		}
	}

	public String getTopicName() {
		return topicName;
	}

	public boolean setTopicName(String topicName) {
		this.topicName = topicName;
		VoteTopic voteTopic = new VoteTopic(topicName);
		VoteTopicDao dao = DaoFactory.getInstance().getVoteTopicDao();
		if (dao.findTilte(topicName) == false)
			return false;
		else {
			dao.save(voteTopic);
			return true;
		}
	}

	public void setTime(String day, String month, String year, String hours,
			String mins, String seconds) {
		int d = 0, m = 0, y = 0, h, min = 0, second = 0;
		if (month.equalsIgnoreCase("jan"))
			m = 0;
		else if (month.equalsIgnoreCase("feb"))
			m = 1;
		else if (month.equalsIgnoreCase("mar"))
			m = 2;
		else if (month.equalsIgnoreCase("apr"))
			m = 3;
		else if (month.equalsIgnoreCase("may"))
			m = 4;
		else if (month.equalsIgnoreCase("june"))
			m = 5;
		else if (month.equalsIgnoreCase("july"))
			m = 6;
		else if (month.equalsIgnoreCase("aug"))
			m = 7;
		else if (month.equalsIgnoreCase("sept"))
			m = 8;
		else if (month.equalsIgnoreCase("oct"))
			m = 9;
		else if (month.equalsIgnoreCase("nov"))
			m = 10;
		else if (month.equalsIgnoreCase("dec"))
			m = 11;

		d = Integer.parseInt(day);
		y = Integer.parseInt(year);
		h = Integer.parseInt(hours);
		min = Integer.parseInt(mins);
		second = Integer.parseInt(seconds);
		date = new Date(y - 1900, m, d, h, min, second);

	}

}
