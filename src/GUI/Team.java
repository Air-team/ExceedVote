package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.Image;
import javax.swing.ImageIcon;
public class Team
{
// instance variables - replace the example below with your own
private String name;
private TeamDescription teamDescription;
private int score;
/**
* Constructor for objects of class Team
*/
public Team(String name,TeamDescription td)
{
this.name = name;
this.teamDescription = td;
}

public int getScore()
{
	return this.score;
}

public String getName()
{
	return this.name;
}

public void setScore(int score)
{
	this.score = this.getScore()+score;
	int result = JOptionPane.showConfirmDialog((Component)
    null, name , name+" has been vote", JOptionPane.DEFAULT_OPTION);
}
}