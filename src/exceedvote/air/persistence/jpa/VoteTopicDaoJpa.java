package exceedvote.air.persistence.jpa;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import exceedvote.air.model.VoteTopic;
import exceedvote.air.persistence.DaoFactory;
import exceedvote.air.persistence.VoteTopicDao;

/**
 * Persistence operations for VoteTopic objects.
 * @author Negisu
 *
 */
public class VoteTopicDaoJpa implements VoteTopicDao {
	private EntityManager em;
	private static Logger logger;
	
	public VoteTopicDaoJpa(EntityManager em) {
		super();
		this.em = em;
	}
	
	@Override
	public List<VoteTopic> findAll(){
		String queryStatement = "SELECT vt FROM VoteTopic vt";
		return em.createQuery(queryStatement).getResultList();
	}
	
	/* (non-Javadoc)
	 * @see exceedvote.air.persistence.jpa.VoteTopicDao#save(exceedvote.air.model.VoteTopic)
	 */
	@Override
	public void save(VoteTopic topic) {
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.persist(topic);
			tx.commit();
		} catch (IllegalStateException ex) {
			getLogger().error("Error saving topic "+topic, ex);
			if (tx.isActive()) tx.rollback();
			// should rethrow exception so the application knows
			// that save failed
			throw ex;
		}
	}
	
	private static Logger getLogger() {
		if (logger == null) logger = Logger.getLogger(VoteTopicDaoJpa.class);
		return logger;
	}
	
	/**
	 * test saving some objects
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner console = new Scanner(System.in);
		System.out.print("Name of topic for voting: ");
		String title = console.nextLine().trim();
		VoteTopic topic = new VoteTopic(title);
		System.out.println("saving topic "+topic);
		VoteTopicDao dao = DaoFactory.getInstance().getVoteTopicDao();
		dao.save(topic);
		System.out.println("saved topic with id "+topic.getId());
		
		List<VoteTopic> allTopic = dao.findAll();
		for (VoteTopic voteTopic : allTopic) {
			System.out.println("ID "+ voteTopic.getId() + ": Title "+ voteTopic.getTitle());
		}
		
		
	}
	
	
}