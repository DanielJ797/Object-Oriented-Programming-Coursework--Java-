package bookshop;

import java.awt.BorderLayout;
import java.lang.Math;
import java.awt.EventQueue;
import java.text.SimpleDateFormat;  
import java.util.Date;  

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.awt.event.ActionEvent;

public class AdminFrame extends JFrame {

	private JPanel contentPane;
	private JTextField title;
	private JTextField genre;
	private JTextField quantity;
	private JTextField price;
	private JTextField optionalField1;
	private JTextField optionalField2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminFrame frame = new AdminFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AdminFrame() {
		
		String[] newBook = new String[10];
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 591, 404);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Initialises the panel which all visual information is displayed on
		
		JLabel lblNewLabel = new JLabel("Please enter all information about the new book(s):");
		lblNewLabel.setBounds(20, 11, 365, 14);
		contentPane.add(lblNewLabel);
		
		// Instantiates/initialises a new label
		
		title = new JTextField();
		title.setBounds(183, 95, 202, 20);
		contentPane.add(title);
		title.setColumns(10);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() 
		
		// Initialises the Back button which the user can return to the previous page
		{
			public void actionPerformed(ActionEvent e) {
				
				try {
					bookshop firstFrame = new bookshop();
					dispose();
					firstFrame.setVisible(true);
					
					/* Instantiates the login frame as the user returns to the previous page, it will then close 
					the current frame and set the LoginPage frame to be visible*/
					
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnNewButton.setBounds(310, 303, 75, 23);
		contentPane.add(btnNewButton);
			
		// Places the button on the panel so that it can be pressed
		
		genre = new JTextField();
		genre.setBounds(183, 126, 202, 20);
		contentPane.add(genre);
		genre.setColumns(10);
		
		/* Initialises the genre textbox which the admin will write into to add a new book, this texbox is then
		placed onto the panel so it can be used*/
		
		quantity = new JTextField();
		quantity.setBounds(183, 157, 202, 20);
		contentPane.add(quantity);
		quantity.setColumns(10);
		
		/* Initialises the quantity textbox which the admin will write into to add a new book, this texbox is then
		placed onto the panel so it can be used*/
		
		price = new JTextField();
		price.setBounds(183, 188, 202, 20);
		contentPane.add(price);
		price.setColumns(10);
		
		/* Initialises the price textbox which the admin will write into to add a new book, this texbox is then
		placed onto the panel so it can be used*/
		
		optionalField1 = new JTextField();
		optionalField1.setBounds(183, 219, 202, 20);
		contentPane.add(optionalField1);
		optionalField1.setColumns(10);
		
		/* Initialises the optionalField textbox which the admin will write into to add a new book, this texbox is then
		placed onto the panel so it can be used*/
		
		JLabel lblNewLabel_1 = new JLabel("Book Title");
		lblNewLabel_1.setBounds(46, 98, 112, 14);
		contentPane.add(lblNewLabel_1);
		
		// Instantiates/initialises a new label
		
		JLabel lblNewLabel_1_1 = new JLabel("Genre");
		lblNewLabel_1_1.setBounds(45, 129, 46, 14);
		contentPane.add(lblNewLabel_1_1);
		
		// Instantiates/initialises a new label
		
		JLabel lblNewLabel_1_2 = new JLabel("Quantity");
		lblNewLabel_1_2.setBounds(45, 158, 59, 14);
		contentPane.add(lblNewLabel_1_2);
		
		// Instantiates/initialises a new label
		
		JLabel optionalLabel1 = new JLabel("Number of Pages");
		optionalLabel1.setBounds(45, 223, 131, 14);
		contentPane.add(optionalLabel1);
		
		// Instantiates/initialises a new label
		
		JLabel optionalLabel2 = new JLabel("Condition");
		optionalLabel2.setBounds(45, 253, 90, 14);
		contentPane.add(optionalLabel2);
		
		// Instantiates/initialises a new label
		
		JLabel lblNewLabel_1_6 = new JLabel("Price");
		lblNewLabel_1_6.setBounds(45, 192, 46, 14);
		contentPane.add(lblNewLabel_1_6);
		
		// Instantiates/initialises a new label
		
		String[] langOptions = {"English","French"};
		JComboBox comboBox_1 = new JComboBox(langOptions);
		comboBox_1.setBounds(209, 54, 138, 22);
		contentPane.add(comboBox_1);
		
		// Instantiates/initialises a new comboBox which allows you to choose between the languages English and French
		
		optionalField2 = new JTextField();
		optionalField2.setColumns(10);
		optionalField2.setBounds(183, 250, 202, 20);
		contentPane.add(optionalField2);
		
		/* Initialises the second optional textbox which the admin will write into to add a new book, this texbox is then
		placed onto the panel so it can be used*/
		
		String[] bookOptions = {"paperback","audiobook","ebook"};
		JComboBox comboBox = new JComboBox(bookOptions);
		comboBox.setBounds(46, 55, 130, 20);	
		contentPane.add(comboBox);
		
		// Instantiates/initialises a new comboBox which allows you to choose between the book types paperbacks, audiobooks and ebooks
		
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currentType = (String) comboBox.getItemAt(comboBox.getSelectedIndex());
				// Retrieves the contents of the comboBox and stores it in the variable currentType
								
				switch(currentType) {
				// Based on the currently selected option from the bookType comboBox, it updates the corresponding labels accordingly
				
					case "paperback":
						optionalLabel1.setText("Number of Pages");
						optionalLabel2.setText("Condition");
						// Sets the labels of optionalField1 and optionalField2 to the relevant values for paperback books
						break;
					case "audiobook":
						optionalLabel1.setText("Length");
						optionalLabel2.setText("Format");
						// Sets the labels of optionalField1 and optionalField2 to the relevant values for audiobooks
						break;
					case "ebook":
						optionalLabel1.setText("Number of Pages");
						optionalLabel2.setText("Format");
						// Sets the labels of optionalField1 and optionalField2 to the relevant values for ebooks
						break;
				}
			}
		});
		
		
		JButton btnNewButton_1 = new JButton("Confirm");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// This function is called when the confirm button is pressed, which indicates the admin has finalised their new book options
				
