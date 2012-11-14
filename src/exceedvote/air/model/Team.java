package exceedvote.air.model;

import java.awt.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.*;

/**
 * Class Team represent the  
 * @author Air Team
 */
public class Team {
	// instance variables - replace the example below with your own
	private String name;
	private int score;
	private Map<VoteTopic,Integer> topic;
	Iterator<Entry<VoteTopic, Integer>> it;
	/**
	 * Constructor for objects of class Team
	 * @param type 
	 */
	public Team(String name, TeamDescription td, List<VoteTopic> type) {
		this.name = name;
		topic = new HashMap<VoteTopic,Integer>();
		addTopic(type);
		
		
	}
	public int getScore(String topicName) {
		 it = topic.entrySet().iterator();
		while( it.hasNext() ){
			Entry<VoteTopic, Integer> key =  it.next();
			VoteTopic t = key.getKey();
			String text = t.getTitle();
		
			if( text.equals(topicName) ){
//				System.out.println(topicName+" "+topic.get(t));
				return topic.get(t);
			}
		}
		return 0;
	}

	public String getName() {
		return this.name;
	}

	public void setScore(int score,String topicName) {

		this.score = this.getScore(topicName) + 1;
		 it = topic.entrySet().iterator();
		while( it.hasNext() ){
			Entry<VoteTopic, Integer> key =  it.next();
			VoteTopic t = key.getKey();
			String text = t.getTitle();
		System.out.println(text);
			if( text.equals(topicName) ){
				topic.put(t, this.score);
				JOptionPane.showConfirmDialog((Component) null, name, name + " has been vote", JOptionPane.DEFAULT_OPTION);
				return;
			}
		}
		
	}
	
	public void addTopic(List<VoteTopic> type){
		
		for(int i=0;i<type.size();i++) {
			topic.put(type.get(i), 0);	
		}
	
	
	}
}