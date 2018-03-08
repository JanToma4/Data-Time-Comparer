/**
 * Jan Toma
 * CS310 Section 2
 */
package data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * The linked list for our hash will only implement the
 * methods in the HashListI interface, a reduced set of
 * methods compared to the linked list from Assignment 1.
 * 
 * @author Jan Toma CS310
 *
 */
public class LinkedList<E> implements HashListI<E> {

	private Node<E> head, tail;
	private int size;

	private class Node<E> {
		E data;
		Node<E> next;
		Node<E> previous;
		
		public Node(E data) {
			this.data = data;
			next = null;
		}
	}

	public LinkedList() {
		head = null;
		tail = null;
		size = 0;
	}

	/**
	 * Add an object to the list at this position.
	 * 
	 * @param obj - the thing to be added.
	 */
	public void add(E obj) {
		addFirst(obj);
	}
	
	/**
	 * Adds an object to the beginning of the list.
	 * 
	 * @param obj the object to be added to the list.
	 */
	public void addFirst(E obj) {		
		Node<E> newNode = new Node<E>(obj);
		if(head == null) {
			tail = newNode;
		}
		newNode.next = head;
		head = newNode;
		size++;
	}
	
	/**
	 * Adds an object to the end of the list.
	 * 
	 * @param obj the object to be added to the list.
	 */
	public void addLast(E obj) {
		Node<E> newNode = new Node<E>(obj);
		if(head == null) {
			head = tail = newNode;
			size++;
			return;
		}
		tail.next = newNode;
		tail = newNode;
		size++;
	}

	/**
	 * Remove an object from the list
	 * 
	 * @param obj The object to remove.
	 * @return The object removed.
	 */
	public E remove(E obj) {
		Node<E> previous = null;
		Node<E> current = head;
		while(current != null) {
			if(((Comparable<E>)obj).compareTo(current.data) == 0) {
				if(current == head) {
					return removeFirst();
				}
				if(current == tail) {
					return removeLast();
				}
				size--;
				previous.next = current.next;
				return current.data;
			}
			previous = current;
			current = current.next;
		}
		return null;
	}

	/**
	 * Removes the first Object in the list and returns it.
	 * Returns null if the list is empty.
	 * 
	 * @return the object removed.
	 */
	public E removeFirst() {
		if(head == null) {
			size = 0;
			return null;
		}
		E temp = head.data;
		if(head == tail) {
			head = tail = null;
			size = 0;
			return temp;
		}
		head = head.next;
		size--;
		return temp;
	}

	/**
	 * Removes the last Object in the list and returns it.
	 * Returns null if the list is empty.
	 * 
	 * @return the object removed.
	 */
	public E removeLast() {
		Node<E> previous = null;
		Node<E> current = head;

		if(head == null) {
			size = 0;
			return null;
		}
		if(head == tail) {
			return removeFirst();
		}
		while(current.next != null) {
			previous = current;
			current = current.next;
		}
		previous.next = null;
		tail = previous;	
		size--;
		return current.data;
	}

	/**
	 * Make the list empty
	 */
	public void makeEmpty() {
		head = null;
		size = 0;
	}

	/**
	 * Is the list empty?
	 * 
	 * @return true if the list is empty.
	 */
	public boolean isEmpty() {
		return head == null;
	}

	/**
	 * The current number of elements in the list.
	 * 
	 * @return the size of the list.
	 */
	public int size() {
		return size;
	}

	/**
	 * Does the list contain this object.
	 * 
	 * @param obj The object to look for.
	 * @return True if the list contains it.
	 */
	public boolean contains(E obj) {
		Node<E> temp = head;
		while(temp != null) {
			if(((Comparable<E>)temp.data).compareTo(obj) == 0) {
				return true;
			}
			temp = temp.next;
		}	
		return false;
	}

	class iteratorHelper implements Iterator<E> {
		Node<E> iter;

		public iteratorHelper() {
			iter = head;
		}
		public boolean hasNext() {
			return iter != null;
		}
		public E next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			E value = iter.data;
			iter = iter.next;
			return value;
		}
	}
	
	/**
	 * An iterator for the list.
	 */
	public Iterator<E> iterator() {
		return new iteratorHelper(){			
		};
	}

}
