package exceedvote.air.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import java.awt.Color;
import java.awt.Font;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JButton;

import exceedvote.air.controller.ControllerControl;
import exceedvote.air.controller.ControllerVote;
import exceedvote.air.model.Clock;

import exceedvote.air.model.VoteTopic;


/**
 * VoteType user interface indicate each topic that you can select
 * 
 * @author AIr Team
 * @version 2012.11.3
 */
public class VoteTypeUI extends JFrame implements RunUI, Observer {

	// private VoteTypeUI voteTypeUI;
	private JPanel contentPane;
	private JTextPane txtpnVotetype = new JTextPane();
	private JButton btnHistory = new JButton(
			Messages.getString("VoteTypeUI.butt.history")); //$NON-NLS-1$
	private JButton btnPoll = new JButton(
			Messages.getString("VoteTypeUI.butt.poll")); //$NON-NLS-1$
	
	private JButton btnLogout;

		
	private JLabel lblSelectTheType = new JLabel(
			Messages.getString("VoteTypeUI.label.clicktype")); //$NON-NLS-1$

	// button submit
	private JButton btnGoToVote;
	private JLabel select = new JLabel(
			Messages.getString("VoteTypeUI.label.select")); //$NON-NLS-1$

	// label shows which user select type
	private JLabel selectType;
	private String labelSelect = ""; //$NON-NLS-1$

	// voteUi run after user select and click submit button
	// ballotleft
	private JLabel ballotLeft;

	private Map<String, JButton> dynamicButtons = new HashMap<String, JButton>();

	// service for call other ui
	SeviceUI serviceUI;
	private Clock clock;
	private Font font;
	private int ballot = 0;
	private Object[] names;
	private JTextField fieldWatch = new JTextField();
	private int lastPos = 0;
	Object user;

	/**
	 * Create the frame.
	 */

	public VoteTypeUI(Clock clock) {
		ControllerControl control = ControllerControl.getInstance();
		names = control.getTopicArray();
		this.clock = clock;
	}
	
	public void ControlPanelBtn(){
		initComponent();
		JButton btnControl = new JButton(new ActionControlPanel());
		btnControl.setVisible(true);
		btnControl.setBounds(20, lastPos + 30 , 150, 23);
		btnControl.setText("contro Panel");
		contentPane.add(btnControl);
		this.setVisible(true);
		this.setResizable(true);
		
	}

