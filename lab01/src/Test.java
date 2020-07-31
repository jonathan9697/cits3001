// Created by Michael Sargeant
// insertion sort code adapted from geeksforgeeks.org
// CITS3001 Lab01
// July 2020

import java.util.Random;

/*
 * The testing framework should generate lots of lists
 * of random numbers of various lengths, then for each
 * it should call insertsort and:
 * 		a) check the result list has the same elements
 * 			as the original
 * 		b) check that the result list is sorted in
 * 			ascending order
 * 		c) record the time take for the sorting
 * 		d) tabulate the times against the lengths of the lists
 */

public class Test {
	
	//FIELDS
	// maximum value of integers in array
	final static int maxValue  = 10;
	// maximum length of array
	final static int maxLength = 20;
	// number of test to perform
	final static int testCount = 5;
	
	//CONSTRUCTOR
	
	public Test() {
	}
	
	//METHODS
	
	// creates a array of random ints
	// @length length of array
	// @return created array
	public int[] makeList(int length) {
		Random objGenerator = new Random();
		int[] randomList = new int[length];
		for (int i =0; i<length; i++) randomList[i] = objGenerator.nextInt(maxValue);;
		return randomList;
	}
	
	// checks lists contain same elements
	//@param original the original list
	//@param result the result list
	//@return true iff both lists contain same elements
	public boolean elementTest(int[] list1, int[] list2) {
		boolean[] checked1 = new boolean[list1.length];
		boolean[] checked2 = new boolean[list2.length];
		int checkedCount = 0;
		for (int i = 0; i < list1.length; i++ ) {
			for (int j = 0; j < list2.length; j++) {
				if (list1[i] == list2[j] && 
					checked1[i] == false &&
					checked2[j] == false) {
						checked1[i] = true;
						checked2[j] = true;
						checkedCount++;
						if (checkedCount == list1.length &&
							checkedCount == list2.length) return true;
						else j = list2.length-1;
				}
			}
		}
		return false;
	}
	
	
	// checks list is sorted in ascending order
	// @param list the list to be checked
	// @return true iff list is sorted
	public boolean sortTest(int[] list) {
		int length = list.length;
		int i = 0;
		while (i < length-1) {
			if (list[i] > list[i+1]) return false;
			else i++;
		}
		return true;
	}
	
	// sorts list and run checks and returns the time
	// @param list the list to be sorted
	// @return duration time taken to sort or -1 if tests fail
	public int sortTimer (int[] list) {
        long startTime, endTime, duration;
        Insertsort newInsertsort = new Insertsort();
        int[] list1 = list;
		startTime = System.nanoTime();
		newInsertsort.sort(list);
		endTime = System.nanoTime();
		duration = (endTime-startTime);
		int[] list2 = list;
		if (!sortTest(list2) && !elementTest(list1, list2)) 
			return -1;
		else return (int) duration;		
	}

	
	// tabulates times against lists
	// @param listCount number of lists
	// @return array times and lengths
	public int[][] tabulate(int listCount) {
		Random objGenerator = new Random();
		int[][] table = new int[listCount][2];
		for (int i = 0; i < listCount; i++) {
			int length = objGenerator.nextInt(maxLength);
			table[i][0] = length;
			table[i][1] = sortTimer(makeList(length));
		}
		return table;
	}
	
	
	public static void main(String[] args) {
        Test tester = new Test();
        int[][] table = tester.tabulate(testCount);
        //Print Results
        for (int i = 0; i<table.length; i++) {
        	System.out.print("length: " + table[i][0]);
        	System.out.println("  time: " + table[i][1]);
        }
	}

}
