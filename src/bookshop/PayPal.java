package bookshop;
import java.io.FileNotFoundException;
import java.util.*;

public class PayPal extends Payment{

	private String emailAddress;
	private ArrayList<String> currentBasket;
	private String username;
	
	public PayPal(ArrayList<String> currentBasket, String username) {
		
		super(currentBasket);
		this.currentBasket = currentBasket;
		this.username = username;
		
		// Initialises the fields currentBasket and username which have been passed into the constructor as parameters
	}
	
	public void logActivity(String status) throws FileNotFoundException {
		
		ActivityLogger newActivity = new ActivityLogger(currentBasket, username, status);
		// Instantiates an object representing a new activity with the relevant information passed into it
		newActivity.assembleRow("PayPal");
		// Calls the function which will write this new activity to ActivityLog.txt with the corresponding payment method passed into it
	}
}

