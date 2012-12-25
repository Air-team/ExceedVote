package exceedvote.air.controller;

import java.util.List;

import exceedvote.air.model.Admin;
import exceedvote.air.model.Committee;
import exceedvote.air.model.Voter;

/**
 * Mediator between Login and LoginUI
 * @author AirTeam
 */
public class ControllerAdmin {
	private static ControllerAdmin controllerAdmin;
	private Admin admin = new Admin();
	
	/**
	 * Controller is the singleton pattern 
	 * @return only one controller
	 */
	public static ControllerAdmin getInstance(){
		if(controllerAdmin == null) controllerAdmin = new ControllerAdmin();
		return controllerAdmin;
	}

	/**
	 *Get all voter in the system
	 * @return List Of all Voter
	 */
	public List<Voter> getAllvoter() {
		return admin.getVoter();		
	}

	/**
	 * Get all committee in the system
	 * @return List Of all committee
	 */
	public List<Committee> getAllcommittee() {
		return admin.getCommittee();		
	}
	
	
	
}
