package bookshop;
import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;  

public class ActivityLogger {

	private ArrayList<String> addedBooks;
	private String username;
	private int userID;
	private String postcode;
	private String ISBN;
	private double price;
	private int quantity;
	private String status;
	private String paymentType;
	private String dateStr;
	
	public ActivityLogger(ArrayList<String> addedBooks, String username, String status) {
		
		this.addedBooks = addedBooks;
		System.out.println(addedBooks);
		this.username = username;
		this.status = status;			

		// Initialises the list of books in the basket, the relevant username, and the type of activity taking place
	}
	
	
	public void assembleRow(String paymentType) throws FileNotFoundException {
		
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");  
        Date date = new Date();  // Instantiates an object representing the current date
        
		User getUser = new User();  // Instantiates a new user object 
		String[] userDetails = getUser.viewUser(username); // Retrieves all information relating to the corresponding user
		
		for (int i = 0; i < addedBooks.size(); i++) {
			
			// Loops through every book in the basket
			
			String currentBook = addedBooks.get(i);
			String[] currentBookArr = currentBook.split(",");
			
			// Retrieves an individual book in the basket and splits it into an array
			
			userID = Integer.parseInt(userDetails[0]);
			postcode = userDetails[4].trim();
			ISBN = currentBookArr[0].trim();
			price = Double.parseDouble(currentBookArr[6].trim());
			quantity = Integer.parseInt(currentBookArr[7].trim());
			this.paymentType = paymentType;
			dateStr = (String) formatter.format(date); // Sets the date field to be the current date in the format outlined earlier
			
			// Initialises all the relevant fields for the activity by retrieving information from both the UserAccounts and Stock files
			
			try {
				writeActivity(); // Calls the function that will write the activity to the ActivityLog file
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	public void writeActivity() throws IOException {
		

			String bookRow = userID+", "+postcode+", "+ISBN+", "+price+", "+quantity+", "+status+", "+paymentType+", "+dateStr+"\n";
			
			// Combines all the relevant private fields into one string which will be appended to the end of ActivityLog
			
			FileOutputStream outputStream = new FileOutputStream("ActivityLog.txt", true);
			// Opens the file to be written to
			byte[] bufferStrByte = bookRow.getBytes();
			// Converts the string to be written to the file into bytes so it can be appended to the end of it
			System.out.println(bookRow);
			outputStream.write(bufferStrByte); 
			// Adds the new activity to ActivityLog

	}
	

}
