package bookshop;

import java.util.ArrayList;
import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import java.io.*;

public class User {

	private static int userID;
	private static String username;
	private static String surname;
	private static int houseNum;
	private static String postcode;
	private static String city;
	private static String userType;
	
	
	
	public String[] viewUser(String username) throws FileNotFoundException {
		
			File userFile = new File("UserAccounts.txt");
			Scanner lineScan = new Scanner(userFile);
			// Opens the UserAccounts file and instantiates a Scanner object called lineScane which will be used to read it
			String[] userDetails = new String[7];
			// Creates a string which will be used to store the corresponding information about the relevant user
			boolean validCheck = false;
			
			
			while (lineScan.hasNextLine()) {
				// Iterates through the User file while there is still a next line to be read
				
				userDetails = (lineScan.nextLine().trim()).split(",");
				// Initialises the userDetails array with segmented version of the current line
				
				if ((userDetails[1].trim()).equals(username)) {
					// Checks if the username corresponding to the current line matches that of the one passed into this function
				
					userID = Integer.parseInt(userDetails[0]);
					username = userDetails[1];
					surname = userDetails[2];
					houseNum = Integer.parseInt(userDetails[3].trim());
					postcode = userDetails[4];
					city = userDetails[5];
					userType = userDetails[6];
					validCheck = true;
					// If so, all the relevant fields will be initialised with the values of this line
					break;
				}
			}
			
			
			if (validCheck == false) { userDetails[6] = ""; }
			return(userDetails);
			// Returns the line containing data about the selected user
	}
	
	public ArrayList<String> getAllUsers() throws FileNotFoundException {
		
		ArrayList<String> userList = new ArrayList<String>();
		Scanner lineScan = scanLines();
		// Instantiates an ArrayList that will hold every line in Stock.txt as elements alonside a scanner to read the file
		
		while (lineScan.hasNextLine()) {
			// Iterates through the Stock file while there is still a next line to be read
			
			userList.add(lineScan.nextLine());
			// Adds each line as a new element to the ArrayList bookList
		}
		
		userList.add(0,"user ID, username, surname, house number, postcode, city, role");
		return userList;
		// Returns the entire file in the form of an ArrayList
	}
	
	
	public Scanner scanLines() throws FileNotFoundException {
		
		File userFile = new File("UserAccounts.txt");
		Scanner lineScan = new Scanner(userFile);
		return lineScan;
		// Opens the stock file, instantiates a scanner which will read it and returns the scanner to be used
	}
	
	
	
	
	
	
	
	
	
	
}
