package exceedvote.air.model;

import org.apache.log4j.Logger;

/**
 * Class Tracking is used for track the action of each class.
 * @author Chupan
 */
public class Tracking {
	/** final attribute of Logger */
	final static Logger lg = Logger.getLogger( BallotBox.class );

	/**
	 * Constructor for objects of class Tracking.
	 */
	public Tracking(){
		lg.trace("Initialize");
	}

	/**
	 * Add Log.
	 * @param teamName
	 */
	public void addLog(String teamName){
		lg.info("vote For "+teamName);
	}
}