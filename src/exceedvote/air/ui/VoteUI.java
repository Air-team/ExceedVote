package exceedvote.air.ui;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.JTextPane;

import exceed.air.controller.ControllerControl;
import exceed.air.controller.ControllerVote;
import exceedvote.air.model.Ballot;
import exceedvote.air.model.Clock;
import exceedvote.air.model.Committee;

import exceedvote.air.model.Team;
import exceedvote.air.model.Voter;
import java.awt.Color;
import java.awt.Font;
import java.text.DateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * eXceed Vote GUI
 * 
 * @author AIr Team
 * @version 2012.10.1
 */
public class VoteUI extends JFrame implements RunUI, Observer {
	private JPanel contentPane;

	private final JLabel label = new JLabel("New label"); //$NON-NLS-1$
	/**
	 * Set the all of components and add action o each components
	 */
	private final JTextField textField_1 = new JTextField();

	private JPanel listTeam = new JPanel();
	private JTextPane status = new JTextPane();
	private JTextPane txtpnTeamlist = new JTextPane();
	private JButton btnViewInfomation = new JButton(
			Messages.getString("VoteUI.butt.viewinfo")); //$NON-NLS-1$
	private JButton voteBtn;
	private JTextPane teamSelect = new JTextPane();
	private JButton revoteBtn = new JButton("-"); //$NON-NLS-1$
	private JLabel lblClickToSelect = new JLabel(
			Messages.getString("VoteUI.label.clickteam")); //$NON-NLS-1$
	private JButton btnHistory = new JButton(
			Messages.getString("VoteUI.butt.history")); //$NON-NLS-1$
	private JLabel lblNewLabel = new JLabel(
			Messages.getString("VoteUI.label.viewhistory")); //$NON-NLS-1$
	private JLabel lblVote = new JLabel(Messages.getString("VoteUI.label.vote")); //$NON-NLS-1$
	private JLabel lblRevote = new JLabel(
			Messages.getString("VoteUI.label.revote")); //$NON-NLS-1$
	private JTextField fieldWatch = new JTextField();
	private JButton btnBack;
	private Font font;

	/*
	 * ballot test
	 */
	int ballot;
	String type = ""; //$NON-NLS-1$
	String nameVoter = ""; //$NON-NLS-1$
	/*
	 * name team test
	 */

	private String typeTeam = ""; //$NON-NLS-1$

	Object[] names;
	// name select
	String selectTeam = ""; //$NON-NLS-1$
	// Voter

	private Clock clock;
	private Map<String, JButton> dynamicButtons = new HashMap<String, JButton>();
	private Object user;
	private ControllerVote controllerVote = ControllerVote.getInstance();
	private ControllerControl controllerControl = ControllerControl
			.getInstance();

	public VoteUI(Clock clock) {
		names = controllerControl.getTeam();
		controllerVote.checkAmountBallot();
		ballot = controllerVote.checkAmountBallot();
		this.clock = clock;
		initComponent();
	}

	// service for call other ui
	SeviceUI serviceUI;


	public void removeTeam(String name) {
		JButton button = dynamicButtons.remove(name);
		contentPane.remove(button);
		contentPane.invalidate();
		contentPane.repaint();
	}

	/**
	 * Set the all of components and add action o each components
	 */
	public void initComponent() {

		font = new Font("Monaco", Font.BOLD, 20); //$NON-NLS-1$

		textField_1.setColumns(10);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 414);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		listTeam.setBounds(10, 64, 218, 301);
		contentPane.add(listTeam);
		listTeam.setLayout(new GridLayout(20, 1, 0, 0));

