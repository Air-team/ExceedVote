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

import exceedvote.air.model.Ballot;
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
public class Detail extends JFrame implements RunUI
{

//	private  VoteTypeUI voteTypeUI;
    private JPanel contentPane;
    private JTextPane txtpnVotetype = new JTextPane();
    private JLabel lblSelectTheType = new JLabel(Messages.getString("Detail.label.clicktype")); //$NON-NLS-1$

    // button submit 
    private JButton btnGoToVote;
    private JLabel select = new JLabel(Messages.getString("Detail.label.select")); //$NON-NLS-1$

    // label shows which user select type
    private JLabel selectType;
    private String labelSelect = ""; //$NON-NLS-1$

    // voteUi run after user select and click submit button
    //ballotleft
//    private JLabel ballotLeft;
    
    private Map<String, JButton> dynamicButtons = new HashMap<String, JButton>();
    
    //service for call other ui
    SeviceUI serviceUI;
    private Clock clock;
    private Font font;
    private int ballot = 0;
    private Object [] names;
    private Committee committee = new Committee(); 
    private Voter voter;
    private JTextField fieldWatch = new JTextField();
    private int lastPos = 0;
    Object user;
    
    
    public Detail(Voter voter) 
    {
    	this.voter = voter;
        ballot = voter.getballotLeft();
        names = committee.getTopic().toArray();
        user = voter;
       
      
    }
    
    public Detail(Committee committee)
    {
       
        user = committee;
        this.committee = committee;
        ballot = committee.getballotLeft();
        names = committee.getTopic().toArray();
     
        
    }
	/*
     * set all component
     */
    public void initComponent()
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        font = new Font("Monaco",Font.BOLD,20); //$NON-NLS-1$

        setBounds(100, 100, 450, 478);
       
        contentPane = new JPanel();

        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setButton();
        
        txtpnVotetype.setEditable(false);
        txtpnVotetype.setFont(new Font("Tahoma", Font.PLAIN, 36)); //$NON-NLS-1$
        txtpnVotetype.setText(Messages.getString("Detail.text.votetype")); //$NON-NLS-1$
        txtpnVotetype.setBounds(10, 11, 176, 50);
        contentPane.add(txtpnVotetype);

        lblSelectTheType.setBounds(20, 72, 185, 14);
        contentPane.add(lblSelectTheType);

        selectType = new JLabel();
        selectType.setText(Messages.getString("Detail.label.none")); //$NON-NLS-1$
        selectType.setBounds(86, lastPos+50, 199, 14);
        contentPane.add(selectType);
        
        btnGoToVote = new JButton(new ActionSubmit());
        btnGoToVote.setText(Messages.getString("Detail.butt.resultpage")); //$NON-NLS-1$
        btnGoToVote.setBounds(232, lastPos+50, 169, 23);
        contentPane.add(btnGoToVote);

        select.setBounds(30, lastPos+50, 46, 14);
        contentPane.add(select);	
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
            if(labelSelect.equals("")) //$NON-NLS-1$
            {
                JOptionPane.showConfirmDialog((Component)
                    null, Messages.getString("Detail.pop.clicktype"), Messages.getString("Detail.pop.selecttype"), JOptionPane.DEFAULT_OPTION); //$NON-NLS-1$ //$NON-NLS-2$
            }
            else
            {
            	
            	TopicScoreUI detail = new TopicScoreUI(labelSelect);
            		Ballot ballot = Ballot.getInstance();
	
            		detail.addData(ballot.getScoreEachTeam(labelSelect));
            		detail.run(""); //$NON-NLS-1$
            		
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
    
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
    }
    
    
	

}

