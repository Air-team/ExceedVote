package exceedvote.air.ui;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.JTextPane;

import exceedvote.air.model.Ballot;
import exceedvote.air.model.Clock;
import exceedvote.air.model.Committee;

import exceedvote.air.model.Team;
import exceedvote.air.model.Voter;
import java.awt.Color;
import java.awt.Font;
import java.text.DateFormat;
import java.util.HashMap;
import java.util.Map;
/**
 * eXceed Vote GUI 
 * 
 * @author AIr Team
 * @version 2012.10.1
 */
public class VoteUI extends JFrame implements RunUI,Observer
{
    private JPanel contentPane;

    private final JLabel label = new JLabel("New label");
    /** 
	 * Set the all of components and add action o each components
	 */
    private final JTextField textField_1 = new JTextField();

    private JPanel listTeam = new JPanel();
    private JTextPane status = new JTextPane();
    private JTextPane txtpnTeamlist = new JTextPane();
    private JButton btnViewInfomation = new JButton("view Infomation");
    private JButton voteBtn;
    private JTextPane teamSelect = new JTextPane();
    private JButton revoteBtn = new JButton("-");
    private JLabel lblClickToSelect = new JLabel("Click to select the team");
    private JButton btnHistory = new JButton("History");
    private JLabel lblNewLabel = new JLabel("View your history");
    private JLabel lblVote = new JLabel("vote");
    private JLabel lblRevote = new JLabel("revote");
    private JTextField fieldWatch = new JTextField();
    private DateFormat dateFormat;
    private JButton btnBack;
    private Font font;
 
    
    /*
     * ballot test
     */
    int ballot;
    String type = "";
    String nameVoter = "";
    /*
     * name team test
     */
    
    private String typeTeam = ""; 
    
    Object[] names;
    // name select
    String selectTeam ="";
    // Voter
   
    private Clock clock;
    private Voter voter;
    private Committee committee = new Committee();
    private Ballot ballott;
    private Map<String, JButton> dynamicButtons = new HashMap<String, JButton>();
    Object user;
    
    public VoteUI(Voter voter, Clock t)
    {
        this.voter = voter;
        ballot = voter.getballotLeft();
        type = voter.getType();
        nameVoter = voter.getName();
        names =committee.getTeam().toArray();
        user = voter;
        clock = t;
        setBallot();
        initComponent();
    }
    
    public VoteUI(){
    	 names =committee.getTeam().toArray();
    	 user = committee;
    	  setBallot();
    }
    
    public VoteUI(Committee committee,Clock t)
    {
       
        names =committee.getTeam().toArray();
        nameVoter = committee.getName();
        ballot = committee.getballotLeft();
        type = committee.getType();
        user = committee;
        clock = t;
        setBallot();
        initComponent();
       
    }
    public void setCom(Committee committee){
		this.committee = committee;
	}
  
     //service for call other ui
    SeviceUI serviceUI;
    
    public void setBallot(){
    	ballott = Ballot.getInstance();
   
    }
    
    public void removeTeam(String name){
    JButton button = dynamicButtons.remove(name);
   	 contentPane.remove(button);
   	 contentPane.invalidate();
   	 contentPane.repaint();
    }
    /** 
	 * Set the all of components and add action o each components
	 */
    public void initComponent()
    {
    
		font = new Font("Monaco",Font.BOLD,20);

        textField_1.setColumns(10);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 414);
        contentPane = new JPanel();
        contentPane.setBackground(Color.BLACK);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        
        listTeam.setBounds(10, 64, 218, 301);
        contentPane.add(listTeam);
        listTeam.setLayout(new GridLayout(20, 1, 0, 0));

        status.setBounds(242, 11, 218, 30);
        contentPane.add(status);
        status.setBackground(Color.BLACK);
        status.setFont(new Font("Tahoma", Font.PLAIN, 15));
        status.setText("Status : "+type+" : "+String.valueOf(ballot)+" Ballot");
        status.setForeground(Color.ORANGE);
        status.setEditable(false);

        txtpnTeamlist.setFont(new Font("Tahoma", Font.PLAIN, 15));
        txtpnTeamlist.setBackground(Color.BLACK);
        txtpnTeamlist.setEditable(false);
        txtpnTeamlist.setForeground(Color.ORANGE);
        txtpnTeamlist.setText("TeamList");
        txtpnTeamlist.setBounds(10, 11, 218, 23);
        contentPane.add(txtpnTeamlist);
        
       
        