		status.setBounds(242, 11, 218, 30);
		contentPane.add(status);
		status.setBackground(Color.BLACK);
		status.setFont(new Font("Tahoma", Font.PLAIN, 15)); //$NON-NLS-1$
		status.setText(Messages.getString("VoteUI.label.status") + type + " : " + String.valueOf(ballot) + Messages.getString("VoteUI.label.ballot")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		status.setForeground(Color.ORANGE);
		status.setEditable(false);

		txtpnTeamlist.setFont(new Font("Tahoma", Font.PLAIN, 15)); //$NON-NLS-1$
		txtpnTeamlist.setBackground(Color.BLACK);
		txtpnTeamlist.setEditable(false);
		txtpnTeamlist.setForeground(Color.ORANGE);
		txtpnTeamlist.setText(Messages.getString("VoteUI.label.teamlist")); //$NON-NLS-1$
		txtpnTeamlist.setBounds(10, 11, 218, 23);
		contentPane.add(txtpnTeamlist);

		btnViewInfomation = new JButton(
				Messages.getString("VoteUI.butt.viewinfo")); //$NON-NLS-1$
		btnViewInfomation.addActionListener(new btnInfoAction());
		btnViewInfomation.setBounds(250, 100, 130, 23);
		contentPane.add(btnViewInfomation);
		updateTeam();

		// add action to vote button
		voteBtn = new JButton(new UpAction());
		voteBtn.setText("+"); //$NON-NLS-1$
		voteBtn.setBounds(250, 152, 89, 23);
		contentPane.add(voteBtn);

		revoteBtn = new JButton(new ReVoteAction());
		revoteBtn.setText("-"); //$NON-NLS-1$
		revoteBtn.setBounds(349, 152, 89, 23);
		contentPane.add(revoteBtn);

		 btnHistory = new JButton(new historyAction());
		btnHistory.setText(Messages.getString("VoteUI.butt.history")); //$NON-NLS-1$
		btnHistory.setBounds(250, 216, 89, 23);
		contentPane.add(btnHistory);

		teamSelect.setEditable(false);
		teamSelect.setBackground(Color.BLACK);
		teamSelect.setForeground(Color.ORANGE);
		teamSelect.setFont(new Font("Tahoma", Font.PLAIN, 15)); //$NON-NLS-1$
		teamSelect.setText(Messages.getString("VoteUI.label.team")); //$NON-NLS-1$
		teamSelect.setBounds(242, 59, 206, 30);
		contentPane.add(teamSelect);

		revoteBtn.setBounds(349, 152, 89, 23);
		contentPane.add(revoteBtn);

		lblVote.setFont(new Font("Tahoma", Font.PLAIN, 13)); //$NON-NLS-1$
		lblVote.setForeground(Color.ORANGE);
		lblVote.setBounds(249, 138, 46, 14);
		contentPane.add(lblVote);

		lblRevote.setForeground(Color.ORANGE);
		lblRevote.setBounds(349, 139, 46, 14);
		contentPane.add(lblRevote);

		lblClickToSelect.setForeground(Color.ORANGE);
		lblClickToSelect.setBounds(10, 45, 210, 14);
		contentPane.add(lblClickToSelect);

		btnHistory.setBounds(250, 216, 89, 23);
		contentPane.add(btnHistory);

		lblNewLabel.setForeground(Color.ORANGE);
		lblNewLabel.setBounds(250, 200, 188, 14);
		contentPane.add(lblNewLabel);

		btnBack = new JButton(new backAction());
		btnBack.setText(Messages.getString("VoteUI.butt.back")); //$NON-NLS-1$
		btnBack.setBounds(349, 342, 89, 23);
		contentPane.add(btnBack);

		fieldWatch = new JTextField(80);
		fieldWatch.setBounds(250, 245, 218, 23);
		fieldWatch.setText("00:00:00"); //$NON-NLS-1$
		fieldWatch.setFont(font);
		fieldWatch.setBackground(Color.BLACK);
		fieldWatch.setForeground(Color.orange);
		contentPane.add(fieldWatch);

		if (clock.isRun() == false)
			closeBtn();
	}

	@Override
	public void update(Observable o, Object arg) {

		if (clock.isRun() == false) {
			closeBtn();
			fieldWatch.setText("00:00:00");} //$NON-NLS-1$
		else if (clock.isRun() == true) {
			openBtn();
			fieldWatch.setText(clock.time());
		}
	}

	private void closeBtn() {
		voteBtn.setEnabled(false);
		revoteBtn.setEnabled(false);
	}

	private void openBtn() {
		voteBtn.setEnabled(true);
		revoteBtn.setEnabled(true);
	}

	private class btnInfoAction extends AbstractAction {
		public btnInfoAction() {
			super();
		}

		public void actionPerformed(ActionEvent e) {
			String teamName = selectTeam;

			InfomationUI informationUI = new InfomationUI(teamName);
			informationUI.run(""); //$NON-NLS-1$

		}
	}

	 private class historyAction extends AbstractAction{
	
	 public historyAction()
	 {
	 super();
	 }
	
	 public void actionPerformed(ActionEvent e){
		 ControllerVote controlVote = ControllerVote.getInstance();
		 controlVote.getHistory();
	
	 }
	
	 }

	private class UpAction extends AbstractAction {

		public UpAction() {
			super();
		}

