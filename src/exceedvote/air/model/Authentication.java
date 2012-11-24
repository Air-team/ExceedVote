package exceedvote.air.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class Authentication {
	private static final String PERSISTENCE_UNIT = "JPAAuthentication";
	private static EntityManagerFactory emf;
	private static EntityManager em;
	static{
		emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
		em = emf.createEntityManager();
	}
	
	public static  Voter authentication(String username,String password) {
		String q = "SELECT u FROM USER WHERE u,username = :username AND u.password = :password";
		Query query = em.createQuery(q);
		query.setParameter("username",username);
		query.setParameter("passsword",password);
		try{
			return (Voter)query.getSingleResult();
		}catch(Exception e){
			return null;
		}
	}
}
