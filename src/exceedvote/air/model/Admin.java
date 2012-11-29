package exceedvote.air.model;

import java.util.ArrayList;
import java.util.List;

import exceedvote.air.persistence.BallotDao;
import exceedvote.air.persistence.CommitteeDao;
import exceedvote.air.persistence.DaoFactory;
import exceedvote.air.persistence.VoterDao;
import exceedvote.air.persistence.jpa.DaoFactoryJpa;

public class Admin {
	private List<Voter> list;
	private List<Committee> listCom = new ArrayList<Committee>();
	private Voter voter;
	private Committee committee;
	
	public boolean saveCommitee(String name, String password, int amountBallot, String type){
		committee = new Committee();
		committee.setName(name);
		committee.setPassword(password);
		committee.setballotLeft(amountBallot);
		committee.setType(type);
		committee.saveInfo(committee);
		CommitteeDao dao =  DaoFactory.getInstance().getCommitteeDao();
		return dao.saveCom(committee);	
	}
	
	
	public List<Committee> getCommittee(){
		CommitteeDao dao =  DaoFactory.getInstance().getCommitteeDao();
		if( dao.findAll() == null) return listCom;
		else return listCom = dao.findAll();
	} 
	
	public Committee getSingleCommittee(String name){
		CommitteeDao dao =  DaoFactory.getInstance().getCommitteeDao();
		committee = dao.findbyName(name);
		return committee;
	}
	
	public Voter getSingleVoter(String name){
		VoterDao dao = DaoFactory.getInstance().getVoterDao();
		voter = dao.findbyName(name);
		return voter;
	}
	
	public List<Voter> getVoter(){
		VoterDao dao = DaoFactory.getInstance().getVoterDao();
		list = dao.findAll();
		return list;
	}
	
	public boolean removeCommittee(String name){
		List<Ballot> ballot;
		BallotDao ballotDao = DaoFactory.getInstance().getBallotDao();
		CommitteeDao dao =  DaoFactory.getInstance().getCommitteeDao();
		committee = dao.findbyName(name);
		ballot = ballotDao.findAllOfSingleVoter(voter);
		if(ballot!=null){
			for(int i=0;i<ballot.size();i++)
			ballotDao.deleteBallot(ballot.get(i));
		}else return false;
		if(committee!=null) dao.remove(committee);
		else return false;
		return true;
	}
	
	public boolean removeVoter(String name){
		
		List<Ballot> ballot;
		BallotDao ballotDao = DaoFactory.getInstance().getBallotDao();
		
		VoterDao dao = DaoFactory.getInstance().getVoterDao();
		voter = dao.findbyName(name);
		ballot = ballotDao.findAllOfSingleVoter(voter);
		if(ballot!=null){
			for(int i=0;i<ballot.size();i++)
			ballotDao.deleteBallot(ballot.get(i));
		}
		else return false;
		if(voter!=null) dao.remove(voter);
		else return false;
		
		return true;
	}
}
