package exceedvote.air.ui;

 

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JScrollBar;

public class HistoryUI extends JFrame {

	private JPanel contentPane;
	private JTable table;
    private DefaultTableModel model;
	/**
	 * Create the frame.
	 */
	public HistoryUI()
	{
		initComponent();
	}
	
	public void initComponent()
	{
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 504, 423);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
//		contentPane.setLayout(null);
		
		
		JLabel lblHistory = new JLabel("History");
		lblHistory.setForeground(Color.WHITE);
		lblHistory.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblHistory.setBounds(10, 11, 113, 31);
		contentPane.add(lblHistory);
		
		Object[][] data = new Object[1][1] ;
		
		
		String[] col = new String[]{
				"Team", "Topic", "Time"
			};
		
		table = new JTable(data,col);
		table.setForeground(Color.BLUE);
		table.setFillsViewportHeight(true);
		table.setEnabled(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		model = new DefaultTableModel(data,col){
			Class[] columnTypes = new Class[] {
					String.class, String.class, String.class, String.class
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
			};
		table.setModel(model);
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setPreferredWidth(130);
		table.getColumnModel().getColumn(2).setPreferredWidth(200);
		table.setGridColor(Color.GRAY);
		table.setPreferredScrollableViewportSize(new Dimension(400, 300));
        table.setFillsViewportHeight(true);
		contentPane.add(table);
		
		
		

        JScrollPane scrollPane = new JScrollPane(table);
 
        //Add the scroll pane to this panel.
        contentPane.add(scrollPane);
	}
	
	public void addData(List<ArrayList> list)
	{
	    
	   for(int i=0;i<list.size();i++){
		   List<String> info = list.get(i);
		   model.insertRow(i,new Object[]{info.get(0),info.get(1),info.get(2)});
	   }
	}
	
	public void run()
	{
	    this.initComponent();
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
//	public static void main(String[] args)
//	{
//	    HistoryUI historyUI = new HistoryUI();
//	    historyUI.run();
//	}
}
