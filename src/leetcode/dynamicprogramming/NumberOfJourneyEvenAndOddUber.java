package leetcode.dynamicprogramming;

import java.util.Scanner;

public class NumberOfJourneyEvenAndOddUber {
    /*
     You are given two arrays a and b -> both are of size “N”.

    Start your journey at index 1 and end your journey at index “N”.

    In a move you can move from a[i]->a[i+1] or a[i]->b[i+1]

    OR

    b[i]->b[i+1] or b[i]->a[i+1]

    Output the number of journeys whose sum is even and odd.
    eg:
        1 2 1
        3 1 1
        even 4
        odd 4
    Understanding:

    You are given two arrays a and b, both of size n. Each element in the arrays can be either even or odd. The task is to count the number of ways to end at the n-th index such that the sum is even or odd, considering all possible transitions between the arrays at each index.

    Even Sum: The sum of numbers ending at the n-th index is even.
    Odd Sum: The sum of numbers ending at the n-th index is odd.
    We define a 3D dp array to track:

    dp[i][0][0]: Number of ways to reach the i-th index with an even sum coming from array a.
    dp[i][1][0]: Number of ways to reach the i-th index with an odd sum coming from array a.
    dp[i][0][1]: Number of ways to reach the i-th index with an even sum coming from array b.
    dp[i][1][1]: Number of ways to reach the i-th index with an odd sum coming from array b.
    * */

    public static void main(String[] args) {
        findNumberOfOddAndEvenAtNthIdx();
    }
    private static int maxi(int a,int b){
        return Math.max(a,b);
    }
    private static int maxi(int a,int b,int c){
        return Math.max(Math.max(a,b),c);
    }
    private static void findNumberOfOddAndEvenAtNthIdx() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n + 1];
        int[] b = new int[n + 1];
        //dp state define -> N,even/odd,coming from a or b array in the ith idx
        // dp state: dp[i][0][0] -> even sum at index i from array a
        //           dp[i][1][0] -> odd sum at index i from array a
        //           dp[i][0][1] -> even sum at index i from array b
        //           dp[i][1][1] -> odd sum at index i from array b
        int[][][] dp = new int[n+1][2][2];
        for (int i = 1; i <= n; i++) {
            a[i] = scanner.nextInt();
        }
        for (int i = 1; i <= n; i++) {
            b[i] = scanner.nextInt();
        }

        dp[1][0][0]=a[1]%2==0?1:0;//a[1] is even
        dp[1][1][0]=a[1]%2!=0?1:0;// a[1] is odd
        dp[1][0][1]=b[1]%2==0?1:0;//b[1] is even
        dp[1][1][1]=b[1]%2!=0?1:0;//a[1] is odd

        for(int i=2;i<=n;i++){
            if(a[i]%2==0){
                dp[i][0][0]=dp[i-1][0][0]+dp[i-1][0][1];/// even sum from previous even
                dp[i][1][0]=dp[i-1][1][0]+dp[i-1][1][1];// odd sum from previous odd
            }else if(a[i]%2!=0){
                dp[i][0][0]=dp[i-1][1][0]+dp[i-1][1][1];// even sum from previous odd
                dp[i][1][0]=dp[i-1][0][0]+dp[i-1][0][1];// odd sum from previous even
            }


            if(b[i]%2==0){
                dp[i][0][1]=dp[i-1][0][0]+dp[i-1][0][1];// even sum from previous even
                dp[i][1][1]=dp[i-1][1][0]+dp[i-1][1][1];// odd sum from previous odd
            }else if(b[i]%2!=0){
                dp[i][0][1]=dp[i-1][1][0]+dp[i-1][1][1];// even sum from previous odd
                dp[i][1][1]=dp[i-1][0][0]+dp[i-1][0][1];// odd sum from previous even
            }
        }

        // Now Calculate total even and odd sums at index n
        int evenSum=dp[n][0][0]+dp[n][0][1];
        int oddSum=dp[n][1][0]+dp[n][1][1];
        System.out.println(evenSum+"---"+oddSum);
    }
}
