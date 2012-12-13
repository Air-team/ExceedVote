package exceedvote.air.model;

import java.util.TimerTask;

/**
 * ClockTask will do the repeated counting of time execution by Timer
 * 
 * @author Air Team
 */
public class ClockTask extends TimerTask {

	private Clock clock;

	/**
	 * Initialize the Clock
	 * 
	 * @param clock is Clock that you will call to update time.
	 */
	public ClockTask(Clock clock) {
		this.clock = clock;
	}

	/**
	 * Calls the Clock to update time.
	 * This method will be call every one second(as it has already set on this program) by execution of Timer.
	 */
	@Override
	public void run() {
		clock.updateTime();
	}

}
