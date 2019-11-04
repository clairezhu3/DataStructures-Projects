

import java.util.Scanner;
/**
 * Date class is used to represent a date.  
 * It stores the numerical values for month, day, and year, but can also store this information in a single string. 
 * @author Claire Zhu
 */

public class Date implements Comparable<Date>{
	
	private String date;
	private int month;
	private int day;
	private int year;
	
	/**
	 * Constructs a new Date object with one specified date value. 
	 * @param String date value to indicate the month, day, and year of this Date; should not be an empty string.  
	 * @throws IllegalArgumentException if date value is invalid. 
	 */
	public Date(String date) throws IllegalArgumentException {
		if (date==null) {
			throw new IllegalArgumentException("Invalid date input. Date must either be formatted as 'MM/DD/YYYY or MM/DD/YY'.");
		}
		//validate that the date is in the format MM/DD/YYYY or MM/DD/YY
		if (! (date.length() == 10 || date.length() == 8)){
			throw new IllegalArgumentException("Invalid date input. Date must either be formatted as 'MM/DD/YYYY or MM/DD/YY'.");
		}
		for (int i=0; i<date.length();i++) {
			char datevalue = date.charAt(i);
			if(i==0 || i==1 || i==3 || i==4) {
				if (!(Character.isDigit(datevalue))) {
					throw new IllegalArgumentException("Invalid date input. Date must either be formatted as 'MM/DD/YYYY or MM/DD/YY'.");
				}
			}
			if((i==2) || (i==5)){
				if (!(datevalue=='/')) {
					throw new IllegalArgumentException("Invalid date input. Date must either be formatted as 'MM/DD/YYYY or MM/DD/YY'.");
				}
			}
			if((i==6) || (i==7) || (i==8) || (i==9)){
				if (!(Character.isDigit(datevalue))) {
					throw new IllegalArgumentException("Invalid date input. Date must either be formatted as 'MM/DD/YYYY or MM/DD/YY'.");
				}
			}
		}
		
		//Take substrings from the date parameter and assign them to values for month, day, and year integers
		String[] dateSplit=date.split("/");
		month = Integer.parseInt(dateSplit[0]);
		day = Integer.parseInt(dateSplit[1]);
		year=Integer.parseInt(dateSplit[2]);
		
		//validate the month, day, and year values before assigning them to the Date object's variables
		validateDateValues(month,day,year);
		this.month=month;
		this.day=day;
		this.year=year;
		
		String formattedMonth=Integer.toString(month);
		String formattedDay=Integer.toString(day);
		String formattedYear=Integer.toString(year);
		if (month<10) {
			formattedMonth="0"+formattedMonth;
		}
		if (year<10) {
			formattedYear="0"+formattedYear;
		}
		if (day<10) {
			formattedDay="0"+formattedDay;
		}
		this.date=formattedMonth+"/"+formattedDay+"/"+formattedYear;
	}
	
	/**
	 * Constructs a new Date object with specified month, day, and year values.
	 * @param int month value of this Date; must be a value from 1-12, inclusive.
	 * @param int day value of this Date; 1 is acceptable the lower bound, and the acceptable upper bound is dependent on the month and year.
	 * @param int year value of this Date; must be a value from 2000-2005, inclusive.
	 * @throws IllegalArgumentException if any month, day, or year parameter is invalid. 
	 */
	public Date(int month, int day, int year) throws IllegalArgumentException {
		//use validation method to validate that values are within the acceptable ranges
		validateDateValues(month,day,year);
		this.month=month;
		this.day=day;
		this.year=year;
		String formattedMonth=Integer.toString(month);
		String formattedDay=Integer.toString(day);
		String formattedYear=Integer.toString(year);
		if (month<10) {
			formattedMonth="0"+formattedMonth;
		}
		if (year<10) {
			formattedYear="0"+formattedYear;
		}
		if (day<10) {
			formattedDay="0"+formattedDay;
		}
		this.date=formattedMonth+"/"+formattedDay+"/"+formattedYear;
	}
	

