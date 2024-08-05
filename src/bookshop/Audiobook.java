package bookshop;

import java.io.FileNotFoundException;

public class Audiobook extends Book{

	private int listenLen;
	private String Format;
	
	public String[] viewBook(String ISBN) throws FileNotFoundException {
		
		// The function takes a unique ISBN as a parameter to identify the book that is being targeted
		
		String[] bookDetails = new String[10];
		// Creates a new array which will hold 10 separate elements representing the fields of a book
		
		bookDetails = super.viewBook(ISBN);
		// Calls the superclass version of viewBook which retrieves the specific book from the Stock file
		listenLen = Integer.parseInt(bookDetails[8]);
		Format = bookDetails[9];
		// Initialises the listenLen and Format fields based on the elements of the bookDetails array
		
		return(bookDetails);
	}
	
	
	
	
	
	
}
