

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The Restaurant class is used to represent a restaurant. 
 * This class stores the String values for the business's name, zip code, address, phone number, and an ArrayList of Inspection objects. 
 * @author Claire Zhu
 */

public class Restaurant implements Comparable<Restaurant> {
	private String name;
	private String address;
	private String phone;
	private String zip;
	private ArrayList<Inspection> listOfInspections;
	
	/**
	 * Constructs a new Restaurant object with specified restaurant name and zip code value. 
	 * @param String name value to indicate the name of this Restaurant; should not be an empty string.  
	 * @param String zip code value of this Restaurant; should be exactly 5 digits.
	 * @throws IllegalArgumentException if name or zip code parameters are invalid. 
	 */
	public Restaurant(String name, String zip) throws IllegalArgumentException{
		//validate if the name is not empty
		if (name==null || name.isEmpty()) {
			throw new IllegalArgumentException("Invalid name input. Name cannot be empty.");
		}
		//validate if the zip code is exactly 5 digits 
		if (!(zip.length()==5)) {
			throw new IllegalArgumentException("Invalid zip code input. Zip code must be exactly 5 digits.");
		}
		for (int i=0;i<zip.length();i++) {
			if (!(Character.isDigit(zip.charAt(i)))) {
				throw new IllegalArgumentException("Invalid zip code input. Zip code must only contain numerical digits.");
			}
		}
		this.name=name;
		this.zip=zip;
		this.listOfInspections = new ArrayList<Inspection>();
	}
	
	/**
	 * Constructs a new Restaurant object with specified Restaurant name, zip code value, address, and phone number. 
	 * @param String name value to indicate the name of this Restaurant; should not be an empty string.  
	 * @param String zip code value of this Restaurant; should be exactly 5 digits.
	 * @param String address value of this Restaurant; can be any non-empty string to indicate the address, null or an empty string when the data is not available.
	 * @param String phone number value of this Restaurant; can any non-empty string to indicate the address, null or an empty string when the data is not available.
	 * @throws IllegalArgumentException if name or zip code parameters are invalid.
	 */
	public Restaurant(String name, String zip, String address, String phone) {
		if (name==null || name.isEmpty()) {
			throw new IllegalArgumentException("Invalid name input. Name cannot be empty.");
		}
		if (!(zip.length()==5)) {
			throw new IllegalArgumentException("Invalid zip code input. Zip code must be exactly 5 digits.");
		}
		for (int i=0;i<zip.length();i++) {
			if (!(Character.isDigit(zip.charAt(i)))) {
				throw new IllegalArgumentException("Invalid zip code input. Zip code must only contain numerical digits.");
			}
		}
		this.name=name;
		this.zip=zip;
		this.address=address;
		this.phone=phone;
		this.listOfInspections = new ArrayList<Inspection>();
	}
	
	/**
	 * Adds a given inspection to the list of inspections for the current Restaurant object.
	 * @param Inspection object inspect
	 * @throws IllegalArgumentException if a null parameter is used
	 */
	public void addInspection(Inspection inspect) throws IllegalArgumentException {
		if (inspect==null) {
			throw new IllegalArgumentException("Inspection cannot be null.");
		}
		else {
			this.listOfInspections.add(inspect);
		}	
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Restaurant restaurant) {
		//this calls the Name class's compareTo method and compares the names of the two restaurants
		int i =this.name.compareToIgnoreCase(restaurant.getName());
		if (i!=0) {
			return i;
		}
		//compare the zip values using the same String method as above
		i =this.zip.compareTo(restaurant.getZip());
		return i;
	}
	
	/**
	 *
	 * @param Object obj 
	 * @return boolean true if this Restaurant object's name and zip code are the same as the parameter Restaurant object's
	 * false if both the name and zip codes are not equal
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Restaurant other = (Restaurant) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equalsIgnoreCase(other.name))
			return false;
		if (zip == null) {
			if (other.zip != null)
				return false;
		} else if (!zip.equals(other.zip))
			return false;
		return true;
	}

	/** 
	 * Returns the string representation of this Restaurant.
	 * @return the string representation of this Restaurant object in a tabular format, with the address, zip, phone, and recent inspection results.
	 */
	@Override
	public String toString() {
		String s ="";
		s+=this.name+"\n";
		s+="-----------------------------------"+"\n";
		s+=String.format("%" + -20 + "s", "address")+(this.address == null ? ":  " : " :  "+this.address)+"\n";
		s+=String.format("%" + -20 + "s", "zip")+(this.zip == null ? ":  " : " :  " + this.zip)+"\n";
		s+=String.format("%" + -20 + "s", "phone")+(this.phone == null ? ":  " : " :  " + this.phone)+"\n";
		s+="recent inspection results:\n";

		//use a while loop to find the inspections with two most recent dates, and return these inspections 
		int numDayChanges=0; 
		int index=listOfInspections.size()-1; //counter for the index position of listOfInspections
		Date previousDate = null;
		
		//sort listOfInspections by order of date, from earliest to latest
		Collections.sort(listOfInspections);
		
		if (listOfInspections.size()==0) {
			return s;
		}
		
		for (int i=index; i>0 && numDayChanges<2; i--) { //iterate backwards in the ArrayList from the most recent date
			s+=listOfInspections.get(i).toString()+"\n";
			index--;
			previousDate=listOfInspections.get(i-1).getDate();
			if ((previousDate.compareTo(listOfInspections.get(i).getDate())!= 0)){
				 numDayChanges++;
			}
		}
		
		if (numDayChanges < 2) {
			s+=listOfInspections.get(0) + "\n";
		}
		
		return s;
}
	
	//Setters
	/**
	 * Sets the name for this Restaurant object.
	 * @param String name value to be set.
	 */
	public void setName(String name) {
		this.name=name;
	}
	
	/**
	 * Sets the address for this Restaurant object.
	 * @param String address value to be set.
	 */
	public void setAddress(String address) {
		this.address=address;
	}
	
	/**
	 * Sets the phone number for this Restaurant object.
	 * @param String phone number value to be set.
	 */
	public void setPhone(String phone) {
		this.phone=phone;
	}
	
	/**
	 * Sets the zip code for this Restaurant object.
	 * @param String zip code value to be set.
	 */
	public void setZip(String zip) {
		this.zip=zip;
	}
	
	/**
	 * Sets the list of Inspections for this Restaurant object.
	 * @param ArrayList<Inspection> listOfInspections value to be set.
	 */
	public void setListOfInspections(ArrayList<Inspection> listOfInspections) {
		this.listOfInspections=listOfInspections;
	}
	
	//Getters
	/**
	 * Returns the name of this Restaurant object. 
	 * @return the String name value of this Restaurant object. 
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Returns the address of this Restaurant object. 
	 * @return the String address value of this Restaurant object. 
	 */
	public String getAddress() {
		return this.address;
	}
	
	/**
	 * Returns the zip code of this Restaurant object. 
	 * @return the String zip code value of this Restaurant object. 
	 */
	public String getZip() {
		return this.zip;
	}
	
	/**
	 * Returns the phone number of this Restaurant object. 
	 * @return the String phone number value of this Restaurant object. 
	 */
	public String getPhone() {
		return this.phone;
	}
	
	/**
	 * Returns the list of Inspections of this Restaurant object. 
	 * @return the ArrayList<Inspection> year value of this Restaurant object. 
	 */
	public ArrayList<Inspection> getListOfInspections() {
		return this.listOfInspections;
	}
	
	
}
