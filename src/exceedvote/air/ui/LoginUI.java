package exceedvote.air.ui;
//package exceedvote.air.ui;
 

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
/**
 * Login user Interface 
 * waiting for test with database
 * 
 * @author AIr Team
 * @version 2012.10.25
 */
public class LoginUI extends JFrame implements RunUI
{
    
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
    
	private JLabel lblUsername = new JLabel("Username");
	private JLabel lblPassword = new JLabel("Password");
	//private Message message  = null;
	
	//service for call other ui
    private SeviceUI serviceUI;
	
	private String[] language = {"eng","th"};
	/**
	 * Create the frame.
	 */
	public LoginUI() 
	{
		
	}
	
	/*
	 * set all component
	 */
	public void initComponent()
	{
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
		
		btnRegister = new JButton("Register");
		btnRegister.setBounds(222, 164, 89, 23);
		contentPane.add(btnRegister);
		
		lblUsername.setBounds(49, 95, 60, 14);
		contentPane.add(lblUsername);
		
		lblPassword.setBounds(49, 126, 60, 14);
		contentPane.add(lblPassword);
		
		comboBox = new JComboBox();
		comboBox.setToolTipText("");
		comboBox.setBounds(369, 231, 55, 20);
		contentPane.add(comboBox); 
		
		for(int i = 0 ; i < language.length ; i++)
		comboBox.addItem(language[i]);
		
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			    String select = ((String)comboBox.getSelectedItem()).toString();
			    if(((String)comboBox.getSelectedItem()).toString().equals("eng")) select ="";
				//message.setLocal(select);
			}
		});
		
		JLabel lblSelectLanguage = new JLabel("Select Language");
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
            boolean correct = false;
            if(!correct)
            {
                JOptionPane.showConfirmDialog((Component)
                    null, "Wrong username or password", "Incorrect!!!", JOptionPane.DEFAULT_OPTION);
            }
            else
            {
                JOptionPane.showConfirmDialog((Component)
                    null, "Login Complete", "Correct", JOptionPane.DEFAULT_OPTION);
            }
            //System.out.print(result);
        }
    }
	
	public void addService(SeviceUI serviceUI)
    {
        this.serviceUI = serviceUI;
    }
    
	/*
	 * start this frame
	 */
	public void run(String info)
	{
	    this.initComponent();
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/*
	 * main to start this fram
	 * (test)
	 */
	public static void main(String[] args)
	{
	   LoginUI logui = new LoginUI();
	   logui.run("");
	}
}
