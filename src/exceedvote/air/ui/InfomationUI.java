package exceedvote.air.ui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class InfomationUI extends JFrame implements RunUI{
	private JPanel contentPane;
	private JLabel teamHeadLine;
	private JLabel teamName;
	private SeviceUI serviceUI;

	
	public InfomationUI(){
		initComponent();
	}

	public void initComponent() {
		
		 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setBounds(100, 100, 392, 388);
	        contentPane = new JPanel();
	        contentPane.setBackground(Color.DARK_GRAY);
	        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	        setContentPane(contentPane);
	        contentPane.setLayout(null);

	        teamHeadLine = new JLabel("Team :");
	        teamHeadLine.setForeground(Color.WHITE);
	        teamHeadLine.setFont(new Font("Tahoma", Font.PLAIN, 16));
	        teamHeadLine.setBounds(10, 11, 356, 21);
	        contentPane.add(teamHeadLine);

	        
	        teamName = new JLabel("Team :");
	        
	        teamName.setForeground(Color.WHITE);
	        teamName.setFont(new Font("Tahoma", Font.PLAIN, 16));
	        teamName.setBounds(10, 20, 356, 21);
	        contentPane.add(teamName);
	}
	
	public void close() {
   		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   	}
	
	public void addService(SeviceUI serviceUI) {
		this.serviceUI = serviceUI;
	}
   
    
	public void run(String info) {
		this.setVisible(true);
		this.setResizable(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String [] args){
		InfomationUI in = new InfomationUI();
		SeviceUI serviceUI = new SeviceUI();
		serviceUI.addUI("InfomationUI", in);
		in.addService(serviceUI);
		in.run("");
		
	}
}
