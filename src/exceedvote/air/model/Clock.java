 package exceedvote.air.model;

import java.util.Observable;

/**
 * Clock represents the stopwatch that will countdown from the start time until the time run out.
 * This class will be observe by the Observer.
 * 
 * @author Air Team
 */
public class Clock extends Observable {
	private long time;
	private long startTime = 0;
	private long stopTime = 0;
	private boolean running = false;
	private Committee committee;

	/**
	 * Initialize committee that will set the stop time.
	 */
	public Clock() {
		committee = new Committee();
	}

	/**
	 * Check whether the time is running or not.
	 * @return true if the time is running.
	 */
	public boolean isRun() {
		return running;
	}

	/**
	 * Start the time by get the stoptime from committee.
	 */
	public void start() {
		this.startTime = System.currentTimeMillis();
		this.stopTime = committee.getTime();

		if (committee.getTime() > System.currentTimeMillis())
			this.running = true;

	}

	/**
	 * Get the elapse time in milliseconds.
	 * @return elapse time in milliseconds.
	 */
	private long getElapsedTime() {
		long elapsed = 0;
		if (running)
			elapsed = (committee.getTime() - System.currentTimeMillis());
		if (elapsed == 0)
			running = false;
		return elapsed;
	}

	/**
	 * Return the String represents the remaining time in the time format(hour:minute:second).
	 * @return time that is formatted.
	 */
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
		if (running == false)
			return 00 + ":" + 00 + ":" + 00;
		// System.out.println(hours+":"+minutes+":"+seconds);
		return hours + ":" + minutes + ":" + seconds;

	}

	/**
	 * Set the time and notify to observer
	 * @param time is the current time from computer.
	 */
	private void setTime(long time) {
		this.time = time;

		setChanged();
		notifyObservers();
	}

	/**
	 * Update the time in every one second.
	 */
	public void updateTime() {
		if (running == true) {
			time = getElapsedTime();
			this.setTime(time);
		}
	}

}
