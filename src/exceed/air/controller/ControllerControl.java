package exceed.air.controller;


import java.util.List;

import exceedvote.air.model.Clock;
import exceedvote.air.model.Committee;
import exceedvote.air.model.Team;
import exceedvote.air.model.TeamDescription;
import exceedvote.air.model.VoteTopic;
import exceedvote.air.ui.ControlPanel;
import exceedvote.air.ui.SeviceUI;
import exceedvote.air.ui.VoteTypeUI;
import exceedvote.air.ui.VoteUI;

public class ControllerControl {
	
private static ControllerControl controllerControl;
private SeviceUI serviceUI = new SeviceUI();
private Committee committee; 
	public static ControllerControl getInstance(){
		if(controllerControl == null) controllerControl = new ControllerControl();
		return controllerControl;
	}

	public void setTime(String selectDay, String selectMonth,String selectYear, String hour, String min, String second) {
		
		committee.setTime(selectDay, selectMonth, selectYear, hour, min, second);
	}

	public void getControlPanel(Committee committee) {
		this.committee = committee;
		ControlPanel controlPanel = new ControlPanel(committee);
  		serviceUI.addUI("ControlPanel", controlPanel);
  		controlPanel.addService(serviceUI);
  		controlPanel.run("");
	}
	
	public void voteAction(){
		ControllerVote vote = ControllerVote.getInstance();
		vote.voteConsole(committee);
	}

	public boolean deleteTopic(VoteTopic voteTopic) {
		if( committee.deleteTopic(voteTopic.getTitle()) ) {
			Clock clock = new Clock();
			VoteUI voteUI = new VoteUI(clock);
			voteUI.initComponent();
			return true;
		}
		else return false;	
	}

	public boolean deleteTeam(Team team) {
		if( committee.deleteTeam(team.getName())) {
			Clock clock = new Clock();
			VoteTypeUI voteTypeUI = new VoteTypeUI(clock);
			voteTypeUI.initComponent();
			return true;
		}
		else return false;	
	}

	public boolean addTopic(String topicName) {
		if(committee.setTopic(topicName)) {
			Clock clock = new Clock();
			VoteTypeUI voteTypeUI = new VoteTypeUI(clock);
			voteTypeUI.initComponent();
			return true;
		}
		else return false;
	}

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

	public Object[] getTeam() {
		Committee committee = new Committee();
		return committee.getTeam().toArray();
	}
	
	public Object[] getTopicArray() {
		Committee committee = new Committee();
		return committee.getTopic().toArray();
	}

	public List<VoteTopic> getTopic() {
		return committee.getTopic();
		
	}



	
}
