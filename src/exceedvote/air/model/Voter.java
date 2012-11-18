package exceedvote.air.model;

import java.io.Serializable;
import javax.persistence.*;

import exceedvote.air.persistence.DaoFactory;
import exceedvote.air.persistence.VoterDao;

/**
 * Entity implementation class for Entity: Voter
 *
 */
@Entity

public class Voter implements Serializable {

	
	private static final long serialVersionUID = 1L;


	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
   private Integer id;
	private String name;
	private String type;
	private int amountOfBallot;

	/**
	 * Constructor for objects of class Voter.
	 * @param name is the name of the voter.
	 * @param type is a kind of the voter such "STUDENT" or "TEACHER".(for now)
	 * @param ballot is the ballot that the voter has.
	 * @param bb is ballot box.
	 * @param teamList is the team list.
	 */
	public Voter(String name, String type) {
		this();
		this.name = name;
		this.type = type;
		setFistQuata(type);
	}

	public Voter() {
		super();
	}
   
	private void setFistQuata(String type) {
		if(type.equals("STUDENT")){
			this.amountOfBallot = 5;
		}
		else if(type.equals("TEACHER")){
			this.amountOfBallot = 10;
		}
	}

	/**
	 * Get the voter name.
	 * @return String name of the voter.
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Get the type of the voter such as "STUDENT" or "TEACHER".
	 * @return
	 */
	public String getType() {
		return this.type;
	}

	
	public Integer getId(){
		return this.id;
	}
	
	public void setballotLeft(int value){
		this.amountOfBallot = value;
		VoterDao dao = DaoFactory.getInstance().getVoterDao();
		dao.save(this);
	}
	
	public int getballotLeft(){
		return amountOfBallot;
	}
	
}
