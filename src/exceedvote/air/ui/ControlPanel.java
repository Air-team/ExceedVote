package exceedvote.air.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

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
import exceedvote.air.model.Clock;
import exceedvote.air.model.ClockTask;
import exceedvote.air.model.Committee;
import exceedvote.air.model.Team;
import exceedvote.air.model.VoteTopic;
import exceedvote.air.persistence.DaoFactory;

import java.util.TimerTask;


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
		voteUI = new VoteUI();
		voteTypeUI = new VoteTypeUI();
	}

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
	

		tab.addTab("Control", controlPan);
		
		btnVote = new JButton("Vote");
		btnVote.addActionListener(new VoteAction() );
		btnVote.setForeground(Color.BLACK);
		btnVote.setBackground(Color.LIGHT_GRAY);
		btnVote.setBounds(10,150,100,50);
		controlPan.add(btnVote);
	}

	public void setControlPage() {
		// create Controlpane
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
		btnAddteam.setText("AddTeam");
		btnAddteam.setBounds(163, 22, 89, 23);
		controlPan.add(btnAddteam);

		btnCriteria = new JButton(new addCreteriaAction());
		btnCriteria.setText("Criteria");
		btnCriteria.setBounds(160, 59, 89, 23);
		controlPan.add(btnCriteria);

		btnDeleteTeam = new JButton(new deleteTeamAction());
		btnDeleteTeam.setText("DeleteTeam");
		btnDeleteTeam.setBounds(250, 151, 89, 23);
		controlPan.add(btnDeleteTeam);

		btnDeleteTopic = new JButton(new deleteTopicAction());

		btnDeleteTopic.setText("DeleteTopic");
		btnDeleteTopic.setBounds(250, 340, 89, 23);
		controlPan.add(btnDeleteTopic);

		btnSettime = new JButton(new setTimeAction());
		btnSettime.setText("SetTime");
		btnSettime.setBounds(10, 91, 242, 23);
		controlPan.add(btnSettime);

		// String [] teamList = {""};

		labelTeam = new JLabel("Team:");
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

		labelCeteria = new JLabel("Topic:");
		labelCeteria.setBounds(259, 140, 190, 126);
		controlPan.add(labelCeteria);

		listCriteria = new JList();
		listCriteria.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listCriteria.setSelectedIndex(0);
		listCriteria.addListSelectionListener(this);
		topic = commitee.getTopic();
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
			
			Clock t = new Clock();
        	VoteUI voteUICom = new VoteUI(commitee,t);
        	VoteTypeUI voteTypeUICom = new VoteTypeUI(commitee,t);
    		t.addObserver(voteUICom);
			
    		t.addObserver(voteTypeUICom);
    		voteUICom.setCom(commitee);
    		serviceUI.addUI("voteUICom",voteUICom);
    		serviceUI.addUI("voteTypeUICom",voteTypeUICom);
    		voteTypeUICom.addService(serviceUI);
    		voteUICom.addService(serviceUI);
    		
    		TimerTask clocktask = new ClockTask( t );
    		Timer timer = new Timer();


    		long delay = 1000 - System.currentTimeMillis()%1000;
    		final long INTERVAL = 1000; 
    		timer.scheduleAtFixedRate(clocktask, delay, INTERVAL);
    		t.start();
    		voteTypeUICom.run("voteTypeUICom");
    		close();
    		
    		
			
		}
		
	}
	// action for addTeambtn
	private class addTeamAction extends AbstractAction {

		public addTeamAction() {
			super();
		}

		public void actionPerformed(ActionEvent e) {
			String text = addTeamInput.getText();

			if (!teamModel.contains(text)) {
				final AddTeamPanel teamDes = new AddTeamPanel(serviceUI);
				teamDes.setTeamName(text);
				teamDes.setModel(teamModel);
				serviceUI.addUI("teamDes", teamDes);
				teamDes.addService(serviceUI);
				serviceUI.runByName("teamDes");
				
				
				close();

				if (teamDes.check()) {
					teamModel = teamDes.getModel();
					listTeam.setModel(teamModel);
				}

			} else {
				// System.out.println(addTeamInput.getText());
				JOptionPane.showConfirmDialog((Component) null,
						"This name is already used.", "Select the team",
						JOptionPane.DEFAULT_OPTION);
			}

			addTeamInput.setText("");

		}
	}

	public void close() {
		this.setVisible(false);
	}

	// action for addCreteribtn
	private class addCreteriaAction extends AbstractAction {

		public addCreteriaAction() {
			super();

		}

		public void actionPerformed(ActionEvent e) {

			if (!topicModel.contains(criteraiInput.getText())) {

				if (commitee.setTopic(criteraiInput.getText())) {
					voteTypeUI.initComponent();
					Team team = new Team();
					team.refreshMap();
					topicModel.addElement(criteraiInput.getText());
					JOptionPane.showConfirmDialog((Component) null,
							"Add successful", "add the topic",
							JOptionPane.DEFAULT_OPTION);
				}
			} else {
				JOptionPane.showConfirmDialog((Component) null,
						"This name is already used.", "add the topic",
						JOptionPane.DEFAULT_OPTION);
			}
			listCriteria.setModel(topicModel);
			criteraiInput.setText("");

		}

	}

	// action for setTimebtn
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

	// action for deleteTopicbtn
	private class deleteTopicAction extends AbstractAction {

		public deleteTopicAction() {
			super();
		}

		public void actionPerformed(ActionEvent e) {
			int index = listCriteria.getSelectedIndex();
			if (index <= 0)
				index = 0;
			VoteTopic top = topic.get(index);

			if (commitee.deleteTopic(top.getTitle())) {
				topicModel.remove(index);
				int size = topicModel.getSize();

				if (size == 0) {
					btnDeleteTeam.setEnabled(false);
				} else { // Select an index.
					if (index == topicModel.getSize()) {
						// removed item in last position

						index--;
						// voteTypeUI.initComponent();

						listCriteria.setSelectedIndex(index);
						listCriteria.ensureIndexIsVisible(index);
					}

				}
				JOptionPane.showConfirmDialog((Component) null,
						"Delete successful", "delete",
						JOptionPane.DEFAULT_OPTION);
			} else {
				JOptionPane.showConfirmDialog((Component) null,
						"Sorry,can't delete", "delete",
						JOptionPane.DEFAULT_OPTION);
			}

		}
	}

	// action for deleteTeambtn
	private class deleteTeamAction extends AbstractAction {

		public deleteTeamAction() {
			super();
		}

		public void actionPerformed(ActionEvent e) {
			int index = listTeam.getSelectedIndex();
			if (index <= 0)
				index = 0;
			Team t = team.get(index);
			// System.out.println(t.getName());

			if (commitee.deleteTeam(t.getName())) {

				teamModel.remove(index);
				int size = teamModel.getSize();

				if (size == 0) {
					btnDeleteTeam.setEnabled(false);
				} else { // Select an index.
					if (index == teamModel.getSize()) {
						// removed item in last position

						index--;
						Team team = new Team();
						team.refreshMap();
						voteUI.initComponent();

						listTeam.setSelectedIndex(index);
						listTeam.ensureIndexIsVisible(index);

					}
					//
					// voteTypeUI.initComponent();
					// voteTypeUI.removeButton(.getName());

				}

				JOptionPane.showConfirmDialog((Component) null,
						"Delete successful", "delete",
						JOptionPane.DEFAULT_OPTION);
			} else {
				JOptionPane.showConfirmDialog((Component) null,
						"Sorry,can't delete", "delete",
						JOptionPane.DEFAULT_OPTION);
			}

		}

	}

	public void run(String info) {
		this.setControlPage();
		this.initComponent();
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void addService(SeviceUI serviceUI) {
		this.serviceUI = serviceUI;
	}

////	public static void main(String[] args) {
//
//		Committee com = new Committee();
//		com.setTime("27", "11", "2012", "1", "0", "0");
//
//		ControlPanel controlPanel = new ControlPanel(com);
//		SeviceUI serviceUI = new SeviceUI();
//		serviceUI.addUI("controlPanel", controlPanel);
//		controlPanel.addService(serviceUI);
//		controlPanel.run("");
//
//	}

	@Override
	public void valueChanged(ListSelectionEvent e) {

	}

}
