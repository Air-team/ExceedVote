package exceedvote.air.model;


import java.util.TimerTask;

import exceedvote.air.ui.LoginUI;
import exceedvote.air.ui.SeviceUI;
import exceedvote.air.ui.VoteUI;

/**
 * Main class for launch the program.
 * 
 * @author Air Team
 * @version 2012.11.20
 */
public class Main {

	public static void main(String[] args) {
	
    	
		Ballot ballot = new Ballot();
		ballot.setInstance(ballot);
//		VoteUI voteUI = new VoteUI()
		LoginUI loginUI = new LoginUI();
		SeviceUI serviceUI = new SeviceUI();
		serviceUI.addUI("LoginUI",loginUI);
		loginUI.addService(serviceUI);
		loginUI.run("");
//		watch.stop();

	}
}