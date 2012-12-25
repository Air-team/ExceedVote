package exceedvote.air.model;

import java.util.ArrayList;
import java.util.List;

import exceedvote.air.persistence.BallotDao;
import exceedvote.air.persistence.CommitteeDao;
import exceedvote.air.persistence.DaoFactory;
import exceedvote.air.persistence.VoterDao;

/**
 * Admin have job to decide who will be the committee by choose from the voters.
 * @author Air Team
 */
public class Admin {
	
	private List<Voter> list;
	private List<Committee> listCom = new ArrayList<Committee>();
	private Voter voter;
	private Committee committee;

	/**
	 * Create committee and save to the database.
	 * @param name is the username of the committee
	 * @param password is the password of this committee.
	 * @param amountBallot is amount of ballot that this committee has.
	 * @param type is the type of the committee.
	 * @return true if the committee is saved to the database.
	 */
	public boolean saveCommitee(String name, String password, int amountBallot, String type) {
		committee = new Committee();
		committee.setName(name);
		committee.setPassword(password);
		committee.setballotLeft(amountBallot);
		committee.setType(type);
		committee.saveInfo(committee);
		CommitteeDao dao = DaoFactory.getInstance().getCommitteeDao();
		return dao.saveCom(committee);
	}

	/**
	 * Return the list of all committees in this competition.
	 * @return list of all committees.
	 */
	public List<Committee> getCommittee() {
		CommitteeDao dao = DaoFactory.getInstance().getCommitteeDao();
		if (dao.findAll() == null)
			return listCom;
		else
			return listCom = dao.findAll();
	}

	/**
	 * Return the specific committee.
	 * @param name is name of the committee.
	 * @return the specific Committee object.
	 */
	public Committee getSingleCommittee(String name) {
		CommitteeDao dao = DaoFactory.getInstance().getCommitteeDao();
		committee = dao.findbyName(name);
		return committee;
	}

	/**
	 * Return the specific Voter.
	 * @param name is name of the voter.
	 * @return the specific Voter object.
	 */
	public Voter getSingleVoter(String name) {
		VoterDao dao = DaoFactory.getInstance().getVoterDao();
		voter = dao.findbyName(name);
		return voter;
	}
	
	/**
	 * Return the list of all voters in this competition.
	 * @return list of all voters.
	 */
	public List<Voter> getVoter() {
		VoterDao dao = DaoFactory.getInstance().getVoterDao();
		list = dao.findAll();
		return list;
	}

	/**
	 * Remove the specific Committee from the competition.
	 * @param name the name of the committee
	 * @return true if successfully delete the committee.
	 */
	public boolean removeCommittee(String name) {
		List<Ballot> ballot;
		BallotDao ballotDao = DaoFactory.getInstance().getBallotDao();
		CommitteeDao dao = DaoFactory.getInstance().getCommitteeDao();
		committee = dao.findbyName(name);
		ballot = ballotDao.findAllOfSingleVoter(voter);
		if (ballot != null) {
			for (int i = 0; i < ballot.size(); i++)
				ballotDao.deleteBallot(ballot.get(i));
		} else
			return false;
		if (committee != null)
			dao.remove(committee);
		else
			return false;
		return true;
	}

	/**
	 * Remove the specific Voter from the competition.
	 * @param name the name of the voter
	 * @return true if successfully delete the voter.
	 */
	public boolean removeVoter(String name) {

		List<Ballot> ballot;
		BallotDao ballotDao = DaoFactory.getInstance().getBallotDao();

		VoterDao dao = DaoFactory.getInstance().getVoterDao();
		voter = dao.findbyName(name);
		ballot = ballotDao.findAllOfSingleVoter(voter);
		if (ballot != null) {
			for (int i = 0; i < ballot.size(); i++)
				ballotDao.deleteBallot(ballot.get(i));
		} else
			return false;
		if (voter != null)
			dao.remove(voter);
		else
			return false;

		return true;
	}
}
