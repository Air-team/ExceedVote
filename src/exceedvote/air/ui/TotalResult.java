package exceedvote.air.ui;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;

import exceedvote.air.model.Poll;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
public class TotalResult extends JFrame {

    private JPanel contentPane;
    private JTable resultTable;
    private DefaultTableModel model;
    private JButton btnGo;
    private Poll poll;

    /**
     * Create the frame.
     */
    public TotalResult() 
    {
    	poll = new Poll();
    	initComponent();
    }

    public void initComponent()
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 529);
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

    	
		Object[][] data = new Object[1][1] ;
		
        String[] col = {
            "Ranking", "Team", "Total Score"
        };
        model = new DefaultTableModel(data,col);


    
        resultTable = new JTable(data,col);
        resultTable.setForeground(Color.BLACK);
        resultTable.setFillsViewportHeight(true);
        resultTable.setEnabled(false);
        resultTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        resultTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		model = new DefaultTableModel(data,col){
			Class[] columnTypes = new Class[] {
					String.class, String.class, String.class
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
			};
			resultTable.setModel(model);
			resultTable.getColumnModel().getColumn(0).setPreferredWidth(100);
			resultTable.getColumnModel().getColumn(1).setPreferredWidth(130);
			resultTable.getColumnModel().getColumn(2).setPreferredWidth(200);
			resultTable.setGridColor(Color.GRAY);
		resultTable.setPreferredScrollableViewportSize(new Dimension(400, 300));
		resultTable.setFillsViewportHeight(true);
		 resultTable.setBounds(10, 57, 414, 384);
		contentPane.add(resultTable);
		

        JScrollPane scrollPane = new JScrollPane(resultTable);
        scrollPane.setBounds(10, 57, 414, 384);
 
        //Add the scroll pane to this panel.
        contentPane.add(scrollPane);

        JLabel lblTotalResult = new JLabel("Total Result");
        lblTotalResult.setFont(new Font("Tahoma", Font.PLAIN, 35));
        lblTotalResult.setBounds(10, 11, 354, 35);
        contentPane.add(lblTotalResult);

        JLabel lblSeeResultIn = new JLabel("See result in detial");
        lblSeeResultIn.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblSeeResultIn.setBounds(138, 453, 175, 28);
        contentPane.add(lblSeeResultIn);

        btnGo = new JButton("Go");
        btnGo.setBounds(323, 458, 89, 23);
        contentPane.add(btnGo);  
        btnGo.addActionListener(new GoToDetail());
    }
    
    private class GoToDetail implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {
          
        }
    }
    
	public void addData(List<ArrayList> list)
	{
	    
	   for(int i=0;i<list.size();i++){
		   List<String> info = list.get(i);
		   String t = (i+1)+"";
		   model.insertRow(i,new Object[]{t,info.get(0),info.get(1)});
	   }
	}
    public void run()
    {
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public static void main(String[] args)
    {
        TotalResult totalResult = new TotalResult();
        totalResult.run();
//        totalResult.addData("1","AIR","2500000");
    }
}
