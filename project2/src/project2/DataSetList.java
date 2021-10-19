package project2;
import java.util.ArrayList;
import java.util.Collections;

/**
 * DataSetList class is used to store a collection of DataSet objects. 
 * This class inherits all of its properties from an ArrayList<DataSet>. It 
 * adds DataSet-specific functions that allow search by title, description, and by URL
 * 
 * @author SamChen
 *
 */
@SuppressWarnings("serial")
public class DataSetList extends ArrayList<DataSet>{
	
	/**
	 * Search through the list of DataSet for an object matching 
	 * the given title. 
	 * @param keyword the title for which to search 
	 * @return a DataSetList of DataSet objects with titles matching keyword, or
	 * null if the matching title is not found  
	 * @throws IllegalArgumentException if keyword is null or empty
	 */
	public DataSetList getByTitle (String keyword) throws IllegalArgumentException {
		boolean ifEmpty = true;
		DataSetList result = new DataSetList();
		
		//check if keyword is null or empty
		if (keyword==null || keyword.isEmpty()){
			throw new IllegalArgumentException("keyword is invalid"); 
		}
		
		for (int i = 0; i < this.size(); i++) {
			 DataSet c = this.get(i);
			 String title = c.getTitle();
				if (title == null) 
					continue; 
				if (title.toLowerCase().contains( keyword.toLowerCase() ) ) {
					result.add(c);
					ifEmpty=false;
				}
		 }
		//return null if there are no matches
		if (ifEmpty) {
			return null;
		}else {
			//sort the DataSetList by the compareTo returns
			Collections.sort(result);
			return result; 
		}

	}
	
	/**
	 * Search through the list of DataSet for an object matching 
	 * the given description. 
	 * @param keyword the description for which to search 
	 * @return a DataSetList of DataSet objects with description matching keyword, or
	 * null if the matching description is not found  
	 * @throws IllegalArgumentException if keyword is null or empty
	 */
	public DataSetList getByDescription ( String keyword ) throws IllegalArgumentException{
		boolean ifEmpty = true;
		DataSetList result = new DataSetList();
		
		//check if keyword is null or empty
		if (keyword==null || keyword.isEmpty()){
			throw new IllegalArgumentException("keyword is invalid"); 
		}
		for (DataSet c : this ) {
			String description = c.getDecription();
			if (description == null) 
				continue; 
			if (description.toLowerCase().contains( keyword.toLowerCase() ) ) {
				result.add(c);
				ifEmpty=false;
			}
		}
		//return null if there are no matches
		if (ifEmpty) {
			return null;
		}else {
			//sort the DataSetList by the compareTo returns
			Collections.sort(result);
			return result; 
			}
		}
	
	/**
	 * Search through the list of DataSet for an object matching 
	 * the given URL. 
	 * @param keyword the URL for which to search 
	 * @return a DataSetList of DataSet objects with URL matching keyword, or
	 * null if the matching description is not found  
	 * @throws IllegalArgumentException if keyword is null or empty
	 */
	public DataSetList getByURL (String keyword) throws IllegalArgumentException{
		
		DataSetList result = new DataSetList();
		
		//check if keyword is null or empty
		if (keyword==null || keyword.isEmpty()){
			throw new IllegalArgumentException("keyword is invalid"); 
		}
		
		for(DataSet c : this) {
			boolean isMatch = false;
			//iterates through the ArrayList of URLs of the DataSet
			for ( int i =0;i<c.getURL().size();i++) {
				if (c.getURL().get(i).toString().toLowerCase().contains( keyword.toLowerCase())){
					isMatch = true;
					}
				}if (isMatch)
					result.add(c);
		}
		//return null if there are no matches
		if (result.isEmpty()) {
			return null;
		}else {
			//sort the DataSetList by the compareTo returns
			Collections.sort(result);
			return result;
			}	
		}
	
	}
