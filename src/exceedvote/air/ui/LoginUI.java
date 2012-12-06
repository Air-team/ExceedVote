
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
	
	private JComboBox comboBox;
	
	private String[] language = {"eng","th"};  //$NON-NLS-1$ //$NON-NLS-2$

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
		txtpnLogin.setFont(new Font("Tahoma", Font.PLAIN, 26)); //$NON-NLS-1$
		txtpnLogin.setEditable(false);
		txtpnLogin.setText(Messages.getString("LoginUI.text.login")); //$NON-NLS-1$
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
		btnLogin.setText(Messages.getString("LoginUI.butt.login")); //$NON-NLS-1$
		btnLogin.setBounds(119, 164, 89, 23);
		contentPane.add(btnLogin);

		btnRegister = new JButton(new Regis());
		btnRegister.setText(Messages.getString("LoginUI.butt.register")); //$NON-NLS-1$
		btnRegister.setBounds(222, 164, 95, 23);
		contentPane.add(btnRegister);

		JLabel lblUsername = new JLabel(Messages.getString("LoginUI.label.username")); //$NON-NLS-1$
		lblUsername.setBounds(49, 95, 60, 14);
		contentPane.add(lblUsername);

		JLabel lblPassword = new JLabel(Messages.getString("LoginUI.label.password")); //$NON-NLS-1$
		lblPassword.setBounds(49, 126, 60, 14);
		contentPane.add(lblPassword);
		
		comboBox = new JComboBox();
		comboBox.setToolTipText(""); //$NON-NLS-1$
		comboBox.setBounds(369, 231, 55, 20);
		contentPane.add(comboBox); 
		
		for(int i = 0 ; i < language.length ; i++)
		comboBox.addItem(language[i]);
		
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			    String select = ((String)comboBox.getSelectedItem()).toString();
			    if(((String)comboBox.getSelectedItem()).toString().equals("eng")) select =""; //$NON-NLS-1$ //$NON-NLS-2$
				Messages.setLocale(select);
			}
		});
		
		JLabel lblSelectLanguage = new JLabel("Choose Language:"); //$NON-NLS-1$
		lblSelectLanguage.setBounds(260, 234, 113, 14);
		contentPane.add(lblSelectLanguage);

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
                  	serviceUI.addUI("AdminUI", adminUI); //$NON-NLS-1$
                  	adminUI.addService(serviceUI);
                  	adminUI.run(""); //$NON-NLS-1$
                  	close();
             
                  }
                  else if(login.hasCommittee()){
                	  Committee committee = login.getCommittee();
                  	 ControlPanel controlPanel = new ControlPanel(committee);
                  		serviceUI.addUI("ControlPanel", controlPanel); //$NON-NLS-1$
                  		controlPanel.addService(serviceUI);
                  		controlPanel.run(""); //$NON-NLS-1$
                      	close();
                  }
            	  
                  else {
                	  
                JOptionPane.showConfirmDialog((Component)
                    null, Messages.getString("LoginUI.pop.wrong.namepass"), Messages.getString("LoginUI.pop.incorrect"), JOptionPane.DEFAULT_OPTION); //$NON-NLS-1$ //$NON-NLS-2$
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
        		serviceUI.addUI("voteUI",voteUI); //$NON-NLS-1$
        		serviceUI.addUI("voteTypeUI",voteTypeUI); //$NON-NLS-1$
        		voteTypeUI.addService(serviceUI);
        		voteUI.addService(serviceUI);
        		TimerTask clocktask = new ClockTask( t );
        		Timer timer = new Timer();
        		long delay = 1000 - System.currentTimeMillis()%1000;
        		final long INTERVAL = 1000; 
        		timer.scheduleAtFixedRate(clocktask, delay, INTERVAL);
        		t.start();
        		voteTypeUI.run("voteTypeUI"); //$NON-NLS-1$
        		close();
        		
                JOptionPane.showConfirmDialog((Component)
                    null, Messages.getString("LoginUI.pop.logincomplete"), Messages.getString("LoginUI.pop.correct"), JOptionPane.DEFAULT_OPTION); //$NON-NLS-1$ //$NON-NLS-2$
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
			serviceUI.addUI("RegisUI",re); //$NON-NLS-1$
			re.addService(serviceUI);
			re.run(""); //$NON-NLS-1$
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

