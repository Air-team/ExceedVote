package exceedvote.air.ui;

 

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;


/**
 * Indicate about history of that person about voting
 * @author AirTeam
 *
 */
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
	
	/** Initial Component */
	public void initComponent()
	{
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 504, 423);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblHistory = new JLabel(Messages.getString("HistoryUI.label.history")); //$NON-NLS-1$
		lblHistory.setForeground(Color.WHITE);
		lblHistory.setFont(new Font("Tahoma", Font.PLAIN, 25)); //$NON-NLS-1$
		lblHistory.setBounds(10, 11, 113, 31);
		contentPane.add(lblHistory);
		
		Object[][] data = new Object[1][1] ;
		
		
		String[] col = new String[]{
				Messages.getString("HistoryUI.team"), Messages.getString("HistoryUI.topic"), Messages.getString("HistoryUI.time") //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
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
	
	/** Add the history in table */
	public void addData(List<ArrayList> list)
	{
	    
	   for(int i=0;i<list.size();i++){
		   List<String> info = list.get(i);
		   model.insertRow(i,new Object[]{info.get(0),info.get(1),info.get(2)});
	   }
	}
	
	/**
	 * run this frame
	 */
	public void run()
	{
	    this.initComponent();
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

}
