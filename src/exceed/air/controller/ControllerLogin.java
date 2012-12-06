package exceed.air.controller;

import exceedvote.air.model.Committee;
import exceedvote.air.model.Login;
import exceedvote.air.model.Voter;
import exceedvote.air.ui.AdminUI;
import exceedvote.air.ui.SeviceUI;

/**
 * Mediator between Login and LoginUI
 * @author AirTeam
 */

public class ControllerLogin {
	private static ControllerLogin controllerLogin;
	private SeviceUI serviceUI = new SeviceUI();
	private ControllerLogin(){
		
	}
	
	/**
	 * Controller is the singleton pattern 
	 * @return only one controller
	 */
	public static ControllerLogin getInstance(){
		if(controllerLogin == null) controllerLogin = new ControllerLogin();
		return controllerLogin;
	}
	
	
	/** 
	 * Check the user successfully login
	 * @param username identity name of each user
	 * @param password security code of each user
	 * @return true if login successfully
	 */
	public boolean checkLogin(String username, String password) {
		Login login = new Login(username,password);
    	
		if(login.hasVoter()){
			Voter voter = login.getVoter();
    		ControllerVote voteController = ControllerVote.getInstance();
    		voteController.voteConsole(voter);
    		return true;
			
		}else if(login.hasCommittee()){
			Committee committee = login.getCommittee();
			ControllerControl control =  ControllerControl.getInstance();
    		control.getControlPanel(committee);
      		return true;
		}
		else if(login.isAdmin()){
			AdminUI adminUI = new AdminUI();
          	serviceUI.addUI("AdminUI", adminUI);
          	adminUI.addService(serviceUI);
          	adminUI.run("");
          	return true;
		}
		
		return false;
		
	}
	

	
	

}
