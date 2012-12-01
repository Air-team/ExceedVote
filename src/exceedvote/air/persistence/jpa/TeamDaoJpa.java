package exceedvote.air.persistence.jpa;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import org.apache.log4j.Logger;

import exceedvote.air.model.Team;
import exceedvote.air.model.VoteTopic;


import exceedvote.air.persistence.TeamDao;

/**
 * Team Data Access Object is a DAO for Team objects. This class represents 
 * the interface of the database that related to Team objects.
 * 
 * @author Air Team
 */
public class TeamDaoJpa implements TeamDao {
	private EntityManager em;
	private static Logger logger;

	public TeamDaoJpa(EntityManager em) {
		super();
		this.em = em;
	}



	/**
	 * Save Team to the persistent storage.
	 * @param Team is object represents the competitor that can be voted.
	*/
	
	@Override
	public List<Team> findAll(){
		String queryStatement = "SELECT t FROM Team t";
		 return em.createQuery(queryStatement).getResultList();
	}
	
	@Override
	public Team findSingle(String teamName){
		List<Team> teams = findAll();
		for (int i=0;i<teams.size();i++) {
			Team team = teams.get(i);
			if (team.getName().equals(teamName))  return team; }
		
		return null;
	}
	
	/* (non-Javadoc)
	 * @see exceedvote.air.persistence.jpa.VoteTopicDao#save(exceedvote.air.model.VoteTopic)
	 */
	@Override
	public void save(Team team) {
		EntityTransaction tx = em.getTransaction();
		try {

			tx.begin();
			em.persist(team);
			tx.commit();
		} catch (IllegalStateException ex) {
			getLogger().error("Error saving topic " + team, ex);
			if (tx.isActive())
				tx.rollback();
			// should rethrow exception so the application knows
			// that save failed
			throw ex;
		}
	}

	/**
	 * Find all the Teams and return a list of all Teams.
	 * @return a list of all Teams that saved on the persistent storage.
	 */
	@Override
	public List<VoteTopic> findAllTopic() {
		String queryStatement = "SELECT vt FROM VoteTopic vt";
		return em.createQuery(queryStatement).getResultList();
	}

	/**
	 * Get the Logger of TeamDaoJpa class.
	 * @return new Logger if it is null else return Logger.
	 */
	private static Logger getLogger() {
		if (logger == null)
			logger = Logger.getLogger(TeamDaoJpa.class);
		return logger;
	}
	
	/**
	 * Delete the team out of persistence
	 * @param team that want to delete
	 */
	@Override
	public boolean remove(Team team){
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.remove(team);
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
	 * Check has this team in persistence or not
	 * @param team that want to check
	 * @return true, this team has in the persistence
	 */
	@Override
	public boolean hasVoter(Team team){
		List<Team> listTeam = this.findAll();
		for(int i=0;i<listTeam.size();i++){
			if(listTeam.equals(team)) return true;
		}
		return false;
	}
	
	@Override
	public List<Team> findSingleTeamAlltopic(String name){
		List<Team> listTeam = this.findAll();
		List<Team> oneTeamAlltopic = new ArrayList<Team> ();
		for(int i=0;i<listTeam.size();i++){
			if(listTeam.get(i).getName().equals(name)) oneTeamAlltopic.add(listTeam.get(i));
		}
		return oneTeamAlltopic;
		
	}



	

}