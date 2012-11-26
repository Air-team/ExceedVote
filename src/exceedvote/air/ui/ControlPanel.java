package exceedvote.air.ui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;


import javax.swing.AbstractAction;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


import exceedvote.air.model.Committee;
import exceedvote.air.model.Team;
import exceedvote.air.model.VoteTopic;

public class ControlPanel extends JFrame implements ListSelectionListener {

	private JPanel contentPane;
	private JPanel vote = new JPanel();
	private JPanel controlPan = new JPanel();
	private JTextField addTeamInput;
	private JTextField criteraiInput;
	private JLabel labelTeam;
	private JLabel labelCeteria;
	//button
	// add team
	private JButton btnAddteam; 
	// add criteria
	private JButton btnCriteria;
	// deleteTeam
	private JButton btnDeleteTeam;
	//deleteTopic
	private JButton btnDeleteTopic;
	//set time
	private JButton btnSettime;
	
	private Committee commitee;
	//listTeam 
//	private String[] teamList = {"Team1","Team2","Team3","Team4","Team5","Team6","Team7","Team8","Team9","Team10","Team11"};
	
	private DefaultListModel teamModel = new DefaultListModel();
	private DefaultListModel topicModel = new DefaultListModel();
	
	//list criteria
//	private String[] criterai = {"Team1","Team2","Team3","Team4","Team5","Team6","Team7","Team8","Team9","Team10","Team11"};
	private List<Team> team = new ArrayList<Team>();
	private List<VoteTopic> topic = new ArrayList<VoteTopic>();
	
	// list for show team
	private JList listTeam;
	// list for show criteria
    private JList listCriteria;
	/**
	 * Create the frame.
	 */
	public ControlPanel(Committee committee) {
		this.commitee = committee;
	}
	
	public void initComponent()
	{
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 493, 471);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		JTabbedPane tab = new JTabbedPane(JTabbedPane.TOP);
		tab.setBounds(0, 0, 612, 441);
		contentPane.add(tab);
		
