package leetcode.dynamicprogramming;

import java.util.Arrays;
import java.util.Scanner;

public class LISWithDecAdjacentDiff {
    public static void main(String[] args) {
        longestSubsequence();
    }
    public static void longestSubsequence() {
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
