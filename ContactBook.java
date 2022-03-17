package comp9103;

import java.util.*;
import java.io.*;

/** 
 * 	This class is used to identify instructions and run instructions
 *  @author Zachary Li
*/
public class ContactBook {
	private String instruction; 
	private ContactList contactPerson;
	private String contactInfo;
	
	final String instAdd = "add";
	final String instDelete = "delete";
	final String instQuery = "query";
	final String instSave = "save";

	/** 
	 *   Build a constructor to run command from .txt document
	 *   @param command command is command from the instruction document
	 *   @param contactTarget contactTarget is a instance from ContactList
	 *   @param contactInforma contactInforma is used to provide file path. Such as result file and report file
	*/ 
	public ContactBook(String command, ContactList contactTarget, String contactInforma) {
		instruction = command;
		contactPerson = contactTarget;
		contactInfo = contactInforma;
		
		String[] commInfo = instruction.split(" ");
		
		if(commInfo[0].equalsIgnoreCase(instAdd)) {
			addContact(); 
		} 
		else if(commInfo[0].equalsIgnoreCase(instDelete)) {
			removeContact();
		}
		else if(commInfo[0].equalsIgnoreCase(instQuery)) {
			queryContact();
		}
		else if(commInfo[0].equalsIgnoreCase(instSave)) {
			saveContact();
		}
	}
	
	/** 
	 *   Run add command 
	*/ 
	private void addContact() {
		String commContent = instruction.substring(instAdd.length() + 1);
		Contact addInfo = new Contact(commContent);
		//Reference: https://www.geeksforgeeks.org/return-keyword-java/
		if(addInfo.getValidation() == false) {
			return ;     //if content is illegal, break
		}
		
		//check if the name and birthday exist
		int indexNum = IfAlreadyExist(addInfo);
		//If the instance is already in the array list, first remove it, then add the new instance  into the array list.
		if(indexNum != -1) {
			//update other information
			if(addInfo.getPhone() != null) {
				contactPerson.getContactList().get(indexNum).setPhone(addInfo.getPhone());
			}
			if(addInfo.getAddress() != null) {
				contactPerson.getContactList().get(indexNum).setAddress(addInfo.getAddress());
			}
			if(addInfo.getEmail() != null) {
				contactPerson.getContactList().get(indexNum).setEmail(addInfo.getEmail());
			}
		}
		else {
			contactPerson.getContactList().add(addInfo);
		}
		return ;
		
	}
	
	/** 
	 *   Run delete command 
	*/ 
	private void removeContact() {
		String commContent = instruction.substring(instDelete.length() + 1);
		String[] deleteInfo = commContent.split(";");
		
		//Delete records from list by name
		if(deleteInfo.length == 1) {
			int index = GetTargetIndex(deleteInfo[0]);
			//find the index
			while(index != -1) {
				contactPerson.getContactList().remove(index);
				index = GetTargetIndex(deleteInfo[0]);
			}
		}
		//Delete records from list by name and birthday
		else if(deleteInfo.length == 2) {
			Birthday sameDate = new Birthday(deleteInfo[1]);
			//check if birthday invalid  
			if(sameDate.getDate() == null) {
				return ;
			}
			
			int index = GetTargetIndex(deleteInfo[0],sameDate);
			//find the index
			while(index != -1) {
				contactPerson.getContactList().remove(index);
				index = GetTargetIndex(deleteInfo[0],sameDate);
			}
		}
		return ;
	}
	
	/** 
	 *   Run query command 
	*/ 
	private void queryContact() {
		String commContent = instruction.substring(instQuery.length() + 1);
		//to save query information
		ArrayList<Contact> reportInfo = new ArrayList<Contact>();
		String[] queryInfo = commContent.split(" ");
		//query by name
		if(queryInfo[0].equalsIgnoreCase(Contact.flag_Name)) {
			String queryStr = commContent.substring(Contact.flag_Name.length() + 1);	
			for(Contact c : contactPerson.getContactList()) {
				if(c.getName().equalsIgnoreCase(queryStr)) {
					reportInfo.add(c);
				}
			}
		}
		//query by birthday
		else if(queryInfo[0].equalsIgnoreCase(Contact.flag_Birthday)) {
			String queryStr = commContent.substring(Contact.flag_Birthday.length() + 1);
			Birthday tempInfo = new Birthday(queryStr);
			//check if date is valid
			if(tempInfo.getDate() == null) {
				return ;
			}
			for(Contact c: contactPerson.getContactList()) {
				if(c.getBitrhday().getValue() == tempInfo.getValue()) {
					reportInfo.add(c);
				}
			}
		}
		//query by Phone
		else if(queryInfo[0].equalsIgnoreCase(Contact.flag_Phone)) {
			String queryStr = commContent.substring(Contact.flag_Phone.length() + 1);
			
			for(Contact c: contactPerson.getContactList()) {
				if(c.getPhone() != null && c.getPhone().equalsIgnoreCase(queryStr)) {
					reportInfo.add(c);
				}
			}
		}
		
		sortInfo(reportInfo);
		
		//The format of contactInforma is "outputFileName + ";" + reportFileName"
		String[] filePath = contactInfo.split(";");
		String reportFilePath = filePath[1];
		try {
			FileWriter writePath = new FileWriter(reportFilePath, true);
			PrintWriter writeInfo = new PrintWriter(writePath);
			
			writeInfo.printf("====== %s ======\n", instruction);
			for(Contact c : reportInfo) {
				writeInfo.printf("%s\n", c.toString());
			}
			writeInfo.printf("====== %s%s ======\n\n", "end of ", instruction);
			writeInfo.close();
		}catch (Exception e) {
			System.out.println("Error: "+ e.getMessage()); 
		}
		return ;
	}
	
