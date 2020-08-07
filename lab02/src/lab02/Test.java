package lab02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		int[] t = {1,  3,  9,  9,  1,  3,  9,  3};
		int[] p = {1, 3, 9};
		List<Integer> ok = new ArrayList<Integer>();
		ok.add(0);
		ok.add(4);
		//System.out.println(Arrays.toString(ok.toArray()));
		
		StringAlgorithms test = new StringAlgorithms();
		int repeats = 10;
		long startTime;
		long endTime;
		long duration[][] = new long[4][repeats];
		List<Integer> result;
		
		
		for (int i = 0; i < repeats; i++) {
		
			startTime = System.nanoTime();
			result = test.naiveAlgo(t, p);
			endTime = System.nanoTime();
			if (ok.equals(result))
				duration[0][i] = (endTime - startTime);
			else throw new Exception("Incorrect result returned");		
	
			
			startTime = System.nanoTime();
			result = test.rabinKarp(t, p);
			endTime = System.nanoTime();
			if (ok.equals(result))
				duration[1][i] = (endTime - startTime);
			else throw new Exception("Incorrect result returned");		
	
			
			startTime = System.nanoTime();
			result = test.knuMoPrat(t, p);
			endTime = System.nanoTime();
			if (ok.equals(result))	
				duration[2][i] = (endTime - startTime);
			else throw new Exception("Incorrect result returned");		
	
			
			startTime = System.nanoTime();
			result = test.boyerMore(t, p);
			endTime = System.nanoTime();
			if (ok.equals(result))	
				duration[3][i] = (endTime - startTime);
			else throw new Exception("Incorrect result returned");			
		}
		// calculate final results
		long[][] finalResult = new long[4][3];
		for (int h = 0; h<4; h++) {
			long best = 99999999;
			long worst = 0;
			long average = 0;
			
			// note: first result discluded due to being an outlier
			for (int i=1; i<repeats; i++) {
				average += duration[h][i];
				if (duration[h][i]<best) best = duration[h][i];
				if (duration[h][i]>worst) worst = duration[h][i];
			}

			average = average/repeats-1;
			finalResult[h][0] = best;
			finalResult[h][1] = average;
			finalResult[h][2] = worst;
		}
		// print final results
		System.out.println("naiveAlgo best:" + finalResult[0][0] + " average:" + finalResult[0][1] + " worst: " + finalResult[0][2]);
		System.out.println("rabinKarp best:" + finalResult[1][0] + " average:" + finalResult[1][1] + " worst: " + finalResult[1][2]);
		System.out.println("knuMoPrat best:" + finalResult[2][0] + " average:" + finalResult[2][1] + " worst: " + finalResult[2][2]);
		System.out.println("boyerMore best:" + finalResult[3][0] + " average:" + finalResult[3][1] + " worst: " + finalResult[3][2]);
		
	}

}
