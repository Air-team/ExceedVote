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
	/** the String URL of file picture*/
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
	 * @param info is the information of a team.
	 */
	public TeamDescription(String info) {
		this();
		this.info = info;
	}

	/**
	 * Initialize TeamDescription.
	 */
	public TeamDescription() {
		super();
	}

	/**
	 * Set the information of a team.
	 * 
	 * @param info is the String represents the information of a team.
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

	/**
	 * Return the String represents the URL of file picture.
	 * @return URL of file picture.
	 */
	public String getFilePic() {
		return filePic;
	}

	/**
	 * Set the URL of file picture.
	 * @param filePic is Strings represent the URL of file picture.
	 */
	public void setFilePic(String filePic) {
		this.filePic = filePic;
		this.setURLPic(filePic);
	}

	/**
	 * Save the TeamDescription to the database
	 * @param teamDes is TeamDescription object.
	 */
	public void saveTeamDes(TeamDescription teamDes) {
		TeamDescriptionDao dao = DaoFactory.getInstance().getTeamDescriptionDao();
		dao.save(teamDes);
	}

	/**
	 * Return the Icon of file picture.
	 * @return Icon of file picture.
	 */
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

	/**
	 * Set the URL of the file picture.
	 * @param filePic the String represents the URL of file picture.
	 */
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

	/**
	 * Return the id of this TeamDescription.
	 * @return id of this TeamDescription.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Set the Id of this TeamDescription
	 * @param id the new id.
	 */
	public void setId(int id) {
		this.id = id;
	}

}