	/**
	 * Validates that the date values of month, day, and year parameters from the Date constructors are within the acceptable ranges.
	 * @param int month value of this Date; must be a value from 1-12, inclusive.
	 * @param int day value of this Date; 1 is acceptable the lower bound, and the acceptable upper bound is dependent on the month and year.
	 * @param int year value of this Date; must be a value from 2000-2005, inclusive.
	 * @throws IllegalArgumentException if any month, day, or year parameter is invalid. 
	 */
	public void validateDateValues(int month, int day, int year) throws IllegalArgumentException {
		//validate that the year value is within the range 
		if (!(year>=2000 && year<=2025) && !(year>=0 && year<=25)) {
			throw new IllegalArgumentException("Invalid year input.");
		}
		
		//validate that the month value is within the range 
		if ((month<1) || (month>12) ) {
			throw new IllegalArgumentException("Invalid month input. Must be a value from 1-12, inclusive");
		}
		
		//validate that the day value is within the range, given the month input
		else if ( (month==1) || (month==3) || (month==5) || (month==7) || (month==8) || (month==10) || (month==12)) {
			if ((day<1) || (day>31)) {
				throw new IllegalArgumentException("Invalid day input.");
			}
		}
		else if ( (month==4) || (month==6) || (month==9) || (month==11) ) {
			if ((day<1) || (day>30)) {
				throw new IllegalArgumentException("Invalid day input.");
			}
		}
		else if ( month==2 ) {
			if ((year==2000 || (year==0)) || ((year==2004) || (year==4)) || (year==2008) || (year==8) || (year==2012) || (year==12) || (year==2016) || (year==16) || (year==2020) || (year==20) || (year==2024) || (year==24)) { 
				if (day<1 || day>29) {
					throw new IllegalArgumentException("Invalid day input.");
				}
			}
			else {
				if (day>28) {
					throw new IllegalArgumentException("Invalid day input.");
				}
			}
		}
		}

	

	
	//Setters
	/**
	 * Validates and sets the date for this Date object.
	 * @param String date value to be examined and set.
	 * @throws IllegalArgumentException if the date value is invalid
	 */
	public void setDate(String date) throws IllegalArgumentException{
		//validation here
		this.date=date;
	}
	
	/**
	 * Sets the month for this Date object.
	 * @param int month value to be set.
	 */
	public void setMonth(int month) {
		//validation here
		this.month=month;
	}
	
	/**
	 * Sets the day for this Date object.
	 * @param int month value to be set.
	 */
	public void setDay(int day) {
		//validation here
		this.day=day;
	}
	
	/**
	 * Sets the year for this Date object.
	 * @param int year value to be set.
	 */
	public void setYear(int year) {
		//validation here
		this.year=year;
	}
	
	//Getters
	/**
	 * Returns the date of this Date object. 
	 * @return the String date value of this Date object. 
	 */
	public String getDate() {
		return this.date;
	}
	
	/**
	 * Returns the month of this Date object. 
	 * @return the int month value of this Date object. 
	 */
	public int getMonth() {
		return this.month;
	}
	
	/**
	 * Returns the day of this Date object. 
	 * @return the int day value of this Date object. 
	 */
	public int getDay() {
		return this.day;
	}
	
	/**
	 * Returns the year of this Date object. 
	 * @return the int year value of this Date object. 
	 */
	public int getYear() {
		return this.year;
	}

	
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Date o) {
		if (this.year<o.year) {
			return -1;
		}
		if (this.year>o.year) {
			return 1;
		}
		if (this.year==o.year) {
			if (this.month<o.month) {
				return -1;
			}
			if (this.month>o.month) {
				return 1;
			}
			if (this.month==o.month) {
				if (this.day<o.day) {
					return -1;
				}
				if (this.day>o.day) {
					return 1;
				}
				else {
					return 0;
				}
			}
		}
		return 0;
	}
	
	/** 
	 * Returns the string representation of this Date.
	 * @returns the string representation of this Date object in "MM/DD/YYYY" format.
	 */
	public String toString() {
		if (this.date.length()==8) {
			this.date=this.date.substring(0,6)+"20"+this.date.substring(6);
			return this.date;
		}
		else {
			return this.date;
		}
	}
}
	