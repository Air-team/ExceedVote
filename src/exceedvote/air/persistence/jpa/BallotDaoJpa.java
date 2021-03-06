package exceedvote.air.persistence.jpa;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import org.apache.log4j.Logger;
import exceedvote.air.model.Ballot;
import exceedvote.air.model.Committee;
import exceedvote.air.model.Voter;
import exceedvote.air.persistence.BallotDao;

/**
 * Ballot Data Access Object is a DAO for ballot objects. 
 * @author Air Team
 */
public class BallotDaoJpa implements BallotDao {

	private EntityManager em;
	private static Logger logger;

	public BallotDaoJpa(EntityManager em) {
		super();
		this.em = em;
	}

	/**
	 * Save ballot to the persistent storage.
	 * @param ballot is object represents the vote.
	 */
	@Override
	public void save(Ballot ballot) {
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.persist(ballot);
			tx.commit();
		} catch (IllegalStateException ex) {
			getLogger().error("Error saving topic " + ballot, ex);
			if (tx.isActive())
				tx.rollback();
			// should rethrow exception so the application knows
			// that save failed
			throw ex;
		}

	}

	/**
	 * Find all the ballot and return a list of all ballots.
	 * @return a list of all ballots that saved on the persistent storage.
	 */
	@Override
	public List<Ballot> findAll() {
		String queryStatement = "SELECT vt FROM Ballot vt";
		return em.createQuery(queryStatement).getResultList();
	}
	

	/**
	 * Find a ballot by search for teamname, topic and voter from a list of all ballots.
	 * @param teamName - The name of the participated team.
	 * @param topic - The vote topic that that the user vote.
	 * @param committee - An object represents the user that relate to the ballot.
	 * @param allBallot - A list of all ballot for searching.
	 * @return a ballot from this user that match with the teamname and topic.
	 */
	@Override
	public Ballot findSingle(String teamName, String topic,Committee committee, List<Ballot> allBallot){
		for (int i = 0; i < allBallot.size(); i++) {
			Ballot bl = allBallot.get(i);
			if(bl.getCommittee() == null) continue;
			if ((bl.getTopic().equals(topic))
					&& (bl.getTeamName().equals(teamName))
					&& (bl.getCommittee().equals(committee))) {
				return bl;
			}
		}
		return null;
	}


	/**
	 * Find a ballot by search for teamname, topic and voter from a list of all ballots.
	 * @param teamName - The name of the participated team.
	 * @param topic - The vote topic that that the user vote.
	 * @param voter - An object represents the user that relate to the ballot.
	 * @param allBallot - A list of all ballot for searching.
	 * @return a ballot from this user that match with the teamname and topic.
	 */
	@Override
	public Ballot findSingle(String teamName, String topic, Voter voter,
			List<Ballot> allBallot) {
		for (int i = 0; i < allBallot.size(); i++) {
			Ballot bl = allBallot.get(i);
			if(bl.getVoter() == null) continue;
			if ((bl.getTopic().equals(topic))
					&& (bl.getTeamName().equals(teamName))
					&& (bl.getVoter().equals(voter))) {
				return bl;
			}
		}
		return null;
	}

	/**
	 * Delete a given ballot from the persistent storage.
	 * @param ballot - A ballot that you want to delete.
	 * @return false if the ballot doesn't contain in the persistent storage or it's null.
	 */
	@Override
	public boolean deleteBallot(Ballot ballot) {
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.remove(ballot);
			tx.commit();
			return true;
			// System.out.printf("Delete Success");
		} catch (Exception ex) {
			getLogger().error("Error Delete Ballot " + ex);
			if (tx.isActive()) {
				tx.rollback();
			}
			return false;
		}
	}


	/**
	 * Find History of the committee
	 * @param committee - Person who we want to see his history
	 * @return- List all of information Team,Topic,Date that committee vote
	 */

	@Override
	public List<ArrayList>  historyCom(Committee committee){
		List<Ballot> allBallot = this.findAll();
		List<ArrayList> his = new ArrayList<ArrayList>();
		List<String> top = new ArrayList<String>();
		List<String> tea = new ArrayList<String>();
		
		for (int i=0;i<allBallot.size();i++) {
			Ballot bl = allBallot.get(i);
			if(bl.getCommittee() == null) continue;
			if (  (bl.getCommittee().equals(committee) )){	
				int count =1;
				String team = bl.getTeamName();
				String topic = bl.getTopic();	
			
				
				for(int j=0;j<allBallot.size();j++){
					if( i!=j && team.equals(allBallot.get(j).getTeamName() ) &&  ( topic.equals(allBallot.get(j).getTopic() ) ) ){
						count++;
					}
				}
					
				ArrayList<String> info = new ArrayList<String>();
				info.add(bl.getTeamName());
				info.add(bl.getTopic());
				info.add(count+"");
				if(!his.contains(info)) his.add(info);
				
			}
		}
		
	
		
		return his;
	}
	
	/**
	 * Find History of the voter
	 * @param voter - Person who we want to see his history
	 * @return- List all of information Team,Topic,Date that vote vote
	 */
	@Override
	public List<ArrayList>  history(Voter voter){
		List<Ballot> allBallot = this.findAll();
		
		List<ArrayList> his = new ArrayList<ArrayList>();
		
		for (int i=0;i<allBallot.size();i++) {
			Ballot bl = allBallot.get(i);
			if(bl.getVoter() == null) continue;
			 if (  (bl.getVoter().equals(voter) )){				
				 int count =0;
					String team = bl.getTeamName();
					String topic = bl.getTopic();	
				
					
					for(int j=0;j<allBallot.size();j++){
						if( team.equals(allBallot.get(j).getTeamName() ) &&  ( topic.equals(allBallot.get(j).getTopic() ) ) ){
							count++;
						}
					}
						
					ArrayList<String> info = new ArrayList<String>();
					info.add(bl.getTeamName());
					info.add(bl.getTopic());
					info.add(count+"");
					if(!his.contains(info)) his.add(info);
					
				
			}
		}
		return his;
	}

	/**
	 * Find all ballot from this voter
	 * @param Voter is the user that want find Ballot information
	 */
	@Override
	public List<Ballot>  findAllOfSingleVoter(Voter voter){
		List<Ballot> allBallot = this.findAll();
		
		for (int i=0;i<allBallot.size();i++) {
			Ballot bl = allBallot.get(i);
			if(bl.getVoter() == null) continue;
			if (  (bl.getVoter().equals(voter) )){				
				allBallot.add(bl);
			}
		}
		
		return allBallot;
	}
	

	/**
	 * Get the Logger of BallotDaoJpa class.
	 * @return new Logger if it is null else return Logger.
	 */

	private static Logger getLogger() {
		if (logger == null)
			logger = Logger.getLogger(BallotDaoJpa.class);
		return logger;
	}

}