				int max = 9;
		        int min = 1;
		        int range = max - min + 1;
		        String rand = "";
		        // Initialises the values which will be used to generate a random number between 0 and 10 (exclusive)
				
		        for (int i = 0; i < 8; i++) {
		            rand = rand + Integer.toString((int)(Math.random() * range) + min); 
		            // Generates 8 random numbers which are between 0 and 10 and concatenates them together to create a new ISBN
		            }
				
		        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");  
		        Date date = new Date();   // Instantiates an object representing the current date
		        
		        
		        try {
		        	int n = Integer. parseInt(quantity.getText());	
		        	// If the value inside the quantity text box is an integer, it will be stored in the quantity field
		        	} catch (NumberFormatException e1) {
		        		return;
		        	}
		        try {
		        	double n = Double.parseDouble(price.getText());
		        	// If the value inside the price text box is a double, it will be stored in the price field
		        	} catch (NumberFormatException e1) {
		        		return;
		        	}
		        
		    	String currentType = (String) comboBox.getItemAt(comboBox.getSelectedIndex());   
		    	// Retrieves the contents of the comboBox and stores it in the variable currentType
		    	
		        switch(currentType) {
		        // based on the currently selected option from the bookType comboBox, it updates the corresponding labels accordingly
		        	case "audiobook":
		        		try {
		        			double n = Double. parseDouble(optionalField1.getText());
		        			// If the value inside the optionalField1 text box is a double, it will be stored in the field n, representing length
			        		} catch (NumberFormatException e1) {
			        			return;
			        		}
		        		break;
		        	
		        	default:
		        		try {
				        	int n = Integer. parseInt(optionalField1.getText());
				        	// If the value inside the optionalField1 text box is an int, it will be stored in the field n, representing the number of pages
				        	} catch (NumberFormatException e1) {
				        		return;        
				        	}
		        }
		        
		        if (!checkFieldTwo(currentType,optionalField2.getText())) {	return; }
		        // Checks if the value in optionalField2 is a valid one and executes the following code if it is, otherwise the program segment is skipped
		        
		        newBook[0] = rand;
				newBook[1] = (String) comboBox.getItemAt(comboBox.getSelectedIndex());
				newBook[2] = title.getText();
				newBook[3] = (String) comboBox_1.getItemAt(comboBox_1.getSelectedIndex());
				newBook[4] = genre.getText();
				newBook[5] = (String) formatter.format(date);
				newBook[6] = quantity.getText();
				newBook[7] = price.getText();
				newBook[8] = optionalField1.getText();
				newBook[9] = optionalField2.getText();
				
				// Initialises the elements of the newBook array with the relevant field values that have been entered into each text box by the admin
				Admin adminMenu = new Admin();
				// Instantiates the object used to add the new book
				try {
					adminMenu.addBook(newBook);
					// Calls the function which will write the new book information to Stock.txt
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		btnNewButton_1.setBounds(46, 303, 89, 23);
		contentPane.add(btnNewButton_1);
		// Places the button on the panel so that it can be pressed
	}
	
	
	public Boolean checkFieldTwo(String bookType, String parameter) {
		
		switch(bookType) {
		// Checks whether the book is a paperback, audiobook or ebook and directs the program flow accordingly
		
			case "paperback":
				if (parameter.equals("used") || parameter.equals("new")) { return(true); }
				else return(false);
				// Checks whether the condition entered matches one of the correct answers and returns a relevant boolean value based on the result
			case "audiobook":
				if (parameter.equals("MP3") || parameter.equals("WMA") || parameter.equals("AAC")) { return(true); }
				else return(false);
				// Checks whether the file type entered matches one of the correct answers and returns a relevant boolean value based on the result
			case "ebook":
				if (parameter.equals("EPUB") || parameter.equals("MOBI") || parameter.equals("AZW3") || parameter.equals("PDF")) { return(true); }
				else return(false);	
				// Checks whether the file type entered matches one of the correct answers and returns a relevant boolean value based on the result
		}
		
		return(false);
		// If the book type does not match any of the specific types, it will return false
	}
}
