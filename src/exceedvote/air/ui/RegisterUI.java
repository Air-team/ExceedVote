package exceedvote.air.ui;

/** Register user inferface indicate the detail that you want to input for first time that use this program*/
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

import exceedvote.air.controller.ControllerRegister;
import exceedvote.air.model.Register;

public class RegisterUI extends JFrame implements ActionListener, RunUI {

	private JPanel contentPane;
	/**
	 * @wbp.nonvisual location=37,-31
	 */
        // password textField for type
	private JPasswordField passwordInput;
	// passwordField for comfirm above password
	private JPasswordField passwordConfirm;
	// username textField for type
	private JTextField usenameInput;
        //submit button
	private JButton btnSumit;
	// cancel button
	private JButton btnCancel;
	// radio button for select teacher type
	private JRadioButton typeTeacher;
	//radio button for select student type
	private JRadioButton typeStudent;
        // register type
	private String type;

	// serviceUI for back to loginUI
	private SeviceUI serviceUI;
         
        /*
         * Constructor for objects of class dqdwd
         */
	public RegisterUI() {
	}

	/**
	 * initialize all components
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
		btnSumit.setText(Messages.getString("RegisterUI.butt.submit")); //$NON-NLS-1$
		btnSumit.setBounds(120, 243, 89, 23);
		contentPane.add(btnSumit);

		btnCancel = new JButton(new CancelAction());
		btnCancel.setBounds(225, 243, 89, 23);
		btnCancel.setText(Messages.getString("RegisterUI.butt.cancel")); //$NON-NLS-1$
		contentPane.add(btnCancel);

		JTextPane txtpnRegister = new JTextPane();
		txtpnRegister.setFont(new Font("Tahoma", Font.PLAIN, 22)); //$NON-NLS-1$
		txtpnRegister.setText(Messages.getString("RegisterUI.text.register")); //$NON-NLS-1$
		txtpnRegister.setBounds(10, 11, 188, 38);
		contentPane.add(txtpnRegister);

		JLabel lblUsername = new JLabel(
				Messages.getString("RegisterUI.label.username")); //$NON-NLS-1$
		lblUsername.setBounds(21, 76, 65, 14);
		contentPane.add(lblUsername);

		JLabel lblPassword = new JLabel(
				Messages.getString("RegisterUI.label.password")); //$NON-NLS-1$
		lblPassword.setBounds(21, 112, 65, 14);
		contentPane.add(lblPassword);

		JLabel lblComfermPassword = new JLabel(
				Messages.getString("RegisterUI.label.comfirmpass")); //$NON-NLS-1$
		lblComfermPassword.setBounds(21, 143, 110, 14);
		contentPane.add(lblComfermPassword);

		passwordInput = new JPasswordField(""); //$NON-NLS-1$
		passwordInput.setBounds(150, 109, 220, 20);
		passwordInput.addActionListener(this);
		contentPane.add(passwordInput);

		passwordConfirm = new JPasswordField(""); //$NON-NLS-1$
		passwordConfirm.setBounds(150, 140, 220, 20);
		passwordConfirm.addActionListener(this);
		contentPane.add(passwordConfirm);

		usenameInput = new JTextField(""); //$NON-NLS-1$
		usenameInput.setBounds(150, 73, 220, 20);
		contentPane.add(usenameInput);
		usenameInput.addActionListener(this);
		usenameInput.setColumns(10);

		JLabel lblType = new JLabel(Messages.getString("RegisterUI.label.type")); //$NON-NLS-1$
		lblType.setBounds(21, 202, 46, 14);
		contentPane.add(lblType);

		typeTeacher = new JRadioButton(
				Messages.getString("RegisterUI.radio.teacher")); //$NON-NLS-1$
		typeTeacher.setBackground(Color.WHITE);
		typeTeacher.setBounds(120, 198, 109, 23);
		typeTeacher.setMnemonic(KeyEvent.VK_0);
		typeTeacher.setActionCommand("Teacher"); //$NON-NLS-1$
		typeTeacher.addActionListener(this);
		contentPane.add(typeTeacher);

		typeStudent = new JRadioButton(
				Messages.getString("RegisterUI.radio.student")); //$NON-NLS-1$
		typeStudent.setBackground(Color.WHITE);
		typeStudent.setBounds(231, 198, 109, 23);
		typeStudent.setMnemonic(KeyEvent.VK_1);
		typeStudent.setActionCommand("Student"); //$NON-NLS-1$
		typeStudent.addActionListener(this);
		contentPane.add(typeStudent);

		ButtonGroup group = new ButtonGroup();
		group.add(typeTeacher);
		group.add(typeStudent);

	}
       /**
        *  actionListener for submit button
        */
	private class submitAction extends AbstractAction {
		public submitAction() {
			super();
		}

