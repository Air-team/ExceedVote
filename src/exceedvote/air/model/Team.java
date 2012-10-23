package exceedvote.air.model;

import java.awt.*;
import javax.swing.*;

public class Team {
	// instance variables - replace the example below with your own
	private String name;
	private int score;

	/**
	 * Constructor for objects of class Team
	 */
	public Team(String name, TeamDescription td) {
		this.name = name;
	}

	public int getScore() {
		return this.score;
	}

	public String getName() {
		return this.name;
	}

	public void setScore(int score) {
		this.score = this.getScore() + score;
		int result = JOptionPane.showConfirmDialog((Component) null, name, name
				+ " has been vote", JOptionPane.DEFAULT_OPTION);
	}
}