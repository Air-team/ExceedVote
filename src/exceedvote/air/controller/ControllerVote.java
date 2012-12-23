package exceedvote.air.controller;

import java.util.Timer;
import java.util.TimerTask;

import exceedvote.air.model.Ballot;
import exceedvote.air.model.Clock;
import exceedvote.air.model.ClockTask;
import exceedvote.air.model.Committee;
import exceedvote.air.model.Voter;
import exceedvote.air.ui.HistoryUI;
import exceedvote.air.ui.SeviceUI;
import exceedvote.air.ui.VoteTypeUI;
import exceedvote.air.ui.VoteUI;
/**
 * Mediator between Vote , VoteUi , VoteTypeUI
 * @author AirTeam
 */
public class ControllerVote {
	private String status="";
	private static ControllerVote controllerVote;
	private SeviceUI serviceUI = new SeviceUI();
	private Voter voter;
	private Committee committee;
	public static ControllerVote getInstance(){
		if(controllerVote == null) controllerVote = new ControllerVote();
		return controllerVote;
	}
	
	/**
	 * Run the VoteTypeUI when this voter successfully.
	 * @param voter the person who vote/enjoy vote system
	 */
	public void voteConsole(Voter voter) {
		this.voter = voter;
		status = "voter";
		Clock t = new Clock();
    	VoteUI voteUI = new VoteUI(t);
		t.addObserver(voteUI);
		VoteTypeUI voteTypeUI = new VoteTypeUI(t);
		t.addObserver(voteTypeUI);
		serviceUI.addUI("voteUI",voteUI);
		serviceUI.addUI("voteTypeUI",voteTypeUI);
		voteTypeUI.addService(serviceUI);
		voteUI.addService(serviceUI);
		TimerTask clocktask = new ClockTask( t );
		Timer timer = new Timer();
		long delay = 1000 - System.currentTimeMillis()%1000;
		final long INTERVAL = 1000; 
		timer.scheduleAtFixedRate(clocktask, delay, INTERVAL);
		t.start();
		voteTypeUI.run("voteTypeUI");
	}

	/**
	 * Run the VoteTypeUI when this committee successfully.
	 * @param committee the person who vote/enjoy vote system
	 */
	public void voteConsole(Committee committee) {
		this.committee = committee;
		status = "committee";
		Clock clock = new Clock();
    	VoteUI voteUI = new VoteUI(clock);
    	VoteTypeUI voteTypeUI = new VoteTypeUI(clock);
    	clock.addObserver(voteUI);
    	clock.addObserver(voteTypeUI);
		serviceUI.addUI("voteUI",voteUI); //$NON-NLS-1$
		serviceUI.addUI("voteTypeUI",voteTypeUI); //$NON-NLS-1$
		voteTypeUI.addService(serviceUI);
		voteUI.addService(serviceUI);
		
		TimerTask clocktask = new ClockTask( clock );
		Timer timer = new Timer();


		long delay = 1000 - System.currentTimeMillis()%1000;
		final long INTERVAL = 1000; 
		timer.scheduleAtFixedRate(clocktask, delay, INTERVAL);
		clock.start();
	
	
		voteTypeUI.ControlPanelBtn();
	}

	/**
	 * Check amount of user that be voter
	 * @return integer amount of ballot
	 */
	public int checkAmountBallot() {
		if(voter!=null) return voter.getballotLeft();
		else if(committee!=null) return committee.getballotLeft();
		return 0;
	}
	
	/**
	 * Get status of that user voter or committee
	 * @return status that match with user
	 */
	public String getStatus(){
		return status;
	}

	/**
	 * PutBallot (Vote) topic and team that you want.
	 * @param selectTeam team in the system that want to vote
	 * @param typeTeam topic that you want to vote
	 * @return true, if if it vote successfully
	 */
	public boolean putBallot(String selectTeam, String typeTeam) {
		if(status.equalsIgnoreCase("committee")){
			Ballot ballot = Ballot.getInstance();
			return ballot.putBallot(selectTeam,typeTeam,committee);
		}
		else if(status.equalsIgnoreCase("voter")){
			Ballot ballot = Ballot.getInstance();
			return ballot.putBallot(selectTeam,typeTeam,voter);
		}
		return false;
	}

	 /**
	  * ReturnBallot(Revote) the team and topic that you want to revote which can revote in case you  has voted this team and this topic 
	  * @param selectTeam team in the system that want to revote
		* @param typeTeam topic that you want to revote
	 * @return true, if if it revote successfully
	  */
	public boolean returnBallot(String selectTeam, String typeTeam) {
		if(status.equalsIgnoreCase("committee")){
			Ballot ballot = Ballot.getInstance();
			return ballot.returnBallot(selectTeam, typeTeam, committee);
		}
		else if(status.equalsIgnoreCase("voter")){
			Ballot ballot = Ballot.getInstance();
			return ballot.returnBallot(selectTeam, typeTeam, voter);
		}
		return false;
	}

	/**
	 * Lookup history about time,team,topic that you has voted
	 */
	public void getHistory() {
		if(status.equalsIgnoreCase("committee")){
			 HistoryUI historyUI = new HistoryUI();
			 historyUI.run();
			 historyUI.addData(committee.history());
			 committee.history();
			
		}
		else if(status.equalsIgnoreCase("voter")){
			 HistoryUI historyUI = new HistoryUI();
			 historyUI.run();
			 historyUI.addData(voter.history());
			 voter.history();

		}
	
		
	}


	
}
