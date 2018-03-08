/**
 * Jan Toma
 * CS310 Section 2
 */
package data_structures;

import java.util.Iterator;


/**
 * @author Jan Toma
 *
 * @param <K> key
 * @param <V> value
 */
public class RedBlackTree<K, V> implements RedBlackI<K, V> {

	private int size;
	public Node<K, V> root;
	
	class Node<K, V> {
		K key;
		V value;
		Node<K, V> left, right, parent;
		boolean isBlack; //True = black, False = red
		boolean isLeftChild;

		public Node(K key, V value) {
			this.key = key;
			this.value = value;
			left = right = parent = null;
			isBlack = false;
			isLeftChild = false;
		}
	}
	
	public RedBlackTree() {
		size = 0;
		root = null;
	}

	/**
	 * The method to add to the RBTree.  It will not allow duplicate additions.
	 * 
	 * @param key the key to add
	 * @param value the value associated with the key
	 */
	public void add(K key, V value) {
		Node<K,V> node = new Node<K, V>(key, value);
		if(root == null) {
			root = node;
			root.isBlack = true;
			root.parent = null;
			size++;
			return;
		}
		add(root, node);
		size++;
	}

	/**
	 * Traverses the tree by using recursions until either node.right or node.left 
	 * is null then adds it to the tree.
	 * 
	 * @param parent the current node
	 * @param newNode the node<K, V> we want to add
	 */
	private void add(Node<K, V> parent, Node<K, V> newNode) {
		if(((Comparable<K>)newNode.key).compareTo(parent.key) >= 0) {
			if(parent.right == null) {
				parent.right = newNode;
				newNode.parent = parent;
				newNode.isLeftChild = false;
				checkColor(newNode);
				return;
			}
			add(parent.right, newNode);
			return;
		}
		if(parent.left == null) {
			parent.left = newNode;
			newNode.parent = parent;
			newNode.isLeftChild = true;
			checkColor(newNode);
			return;
		}
		add(parent.left, newNode);
		return;
	}

	/**
	 * Takes in a newly added node, and checks the color and makes sure it doesn't
	 * cause any violations, if it does it will go and balance the tree.
	 * 
	 * @param node the most recent added node
	 */
	private void checkColor(Node<K, V> node) {
		if(node == root) {
			if(!root.isBlack) {
				root.isBlack = true;
			}
			return;
		}
		if(!node.isBlack && !node.parent.isBlack) {
			balanceTree(node);
		}
		if(node.parent == null) {
			checkColor(root);
			return;
		}
		checkColor(node.parent);
	}

	/**
	 * If there is a violations, the node that cause a violation will go to this 
	 * method, which will then proceed to fix the violation, it will rotate if the
	 * aunt is black, and color flip if the aunt is red.
	 * 
	 * @param node the node that caused the violation
	 */
	private void balanceTree(Node<K, V> node) {
		Node<K,V> aunt = node.parent.isLeftChild ? node.parent.parent.right : node.parent.parent.left;
		if(node.parent.isLeftChild) {	
			if(aunt == null || aunt.isBlack) {
				rotate(node); //Add the different rotate
				return;
			}
			if(aunt != null) {
				aunt.isBlack = true;
			}
			node.parent.parent.isBlack = false;
			node.parent.isBlack = true;
			return;
		}
		else {
			if(aunt == null || aunt.isBlack) {
				rotate(node);
				return;
			}
			if(aunt != null) {
				aunt.isBlack = true;
			}
			node.parent.parent.isBlack = false;
			node.parent.isBlack = true;
			return;
		}

	}

	/**
	 * Decides what rotate we should use to balance the tree.
	 * 
	 * @param node the that cause the violation
	 */
	private void rotate(Node<K, V> node) {
		if(node.isLeftChild) {
			if(node.parent.isLeftChild) {
				rightRotate(node.parent.parent);
				node.isBlack = false;
				node.parent.isBlack = true;
				if(node.parent.right != null) {
					node.parent.right.isBlack = false;
				}
				return;
			}
			rightLeftRotate(node.parent.parent);
			node.isBlack = true;
			node.right.isBlack = false;
			node.left.isBlack = false;
		}
		else {
			if(!node.parent.isLeftChild) {
				leftRotate(node.parent.parent);
				node.isBlack = false;
				node.parent.isBlack = true;
				if(node.parent.left != null) {
					node.parent.left.isBlack = false;
				}
				return;
			}
			leftRightRotate(node.parent.parent);
			node.isBlack = true;
			node.right.isBlack = false;
			node.left.isBlack = false;
		}
	}

