/**
 * Write a description of class Team here.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public class Team {
	// instance variables - replace the example below with your own
	private String name;
	private TeamDescription teamDescription;
	private int score;

	/**
	 * Constructor for objects of class Team
	 */
	public Team(String name, TeamDescription td) {
		this.name = name;
		this.teamDescription = td;
	}

	public int getScore() {
		return this.score;
	}

	public String getName() {
		return this.name;
	}

	public void setScore(int score) {
		this.score = this.getScore() + score;
	}
}