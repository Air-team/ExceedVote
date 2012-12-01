package exceedvote.air.ui;

import java.awt.Font;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import exceedvote.air.model.Admin;
import exceedvote.air.model.Committee;
import exceedvote.air.model.Team;
import exceedvote.air.model.VoteTopic;
import exceedvote.air.model.Voter;

public class AdminUI extends JFrame implements RunUI, ListSelectionListener {
	
	private JPanel contentPane;
	private JList listVoter;
	private JList listCommitee;
	private JButton buttonChCom;
	private JButton buttonChVoter;
	private JLabel labelVoter;
	private JLabel labelCom;
	private Font font;
	private List<Voter> user;
	private SeviceUI serviceUI;
	private List<Committee> com;
	private DefaultListModel voteModel = new DefaultListModel();
	private DefaultListModel comModel = new DefaultListModel();
	private Admin admin = new Admin();

	public AdminUI() {
		user = admin.getVoter();
		com = admin.getCommittee();
		inicomponent();

	}

	private void inicomponent() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 570, 230);
		font = new Font("Monaco", Font.BOLD, 20);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBounds(0, 0, 600, 328);
		contentPane.setLayout(null);
		this.add(contentPane);

		labelVoter = new JLabel();
		labelVoter.setText("Commitee:");
		labelVoter.setFont(font);
		labelVoter.setBounds(50, 30, 100, 20);
		contentPane.add(labelVoter);

		labelCom = new JLabel();
		labelCom.setText("Voter:");
		labelCom.setFont(font);
		labelCom.setBounds(330, 30, 200, 20);
		contentPane.add(labelCom);

		buttonChCom = new JButton();
		buttonChCom.addActionListener(new changeToComAction());
		buttonChCom.setText("<---");
		buttonChCom.setBounds(250, 90, 70, 30);
		contentPane.add(buttonChCom);

		buttonChVoter = new JButton();
		buttonChVoter.addActionListener(new changeToVotAction());
		buttonChVoter.setText("--->");
		buttonChVoter.setBounds(250, 120, 70, 30);
		contentPane.add(buttonChVoter);

			
		listVoter = new JList();
		listVoter.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listVoter.setSelectedIndex(0);
		listVoter.addListSelectionListener(this);

		for (int i = 0; i < user.size(); i++)
			if (!voteModel.contains(user.get(i).getName()))
				voteModel.addElement(user.get(i).getName());
		listVoter.setModel(voteModel);
//		int indexVoter= listVoter.getSelectedIndex();
		

		listCommitee = new JList();
		listCommitee.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listCommitee.setSelectedIndex(0);

		for (int i = 0; i < com.size(); i++)
			if (!comModel.contains(com.get(i).getName()))
				comModel.addElement(com.get(i).getName());
		listCommitee.setModel(comModel);
		
//		int indexCom = listCommitee.getSelectedIndex();
		JScrollPane scrollPaneVoter = new JScrollPane(listVoter);
		scrollPaneVoter.setBounds(330, 50, 190, 126);
		contentPane.add(scrollPaneVoter);

		JScrollPane scrollPaneComittee = new JScrollPane(listCommitee);
		scrollPaneComittee.setBounds(50, 50, 190, 126);
		contentPane.add(scrollPaneComittee);
		

//		if(listVoter.getSelectedIndex()==0)  buttonChCom.setEnabled(false);
//		else  buttonChCom.setEnabled(true);
//		if(listCommitee.getSelectedIndex()==0)  buttonChVoter.setEnabled(false);
//		else buttonChVoter.setEnabled(true);

	}
	//change to committee
	private class changeToComAction implements ActionListener {

		public changeToComAction() {
			super();
		}

		public void actionPerformed(ActionEvent e) {
			int index = listVoter.getSelectedIndex();
			
			if(index >= 0){ 
				String name = user.get(index).getName();
				
			int size = voteModel.getSize();
			if (size ==  0) {
				buttonChCom.setEnabled(false);
			}
				voteModel.remove(index);
				user.remove(index);
				Voter voter = admin.getSingleVoter(name);
				System.out.println(voter);
				String username = admin.getSingleVoter(name).getName();
				String password = admin.getSingleVoter(name).getPassword();
				int amountBallot = admin.getSingleVoter(name).getballotLeft();
				String type = admin.getSingleVoter(name).getType();
				
				Committee committee = new Committee();
				
				// removed item in last position
				if (voter != null) {
					
					committee.setName(username);
					committee.setPassword(password);
					admin.removeVoter(name);
					admin.saveCommitee(name, password, amountBallot, type);
					
					int i = comModel.getSize();
					comModel.add(i, name);
					com.add(i, committee);
				
				}
			}
		}
	}

	//change to voter
	private class changeToVotAction implements ActionListener {

		public changeToVotAction() {
			super();
		}

		public void actionPerformed(ActionEvent e) {
			
			
			int index = listCommitee.getSelectedIndex();
			
			if(index >= 0) { 
				
				String name = com.get(index).getName();
				Committee committee = admin.getSingleCommittee(name);
				int size = comModel.getSize();
				if (size== 0) {
					buttonChVoter.setEnabled(false);
				} 
				
				
				comModel.remove(index);
				com.remove(index);
				
				if (committee != null) {
					
					String username = admin.getSingleCommittee(name).getName();
					String password = admin.getSingleCommittee(name).getPassword();
					String type = admin.getSingleCommittee(name).getType();
					int amountBallot = admin.getSingleCommittee(name).getballotLeft();
					Voter voter = new Voter(username, password, type);
					
					if ( admin.removeCommittee(name) ){
						voter.saveInfo(voter);
						voter.setballotLeft(amountBallot);
					
					
						int i = voteModel.getSize();
						voteModel.add(i, name);
						user.add(i, voter);
						
					}
				}
			}
			
		}
		

	}
	
	
 	public void close() {
   		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   	}
   	
   
    
	public void run(String info) {
		this.setVisible(true);
		this.setResizable(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

//	public static void main(String[] args) {
//
//		Committee com = new Committee();
//		com.setName("PRisa");
//		com.saveInfo(com);
//		AdminUI admin = new AdminUI();
//		SeviceUI serviceUI = new SeviceUI();
//		serviceUI.addUI("adminUI", admin);
//		admin.addService(serviceUI);
//		admin.run("");
//
//	}

	public void addService(SeviceUI serviceUI) {
		this.serviceUI = serviceUI;
	}

	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		// TODO Auto-generated method stub

	}

}
