package comp9103;

import java.util.ArrayList;

/** 
 * 	This class is used to orderly save all contact information
 *  @author Zachary Li
*/
public class ContactList {
	private ArrayList<Contact> contactPerson;
	
	/** 
	 *   Build a constructor to save information 
	*/ 
	public ContactList() {
		contactPerson = new ArrayList<Contact>();
	}
	
	/** 
	 *   Build a method to get contactPerson
	 *   @return contactPerson contactPerson is instance in contactPerson
	*/ 
	public ArrayList<Contact> getContactList(){
		return contactPerson;
	}
}
