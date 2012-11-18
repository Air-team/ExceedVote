package exceedvote.air.model;

import java.awt.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.swing.*;

import exceed.air.persistence.DaoFactory;
import exceed.air.persistence.TeamDao;
import exceed.air.persistence.VoteTopicDao;

/**
 * Class Team represent the  
 * @author Air Team
 */
@Entity
public class Team implements Serializable{
	// instance variables - replace the example below with your own
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String name;
	private int score;
	private VoteTopic topic;

	/**
	 * Constructor for objects of class Team
	 * @param type 
	 */
	public Team(String name, TeamDescription td, List<VoteTopic> type) {
		this();
		this.name = name;		
	}
	
	public Team(){
		super();
	}
	public int getScore(String topicName) {
		return score;
	}

	public String getName() {
		return this.name;
	}

	public void setScore(int score,String topicName) {
		VoteTopicDao dao2 = DaoFactory.getInstance().getVoteTopicDao();
		topic = dao2.find(topicName);
		this.score = score;
	}
	

}