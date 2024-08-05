package bookshop;

import java.awt.BorderLayout;
import java.util.*;
import java.awt.EventQueue;
import java.io.FileNotFoundException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JList;


public class bookshop extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					bookshop frame = new bookshop();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
				
	}

	/**
	 * Create the frame.
	 * @throws FileNotFoundException 
	 */
	public bookshop() throws FileNotFoundException {
		
		User testUser = new User();
		// Instantiates a new object 'testUser' of the class 'User'
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 630, 419);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Initialises the panel which all visual information is displayed on
		
		textField = new JTextField();
		textField.setBounds(73, 12, 183, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		/* Initialises the username textbox which the user will write into when trying to log in, they can either login as an admin or a customer
		depending on what they enter */
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setBounds(10, 14, 97, 17);
		contentPane.add(lblNewLabel);
		
		// Instantiates/initialises a new label
		
		JButton btnNewButton = new JButton("Confirm");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String username = textField.getText();
				// Retrieves the contents of the textField textbox and stores it in the username field

				String[] userDetails = new String[7];
				// Creates a string that will store the data related to a user as elements
				
				try {
					userDetails = testUser.viewUser(username);
					// Calls the function used to retrieve the information related to a specific user
					
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				if (userDetails[6].trim().equals("admin")) {
					AdminFrame secondFrame = new AdminFrame();
					dispose();
					secondFrame.setVisible(true);
					
					/* Instantiates the Admin frame as the user progresses to the next page, it will then close 
					the current frame and set AdminFrame to be visible*/
					}
				else if (userDetails[6].trim().equals("customer")) {
					CustomerFrame secondFrame;
					try {
						secondFrame = new CustomerFrame(username, false);
						dispose();
						secondFrame.setVisible(true); 
						
						/* Instantiates the Customer frame as the user progresses to the next page, it will then close 
						the current frame and set CustomerFrame to be visible*/
						
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					}
			}
		});
		
		
		
		
		btnNewButton.setBounds(280, 11, 89, 23);
		contentPane.add(btnNewButton);
		
		JList list = new JList((testUser.getAllUsers()).toArray());
		// Retrieves all users in the UserAccounts file along with a title row
        JScrollPane scrollPane = new JScrollPane();
        
      	scrollPane.setViewportView(list);
      	list.setLayoutOrientation(JList.VERTICAL);
		scrollPane.setBounds(20, 50, 558, 295);
		contentPane.add(scrollPane);	
	 	// Adds a scroll bar to the listBox so the user can scroll up/down, it also places the listBox (and scrollbar) on the panel for the user to see
		
		JLabel lblNewLabel_1 = new JLabel("Please enter a username");
		lblNewLabel_1.setBounds(407, 15, 171, 14);
		contentPane.add(lblNewLabel_1);
	}
}
