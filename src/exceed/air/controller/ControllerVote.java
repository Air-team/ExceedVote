package exceed.air.controller;

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
		voteTypeUI.run("voteTypeUI"); //$NON-NLS-1$
	}


	public int checkAmountBallot() {
		if(voter!=null) return voter.getballotLeft();
		else if(committee!=null) return committee.getballotLeft();
		return 0;
	}
	
	public String getStatus(){
		return status;
	}


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
