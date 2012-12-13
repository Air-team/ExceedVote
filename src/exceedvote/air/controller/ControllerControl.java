package exceedvote.air.controller;


import java.util.ArrayList;
import java.util.List;

import exceedvote.air.model.Ballot;
import exceedvote.air.model.Clock;
import exceedvote.air.model.Committee;
import exceedvote.air.model.Team;
import exceedvote.air.model.TeamDescription;
import exceedvote.air.model.VoteTopic;
import exceedvote.air.ui.ControlPanel;
import exceedvote.air.ui.SeviceUI;
import exceedvote.air.ui.VoteTypeUI;
import exceedvote.air.ui.VoteUI;
/**
 * Mediator between ControlPanel and Committee which manage about setTime ,add criteria, add team etc.
 * @author AirTeam
 */
public class ControllerControl {
	
private static ControllerControl controllerControl;
private SeviceUI serviceUI = new SeviceUI();
private Committee committee; 

	/**
	 * Set ConttollerControl be singleton
	 * @return
	 */
	public static ControllerControl getInstance(){
		if(controllerControl == null) controllerControl = new ControllerControl();
		return controllerControl;
	}
	
	/**
	 * SetTime that close voting system
	 * @param selectDay given date be string (such as"31")
	 * @param selectMonth given month be string
	 * @param selectYear given year (B.C.) be string (such as "2012")
	 * @param hour given hour be string
	 * @param min given minute be string
	 * @param second given second be string
	 */
	public void setTime(String selectDay, String selectMonth,String selectYear, String hour, String min, String second) {
		
		committee.setTime(selectDay, selectMonth, selectYear, hour, min, second);
	}

	/**
	 * Run ControlpanelUI
	 * @param committee the person that login to use controlpanel.
	 */
	public void getControlPanel(Committee committee) {
		this.committee = committee;
		ControlPanel controlPanel = new ControlPanel(committee);
  		serviceUI.addUI("ControlPanel", controlPanel);
  		controlPanel.addService(serviceUI);
  		controlPanel.run("");
	}
	
	/**
	 * Vote the regular vote system
	 */
	public void voteAction(){
		ControllerVote vote = ControllerVote.getInstance();
		vote.voteConsole(committee);
	}

	/**
	 * Delete topic out of the persistence
	 * @param voteTopic : topic that want to remove.
	 * @return true, if  it remove successfully
	 */
	public boolean deleteTopic(VoteTopic voteTopic) {
		if( committee.deleteTopic(voteTopic.getTitle()) ) {
			Clock clock = new Clock();
			VoteUI voteUI = new VoteUI(clock);
			voteUI.initComponent();
			return true;
		}
		else return false;	
	}
	
	/**
	 * Delete Team out of the persistence
	 * @param team : team that want to remove.
	 * @return true, if it is removed successfully.
	 */
	public boolean deleteTeam(Team team) {
		if( committee.deleteTeam(team.getName())) {
			Clock clock = new Clock();
			VoteTypeUI voteTypeUI = new VoteTypeUI(clock);
			voteTypeUI.initComponent();
			return true;
		}
		else return false;	
	}

	/**
	 * Add the new topic in the persistence
	 * @param topicName is name of topic that want to add
	 * @return true, if it is added successfully.
	 */
	public boolean addTopic(String topicName) {
		if(committee.setTopic(topicName)) {
			Clock clock = new Clock();
			VoteTypeUI voteTypeUI = new VoteTypeUI(clock);
			voteTypeUI.initComponent();
			return true;
		}
		else return false;
	}
	
	/**
	 * Add the new team in the persistence
	 * @param teamName is name of team that want to add
	 * @param pic , Picture files(jpg,png etc.) identity of team
	 * @param descriptionText , description software of this team
	 * @return true, if it i added successfully.
	 */
	public boolean addTeam(String teamName,String pic, String descriptionText) {
		TeamDescription teamDes = new TeamDescription("");
    	teamDes.setFilePic(pic);
    	teamDes.setInfo(descriptionText);
    	teamDes.saveTeamDes(teamDes);
    	Team team = new Team(teamName,teamDes);
    	team.saveInfo(teamName, teamDes);
    	Clock clock = new Clock();
		VoteUI voteUI = new VoteUI(clock);
		voteUI.initComponent();
    	return committee.setTeam(teamName);

	}

	/**
	 * Get of all team in the persistence
	 * @return array of all team
	 */
	public Object[] getTeam() {
		Committee committee = new Committee();
		return committee.getTeam().toArray();
	}
	
	/**
	 * Get of All topic in the persistence
	 * @return array of all team
	 */
	public Object[] getTopicArray() {
		Committee committee = new Committee();
		return committee.getTopic().toArray();
	}

	/**
	 * Get of All topic in the persistence
	 * @return list of all topic
	 */
	public List<VoteTopic> getTopic() {
		return committee.getTopic();
		
	}

	/**
	 * Get result ballot of any team
	 * @return List of score any team 
	 */
	public List<ArrayList> getBallot(String topic){
		Ballot ballot = Ballot.getInstance();
		return ballot.getScoreEachTeam(topic);
	}


	
}