	/** 
	 *   Run save command 
	*/ 
	private void saveContact() {
		String[] SaveFilePath = contactInfo.split(";");
		String resultFilePath = SaveFilePath[0];
		int index = 1;
		try {
			FileWriter writePath = new FileWriter(resultFilePath);
			PrintWriter writeInfo = new PrintWriter(writePath);
			
			for(Contact c : contactPerson.getContactList()) {
				writeInfo.printf("%s", c.toString());
				if(index != contactPerson.getContactList().size()) {
					writeInfo.printf("\n");
				}
				index++;
			}
			writeInfo.close();
		}catch (Exception e) {
			System.out.println("Error: "+e.getMessage()); 
		}
		return ;
	}
	
	/** 
	 *   In ascending order sort by name, and then sort by birthday
	*/ 
	private void sortInfo(ArrayList<Contact> infoSort) {
		for(int i = 0; i < infoSort.size(); i++) {
			for(int j = 0; j < infoSort.size() - i - 1; j++) {
				if(compareContact(infoSort.get(j), infoSort.get(j + 1))) {
					Contact tmp = null;
					tmp = infoSort.get(j + 1);
					infoSort.set(j + 1, infoSort.get(j));
					infoSort.set(j, tmp);
				}
			}
		}
		return ;
	}
	
	/** 
	 *   check if instance_1 bigger than instance_2, first check by name, then check by birthday
	 *   That is because it has order in report.txt
	 *   @param cont_1, cont_1 is instance in ContactList
	 *   @param cont_2, cont_2 is instance in ContactList
	 *   @return if cont_1 bigger than cont_2 in ascending order return true, otherwise return false
	*/ 
	private boolean compareContact(Contact cont_1, Contact cont_2) {
		if(cont_1.getName().compareToIgnoreCase(cont_2.getName()) > 0) {
			return true;
		}
		if(cont_1.getName().compareToIgnoreCase(cont_2.getName()) == 0) {
			if(cont_1.getBitrhday().getValue() > cont_2.getBitrhday().getValue()) {
				return true;
			}
		}
		return false;
	}

	/** 
	 *   check if instance is already in the ContactList
	 *   @param conInstance, conInstance is instance in ContactList
	 *   @return if instance is already in the ContactList, return its index, otherwise return -1
	*/ 
	private int IfAlreadyExist(Contact conInstance)
	{
	    return GetTargetIndex(conInstance.getName(), conInstance.getBitrhday());
	}

	/** 
	 *   Find the index of a instance in the ContactList by matching name
	 *   @param strName, strName is a instance's name
	 *   @return if matching name successfully, return index, otherwise return -1
	*/ 
	private int GetTargetIndex(String strName)
    {
        for (int i = 0; i < contactPerson.getContactList().size();  i++)
        {
            if (contactPerson.getContactList().get(i).getName().equalsIgnoreCase(strName))
            {
                return i;
            }
        }      
        return -1;
    }
	
	/** 
	 *   Find the index of a instance in the ContactList by matching name and birthday
	 *   @param strName, strName is a instance's name
	 *   @param strDate, strDate is a instance's birthday
	 *   @return if matching name and birthday successfully, return index, otherwise return -1
	*/ 
	private int GetTargetIndex(String strName, Birthday strDate)
    {
        for (int i = 0; i < contactPerson.getContactList().size(); i++)
        {
            if (contactPerson.getContactList().get(i).getName().equalsIgnoreCase(strName) &&
            		contactPerson.getContactList().get(i).getBitrhday().getValue() == strDate.getValue())
            {
                return i;
            }
        }
        return -1;
    }
}




