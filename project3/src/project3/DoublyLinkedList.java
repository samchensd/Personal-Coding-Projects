package project3;

import java.util.*;

/**
 * a generic class that does not permit null elements
 * @author SamChen
 *
 */

public class DoublyLinkedList<E> implements Iterable<E>{
	
	//private variables that hold the head and tail nodes of the doubly linked list
	private Node<E> head;
	private Node<E> tail;
	
	/**
	 * Constructor of the class, sets list to be null
	 */
	public DoublyLinkedList() {
		head = null;
		tail = null;
	}
	
	/**
	 * Node class that defines nodes that form the doubly linked lists,
	 * Records previous node and node after it, stores the element of the node in data
	 * @author SamChen
	 * @param <E>
	 */
	@SuppressWarnings("hiding")
	private class Node<E>{
		E data;
		Node<E> next;
		Node<E> prev;
		public Node(E data) {
			this.data = data;
		}
	}
	
	/**
	 * Appends the specified element to the end of this list. 
	 * @param e : element to be appended to this list
	 * @return true if this list changed as a result of the call
	 * @return false if the specified element is null
	 * @throws ClassCastException: if the class of the specified element prevents it from being added to this list
	 */
	public boolean add(E e) throws ClassCastException{
		
		//checks if element is null
		if (e ==null)
			return false;
		try {
			//create a Node to store e
			Node<E> newNode = new Node<E>(e);
			
			//check if the list is empty, if yes make e first element
			if (head== null) {
				head = tail = newNode;
				head.prev = null;
				tail.next = null;
				return true;
			}else {
				//if list is not empty, add e to last element
				tail.next = newNode;
				newNode.prev = tail;
				tail = newNode;
				tail.next = null;
				return true;
			}
		//throw exception if the class of the specified element prevents it from being added to this list
		}catch(ClassCastException e1 ){
			throw new ClassCastException ("class of the specified element prevents it from being added to this list ");
		}
	}
	
	/**
	 * Inserts the specified element at the specified position in this list.
	 * Shifts the element currently at that position (if any) 
	 * and any subsequent elements to the right (adds one to their indices).
	 * @param e - element to be added to this list
	 * @param pos - specified position to add the given element, e
	 * @return true - if this list changed as a result of the call
	 * @return false - if the specified element is null
	 * @throws ClassCastException - if the class of the specified element prevents it from being added to this list
	 * @throws IndexOutOfBoundsException - if pos is out of range (pos < 0 || pos > size())
	 */
	public boolean add(E e,int pos) throws IndexOutOfBoundsException, ClassCastException{
		
		//checks if element is null
		if (e ==null)
			return false;
		//checks if pos is out of range
		if (pos <0 || pos>size()) {
			throw new IndexOutOfBoundsException ("pos is out of range");
		}
		
		try {
			//create a Node to store e
			Node<E> newNode = new Node<E>(e);
			//if the indicated location is at the end of the list 
			//call add function that add element to end of list
			if (pos == size())
				return add(e);
			
			//if pos = 0 add element to front of list
			if (pos == 0) {
				newNode.next = head;
				head.prev = newNode;
				head = newNode;
				return true;
			}
			
			//if not adding to front or end, add to middle of list
			//iterate until reaching node in front of specified location
			Node<E> current = head;
			for (int i = 0;i<pos-1;i++) {
				current = current.next;
			}
			//insert element to list at specified position
			newNode.next = current.next;
			(current.next).prev = newNode;
			current.next = newNode;
			newNode.prev = current;
			
			return true;
			
		}
		//throw exception if the class of the specified element prevents it from being added to this list
		catch(ClassCastException e1 ){
			throw new ClassCastException ("class of the specified element prevents it from being added to this list ");
		}
	}
	
	/**
	 * Removes all of the elements from this list. The list will be empty after this call returns.
	 */
	public void clear() {
		head = null;
		tail= null;
	}
	
	/**
	 * Check if specified element exist in list
	 * @param o - element whose presence in this list is to be tested
	 * @return true - if this list contains the specified element
	 * @return false - if this list does not contain the specified element
	 */
	public boolean contains(Object o) {
		Node<E> temp = head;
		//loops through each element of the list to see if it equals to o
		while(temp!=null) {
			if (Objects.equals(o,temp.data)) {
				return true;
			}
			temp = temp.next;
		}return false;
	}
	
	/**
	 * Compares the specified object with this list for equality.
	 * two lists are defined to be equal if they contain the same elements in the same order.
	 * @param o -  the object to be compared for equality with this list
	 * @return true - if and only if the specified object is also an instance of a DoublyLinkedList,
	 * both lists have the same size, and all corresponding pairs of elements in the two lists are equal
	 */
	public boolean equals(Object o) {
		//checks if o is null and if it is an instance of DoublyLinkedList
		if (o == null)
			return false;
		if (!(o instanceof DoublyLinkedList))
			return false;
		//Create a DoublyLinkedList of o and then compare each element in order to this list
		@SuppressWarnings("unchecked")
		DoublyLinkedList<E> temp = (DoublyLinkedList<E>) o;
		if ((temp.size()!=size()))
			return false;
		Node<E> current1 = temp.head;
		Node<E> current2 = this.head;
		while (current1!=null) {
			if (!(current1.data.equals(current2.data)))
				return false;
			current1 = current1.next;
			current2 = current2.next;
		}return true;
	}
	
