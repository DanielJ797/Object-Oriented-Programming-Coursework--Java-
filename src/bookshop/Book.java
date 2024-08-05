package bookshop;

import java.io.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Book {

	private int ISBN;
	private String bookType;
	private String Title;
	private String Language;
	private String Genre;
	private String ReleaseD; //Change to date at some point
	private double Price;
	private int Quantity;
	
	public String[] viewBook(String ISBN) throws FileNotFoundException {
		
		Scanner lineScan = scanLines();
		// Instantiates a new object called lineScan used to read the lines of a file
		String[] bookDetails = new String[10];
		// Creates a new array which will hold 10 separate elements representing the fields of a book
		
		while (lineScan.hasNextLine()) {
			// Iterates through the Stock file while there is a next line available to read
			
			bookDetails = (lineScan.nextLine().trim()).split(",");
			// Initialises the bookDetails array by splitting the contents of the current line into separate elements
			
			if (bookDetails[0].equals(ISBN)) {
				// Checks whether the entered ISBN matches that of a specific line in the Stock file
				
				ISBN = bookDetails[0];
				bookType = bookDetails[1];
				Title = bookDetails[2];
				Language = bookDetails[3];
				Genre = bookDetails[4];
				ReleaseD = bookDetails[5];
				Price  = Double.parseDouble(bookDetails[6]);
				Quantity = Integer.parseInt(bookDetails[7].trim());
				
				// If the ISBN matches, the field values related to the specific book are all initialised
				break;
			}
		}
		
		return(bookDetails);
		// Returns the array containing the corresponding book
	}
	
	public ArrayList<String> getAllBooks() throws FileNotFoundException {
		
		ArrayList<String> bookList = new ArrayList<String>();
		Scanner lineScan = scanLines();
		// Instantiates an ArrayList that will hold every line in Stock.txt as elements alonside a scanner to read the file
		
		while (lineScan.hasNextLine()) {
			// Iterates through the Stock file while there is still a next line to be read
			
			bookList.add(lineScan.nextLine());
			// Adds each line as a new element to the ArrayList bookList
		}
		
		return bookList;
		// Returns the entire file in the form of an ArrayList
	}

	public Scanner scanLines() throws FileNotFoundException {
		
		File bookFile = new File("Stock.txt");
		Scanner lineScan = new Scanner(bookFile);
		return lineScan;
		// Opens the stock file, instantiates a scanner which will read it and returns the scanner to be used
	}




}
