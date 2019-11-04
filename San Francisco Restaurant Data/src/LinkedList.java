

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * The LinkedList class is used to store a collection of <E> objects.
 * This class makes a list of elements linked together with nodes.
 * @param <E> 
 * @author Claire Zhu
 */

public class LinkedList<E> implements Iterable<E>, Collection<E> {
	
	private Node<E> head;
	private int size;
	
	/**
	 * This Node<E> class is a nested class that provides nodes for this LinkedList.
	 * @author clairezhu
	 * @param <E> Element e passed in the one-param constructor 
	 */
	private static class Node <E> {
		E e;
		Node<E> next;
	
		// empty constructor 
		Node(){
		}
		
		// one-param constructor
		Node(E e) {
			this.e=e;
		}
	}
	
	/**
	 * Returns the index of the first occurrence of the specified element in this list, or -1 if this list does not contain the element
	 * @param Element e, the element to search for
	 * @return the int value of the index of the first occurrence of the specified element in this list, or -1 if this list does not contain the element
	 */
	public int indexOf(E e) {
		Node<E> current = head;
		int counter=0;
		
		while ( current != null) {
			if (current.e.equals(e)) {
				return counter;
			}
			current=current.next;
			counter++;
		}
		return -1;
	}
	
	/**
	 * Returns the element at the specified position in this list.
	 * @param int index of the element to return
	 * @return Element object at the specified position in this list 
	 * @throws IndexOutOfBoundsException if the index is out of range (less than 0 or greater than the size of this LinkedList)
	 */
	public E get(int index) throws IndexOutOfBoundsException {
		if (index<0 || index>=this.size()) throw new IndexOutOfBoundsException("Index value not within range.");
		
		Node<E> current = head;
		int counter=0;
		
		while (counter!=index && current!=null) {
			current=current.next;
			counter++;
		}

		return current.e;
	}
	
	/**
	 * Returns the string representation of this collection.
	 * @return the string representation of this collection, which consists of a list of the collection's elements in the order they are returned by its iterators 
	 */
	public String toString() {  
		String str="[";
		//instantiate Iterator<E> object
		Iterator<E> iterator = this.iterator();
		
		//use iterator to go through this collection 
		while (iterator.hasNext()) {
			str+=String.valueOf(iterator.next()) + ", ";
		}
		if (str.length()!=1) {
			str=str.substring(0,str.length()-1);
		}
		
		str+="]";
		
		return str;
	}
	
	/**
	 * Sorts the elements in this list into an array, sorting the array, and then converting it back to a list
	 */
	public void sort() {
		Object [] array = toArray();
		Arrays.sort(array);
		this.clear();
		for (Object o : array ) {
			this.add((E)o);
		}
	}
	/**
	 * Compares the specified object with this collection for equality
	 * @param Object o to be compared
	 * @return boolean value depending on whether or not this list and object o are the same
	 */
	public boolean equals(Object o) {
		
		//returns false if o is not of the type LinkedList<?>
		if (!(o instanceof LinkedList<?>)) {
			return false;
		}
		
		//cast Object o as a LinkedList<E> type and set reference variable list
		LinkedList<E> list = (LinkedList<E>)o; 
		
		//instantiate 2 Iterator<E> objects; one for this list and one for list
		Iterator<E> thisIterator = this.iterator(); 
		Iterator<E> listIterator = list.iterator();
		
		//return false if the sizes of these two lists are not equal 
		if (!(this.size()==list.size)) {
			return false;
		}
		
		//iterate through both lists and use the .equals() method from the object inside the nodes to do the actual comparison
		//return false if there is any part of the lists that are not the same, and return true otherwise
		while (thisIterator.hasNext() && listIterator.hasNext()) {
			if (!(thisIterator.next().equals(listIterator.next()))) {
				return false;
			}
		}
		return true; 
	}
	
	/**
	 * Returns the hash code value for this collection. 
	 * @return int hash code value for this collection
	 */
	public int hashCode() {
		
		Node<E> current = head;
		int counter = 31;
		int result = 0;
		
		//iterate through this list and add to result variable for each element  
		while (current!=null) {
			result+=counter*current.e.hashCode();
			counter++;
		}
		return result; 
	}
	
	/**
	 * Returns the number of elements in this collection
	 * @return int value of the number of elements in this collection
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Returns true if this collection contains no elements
	 * @return boolean value true if this collection contains no elements, and false if the collection does contains elements
	 */
	@Override
	public boolean isEmpty() {
		//if there are no elements in this collection, return true
		if (this.size()==0) {
			return true;
		}
		//return false otherwise
		else {
			return false;
		}
	}

