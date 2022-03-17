package comp9103;

import java.util.*;


/** 
 * 	This class is used to check if the input information is legal and adjust to the correct format
 *  @author Zachary Li
*/
public class Contact {
	// instance fields
	private String name;
	private Birthday birthday;
	private String phone;
	private String email;
	private String address;
	private boolean n_bIsValid;    //Check if contains both name and birthday
	
	final static String flag_Name = "name";
    final static String flag_Birthday = "birthday"; 
    final static String flag_Phone = "phone"; 
    final static String flag_Address = "address"; 
    final static String flag_Email = "email"; 
	
    
	/** 
	 *   Build a constructor to get the information from input
	 *   Check if the information is legal
	 *   @param  data data is the input information from .txt document
	*/ 
	public Contact(String data) {
		n_bIsValid = true;
		String[] temp = data.split(";");   //split instruction by';'

		for(int i = 0; i < temp.length; i++) {
			String[] strTemp = temp[i].trim().split(" ");   //split instruction by ' '
			String strFlag = strTemp[0];
			
			/*switch(strFlag) {
			case "name":
				name = temp[i].trim().substring(flag_Name.length() + 1).trim();   //Ignore operation characters
				if (hasVaildName(name) == false) {         //check if the format of information is legal
					n_bIsValid = false;
					break;
				}
			case "birthday":
				birthday = new Birthday(temp[i].trim().substring(flag_Birthday.length() + 1));
				if(hasVaildBirthday(birthday) == false) {
					n_bIsValid = false;
					break;
				}
			case "phone":
				phone = temp[i].trim().substring(flag_Phone.length() + 1).trim();
				if(hasVaildPhone(phone) == false) {
					phone = null;
				}
				//Reference: https://beginnersbook.com/2013/12/java-string-replace-replacefirst-replaceall-method-examples/
				//replace the first string to "" if it is illegal("0 or other char")
				if(phone != null) {
					phone = phone.replaceFirst("^0+(?!$)", "");   
				} 
				
			case "address":
				address = temp[i].trim().substring(flag_Address.length() + 1).trim();
				if(hasVaildAddress(address) == false) {
					address = null;
				}
			case "email":
				email = temp[i].trim().substring(flag_Email.length() + 1).trim();
				if(hasVaildEmail(email) == false) {
					email = null;
				}
			default:
				break;
			}*/
			
			if(strFlag.equalsIgnoreCase(flag_Name)) {      //Check if information is name
				name = temp[i].trim().substring(flag_Name.length() + 1).trim();   //Ignore operation characters
				if (hasVaildName(name) == false) {         //check if the format of information is legal
					n_bIsValid = false;
					break;
				}
			}
			else if(strFlag.equalsIgnoreCase(flag_Birthday)) {  
				birthday = new Birthday(temp[i].trim().substring(flag_Birthday.length() + 1));
				if(hasVaildBirthday(birthday) == false) {
					n_bIsValid = false;
					break;
				}
			}
			else if(strFlag.equalsIgnoreCase(flag_Phone)) {   
				phone = temp[i].trim().substring(flag_Phone.length() + 1).trim();
				if(hasVaildPhone(phone) == false) {
					phone = null;
				}
				//Reference: https://beginnersbook.com/2013/12/java-string-replace-replacefirst-replaceall-method-examples/
				//replace the first string to "" if it is illegal("0 or other char")
				if(phone != null) {
					phone = phone.replaceFirst("^0+(?!$)", "");   
				} 
			}
			else if(strFlag.equalsIgnoreCase(flag_Address)) {
				address = temp[i].trim().substring(flag_Address.length() + 1).trim();
				if(hasVaildAddress(address) == false) {
					address = null;
				}
			}
			else if(strFlag.equalsIgnoreCase(flag_Email)) {
				email = temp[i].trim().substring(flag_Email.length() + 1).trim();
				if(hasVaildEmail(email) == false) {
					email = null;
				}
			}
		}

		if (name == null || birthday == null){
			n_bIsValid = false;
        }
	}
	
	
	/** 
	 *   Check if the entered name is legal
	 *   @param  name name is a string come from .txt document
	 *   @return false if name is illegal
	 *   Reference: https://www.vogella.com/tutorials/JavaRegularExpressions/article.html
	*/ 
	public boolean hasVaildName(String name) {
		if (name.matches("^[a-zA-Z ,.'-]+$") == false) {
			return false;
		}
		else return true;
	}
	
