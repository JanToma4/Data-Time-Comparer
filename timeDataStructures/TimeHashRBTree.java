/**
 * Jan Toma
 * CS310 Section 2
 */
package timeDataStructures;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.TreeMap;

import data_structures.Hash;
import data_structures.HashI;
import data_structures.LinkedList;
import data_structures.RedBlackTree;
import dns_resolver.IPAddress;
import dns_resolver.URL;
import exceptions.FileFormatException;

public class TimeHashRBTree {
	
	public static void main(String[] args) throws FileFormatException {
		LinkedList<IPAddress> ll = new LinkedList<>();
		Hash<IPAddress, URL> hash = new Hash<IPAddress, URL>(100);
		RedBlackTree<IPAddress, URL> rb = new RedBlackTree<>();
		HashMap<IPAddress, URL> hm = new HashMap<>();
		TreeMap<IPAddress, URL> t = new TreeMap<>();

//		String filename = "src/data/top-250k.ip";
		String filename = "src/data/top-1m.ip";
		BufferedReader br = null;
		String line;
		long start, stop, time;
		
		// Adding IPAddress objects into my Linked List to Compare later
		
		try {
			br = new BufferedReader(new FileReader(filename));

		} catch (FileNotFoundException e) {
			System.err.println("Error: File Not Found");
			e.printStackTrace();
			System.exit(1);
		}
		
		try{
			while((line = br.readLine()) != null) {
				String [] temp = line.split("\t");
				if(temp.length != 2) {
					throw new FileFormatException ("Error: There is something wrong with the file");
				}
				IPAddress ip = new IPAddress(temp[1]);
				ll.add(ip);
			}
		}
		catch(IOException e){

		}
		// Beginning of My Hash Table && Adding to my hash
		try {
			br = new BufferedReader(new FileReader(filename));

		} catch (FileNotFoundException e) {
			System.err.println("Error: File Not Found");
			e.printStackTrace();
			System.exit(1);
		}
		try{
			start = System.currentTimeMillis();
			while((line = br.readLine()) != null) {
				String [] temp = line.split("\t");
				if(temp.length != 2) {
					throw new FileFormatException ("Error: There is something wrong with the file");
				}
				URL u = new URL(temp[0]);
				IPAddress ip = new IPAddress(temp[1]);
				hash.add(ip, u);
			}
			stop = System.currentTimeMillis();
			time = stop - start;
			System.out.println("Adding to my Hash, it takes " + time + " Milliseconds");
		}
		catch(IOException e){

		}
		// Comparing my hash
		start = System.currentTimeMillis();
		for(IPAddress ip : ll) {
			hash.contains(ip);
			if(hash.contains(ip) == false) {
				System.out.println("ERROR");
				System.exit(1);
			}
		}
		stop = System.currentTimeMillis();
		time = stop - start;
		System.out.println("Comparing my hash, it takes " + time + " Milliseconds");

		hash.makeEmpty();
		// Beginning of My RedBlack Tree && Adding to it		
		try {
			br = new BufferedReader(new FileReader(filename));

		} catch (FileNotFoundException e) {
			System.err.println("Error: File Not Found");
			e.printStackTrace();
			System.exit(1);
		}
		try{
			start = System.currentTimeMillis();
			while((line = br.readLine()) != null) {
				String [] temp = line.split("\t");
				if(temp.length != 2) {
					throw new FileFormatException ("Error: There is something wrong with the file");
				}
				URL u = new URL(temp[0]);
				IPAddress ip = new IPAddress(temp[1]);
				rb.add(ip, u);
			}
			stop = System.currentTimeMillis();
			time = stop - start;
			System.out.println("Adding to my RedBlackTree, it takes " + time + " Milliseconds");
		}
		catch(IOException e){

		}
		// Comparing my RedBlackTree
		start = System.currentTimeMillis();
		for(IPAddress ip : ll) {
			rb.contains(ip);
			if(rb.contains(ip) == false) {
				System.out.println("ERROR");
				System.exit(1);
			}
		}
		stop = System.currentTimeMillis();
		time = stop - start;
		System.out.println("Comparing my RedBlackTree, it takes " + time + " Milliseconds");

		rb.makeEmpty();
		// Beginning of Java Hash Map && Adding to it		
		try {
			br = new BufferedReader(new FileReader(filename));

		} catch (FileNotFoundException e) {
			System.err.println("Error: File Not Found");
			e.printStackTrace();
			System.exit(1);
		}
		try{
			start = System.currentTimeMillis();
			while((line = br.readLine()) != null) {
				String [] temp = line.split("\t");
				if(temp.length != 2) {
					throw new FileFormatException ("Error: There is something wrong with the file");
				}
				URL u = new URL(temp[0]);
				IPAddress ip = new IPAddress(temp[1]);
				hm.put(ip, u);
			}
			stop = System.currentTimeMillis();
			time = stop - start;
			System.out.println("Adding to Java HashMap, it takes " + time + " Milliseconds");
		}
		catch(IOException e){

		}
		// Comparing the Java HashMap
		start = System.currentTimeMillis();
		for(IPAddress ip : ll) {
			hm.containsKey(ip);
			if(hm.containsKey(ip) == false) {
				System.out.println("ERROR");
				System.exit(1);
			}
		}
		stop = System.currentTimeMillis();
		time = stop - start;
		System.out.println("Comparing the Java HashMap, it takes " + time + " Milliseconds");

		hm.clear();
		// Beginning of Java TreeMap && Adding to it	
		try {
			br = new BufferedReader(new FileReader(filename));

		} catch (FileNotFoundException e) {
			System.err.println("Error: File Not Found");
			e.printStackTrace();
			System.exit(1);
		}
		try{
			start = System.currentTimeMillis();
			while((line = br.readLine()) != null) {
				String [] temp = line.split("\t");
				if(temp.length != 2) {
					throw new FileFormatException ("Error: There is something wrong with the file");
				}
				URL u = new URL(temp[0]);
				IPAddress ip = new IPAddress(temp[1]);
				t.put(ip, u);
			}
			stop = System.currentTimeMillis();
			time = stop - start;
			System.out.println("Adding to Java TreeMap, it takes " + time + " Milliseconds");
		}
		catch(IOException e){

		}
		// Comparing my Java TreeMap
		start = System.currentTimeMillis();
		for(IPAddress ip : ll) {
			t.containsKey(ip);
			if(t.containsKey(ip) == false) {
				System.out.println("ERROR");
				System.exit(1);
			}
		}
		stop = System.currentTimeMillis();
		time = stop - start;
		System.out.println("Comparing the Java TreeMap, it takes " + time + " Milliseconds");

		t.clear();
		
		
	}
}
