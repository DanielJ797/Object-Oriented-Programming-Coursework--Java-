package bookshop;
import java.io.FileNotFoundException;
import java.util.*;

public class Customer extends User {

	private ArrayList<String> booksInBasket = new ArrayList<String>();
	
	public Customer(ArrayList<String> booksInBasket) {
	
		this.booksInBasket = booksInBasket;
	}
	
	public boolean checkValue(String entry) {

		try {
            int n = Integer.valueOf(entry) ;
            // Checks to see if the value entered is an integer, if so it returns true
            
        } catch(NumberFormatException e) {
        	return false;
        	// Otherwise, the function will return false
        }
		
		return true;
	}
	
	public boolean checkBasket(String ISBN) {
		
		for (int i = 0; i < booksInBasket.size(); i++) {
			if ((booksInBasket.get(i)).contains(ISBN)) {
				return true;
			}
		}
		/* Loops through all books in the basket, if one matches the ISBN of the chosen book
		then the function will return True and the addition will be denied */
		
		return false;
		// Otherwise, the function will return False and the book will be added
	}
	
	

}
