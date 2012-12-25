package exceedvote.air.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * VoteTopic represents the topics to vote for the team.
 * 
 * @author Air Team.
 * @version 2012.11.20
 */
@Entity
public class VoteTopic implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	private String title;

	/**
	 * VoteTopic Constructor.
	 */
	private VoteTopic() {
		super();
	}

	/**
	 * Initialize the VoteTopic with title.
	 * 
	 * @param title is the topic title.
	 */
	public VoteTopic(String title) {
		this();
		this.title = title;
	}

	/**
	 * Return the title of the topic.
	 * 
	 * @return String represents the title of the topic.
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Set the title of the topic.
	 * 
	 * @param title is the changed title.
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
}
