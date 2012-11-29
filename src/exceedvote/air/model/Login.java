package exceedvote.air.model;

import exceedvote.air.persistence.CommitteeDao;
import exceedvote.air.persistence.DaoFactory;
import exceedvote.air.persistence.VoterDao;

public class Login {
	
	private Voter voter;
	private Committee committee;
	private String username;
	private String password;
	
	public Login(String username,String password){
		this.username = username;
		this.password = password;
	}
	
	public boolean isAdmin(){
		if(username.equals("root") && password.equals("root")) return true;
		return false;
	}
	public Voter getVoter(){
		return voter;
	}
	
	public boolean hasVoter(){
		VoterDao dao = DaoFactory.getInstance().getVoterDao();
		voter = dao.findSingle(username, password);
		if(voter == null) return false;
		return true;
	}
	
	public Committee getCommittee(){
		return committee;
	}
	
	public boolean hasCommittee(){
		CommitteeDao dao = DaoFactory.getInstance().getCommitteeDao();
		committee = dao.findSingle(username, password);
//		System.out.println(committee.getName());
		if(committee == null) return false;
		return true;
	}
}
