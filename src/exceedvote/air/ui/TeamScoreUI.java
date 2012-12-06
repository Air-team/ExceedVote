package exceedvote.air.ui;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;


import java.util.ArrayList;
import java.util.List;
import javax.swing.JScrollPane;
import javax.swing.table.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;

public class TeamScoreUI extends JFrame {

    private JPanel contentPane;
    private JTable resultTable;
    
    
    // model data
    private DefaultTableModel model;
    private static int currnetRow = 0;
    private static int currentColum = 2;
    private static List<String> numberType = new ArrayList<String>();
    private String team;
    
    
    public TeamScoreUI(String labelSelect) {
    	this.team = labelSelect;
    	initComponent();
    }
    
    public void  initComponent()
    {
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 724, 466);
        getContentPane().setLayout(null);

        JLabel lblDetailResult = new JLabel(Messages.getString("TeamScoreUI.label.result")); //$NON-NLS-1$
        lblDetailResult.setFont(new Font("Tahoma", Font.PLAIN, 20)); //$NON-NLS-1$
        lblDetailResult.setBounds(10, 11, 131, 30);
        getContentPane().add(lblDetailResult);


      
        //getContentPane().add(sortTable);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        
        Object[][] data = new Object[1][1] ;
		
        String[] col = {
            Messages.getString("TeamScoreUI.table.ranking"), Messages.getString("TeamScoreUI.table.topic"), Messages.getString("TeamScoreUI.table.score") //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        };
      


    
        resultTable = new JTable(data,col);
        resultTable.setForeground(Color.BLACK);
        resultTable.setFillsViewportHeight(true);
        resultTable.setEnabled(true);
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
        
		scrollPane.setBounds(10, 52, 688, 320);
		getContentPane().add(scrollPane);
     
    }
    
    /*
     * addType by string
     */
    public void addType(String type)
    {   
        model.addColumn(type);
        currentColum++;
        numberType.add(type);
    }

    
    /*
     * add the result for each team by array string {rank,team name,score in each topic}
     * ex. {1,"Air",1000} 1000 mean score in first topic
     * 
     */
    public void addData(List<ArrayList> list)
	{
	    
	   for(int i=0;i<list.size();i++){
		   List<String> info = list.get(i);
		   String t = (i+1)+""; //$NON-NLS-1$
		   model.insertRow(i,new Object[]{t,info.get(0),info.get(1)});
	   }
	}
    
    public void run(String info)
    {
        
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
    public void sortBytype()
    {
        
    }
    
   

}
