package bookshop;

import java.io.FileNotFoundException;

public class Paperback extends Book{

	private int pageNum;
	private String Condition;
	
	
	public String[] viewBook(String ISBN) throws FileNotFoundException {
		
		String[] bookDetails = new String[10];
		// Creates a new array which will hold 10 separate elements representing the fields of a book
		
		bookDetails = super.viewBook(ISBN);
		// Creates a new array which will hold 10 separate elements representing the fields of a book
		pageNum = Integer.parseInt(bookDetails[8]);
		Condition = bookDetails[9];
		// Initialises the pageNum and condition fields based on the elements of the bookDetails array
		
		return(bookDetails);	
	}
}