		public void actionPerformed(ActionEvent e) {
			String username = usenameInput.getText();
			String pass = passwordInput.getText();
			String passConfrim = passwordConfirm.getText();

			if (username.equals("") || pass.equals("") //$NON-NLS-1$ //$NON-NLS-2$
					|| passConfrim.equals("")) { //$NON-NLS-1$

				JOptionPane
						.showConfirmDialog(
								(Component) null,
								Messages.getString("RegisterUI.pop.incomplete.info"), Messages.getString("RegisterUI.pop.fillinfo"), //$NON-NLS-1$ //$NON-NLS-2$
								JOptionPane.DEFAULT_OPTION);

			} else if (username != "" && pass != "" && passConfrim != "") { //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				if (pass.equals(passConfrim)) {

					if (type == null) {
						JOptionPane
								.showConfirmDialog(
										(Component) null,
										Messages.getString("RegisterUI.pop.selecttype"), Messages.getString("RegisterUI.pop.regist"), //$NON-NLS-1$ //$NON-NLS-2$
										JOptionPane.DEFAULT_OPTION);
					} else {
						ControllerRegister controllerRegister = ControllerRegister
								.getInstance();
						boolean make = controllerRegister.addUser(username,
								pass, type);

						if (make) {
							JOptionPane
									.showConfirmDialog(
											(Component) null,
											Messages.getString("RegisterUI.pop.complete.regis"), Messages.getString("RegisterUI.pop.regis"), //$NON-NLS-1$ //$NON-NLS-2$
											JOptionPane.DEFAULT_OPTION);
							// back to loginUI and close this UI

							serviceUI.runByName("LoginUI", ""); //$NON-NLS-1$ //$NON-NLS-2$
							close();
						} else {
							JOptionPane
									.showConfirmDialog(
											(Component) null,
											Messages.getString("RegisterUI.pop.warning.nameused"), Messages.getString("RegisterUI.pop.warning.regis"), //$NON-NLS-1$ //$NON-NLS-2$
											JOptionPane.DEFAULT_OPTION);
							// back to loginUI and close this UI
						}

					}
				} else {
					JOptionPane
							.showConfirmDialog(
									(Component) null,
									Messages.getString("RegisterUI.pop.warning.notmatch.namepass"), //$NON-NLS-1$
									Messages.getString("RegisterUI.pop.notmatch"), JOptionPane.DEFAULT_OPTION); //$NON-NLS-1$
				}
			}
		}
	}
        /**
        *  actionListener for cancel button
        */
	private class CancelAction extends AbstractAction {
		public CancelAction() {
			super();
		}

		public void actionPerformed(ActionEvent e) {
			serviceUI.runByName("LoginUI", ""); //$NON-NLS-1$ //$NON-NLS-2$
			close();
		}
	}

	/**
	 * close this flame
	 */
	public void close() {
		this.setVisible(false);
	}

	/**
	 * add service for run the other ui
	 */
	public void addService(SeviceUI serviceUI) {
		this.serviceUI = serviceUI;
	}

	/**
	 * run this frame
	 */
	public void run(String info) {
		this.initComponent();
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	/**
	 * Actionc select tyoe between teacher and student
	 */
	public void actionPerformed(ActionEvent e) {
		type = e.getActionCommand();

	}

}