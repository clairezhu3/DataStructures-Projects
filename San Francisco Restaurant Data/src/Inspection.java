



/**
 * The Inspection Class used to represent the particular inspection of a restaurant.
 * This class stores the date of the inspection, the assigned score, the violation description and the risk category.
 * @author Claire Zhu
 */

public class Inspection implements Comparable<Inspection>{
	private Date date;
	private int score;
	private String violation;
	private String risk;
	
	/**
	 * Constructs a new Inspection object with specified date value, score value, violation value, and risk value.
	 * This class's two String parameters, violation and risk, are allowed to be null or empty.
	 * @param date
	 * @param score
	 * @param violation
	 * @param risk
	 * @throws IllegalArgumentException if called with null value for date or if called with the score outside of the valid range of 0 to 100, inclusive.
	 */
	public Inspection (Date date, int score, String violation, String risk) throws IllegalArgumentException {
		if (date==null) {
			throw new IllegalArgumentException("Invalid input. Date cannot be null");
		}
		if (score<0 || score>100) {
			throw new IllegalArgumentException("Invalid input. Score must be in the range of 0 to 100 (inclusive)");
		}
		this.date=date;
		this.score=score;
		this.violation=violation;
		this.risk=risk;	
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	//uses the Date class's compareTo method to compare two Inspection class objects by their dates
	public int compareTo(Inspection i) {
		return(this.date.compareTo(i.date));
	}

	//setters
	/**
	 * Sets the date for this Inspection object.
	 * @param Date class date value to be set.
	 */
	public void setDate(Date date) {
		this.date=date;
	}
	/**
	 * Sets the int score for this Inspection object.
	 * @param int score value to be set.
	 */
	public void setScore(int score) {
		this.score=score;
	}
	/**
	 * Sets the String violation description for this Inspection object.
	 * @param String violation description to be set.
	 */
	public void setViolation(String violation) {
		this.violation=violation;
	}
	
	/**
	 * Sets the String risk category for this Inspection object.
	 * @param String risk category to be set.
	 */
	public void setRisk(String risk) {
		this.risk=risk;
	}
	
	//getters
	/**
	 * Returns the date of this Inspection object. 
	 * @return the Date class date value of this Inspection object. 
	 */
	public Date getDate() {
		return this.date;
	}
	
	/**
	 * Returns the score of this Inspection object. 
	 * @return the int score value of this Inspection object. 
	 */
	public int getScore() {
		return this.score;
	}
	
	/**
	 * Returns the violation description of this Inspection object.
	 * @return the String violation description of this Inspection object;
	 */
	public String getViolation() {
		return this.violation;
	}
	/**
	 * Returns the risk category of this Inspection object.
	 * @return the String risk category of this Inspection object;
	 */
	public String getRisk() {
		return this.risk;
	}
	
	/**
	 * Returns the string representation of this Inspection. 
	 * @return the string representation of the date, score, violation, and risk values of this Inspection.
	 */
	public String toString() {
		String s = "";
		s+=date.toString()+", "+Integer.toString(score);
		if (this.violation == null || this.violation.equals("")) {
			if (this.risk == null || this.risk.equals("")) {
				return s;
			}
			else {
				return s+=", "+this.risk;
			}
		}
		else if (this.risk ==null || this.risk.equals("")) {
			return s+=", "+this.violation;
		}
		else {
			return s+=", "+this.violation+", "+this.risk;
		}
	}


}
