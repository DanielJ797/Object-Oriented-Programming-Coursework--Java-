package bookshop;

import java.io.FileNotFoundException;

public class eBook extends Book{

	private int pageNum;
	private  String Format;
	
	
	public String[] viewBook(String ISBN) throws FileNotFoundException {
		
		String[] bookDetails = new String[10];
		// Creates a new array which will hold 10 separate elements representing the fields of a book
		
		bookDetails = super.viewBook(ISBN);
		// Calls the superclass version of viewBook which retrieves the specific book from the Stock file
		pageNum = Integer.parseInt(bookDetails[8]);
		Format = bookDetails[9];
		// Initialises the pageNum and Format fields based on the elements of the bookDetails array
		
		return(bookDetails);
	}
}
