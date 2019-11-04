

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

	/**
	 * This class is the interactive program that  allows the user to search for restaurants and their information in San Francisco. 
	 * When the program is executed the name of the input file containing all of the restaurant information is provided as the program's single command line argument. The data in this file 
	 * serves as a database of all the named restaurants. 
	 * In the interactive part, the user enters a keyword name or zip code value. The program 
	 * responds by the list of restaurants that correlate to the keyword. 
	 * @author Claire Zhu
	 */

public class SFRestaurantData {
	
	/**
	 * The main() method of this program. 
	 * @param args array of Strings provided on the command line when the program is started. 
	 * The first String should be the name of the input CSV file containing the named restaurants. 
	 */
	public static void main(String[] args) {
	
		//validate that the command line exists
		if (args.length == 0) {
			System.out.println("ERROR: The program expects a file name as an argument.\n");
			System.exit(1);
		}
		
		//validate that there is an existing file contained by the command line
		File restaurantFile = new File(args[0]);
		if (!restaurantFile.exists()){
			System.err.println("ERROR: the file "+restaurantFile.getAbsolutePath()+" does not exist.\n");
			System.exit(1);
		}
		if ((!restaurantFile.canRead())) {
			System.out.println("ERROR: Cannot read file. Exiting program. \n");
			System.exit(1);
		}
		
		//open file to be read
		Scanner inRestaurant = null;
		
		try {
			inRestaurant = new Scanner(restaurantFile);
		}
		catch (FileNotFoundException e) {
			System.err.println("ERROR: the file "+restaurantFile.getAbsolutePath()+" cannot be opened for reading. \n");
			System.exit(1);
		}
		
		String line = null;
		
		RestaurantList restaurantList = new RestaurantList();
		 
		
		while (inRestaurant.hasNextLine()) {
			try {
				line = inRestaurant.nextLine();
				//read through each line of the file and save it in a list of named restaurants
				ArrayList<String> entries = splitCSVLine(line);
				
					
				String formattedDate = 	entries.get(11).split(" ")[0];
				if (formattedDate.indexOf("/") == 1) {
					formattedDate = "0" + formattedDate;
				}
				
				if (formattedDate.lastIndexOf("/") == 4) {
					formattedDate = formattedDate.substring(0,3) + "0" + formattedDate.substring(3);
				}
					
				if (entries.get(12).isEmpty()) {
						continue;
				}
					
				Inspection restaurantInspection = new Inspection(new Date(formattedDate), Integer.valueOf(entries.get(12)), entries.get(15),entries.size()==17? entries.get(16) : null);
				
				String name = entries.get(1);
				String zip = entries.get(5);
				boolean found = false;
				for (Restaurant r : restaurantList) {
					if (r.equals(new Restaurant(name, zip))) {
						r.addInspection(restaurantInspection);
						found = true;
						break;
					}
				}
				//create new Restaurant objects for each line, and use the appropriate constructor depending on what information is present in each line 
				if (!found) {
					if (entries.get(2).isEmpty() && entries.get(9).isEmpty()) {
						Restaurant r = new Restaurant(entries.get(1), entries.get(5));
						restaurantList.add(r);
						r.addInspection(restaurantInspection);
						
					}
					else {
						Restaurant r = new Restaurant(entries.get(1), entries.get(5), entries.get(2), entries.get(9)); 
						restaurantList.add(r);
						r.addInspection(restaurantInspection);
					}
				}
			}
			
			catch (NoSuchElementException exception1) {
			}
			
			catch (IllegalArgumentException exception2) {
			}
		}
		
		//interactive part; ask for user input
		Scanner input =  new Scanner(System.in);
		String userInput="";
		
		do {
			System.out.println("Search the database by matching keywords to titles or actor names.");
			System.out.println("    name KEYWORD");
			System.out.println("  To search for matching restaurant names, enter");
			System.out.println("    zip KEYWORD");
			System.out.println("  To finish the program, enter");
			System.out.println("    quit");
		
			userInput = input.nextLine();
			String[] userInputList = userInput.split(" ");
			
			if (userInputList[0].equalsIgnoreCase("quit")) {
				break;
			}
			if (!(userInputList[0].equalsIgnoreCase("name") || userInputList[0].equalsIgnoreCase("zip"))) {
				System.out.println("This is not a valid query. Try again.");
				continue; //is this correct 
			}
			else if (userInputList[0].equalsIgnoreCase("name")) {
				// System.out.println(restaurantList.size());
				if (restaurantList.getMatchingRestaurants(userInputList[1])==null) {
					System.out.println("No matches found. Try again.");
					continue;
				}

				for (Restaurant restaurant: restaurantList.getMatchingRestaurants(userInputList[1])) {
					System.out.println(restaurant.toString());
				}
				//System.out.println(restaurantList.toString());
			}
			else if (userInputList[0].equalsIgnoreCase("zip")) {
				if (restaurantList.getMatchingZip(userInputList[1])==null) {
					System.out.println("No matches found. Try again.");
					continue;
				}
				for (Restaurant restaurant: restaurantList.getMatchingZip(userInputList[1])) {
					System.out.println(restaurant.toString());
				}
			}
	
			
		} while (!userInput.trim().equalsIgnoreCase("quit"));
		
		System.exit(1);	
	}
	
	 /**
	 * Splits the given line of a CSV file according to commas and double quotes
	 * (double quotes are used to surround multi-word entries so that they may contain commas)
	 * @author Joanna Klukowska
	 * @param textLine a line of text to be passed
	 * @return an Arraylist object containing all individual entries found on that line
	 */
	 public static ArrayList<String> splitCSVLine(String textLine){
	
	 if (textLine == null ) return null;
	
	 ArrayList<String> entries = new ArrayList<String>();
	 int lineLength = textLine.length();
	 StringBuffer nextWord = new StringBuffer();
	 char nextChar;
	 boolean insideQuotes = false;
	 boolean insideEntry= false;
	
	 // iterate over all characters in the textLine
	 for (int i = 0; i < lineLength; i++) {
	 nextChar = textLine.charAt(i);
	
	 // handle smart quotes as well as regular quotes
	 if (nextChar == '"' || nextChar == '\u201C' || nextChar =='\u201D') {
	 // change insideQuotes flag when nextChar is a quote
	 if (insideQuotes) {
		 insideQuotes = false;
		 insideEntry = false;
	 }	
	 else {
		 insideQuotes = true;
		 insideEntry = true;
	 }
	 } else if (Character.isWhitespace(nextChar)) {
	 if ( insideQuotes || insideEntry ) {
	 // add it to the current entry
	 nextWord.append( nextChar );
	 }else { // skip all spaces between entries
	 continue;
	 }
	 } else if ( nextChar == ',') {
	 if (insideQuotes){ // comma inside an entry
	 nextWord.append(nextChar);
	 } else { // end of entry found
	 insideEntry = false;
	 entries.add(nextWord.toString());
	 nextWord = new StringBuffer();
	 }
	 } else {
	 // add all other characters to the nextWord
	 nextWord.append(nextChar);
	 insideEntry = true;
	 }
	
	 }
	 // add the last word ( assuming not empty )
	 // trim the white space before adding to the list
	 if (!nextWord.toString().equals("")) {
	 entries.add(nextWord.toString().trim());
	 }
	
	 return entries;
	 }


}