	/**
	 * This will do a left rotate.
	 * 
	 * @param node the node we are going to rotate
	 */
	private void leftRotate(Node<K, V> node) {
		Node<K, V> temp = node.right;
		node.right = temp.left;
		if(node.right != null) {
			node.right.parent = node;
			node.right.isLeftChild = false;
		}
		if(node.parent == null) {
			root = temp;
			temp.parent = null;
		}
		else {
			temp.parent = node.parent;
			if(node.isLeftChild) {
				temp.isLeftChild = true;
				temp.parent.left = temp;
			}
			else {
				temp.isLeftChild = false;
				temp.parent.right = temp;
			}
		}
		temp.left = node;
		node.isLeftChild = true;
		node.parent = temp;
	}

	/**
	 * This will do a right rotate.
	 * 
	 * @param node the node we are going to rotate.
	 */
	private void rightRotate(Node<K, V> node) {
		Node<K, V> temp = node.left;
		node.left = temp.right;
		if(node.left != null) {
			node.left.parent = node;
			node.left.isLeftChild = true;
		}
		if(node.parent == null) {
			root = temp;
			temp.parent = null;
		}
		else {
			temp.parent = node.parent;
			if(!node.isLeftChild) {
				temp.isLeftChild = false;
				temp.parent.right = temp;
			}
			else {
				temp.isLeftChild = true;
				temp.parent.left = temp;
			}
		}
		temp.right = node;
		node.isLeftChild = false;
		node.parent = temp;	
		return;
	}

	/**
	 * This will do a left rotate first, then a right rotate.
	 * 
	 * @param node the node we are going to rotate
	 */
	private void leftRightRotate(Node<K, V> node) {
		leftRotate(node.left);
		rightRotate(node);
	}

	/**
	 * This will do a right rotate, then a left rotate.
	 * 
	 * @param node the node we are going to rotate
	 */
	private void rightLeftRotate(Node<K, V> node) {
		rightRotate(node.right);
		leftRotate(node);
	}

	/**
	 * Tests whether the RBTree contains the key.
	 * 
	 * @param key the key to look for
	 * @return whether the key is found
	 */
	public boolean contains(K key) {
		if(isEmpty()) {
			return false;
		}
		if(((Comparable<K>)root.key).compareTo(key) == 0) {
			return true;
		}
		return contains(root, key);
	}

	/**
	 * This will traverse the tree using recursion until the key is found, if the
	 * key is not found, it will return null.
	 * 
	 * @param node the current position in the tree
	 * @param key the key we want to find
	 * @return true if the tree contains the key, else false
	 */
	private boolean contains(Node<K, V> node, K key) {
		if(node == null) {
			return false;
		}
		if(((Comparable<K>)key).compareTo(node.key) == 0) {
			return true;
		}
		if(((Comparable<K>)key).compareTo(node.key) < 0) {
			return contains(node.left, key);
		}
		return contains(node.right, key);
	}

	/**
	 * Get the value associated with a given key.
	 * 
	 * @param key the key to get the value for
	 * @return the current value
	 */
	public V getValue(K key) {
		if(isEmpty()) {
			return null;
		}
		if(((Comparable<K>)root.key).compareTo(key) == 0) {
			return root.value;
		}
		return getValue(root, key);
	}

	/**
	 * This will traverse the tree by using recursion until the key is found, then
	 * it will return the value of the key.
	 * 
	 * @param node the current position in the tree
	 * @param key the key we want to find
	 * @return the value of the key we are looking for
	 */
	private V getValue(Node<K, V> node, K key) {
		if(node == null) {
			return null;
		}
		if(((Comparable<K>)key).compareTo(node.key) == 0) {
			return node.value;
		}
		if(((Comparable<K>)key).compareTo(node.key) < 0) {
			return getValue(node.left, key);
		}
		return getValue(node.right, key);
	}

