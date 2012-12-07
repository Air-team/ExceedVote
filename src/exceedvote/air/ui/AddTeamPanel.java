package exceedvote.air.ui;
 


import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import exceedvote.air.controller.ControllerControl;
import exceedvote.air.model.Committee;


public class AddTeamPanel extends JFrame implements RunUI {

    private JPanel contentPane;
    private JTextField imageName;
    private JLabel teamHeadLine;
    // headline
    private JLabel teamName = new JLabel();

    // browse btn
    private JButton btnBrowse;

    //add btn
    private JButton btnAdd;

    // btn cancel
    private JButton btnCancel;

    // description text
    private JTextPane descriptionText;
    
    private DefaultListModel teamModel = new DefaultListModel();
    
    //JFileChooser
    private JFileChooser chooser;
	private SeviceUI serviceUI;
	private boolean checkComplete =  false;
	Committee commitee = new Committee();


    /**
     * Create the frame.
     * @param serviceUI 
     */
    public AddTeamPanel(SeviceUI serviceUI) 
    {
    	this.serviceUI = serviceUI;
    }
    
    public void setModel(DefaultListModel teamModel ){
    	this.teamModel = teamModel;
    }
    
    public DefaultListModel getModel(){
    	return teamModel;
    }
    
    public boolean getCheckComplete(){
    	return checkComplete;
    }
    
    public void initComponent()
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 392, 388);
        contentPane = new JPanel();
        contentPane.setBackground(Color.DARK_GRAY);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        teamHeadLine = new JLabel(Messages.getString("AddTeamPanel.label.team")); //$NON-NLS-1$
        teamHeadLine.setForeground(Color.WHITE);
        teamHeadLine.setFont(new Font("Tahoma", Font.PLAIN, 16)); //$NON-NLS-1$
        teamHeadLine.setBounds(10, 11, 356, 21);
        contentPane.add(teamHeadLine);

        
       
        teamName.setForeground(Color.WHITE);
        teamName.setFont(new Font("Tahoma", Font.PLAIN, 16)); //$NON-NLS-1$
        teamName.setBounds(10, 20, 356, 21);
        contentPane.add(teamName);
        
        JLabel lblDescription = new JLabel(Messages.getString("AddTeamPanel.label.description")); //$NON-NLS-1$
        lblDescription.setForeground(Color.WHITE);
        lblDescription.setBounds(10, 43, 356, 14);
        contentPane.add(lblDescription);

        imageName = new JTextField();
        imageName.setEditable(false);
        imageName.setBounds(10, 252, 249, 20);
        contentPane.add(imageName);
        imageName.setColumns(10);

        JLabel lblNewLabel = new JLabel(Messages.getString("AddTeamPanel.label.picture")); //$NON-NLS-1$
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setBounds(10, 228, 249, 14);
        contentPane.add(lblNewLabel);

        btnBrowse = new JButton(Messages.getString("AddTeamPanel.butt.browse")); //$NON-NLS-1$
        btnBrowse.addActionListener(new OpenClass());
        btnBrowse.setBounds(269, 251, 89, 23);
        contentPane.add(btnBrowse);

        btnAdd = new JButton(Messages.getString("AddTeamPanel.butt.add")); //$NON-NLS-1$
        btnAdd.addActionListener(new addTeam());
        btnAdd.setBounds(91, 304, 89, 23);
        contentPane.add(btnAdd);

        btnCancel = new JButton(Messages.getString("AddTeamPanel.butt.cancel")); //$NON-NLS-1$
        btnCancel.addActionListener(new cancelAction());
        btnCancel.setBounds(190, 304, 89, 23);
        contentPane.add(btnCancel);

        descriptionText = new JTextPane();
        descriptionText.setBounds(10, 64, 356, 155);
        contentPane.add(descriptionText);

    }
    
    private class OpenClass implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            chooser = new JFileChooser();

            int option = chooser.showOpenDialog(AddTeamPanel.this);
            if(option == JFileChooser.APPROVE_OPTION) {
                imageName.setText( 
                    ((chooser.getSelectedFile()!=null)?
                            chooser.getSelectedFile().getAbsolutePath():
                        Messages.getString("AddTeamPanel.pop.nothing"))); //$NON-NLS-1$
            }

            if(option == JFileChooser.CANCEL_OPTION) {
                imageName.setText(Messages.getString("AddTeamPanel.pop.canceled")); //$NON-NLS-1$
            }
        }
    }
    
    private class cancelAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	checkComplete = false;
        	ControlPanel panel = new ControlPanel(commitee);
        	serviceUI.addUI("Panel",panel); //$NON-NLS-1$
        	panel.addService(serviceUI);
        	panel.run(""); //$NON-NLS-1$
        	dispose();
        }
    }

   
    private class addTeam implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try
            {
                ImageIcon imageIc = new ImageIcon(imageName.getText());
                java.awt.Image image = imageIc.getImage();  
                RenderedImage rendered = null;  
                if (image instanceof RenderedImage)  
                {  
                    rendered = (RenderedImage)image;  
                    
                }  
                else  
                {  
                    BufferedImage buffered = new BufferedImage(  
                            imageIc.getIconWidth(),  
                            imageIc.getIconHeight(),  
                            BufferedImage.TYPE_INT_RGB  
                        );  
                    Graphics2D g = buffered.createGraphics();  
                    g.drawImage(image, 0, 0, null);  
                    g.dispose();  
                    rendered = buffered;  
                }  
                ImageIO.write(rendered, "JPEG", new File(chooser.getSelectedFile().getName()));   //$NON-NLS-1$
                ControllerControl control = ControllerControl.getInstance();
            	String text = teamName.getText();
            	if( control.addTeam(teamName.getText(),chooser.getSelectedFile().getName(),descriptionText.getText())){
					teamModel.addElement(text);
					checkComplete = true;
					 JOptionPane.showConfirmDialog((Component)
			                    null, Messages.getString("AddTeamPanel.pop.added"), Messages.getString("AddTeamPanel.pop.success"), JOptionPane.DEFAULT_OPTION); //$NON-NLS-1$ //$NON-NLS-2$
			                checkComplete = true;
			           
			             
            	}
            	ControlPanel panel = new ControlPanel(commitee);
            	serviceUI.addUI("Panel",panel); //$NON-NLS-1$
            	panel.addService(serviceUI);
            	panel.run(""); //$NON-NLS-1$
            	dispose();
	             
               
            }
            catch(Exception x){
                    JOptionPane.showConfirmDialog((Component)
                    null, Messages.getString("AddTeamPanel.pop.warning"), Messages.getString("AddTeamPanel.pop.error"), JOptionPane.DEFAULT_OPTION); //$NON-NLS-1$ //$NON-NLS-2$
             }
        }
       
    
    }

    public void setTeamName(String team){
    	teamName.setText(team);
    }
  
    public boolean check(){
    	return checkComplete;
    }
    

    public void run()
    {
        this.initComponent();
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    /*
   	 * close
   	 */
   	public void close() {
   		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
   	}
   	
    public void addService(SeviceUI serviceUI)
    {
        this.serviceUI = serviceUI;
    }
    
	@Override
	public void run(String info) {
		this.initComponent();
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	}
}

