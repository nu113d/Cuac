
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JButton;
//import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.*;

import org.jpedal.examples.viewer.Commands;
import org.jpedal.examples.viewer.Viewer;

import java.io.File;
import java.time.temporal.ChronoUnit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Calendar;
import java.util.GregorianCalendar;

@SuppressWarnings("serial")
public class CovidWallet extends JFrame{
	//Constructors and variables
	private JFrame frame = new JFrame();
	private JPanel panel = new JPanel();
	private JTextField expirDateField = new JTextField("DD/MM/YY");
	//private JComboBox<String> certificateSelection;// Dropdown box for the user to choose the type of certificate.
	private JButton uploadFile = new JButton("Select File"); //Button that fires up JFileChooser, for file selection.
	private JButton submitDate = new JButton("Submit");
	private JButton showFile = new JButton("Show PDF"); // Button that fires up PDF Viewer.
	private ButtonListener clickUpload = new ButtonListener(); //ButtonListener assigned to the uploadFile button.
	private ButtonListener clickShow = new ButtonListener(); //ButtonListener assigned to the showFIle button.
	private ButtonListener clickSubmit = new ButtonListener();
	//private ComboBoxListener select = new ComboBoxListener(); //ActionListener assigned to Combobox.
	private File file = null; //PDF file is stored here.
	private String filePath; //Path to the PDF file is stored here.
	//private String[] kind = {"Πιστοποιητικό Εμβολιασμού", "Πιστοποιητικό Νόσησης", "Rapid Test"};//Array containing the different kinds of certificates.
    private JLabel Quarantine_Countdown;
    private String pdfDate;
	//private Calendar issuingDate;
    long previousData=-1;
    JFrame pdfContainer = new JFrame(); //JFrame for the pdf viewer.
	
	private User u;
		
	//Constructor for CovidWallet window.
	public CovidWallet(User u) {
		
		this.u= u;
		filePath = u.getmyPdfPath(); //IT CAN BE NULL
		if(filePath!=null) {
			this.file = new File(filePath);
		}
		pdfDate = u.getmyPdfDate();//IT CAN BE NULL too
		if (!(pdfDate==(null))) {
		
			expirDateField.setText(pdfDate);
			countdown();
		
		
		
		
		}
		
		setIconImage(Toolkit.getDefaultToolkit().getImage("images\\UOMLOGOGR22.png"));
		setTitle("Cuac");
		frame.setTitle("Covid Wallet"); //Sets the title.
		panel.setBackground(new Color(0, 0, 51)); //Sets background colour.
		frame.add(panel);
		
		//certificateSelection = new JComboBox<String>(kind);
		//panel.add(certificateSelection);
		//certificateSelection.addActionListener(select);
		
		uploadFile.addActionListener(clickUpload);
		panel.add(uploadFile);
		
		showFile.addActionListener(clickShow);
		panel.add(showFile);
			
		panel.add(expirDateField);
		
		submitDate.addActionListener(clickSubmit);
		panel.add(submitDate);
		
		
		Quarantine_Countdown = new JLabel("");
		Quarantine_Countdown.setFont(new Font("Tahoma", Font.BOLD, 22));
		Quarantine_Countdown.setForeground(new Color(196,215,233,255));
		panel.add(Quarantine_Countdown);
				
		frame.setVisible(true);
		frame.setSize(500, 500);
	}
	
	class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			String action = e.getActionCommand();
			