	/**
	 * Returns the number of elements in the RBTree
	 * 
	 * @return the number of elements in the tree
	 */
	public int size() {
		return size;
	}

	/**
	 * Makes the tree empty.
	 */
	public void makeEmpty() {
		root = null;
		size = 0;
	}

	/**
	 * Test whether the RBTree is empty.
	 * 
	 * @return true if the tree is empty, else false.
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * The height of the tree. Recall that a tree with only a root 
	 * node has height 0.
	 *  
	 * @return the height of the tree at the root node
	 */
	public int height() {
		if(root == null) {
			return 0;
		}
		return height(root) - 1;
	}

	/**
	 *  The height of the tree. Recall that a tree with only a root 
	 * node has height 0.
	 * 
	 * @param node the node we want to start at
	 * @return the height of the tree
	 */
	private int height(Node<K, V> node) {
		if(node == null) {
			return 0;
		}
		int leftHeight = height(node.left) + 1;
		int rightHeight = height(node.right) + 1;
		if(leftHeight > rightHeight) {
			return leftHeight;
		}
		return rightHeight;
	}

	/**
	 * Returns how many blackNodes the tree has.
	 * 
	 * @return the number of black nodes
	 */
	public int blackNodes() {
		return blackNodes(root);
	}

	/**
	 * Returns how many blackNodes the tree has.
	 * 
	 * @param node the node we want to start at
	 * @return the number of black nodes the tree has
	 */
	public int blackNodes(Node<K, V> node) {
		if(node == null) {
			return 1;
		}
		int rightBlackNodes = blackNodes(node.right);
		int leftBlackNodes = blackNodes(node.left);
		try {
			if(rightBlackNodes != leftBlackNodes) {
				throw new IllegalArgumentException("ERROR: THE TREE IS NOT VALID");
			}
			if( leftBlackNodes < rightBlackNodes) {
				leftBlackNodes = rightBlackNodes;
			}
		}
		catch (IllegalArgumentException e) {
			System.out.println(e);			
		}
		if(node.isBlack) {
			leftBlackNodes++;
		}
		return leftBlackNodes;
	}

	/**
	 * Gets the left most key.
	 * 
	 * @return the left most key
	 */
	public K getLeftMost() {
		if(isEmpty()) {
			return null;
		}
		Node<K, V> current = root;
		while(current.left != null) {
			current = current.left;
		}
		return current.key;
	}

	/**
	 * An iterator for all the keys in the RBTree. This will
	 * iterate over the keys using InOrder Traversal
	 * 
	 * @see java.lang.Iterable#iterator()
	 */
	public Iterator<K> iterator() {
		return new iteratorHelper<K>();
	}

	class iteratorHelper<T> implements Iterator<T> {
		T[] keys;
		int position;

		public iteratorHelper() {
			keys = (T[]) new Object[size];
			position = 0;			
			getKey(root);
			position = 0;
		}

		private void getKey(Node<K, V> node) {
			if(node == null) {
				return;
			}
			getKey(node.left);
			keys[position++] = (T) node.key;
			getKey(node.right);
		}

		@Override
		public boolean hasNext() {
			return position < keys.length;
		}

		@Override
		public T next() {
			if(!hasNext()) {
				return null;
			}
			return keys[position++];
		}

	}

	/**
	 * Prints out the tree in InOrder Traversal.
	 */
	public void print() {	
		if(isEmpty()) {
			return;
		}
		print(root);
	}

	/**
	 * Prints out the tree in InOrder Traversal using recursion.
	 * 
	 * @param node the position we want to start at.
	 */
	private void print(Node<K ,V> node) {
		if(node.left != null) {
			print(node.left);
		}
		if(node == root && node.isBlack) {
			System.out.println(node.key + " " + "B:Root");
		}
		else if(node.isBlack) {
			System.out.println(node.key + " " + "B" + " " + node.isLeftChild);
		}
		else {
			System.out.println(node.key + " " + "R" + " " + node.isLeftChild);
		}
		if(node.right != null) {
			print(node.right);
		}
	}
}
