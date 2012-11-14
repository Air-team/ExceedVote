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
import java.awt.Color;
import java.awt.Font;
import java.util.List;
import java.util.Observer;
import java.util.Observable;
/**
 * eXceed Vote GUI 
 * 
 * @author AIr Team
 * @version 2012.10.1
 */
public class VoteUI extends JFrame implements Observer
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
    /*
     * ballot test
     */
    private int ballot;
    private String type = "";
    private String nameVoter = "";
    /*
     * name team test
     */
    private String typeTeam = ""; 
    private String[] names;
    // name select
    private String select ="";
    // Voter
    private Voter voter;

    public VoteUI(Voter voter)
    {
        this.voter = voter;
        this.ballot = ((Ballot)this.voter.getBallot()).getValue();
        names = voter.getTeamNames();
        type = voter.getType();
        nameVoter = voter.getName();
    }

    /*
     * init all component
     */
    public void initComponent()
    {
        textField_1.setColumns(10);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 474, 414);
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        listTeam.setBounds(10, 64, 218, 301);
        contentPane.add(listTeam);
        listTeam.setBackground(Color.DARK_GRAY);
        listTeam.setLayout(new GridLayout(20, 1, 0, 0));
        
        status.setBounds(242, 11, 218, 30);
        contentPane.add(status);
        status.setBackground(Color.WHITE);
        status.setFont(new Font("Tahoma", Font.PLAIN, 15));
        status.setText("Status : "+type+" : "+String.valueOf(ballot)+" Ballot");
        status.setForeground(Color.BLACK);
        status.setEditable(false);
        
        
        txtpnTeamlist.setFont(new Font("Tahoma", Font.PLAIN, 15));
        txtpnTeamlist.setBackground(Color.WHITE);
		txtpnTeamlist.setEditable(false);
		txtpnTeamlist.setForeground(Color.BLACK);
        txtpnTeamlist.setText("TeamList:Good UI");
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
        teamSelect.setBackground(Color.WHITE);
		teamSelect.setForeground(Color.BLACK);
        teamSelect.setFont(new Font("Tahoma", Font.PLAIN, 15));
        teamSelect.setText("Team :");
        teamSelect.setBounds(242, 59, 206, 30);
        contentPane.add(teamSelect);
        
        revoteBtn.setBounds(349, 152, 89, 23);
        contentPane.add(revoteBtn);
        
        lblVote.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblVote.setForeground(Color.BLACK);
        lblVote.setBounds(249, 138, 46, 14);
        contentPane.add(lblVote);
        
        
        lblRevote.setForeground(Color.BLACK);
        lblRevote.setBounds(349, 139, 46, 14);
        contentPane.add(lblRevote);
        
        lblClickToSelect.setForeground(Color.BLACK);
        lblClickToSelect.setBounds(10, 45, 210, 14);
        contentPane.add(lblClickToSelect);
        
        btnHistory.setBounds(250, 216, 89, 23);
        contentPane.add(btnHistory);
        
        lblNewLabel.setForeground(Color.BLACK);
        lblNewLabel.setBounds(250, 200, 188, 14);
        contentPane.add(lblNewLabel);
        
    }

    private class UpAction extends AbstractAction{ 

        public UpAction()
        { 
            super(); 
        } 

        public void actionPerformed(ActionEvent e)
        {   
            if(select.equals(""))
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
                String alert = "Do you want to vote " + select ;
                int result = JOptionPane.showConfirmDialog((Component)
                        null, alert , "Submit Vote!!!", JOptionPane.YES_NO_OPTION);
                if(result == 0)
                {
                    boolean canVote = voter.pullBollot(select);
                    if(canVote)
                    {
                        ballot--;
                        status.setText("Status : "+type+" : "+String.valueOf(ballot)+" Ballot");
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
            select = o.getText();
            teamSelect.setText("Team : "+select);
        }
    }
    
    public void update(Observable subject , Object info)
    {   
        List<Team> teamList = (List<Team>)info;
        String nameNew = ((Team)teamList.get(teamList.size()-1)).getName();
        JButton eachTeam = new JButton(new ActionSelect());
        eachTeam.setText(nameNew);
        listTeam.add(eachTeam);
        teamSelect.setText("Team : "+select);
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