	/**
	 * set all component
	 */
	public void initComponent() {
		
		font = new Font("Monaco", Font.BOLD, 20); //$NON-NLS-1$

		setBounds(100, 100, 450, 478);

		contentPane = new JPanel();

		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtpnVotetype.setEditable(false);
		txtpnVotetype.setFont(new Font("Tahoma", Font.PLAIN, 36)); //$NON-NLS-1$
		txtpnVotetype.setText(Messages.getString("VoteTypeUI.text.votetype")); //$NON-NLS-1$
		txtpnVotetype.setBounds(10, 11, 176, 50);
		contentPane.add(txtpnVotetype);

		lblSelectTheType.setBounds(20, 72, 185, 14);
		contentPane.add(lblSelectTheType);

		setButton();
		
		
		btnGoToVote = new JButton(new ActionSubmit());
		btnGoToVote.setText(Messages.getString("VoteTypeUI.butt.gonext")); //$NON-NLS-1$
		btnGoToVote.setBounds(232, lastPos + 50, 169, 23);
		contentPane.add(btnGoToVote);

		select.setBounds(30, lastPos + 50, 46, 14);
		contentPane.add(select);

		selectType = new JLabel();
		selectType.setText(Messages.getString("VoteTypeUI.text.none")); //$NON-NLS-1$
		selectType.setBounds(86, lastPos + 50, 199, 14);
		contentPane.add(selectType);
		ControllerVote voteControl = ControllerVote.getInstance();
		ballot = voteControl.checkAmountBallot();
		String shows = Messages.getString("VoteTypeUI.str.uhave") + String.valueOf(ballot) + Messages.getString("VoteTypeUI.str.ballot"); //$NON-NLS-1$ //$NON-NLS-2$
		ballotLeft = new JLabel(shows);
		ballotLeft.setBounds(267, 11, 142, 14);
		contentPane.add(ballotLeft);

		fieldWatch = new JTextField(80);
		fieldWatch.setText("00:00:00"); //$NON-NLS-1$
		fieldWatch.setBounds(280, 30, 218, 23);
		fieldWatch.setFont(font);
		fieldWatch.setBackground(Color.WHITE);
		fieldWatch.setBorder(null);
		fieldWatch.setForeground(Color.orange);
		contentPane.add(fieldWatch);

		btnHistory = new JButton(new historyAction());
		btnHistory.setText(Messages.getString("VoteTypeUI.butt.history")); //$NON-NLS-1$
		btnHistory.setBounds(150, lastPos + 50, 89, 23);
		contentPane.add(btnHistory);

		btnPoll = new JButton(new pollAction());
		btnPoll.setText(Messages.getString("VoteTypeUI.bott.poll")); //$NON-NLS-1$
		btnPoll.setBounds(180, 60, 218, 23);
		

		btnLogout = new JButton(new ActionLogout());
		btnLogout.setVisible(true);
		btnLogout.setBounds(180, lastPos + 80 , 218, 23);
		btnLogout.setText("Log out");
		contentPane.add(btnLogout);
		
		
		
		if (clock.isRun() == false)
			btnPoll.setEnabled(true);
		else
			btnPoll.setEnabled(false);
		contentPane.add(btnPoll);
		lastPos = lastPos + 50;
		setSize(430, lastPos + 100);
		
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				serviceUI.runByName("LoginUI");
				
				
			}
		});
	
		

	}
        /*
         *remove  button by name
         *@param String name of button that you want to remove
         */
	public void removeButton(String name) {
		JButton button = dynamicButtons.remove(name);
		contentPane.remove(button);
		contentPane.invalidate();
		contentPane.repaint();

	}
	/*
         *Set a button in the panel
         */
	private void setButton() {
		int pos = 55;
		for (int i = 0; i < names.length; i++) {
			pos += 30;
			JButton topicBtn = new JButton();
			topicBtn.setText(((VoteTopic) names[i]).getTitle());
			topicBtn.addActionListener(new ActionSelect());
			dynamicButtons.put(((VoteTopic) names[i]).getTitle(), topicBtn);
			topicBtn.setBounds(20, pos, 379, 30);
			contentPane.add(topicBtn);
			if (i == (names.length - 1)) {
				lastPos = pos;

			}
		}

		contentPane.invalidate();
		contentPane.repaint();

	}
	
	/**
	 * Action for control panel button
	 */
	private class ActionControlPanel extends AbstractAction {

		public ActionControlPanel() {
			super();
		}

		public void actionPerformed(ActionEvent e) {
			serviceUI.runByName("ControlPanel");
			close();
		}
	}

	/**
	 * Action for poll button
	 */
	private class pollAction extends AbstractAction {

		public pollAction() {
			super();
		}

		public void actionPerformed(ActionEvent e) {
			ControllerControl control = ControllerControl.getInstance();
			TotalResult result = new TotalResult();
			result.addData(control.getTotalResult());
			result.run();

		}
	}

	
	/**
	 * Action for logout button
	 */
	private class historyAction extends AbstractAction {

		public historyAction() {
			super();
		}

		public void actionPerformed(ActionEvent e) {
			ControllerVote controlVote = ControllerVote.getInstance();
			controlVote.getHistory();
		}

	}
	
	/**
	 * Action for history button
	 */
	private class ActionLogout extends AbstractAction {

		public ActionLogout() {
			super();
		}

		public void actionPerformed(ActionEvent e) {
			serviceUI.runByName("LoginUI");
			close();
			
		}

	}

	/**
	 * action event when user select After user click any type, the type will
	 * show in button of interface
	 */
	public class ActionSelect extends AbstractAction {

		public ActionSelect() {
			super();
		}

		public void actionPerformed(ActionEvent e) {
			JButton o = (JButton) e.getSource();
			labelSelect = o.getText();
			selectType.setText(labelSelect);
		}
	}

	/**
	 * Action event when user click submit button if type was select, run
	 * voterUi. Show messagebox when user click submit but don't select any
	 * type.
	 */
	private class ActionSubmit extends AbstractAction {

		public ActionSubmit() {
			super();
		}

		public void actionPerformed(ActionEvent e) {
			if (labelSelect.equals("")) //$NON-NLS-1$
			{
				JOptionPane
						.showConfirmDialog(
								(Component) null,
								Messages.getString("VoteTypeUI.pop.clicktype"), Messages.getString("VoteTypeUI.pop.selecttype"), JOptionPane.DEFAULT_OPTION); //$NON-NLS-1$ //$NON-NLS-2$
			} else {
				serviceUI.runByName("voteUI", labelSelect); //$NON-NLS-1$

			}
		}
	}
        /**
         * close this frame
         */
	
	
	public void close() {
		this.setVisible(false);
	}

	public void addService(SeviceUI serviceUI) {
		this.serviceUI = serviceUI;
	}

	/**
	 * run this frame
	 */
	public void run(String info) {
		this.initComponent();
		this.setVisible(true);
		this.setResizable(true);	
	}

	@Override
	/**
	 * Update every second
	 */
	public void update(Observable o, Object arg) {

		if (clock.isRun() == false) {
			btnPoll.setEnabled(true);
			fieldWatch.setText("00:00:00");} //$NON-NLS-1$
		else
			fieldWatch.setText(clock.time());

	}
	
	
	
	

}
