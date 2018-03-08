/**
 * Jan Toma
 * CS310 Section 2
 */
package Tester;

import data_structures.RedBlackTree;

public class RedBlackTester {

	RedBlackTree<Integer, String> rb = new RedBlackTree();

	public RedBlackTester() {
		rb.add(8, "a");
		rb.add(6, "b");
		rb.add(3, "c");
		rb.add(2, "d");
		rb.add(5, "e");
		rb.add(7, "f");
		rb.add(10, "g");
		rb.add(9, "h");
		rb.add(14, "i");
		rb.add(11, "j");
		System.out.println("Contains(10): " + rb.contains(10));
		System.out.println("Contains(24): " + rb.contains(24));
		System.out.println("ValueOf(8): " + rb.getValue(8));
		System.out.println("ValueOf(232): " + rb.getValue(232));
		System.out.println("Size: " + rb.size());
		System.out.println("Empty?: " + rb.isEmpty());
		System.out.println("Height: " + rb.height());
		System.out.println("Black Nodes: " + rb.blackNodes());
		System.out.println("Most Left: " + rb.getLeftMost());
		System.out.println("Printing out the Tree \n~~~~~~~~~~~~~~~~~~\n");
		rb.print();

	}


	private static void runTests() {
		new RedBlackTester();	
	}

	public static void main(String[] a) {
		runTests();
	}
}
