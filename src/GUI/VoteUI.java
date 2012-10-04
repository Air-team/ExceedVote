package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.net.URL;
import java.lang.Class;
/**
 * eXceed Vote GUI 
 * 
 * @author AIr Team
 * @version 2012.10.1
 */
public class VoteUI extends JFrame
{
    private JPanel top ;
    private JPanel statPanel;
    private JPanel ballotPanel;
    private JPanel buttom;
    private JButton  vote;
    private JLabel status;
    private JLabel showBallot;
    private JLabel teamName;
    private JPanel team;
    private JPanel votePanel;
    private JLabel image = new JLabel();
    private Icon teamList = new ImageIcon();

    // test teamInfo 
    private JPanel teamInfo = new JPanel();

    /*
     * ballot test
     */
    int ballot;
    String tName = "";
    String type = "";
    String nameVoter = "";
    /*
     * name team test
     */
    String nameT = "Durian"; 
    // Voter
    private Voter voter;

    public VoteUI(Voter voter)
    {
        this.voter = voter;
        this.ballot = ((Ballot)this.voter.getBallot()).getValue();
        type = voter.getType();
        nameVoter = voter.getName();
        this.pack();
    }

    /*
     * init all component
     */
    public void initComponent()
    {
        top = new JPanel();
        statPanel = new JPanel();
        ballotPanel = new JPanel();
        buttom = new JPanel();
        vote = new JButton(new UpAction());
        status= new JLabel(type+" : "+nameVoter);
        showBallot = new JLabel("YOU HAVE BALLOT!!");
        teamName = new JLabel(nameT);
        team = new JPanel();
        votePanel = new JPanel();

        // test
        teamInfo.add(new JLabel("Name:  Durian"));
    }

    private class UpAction extends AbstractAction{ 

        public UpAction()
        { 
            super(); 
        } 

        public void actionPerformed(ActionEvent e)
        {   
            if(ballot == 0)
            {
                JOptionPane.showConfirmDialog((Component)
                    null, "No ballot Ja", "No Ballot!!", JOptionPane.DEFAULT_OPTION);
            }
            else
            {    
                String alert = "Do you want to vote " + nameT ;
                int result = JOptionPane.showConfirmDialog((Component)
                        null, alert , "Submit Vote!!!", JOptionPane.YES_NO_OPTION);
                if(result == 0)
                {
                    boolean canVote = voter.pullBollot(nameT);
                    if(canVote)
                    {
                        ballot--;
                        String shows = "YOU HAVE  "+ String.valueOf(ballot) +"  BALLOT!!";
                        showBallot.setText(shows);
                    }
                }
            }
            //System.out.print(result);
        }
    }

    /*
     * set component color(test)
     */
    public void setColor()
    {
        statPanel.setBackground(Color.ORANGE);
        buttom.setBackground(Color.BLACK);
        ballotPanel.setBackground(Color.ORANGE);
        teamName.setForeground(Color.BLACK);
    }

    /*
     * set vote button
     */
    public void setButton()
    {  
        GridLayout grid = new GridLayout(1,2);
        buttom.setLayout(grid);
        buttom.add(team);
        buttom.add(votePanel);
        vote.setText("Vote");
    }

    /*
     * set team in UI,
     */
    public void setTaem()
    {
        /*URL position = VoteUI.class.getResource("test.jpg");
        teamList = new ImageIcon(position);*/ 
        image.setIcon(teamList);
        team.setLayout(new BorderLayout());
        team.add(teamName,BorderLayout.NORTH);
        team.add(image,BorderLayout.CENTER);

        votePanel.setLayout(new BorderLayout());
        votePanel.add(vote,BorderLayout.NORTH);
        votePanel.add(teamInfo,BorderLayout.CENTER);
    }

    /*
     * set Label in UI
     */
    public void setLabel()
    {
        statPanel.add(status);
        ballotPanel.add( showBallot);
        teamName.setHorizontalAlignment(JLabel.CENTER);  
        teamName.setFont(new Font("Arial",Font.BOLD,23));
        String initText = "YOU HAVE "+String.valueOf(ballot)+" BALLOT!!!";
        showBallot.setText(initText);
        //turnBase.setHorizontalAlignment(JLabel.CENTER);    
    }

    /*
     * put components on JFrame
     */
    public void setFrame()
    {
        this.setLayout(new BorderLayout());
        top.setLayout(new GridLayout(1,2));
        top.add(statPanel);
        top.add(ballotPanel);
        //buttom.add()
        this.add(top,BorderLayout.NORTH);
        this.add(buttom, BorderLayout.CENTER);

    }

    /*
     * run Frame
     */
    public void run()
    {    
        this.initComponent();
        this.setFrame();
        this.setColor();
        this.setTaem();
        this.setLabel();
        this.setButton();

        this.setTitle("Vote UI");
        this.setSize(900,500);
        //this.pack();
        this.setVisible(true);
        // set Lock frame :|
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void setBallot()
    {
    }

    public void setListTeam()
    {
    }

    public void setStatus()
    {
    }

    public void update()
    {
    }
}
