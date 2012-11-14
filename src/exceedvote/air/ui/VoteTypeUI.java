package exceedvote.air.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;


/**
 * VoteType user interface
 * 
 * @author AIr Team
 * @version 2012.11.3
 */
public class VoteTypeUI extends JFrame 
{

    private JPanel contentPane;
    private JTextPane txtpnVotetype = new JTextPane();
    // button type beautiful
    private JButton btnBeautifulUI;
    // button type good function
    private JButton btnGoodFunction;
    // button type no bug
    private JButton btnNoBug;
    // button type presentation
    private JButton btnPresentation;

    private JLabel lblSelectTheType = new JLabel("Click to select the type");

    // button submit 
    private JButton btnGoToVote;
    private JLabel select = new JLabel("Select :");

    // label shows which user select type
    private JLabel selectType;
    private String labelSelect = "";

    // voteUi run after user select and click submit button
    private VoteUI voteUI;
    /**
     * Create the frame.
     */
    public VoteTypeUI(VoteUI voteUi) 
    {
        this.voteUI = voteUi;
    }

    /*
     * set all component
     */
    public void initComponent()
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 478);
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        txtpnVotetype.setEditable(false);
        txtpnVotetype.setFont(new Font("Tahoma", Font.PLAIN, 36));
        txtpnVotetype.setText("VoteType");
        txtpnVotetype.setBounds(10, 11, 248, 50);
        contentPane.add(txtpnVotetype);

        lblSelectTheType.setBounds(20, 72, 185, 14);
        contentPane.add(lblSelectTheType);

        btnBeautifulUI = new JButton(new ActionSelect());
        btnBeautifulUI.setText("Beautiful UI");
 
        btnGoodFunction = new JButton(new ActionSelect());
        btnGoodFunction.setText("Good Function");
     
        btnNoBug = new JButton(new ActionSelect());
        btnNoBug.setText("No Bug");
      
        btnPresentation = new JButton(new ActionSelect());
        btnPresentation.setText("Presentation");
      

        btnBeautifulUI.setBounds(30, 97, 379, 30);
        contentPane.add(btnBeautifulUI);

        btnGoodFunction.setBounds(30, 138, 379, 30);
        contentPane.add(btnGoodFunction);

        btnNoBug.setBounds(30, 179, 379, 30);
        contentPane.add(btnNoBug);

        btnPresentation.setBounds(30, 220, 379, 30);
        contentPane.add(btnPresentation);

        btnGoToVote = new JButton(new ActionSubmit(voteUI));
        btnGoToVote.setText("Go to vote page");
        btnGoToVote.setBounds(255, 406, 169, 23);
        contentPane.add(btnGoToVote);

        select.setBounds(30, 410, 46, 14);
        contentPane.add(select);

        selectType = new JLabel();
        selectType.setText("none");
        selectType.setBounds(86, 410, 199, 14);
        contentPane.add(selectType);

    }

    /*
     * action event when user select
     * After user click any type, the type will show in buttom of interface
     */
    private class ActionSelect extends AbstractAction{ 

        public ActionSelect()
        { 
            super(); 
        } 

        public void actionPerformed(ActionEvent e)
        {   
            JButton o = (JButton)e.getSource();
            labelSelect = o.getText();
            selectType.setText(labelSelect);
        }
    }

    /*
     * Action event when user click submit button
     * if type was select, run voterUi.
     * Show messaebox when user click submit but don't select any type. 
     */
    private class ActionSubmit extends AbstractAction{ 

        private VoteUI voteUI;

        public ActionSubmit(VoteUI voteUI)
        { 
            super(); 
            this.voteUI = voteUI;
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
                if(voteUI != null){
//                	VoteTopic voteTopic = new VoteTopic(labelSelect);
                    voteUI.run(labelSelect);
                }
            }
        }
    }
    
    /*
     * run this frame 
     */
    public void run()
    {
        this.initComponent();
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}

