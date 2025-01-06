package leetcode.dynamicprogramming;

import java.util.Arrays;
import java.util.Scanner;

public class LISWithDecAdjacentDiff {
    //Problem Link: https://leetcode.com/problems/longest-subsequence-with-decreasing-adjacent-difference/
    public static void main(String[] args) {
        longestSubsequence();
    }
    public static void longestSubsequence() {
        /*
        TC:300*N^2

        Understanding:
        Find longest sequence which is valid.

            -> Any sequence which is valid will always have decreasing order of their adjacent differences.

            -> [6 5 3 4 2 1]

            -> [6,4,2,1] =>(2,2,1)

            ->adj1 >= ad2 >= ……..

            -> dp[i] = largest valid sequence ending at index “i” (ith element should be included forcefully)

            -> not enough to solve.

            -> increase the state.

            -> dp[i][diff] = largest valid sequence ending at index “i” such that its difference with the second last element is diff.

            -> whenever you are at index “i” -> travel from 0 to i-1 ;

            -> let us say abs(b[j]-b[i]) == diff; j < i

            -> dp[i][diff] = 1 + (dp[j][diff] OR dp[j][diff+1] OR                      dp[j][diff+2] OR
                                = ……………………………….. OR dp[j][300]
                              = 1 + max of all of them.

            -> final answer = max(dp[1][from 0 to 300] , dp[2][...],.....dp[n][...])

        * */
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();
        int[]arr=new int[n+1];
        for(int i=1;i<=n;i++){
            arr[i]=scanner.nextInt();
        }
        int dp[][]=new int[n+1][301];
        int result=1;
        for(int i=1;i<=n;i++){
            Arrays.fill(dp[i],1);
        }

        for(int i=1;i<=n;i++){
            for(int j=1;j<i;j++){
                int diff=Math.abs(arr[i]-arr[j]);
                for(int k=diff;k<=300;k++){
                    dp[i][diff]= Math.max(1+dp[j][k],dp[i][diff]);
                }
            }
            for (int k = 0; k <= 300; k++) {
                result = Math.max(result, dp[i][k]);
            }
        }
        System.out.println(result);
    }
}
