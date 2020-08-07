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
	
	/*
	 * applies naive algorithm to
	 * find pattern within text
	 * 
	 * @param t the text to search
	 * @param p the pattern to find
	 * @returns result list of shifts
	 * 
	 */
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
	
	/*
	 * applies Rabin-Karp algorithm to
	 * find pattern within text
	 * 
	 * @param t the text to search
	 * @param p the pattern to find
	 * @returns result list of shifts
	 * 
	 */
	List<Integer> rabinKarp(int[] t, int[] p) {
		int n = t.length;
		int m = p.length;
		List<Integer> result=new ArrayList<Integer>();
		
		//Rabin-Karp pre-processing
		int pInt = 0;
		for (int j=0; j<m; j++) {
			pInt = pInt*10 + p[j];
		}
		System.out.println("pInt: " + pInt);
		
		//Rabin-Karp matching
		for (int s=0; s<n-m+1; s++) {
				int z = 0;
				for(int j=0; j<m; j++) {
					z = z*10 + t[j+s];
				}
				if (z != pInt) continue;
				else result.add(s);
		}
		return result;
	}
	
	

	/*
	 * main method for testing
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] t = {1,  3,  9,  9,  1,  3,  9,  3};
		int[] p = {3, 9};
		
		StringAlgorithms test = new StringAlgorithms();
		System.out.println("naive:     " + Arrays.toString(test.naive(t, p).toArray()));
		System.out.println("rabinKarp: " + Arrays.toString(test.rabinKarp(t, p).toArray()));

	}

}
