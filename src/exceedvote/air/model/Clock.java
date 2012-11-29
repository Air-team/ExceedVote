package exceedvote.air.model;

import java.util.Observable;

public class Clock extends Observable {
	private long time;
	private long startTime = 0;
	private long stopTime = 0;
	private boolean running = false;
	private Committee committee = new Committee();

	public Clock() {
		committee.setTime("29", "nov", "2012", "2", "45", "0");
	}

	public boolean isRun(){
		return running;
	}

	public void start() {
		this.startTime = System.currentTimeMillis();
		this.stopTime = committee.getTime();
		if(committee.getTime()>System.currentTimeMillis()) this.running = true;
		
		
	}

	// elaspsed time in milliseconds
	public long getElapsedTime() {
		long elapsed = 0;
		if(running)elapsed = (committee.getTime() - System.currentTimeMillis());
		if(elapsed==0) running = false;
		return elapsed;
	}



	public String time() {
	 
		long time = this.getElapsedTime() / 1000;
		String seconds = Integer.toString((int) (time % 60));
		String minutes = Integer.toString((int) ((time % 3600) / 60));
		String hours = Integer.toString((int) (time / 3600));
		for (int i = 0; i < 2; i++) {
			if (seconds.length() < 2) {
				seconds = "0" + seconds;
			}
			if (minutes.length() < 2) {
				minutes = "0" + minutes;
			}
			if (hours.length() < 2) {
				hours = "0" + hours;
			}
		}
		if(running==false) return 00 + ":" + 00 + ":" + 00;
		// System.out.println(hours+":"+minutes+":"+seconds);
		return hours + ":" + minutes + ":" + seconds;

	}

	/**
	 * Set the time and notify something to observer
	 * 
	 * @param time
	 *            is the current time from your computer
	 */
	public void setTime(long time) {
		this.time = time;

		setChanged();
		notifyObservers();
	}

	/**
	 * Set the current time every 1 minute
	 */
	public void updateTime() {
		if(running==true){
			time = getElapsedTime();
			this.setTime(time);
		}
	}

}