	    tab.addTab("Control", controlPan);
	}
	
	public void setControlPage()
	{
	    // create Controlpane
		controlPan.setBounds(0, 0, 475, 328);
		controlPan.setLayout(null);
		
		addTeamInput = new JTextField();
		addTeamInput.setBounds(10, 23, 140, 20);
		controlPan.add(addTeamInput);
		addTeamInput.setColumns(10);
		
		criteraiInput = new JTextField();
		criteraiInput.setColumns(10);
		criteraiInput.setBounds(10, 60, 140, 20);
		controlPan.add(criteraiInput);
		
		btnAddteam = new JButton(new addTeamAction());
		btnAddteam.setText("AddTeam");
		btnAddteam.setBounds(163, 22, 89, 23);
		controlPan.add(btnAddteam);
		
		btnCriteria = new JButton(new addCreteriaAction());
		btnCriteria.setText("Criteria");
		btnCriteria.setBounds(160, 59, 89, 23);
		controlPan.add(btnCriteria);
		
		
		btnDeleteTeam = new JButton(new deleteTeamAction());
		btnDeleteTeam.setText("DeleteTeam");
		btnDeleteTeam.setBounds(250, 151, 89, 23);
		controlPan.add(btnDeleteTeam);
		
		
		btnDeleteTopic = new JButton(new deleteTopicAction());
		
		btnDeleteTopic.setText("DeleteTopic");
		btnDeleteTopic.setBounds(250, 340, 89, 23);
		controlPan.add(btnDeleteTopic);
		
		btnSettime = new JButton(new setTimeAction());
		btnSettime.setText("SetTime");
		btnSettime.setBounds(10, 91, 242, 23);
		controlPan.add(btnSettime);
		
//		String [] teamList = {""};
	
		labelTeam = new JLabel("Team:");
		labelTeam.setBounds(259,-50, 190, 126);
		controlPan.add(labelTeam);
		
		listTeam = new JList();
		listTeam.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listTeam.setSelectedIndex(0);
		listTeam.addListSelectionListener(this);
		team = commitee.getTeam();
		for(int i=0;i<team.size();i++) 
			if(!teamModel.contains(team.get(i).getName())) teamModel.addElement(team.get(i).getName());
		listTeam.setModel(teamModel);
		
		
		labelCeteria = new JLabel("Topic:");
		labelCeteria.setBounds(259,140, 190, 126);
		controlPan.add(labelCeteria);
		
		listCriteria = new JList();
		listCriteria.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listCriteria.setSelectedIndex(0);
		listCriteria.addListSelectionListener(this);
		topic = commitee.getTopic();
		for(int i=0;i<topic.size();i++) 
			if(!topicModel.contains(topic.get(i).getTitle())) topicModel.addElement(topic.get(i).getTitle());
		listCriteria.setModel(topicModel);
		
		JScrollPane scrollPaneCriteria = new JScrollPane(listCriteria);
		scrollPaneCriteria.setBounds(259, 210, 190, 126);
		controlPan.add(scrollPaneCriteria);
		
		JScrollPane scrollPaneTeam = new JScrollPane(listTeam);
		scrollPaneTeam.setBounds(259, 20, 190, 126);
		controlPan.add(scrollPaneTeam);
		
		// finish
	}
	
	// action for addTeambtn
	private class addTeamAction extends AbstractAction{ 

        public addTeamAction()
        { 
            super(); 
        } 

        public void actionPerformed(ActionEvent e){

        	if(!teamModel.contains(addTeamInput.getText())) {
        		
        		if ( commitee.setTeam(addTeamInput.getText())){
        			teamModel.addElement(addTeamInput.getText());
        			JOptionPane.showConfirmDialog((Component)
        	                 null, "Add successful", "Select the team", JOptionPane.DEFAULT_OPTION);
        		}
        	}
        	else {
        		 JOptionPane.showConfirmDialog((Component)
     	                 null, "This name is already used.", "Select the team", JOptionPane.DEFAULT_OPTION);
        	}
        	listTeam.setModel(teamModel);
        	addTeamInput.setText("");
        	
        }
    }
    
    // action for addCreteribtn
    private class addCreteriaAction extends AbstractAction{ 

        public addCreteriaAction()
        { 
            super(); 
        } 

        public void actionPerformed(ActionEvent e)
        {   
           	if(!topicModel.contains(criteraiInput.getText())) {
           		
        		if ( commitee.setTopic(criteraiInput.getText())){
        			topicModel.addElement(criteraiInput.getText());
        			JOptionPane.showConfirmDialog((Component)
        	                 null, "Add successful", "add the topic", JOptionPane.DEFAULT_OPTION);
        		}
        	}
        	else {
        		 JOptionPane.showConfirmDialog((Component)
     	                 null, "This name is already used.", "add the topic", JOptionPane.DEFAULT_OPTION);
        	}
        	listCriteria.setModel(topicModel);
        	criteraiInput.setText("");
        	
        }
        
    }
    // action for setTimebtn
    private class setTimeAction extends AbstractAction{ 

        public setTimeAction()
        { 
            super(); 
        } 

        public void actionPerformed(ActionEvent e)
        {   
            
        }
    }
    
    
    
    //action for deleteTopicbtn
      private class deleteTopicAction extends AbstractAction{ 

          public deleteTopicAction()
          { 
              super(); 
          } 

          public void actionPerformed(ActionEvent e)
          {   
          	   int index = listCriteria.getSelectedIndex();
          	    VoteTopic top = topic.get(index);
          	 
          	   
          	    if(	commitee.deleteTopic(top.getTitle())){
          	    	 topicModel.remove(index);
          	    	    int size = topicModel.getSize();

                  	    if (size == 0) { 
                  	    	btnDeleteTeam.setEnabled(false);
                  	    } 
                  	    else { //Select an index.
                  	        if (index == topicModel.getSize()) {
                  	            //removed item in last position
                  	            index--;
                  	        }

                  	        listCriteria.setSelectedIndex(index);
                  	      listCriteria.ensureIndexIsVisible(index);
                  	    }
                  	    JOptionPane.showConfirmDialog((Component)
             	                 null, "Delete successful", "delete", JOptionPane.DEFAULT_OPTION);
          	    }
          	    else {
             		 JOptionPane.showConfirmDialog((Component)
          	                 null, "Sorry,can't delete", "delete", JOptionPane.DEFAULT_OPTION);
          	    }
          	    
           }
       }
      
    //action for deleteTeambtn
    private class deleteTeamAction extends AbstractAction{ 

        public deleteTeamAction()
        { 
            super(); 
        } 

        public void actionPerformed(ActionEvent e)
        {   
        	   int index = listTeam.getSelectedIndex();
        	    Team t = team.get(index);
        	 
        	   
        	    if(	commitee.deleteTeam(t.getName())){
        	    	 teamModel.remove(index);
        	    	    int size = teamModel.getSize();

                	    if (size == 0) { 
                	    	btnDeleteTeam.setEnabled(false);
                	    } 
                	    else { //Select an index.
                	        if (index == teamModel.getSize()) {
                	            //removed item in last position
                	            index--;
                	        }

                	        listTeam.setSelectedIndex(index);
                	        listTeam.ensureIndexIsVisible(index);
                	    }
                	    JOptionPane.showConfirmDialog((Component)
           	                 null, "Delete successful", "delete", JOptionPane.DEFAULT_OPTION);
        	    }
        	    else {
           		 JOptionPane.showConfirmDialog((Component)
        	                 null, "Sorry,can't delete", "delete", JOptionPane.DEFAULT_OPTION);
        	    }
        	    
        	
        }
  
    }
	
	public void run(String info)
	{    
	    this.setControlPage();
	    this.initComponent();
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args)
	{
		
		Committee com = new Committee();
	    ControlPanel controlPanel = new ControlPanel(com);
	    controlPanel.run("");
	     
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		
	}

	
}

