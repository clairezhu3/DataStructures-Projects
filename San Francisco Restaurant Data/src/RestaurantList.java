

import java.util.ArrayList;
import java.util.Collections;

/**
 * RestaurantList class is used to store a collection of Restaurant objects. 
 * This class inherits from the ArrayList<Restaurant> class. 
 * It is able to find a list of objects related by a name keyword or a zip code keyword. 
 * @author Claire Zhu
 */

public class RestaurantList extends LinkedList<Restaurant> {
	
	
	/**
	 * This default constructor constructs a new empty RestaurantList object. 
	 */
	public RestaurantList() {
	}
	
	
	/**
	 * Searches through an ArrayList of Restaurant objects and looks for which objects' names contain the keyword as a substring (case insensitive).
	 * @param String keyword (restaurant name) from user input.
	 * @return RestaurantList list of Restaurant objects whose names contain the keyword parameter as a substring (case insensitive).
	 * null if the functions are called with a keyword that is either equal to null or an empty string, or if there are no matches for the keyword.  
	 *The returned list is sorted according to the natural order of its elements.
	 */
	public RestaurantList getMatchingRestaurants(String keyword) {
		
		RestaurantList listFromKeyword = new RestaurantList();
		if (keyword == null || keyword.equals("")) {
			return null;
		}
		
		for (Restaurant restaurant: this) {
			if (restaurant.getName().toLowerCase().contains(keyword.toLowerCase())) {
				listFromKeyword.add(restaurant);
			}
		}
		if (listFromKeyword.isEmpty()) {
			return null;
		}
		listFromKeyword.sort();
		return (RestaurantList) listFromKeyword;
	}
	
	/**
	 * Searches through an ArrayList of Restaurant objects and looks for which objects' zip codes are equal to the keyword.
	 * @param String keyword (restaurant zip code) from user input.
	 * @return RestaurantList list of Restaurant objects whose zip codes are equal to the keyword.
	 * null if the functions are called with a keyword that is either equal to null or an empty string, or if there are no matches for the keyword.  
	 *The returned list is sorted according to the natural order of its elements.
	 */
	public RestaurantList getMatchingZip(String keyword) {
		
		RestaurantList listFromKeyword = new RestaurantList();
		
		if (keyword == null || keyword.isEmpty()) {
			return null;
		}
		for (Restaurant restaurant: this ) {
			if (restaurant.getZip().contains(keyword)) {
				listFromKeyword.add(restaurant);
			}
		}
		if (listFromKeyword.isEmpty()) {
			return null;
		}
		listFromKeyword.sort();
		return listFromKeyword;
		
	}
	
	
	
	/**
	 * Returns a String representation of the names of Restaurant object stored in this list. 
	 * @return String containing a list of names of Restaurant objects stored in this list.
	 */
	public String toString() {
		//instantiate a new list that will be returned
		String restaurantListRepresentation = "";
		if (this.size()==0) {
			return "";
		}
		//add in each restaurant in this RestaurantList object
		for (int i=0; i<this.size()-1;i++) {
			restaurantListRepresentation+=this.get(i).getName()+"; ";
		}
		restaurantListRepresentation+=this.get(this.size()-1).getName();
		return restaurantListRepresentation;
	}
}


