package exceedvote.air.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import exceedvote.air.persistence.BallotDao;
import exceedvote.air.persistence.DaoFactory;
import exceedvote.air.persistence.VoterDao;

/**
 * Voter represents the a kind of user that appear for voting the competitors.
 * 
 * @author Air Team
 * @version 2012.11.20
 */
@Entity
public class Voter implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
<<<<<<< HEAD
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
=======
	@GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
>>>>>>> Implement login
	private String name;
	private String type;
	private String password;
	private int amountOfBallot;

	/**
	 * Voter Constructor.
	 */
	private Voter() {
		super();
	}

	/**
	 * Initialize the Voter that has name and type.
	 * 
	 * @param name
	 *            - the name of the user.
	 * @param type
	 *            - the type such as STUDENT or TEACHER.
	 */
	public Voter(String username,String password,String type) {
		this();
		this.name = username;
		this.password = password;
		this.type = type;
		setFistQuota(type);
	}

<<<<<<< HEAD
	/**
	 * Set the quota for each user depend on what kind of the user.
	 * 
	 * @param type
	 *            - the type of user.
	 */
	private void setFistQuota(String type) {
		if (type.equals("STUDENT")) {
			this.amountOfBallot = 5;
		} else if (type.equals("TEACHER")) {
=======
	public Voter() {
		super();
	}
   
	private void setFistQuata(String type) {
		if(type.equals("Student")){
			this.amountOfBallot = 5;
		}
		else if(type.equals("Teacher")){
>>>>>>> Implement login
			this.amountOfBallot = 10;
		}
	}

	/**
	 * Return Voter's name.
	 * 
	 * @return String represents Voter's name.
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Return the type of the user such as "STUDENT" or "TEACHER".
	 * 
	 * @return String represents the type of the user.
	 */
	public String getType() {
		return this.type;
	}

	public Integer getId() {
		return this.id;
	}

	public void setballotLeft(int value) {
		this.amountOfBallot = value;
		VoterDao dao = DaoFactory.getInstance().getVoterDao();
		dao.save(this);
	}

	public int getballotLeft() {
		return amountOfBallot;
	}
<<<<<<< HEAD

=======
	
	public List<ArrayList> history(){
		BallotDao dao = DaoFactory.getInstance().getBallotDao();
		return dao.history(this);
	}
	
	public void saveInfo(Voter voter){
		VoterDao dao = DaoFactory.getInstance().getVoterDao();
		dao.save(voter);
//		System.out.println(dao.save(voter));
	}

	
	public String getPassword(){
		return password;
	}
	
	
>>>>>>> Implement login
}