	/** 
	 *   Check if the entered birthday is legal
	 *   @param  birthday birthday is a string come from .txt document
	 *   @return false if birthday is illegal
	 *   Reference: https://www.geeksforgeeks.org/date-after-method-in-java/
	 *   Reference: https://www.tutorialspoint.com/java/java_date_time.htm
	 *   Reference: https://www.vogella.com/tutorials/JavaRegularExpressions/article.html
	*/ 
	public boolean hasVaildBirthday(Birthday birthday) {
		if(birthday.isValid() == false || birthday.getDate().after(new Date())) {
			return false;
		}
		else return true;
	}
	
	/** 
	 *   Check if the entered phone is legal
	 *   @param  phone phone is a string come from .txt document
	 *   @return false if phone is illegal
	 *   Reference: https://www.vogella.com/tutorials/JavaRegularExpressions/article.html
	*/ 
	public boolean hasVaildPhone(String phone) {
		if(phone.matches("^[0-9 +-]+$") == false) {
			return false;
		}
		else return true;
	}
	
	/** 
	 *   Check if the entered address is legal
	 *   @param  address address is a string come from .txt document
	 *   @return false if address is illegal
	 *   Reference: https://www.vogella.com/tutorials/JavaRegularExpressions/article.html
	*/ 
	public boolean hasVaildAddress(String address) {
		if(address.matches(".*[0-9]{4}$") == false) {
			return false;
		}
		else return true;
		}
	
	/** 
	 *   Check if the entered email is legal
	 *   @param  email email is a string come from .txt document
	 *   @return false if email is illegal
	 *   Reference: https://www.vogella.com/tutorials/JavaRegularExpressions/article.html
	*/ 
	public boolean hasVaildEmail(String email) {
		if(email.matches("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$") == false) {
			return false;
		}
		else return true;
		}
	
	/** 
	 *   Put all the contact information together and return
	 *   @return index_Info index_Info is all information for one person
	*/ 
	public String toString() {
		
		String index_Info = flag_Name + ": " + name + "\n" + flag_Birthday + ": "  + birthday.getstrData() + "\n";
		
		if(address != null) {
			index_Info += flag_Address + ": " + address + "\n";
		}
		if(phone != null) {
			index_Info += flag_Phone + ": " + phone + "\n";
		}
		if(email != null) {
			index_Info += flag_Email + ": " + email + "\n";
			
		}
		return index_Info;
	}
	
	/** 
	 *   @return return true if instance is legal; otherwise return false
	*/
	public boolean getValidation() {
		return n_bIsValid;
	}
	
	/** 
	 *   @return return instance's name
	*/
	public String getName() {
		return name;
	}
	
	/**
     * @param name set instance's name 
     */
	public void setName(String name) {
		this.name = name;
	}
	
	/** 
	 *   @return birthday return instance's birthday
	*/
	public Birthday getBitrhday() {
		return birthday;
	}
	
	/**
     * @param birthday set instance's birthday
     */
	public void setBirthday(Birthday birthday) {
		this.birthday = birthday;
	}
	
	/** 
	 *   @return phone return instance's phone
	*/
	public String getPhone() {
		return phone;
	}
	
	/**
     * @param phone set instance's phone
     */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	/** 
	 *   @return address return instance's address
	*/
	public String getAddress() {
		return address;
	}
	
	/**
     * @param address set instance's address
     */
	public void setAddress(String address) {
		this.address = address;
	}
	
	/** 
	 *   @return email return instance's email
	*/
	public String getEmail() {
		return email;
	}
	
	/**
     * @param email set instance's email
     */
	public void setEmail(String email) {
		this.email = email;
	}
}
