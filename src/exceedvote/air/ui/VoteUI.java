package exceedvote.air.ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.JTextPane;

import exceedvote.air.model.Ballot;
import exceedvote.air.model.Voter;


import java.awt.Color;
import java.awt.Font;

/**
 * eXceed Vote GUI
 * 
 * @author AIr Team
 * @version 2012.10.1
 */
public class VoteUI extends JFrame {
	private JPanel contentPane;

	private final JLabel label = new JLabel("New label");
	/**
	 * @wbp.nonvisual location=81,-31
	 */
	private final JTextField textField_1 = new JTextField();

	private JButton voteBtn;
	private JPanel listTeam = new JPanel();
	private JPanel infoPanel = new JPanel();
	private JTextPane teamInfomation = new JTextPane();
	private JPanel statusPanel = new JPanel();
	private JTextPane status = new JTextPane();
	private JPanel ballotPanel = new JPanel();
	JTextPane ballotRemain = new JTextPane();
	/*
	 * ballot test
	 */
	int ballot;
	String type = "";
	String nameVoter = "";
	/*
	 * name team test
	 */
	String[] names;
	// name select
	String select = "";
	// Voter
	private Voter voter;

	public VoteUI(Voter voter) {
		this.voter = voter;
		this.ballot = ((Ballot) this.voter.getBallot()).getValue();
		names = voter.getTeamNames();
		type = voter.getType();
		nameVoter = voter.getName();
	}

	/*
	 * init all component
	 */
	public void initComponent() {
		textField_1.setColumns(10);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 513, 414);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		// button
		voteBtn = new JButton(new UpAction());
		voteBtn.setText("Vote");
		voteBtn.setBounds(329, 342, 89, 23);
		contentPane.add(voteBtn);
		// listTeam
		listTeam.setBounds(10, 54, 218, 311);
		contentPane.add(listTeam);
		listTeam.setLayout(new GridLayout(20, 1, 0, 0));
		// infomationPanel
		infoPanel.setBounds(241, 52, 246, 279);
		contentPane.add(infoPanel);
		infoPanel.setLayout(null);
		// teamInfomation
		teamInfomation.setText("Name : \r\nInfomation : ");
		teamInfomation.setEditable(false);
		teamInfomation.setBounds(0, 0, 246, 279);
		infoPanel.add(teamInfomation);
		// statusPanel
		statusPanel.setBounds(10, 11, 218, 30);
		contentPane.add(statusPanel);
		statusPanel.setLayout(null);
		// status
		status.setBackground(Color.BLACK);
		status.setFont(new Font("Tahoma", Font.PLAIN, 18));
		status.setText(type + " name : " + nameVoter);
		status.setForeground(Color.ORANGE);
		status.setEditable(false);
		status.setBounds(0, 0, 218, 30);
		statusPanel.add(status);
		// ballotPanel
		ballotPanel.setBounds(241, 11, 246, 30);
		contentPane.add(ballotPanel);
		ballotPanel.setLayout(null);
		// ballotRemain
		ballotRemain.setFont(new Font("Tahoma", Font.PLAIN, 19));
		ballotRemain.setText("Ballot : " + String.valueOf(ballot));
		ballotRemain.setForeground(Color.ORANGE);
		ballotRemain.setBackground(Color.BLACK);
		ballotRemain.setBounds(0, 0, 246, 30);
		ballotPanel.add(ballotRemain);
		ballotRemain.setEditable(false);
	}

	private class UpAction extends AbstractAction {

		public UpAction() {
			super();
		}

		public void actionPerformed(ActionEvent e) {
			if (ballot == 0) {
				JOptionPane.showConfirmDialog((Component) null, "No ballot Ja",
						"No Ballot!!", JOptionPane.DEFAULT_OPTION);
			} else {
				String alert = "Do you want to vote " + select;
				int result = JOptionPane.showConfirmDialog((Component) null,
						alert, "Submit Vote!!!", JOptionPane.YES_NO_OPTION);
				if (result == 0) {
					boolean canVote = voter.pullBollot(select);
					if (canVote) {
						ballot--;
						String shows = "YOU HAVE  " + String.valueOf(ballot)
								+ "  BALLOT!!";
						ballotRemain.setText(shows);
					}
				}
			}
			// System.out.print(result);
		}
	}

	private class ActionSelect extends AbstractAction {

		public ActionSelect() {
			super();
		}

		public void actionPerformed(ActionEvent e) {
			JButton o = (JButton) e.getSource();
			select = o.getText();
			teamInfomation.setText("Name : " + select + "\r\nInfomation : ");
		}
	}

	/*
	 * set team in UI,
	 */
	public void setTeam() {
		for (int i = 0; i < names.length; i++) {
			JButton eachTeam = new JButton(new ActionSelect());
			eachTeam.setText(names[i]);
			listTeam.add(eachTeam);
		}
	}

	/*
	 * run Frame
	 */
	public void run() {
		this.initComponent();
		this.setTeam();
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}