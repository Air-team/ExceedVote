package exceedvote.air.ui;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.util.Date;

import javax.swing.JOptionPane;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * SetTimeUI for adding team
 * 
 * @author Air Team
 * @version (a version number or a date)
 */
public class SetTimeUI extends JFrame {

    private JPanel contentPane;
    private JTextField setHour;
    private JTextField setMin;
   
    private JComboBox setMonth;
    private JComboBox setYear;
    private JComboBox setDay;
    private JButton btnOk;
    
    // infomation when select
    private String selectYear;
    private String selectMonth;
    private String selectDay;
    private String hour;
    private String min;
    
    /**
     * Create the frame.
     */
    public SetTimeUI() {

    }

    public void initComponent()
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 186);
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        setYear = new JComboBox();
        setYear.setBounds(10, 40, 84, 20);
        contentPane.add(setYear);

        JLabel lblYear = new JLabel("Year");
        lblYear.setBounds(104, 43, 46, 14);
        contentPane.add(lblYear);

        setMonth = new JComboBox();
        setMonth.setBounds(145, 40, 71, 20);
        contentPane.add(setMonth);

        JLabel lblMonth = new JLabel("Month");
        lblMonth.setBounds(226, 43, 46, 14);
        contentPane.add(lblMonth);

        setDay = new JComboBox();
        setDay.setBounds(270, 40, 59, 20);
        contentPane.add(setDay);

        JLabel lblNewLabel = new JLabel("Day");
        lblNewLabel.setBounds(339, 43, 46, 14);
        contentPane.add(lblNewLabel);

        JLabel lblSetdate = new JLabel("SetTime");
        lblSetdate.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblSetdate.setBounds(10, 12, 464, 26);
        contentPane.add(lblSetdate);

        setHour = new JTextField();
        setHour.setBounds(8, 85, 86, 20);
        contentPane.add(setHour);
        setHour.setColumns(10);

        JLabel lblHour = new JLabel("Hour");
        lblHour.setBounds(104, 88, 359, 14);
        contentPane.add(lblHour);

        setMin = new JTextField();
        setMin.setColumns(10);
        setMin.setBounds(10, 116, 86, 20);
        contentPane.add(setMin);

        JLabel lblMin = new JLabel("Min\r\n");
        lblMin.setBounds(104, 119, 271, 14);
        contentPane.add(lblMin);

        btnOk = new JButton("OK");
        btnOk.setBounds(385, 113, 89, 23);
        contentPane.add(btnOk);
        btnOk.addActionListener(new OKbtn());
        
        
        setMonth.addItem("Jan");
        setMonth.addItem("Feb");
        setMonth.addItem("Mar");
        setMonth.addItem("Apr");
        setMonth.addItem("May");
        setMonth.addItem("Jun");
        setMonth.addItem("Jul");
        setMonth.addItem("Aug");
        setMonth.addItem("Sep");
        setMonth.addItem("Oct");
        setMonth.addItem("Nov");
        setMonth.addItem("Dec");

        Date d = new Date();
        int currentYear = d.getYear();
        int currentDay =d.getDay();
        int currentMonth = d.getMonth();

        for(int i = 0 ; i <= 12 ; i++)
        {
            setYear.addItem(String.valueOf(1900+currentYear-i));
        }

        for(int i = 1 ; i <= 31 ; i++)
        {
            setDay.addItem(String.valueOf(i));
        }
        
        selectYear = String.valueOf(currentYear);
        selectMonth = String.valueOf(currentMonth);
        selectDay = String.valueOf(currentDay);
        // add action in combobox
        setYear.addActionListener(new SelectYear());
        setDay.addActionListener(new SelectDay());
        setMonth.addActionListener(new SelectMonth());
    }
    
    
    // action when select year
    private class SelectYear implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {
            JComboBox cb = (JComboBox) e.getSource();
            String newSelection = (String) cb.getSelectedItem();
            selectYear = newSelection;
        }
    }
     
    // action when select month
    private class SelectMonth implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {
            JComboBox cb = (JComboBox) e.getSource();
            String newSelection = (String) cb.getSelectedItem();
            selectMonth = newSelection;
           
        }
    }
    
    // action when select day
    private class SelectDay implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {
            JComboBox cb = (JComboBox) e.getSource();
            String newSelection = (String) cb.getSelectedItem();
            selectDay = newSelection;
        }
    }
    
    // action when click ok
    private class OKbtn implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {
            String checkHour = setHour.getText();
            String checkMin = setMin.getText();
            try
            {
                int test = Integer.parseInt(checkHour);
                test = Integer.parseInt(checkMin);
                
                //if not cash 
                hour = setHour.getText();
                min = setMin.getText();
                
                // check it!!!
                JOptionPane.showConfirmDialog((Component)
                    null,"You select " +selectYear +" -- "+selectMonth+" -- "+selectDay+" -- "+hour+" : " +min , "Pass!!", JOptionPane.DEFAULT_OPTION);
            }
            catch(Exception x){ 
                JOptionPane.showConfirmDialog((Component)
                    null, "Your time is wrong ", "Wrong Input!!!", JOptionPane.DEFAULT_OPTION);}
        }
    }

    public void run(String info)
    {
        this.initComponent();
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args)
    {
        SetTimeUI setTimeUI = new SetTimeUI();
        setTimeUI.run("");
    }
}
