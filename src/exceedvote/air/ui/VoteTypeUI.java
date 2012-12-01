package exceedvote.air.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import com.sun.tools.xjc.reader.xmlschema.bindinfo.BIConversion.User;

import exceedvote.air.model.Clock;
import exceedvote.air.model.Committee;
import exceedvote.air.model.Poll;
import exceedvote.air.model.VoteTopic;
import exceedvote.air.model.Voter;
/**
 * VoteType user interface
 * 
 * @author AIr Team
 * @version 2012.11.3
 */
public class VoteTypeUI extends JFrame implements RunUI,Observer
{

//	private  VoteTypeUI voteTypeUI;
    private JPanel contentPane;
    private JTextPane txtpnVotetype = new JTextPane();
    private JButton btnHistory = new JButton("History");
    private JButton btnPoll = new JButton("Poll");
    private JLabel lblSelectTheType = new JLabel("Click to select the type");

    // button submit 
    private JButton btnGoToVote;
    private JLabel select = new JLabel("Select :");

    // label shows which user select type
    private JLabel selectType;
    private String labelSelect = "";

    // voteUi run after user select and click submit button
    //ballotleft
    private JLabel ballotLeft;
    
    private Map<String, JButton> dynamicButtons = new HashMap<String, JButton>();
    
    //service for call other ui
    SeviceUI serviceUI;
    private Clock clock;
    private Voter voter;
    private Font font;
    private int ballot = 0;
    private Object [] names;
    private Committee committee = new Committee(); 
    private JTextField fieldWatch = new JTextField();
    private int lastPos = 0;
    Object user;
    /**
     * Create the frame.
     * @param t 
     */
    
    public VoteTypeUI(){
    	user = committee;
    	names = committee.getTopic().toArray();
    }
    public VoteTypeUI(Voter voter, Clock t) 
    {
    	this.voter = voter;
        ballot = voter.getballotLeft();
        names = committee.getTopic().toArray();
        user = voter;
        this.clock = t;
      
    }
    
    public VoteTypeUI(Committee committee,Clock t)
    {
       
        user = committee;
        this.committee = committee;
        ballot = committee.getballotLeft();
        names = committee.getTopic().toArray();
        this.clock = t;
        
    }
    

	public void setCom(Committee committee){
		this.committee = committee;
	}
	
	/*
     * set all component
     */
    public void initComponent()
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        font = new Font("Monaco",Font.BOLD,20);

        setBounds(100, 100, 450, 478);
       
        contentPane = new JPanel();

        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        txtpnVotetype.setEditable(false);
        txtpnVotetype.setFont(new Font("Tahoma", Font.PLAIN, 36));
        txtpnVotetype.setText("VoteType");
        txtpnVotetype.setBounds(10, 11, 176, 50);
        contentPane.add(txtpnVotetype);

        lblSelectTheType.setBounds(20, 72, 185, 14);
        contentPane.add(lblSelectTheType);

        setButton();
        
        btnGoToVote = new JButton(new ActionSubmit());
        btnGoToVote.setText("Go to vote page");
        btnGoToVote.setBounds(232, lastPos+50, 169, 23);
        contentPane.add(btnGoToVote);

        select.setBounds(30, lastPos+50, 46, 14);
        contentPane.add(select);

        selectType = new JLabel();
        selectType.setText("none");
        selectType.setBounds(86, lastPos+50, 199, 14);
        contentPane.add(selectType);
        if(user.equals(voter)) ballot = voter.getballotLeft();
        else  if(user.equals(committee)) ballot = committee.getballotLeft();
        String shows = "You Have : "+String.valueOf(ballot)+" Ballot";
        ballotLeft = new JLabel(shows);
		ballotLeft.setBounds(267, 11, 142, 14);
		contentPane.add(ballotLeft);
	
		

