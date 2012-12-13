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
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String name;
	private String type;
	private String password;
	private int amountOfBallot = 100;

	/**
	 * Voter Constructor.
	 */
	private Voter() {
		super();
	}

	/**
	 * Initialize the Voter that has name and type.
	 * 
	 * @param name is the name of the user.
	 * @param type is the type such as STUDENT or TEACHER.
	 */
	public Voter(String username,String password,String type) {
		this();
		this.name = username;
		this.password = password;
		this.type = type;
		setFirstQuata(type);
	}

	

	/**
	 * Set the quota for each user depend on what kind of the user.
	 * 
	 * @param type is the type of user.
	 */
	private void setFirstQuata(String type) {
		if(type.equalsIgnoreCase("Student")){
			this.amountOfBallot = 5;
		}
		else if(type.equalsIgnoreCase("Teacher")){

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

	/**
	 * Return the id of this voter.
	 * @return the Integer represents the id.
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * Set the left ballot for this voter.
	 * @param value the amount of ballots.
	 */
	public void setballotLeft(int value) {
		this.amountOfBallot = value;
		VoterDao dao = DaoFactory.getInstance().getVoterDao();
		dao.save(this);
	}

	/**
	 * Return the amount of the left ballots.
	 * @return amount of the left ballots.
	 */
	public int getballotLeft() {
		return amountOfBallot;
	}

	/**
	 * Return the list of histories.
	 * @return list of histories.
	 */
	public List<ArrayList> history(){
		BallotDao dao = DaoFactory.getInstance().getBallotDao();
		return dao.history(this);
	}
	
	/**
	 * Save the information of this voter to the database.
	 * @param voter is the voter object.
	 */
	public void saveInfo(Voter voter){
		VoterDao dao = DaoFactory.getInstance().getVoterDao();
		dao.save(voter);
	}

	/**
	 * Return the password of this voter.
	 * @return password of this voter.
	 */
	public String getPassword(){
		return password;
	}

}