	/**
	 * Returns the element at the specified position in this list or null if such position does not exist.
	 * @param pos - index of the element to return
	 * @return element of the list at index or null if position does not exist
	 */
	public E get(int pos) {
		//check if pos is valid index
		if (pos <0 || pos>=size()) {
			return null;
		}
		//iterate through list until reaching element at specified index
		Node<E> current = head;
		for (int i = 0;i<pos;i++) {
			current = current.next;
		}return current.data;
	}
	
	/**
	 * Self created class that helps with navigating the list in other methods
	 * Returns the Node at the specified position in this list or null if such position does not exist.
	 * @param pos - index of the element to return
	 * @return Node of the list at index or null if position does not exist
	 */
	public Node<E> getNode(int pos) {
		//check if pos is valid index
		if (pos <0 || pos>=size()) {
			return null;
		}
		//iterate through list until reaching Node at specified index
		Node<E> current = head;
		for (int i = 0;i<pos;i++) {
			current = current.next;
		}return current;
	}
	
	/**
	 * check if list is empty
	 * @return true if this list contains no elements
	 */
	public boolean isEmpty() {
		if(head ==null) {
			return true;
		}return false;
	}
	
	/**
	 * Returns the index of the first occurrence of the specified element in this list,
	 * or -1 if this list does not contain the element. 
	 * @param o - element to search for
	 * @return index of first occurrence of o, or -1 o not in list
	 */
	public int indexOf (Object o) {
		for (int i=0;i<size();i++) {
			if (o==null) {
				if (get(i)==null)
					return i;
				else
					return -1;
			}
			if (o.equals(get(i)))
				return i;
			}
		return -1;
	}
	
	/**
	 * Removes the first occurrence of the specified element from this list, if it is present.
	 * If this list does not contain the element, it is unchanged.
	 * @param o - element to be removed from this list, if present
	 * @return true - if this list contained the specified element
	 * @throws ClassCastException - if the type of the specified element is incompatible with this list
	 * @throws NullPointerException - if the specified element is null
	 */
	public boolean remove (Object o) throws NullPointerException, ClassCastException{
		//checks if o is null
		if (o==null)
			throw new NullPointerException("specified element is null");
		
		try {
			//iterate through list to find index of object then call remove at index method
			for (int i=0;i<size();i++) {
				if (Objects.equals(o, get(i))) {
					this.remove(i);
					return true;
				}
			}return false;
		}
		//throw exception if o class is incompatible with list
		catch (ClassCastException e) {
			throw new ClassCastException (" class of the specified element is incompatible with this list");
		}
	}
	
	/**
	 * Removes the element at the specified position pos in this list.
	 * Shifts any subsequent elements to the left (subtracts one from their indices).
	 * @param pos - the index of the element to be removed
	 * @return element that was removed from the list.
	 * @throws IndexOutOfBoundsException - if pos is out of range (pos < 0 || pos >= size())
	 */
	public E remove (int pos) throws IndexOutOfBoundsException{
		//checks if pos is out of range
		if (pos <0 || pos>=size()) {
			throw new IndexOutOfBoundsException ("pos is out of range");
		}
		
		Node<E> current = getNode(pos);
		//make second node head if removing head
		if (pos == 0) {
			head = current.next;
			if (head == null)
				this.clear();
			else
				head.prev = null;
		}
		//make second to last node tail if removing tail
		else if (pos == size()-1) {
			tail = current.prev;
			tail.next = null;
			if (pos-1 ==0)
				head = current.prev;
			else
				current.prev.prev.next = current.prev;
		}
		//removes node
		else {
			(current.prev).next = current.next;
		}
		return current.data;
	}
	/**
	 * @return the number of elements in this list
	 */
	public int size() {
		int count = 0;
		Node<E> temp = head;
		while(temp!=null) {
			temp = temp.next;
			count++;
		}
		return count;
	}
	
	/**
	 * Returns a string representation of this list.
	 * The string representation consists of a list of the collection's elements in the order they are returned by its iterator,
	 * enclosed in square brackets ("[]"). Adjacent elements are separated by the characters ", " (comma and space).
	 * Elements are converted to strings as by String.valueOf(Object).
	 */
	public String toString() {
		//calls iterator
		Iterator <E> itr = this.iterator();
		String result = "[";
		while(itr.hasNext()) {
			result = result + itr.next() + ",";
		}return result +"]";
	}

	/**
	 * Iterator<E> iterator() Returns an iterator over the elements in this list.
	 * This iterator returns elements in the same order in which they are stored in this list
	 */
	@Override
	public Iterator<E> iterator() {
		return new DoublyLinkedListIterator();
	}
	
	/**
	 * Iterable<E> interface
	 * @author SamChen
	 *
	 */
	private class DoublyLinkedListIterator implements Iterator<E>{
		private Node<E> current = head;
		@Override
		public boolean hasNext() {
			return current!=null;
		}

		@Override
		public E next() {
			E temp = current.data;
			current = current.next;
			return temp;
		}
		
	}
	
}
