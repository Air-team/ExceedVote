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
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import exceedvote.air.model.Ballot;
import exceedvote.air.model.Committee;
import exceedvote.air.model.Team;
import exceedvote.air.model.TeamDescription;

public class AddTeamPanel extends JFrame implements RunUI {

    private JPanel contentPane;
    private JTextField imageName;
    private VoteUI voteUI;
    // headline
    // headline
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
     */
    public AddTeamPanel() 
    {
    	
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

        teamHeadLine = new JLabel("Team :");
        teamHeadLine.setForeground(Color.WHITE);
        teamHeadLine.setFont(new Font("Tahoma", Font.PLAIN, 16));
        teamHeadLine.setBounds(10, 11, 356, 21);
        contentPane.add(teamHeadLine);

        
       
        teamName.setForeground(Color.WHITE);
        teamName.setFont(new Font("Tahoma", Font.PLAIN, 16));
        teamName.setBounds(10, 20, 356, 21);
        contentPane.add(teamName);
        
        JLabel lblDescription = new JLabel("Description : ");
        lblDescription.setForeground(Color.WHITE);
        lblDescription.setBounds(10, 43, 356, 14);
        contentPane.add(lblDescription);

        imageName = new JTextField();
        imageName.setEditable(false);
        imageName.setBounds(10, 252, 249, 20);
        contentPane.add(imageName);
        imageName.setColumns(10);

        JLabel lblNewLabel = new JLabel("Picture : ");
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setBounds(10, 228, 249, 14);
        contentPane.add(lblNewLabel);

        btnBrowse = new JButton("Browse");
        btnBrowse.addActionListener(new OpenClass());
        btnBrowse.setBounds(269, 251, 89, 23);
        contentPane.add(btnBrowse);

        btnAdd = new JButton("Add");
        btnAdd.addActionListener(new addTeam());
        btnAdd.setBounds(91, 304, 89, 23);
        contentPane.add(btnAdd);

        btnCancel = new JButton("Cancel");
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
                        "nothing"));
            }

            if(option == JFileChooser.CANCEL_OPTION) {
                imageName.setText("You canceled.");
            }
        }
    }
    
    private class cancelAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	checkComplete = false;
        	ControlPanel panel = new ControlPanel(commitee);
            panel.run("");
        	close();
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
                ImageIO.write(rendered, "JPEG", new File(chooser.getSelectedFile().getName()));  
            	TeamDescription teamDes = new TeamDescription("");
            	teamDes.setInfo(descriptionText.getText());
            	String text = teamName.getText();
            	Team team = new Team(teamName.getText(),teamDes);
            	
            	
            	if(commitee.setTeam(text)){
            		voteUI = new VoteUI();
					voteUI.initComponent();
					teamModel.addElement(text);
					checkComplete = true;
					 JOptionPane.showConfirmDialog((Component)
			                    null, "Team has been add", "Success!!", JOptionPane.DEFAULT_OPTION);
			                checkComplete = true;
			           
			             
            	}
            	 ControlPanel panel = new ControlPanel(commitee);
	             panel.run("");
	             close();
               
            }
            catch(Exception x){
                    JOptionPane.showConfirmDialog((Component)
                    null, "Image must be JPG or Your file is not an image", "Error", JOptionPane.DEFAULT_OPTION);
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

//    public static void main(String[] args)
//    {
////    	SeviceUI serviceUI = new SeviceUI();
////    	serviceUI.addUI("", ui)
//        AddTeamPanel addTeamPanel = new AddTeamPanel();
//        addTeamPanel.run();
//    }

    /*
   	 * close
   	 */
   	public void close() {
   		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
}

