package project3;
/**
 * implement the PossibleLocations interface.
 * a reference based stack that uses DoublyLinkedLIst as the internal storage
 * @author SamChen
 *
 */
public class StackOfSpaces implements PossibleLocations {

	//create new DoublyLinkedList for storage
	DoublyLinkedList<Location> locationsList = new DoublyLinkedList<Location>();
	
	/**
	 * push
	 * add location to end of list
	 */
	@Override
	public void add(Location s) {
		locationsList.add(s);
	}

	/**
	 * pop
	 * remove and return location at end of list
	 */
	@Override
	public Location remove() {
		return locationsList.remove(locationsList.size()-1);
	}

	/**
	 * return true if list is empty
	 */
	@Override
	public boolean isEmpty() {
		return locationsList.isEmpty();
	}

}
