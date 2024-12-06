package desiqna;

import java.util.HashSet;
import java.util.Set;

public class SubsetOfSubsetSum_Atlassian_OA {
    /*
        Session Link : https://docs.google.com/document/d/14Q6kK6-utuOEhLUpJ7cr9FMV2vcT3DNJBGl01QkjUDY/edit?tab=t.0

       Observation : First you select all the subsets from array whose sum is K
       now -> select a subset of those subset and tell me if it is possible to reach a particular sum value
       and finally report how many different sum values are possible.
       dp[i][j][k] = consider the first “i” elements; is there a subset with sum “j” if yes;
       if there is a subset of this subset with sum k if yes print true.
    */

    public static void main(String[] args) {
        int arr[]={5,6,1,10,12,2};
        int K=18;
        int nextSum=10;
       int result = findDifferntSubset(arr,K,nextSum);
       System.out.println("Total size: "+result);
    }
    private static int findDifferntSubset(int arr[],int K,int totalSum){
        int n = arr.length;
        // 3D DP table
        boolean[][][] dp = new boolean[n + 1][K + 1][totalSum + 1];

        dp[0][0][0] = true; // Base case

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= K; j++) {
                for (int k = 0; k <= totalSum; k++) {

                    // Case 1: Not selecting the current element
                    dp[i][j][k] |= dp[i - 1][j][k];

                    // Case 2: Selecting the current element for subset but not for sub-subset
                    if (j - arr[i-1]>=0) {
                        dp[i][j][k] |= dp[i - 1][j - arr[i-1]][k];
                    }

                    // Case 3: Selecting the current element for both subset and sub-subset
                    if (j-arr[i-1] >= 0 && k-arr[i-1]>=0) { // Ensure subset size and sum constraints
                        dp[i][j][k] |= dp[i - 1][j - arr[i-1]][k - arr[i-1]];
                    }
                }
            }
        }

        // Find all different sums achievable
        Set<Integer> possibleSums = new HashSet<>();

            for (int k = 0; k <= totalSum; k++) {
                if (dp[n][K][k]) {
                    possibleSums.add(k);
                }
            }


        // Print all unique sums
        for (int num : possibleSums) {
            System.out.println(num);
        }
       return  possibleSums.size();
    }
}
