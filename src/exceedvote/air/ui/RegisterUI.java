package exceedvote.air.ui;

/*
 * what changed
 * implements RunUI
 * add method addService
 * add method close
 * add actionListener in cancel button
 * 
 */
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextPane;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JRadioButton;

import exceedvote.air.model.Register;

public class RegisterUI extends JFrame implements ActionListener, RunUI {

	private JPanel contentPane;
	/**
	 * @wbp.nonvisual location=37,-31
	 */
	private final JButton button = new JButton("New button");
	private JPasswordField passwordInput;
	private JPasswordField passwordConfirm;
	private JTextField usenameInput;

	private JButton btnSumit;
	private JButton btnCancel;
	private JRadioButton typeTeacher;
	private JRadioButton typeStudent;

	private String type;

	// serviceUI for back to loginUI
	private SeviceUI serviceUI;

	public RegisterUI() {
	}

	/**
	 * Create the frame.
	 */
	public void initComponent() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 484, 319);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnSumit = new JButton(new submitAction());
		btnSumit.setText("Submit");
		btnSumit.setBounds(120, 243, 89, 23);
		contentPane.add(btnSumit);

		btnCancel = new JButton(new CancelAction());
		btnCancel.setBounds(225, 243, 89, 23);
		btnCancel.setText("Cancel");
		contentPane.add(btnCancel);

		JTextPane txtpnRegister = new JTextPane();
		txtpnRegister.setFont(new Font("Tahoma", Font.PLAIN, 22));
		txtpnRegister.setText("Register");
		txtpnRegister.setBounds(10, 11, 188, 38);
		contentPane.add(txtpnRegister);

		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(21, 76, 65, 14);
		contentPane.add(lblUsername);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(21, 112, 65, 14);
		contentPane.add(lblPassword);

		JLabel lblComfermPassword = new JLabel("Confirm Password");
		lblComfermPassword.setBounds(21, 143, 104, 14);
		contentPane.add(lblComfermPassword);

		passwordInput = new JPasswordField("");
		passwordInput.setBounds(120, 109, 220, 20);
		passwordInput.addActionListener(this);
		contentPane.add(passwordInput);

		passwordConfirm = new JPasswordField("");
		passwordConfirm.setBounds(120, 140, 220, 20);
		passwordConfirm.addActionListener(this);
		contentPane.add(passwordConfirm);

		usenameInput = new JTextField("");
		usenameInput.setBounds(120, 73, 220, 20);
		contentPane.add(usenameInput);
		usenameInput.addActionListener(this);
		usenameInput.setColumns(10);

		JLabel lblType = new JLabel("Type");
		lblType.setBounds(21, 202, 46, 14);
		contentPane.add(lblType);

		typeTeacher = new JRadioButton("Teacher");
		typeTeacher.setBackground(Color.WHITE);
		typeTeacher.setBounds(120, 198, 109, 23);
		typeTeacher.setMnemonic(KeyEvent.VK_0);
		typeTeacher.setActionCommand("Teacher");
		typeTeacher.addActionListener(this);
		contentPane.add(typeTeacher);

		typeStudent = new JRadioButton("Student");
		typeStudent.setBackground(Color.WHITE);
		typeStudent.setBounds(231, 198, 109, 23);
		typeStudent.setMnemonic(KeyEvent.VK_1);
		typeStudent.setActionCommand("Student");
		typeStudent.addActionListener(this);
		contentPane.add(typeStudent);

		ButtonGroup group = new ButtonGroup();
		group.add(typeTeacher);
		group.add(typeStudent);

	}

	private class submitAction extends AbstractAction {
		public submitAction() {
			super();
		}

		public void actionPerformed(ActionEvent e) {
			String username = usenameInput.getText();
			String pass = passwordInput.getText();
			String passConfrim = passwordConfirm.getText();

			if (username.equals("") || pass.equals("")
					|| passConfrim.equals("")) {
				
				JOptionPane.showConfirmDialog((Component) null,
						"Please fill completely information", "Fill info",
						JOptionPane.DEFAULT_OPTION);
			} else if (username != "" && pass != "" && passConfrim != "") {
				if (pass.equals(passConfrim)) {
					Register regis = new Register();
					regis.addVoter(username,pass,type);
					JOptionPane.showConfirmDialog((Component) null,
							"Complete regist", "regist",
							JOptionPane.DEFAULT_OPTION);
					// back to loginUI and close this UI
					
					serviceUI.runByName("LoginUI", "");
					close();
				} else {
					JOptionPane.showConfirmDialog((Component) null,
							" Password and Confirmationpassword Not match",
							"Not match", JOptionPane.DEFAULT_OPTION);
				}
			}
		}
	}

	private class CancelAction extends AbstractAction {
		public CancelAction() {
			super();
		}

		public void actionPerformed(ActionEvent e) {
			serviceUI.runByName("LoginUI", "");
			close();
		}
	}

	/*
	 * close
	 */
	public void close() {
		this.setVisible(false);
	}

	/*
	 * add service for run the other ui
	 */
	public void addService(SeviceUI serviceUI) {
		this.serviceUI = serviceUI;
	}

	public void run(String info) {
		this.initComponent();
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		type = e.getActionCommand();

	}

//	public static void main(String[] args) {
//		RegisterUI ui = new RegisterUI();
//		ui.run("");
//	}
}