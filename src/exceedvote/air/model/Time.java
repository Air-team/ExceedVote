package exceedvote.air.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import exceedvote.air.persistence.DaoFactory;
import exceedvote.air.persistence.TimeDao;

/**
 * Time represents the end of voting time that committee set.
 * @author Air Team
 */
@Entity
public class Time implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private long time = 0;

	@OneToOne(cascade = CascadeType.PERSIST)
	private Committee committee;
	private List<Time> list;
	
	/**
	 * Initialize Time.
	 */
	public Time(){
		super();
	}
	
	/**
	 * Initialize Time related to the committee
	 * @param committee
	 */
	public Time(Committee committee){
		this.committee = committee;
	}
	
	/**
	 * Save Time to the database.
	 */
	public void saveTime(){
		TimeDao timeDao = DaoFactory.getInstance().getTimeDao();
		timeDao.save(this);
	}
	
	/**
	 * Get and return time from the database in millisecond.
	 * @return time in millisecond.
	 */
	public long getTime(){
		TimeDao timeDao = DaoFactory.getInstance().getTimeDao();
		list = timeDao.find();
		if(list.size() == 0) return 0;
		return list.get(list.size()-1).time;
	}
	
	/**
	 * Set the time in millisecond.
	 * @param time the new time.
	 */
	public void setTime(long time){
		this.time = time;
	}
	
	
}
