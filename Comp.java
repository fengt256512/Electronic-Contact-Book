package comp9103;

public class Comp { 
	public static void main(String[] args) {
		//Check if the number of parameter is right
		if(args.length == 4){
			FileProcessor test = new FileProcessor(args);
			test.processPhoneBook();
			test.processInstruction();
		}
	} 
} 
 