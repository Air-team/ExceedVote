package exceedvote.air.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextPane;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JRadioButton;

public class RegisterUI extends JFrame {

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

		btnSumit = new JButton("submit");
		btnSumit.setBounds(120, 243, 89, 23);
		contentPane.add(btnSumit);

		btnCancel = new JButton("cancel");
		btnCancel.setBounds(225, 243, 89, 23);
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

		passwordInput = new JPasswordField();
		passwordInput.setBounds(120, 109, 220, 20);
		contentPane.add(passwordInput);

		passwordConfirm = new JPasswordField();
		passwordConfirm.setBounds(120, 140, 220, 20);
		contentPane.add(passwordConfirm);

		usenameInput = new JTextField();
		usenameInput.setBounds(120, 73, 220, 20);
		contentPane.add(usenameInput);
		usenameInput.setColumns(10);

		JLabel lblType = new JLabel("Type");
		lblType.setBounds(21, 202, 46, 14);
		contentPane.add(lblType);

		JRadioButton typeTeacher = new JRadioButton("Teacher");
		typeTeacher.setBackground(Color.WHITE);
		typeTeacher.setBounds(120, 198, 109, 23);
		contentPane.add(typeTeacher);

		JRadioButton typeStudent = new JRadioButton("Student");
		typeStudent.setBackground(Color.WHITE);
		typeStudent.setBounds(231, 198, 109, 23);
		contentPane.add(typeStudent);
	}

	public void run() {
		this.initComponent();
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}