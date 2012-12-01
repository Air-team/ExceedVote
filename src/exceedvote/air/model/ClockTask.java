package exceedvote.air.model;

import java.util.TimerTask;

/**
 * Loop of time every 1 minute
 */
public class ClockTask extends TimerTask {

	private Clock clock;

	/**
	 * Initialize the ClockModel
	 * 
	 * @param model
	 *            is ClockModel that you have to update time
	 */
	public ClockTask(Clock clock) {
		this.clock = clock;
	}

	/**
	 * Update current time to the model
	 */
	@Override
	public void run() {
		clock.updateTime();
	}

}
