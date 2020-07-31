// Created by Michael Sargeant
// insertion sort code adapted from geeksforgeeks.org
// CITS3001 Lab01
// July 2020

public class Insertsort {
	
	//FIELDS
	
	// CONSTRUCTOR
	public Insertsort() {
		
	}
	
	//METHODS
	int[] sort(int[] numbers) {
		int key, i, j;
		int length = numbers.length;
		for (i = 1; i<length; i++) {
			key = numbers[i];
			j = i-1;
			
			while (j>= 0 && numbers[j]>key) {
				numbers[j+1]=numbers[j];
				j=j-1;
			}
			numbers[j+1] = key;
		}
		return numbers;
	}
	
}
