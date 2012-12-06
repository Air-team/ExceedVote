package exceedvote.air.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import exceedvote.air.model.Team;
import exceedvote.air.model.TeamDescription;

public class InfomationUI extends JFrame implements RunUI{
	private JPanel contentPane;
	private JTextPane infoPane;
	private JLabel teamHeadLine;
	private JLabel teamName;
	private JLabel picture;
	private JLabel infoName;
	private SeviceUI serviceUI;
	private TeamDescription teamDes;
	private Team team;
	private JScrollPane scrollPane;
	Border raisedbevel = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
	
	public InfomationUI(String teamName){
		team = Team.getTeam(teamName);
		teamDes = team.getTeamDescription();
		initComponent();
	}

	public void initComponent() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setBounds(100, 100, 392, 500);
	        contentPane = new JPanel();
	        contentPane.setBackground(Color.DARK_GRAY);
	        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	        setContentPane(contentPane);
	        contentPane.setLayout(null);

	        teamHeadLine = new JLabel(Messages.getString("InfomationUI.label.team")); //$NON-NLS-1$
	        teamHeadLine.setForeground(Color.WHITE);
	        teamHeadLine.setFont(new Font("Tahoma", Font.PLAIN, 16)); //$NON-NLS-1$
	        teamHeadLine.setBounds(10, 11, 356, 21);
	        contentPane.add(teamHeadLine);

	        
	        teamName = new JLabel(team.getName());
	        	        teamName.setForeground(Color.WHITE);

	        teamName.setFont(new Font("Tahoma", Font.PLAIN, 16)); //$NON-NLS-1$
	        teamName.setBounds(62, 11, 356, 21);
	        contentPane.add(teamName);
	        
	      
	       
	        picture = new JLabel(teamDes.getIcon() );
	        picture.setBounds(60, 40, 269, 250);
	        scrollPane = new JScrollPane();
			scrollPane.getViewport().add( picture );
	        contentPane.add(picture);
	        
	        infoPane = new JTextPane();
	        infoPane.setBackground(Color.white);

	        infoPane.setBounds(60, 340, 269, 90);
	        infoPane.setText(teamDes.getInfo());
	       contentPane.add(infoPane);
	          
	       
	}
	
	public void close() {
   		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
   	}
	
	public void addService(SeviceUI serviceUI) {
		this.serviceUI = serviceUI;
	}
   
	public void run(String info) {
		this.setVisible(true);
		this.setResizable(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	

}
