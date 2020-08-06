package lab02;

/*
 * Author: Michael Sargeant
 * for CITS3001
 * August 2020
 * Lab02
 */

public class MaxVal {
		
	/*
	 * uses a naive algorithm
	 * to return the highest value of
	 * A[s]-A[r]+A[q]-A[p]
	 * where: s > r > q > p
	 * @param array the set of integers
	 * @return highest value
	 */
	public int naive(int[] array) {
		int max = 0;
		for(int s=array.length-1; s>=3; s--)
			for(int r=s-1; r>=3; r--)
				for(int q=r-1; q>=2; q--)
					for(int p=q-1; p>=1; p--) 
						if (array[s]-array[r]+array[q]-array[p] > max)
							max = array[s]-array[r]+array[q]-array[p];
		return max;
	}
	
	/*
	 * uses a dynamic programming algorithm
	 * to return the highest value of
	 * A[s]-A[r]+A[q]-A[p]
	 * where: s > r > q > p
	 * @param array the set of integers
	 * @return highest value
	 */
	public int dynam(int[] array) {
		int length = array.length;
		
		//first: store highest value including and and above current
		int[] first = new int[length];
		first[length-1] = array[length-1];
		for (int i=length-2; i>=0; i--) {
			if (first[i+1] >= array[i]) first[i] = first[i+1];
			else first[i] = array[i];
			
		}
		
		//second: store highest value, subtracting from below 
		int[] second = new int[length];
		for (int i=length-2; i>=0; i--) {
			if (first[i+1] - array[i] >= second[i+1]) {
				second[i] = first[i+1] - array[i];
			}
			else second[i] = second[i+1];
		}
		
		//third: store highest value, adding from below 
		int[] third = new int[length];
		for (int i=length-3; i>=0; i--) {
			if (second[i+1] + array[i] > third[i+1]) {
				third[i] = second[i+1] + array[i]; 
			}
			else third[i] = third[i+1];
		}
		
		//fourth: store highest value, subtracting from below
		int[] fourth = new int[length];
		for (int i = length-4; i>=0; i--) {
			if (third[i+1] - array[i] > fourth[i+1]) {
				fourth[i] = third[i+1] - array[i];
			}
			else fourth[i] = fourth[i+1];
		}
		return fourth[0];
		
	}
	
	public static void main(String[] args) {
		
		int[] array1 = {22, 3,  9, 10,  1, 30, 40, 33};
		int[] array2 = {9,  9,  9, 99,  1, 98,  2, 97};
		
		MaxVal test = new MaxVal();
		
		long startTime;
		long endTime;
		long duration;
		int result;
		
		startTime = System.nanoTime();
		result = test.naive(array1);
		endTime = System.nanoTime();
		duration = (endTime - startTime);
		System.out.println("naiveTest1: " + result + " time: " + duration);
		
		startTime = System.nanoTime();
		result = test.dynam(array1);
		endTime = System.nanoTime();
		duration = (endTime - startTime);
		System.out.println("dynamTest1: " + result + " time: " + duration);
		
		startTime = System.nanoTime();
		result = test.naive(array2);
		endTime = System.nanoTime();
		duration = (endTime - startTime);
		System.out.println("naiveTest2: " + result + " time: " + duration);
		
		startTime = System.nanoTime();
		result = test.dynam(array2);
		endTime = System.nanoTime();
		duration = (endTime - startTime);
		System.out.println("dynamTest2: " + result + " time: " + duration);
		
	}
}