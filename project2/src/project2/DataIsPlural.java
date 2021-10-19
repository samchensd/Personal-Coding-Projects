package project2;
import java.net.URL;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Main program that interacts with the given CSV file
 * Allows the user to search through the data in CSV by up to two conditions (title, description, URL)
 * Responsible for opening and reading the data file, 
 * obtaining user input, performing some data validation and handling all errors that may occur 
 * (in particular, it should handle any exceptions thrown by your other classes and terminate gracefully,
 *  if need be, with a friendly error message presented to the user.
 * @author SamChen
 *
 */
public class DataIsPlural {
	
	/**
	 * The main() method of this program. 
	 * @param args CSV file in (date, position, headline, text, links, and hattips format)
	 */
	public static void main(String[] args) {
		
		//check if filename is provided through command line
		if (args.length == 0) {
			System.err.println("Usage Error: the program expects file name as an argument.\n");
			System.exit(1);
		}
		
		//Check if CSV file is valid and readable
		File csvFile = new File(args[0]); 
		if (!csvFile.exists()){
			System.err.println("Error: the file "+csvFile.getAbsolutePath()+" does not exist.\n");
			System.exit(1);
		}
		if (!csvFile.canRead()){
			System.err.println("Error: the file "+csvFile.getAbsolutePath()+
											" cannot be opened for reading.\n");
			System.exit(1);
		}
		
		DataSetList dataSetList = new DataSetList();
		try {
			//read CSV file
			CSV inCSV = new CSV (new Scanner(csvFile) );
			inCSV.getNextRow();
			
			//create a DataSetList that contains entries from CSV file
			for (int i=1;i<inCSV.getNumOfRows();i++) {
				ArrayList <String> temp = inCSV.getNextRow();
				ArrayList <URL> tempURL = new ArrayList<>();
				String tempString = temp.get(4);
				String [] URLList = tempString.split("\n");
				
				for (int b=0;b<URLList.length;b++) {
					tempURL.add(new URL(URLList[b]));
					}
				
				DataSet tempSet = new DataSet(temp.get(2),temp.get(3),tempURL);
				tempString = temp.get(0);
				String[] tempDate = tempString.split("\\.");
				Date thisDate = new Date (Integer.valueOf(tempDate[0]),Integer.valueOf(tempDate[1]),Integer.valueOf(tempDate[2]));	
				tempSet.setDate(thisDate);
				dataSetList.add(tempSet);				
				}
			}
		 catch (FileNotFoundException e) {
			System.err.println("Error: the file "+csvFile.getAbsolutePath()+
											" cannot be opened for reading.\n");
			System.exit(1);
		} catch (Exception e) {
			System.out.println("Exception caught");
		}
		
		//interactive mode: 
		Scanner userInput  = new Scanner (System.in ); 
		String userValue = "";
		boolean ifQuit = true;
		System.out.println("Welcome the Data Is Plural data explorer!\n");
		System.out.println("You can use the following queries to search through the data:\n"
				+ "   title KEYWORD\n"
				+ "   description KEYWORD\n"
				+ "   url KEYWORD\n"
				+ "You can combine up to two queries to narrow down the results, for example:\n"
				+ "   title KEYWORD1  url KEYWORD2\n"
				+ "");
		
		do {
			System.out.println("Enter query or \"quit\" to stop:");
			//get value of from the user 
			userValue = userInput.nextLine();
			
			//check whether user wants to quit
			if(userValue.equals("quit")) {
				ifQuit = false;
			}else {
				
				//search based on first condition
				String [] inputType = userValue.split(" ");
				DataSetList temp = null;
				if (inputType.length==2||inputType.length==4) {
					if (inputType[0].equalsIgnoreCase("title")) {
						temp = dataSetList.getByTitle(inputType[1]);
					}else if (inputType[0].equalsIgnoreCase("description")) {
						temp=dataSetList.getByDescription(inputType[1]);
					}else if (inputType[0].equalsIgnoreCase("url")) {
						temp=dataSetList.getByURL(inputType[1]);
					}else {
						System.out.println("This is not a valid query. Try again.");
					}
				}
				
				//Search based on second condition if it exists
				if (inputType.length==4) {
					if (inputType[2].equalsIgnoreCase("title")) {
						temp = temp.getByTitle(inputType[3]);
					}else if (inputType[2].equalsIgnoreCase("description")) {
						temp=temp.getByDescription(inputType[3]);
					}else if (inputType[2].equalsIgnoreCase("url")) {
						temp=temp.getByURL(inputType[3]);
					}else {
						System.out.println("This is not a valid query. Try again.");
						}
					}
				//Return error message if input is invalid
				if (inputType.length!=2 && inputType.length!=4) {
					System.out.println("This is not a valid query. Try again.");
				}else if(temp == null){
					System.out.println("No matches found. Try again.");
				}else {
					//return result list of matches
					for (DataSet c : temp) {
						System.out.println(c.toString());
						System.out.print("----- \n");
						}
					}
				}
			}while(ifQuit);
		}
	}