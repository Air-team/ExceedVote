package exceedvote.air.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JButton;

import exceedvote.air.model.Voter;
/**
 * VoteType user interface
 * 
 * @author AIr Team
 * @version 2012.11.3
 */
public class VoteTypeUI extends JFrame implements RunUI
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
    //ballotleft
    private JLabel ballotLeft;
    
    //service for call other ui
    SeviceUI serviceUI;
    private int ballot = 0;
    /**
     * Create the frame.
     */
    public VoteTypeUI(Voter voter) 
    {
        ballot = voter.getballotLeft();
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
        txtpnVotetype.setBounds(10, 11, 176, 50);
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

        btnGoToVote = new JButton(new ActionSubmit());
        btnGoToVote.setText("Go to vote page");
        btnGoToVote.setBounds(255, 406, 169, 23);
        contentPane.add(btnGoToVote);

        select.setBounds(30, 410, 46, 14);
        contentPane.add(select);

        selectType = new JLabel();
        selectType.setText("none");
        selectType.setBounds(86, 410, 199, 14);
        contentPane.add(selectType);
        
        String shows = "You Have : "+String.valueOf(ballot)+" Ballot";
        ballotLeft = new JLabel(shows);
		ballotLeft.setBounds(267, 11, 142, 14);
		contentPane.add(ballotLeft);

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
                    serviceUI.runByName("voteUI",labelSelect);
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
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}