	/**
	 * Returns true if this collection contains the specified element 
	 * @param Object o to be searched for within this collection
	 * @return boolean value true if this collection contains the specified element 
	 */
	@Override
	public boolean contains(Object o) {
		//instantiate new Iterator<E> object for this list
		Iterator<E> iterator = iterator();
		while (iterator.hasNext()) {
			//if the object inside the iterator's next node is equal to the Object o, return true
			if (iterator.next().equals(o)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns an array containing all of the elements in this collection
	 * @return array of type Object containing all of the elements in this collection
	 */
	@Override
	public Object[] toArray() {
		//instantiate a new array with the size of this list, which will be returned 
		Object[] array = new Object[this.size];
		
		//assign the current Node to reference the head of this list
		Node<E> current = head;
		
		for (int i=0; i<size; i++) {
			//assign each index of the array as a reference of the object in the current node
			array[i] = current.e;
			current=current.next;
		}
		return array;
	}

	/**
	 * Returns an array containing all of the elements in this collection
	 * The runtime type of the returned array is that of the specified array
	 * @param Generic type array 
	 * @return Generic type array containing all of the elements in this collection 
	 */
	@Override
	public <T> T[] toArray(T[] a) {
		
		//if the length of a is less than the size of this collection, then 
		if (a.length<this.size()) {
			//Taken from Java source code
			a = (T[])java.lang.reflect.Array.newInstance(a.getClass().getComponentType(), this.size());
		}
		
		Node<E> current = head;
		
		//assign an Object type array variable to the Generic type array, a
		Object[] a1 = a;
	
		for (int i=0; i<this.size(); i++) {
			//assign each index of the array as a reference of the object in the current node
			a1[i] = current.e;
			current=current.next;
		}
		return a;
	}
	
	/**
	 * Ensures that this collection contains the specified element 
	 * @param Element e 
	 * @return boolean true if this collection contains the specified element, and false otherwise
	 */
	@Override
	public boolean add(E e) {
		Iterator<E> iterator = iterator();
		Node<E> current = new Node(); //create a pointer
		current.next = head;
		
		if (head==null) {
			//add Element e to the collection
			head=new Node<E>(e);
			size++;
			return true;
		}
		while (iterator.hasNext()) {
			current=current.next;
			//if the element is in the collection, return false
			if (iterator.next().equals(e)) {
				return false;
			} 	
		}

		current.next = new Node<E>(e);
		size++;
		return true;
	}
	/**
	 * Removes a single instance of the specified element from this collection, if it is present 
	 * @param Object o
	 * @return boolean true if Object o is in this collection, and remove the element; false is returned otherwise
	 */
	@Override
	public boolean remove(Object o) {
		   	if (head==null) {
	    		return false;
	    	}
	    	if (head.e.equals(o)) {
	    		head=head.next;
	    		size--;
	    		return true;
	    	}

	    	Node previous = head;
	    	Node current = head.next;
	    	
	    	//search for o in this collection and reduce size of collection if found
	    	while (current != null) {
	    		if (current.e.equals(o)) {
	    			previous.next=current.next;
	    			size--;
	    			return true;
	    		}
	    		previous=previous.next;
	    		current=current.next;
	    	}
			return false;
	}

	/**
	 * Returns true if this collection contains all of the elements in the specified collection
	 * @param Collection<?> c
	 * @return boolean true if this collection contains all of the elements in the specified collection, and false otherwise
	 */
	@Override
	public boolean containsAll(Collection<?> c) {
		
		//instantiate an iterator for the Collection<?> parameter 
		Iterator<?> iter = c.iterator();
		
		//return false if there is any element in c that is not contained by this collection
		while (iter.hasNext()) {
			if (!this.contains(iter.next())) {
				return false;
			}
		}
		//otherwise return true
		return true;
	}

	/**
	 * Removes all of the elements from this collection
	 */
	@Override
	public void clear() {
		//remove access to the collection  
		head=null;
		//assign the size of this collection to 0
		size=0;
	}
	
	/**
	 * Instantiates and returns and iterator object to be used to iterate over the elements in this collection
	 * @return Iterator<E> iterator object to be used to iterate over the elements in this collection 
	 */
	@Override
	public Iterator<E> iterator() {
		Iterator<E> itr = new Iter(head);
		return itr;
	}
	
	/**
	 * Iter class is used to iterate over a collection of E elements, and implements the Iterator<E> interface
	 * It is able to tell whether or not the next node in this collection exists, and can also return the next element in this collection
	 * @author Claire Zhu 
	 */
	private class Iter implements Iterator<E> {
		
		//1-param constructor
		Iter(Node<E> head) {
			this.current=head;
		}
		
		//set Node<E> variable as a reference to the head
		Node<E> current = head;
		
		/**
		 * Returns true if the next node in this collection exists
		 * @return boolean true if the next node in this collection exists, and false otherwise
		 */
		@Override
		public boolean hasNext() {
			if (current != null) {
				return true;
			}
			return false;
		}
		/**
		 * Returns the next element in this collection 
		 * @return Element value of the next element in this collection 
		 */
		@Override
		public E next() throws NoSuchElementException {
			//throw exception if at the end of this collection 
			if (current == null) throw new NoSuchElementException("You have reached the end of this collection.");
			
			//instantiate Element e reference to the element inside the current Node object
			E e = current.e;
			
			//e will also be changed once current points at the memory address of current.next
			current=current.next;
			return e;
		}
	}

	
	//Methods that do not have to be implemented for this project
	@Override
	public boolean addAll(Collection<? extends E> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
