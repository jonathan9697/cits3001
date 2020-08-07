package lab02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Michael Sargeant
 * CITS3001 Lab02
 * August 2020
 * 
 * knuthMorrisPratt() code adapted from geeksforgeeks.com
 * 
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
	List<Integer> naiveAlgo(int[] t, int[] p) {
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
		
		//Rabin-Karp matching
		//check initial shift
		int z = 0;
		for(int j=0; j<m; j++) z = z*10 + t[j];
		if (z == pInt) result.add(0);
		//check remaining shifts
		for (int s=1; s<n-m+1; s++) {
			z = (int) (z%(Math.pow(10, m-1)) * 10 + t[s+m-1]);
			if (z != pInt) continue;
			else result.add(s);
		}
		return result;
	}
	
	
	/*
	 * applies Knuth-Morris-Pratt algorithm
	 * to find a pattern in text
	 * @param t the text to search
	 * @param p the pattern to be found
	 * @returns result list of shifts
	 */
	List<Integer> knuMoPrat(int[] t, int[] p) {
		List<Integer> result = new ArrayList<Integer>();
		int m = p.length;
		int n = t.length;
		int lps[] = new int[m];
		int j = 0;
		int len = 0;
		int i = 1;
		lps[0] = 0;
		
		// preprocessing		
		while (i < m) {
			if (p[i] == p[len]) {
				len++;
				lps[i] = len;
				i++;
			}
			else {
				if (len !=0) {
					len = lps[len -1];
				}
				else {
					lps[i] = len;
					i++;
				}
			}
		}
		
		// matching
		i = 0;
		j = 0;
		while (i < n) {
			if (p[j] == t[i]) {
				j++;
				i++;
			}
			if (j==m) {
				result.add(i-j);
				j = lps[j-1];
			}
			else if (i<n && p[j] != t[i]) {
				if (j !=0) j = lps[j-1];
				else i = i+1;
			}
		}
		return result;
	}
	
	
	
	
	/*
	 * 
	 * applies Boyer-Moore algorithm
	 * to find a pattern in text
	 * @param t the text to search
	 * @param p the pattern to be found
	 * @returns result list of shifts
	 */
	List<Integer> boyerMore (int[] t, int[] p) {
		List<Integer> result = new ArrayList<Integer>();
		int n = t.length;
		int m = p.length;
		
		
		for (int s=0; s<=n-m; s++) {
			for (int j = m-1; j>=0; j--) {
				if (t[s+j] != p[j]) continue;
				if (j==0) result.add(s);
			}
		}
		return result;
	}

	/*
	 * main method for testing
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] t = {1,  3,  9,  9,  1,  3,  9,  3};
		int[] p = {3};
		
		StringAlgorithms test = new StringAlgorithms();
		long startTime;
		long endTime;
		long duration;
		List<Integer> result;
		
		startTime = System.nanoTime();
		result = test.naiveAlgo(t, p);
		endTime = System.nanoTime();
		duration = (endTime - startTime);
		System.out.println("naiveAlgo: " + Arrays.toString(test.naiveAlgo(t, p).toArray()) + " time: " + duration);
		
		startTime = System.nanoTime();
		result = test.rabinKarp(t, p);
		endTime = System.nanoTime();
		duration = (endTime - startTime);
		System.out.println("rabinKarp: " + Arrays.toString(test.rabinKarp(t, p).toArray()) + " time: " + duration);
		
		startTime = System.nanoTime();
		result = test.knuMoPrat(t, p);
		endTime = System.nanoTime();
		duration = (endTime - startTime);
		System.out.println("knuMoPrat: " + Arrays.toString(test.knuMoPrat(t, p).toArray()) + " time: " + duration);
		
		startTime = System.nanoTime();
		result = test.boyerMore(t, p);
		endTime = System.nanoTime();
		duration = (endTime - startTime);
		System.out.println("boyerMore: " + Arrays.toString(test.boyerMore(t, p).toArray()) + " time: " + duration);
		
	}

}
