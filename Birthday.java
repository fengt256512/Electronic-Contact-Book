package comp9103;

import java.util.*; 
import java.text.*;

/** 
 * 	This class is used to get time and convert it to standard mode 
 *  @author Zachary Li
*/
public class Birthday {
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy"); 
	private String strData;
	private Date date;
	
	/** 
	 *   Process entered date
	 *   @param  dataInput dataInput is the date come from input
	*/ 
	public Birthday(String dataInput) {
		strData = dataInput; 
		String[] temp;
		if (strData.matches("\\d+\\D\\d+\\D\\d+")) { 
			temp = strData.split("\\D"); 
			if (temp.length == 3) { 
				for (int i = 0; i < 2; ++i) { 
					if (temp[i].length() < 2) {
						temp[i] = "0" + temp[i];
					}
				}
				strData = temp[0] + "-" + temp[1] + "-" + temp[2];
			}
		}
		try {
			date = dateFormat.parse(strData); 
		} catch (Exception e) { 
			date = null;
		}
	}
	
	/** 
	 *   Build a method to check if date exists
	 *   @return true if date exists
	*/ 
	public boolean isValid() { 
		if(date != null){ 
			return true;   
		}
		else{ 
			return false;
		}
	}
	
	/** 
	 *   Build a method to check if the date is legal and return value
	 *   @return date if the data is legal
	 *   Reference: https://docs.oracle.com/javase/8/docs/api/java/util/Date.html
	*/ 
	public Date getDate() { 
		if(isLegalDate(strData) == false) {
			return null;
		}
		return date;
	}
	
	/** 
	 *   Get date in String format
	 *   @return strData strData is the date from input
	*/ 
	public String getstrData() {
		return strData;
	}
	
	/** 
	 *   Get value for comparison or sorting
	 *   @return date.getTime() date.getTime() the value of time
	*/ 
	public long getValue() {
		return date.getTime();
	}
	
	/** 
	 *   Check if the date format is legal
	 *   @return true if date is legal
	 *   Reference: http://tutorials.jenkov.com/java-date-time/parsing-formatting-dates.html
	*/ 
	private boolean isLegalDate(String strDate) {
		dateFormat.setLenient(false);
		return dateFormat.parse(strDate, new ParsePosition(0)) != null;
	}
}
