package lab02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Michael Sargeant
 * CITS3001 Lab02
 * August 2020
 *
 * Task:
 * Implement and analyse each of the pattern-matching
 * algorithms from Lecture 3:
 * 	a) the naive algorithm
 * 	b) Rabin-Karp (the basic version, with m<=8)
 * 	c) Knuth-Morris-Pratt
 * 	d) Boyer-Moore
 * 
 */
public class StringAlgorithms {

	//FIELDS
	
	//CONSTRUCTOR
	
	//METHODS
	List<Integer> naive(int[] t, int[] p) {
		
		int n = t.length;
		int m = p.length;
		List<Integer> result=new ArrayList<Integer>();
		for (int s=0; s<n-m+1; s++) {
			for (int j=0; j<m; j++) {
				if (t[s+j] != p[j]) break;
				else if(j==m-1) result.add(s);
			}
		}
		return result;
	}
	
	
	/**
	 * @param args
	 */
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] t = {1,  3,  9,  9,  1,  3,  9,  3};
		int[] p = {3, 9};
		
		StringAlgorithms test = new StringAlgorithms();
		System.out.println(Arrays.toString(test.naive(t, p).toArray()));

	}

}
