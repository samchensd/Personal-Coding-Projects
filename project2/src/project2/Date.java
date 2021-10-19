package project2;

/* This class represent a calendar date.
 * It store the information about year, month and day.
 * @author Sam Chen
 * */

public class Date implements Comparable<Date>{
	
	private int year;
	private int month; 
	private int day;
	/**
	 * Constructs a new Date for with specified year, month and day
	 * @param year
	 * @param month
	 * @param day
	 * @throws IllegalArgumentException if the input of year, month, or day is invalid
	 */
	public Date( int year, int month, int day) throws IllegalArgumentException{
		
		// a list to keep track which months are long
		int longMonth[] = new int[]{1,3,5,7,8,10,12};
		//whether the input year is a leap year
		boolean leapYear;
		//whether the month is long
		boolean ifLongMonth= false;
		
		//determine if year and month are valid
		if (year<0) {
			throw new IllegalArgumentException("year has to be positive integer");
		}if (month>12 || month<1) {
			throw new IllegalArgumentException("month has to be between 1 and 12");
		}
		
		//leap year algorithm, determines whether the year is a leap year
		if (year%4!=0) {
			leapYear = false;
		}else if (year%100!=0) {
			leapYear = true;
		}else if (year%400!=0) {
			leapYear = false;
		}else {
			leapYear = true;
		}
		
		//Check if day is valid when it is a leap year and it is February
		if (leapYear) {
			if (month ==2) {
				if(day>29) {
					throw new IllegalArgumentException("day does not exist");
				}
			}
		}else {
			if (month ==2) {
				if(day>28) {
					throw new IllegalArgumentException("day does not exist");
				} 
			}
		}
		
		//determines if month is long
		for (int x :longMonth) {
			if (x == month) {
				ifLongMonth = true;
			}
		}
		
		//determine if day is valid
		if (ifLongMonth) {
			if (day>31)
				throw new IllegalArgumentException("dday does not exist"); 
		}else {
			if(day>30)
				throw new IllegalArgumentException("cday does not exist");
		}
		
		//assign input values of year, month, and date to local variable
		this.setYear(year);
		this.month = month;
		this.day = day;
	}

	/**
	 * Compares a date object to another based on year, month and day in that order for order
	 * earlier the dates, the smaller
	 * @param o Date object to be compared to
	 * @return a negative integer, zero, or a positive integer as this object is 
	 * less than, equal to, or greater than the specified object.
	 */
	@Override
	public int compareTo(Date o) {
		if (this.getYear()!=o.getYear()) {
			return (this.getYear()-o.getYear());
		}if (this.month!=o.month) {
			return (this.month-o.month);
		}if (this.day!=o.day) {
			return (this.day-o.day);
		}
		return 0;
	}
	
	/**
	 * Indicates whether some object obj is "equal to" this one. 
	 * Two Date objects are considered equal if they represent identical dates.
	 * @return true if this object is the same as the obj argument; false otherwise.
	 */
	@Override
	public boolean equals(Object obj ) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Date))
			return false;
		Date other = (Date) obj;
		if(this.compareTo(other)==0) {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * Returns the string representation of this Date.
	 * @returns the string representation of this Date object 
	 */
	@Override
	public String toString () {
		return String.format("%4d-%02d-%02d", this.getYear(), this.month,this.day);
		//add 0 to format
	}
	
	/**
	 * Returns the year this Date object. 
	 * @return the year name of this Date object 
	 */
	public int getYear() {
		return year;
	}

	/**
	 * Set the year of this Date object to parameter
	 * @param year
	 */
	public void setYear(int year) {
		this.year = year;
	}

}
