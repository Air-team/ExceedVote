package GUI;

import org.apache.log4j.Logger;
public class Tracking {
	final static Logger lg = Logger.getLogger( BallotBox.class );
	
	public Tracking(){
		lg.trace("Initialize");
	}
	
	public void addLog(String teamName){
		lg.info("vote For "+teamName);
	}
}