			fieldWatch = new JTextField(80);
			fieldWatch.setText("00:00:00");
	    	fieldWatch.setBounds(280, 30, 218, 23);
	    	fieldWatch.setFont(font);
	        fieldWatch.setBackground(Color.WHITE);
	        fieldWatch.setBorder(null);
	        fieldWatch.setForeground(Color.orange);
			contentPane.add(fieldWatch);
			
			 
			 btnHistory = new JButton(new historyAction());
			 btnHistory.setText("History");
			 btnHistory.setBounds(150, lastPos+50, 89, 23);
		        contentPane.add(btnHistory);
		        
		        btnPoll = new JButton(new pollAction());
		        btnPoll.setText("Poll");
		        btnPoll.setBounds(180, 60, 218, 23);
		        if(clock.isRun()==false)   btnPoll.setEnabled(true);
		        else  btnPoll.setEnabled(false);
			     contentPane.add(btnPoll);
		        lastPos = lastPos+50;
			  setSize(430, lastPos+100);
		
				
    }

    public void removeButton(String name){
   	 JButton button = dynamicButtons.remove(name);
   	 contentPane.remove(button);
   	 contentPane.invalidate();
   	 contentPane.repaint();
   	
   }

   private void setButton() {
   	int pos = 55;
   	for(int i=0;i<names.length;i++){
   		pos += 30;
	    	JButton topicBtn = new JButton();
	    	topicBtn.setText(((VoteTopic)names[i]).getTitle());
	    	topicBtn.addActionListener(new ActionSelect());
	    	dynamicButtons.put( ((VoteTopic)names[i]).getTitle(), topicBtn);
	    	topicBtn.setBounds(20, pos, 379, 30);
	    	contentPane.add(topicBtn);
	    	if(i == (names.length-1)) {
	    		lastPos = pos;
	    	
	    	}
   	}
   	    
	    	contentPane.invalidate();
	    	contentPane.repaint();
	    	
		  
	}
   
   private class pollAction extends AbstractAction{ 

       public pollAction()
       { 
           super(); 
       } 

       public void actionPerformed(ActionEvent e){
    	   Poll poll = new Poll();
    	  
    	   
    	  TotalResult result = new TotalResult();
    	  result.addData( poll.totalInfo());
    	  result.run();
    	   
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
	     	 if(voter != null) {
	     		 historyUI.addData(voter.history());
	     		 voter.history();
	     	}
	     	 else if(committee != null){
	     		 historyUI.addData(committee.history());
	     		committee.history();
	     	 }
        }
    }
    
    
    /*
     * action event when user select
     * After user click any type, the type will show in buttom of interface
     */
    public class ActionSelect extends AbstractAction{ 

        public ActionSelect()
        { 
            super(); 
        } 

        public void actionPerformed(ActionEvent e)
        {   
            JButton o = (JButton)e.getSource();
            labelSelect = o.getText();
            System.out.println(labelSelect);
            selectType.setText(labelSelect);
        }
    }

    /*
     * Action event when user click submit button
     * if type was select, run voterUi.
     * Show messagebox when user click submit but don't select any type. 
     */
    private class ActionSubmit extends AbstractAction{ 

        public ActionSubmit()
        { 
            super(); 
        } 

        public void actionPerformed(ActionEvent e)
        {       
            if(labelSelect.equals(""))
            {
                JOptionPane.showConfirmDialog((Component)
                    null, "Please Click to select the type", "Select the type", JOptionPane.DEFAULT_OPTION);
            }
            else
            {
            	
            		
                    if(voter != null)serviceUI.runByName("voteUI",labelSelect);
                    else if(committee != null) serviceUI.runByName("voteUICom",labelSelect);
                    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    close();
            }
        }
    }
    
    public void close()
    {
       this.setVisible(false);
    }
    
    public void addService(SeviceUI serviceUI)
    {
        this.serviceUI = serviceUI;
    }
    
    /*
     * run this frame 
     */
    public void run(String info)
    {
    	
        this.initComponent();
        this.setVisible(true);
        this.setResizable(true);
//        this.setMinimumSize(this.getSize());
    
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }

	@Override
	public void update(Observable o, Object arg) {
		
		 if(clock.isRun()==false)  {  btnPoll.setEnabled(true);  fieldWatch.setText("00:00:00");}
		 else fieldWatch.setText(clock.time());
   
    
	}

}

