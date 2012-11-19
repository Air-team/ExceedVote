package exceedvote.air.model;

import java.io.Serializable;
import javax.persistence.*;

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
	public Voter(String name, String type) {
		this();
		this.name = name;
		this.type = type;
		setFistQuota(type);
	}

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

}
