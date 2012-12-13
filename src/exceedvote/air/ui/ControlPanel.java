package exceedvote.air.ui;

import java.awt.event.ActionEvent;

import java.awt.Color;
import java.awt.Component;

import java.util.ArrayList;
import java.util.List;


import javax.swing.AbstractAction;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.util.Timer;


import exceedvote.air.controller.ControllerControl;
import exceedvote.air.model.Committee;
import exceedvote.air.model.Team;
import exceedvote.air.model.VoteTopic;
import java.util.TimerTask;

/**
 * ControlPanel manage about setTime ,addTeam ,add topic etc. 
 * This UI for committee
 */
public class ControlPanel extends JFrame implements ListSelectionListener,
		RunUI {

	private JPanel contentPane;
	private JPanel controlPan = new JPanel();
	private JTextField addTeamInput;
	private JTextField criteraiInput;
	private JLabel labelTeam;
	private JLabel labelCeteria;
	// button
	// add team
	private JButton btnAddteam;
	// add criteria
	private JButton btnCriteria;
	// deleteTeam
	private JButton btnDeleteTeam;
	// deleteTopic
	private JButton btnDeleteTopic;
	// set time
	private JButton btnSettime;
	// vote
	private JButton btnVote;

	private VoteUI voteUI;
	private VoteTypeUI voteTypeUI;
	private Committee commitee;
	private DefaultListModel teamModel = new DefaultListModel();
	private DefaultListModel topicModel = new DefaultListModel();
	private List<Team> team = new ArrayList<Team>();
	private List<VoteTopic> topic = new ArrayList<VoteTopic>();

	// list for show team
	private JList listTeam;
	// list for show criteria
	private JList listCriteria;
	private SeviceUI serviceUI;
	/**
	 * Create the frame.
	 */
	public ControlPanel(Committee committee) {
		this.commitee = committee;

	}

	/**
	 * Initial components
	 */
	public void initComponent() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 493, 471);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane tab = new JTabbedPane(JTabbedPane.TOP);
		tab.setBounds(0, 0, 612, 441);
		contentPane.add(tab);
	

		tab.addTab(Messages.getString("ControlPanel.tab.control"), controlPan); //$NON-NLS-1$
		
		btnSettime = new JButton(new setTimeAction());
		btnSettime.setText("SetTime");
		btnSettime.setBounds(10, 91, 242, 23);
		controlPan.add(btnSettime);
		
		btnVote = new JButton(Messages.getString("ControlPanel.butt.vote.vote")); //$NON-NLS-1$
		btnVote.addActionListener(new VoteAction() );
		btnVote.setForeground(Color.BLACK);
		btnVote.setBackground(Color.LIGHT_GRAY);
		btnVote.setBounds(10,150,100,50);
		controlPan.add(btnVote);
	}

	/**
	 * set Component in the panel
	 */
	public void setControlPage() {
		
		controlPan.setBounds(0, 0, 475, 328);
		controlPan.setLayout(null);

		addTeamInput = new JTextField();
		addTeamInput.setBounds(10, 23, 140, 20);
		controlPan.add(addTeamInput);
		addTeamInput.setColumns(10);

		criteraiInput = new JTextField();
		criteraiInput.setColumns(10);
		criteraiInput.setBounds(10, 60, 140, 20);
		controlPan.add(criteraiInput);

		btnAddteam = new JButton(new addTeamAction());
		btnAddteam.setText(Messages.getString("ControlPanel.butt.team.addteam")); //$NON-NLS-1$
		btnAddteam.setBounds(163, 22, 89, 23);
		controlPan.add(btnAddteam);

		btnCriteria = new JButton(new addCreteriaAction());
		btnCriteria.setText(Messages.getString("ControlPanel.butt.criteria.criteria")); //$NON-NLS-1$
		btnCriteria.setBounds(160, 59, 89, 23);
		controlPan.add(btnCriteria);

		btnDeleteTeam = new JButton(new deleteTeamAction());
		btnDeleteTeam.setText(Messages.getString("ControlPanel.butt.deleteteam.deleteteam")); //$NON-NLS-1$
		btnDeleteTeam.setBounds(250, 151, 89, 23);
		controlPan.add(btnDeleteTeam);

		btnDeleteTopic = new JButton(new deleteTopicAction());

		btnDeleteTopic.setText(Messages.getString("ControlPanel.butt.deletetopic.deletetopic")); //$NON-NLS-1$
		btnDeleteTopic.setBounds(250, 340, 89, 23);
		controlPan.add(btnDeleteTopic);

		btnSettime = new JButton(new setTimeAction());
		btnSettime.setText(Messages.getString("ControlPanel.butt.settime.setime")); //$NON-NLS-1$
		btnSettime.setBounds(10, 91, 242, 23);
		controlPan.add(btnSettime);

		// String [] teamList = {""};

		labelTeam = new JLabel(Messages.getString("ControlPanel.label.team.team")); //$NON-NLS-1$
		labelTeam.setBounds(259, -50, 190, 126);
		controlPan.add(labelTeam);

		listTeam = new JList();
		listTeam.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listTeam.setSelectedIndex(0);
		listTeam.addListSelectionListener(this);
		team = commitee.getTeam();
		for (int i = 0; i < team.size(); i++)
			if (!teamModel.contains(team.get(i).getName()))
				teamModel.addElement(team.get(i).getName());
		listTeam.setModel(teamModel);

		labelCeteria = new JLabel(Messages.getString("ControlPanel.label.criteria.topic")); //$NON-NLS-1$
		labelCeteria.setBounds(259, 140, 190, 126);
		controlPan.add(labelCeteria);

		listCriteria = new JList();
		listCriteria.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listCriteria.setSelectedIndex(0);
		listCriteria.addListSelectionListener(this);
		
		ControllerControl control = ControllerControl.getInstance();
		topic = control.getTopic();
		for (int i = 0; i < topic.size(); i++)
			if (!topicModel.contains(topic.get(i).getTitle()))
				topicModel.addElement(topic.get(i).getTitle());
		listCriteria.setModel(topicModel);

		JScrollPane scrollPaneCriteria = new JScrollPane(listCriteria);
		scrollPaneCriteria.setBounds(259, 210, 190, 126);
		controlPan.add(scrollPaneCriteria);

		JScrollPane scrollPaneTeam = new JScrollPane(listTeam);
		scrollPaneTeam.setBounds(259, 20, 190, 126);
		controlPan.add(scrollPaneTeam);

		// finish
	}

	// action for vote
	private class VoteAction extends AbstractAction{
		public VoteAction() {
			super();
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			ControllerControl control = ControllerControl.getInstance();
			control.voteAction();
    		close();	
		}
		
	}
	
	/**
	 * Action of AddTeam Button 
	 */
	private class addTeamAction extends AbstractAction {

		public addTeamAction() {
			super();
		}

		public void actionPerformed(ActionEvent e) {
			String text = addTeamInput.getText();

			if (!teamModel.contains(text) && text != "") {
			
				final AddTeamPanel teamDes = new AddTeamPanel(serviceUI);
				teamDes.setTeamName(text);
				teamDes.setModel(teamModel);
				final SeviceUI serviceUI = new SeviceUI();
				serviceUI.addUI("teamDes", teamDes); //$NON-NLS-1$
				teamDes.addService(serviceUI);
				serviceUI.runByName("teamDes"); //$NON-NLS-1$
				close();

				if (teamDes.check()) {
					teamModel = teamDes.getModel();
					listTeam.setModel(teamModel);
				}

			}
			else if(text!=""){
				JOptionPane.showConfirmDialog((Component) null,
						"Please fill  Team", "Incorrect!!!",
						JOptionPane.DEFAULT_OPTION);
			}
			else {
				// System.out.println(addTeamInput.getText());
				JOptionPane.showConfirmDialog((Component) null,
						Messages.getString("ControlPanel.pop.setmodel.nameused"), Messages.getString("ControlPanel.pop.setmodel.selectteam"), //$NON-NLS-1$ //$NON-NLS-2$
						JOptionPane.DEFAULT_OPTION);
			}

			addTeamInput.setText(""); //$NON-NLS-1$

		}
	}



	/** Action for add criteria Button*/
	private class addCreteriaAction extends AbstractAction {

		public addCreteriaAction() {
			super();

		}

		public void actionPerformed(ActionEvent e) {
			ControllerControl control = ControllerControl.getInstance();
			
			if (!topicModel.contains(criteraiInput.getText()) && (criteraiInput.getText()!="")) {
				if (control.addTopic(criteraiInput.getText())) {
				
					topicModel.addElement(criteraiInput.getText());
					JOptionPane.showConfirmDialog((Component) null,
							Messages.getString("ControlPanel.pop.addsuccess"), Messages.getString("ControlPanel.pop.addtopic"), //$NON-NLS-1$ //$NON-NLS-2$
							JOptionPane.DEFAULT_OPTION);
				}
			} 
			else if(criteraiInput.getText()!=""){
				JOptionPane.showConfirmDialog((Component) null,
						"Please fill  Topic", "Incorrect!!!",
						JOptionPane.DEFAULT_OPTION);
			}
			else {
				JOptionPane.showConfirmDialog((Component) null,
						Messages.getString("ControlPanel.pop.settopic.nameused"), Messages.getString("ControlPanel.pop.settopic.addtopic"), //$NON-NLS-1$ //$NON-NLS-2$
						JOptionPane.DEFAULT_OPTION);
			}
			listCriteria.setModel(topicModel);
			criteraiInput.setText(""); //$NON-NLS-1$

		}

	}

	/**
	 * Action set time button
	 */
		private class setTimeAction extends AbstractAction {

			public setTimeAction() {
				super();
			}

			public void actionPerformed(ActionEvent e) {
				SetTimeUI timeUI = new SetTimeUI(serviceUI);
				serviceUI.addUI("timeUI", timeUI);
				timeUI.addService(serviceUI);
				serviceUI.runByName("timeUI");
				dispose();
			}
		}

	/**
	 * Action fon deleteTopic button
	 */
	private class deleteTopicAction extends AbstractAction {

		public deleteTopicAction() {
			super();
		}

		public void actionPerformed(ActionEvent e) {
			ControllerControl control = ControllerControl.getInstance();
		
		
			int index = listCriteria.getSelectedIndex();
			if (index <= 0)
				index = 0;
		

			if ( control.deleteTopic( topic.get(index)) ) {
				topicModel.remove(index);
				int size = topicModel.getSize();

				if (size == 0) {
					btnDeleteTeam.setEnabled(false);
				} else { // Select an index.
					if (index == topicModel.getSize()) {
						index--;
						listCriteria.setSelectedIndex(index);
						listCriteria.ensureIndexIsVisible(index);
					}

				}
				JOptionPane.showConfirmDialog((Component) null,
						Messages.getString("ControlPanel.pop.deletesuccess"), Messages.getString("ControlPanel.pop.success.delete"), //$NON-NLS-1$ //$NON-NLS-2$
						JOptionPane.DEFAULT_OPTION);
			} else {
				JOptionPane.showConfirmDialog((Component) null,
						Messages.getString("ControlPanel.pop.cannotdelete"), Messages.getString("ControlPanel.pop.cannot.delete"), //$NON-NLS-1$ //$NON-NLS-2$
						JOptionPane.DEFAULT_OPTION);
			}

		}
	}

	/**
	 * Action for deleteTeam Button
	 */
	private class deleteTeamAction extends AbstractAction {
		ControllerControl control = ControllerControl.getInstance();
		
		public deleteTeamAction() {
			super();
		}

		public void actionPerformed(ActionEvent e) {
			int index = listTeam.getSelectedIndex();
			if (index <= 0)
				index = 0;
			
		
			if (control.deleteTeam(team.get(index))) {
				
				teamModel.remove(index);
				int size = teamModel.getSize();

				if (size == 0) {
					btnDeleteTeam.setEnabled(false);
				} else { // Select an index.
					if (index == teamModel.getSize()) {
						// removed item in last position
						index--;
						voteUI.initComponent();
						listTeam.setSelectedIndex(index);
						listTeam.ensureIndexIsVisible(index);
					}
		
				}

				JOptionPane.showConfirmDialog((Component) null,
						Messages.getString("ControlPanel.popp.deletesuccess"), Messages.getString("ControlPanel.popp.success.delete"), //$NON-NLS-1$ //$NON-NLS-2$
						JOptionPane.DEFAULT_OPTION);
			} else {
				JOptionPane.showConfirmDialog((Component) null,
						Messages.getString("ControlPanel.popp.cannotdelete"), Messages.getString("ControlPanel.popp.cannotdelete.delete"), //$NON-NLS-1$ //$NON-NLS-2$
						JOptionPane.DEFAULT_OPTION);
			}

		}

	}


	/**
	 * Run this UI (Set Visible)
	 */
	public void run(String info) {
		this.setControlPage();
		this.initComponent();
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}


	/**
	 * add serviceUI in this class
	 * @param SeviceUI
	 */
	public void addService(SeviceUI serviceUI) {
		this.serviceUI = serviceUI;
	}
	
	/**
	 * Invisible
	 */
	public void close() {
		this.setVisible(false);
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {

	}

}