        btnViewInfomation = new JButton("view Infomation");
        btnViewInfomation.addActionListener(new btnInfoAction());
        btnViewInfomation.setBounds(250, 100, 130, 23);
        contentPane.add(btnViewInfomation);
        updateTeam();
       
        // add action to vote button
        voteBtn = new JButton(new UpAction());
        voteBtn.setText("+");
        voteBtn.setBounds(250, 152, 89, 23);
        contentPane.add(voteBtn);
        
        revoteBtn = new JButton(new ReVoteAction());
        revoteBtn.setText("-");
        revoteBtn.setBounds(349, 152, 89, 23);
        contentPane.add(revoteBtn);

        btnHistory = new JButton(new historyAction());
        btnHistory.setText("History");
		btnHistory.setBounds(250, 216, 89, 23);
		contentPane.add(btnHistory);
		
		
        teamSelect.setEditable(false);
        teamSelect.setBackground(Color.BLACK);
        teamSelect.setForeground(Color.ORANGE);
        teamSelect.setFont(new Font("Tahoma", Font.PLAIN, 15));
        teamSelect.setText("Team :");
        teamSelect.setBounds(242, 59, 206, 30);
        contentPane.add(teamSelect);

        revoteBtn.setBounds(349, 152, 89, 23);
        contentPane.add(revoteBtn);

        lblVote.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblVote.setForeground(Color.ORANGE);
        lblVote.setBounds(249, 138, 46, 14);
        contentPane.add(lblVote);

        lblRevote.setForeground(Color.ORANGE);
        lblRevote.setBounds(349, 139, 46, 14);
        contentPane.add(lblRevote);

        lblClickToSelect.setForeground(Color.ORANGE);
        lblClickToSelect.setBounds(10, 45, 210, 14);
        contentPane.add(lblClickToSelect);

        btnHistory.setBounds(250, 216, 89, 23);
        contentPane.add(btnHistory);

        lblNewLabel.setForeground(Color.ORANGE);
        lblNewLabel.setBounds(250, 200, 188, 14);
        contentPane.add(lblNewLabel);
        
        btnBack = new JButton(new backAction());
        btnBack.setText("Back");
		btnBack.setBounds(349, 342, 89, 23);
		contentPane.add(btnBack);
		
		fieldWatch = new JTextField(80);
    	fieldWatch.setBounds(250, 245, 218, 23);
    	fieldWatch.setText("00:00:00");
    	fieldWatch.setFont(font);
        fieldWatch.setBackground(Color.BLACK);
        fieldWatch.setForeground(Color.orange);
		contentPane.add(fieldWatch);
		
