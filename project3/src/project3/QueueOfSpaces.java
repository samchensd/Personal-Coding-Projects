package project3;

/**
 * implement the PossibleLocations interface.
 * a reference based queue that uses DoublyLinkedLIst as the internal storage
 * @author SamChen
 *
 */
public class QueueOfSpaces implements PossibleLocations {

	//create new DoublyLinkedList for storage
	DoublyLinkedList<Location> locationsList = new DoublyLinkedList<Location>();
	
	/**
	 * enqueue
	 * add location to end of list
	 */
	@Override
	public void add(Location s) {
		locationsList.add(s);
		}

	/**
	 * dequeue
	 * remove and return location at beginning of list
	 */
	@Override
	public Location remove() {
		return locationsList.remove(0);
	}

	/**
	 * return true if list is empty
	 */
	@Override
	public boolean isEmpty() {
		return locationsList.isEmpty();
	}
}
