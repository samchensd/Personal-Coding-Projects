package project2;

import java.util.*;
import java.net.URL; 

/**
 * DataSet class represent a data set with all of its characteristics 
 * (date, title/headline, description/text, list of links)
 * 
 * @author SamChen
 *
 */

public class DataSet implements Comparable <DataSet>{

	
	private Date date;
	private String hatTips;
	private String title;
	private String description;
	private ArrayList<URL> links;
	
	/**
	 * Construct a new DataSet with specified title, description, and links
	 * @param title
	 * @param description
	 * @param links: list of URL objects
	 * @throws IllegalArgumentException if title or description is empty 
	 * or links is not an non-empty list of URL objects
	 */
	public DataSet (String title, String description, ArrayList<URL> links) throws IllegalArgumentException {
		
		//validates parameters
		if (title==null || title.isEmpty()) {
			throw new IllegalArgumentException("the title cannot be empty");
		}if (description==null || description.isEmpty()) {
			throw new IllegalArgumentException("the description cannot be empty");
		}if (links.isEmpty()) {
			throw new IllegalArgumentException("links should be a non-empty list of URL objects");
		}
		
		//assign parameters to local variables
		this.title = title;
		this.description = description;
		this.links = links;
		}
	
	/**
	 * Mutator method that set the Date to specified Date object
	 * @param date, cannot be null and year has to be equal 
	 * or greater than 2000
	 * @throws IllegalArgumentException if param requirements are not met
	 */
	public void setDate(Date date) throws IllegalArgumentException {
		if (date!=null && date.getYear()>=2000) {
			this.date=date;
		}else {
			throw new IllegalArgumentException("the date cannot be null and the year need to be greater than 2000");
		}
	}
	
	/**
	 * Accessor method retrieves the Date object of this DataSet
	 * @return date object of this DataSet
	 */
	public Date getDate() {
		if (date!=null && date.getYear()>=2000) {
			return this.date;
		}else {
			throw new IllegalArgumentException("the date cannot be null and the year need to be greater than 2000");
		}
	}
	
	/**
	 * Mutator method that set a hattips string to specified string
	 * @param hatTips,  a valid hatTip not null string (hat-tips are missing 
	 * for many of the entries in the data set, so they could be and empty string
	 * @throws IllegalArgumentException if hatTips is null
	 */
	public void setHatTips(String hatTips) {
		if (hatTips!=null) {
			this.hatTips=hatTips;
		}else {
			throw new IllegalArgumentException("hatTips cannot be null");
		}
	}
	
	/**
	 * Accessor method retrieves the hatTips string of this DataSet
	 * if the hat-tip was never set or it was set to an empty string,
	 *  the getHatTips method should return an empty string)
	 * @return hatTips of this DataSet
	 */
	public String getHatTips() {
		if (this.hatTips.isBlank()) {
			return "";
		}else {
			return hatTips;
		}
	}
	
	/**
	 * Retrieves the title of this DataSet
	 * @return title
	 */
	public String getTitle() {
			return title;
	}
	/**
	 * Retrieves the description of this DataSet
	 * @return description
	 */
	public String getDecription() {
		return description;
	}
	/**
	 * Retrieves the URL list of this DataSet
	 * @return links
	 */
	public ArrayList<URL> getURL() {
		return links;
	}


	/** Compares this DataSet object with the specified DataSet object for order. 
	 * @param o the object to be compared.
	 * @return a negative integer, zero, or a positive integer as this object is 
	 * less than, equal to, or greater than the specified object.
	 */
	@Override
	public int compareTo(DataSet o) {
		if(this.date ==null || o.date==null) {
			return this.title.compareToIgnoreCase(o.title);
		}
		if(this.date.compareTo(o.date)!=0) {
			return this.date.compareTo(o.date);
		}else {
			return this.title.compareToIgnoreCase(o.title);
		}
	}
	
	/**
	 * Indicates whether some object obj is "equal to" this one. 
	 * Two DataSet objects are considered equal if they have identical dates and titles (case of letters does not matter).
	 * @return true if this object is the same as the obj argument; false otherwise.
	 */
	@Override
	public boolean equals(Object obj ) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof DataSet))
			return false;
		DataSet other = (DataSet) obj;
		if(this.compareTo(other)==0) {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * Returns the string representation of this DataSet.
	 * @returns the string representation of this DataSet object 
	 */
	@Override
	public String toString () {
		
		//turn URL ArrayList links into String
		String tempURL = "";
		for (URL b:this.links) {
			tempURL = tempURL + b.toString() + "\n";
		}
		
		//Check whether to print date
		if (this.date==null) {
			return this.title
					.concat("\n")
					.concat(this.description)
					.concat("\n")
					.concat(tempURL);
		}else{
			return this.date.toString()
					.concat("\n")
					.concat(this.title)
					.concat("\n")
					.concat(this.description)
					.concat("\n")
					.concat(tempURL);
			}			
		}
	}
