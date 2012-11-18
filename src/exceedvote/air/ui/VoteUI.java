package exceedvote.air.ui;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.JTextPane;

import exceedvote.air.model.Ballot;
import exceedvote.air.model.Voter;

import java.awt.Color;
import java.awt.Font;
import java.util.List;
/**
 * eXceed Vote GUI 
 * 
 * @author AIr Team
 * @version 2012.10.1
 */
public class VoteUI extends JFrame implements RunUI
{
    private JPanel contentPane;

    private final JLabel label = new JLabel("New label");
    /**
     * @wbp.nonvisual location=81,-31
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
    private JButton btnBack;
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
    
    String[] names;
    // name select
    String selectTeam ="";
    // Voter
   
    private Voter voter;
    private Ballot ballott;
    public VoteUI(Voter voter,Ballot ballott)
    {
        this.voter = voter;
        this.ballott = ballott;
        ballot = voter.getballotLeft();
        names = ballott.getTeamNames();
        type = voter.getType();
        nameVoter = voter.getName();
        
    }
     //service for call other ui
    SeviceUI serviceUI;


    /*
     * init all component
     */
    public void initComponent()
    {
        textField_1.setColumns(10);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 474, 414);
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

        btnViewInfomation.setBounds(250, 100, 130, 23);
        contentPane.add(btnViewInfomation);

        // add action to vote button
        voteBtn = new JButton(new UpAction());
        voteBtn.setText("+");
        voteBtn.setBounds(250, 152, 89, 23);
        contentPane.add(voteBtn);

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
                	 boolean canVote = ballott.putBallot(selectTeam,typeTeam,voter);
                     
                     if(canVote)
                     {
                     	
                         status.setText("Status : "+type+" : "+String.valueOf(voter.getballotLeft())+" Ballot");
                     }
                }
            }
            //System.out.print(result);
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
    
    private class backAction extends AbstractAction{ 

        public backAction()
        { 
            super(); 
        } 

        public void actionPerformed(ActionEvent e)
        {   
            serviceUI.runByName("voteTypeUI");
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
        for(int i = 0 ; i < names.length ; i++)
        {    
            JButton eachTeam = new JButton(new ActionSelect());
            eachTeam.setText(names[i]);
            listTeam.add(eachTeam);
        }
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
        this.initComponent();
        this.setType(type);
        this.updateTeam();
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
