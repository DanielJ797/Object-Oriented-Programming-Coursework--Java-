package bookshop;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class Payment {

	private static ArrayList<String> Basket;
	
	public Payment(ArrayList<String> Basket) {
		
		this.Basket = Basket;
	}
	
	
	public ArrayList<String> getBasket() {
		
		return(Basket);
		// returns the contents of the Basket ArrayList to the calling program
	}
	
	
	public void logActivity(String status, String username) throws FileNotFoundException {
		
		ActivityLogger newActivity = new ActivityLogger(Basket, username, status);
		// Takes the relevant username and optional field as parameters and Instantiates an Activity with them and the current Basket as parameters
		newActivity.assembleRow("");
		/* Calls the function which will write this new activity to ActivityLog.txt with the corresponding payment method passed into it, because the
		activity is cancelling an order: there is no payment method and a blank string is being passed */
	}
	
	public void updateStock(ArrayList<String> currentBasket) throws IOException {
		

		Book stockFile = new Book();
		// Instantiates a new object 'stockFile' of the class 'Book'
		ArrayList<String> Stock = stockFile.getAllBooks();
		// Instantiates and initialises an ArrayList used to hold all books in Stock.txt
		FileOutputStream outputStream = new FileOutputStream("Stock.txt");
		// Opens the Stock file and instantiates a Scanner object called outputStream which will be used to read it
		int j = 0;
		
		for (int i = 0; i < Stock.size()-currentBasket.size(); i++) {
			// Loops through every row in Stock.txt and updates the relevant ones by comparing them to the customer basket
			
			if (j < currentBasket.size()) {
				String[] currentBook = currentBasket.get(j).split(",");
				String[] comparedBook = Stock.get(i).split(",");
				// Splits the currrentBook and comparedBook rows into seperate arrays so specific fields inside of them can be compared
				
			
				if (currentBook[0].equals(comparedBook[0])) {
					// If the ISBN of the current and compared books are identical then the program will execute the following block. Otherwise, it will not
							
					currentBook[7] = String.valueOf(Integer.parseInt(comparedBook[7].trim())-Integer.parseInt(currentBook[7].trim()));
					// Evaluates the new quantity value for this book by subtracting the customer order from the current stock of this book
					String newBook = "";
					
					for (int m = 0; m < currentBook.length; m++) {
						// Loops through every field in the row and assembles the new row to be written to Stock.txt
						newBook = newBook + " " + currentBook[m].trim() + ",";
						// Appends each field to the previous ones
						}
					
					
					newBook = newBook.substring(1, newBook.length()-1);
					currentBasket.set(j, newBook);
					// Stores the new row in an ArrayList to be copied later
					Stock.remove(i);
					// removes the old row from the stock file so the new one can be added
					Stock.add(currentBasket.get(j)+"\n");
					// Adds the new book row to the file so it can be accessed by the customer
					j++;	
				}
			}
			
				Stock.set(i, Stock.get(i)+"\n");
				// Appends a newline character to the end of every book row so it can be stored correctly in Stock.txt
			
		}
		
 
	 outputStream.close();
	 // Closes the file so another one can be used
	 
	 FileOutputStream newFile = new FileOutputStream("Stock.txt",true);
	// Opens the Stock file and instantiates a Scanner object called newFile which will be used to read it
	 
	 for (int l = 0; l < Stock.size(); l++) {
		 // While there is still a next line to be written, the program writes every book row to Stock.txt to update its contents
		 byte[] bufferStrByte = (Stock.get(l)).getBytes();
		// Converts the string to be written to the file into bytes so it can be appended to the end of it
		 newFile.write(bufferStrByte);
		// Adds the new book row to Stock
	 } 
		newFile.close();
	}
	
	
}
