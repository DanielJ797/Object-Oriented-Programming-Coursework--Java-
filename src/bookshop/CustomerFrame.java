package bookshop;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.FileNotFoundException;
import java.util.*;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class CustomerFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private static ArrayList<String> booksInBasket = new ArrayList<String>();
	private static String username;
	private JTextField textField_2;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerFrame frame = new CustomerFrame(username, false);
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
	public CustomerFrame(String username, boolean madePayment) throws FileNotFoundException {
				
		this.username = username;
		
		if (madePayment) { booksInBasket.clear(); }
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 661);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Initialises the panel which all visual information is displayed on
		
		JLabel feedbackLabel = new JLabel("");
		feedbackLabel.setForeground(Color.RED);
		feedbackLabel.setBounds(663, 226, 196, 14);
		contentPane.add(feedbackLabel);
		
		// Instantiates/initialises a new label
		
		JLabel feedbackLabel2 = new JLabel("");
		feedbackLabel2.setBounds(663, 240, 153, 14);
		feedbackLabel2.setForeground(Color.RED);
		contentPane.add(feedbackLabel2);
		
		// Instantiates/initialises a new label
		
		Book testBook = new Book();
		// Instantiates a new object 'testBook' of the class 'Book'
		ArrayList<String> bookList = testBook.getAllBooks();
		bookList.add(0,"ISBN, Type, Title, Language, Genre, Release Date, Price, Quantity, Metric 1, Metric 2");
		JList list = new JList(bookList.toArray());
		
		// Retrieves all books in the Stock file and displays them as elements of a listBox, alonside a title row showing what each field represents
        
        JScrollPane scrollPane = new JScrollPane();
      	scrollPane.setViewportView(list);
      	list.setLayoutOrientation(JList.VERTICAL);
		scrollPane.setBounds(25, 22, 600, 441);
      	contentPane.add(scrollPane);
      	
      	// Adds a scroll bar to the listBox so the user can scroll up/down, it also places the listBox (and scrollbar) on the panel for the user to see
		
		textField = new JTextField();
		textField.setBounds(651, 82, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		// Initialises the ISBN textbox which the customer will write into to choose a new book, this texbox is then placed onto the panel so it can be used
		
		JLabel lblNewLabel = new JLabel("ISBN");
		lblNewLabel.setBounds(767, 85, 92, 14);
		contentPane.add(lblNewLabel);
		
		// Instantiates/initialises a new label
		
		JButton btnNewButton = new JButton("Add to basket");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Executes if the 'Add to basket' button to pressed by the customer
								
				Customer checkInput = new Customer(booksInBasket);
				String ISBN = textField.getText();
				// Initialises the ISBN field by retrieving the contents of the textField textbox

				if (!checkInput.checkValue(ISBN)) { 
					feedbackLabel.setText("Invalid Input");
					feedbackLabel2.setVisible(false);
					return; }
				
				if (!checkInput.checkValue(textField_1.getText())) { 
					feedbackLabel.setText("Invalid Input");
					feedbackLabel2.setVisible(false);
					return; }
				
				int Quantity = Integer.parseInt(textField_1.getText());
				// Initialises the Quantity field by retrieving the contents of the textField textbox
				
				if (checkInput.checkBasket(ISBN)) { 
					feedbackLabel.setText("This Specific book is already");
					feedbackLabel2.setText("in the basket.");
					feedbackLabel2.setVisible(true);
					feedbackLabel.setForeground(Color.red);
					return; }
				feedbackLabel2.setVisible(false);
				
				String[] selectedBook = new String[10];
				// Creates a new array which will hold the currently selected book's information
				
				try {
					selectedBook = testBook.viewBook(ISBN);
					// Calls the function in the Book class which retrieves a specific row in Stock.txt using the ISBN entered by the customer
					
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				if (Quantity > Integer.parseInt(selectedBook[7].trim())) { 
					feedbackLabel.setText("This amount exceeds");
					feedbackLabel2.setText("the current total.");
					feedbackLabel.setForeground(Color.red);
					feedbackLabel2.setVisible(true);
					return; }
				// Checks to make sure that the entered quantity by the customer does not exceed the current quantity of the book selected
				
				selectedBook[7] = " "+Quantity; 
				String selectedBookStr = Arrays.toString(selectedBook);
				// Converts the array to a string
				booksInBasket.add(selectedBookStr.substring(1,selectedBookStr.length()-1));
				// Adds the selectedBook to the list of books currently in the basket
				feedbackLabel.setText("Added to Basket");
				feedbackLabel.setForeground(Color.green);
			}
		});
		btnNewButton.setBounds(663, 177, 153, 23);
		contentPane.add(btnNewButton);
		// Adds the 'Add to basket' button for the customer to press
		
		JLabel lblNewLabel_1 = new JLabel("Quantity");
		lblNewLabel_1.setBounds(767, 129, 92, 14);
		contentPane.add(lblNewLabel_1);
		
		// Instantiates/initialises a new label
		
		textField_1 = new JTextField();
		textField_1.setBounds(651, 126, 86, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		// Initialises the Quantity textbox which the customer will write into to choose a new book, this textbox is then placed onto the panel so it can be used
		
		JLabel lblNewLabel_2 = new JLabel("you would like to add to basket.");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_2.setBounds(637, 38, 199, 20);
		contentPane.add(lblNewLabel_2);
		
		// Instantiates/initialises a new label
		
		JLabel lblNewLabel_2_1 = new JLabel("Please enter the ISBN of the book(s)");
		lblNewLabel_2_1.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2_1.setBounds(637, 24, 237, 20);
		contentPane.add(lblNewLabel_2_1);
		
		// Instantiates/initialises a new label
		
		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.addActionListener(new ActionListener() {
			// Initialises the Back button which the user can return to the previous page
			
			public void actionPerformed(ActionEvent e) {
				try {
					booksInBasket.clear();
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
		btnNewButton_1.setBounds(770, 560, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("View Basket");
		btnNewButton_2.addActionListener(new ActionListener() {
			// Initialises the 'Confirm Purchase' button which the user can return to the previous page

			
			public void actionPerformed(ActionEvent e) {
				
				Payment currentBasket = new Payment(booksInBasket);
				// Instantiates a new object 'currentBasket' of the class 'Payment'
				
				PaymentFrame thirdFrame;
				try {
					thirdFrame = new PaymentFrame(currentBasket,username);
					dispose();
					thirdFrame.setVisible(true);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				
			}
		});
		
		btnNewButton_2.setBounds(663, 306, 153, 23);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_2_1 = new JButton("Clear basket");
		btnNewButton_2_1.addActionListener(new ActionListener() {
			// Initialises the 'Clear basket' button which the user can return to the previous page

			public void actionPerformed(ActionEvent e) {
				
				Payment currentBasket = new Payment(booksInBasket);
				// Instantiates a new object 'currentBasket' of the class 'Payment'
				
				try {
					currentBasket.logActivity("cancelled", username);
					// Calls the function used to log activities with the relevant username and the 'cancelled' tag 
					
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				booksInBasket.clear();
				// Empties the list holding all books in the basket
				
				feedbackLabel.setText("Cleared Basket");
				feedbackLabel2.setVisible(false);
				feedbackLabel.setForeground(Color.green);
			}
		});
		btnNewButton_2_1.setBounds(663, 272, 153, 23);
		contentPane.add(btnNewButton_2_1);
		
		JLabel lblNewLabel_3 = new JLabel("Paperback:");
		lblNewLabel_3.setBounds(25, 474, 115, 14);
		contentPane.add(lblNewLabel_3);
		
		// Instantiates/initialises a new label
		
		JLabel lblNewLabel_4 = new JLabel("Metric 1 = Number of pages");
		lblNewLabel_4.setBounds(25, 494, 173, 14);
		contentPane.add(lblNewLabel_4);
		
		// Instantiates/initialises a new label
		
		JLabel lblNewLabel_4_1 = new JLabel("Metric 2 = Condition");
		lblNewLabel_4_1.setBounds(25, 507, 145, 14);
		contentPane.add(lblNewLabel_4_1);
		
		// Instantiates/initialises a new label
		
		JLabel lblNewLabel_3_1 = new JLabel("Audiobook:");
		lblNewLabel_3_1.setBounds(230, 474, 107, 14);
		contentPane.add(lblNewLabel_3_1);
		
		// Instantiates/initialises a new label
		
		JLabel lblNewLabel_4_2 = new JLabel("Metric 1 = Length");
		lblNewLabel_4_2.setBounds(230, 494, 195, 14);
		contentPane.add(lblNewLabel_4_2);
		
		// Instantiates/initialises a new label
		
		JLabel lblNewLabel_4_1_1 = new JLabel("Metric 2 = Format");
		lblNewLabel_4_1_1.setBounds(230, 507, 145, 14);
		contentPane.add(lblNewLabel_4_1_1);
		
		// Instantiates/initialises a new label
		
		JLabel lblNewLabel_3_2 = new JLabel("eBook:");
		lblNewLabel_3_2.setBounds(413, 474, 101, 14);
		contentPane.add(lblNewLabel_3_2);
		
		// Instantiates/initialises a new label
		
		JLabel lblNewLabel_4_3 = new JLabel("Metric 1 = Number of pages");
		lblNewLabel_4_3.setBounds(413, 494, 212, 14);
		contentPane.add(lblNewLabel_4_3);
		
		// Instantiates/initialises a new label
		
		JLabel lblNewLabel_4_1_2 = new JLabel("Metric 2 = Format");
		lblNewLabel_4_1_2.setBounds(413, 507, 145, 14);
		contentPane.add(lblNewLabel_4_1_2);
		
		// Instantiates/initialises a new label
		
		textField_2 = new JTextField();
		textField_2.setBounds(104, 561, 189, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		// Initialises the genre textbox which the customer will write into to choose a new book, this texbox is then placed onto the panel so it can be used
		
		JLabel lblNewLabel_5 = new JLabel("Genre Filter");
		lblNewLabel_5.setBounds(25, 564, 86, 14);
		contentPane.add(lblNewLabel_5);
		
		// Instantiates/initialises a new label
		
		JButton btnNewButton_3 = new JButton("Search");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String genre = textField_2.getText();
				try {
					ArrayList<String> FilteredList = genreFilter(genre);
					// Instantiates and initialises the new list of books to be displayed by calling the function used to filter them
					FilteredList.add(0,"ISBN, Type, Title, Language, Genre, Release Date, Price, Quantity, Metric 1, Metric 2");
					// Adds the Title row so the user can see what each field represents
					list.setListData(FilteredList.toArray());
					// Updates the data being used by the listbox
					list.updateUI();
					// Updates the listbox so it shows the new data
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}
		});
		btnNewButton_3.setBounds(318, 560, 101, 23);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Reset Filter");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				list.setListData(bookList.toArray());
				// Updates the data being used by the listbox
				list.updateUI();
				// Updates the listbox so it shows the new data
			}
		});
		btnNewButton_4.setBounds(451, 560, 107, 23);
		contentPane.add(btnNewButton_4);
			
	}
	
	public ArrayList<String> genreFilter(String genre) throws FileNotFoundException {
		
			ArrayList<String> filteredBooks = new ArrayList<>();
			// Instantiates an ArrayList that will hold all books that are of the genre specified by the customer
			Book getBooks = new Book();
			// Instantiates a new object 'getBooks' of the class 'Book'
			ArrayList<String> allBooks = getBooks.getAllBooks();
			// Instantiates and initialises an ArrayList used to hold all books in Stock.txt
		
			for (int i = 0; i < allBooks.size(); i++) {
				String[] comparedBook = allBooks.get(i).split(",");
				if ((comparedBook[4].trim()).equals(genre.trim())) {
					filteredBooks.add(allBooks.get(i));
					
					// Loops through every book in Stock.txt and if any book is of a genre that matches the filter query, it's added to the filtered list
				}
			}
			return filteredBooks;
			// Returns the new list of all books with a matching genre to the one specified by the customer
	}
}
