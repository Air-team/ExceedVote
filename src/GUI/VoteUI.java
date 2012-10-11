import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
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
    private JButton teamName;
    private JPanel team;
    private JPanel votePanel;

    // test teamInfo 
    private JPanel teamInfo = new JPanel();

    /*
     * ballot test
     */
    int ballot;
    String type = "";
    String nameVoter = "";
    /*
     * name team test
     */
    String[] names;
    // name select
    String select ="";
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
        top = new JPanel();
        statPanel = new JPanel();
        ballotPanel = new JPanel();
        buttom = new JPanel();
        vote = new JButton(new UpAction());
        status= new JLabel(type+" : "+nameVoter);
        showBallot = new JLabel("YOU HAVE BALLOT!!");
        teamName = new JButton();
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
                String alert = "Do you want to vote " + select ;
                int result = JOptionPane.showConfirmDialog((Component)
                        null, alert , "Submit Vote!!!", JOptionPane.YES_NO_OPTION);
                if(result == 0)
                {
                    boolean canVote = voter.pullBollot(select);
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

    private class ActionSelect extends AbstractAction{ 

        public ActionSelect()
        { 
            super(); 
        } 

        public void actionPerformed(ActionEvent e)
        {   
            JButton o = (JButton)e.getSource();
            select = o.getText();
            vote.setText("Vote "+select);
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
        teamName.setText(select);
    }

    /*
     * set team in UI,
     */
    public void setTaem()
    {
        team.setLayout(new GridLayout(20,1));
        for(int i = 0 ; i < names.length ; i++)
        {    
            JButton eachTeam = new JButton(new ActionSelect());
            eachTeam.setText(names[i]);
            team.add(eachTeam);
        }

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
