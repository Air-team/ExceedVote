package exceedvote.air.model;

import java.io.File;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import exceedvote.air.persistence.DaoFactory;
import exceedvote.air.persistence.TeamDescriptionDao;

/**
 * TeamDescription represents the description of a team.
 * 
 * @author Air Team
 * @version 2012.11.20
 */
@Entity
public class TeamDescription implements Serializable {

	/** the information of a team. */
	private String info;
	private String filePic;
	@Transient
	private Icon icon;

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	/**
	 * Initialize TeamDescription with information.
	 * 
	 * @param info
	 *            - the information of a team.
	 */
	public TeamDescription(String info) {
		this();
		this.info = info;
	}

	public TeamDescription() {
		super();
	}

	/**
	 * Set the information of a team.
	 * 
	 * @param info
	 *            - String represents the information of a team.
	 */
	public void setInfo(String info) {
		this.info = info;
	}

	/**
	 * Get the information of a team.
	 * 
	 * @return String represents the information of team.
	 */
	public String getInfo() {
		return this.info;
	}

	public String getFilePic() {
		return filePic;
	}

	public void setFilePic(String filePic) {
		this.filePic = filePic;
		this.setURLPic(filePic);
	}

	public void saveTeamDes(TeamDescription teamDes) {
		TeamDescriptionDao dao = DaoFactory.getInstance()
				.getTeamDescriptionDao();
		dao.save(teamDes);
	}

	public Icon getIcon() {
		if (icon == null) {
			try {
				icon = new ImageIcon(new File(filePic).toURL());
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}
		return icon;
	}

	public void setURLPic(String filePic) {

		this.filePic = filePic;
		URL position = null;

		try {
			position = new File(filePic).toURL();
		} catch (MalformedURLException e) {
		
			e.printStackTrace();
		}// class.getResource(filePic);
		icon = new ImageIcon(position);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}