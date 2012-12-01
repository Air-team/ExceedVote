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
@Entity
public class Time implements Serializable {
	private long time = 0;

	@OneToOne(cascade = CascadeType.PERSIST)
	private Committee committee;
	private List<Time> list;
	public Time(){
		super();
	}
	public Time(Committee committee){
		this();
		this.committee = committee;
	}
	
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	public void saveTime(){
		TimeDao timeDao = DaoFactory.getInstance().getTimeDao();
		timeDao.save(this);
	}
	
	public long getTime(){
		TimeDao timeDao = DaoFactory.getInstance().getTimeDao();
		list = timeDao.find();
		return list.get(list.size()-1).time;
		
	}
	
	public void setTime(long time){
		this.time = time;
	}
	
	
}
