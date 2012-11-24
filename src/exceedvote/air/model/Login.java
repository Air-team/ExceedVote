package exceedvote.air.model;

import exceedvote.air.persistence.DaoFactory;
import exceedvote.air.persistence.VoterDao;

public class Login {
	
	private Voter voter;
	private String username;
	private String password;
	
	public Login(String username,String password){
		this.username = username;
		this.password = password;
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
}
