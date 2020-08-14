/**
 * Implements the Nearest Neighbour algorithm for the TSP, and
 * an iterative improvement method that uses 2-OPT greedily.
 * Results are returned as an array of indices into the table argument, 
 * e.g. if the table has length four, a valid result would be {2,0,1,3}. 
 */
import java.util.*;

public class NearestNeighbour
{
    private NearestNeighbour(){}

    /**
     * Returns the shortest tour found by exercising the NN algorithm 
     * from each possible starting city in table.
     * table[i][j] == table[j][i] gives the cost of travel between City i and City j.
     */
    public static int[] tspnn(double[][] table)
    {
        // COMPLETE THIS METHOD
       
        /*
        for(int i = 0; i<table.length; i++) {
            System.out.println("table " + i);
            for (int j = 0; j<table[0].length; j++) {
                System.out.print((int) table[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
        */
       
        int length = table.length;
        int[] result = new int[length];
        int[] bestResult = new int[length];
        boolean[] checked = new boolean[length];
        int startTown;
        double totalDistance = 0;
        double bestTotalDistance = 999999999;
        result[0] = 0;
        // use m to change the start town value i.e result[0]    
        for (int m = 0; m<length; m++) {
            result[0] = m;
            totalDistance = 0;
            
            
            //use k to progress through result list
            // complete one cycle and at the end fill in the next result[k] value
            for(int k=0; k<length-1; k++) {
                
                
                startTown = result[k]; // result[0] = town 0 i.e. 0 is first
                checked[startTown] = true;
                double closestDistance = 999999999;
                int closestTown = startTown;
    
                // starting from result[k], find the nearest town to that
                for(int i = 0; i<length; i++) {
                    if (table[startTown][i] == 0 ||
                        checked[i] == true) continue;
                    else if (table[startTown][i] < closestDistance) {
                            closestDistance = table[startTown][i];
                            closestTown = i;
                    }
                }
                // store results
                checked[closestTown] = true;
                if (k+1 < length) {
                    result[k+1] = closestTown;
                    totalDistance += closestDistance;
                }
                
                // print outcomes
                /*
                System.out.println("startTown: " + startTown + "  " + "closestTown: " + closestTown);
                System.out.println("closestDistance: " + closestDistance);
                System.out.println("totalDistance: " + totalDistance);
                System.out.println();
                */
                //yaass it works
            }
            totalDistance += table[result[0]][result[length-1]];
            
            
            //System.out.println("totalDistance pretest: " + totalDistance);
            //System.out.println("bestTotalDistance pretest: " + bestTotalDistance);
            // check if result for this start town is best then store if so
            if (totalDistance < bestTotalDistance) {
                bestTotalDistance = totalDistance;
                System.arraycopy(result, 0, bestResult, 0, result.length);
                //System.out.println("check passed");
            }
            
            //printtest
            /*
            System.out.println("totalDistance: " + totalDistance);
            System.out.println("bestTotalDistance: " + bestTotalDistance);
            System.out.println("Start:" + m + " totalDistance:" + totalDistance);
            System.out.println("result: " + Arrays.toString(result));
            System.out.println("bestResult: " + Arrays.toString(bestResult));
            */
            //reset for next m
            for (int n=0; n<length; n++){
                result[n] = 0;
                checked[n] = false;
            }
            totalDistance = 0;
            //System.out.println(" ");
            
        }
        
        //System.out.println("bestTotalDistance: " + bestTotalDistance);
        //System.out.println("bestResult: " + Arrays.toString(bestResult));
        return bestResult;
    }
    
    /**
     * Uses 2-OPT repeatedly to improve cs, choosing the shortest option in each iteration.
     * You can assume that cs is a valid tour initially.
     * table[i][j] == table[j][i] gives the cost of travel between City i and City j.
     */
    public static int[] tsp2opt(int[] cs, double[][] table)
    {
        // COMPLETE THIS METHOD
        return new int[] {};
    }
}
