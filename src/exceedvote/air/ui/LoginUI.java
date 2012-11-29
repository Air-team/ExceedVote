
package exceedvote.air.ui;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import java.awt.Font;
import java.awt.Color;
import java.util.TimerTask;
import java.util.Timer;
import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import exceedvote.air.model.Ballot;
import exceedvote.air.model.Clock;
import exceedvote.air.model.ClockTask;
import exceedvote.air.model.Committee;
import exceedvote.air.model.Login;
import exceedvote.air.model.Voter;

/**
 * Login user Interface waiting for test with database
 * 
 * @author AIr Team
 * @version 2012.10.25
 */
public class LoginUI extends JFrame implements RunUI {

	private JPanel contentPane;

	// username test field
	private JTextField usernameType;
	// password test field
	private JPasswordField passwordType;

	private JTextPane txtpnLogin = new JTextPane();

	// button submit login
	private JButton btnLogin;
	// button submit register
	private JButton btnRegister;

	// serviceUI for back to loginUI
	private SeviceUI serviceUI;
	
	private Login login;

	/**
	 * Create the frame.
	 */
	public LoginUI() {

	}

	/*
	 * set all component
	 */
	public void initComponent() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtpnLogin.setBackground(Color.WHITE);
		txtpnLogin.setForeground(Color.BLACK);
		txtpnLogin.setFont(new Font("Tahoma", Font.PLAIN, 26));
		txtpnLogin.setEditable(false);
		txtpnLogin.setText("Login");
		txtpnLogin.setBounds(10, 11, 186, 38);
		contentPane.add(txtpnLogin);

		usernameType = new JTextField();
		usernameType.setBounds(119, 92, 230, 20);
		contentPane.add(usernameType);
		usernameType.setColumns(10);

		passwordType = new JPasswordField();
		passwordType.setBounds(119, 123, 230, 20);
		contentPane.add(passwordType);

		btnLogin = new JButton(new UpAction());
		btnLogin.setText("Login");
		btnLogin.setBounds(119, 164, 89, 23);
		contentPane.add(btnLogin);

		btnRegister = new JButton(new Regis());
		btnRegister.setText("Register");
		btnRegister.setBounds(222, 164, 89, 23);
		contentPane.add(btnRegister);

		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(49, 95, 60, 14);
		contentPane.add(lblUsername);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(49, 126, 60, 14);
		contentPane.add(lblPassword);
	}

	/*
	 * action in login button
	 */
	 private class UpAction extends AbstractAction{ 
		 
        public UpAction()
        { 
            super(); 
        } 
        

        public void actionPerformed(ActionEvent e)
        {  
        	login = new Login(usernameType.getText(),passwordType.getText());
            boolean correct = login.hasVoter();
            if(!correct)
            {
            	  if(login.isAdmin()) {
                  	AdminUI adminUI = new AdminUI();
                  	serviceUI.addUI("AdminUI", adminUI);
                  	adminUI.addService(serviceUI);
                  	adminUI.run("");
                  	close();
             
                  }
                  else if(login.hasCommittee()){
                	  Committee committee = login.getCommittee();
                  	 ControlPanel controlPanel = new ControlPanel(committee);
                  		serviceUI.addUI("ControlPanel", controlPanel);
                  		controlPanel.addService(serviceUI);
                  		controlPanel.run("");
                      	close();
                  }
            	  
                  else {
                	  
                JOptionPane.showConfirmDialog((Component)
                    null, "Wrong username or password", "Incorrect!!!", JOptionPane.DEFAULT_OPTION);
                  }
            }
           
            else
            {
            	Voter voter = login.getVoter();
            	
            	Clock t = new Clock();
            	VoteUI voteUI = new VoteUI(voter,t);
        		t.addObserver(voteUI);
        		
        	
        		VoteTypeUI voteTypeUI = new VoteTypeUI(voter,t);
        		t.addObserver(voteTypeUI);
        		serviceUI.addUI("voteUI",voteUI);
        		serviceUI.addUI("voteTypeUI",voteTypeUI);
        		voteTypeUI.addService(serviceUI);
        		voteUI.addService(serviceUI);
        		TimerTask clocktask = new ClockTask( t );
        		Timer timer = new Timer();
        		long delay = 1000 - System.currentTimeMillis()%1000;
        		final long INTERVAL = 1000; 
        		timer.scheduleAtFixedRate(clocktask, delay, INTERVAL);
        		t.start();
        		voteTypeUI.run("voteTypeUI");
        		close();
        		
                JOptionPane.showConfirmDialog((Component)
                    null, "Login Complete", "Correct", JOptionPane.DEFAULT_OPTION);
            }
            //System.out.print(result);
        }
    }

	 
	 
	/*
	 * action in regist button
	 */
	private class Regis extends AbstractAction {

		public Regis() {
			super();
		}

		public void actionPerformed(ActionEvent e) {
			RegisterUI re = new RegisterUI();
			SeviceUI serviceUI = new SeviceUI();
			serviceUI.addUI("RegisUI",re);
			re.addService(serviceUI);
			re.run("");
		}
	}

	public void addService(SeviceUI serviceUI) {
		this.serviceUI = serviceUI;
	}
	
 	public void close() {
   		this.setVisible(false);
   	}
   	
   
    

	/*
	 * start this frame
	 */
	public void run(String info) {
		this.initComponent();
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	// /*
	// * main to start this frame
	// * (test)
	// */
//	 public static void main(String[] args)
//	 {
//	 LoginUI logui = new LoginUI();
//	 logui.run("");
//	 }
}

