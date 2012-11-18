package exceedvote.air.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: l
 *
 */
@Entity
public class VoteTopic implements Serializable {

	
	private static final long serialVersionUID = 1L;

	public VoteTopic() {
		super();
	}

	@Id
	private String title;
	
	
	public VoteTopic(String name) {
		this();
		this.title = name;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
   
}
