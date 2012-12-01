package exceedvote.air.ui;


import java.awt.BorderLayout;
import java.awt.EventQueue;

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

import exceedvote.air.model.Committee;
import exceedvote.air.model.Time;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * SetTimeUI for adding team
 * 
 * @author Air Team
 * @version (a version number or a date)
 */
public class SetTimeUI extends JFrame implements RunUI {

    private JPanel contentPane;
    private JComboBox setHour;
    private JComboBox setMin;
    private JComboBox setSecond;

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
    private String second;
    private SeviceUI serviceUI;
    /**
     * Create the frame.
     */
    public SetTimeUI() {

    }

    public SetTimeUI(SeviceUI serviceUI) {
		// TODO Auto-generated constructor stub
	}

	public void initComponent()
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 405, 242);
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JComboBox setYear = new JComboBox();
        setYear.setBounds(10, 40, 84, 20);
        contentPane.add(setYear);

        JLabel lblYear = new JLabel("Year");
        lblYear.setBounds(104, 43, 84, 14);
        contentPane.add(lblYear);

        JComboBox setMonth = new JComboBox();
        setMonth.setBounds(10, 85, 84, 20);
        contentPane.add(setMonth);

        JLabel lblMonth = new JLabel("Month");
        lblMonth.setBounds(104, 88, 84, 14);
        contentPane.add(lblMonth);

        JComboBox setDay = new JComboBox();
        setDay.setBounds(10, 126, 84, 20);
        contentPane.add(setDay);

        JLabel lblNewLabel = new JLabel("Day");
        lblNewLabel.setBounds(104, 129, 84, 14);
        contentPane.add(lblNewLabel);

        JLabel lblSetdate = new JLabel("SetTime");
        lblSetdate.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblSetdate.setBounds(10, 12, 464, 26);
        contentPane.add(lblSetdate);

        JLabel lblHour = new JLabel("Hour");
        lblHour.setBounds(296, 43, 84, 14);
        contentPane.add(lblHour);

        JLabel lblMin = new JLabel("Min\r\n");
        lblMin.setBounds(296, 88, 96, 14);
        contentPane.add(lblMin);

        JButton btnOk = new JButton("OK");
        btnOk.addActionListener(new OKbtn());
        btnOk.setBounds(10, 170, 89, 23);
        contentPane.add(btnOk);

        setHour = new JComboBox();
        setHour.addActionListener(new SelectHour());
        setHour.setBounds(202, 40, 84, 20);
        contentPane.add(setHour);

        setMin = new JComboBox();
        setMin.addActionListener(new SelectMin());
        setMin.setBounds(202, 85, 84, 20);
        contentPane.add(setMin);

        setSecond = new JComboBox();
        setSecond.addActionListener(new SelectSecond());
        setSecond.setBounds(202, 126, 84, 20);
        contentPane.add(setSecond);

        JLabel lblSecond = new JLabel("second");
        lblSecond.setBounds(296, 129, 120, 14);
        contentPane.add(lblSecond);

        
        setMonth.addItem("Jan");
        setMonth.addItem("Feb");
        setMonth.addItem("Mar");
        setMonth.addItem("Apr");
        setMonth.addItem("May");
        setMonth.addItem("June");
        setMonth.addItem("July");
        setMonth.addItem("Aug");
        setMonth.addItem("Sept");
        setMonth.addItem("Oct");
        setMonth.addItem("Nov");
        setMonth.addItem("Dec");

        Date d = new Date();
        int currentYear = d.getYear()+1900;
        int currentDay =d.getDay();
        int currentMonth = d.getMonth();
        setYear.addItem(String.valueOf(currentYear));
        for(int i = currentYear+1 ; i <= currentYear+12  ; i++)
        {
            setYear.addItem(i);
        }

        for(int i = 1 ; i <= 31 ; i++)
        {
            setDay.addItem(String.valueOf(i));
        }
        
        for(int i = 1 ; i <= 24 ; i++)
        {
            setHour.addItem(String.valueOf(i));
        }
        
        for(int i = 1 ; i <= 60 ; i++)
        {
            setMin.addItem(String.valueOf(i));
        }
        
        for(int i = 1 ; i <= 60 ; i++)
        {
            setSecond.addItem(String.valueOf(i));
        }

        selectYear = String.valueOf(2012);
        selectMonth = String.valueOf("Dec");
        selectDay = String.valueOf(1);
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
         
            String newSelection = cb.getSelectedItem()+"";
        
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
    
     private class SelectHour implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {
            JComboBox cb = (JComboBox) e.getSource();
            String newSelection = (String) cb.getSelectedItem();
            hour = newSelection;
        }
    }
    
     private class SelectMin implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {
            JComboBox cb = (JComboBox) e.getSource();
            String newSelection = (String) cb.getSelectedItem();
            min = newSelection;
        }
    }
    
     private class SelectSecond implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {
            JComboBox cb = (JComboBox) e.getSource();
            String newSelection = (String) cb.getSelectedItem();
            second = newSelection;
        }
    }

    // action when click ok
    private class OKbtn implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {
        
        		Committee com = new Committee();
        		com.setTime(selectDay, selectMonth, selectYear, hour, min, second);
   
                JOptionPane.showConfirmDialog((Component)
                    null,"You select " +selectYear +" -- "+selectMonth+" -- "+selectDay+" -- "+hour+" : " +min , "Pass!!", JOptionPane.DEFAULT_OPTION);
                dispose();
        }
    }

    public void run(String info)
    {
        this.initComponent();
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public static void main(String[] args)
    {
        SetTimeUI setTimeUI = new SetTimeUI();
        setTimeUI.run("");
    }

	public void addService(SeviceUI serviceUI) {
		this.serviceUI = serviceUI;
	}
}
