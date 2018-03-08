/**
 * Jan Toma
 * CS310 Section 2
 */
package data_structures;

import java.util.Iterator;

/**
 * The Hash data structure has O(1) time complexity (best case) for add, remove, and find
 * for an object in the data structure. The methods in the Hash data structure are defined
 * by the HashI interface. The Hash consists of an array of Linked Lists,
 * the Linked Lists are defined by the HashListI interface.
 * 
 * @author Jan Toma CS310
 *
 * @param <K> The key for entries in the hash
 * @param <V> The value for entries in the hash
 */

public class Hash<K, V> implements HashI<K, V> {

	LinkedList<HashElement<K, V>> [] hashArr;

	class HashElement<K, V> implements Comparable<HashElement<K, V>> {

		K key; 
		V Value;

		public HashElement(K key, V value) {
			this.key = key;
			this.Value = value;
		}

		@Override
		public int compareTo(HashElement<K, V> o) {
			return ((Comparable<K>)o.key).compareTo(this.key);
		}		
	}

	int numElements, tableSize;
	double maxLoadFactor;

	public Hash(int tableSize) {
		this.tableSize = tableSize;
		maxLoadFactor = 0.75;
		numElements = 0;
		hashArr = (LinkedList<HashElement<K, V>> []) new LinkedList[tableSize];
		for(int i = 0; i < tableSize; i++) { // for each loop
			hashArr[i] = new LinkedList<HashElement<K, V>>();
		}
	}

	 /**  
	 * Adds the given key/value pair to the dictionary.  Returns 
	 * false if the dictionary is full, or if the key is a duplicate. 
	 * Returns true if addition succeeded. 
	 *  
	 * @param key the key to add.
	 * @param value the value associated with the key.
	 * @return true if the key/value are added to the hash.
	 */
	public boolean add(K key, V value) {
		if(loadFactor() >= maxLoadFactor) {
			resize(tableSize * 2);
		}
		HashElement<K, V> hashElement = new HashElement<K,V>(key, value);
		int hashVal = (key.hashCode() & 0x7FFFFFFF) % tableSize;
		hashArr[hashVal].add(hashElement);
		numElements++;
		return true;
	}

	/**
	 * Deletes the key/value pair identified by the key parameter. 
	 * Returns true if the key/value pair was found and removed, 
	 * otherwise returns false.
	 *  
	 * @param key the key to remove.
	 * @return true if it successfully deletes it, else false.
	 */
	public boolean remove(K key) {
		if(isEmpty()) {
			return false;
		}
		int hashVal = (key.hashCode() & 0x7FFFFFFF) % tableSize;
		for(HashElement<K, V> hashElement : hashArr[hashVal]) {
			if(key.equals(hashElement.key)) {
				hashArr[hashVal].remove(hashElement);
				numElements--;
				return true;
			}
		}
		return false;
	}

	/**
	 * Change the value associated with an existing key.
	 * 
	 * @param key The key to change.
	 * @param value The Value to change.
	 * @return true, if it successfully changes the value, else false.
	 */
	public boolean changeValue(K key, V value) {
		int hashVal = (key.hashCode() & 0x7FFFFFFF) % tableSize;
		if(getValue(key) == null) {
			add(key, value);
			return true;
		}
		for(HashElement<K, V> hashElement : hashArr[hashVal]) {
			remove(key);
			add(key, value);
			return true;
		}
		return false;
	}

	/**
	 * Test whether the hash has the entry associated with the key.
	 * 
	 * @param key the key to look for.
	 * @return true if it find the key, else false.
	 */
	public boolean contains(K key) {
		int hashVal = (key.hashCode() & 0x7FFFFFFF) % tableSize;
		for(HashElement<K, V> hashElement : hashArr[hashVal]) {
			if(((Comparable<K>)key).compareTo(hashElement.key) == 0) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns the value associated with the parameter key. 
	 * Returns null if the key is not found or the dictionary is empty.
	 *  
	 * @param key the key to find the value for
	 * @return the value
	 */
	public V getValue(K key) {
		if(isEmpty()) {
			return null;
		}
		int hashVal = (key.hashCode() & 0x7FFFFFFF) % tableSize;
		for(HashElement<K, V> hashElement : hashArr[hashVal]) {
			if(((Comparable<K>)key).compareTo(hashElement.key) == 0){
				return hashElement.Value;
			}
		}
		return null;
	}

	/**
	 * Returns the number of key/value pairs currently stored in the dictionary. 
	 * 
	 * @return the number of Elements that are stored in the dictionary.
	 */
	public int size() {
		return numElements;
	}

	/**
	 * Returns true if the dictionary is empty. 
	 * 
	 * @return true if the dictionary is empty, else false.
	 */
	public boolean isEmpty() {
		return numElements == 0;
	}

	/**
	 * Makes the dictionary empty.
	 */
	public void makeEmpty() {
		for(LinkedList<HashElement<K, V>> k : hashArr){
			k.makeEmpty();
		}
		numElements = 0;
	}

	/**
	 * Returns the current load factor of the dictionary (lambda).
	 * 
	 * @return the loadFactor
	 */
	public double loadFactor() {
		return numElements/(double)tableSize;
	}

	/**
	 * Get the maximum load factor (at which point we need to resize).
	 * 
	 * @return the maximum load factor of the hash.
	 */
	public double getMaxLoadFactor() {
		return maxLoadFactor;
	}

	/**
	 * Set the maximum load factor (at which point we need to resize).
	 * 
	 * @param loadfactor the maximum load factor.
	 */
	public void setMaxLoadFActor(double loadfactor) {
		maxLoadFactor = loadfactor;
	}

	/**
	 * Resizes the dictionary.
	 * 
	 * @param newSize the size of the new dictionary.
	 */
	public void resize(int newSize) {
		LinkedList<HashElement<K, V>>[] newArr = (LinkedList<HashElement<K, V>>[]) new LinkedList[newSize];
		for(int i = 0; i < newSize; i++) {
			newArr[i] = new LinkedList<HashElement<K, V>>();
		}
		for(K key: this) {
			V value = getValue(key);
			HashElement<K, V> hashElement = new HashElement<K, V>(key, value);
			int hashVal = (key.hashCode() & 0x7FFFFFFF) % newSize;
			newArr[hashVal].add(hashElement);
		}
		hashArr = newArr;
		tableSize = newSize;
	}
	
	/**
	 * Returns an Iterator of the keys in the dictionary, in ascending 
	 * sorted order. 
	 */
	public Iterator<K> iterator() {
		return new KeyIteratorHelper<K>(); 	
	}

	class KeyIteratorHelper<T> implements Iterator<T> {
		T[] keys;
		int idx;

		public KeyIteratorHelper() {
			int j = 0;
			keys = (T[]) new Object[numElements];
			for(int i = 0; i < tableSize; i++) {
				LinkedList<HashElement<K, V>> list = hashArr[i];
				for(HashElement<K, V> n: list) {
					keys[j++] = (T) n.key;
				}
			}
			idx = 0;
		}

		@Override
		public boolean hasNext() {
			return idx < keys.length;
		}

		@Override
		public T next() {
			if(!hasNext()) {
				return null;
			}
			return keys[idx++];
		}
	}
}
