package comp9103;

import java.io.*;
import java.util.*; 

/** 
 * 	This class is used to control program and run the program
 *  @author Zachary Li
*/
public class FileProcessor {
	private File phoneBook;
	private File instruction;
	private File outputFile;
	private File reportFile;
	private ContactList contactInstance;
	
	/** 
	 *   Build a constructor to receive file name and run the program
	 *   @param s s is a array including file name, s[0] is contact file, s[1] is instruction file
	 *   											s[2] is report file, s[3] is result file
	*/ 
	public FileProcessor(String[] s) {
		phoneBook = new File(s[0]);
		instruction = new File(s[1]); 
		outputFile = new File(s[2]);
		reportFile = new File(s[3]);		
		contactInstance = new ContactList();
	}
	
	/** 
	 *   read information from contact.txt and convert it as add command, add information into contactList
	 *   @return if add information successfully, return 0, otherwise return exception
	*/ 
	public int processPhoneBook() {
		try {
			Scanner scan = new Scanner(phoneBook);
			String commArgu = "";
			while (scan.hasNextLine()) {
				String infoTmp = scan.nextLine();
				
				//check if it has the delimiter of a contact information
				if(infoTmp.length() == 0) {
					commArgu = "add " + commArgu;
					ContactBook conTmp = new ContactBook(commArgu, contactInstance, null);
					commArgu = "";
				}
				//check if this is informative string and check if information not end
				else {
					if(infoTmp.endsWith(", ")) {
						commArgu = commArgu + infoTmp.trim() + " ";
					}
					else commArgu = commArgu + infoTmp.trim() + ";";
				}
			}
			
			//check if the file not end with an empty line
			if(commArgu.length() != 0) {
				commArgu = "add " + commArgu;
				ContactBook conTmp = new ContactBook(commArgu, contactInstance, null);
			}
			scan.close();
		} catch (FileNotFoundException e) { 
			e.printStackTrace();
		}
		return 0;
	}
	
	/** 
	 *   read information from instruction.txt, identify and run instructions
	 *   @return if run information successfully, return 0, otherwise return exception
	*/ 
	public int processInstruction() {
		try {
			Scanner sscan = new Scanner(instruction);
			while(sscan.hasNextLine()) {
				ContactBook Tmp = new ContactBook(sscan.nextLine(), contactInstance, outputFile  + ";" +  reportFile);
			}
			sscan.close();
		} catch (FileNotFoundException e) { 
			e.printStackTrace();
		}
		return 0;
	}	
}