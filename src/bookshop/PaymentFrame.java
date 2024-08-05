package bookshop;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class PaymentFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField secCodeText;
	private static Payment currentBasket;
	private double TotalCost;
	private String email;
	private String cardNumber;
	private String securityCode;
	private String currentType;
	private static String username;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PaymentFrame frame = new PaymentFrame(currentBasket, username);
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
	public PaymentFrame(Payment currentBasket, String username) throws FileNotFoundException {
		
		this.currentBasket = currentBasket;
		this.username = username;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 663, 517);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Initialises the panel which all visual information is displayed on
		
		String[] paymentOptions = {"Credit Card","PayPal"};
		JComboBox comboBox = new JComboBox(paymentOptions);
		comboBox.setBounds(22, 48, 138, 22);
		contentPane.add(comboBox);
		
		// Instantiates/initialises a new comboBox which allows you to choose between the payment methods Credit Card and PayPal
		
		JLabel lblNewLabel = new JLabel("Payment Method");
		lblNewLabel.setBounds(22, 23, 103, 14);
		contentPane.add(lblNewLabel);
		
		// Instantiates/initialises a new label
		
		JLabel lblNewLabel_1 = new JLabel("Current basket:");
		lblNewLabel_1.setBounds(22, 118, 128, 14);
		contentPane.add(lblNewLabel_1);
		
		// Instantiates/initialises a new label
		
		Book testBook = new Book();
		ArrayList<String> bookList = currentBasket.getBasket();
		bookList.add(0,"ISBN, Type, Title, Language, Genre, Release Date, Price, Quantity, Metric 1, Metric 2");
		JList list = new JList(bookList.toArray());
		
		// Retrieves all books in the ArrayList bookList and displays them as elements of a listBox, alonside a title row showing what each field represents
		        
        JScrollPane scrollPane = new JScrollPane();
      	scrollPane.setViewportView(list);
      	list.setLayoutOrientation(JList.VERTICAL);
		scrollPane.setBounds(22, 143, 600, 243);
      	contentPane.add(scrollPane);
      	
      	JLabel primaryLabel = new JLabel("Card Number");
      	primaryLabel.setHorizontalAlignment(SwingConstants.CENTER);
      	primaryLabel.setBounds(220, 23, 116, 14);
      	contentPane.add(primaryLabel);
      	
     // Instantiates/initialises a new label
      	
      	JLabel secCodeLabel = new JLabel("Security Code");
      	secCodeLabel.setHorizontalAlignment(SwingConstants.CENTER);
      	secCodeLabel.setBounds(393, 23, 116, 14);
      	contentPane.add(secCodeLabel);
      	
     // Instantiates/initialises a new label
      	
      	textField = new JTextField();
      	textField.setBounds(220, 49, 116, 20);
      	contentPane.add(textField);
      	textField.setColumns(10);
      	
     /* Initialises the textField textbox which the customer will write either an email or card number into to choose a new book, this textbox is then placed 
     onto the panel so it can be used */
      	
      	secCodeText = new JTextField();
      	secCodeText.setBounds(393, 49, 116, 20);
      	contentPane.add(secCodeText);
      	secCodeText.setColumns(10);
      	     	     	
     /* Initialises the textField textbox which the customer will write a security code into to choose a new book, this textbox is then placed onto the panel
	 so it can be used */
      	
      	JButton btnBack = new JButton("Back");
      	btnBack.addActionListener(new ActionListener() {
      		public void actionPerformed(ActionEvent e) {
      			
				CustomerFrame secondFrame;
				try {
					
					secondFrame = new CustomerFrame(username, false);
					dispose();
					secondFrame.setVisible(true);
					
					/* Instantiates CustomerFrame as the user returns to the previous page, it will then close 
					the current frame and set the CustomerFrame frame to be visible*/
					
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

      		}
      	});
      	btnBack.setBounds(548, 424, 74, 23);
      	contentPane.add(btnBack);
      	
      	JLabel lblNewLabel_2 = new JLabel("Total Price:");
      	lblNewLabel_2.setBounds(201, 428, 135, 14);
      	contentPane.add(lblNewLabel_2);
      	
        // Instantiates/initialises a new label
      	
      	bookList.remove(0);
      	
		for (int i = 0; i < bookList.size(); i++) {
			// Iterates through every item in bookList
			
			String currentBook = bookList.get(i);
			// Retrieves the current book in the list and stores it in the string currentBook
			String[] currentBookArr = currentBook.split(",");
			// Splits the currentBook string into an array of elements representing each field of a book in Stock.txt
			TotalCost = TotalCost + (Integer.parseInt(currentBookArr[7].trim())*Double.parseDouble(currentBookArr[6].trim()));
			// Adds the price of the current book(s) to the current total sum
		}
		
		lblNewLabel_2.setText(String.valueOf("Total Cost: £"+TotalCost));
		// Prints the total cost of all the books in the basket
      	
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// This function is called when one of the comboBox options are selected
				
				currentType = (String) comboBox.getItemAt(comboBox.getSelectedIndex());
				// Retrieves the contents of the selected option in the comboBox and stores it in the currentType field
				
				switch(currentType) {
				/* Based on the currently selected option from the payment comboBox, it updates the corresponding labels accordingly and sets the visibility
				of the securityNumber textbox */
				
					case "Credit Card":
						primaryLabel.setText("Card Number");
						// Sets the label for the first box to the credit card variant
						secCodeText.setVisible(true);
						secCodeLabel.setVisible(true);
						// Makes the security number textbox and label visible so the customer can enter one
						break;
						
					case "PayPal":
						primaryLabel.setText("Email");
						// Sets the label for the first box to the PayPal variant
						secCodeText.setVisible(false);
						secCodeLabel.setVisible(false);
						// Makes the security number textbox and label invisible so the customer cannot enter one
						break;

				}
			}
		});
	
      	JButton btnNewButton = new JButton("Confirm Payment");
      	btnNewButton.addActionListener(new ActionListener() {
      		public void actionPerformed(ActionEvent e) {
      			
      			if (bookList.isEmpty()) { return; }
      			currentType = (String) comboBox.getItemAt(comboBox.getSelectedIndex());
      			
				switch(currentType) {
				/* Based on the currently selected option from the payment comboBox, it updates the corresponding labels accordingly and sets the visibility
				of the securityNumber textbox */
				
				case "Credit Card":
					cardNumber = (textField.getText()).trim();
					securityCode = (secCodeText.getText()).trim();
					// Retrieves the values in the cardNumber and securityNumber textboxes and stores them in their respective fields
					
					if (!(cardNumber.length() == 8 && securityCode.length() == 3)) {
						// Checks if the card number and security code are the correct length, if they're not then the program will exit the function
						return;
					}	
					
						CreditCard creditPay = new CreditCard(bookList,username);
						// If they are the correct length then the program instantiates a new object 'creditPay' of the class 'CreditCard'
					try {
						creditPay.logActivity("purchased");
						// Calls the function used to log activities with 'purchased' tag
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					

					
					break;
					
				case "PayPal":

					email = textField.getText();
					// Retrieves the contents of the textField textbox and stores them in the email field
					PayPal onlinePay = new PayPal(bookList,username);
					// Instantiates a new object 'onlinePay' of the class 'Paypal'
					try {
						onlinePay.logActivity("purchased");
						// Calls the function used to log activities with 'purchased' tag
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					break;

			}
      			
      			try {
					currentBasket.updateStock(bookList);
					// Updates the Stock.txt file with the new quantities of books
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
      			
				
				CustomerFrame secondFrame;
				try {
					secondFrame = new CustomerFrame(username,true);
					secondFrame.setVisible(true);
					// Sets the CustomerFrame to be visible after instantiating it
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				finalPaymentFrame finalFrame = new finalPaymentFrame(TotalCost,currentType);
				dispose();
				finalFrame.setVisible(true);
				
				/* Instantiates finalFrame as the user returns to the previous page, it will then close 
				the current frame and set the finalFrame frame to be visible, this frame will show the
				total cost of the customer's purchase*/
      			
      		}
      	});
      	btnNewButton.setBounds(22, 424, 169, 23);
      	contentPane.add(btnNewButton);
	
	
	
	
	
	
	}
}