		public void actionPerformed(ActionEvent e) {
			if (selectTeam.equals("")) //$NON-NLS-1$
			{
				JOptionPane
						.showConfirmDialog(
								(Component) null,
								Messages.getString("VoteUI.pop.clickteam"), Messages.getString("VoteUI.pop.selectteam"), JOptionPane.DEFAULT_OPTION); //$NON-NLS-1$ //$NON-NLS-2$
			} else if (ballot == 0) {
				JOptionPane
						.showConfirmDialog(
								(Component) null,
								Messages.getString("VoteUI.pop.noballot"), Messages.getString("VoteUI.pop.nobal"), JOptionPane.DEFAULT_OPTION); //$NON-NLS-1$ //$NON-NLS-2$
			} else {
				String alert = Messages.getString("VoteUI.str.wantvote") + selectTeam; //$NON-NLS-1$
				int result = JOptionPane
						.showConfirmDialog(
								(Component) null,
								alert,
								Messages.getString("VoteUI.pop.submitvote"), JOptionPane.YES_NO_OPTION); //$NON-NLS-1$
				if (result == 0) {

					boolean canVote = false;
					ControllerVote user = ControllerVote.getInstance();
					canVote = user.putBallot(selectTeam, typeTeam);
					if (canVote) {
						ballot--;
						status.setText(Messages
								.getString("VoteUI.label.set.status") + type + " : " + String.valueOf(ballot) + Messages.getString("VoteUI.label.set.ballot")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					}

				}
			}

		}
	}

	private class ReVoteAction extends AbstractAction {
		public ReVoteAction() {
			super();
		}

		public void actionPerformed(ActionEvent arg0) {

			if (selectTeam.equals("")) //$NON-NLS-1$
			{
				JOptionPane
						.showConfirmDialog(
								(Component) null,
								Messages.getString("VoteUI.pop.clickteam"), Messages.getString("VoteUI.pop.selectteam"), JOptionPane.DEFAULT_OPTION); //$NON-NLS-1$ //$NON-NLS-2$
			}

			else {
				String alert = Messages.getString("VoteUI.str.disvote") + selectTeam; //$NON-NLS-1$
				int result = JOptionPane
						.showConfirmDialog(
								(Component) null,
								alert,
								Messages.getString("VoteUI.pop.submitvote"), JOptionPane.YES_NO_OPTION); //$NON-NLS-1$
				if (result == 0) {
					boolean canVote = false;

					ControllerVote user = ControllerVote.getInstance();
					canVote = user.returnBallot(selectTeam, typeTeam);

					if (canVote) {
						ballot++;
						status.setText(Messages
								.getString("VoteUI.label.set.status") + type + " : " + String.valueOf(ballot) + Messages.getString("VoteUI.label.set.ballot")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					} else {
						JOptionPane
								.showConfirmDialog(
										(Component) null,
										Messages.getString("VoteUI.pop.novote"), Messages.getString("VoteUI.pop.selectteam"), JOptionPane.DEFAULT_OPTION); //$NON-NLS-1$ //$NON-NLS-2$
					}

				}
			}
		}
	}

	private class ActionSelect extends AbstractAction {

		public ActionSelect() {
			super();
		}

		public void actionPerformed(ActionEvent e) {
			JButton o = (JButton) e.getSource();
			selectTeam = o.getText();
			teamSelect
					.setText(Messages.getString("VoteUI.label.set.team") + selectTeam); //$NON-NLS-1$
		}
	}

	public void reTeam() {
		listTeam.removeAll();
	}

	private class backAction extends AbstractAction {

		public backAction() {
			super();
		}

		public void actionPerformed(ActionEvent e) {
			serviceUI.runByName("voteTypeUI");
			close();
			
		}
	}

	public void close() {
		setVisible(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/*
	 * set team in UI,
	 */
	public void updateTeam() {
		// reTeam();
		//
		for (int i = 0; i < names.length; i++) {
			JButton Btn = new JButton();
			Btn.setText(((Team) names[i]).getName());
			Btn.addActionListener(new ActionSelect());
			dynamicButtons.put(((Team) names[i]).getName(), Btn);
			listTeam.add(Btn);

		}

		contentPane.invalidate();
		contentPane.repaint();
	}

	public void setType(String type) {
		this.typeTeam = type;
		txtpnTeamlist
				.setText(Messages.getString("VoteUI.label.set.teamlist") + typeTeam); //$NON-NLS-1$
	}

	public void addService(SeviceUI serviceUI) {
		this.serviceUI = serviceUI;
	}

	/*
	 * run Frame
	 */
	public void run(String type) {
		// this.initComponent();
		this.setType(type);
		this.setVisible(true);
		this.setResizable(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
