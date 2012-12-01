package exceedvote.air.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import exceedvote.air.persistence.DaoFactory;
import exceedvote.air.persistence.TeamDao;

public class Poll {
	
	private List<ArrayList> rank = new ArrayList<ArrayList>();
	private List<Team> TeamList = new ArrayList<Team>();
	private List<String> name = new ArrayList<String>();
	private List<Integer> Score = new ArrayList<Integer>();
	
	public Poll(){
		
	}
	
	public void getAllteam(){
		TeamDao dao = DaoFactory.getInstance().getTeamDao();
		TeamList = dao.findAll();
	
		
	}
	
	public List<String> getTeanName(){
		return name;
	}
	
	public List<ArrayList> totalInfo(){
		TeamDao dao = DaoFactory.getInstance().getTeamDao();
		TeamList = dao.findAll();

	
		for(int i=0;i<TeamList.size();i++){
			ArrayList<String> info = new ArrayList<String>();
			info.add(TeamList.get(i).getName());
			info.add(TeamList.get(i).totalScore()+"");
			rank.add(info);
			
		}
		
		return rank;
		
	}
	
	
}
