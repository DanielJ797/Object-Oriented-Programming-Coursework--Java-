package bookshop;

import java.io.*;

public class Admin extends User {

	private String bufferStr = "\n";
	// Initialises a new line for a book to be written to the Stock file
	
	public void addBook(String[] newBook) throws IOException {
		
		
		for (int i = 0; i< newBook.length; i++) {
			if (i > 0) { bufferStr = bufferStr + ", ";}
			bufferStr = bufferStr + newBook[i];
		}
		
		/* Assembles the string which will be added to the end of Stock.txt by iterating through the array passed into addBook as a parameter and appending it
		to the current String bufferStr */
		
		FileOutputStream outputStream = new FileOutputStream("Stock.txt", true);
		byte[] bufferStrByte = bufferStr.getBytes();
		
		// Opens the Stock file and converts bufferStr into bytes so it can be written to the file
		
		outputStream.write(bufferStrByte);
		
		// the new book information is written to Stock.txt as an additional row
	}
		
	
	
	
	
	
}