			if(action.equals("Select File")  ) { //If the source of the event is the clickUpload button, the following lines are executed.
				JFileChooser selectFile = new JFileChooser(); //Creates JFileChooser object.
				
				selectFile.setCurrentDirectory(null); //Sets the directory that the file chooser will display by default. (Set to 'Documents')
				selectFile.setDialogTitle("Select Certificate"); //Sets the title of the file chooser's window.
				selectFile.setFileSelectionMode(JFileChooser.FILES_ONLY); // Only files will be displayed.
				selectFile.setFileFilter(new myFileFilter()); // Filters out all files except for PDFs.
	
				if (selectFile.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) { //If User chooses a file:
					file = selectFile.getSelectedFile(); // file is stored in the 'file' variable.
					filePath = file.getAbsolutePath();
					u.updatePDFPath(filePath);
					JOptionPane.showMessageDialog(null,"Επιτυχής ανανέωση πιστοποιητικού");
				  
				} else {
					System.out.println("No Selection "); // 'No Selection' is printed in the console when User does not choose a file.
				
				}
			
			}else if (action.equals("Submit")) {
			
				pdfDate = expirDateField.getText(); 
				u.updatePDFDate(pdfDate);
				JOptionPane.showMessageDialog(null,"Επιτυχής ανανέωση ημερομηνίας.");
				
				countdown();
			
			}else if(action.equals("Show PDF")) {
				if (file==null){
					JOptionPane.showMessageDialog(null,"Δεν εχει φορτωθεί καποιο αρχείο. Πάτα 'Select File' για να φορτώσεις ένα");
				
				}else if(!file.exists()) {
					JOptionPane.showMessageDialog(null,"Το αρχείο ίσως έχει αλλάξει θέση. Βρές το μέσω του 'Select File'");
				
				}else if(file.isFile()) {
					
					JPanel pdfViewer = new JPanel(); //JPanel for the pdf viewer.
					
					pdfContainer.add(pdfViewer); //JPanel is added to JFrame.
					
					Viewer viewer = new Viewer(pdfViewer, null); //PDF Viewer is created. It will appear in the JFrame created above.
					viewer.setupViewer(); //PDF Viewer is initialised.
					viewer.executeCommand(Commands.OPENFILE, new Object[] {filePath}); //Viewer displays PDF file specified by the 'filePath' variable.
					
					pdfContainer.setDefaultCloseOperation(HIDE_ON_CLOSE); //Default operation is set to hiding the JFrame.
					pdfContainer.setSize(700, 500); //Default width and height for the JFrame.
					pdfContainer.setResizable(true); //JFrame is set to be resizable.
					pdfContainer.setVisible(true); //JFrame is set to be visible.
				}
				
				else {
					JOptionPane.showMessageDialog(null, "Δεν έχει επιλεχθεί αρχείο.");
				}
				
			}
		}
		
	}
	
	//Filter class.
	public class myFileFilter extends FileFilter{
		private String fileFormat = "PDF";
		private char DOTIndex = '.';
		
		@Override
		public boolean accept(File f) {
			if(f.isDirectory()) { // If selected object is a directory, return true. (Made obsolete by exclusion of directories)
				return true;
			
			}else if(extension(f).equalsIgnoreCase(fileFormat)){ //If file is of type PDF, return true.
				return true;
			
			}else { // Else, return false.
				return false;
			}
			
		}

		@Override
		public String getDescription() { // Displays the type of files that are allowed, in the 'Files of Type:' field.
			
			return "PDF files only";
		}
		
		//Checks the extension of a file
		public String extension(File f) {
			String fileName = f.getName();
			int indexFile = fileName.lastIndexOf(DOTIndex);
			
			if(indexFile > 0 && indexFile < fileName.length() - 1) { //If the extension is of type '.pdf', it returns the file name.
				return fileName.substring(indexFile + 1);
			
			}else { //If not, it returns a blank string.
				return "";
			}
		
		
	}
}
	
	/*class ComboBoxListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(certificateSelection.getSelectedItem() == "Πιστοποιητικό Εμβολιασμού") {
				
			}
			
		}
		
	}*/

	public String getFilePath() {
		return filePath;
	}
	
	
	
	// This countdown calculates the time that each covid certificate is valid.
	
	 public void countdown() {
		 
		 
		 
		 
		 Thread clock= new Thread() {
			
			 
			 public void run() {
				 
				 try {
					 for(;;) {
						 
						 String[] nums3= pdfDate.split("/");   // splitting the numbers and adding them in a table 
						 int	nums[] = {-1,-1,-1};
							
							for (int index = 0; index < nums3.length; index++) {
								nums[index]=Integer.parseInt(nums3[index]); // from String to int
							                     
							}
						
						 
					 Calendar cal_now= new GregorianCalendar();  // creation of calendar of current day
					 Calendar cal_end= new GregorianCalendar();  // creation of calendar of expiration day
					 
					 int now_day = cal_now.get(Calendar.DAY_OF_MONTH);
					 int now_month = cal_now.get(Calendar.MONTH ) ;
					 int now_year = cal_now.get(Calendar.YEAR);
					 
					 cal_now.set(Calendar.DAY_OF_MONTH, now_day);						 
					 cal_now.set(Calendar.MONTH, now_month);
					 cal_now.set(Calendar.YEAR, now_year);
					 
					 int day_end =nums[0];
					 int month_end=nums[1]-1;    //modifications so that the date will be viewed in standard greek format
					 int year_end= nums[2];
					 
					 cal_end.set(Calendar.DAY_OF_MONTH, day_end);
					 cal_end.set(Calendar.MONTH, month_end);
					 cal_end.set(Calendar.YEAR, year_end);
					 
					
				
					 long noOfDaysBetween = ChronoUnit.DAYS.between(cal_now.toInstant(), cal_end.toInstant()); // calculate the days between two dates
				
					 if (noOfDaysBetween>0) {
						 Quarantine_Countdown.setText("Το πιστοποιητικό λήγει σε : "+noOfDaysBetween+ " μέρες");
						 u.getUserNotifications().updateMessage(1,"Το πιστοποιητικό σας λήγει σε : "+noOfDaysBetween+ " μέρες" );
						 u.updateNotification(1, u.getEmail(), "Το πιστοποιητικό σας λήγει σε : "+noOfDaysBetween+ " μέρες");
					 }
					 else {
							Quarantine_Countdown.setText("Το πιστοποιητικό έχει λήξει");
						 	u.getUserNotifications().updateMessage(1,"Το πιστοποιητικό έχει λήξει" );
						 	u.updateNotification(1, u.getEmail(), "Το πιστοποιητικό έχει λήξει");
						 	return;
					 }
					 

					 Quarantine_Countdown.revalidate(); //updates label
					 
					 
					 
					sleep(1000);
					 }
				 
			 }
				catch (InterruptedException e) {
					e.printStackTrace();
				}
				 
				 
		 }
			 
			 
		 };
		 
		
		clock.start();
	 	 
	 }	 
	
	
	
	
}




