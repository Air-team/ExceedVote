package GUI;

import java.util.ArrayList;
import java.util.List;

/**
 *  Box that you keep the ballot of each team that you want to vote
 */

/**
 * @author PrisaDumrongsiri
 * 
 */
public class BallotBox {

		private TeamList teamList;
		private List list;
		private int value = 1;
		
		public BallotBox(TeamList teamList){
			this.teamList = teamList;	
			list = teamList.getTeam();
		}
		
		/** 
		 * Put the ballot in the team that you want to vote via ballot box
		 * @param teamName is the team name that you want to vote
		 */
		public boolean putBallot( String teamName){
				
			try
			{
			for(int i=0;i<list.size();i++){
				if(  ( (Team)list.get(i) ).getName().equals(teamName) )  {
							teamList.setBallot(teamName);
							return true;
				}
			}
			} catch(NullPointerException ne)
			{
				System.out.println("putballot");
			}
			return false;
	}	
		
		
	
		
}