		  if(clock.isRun()==false) closeBtn();
    }
    
    @Override
	public void update(Observable o, Object arg) {
        
        if(clock.isRun()==false)  { closeBtn(); fieldWatch.setText("00:00:00");}
        else if(clock.isRun()==true) { openBtn(); fieldWatch.setText(clock.time()); }
    }
    
    private void closeBtn() {
    	voteBtn.setEnabled(false);
    	revoteBtn.setEnabled(false);
	}

    private void openBtn() {
    	voteBtn.setEnabled(true);
    	revoteBtn.setEnabled(true);
	}

    private class btnInfoAction extends AbstractAction{
    	 public btnInfoAction()
         { 
             super(); 
         } 

         public void actionPerformed(ActionEvent e)
         {  
               	 String teamName = selectTeam;
        	
        	
           InfomationUI informationUI = new InfomationUI(teamName);
           informationUI.run("");

         }
    }
  
	private class historyAction extends AbstractAction{ 

        public historyAction()
        { 
            super(); 
        } 

        public void actionPerformed(ActionEvent e)
        {   
          HistoryUI historyUI = new HistoryUI();
          historyUI.run();
     	  if(voter != null ){
     		  historyUI.addData(voter.history());
     		  voter.history();
     	  }
     	  else if(committee!= null){
     		  historyUI.addData(committee.history());
     		  committee.history();
     	  }
     	 
        }
    }


    private class UpAction extends AbstractAction{ 

        public UpAction()
        { 
            super(); 
        } 

        public void actionPerformed(ActionEvent e)
        {  
            if(selectTeam.equals(""))
            {
                JOptionPane.showConfirmDialog((Component)
                    null, "Please Click to select the team", "Select the team", JOptionPane.DEFAULT_OPTION);
            }
            else if(ballot == 0)
            {
                	JOptionPane.showConfirmDialog((Component)
                    null, "No ballot !!", "No Ballot!!", JOptionPane.DEFAULT_OPTION);
            }
            else
            {    
                String alert = "Do you want to vote " + selectTeam ;
                int result = JOptionPane.showConfirmDialog((Component)
                        null, alert , "Submit Vote!!!", JOptionPane.YES_NO_OPTION);
                if(result == 0)
                {
                	 
                	 boolean canVote = false;
                	 if(voter != null) {
                		 canVote = ballott.putBallot(selectTeam,typeTeam,voter);
                     
	                     if(canVote)
	                     {
	                     	ballot--;
	                         status.setText("Status : "+type+" : "+String.valueOf(voter.getballotLeft())+" Ballot");
	                     }
                	 }
                	 else  if(committee != null) {
                		 canVote = ballott.putBallot(selectTeam,typeTeam,committee);
                     
	                     if(canVote)
	                     {
	                     	ballot--;
	                         status.setText("Status : "+type+" : "+String.valueOf(committee.getballotLeft())+" Ballot");
	                     }
                	 }
                }
            }
//            System.out.print(result);
        }
    }

    private class ReVoteAction extends AbstractAction{ 
    	  public ReVoteAction() { 
              super(); 
          } 
    	

		public void actionPerformed(ActionEvent arg0) {
			
			 if(selectTeam.equals(""))
	         {
	             JOptionPane.showConfirmDialog((Component)
	                 null, "Please Click to select the team", "Select the team", JOptionPane.DEFAULT_OPTION);
	         }
	   
	         else
	         {    
	             String alert = "Do you want to disvote " + selectTeam ;
	             int result = JOptionPane.showConfirmDialog((Component)
	                     null, alert , "Submit Vote!!!", JOptionPane.YES_NO_OPTION);
	             if(result == 0)
	             {
	             	 boolean canVote  = false; ballott.returnBallot(selectTeam,typeTeam,voter);
	             	 if(voter != null) {
	             		canVote  = ballott.returnBallot(selectTeam,typeTeam,voter);
		                  if(canVote)
		                  {
		                  	ballot++;
		                      status.setText("Status : "+type+" : "+String.valueOf(voter.getballotLeft())+" Ballot");
		                  }
		                  else {
		                	  JOptionPane.showConfirmDialog((Component)
		         	                 null, "You didn't vote this team", "Select the team", JOptionPane.DEFAULT_OPTION);
		                  }
	             	 }
	             	 else  if(committee != null) {
		             		canVote  = ballott.returnBallot(selectTeam,typeTeam,committee);
			                  if(canVote)
			                  {
			                  	ballot++;
			                      status.setText("Status : "+type+" : "+String.valueOf(committee.getballotLeft())+" Ballot");
			                  }
			                  else {
			                	  JOptionPane.showConfirmDialog((Component)
			         	                 null, "You didn't vote this team", "Select the team", JOptionPane.DEFAULT_OPTION);
			                  }
		             	 }
	             }
	         }
		}	
    }

    private class ActionSelect extends AbstractAction{ 

        public ActionSelect()
        { 
            super(); 
        } 

        public void actionPerformed(ActionEvent e)
        {   
            JButton o = (JButton)e.getSource();
            selectTeam = o.getText();
            teamSelect.setText("Team : "+selectTeam);
        }
    }
    
    public void reTeam()
    {
    	listTeam.removeAll();
    }
    
    private class backAction extends AbstractAction{ 

        public backAction()
        { 
            super(); 
        } 

        public void actionPerformed(ActionEvent e)
        {   
     
           
            if(voter != null) {
        	   System.out.println(user);
        	   serviceUI.runByName("voteTypeUI");
           }
            else if(committee != null) {
        	   System.out.println("Aa");
        	   serviceUI.runByName("voteTypeUICom");
           }
            close();
        }
    }
    

    public void close()
    {
        setVisible(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    /*
     * set team in UI,
     */
    public void updateTeam()
    { 
//	    reTeam();
//	 
	    for(int i=0;i<names.length;i++){
	    	JButton Btn = new JButton();
	    	Btn.setText(((Team)names[i]).getName());
	    	Btn.addActionListener(new ActionSelect());
	    	dynamicButtons.put( ((Team)names[i]).getName(), Btn);
	    	listTeam.add(Btn);
	    	
    	}
	    
	    contentPane.invalidate();
    	contentPane.repaint();
    }
    

    public void setType(String type)
    {
       this.typeTeam = type;
       txtpnTeamlist.setText("TeamList: "+typeTeam);
    }
    
    public void addService(SeviceUI serviceUI)
    {
        this.serviceUI = serviceUI;
    }
    
    /*
     * run Frame
     */
    public void run(String type)
    {    
//         this.initComponent();
        this.setType(type);
        this.setVisible(true);
        this.setResizable(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
