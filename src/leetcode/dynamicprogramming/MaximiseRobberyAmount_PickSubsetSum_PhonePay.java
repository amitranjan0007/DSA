package leetcode.dynamicprogramming;

import java.util.Scanner;

public class MaximiseRobberyAmount_PickSubsetSum_PhonePay {

    /*
    Problem Link: https://drive.google.com/file/d/1MDj1wNenMXek5vZrPOlYJPrfgfuhuTLC/view
    https://docs.google.com/document/d/1zqjc95zDgkDbLtOoiK5zaRS7VPSAEiF3VXh7n3tYvH8/edit?tab=t.0

    Question
     Pick subset of elements from array -> Sum of that subset should be maximum.

    -> If you pick a group of 2 numbers; you can again do this operation only after ignoring 1 next number

    -> You can select a group of 3 numbers as well -> but if you do that the next 2 numbers should not be selected and the same goes for the previous 2 numbers.


     Example:
        input 1 output 0
        input 1 2  output 3
        input 1 2 3  output 6
        input 1 2 3 4  output 9
        input 4 1 1 5 6  output 16
        input 1 5 5 5 1  output 15

    * */
    public static void main(String[] args) {
      //  maxRobberyAmountFirstApproachSubOptimal();//TC:O(n^2)

       maxRobberyAmountOptimal();// very important, how you optimise using prefix sum TC:O(n)


      //maxRobberyAmountSecondApproachOptimal();//TC:O(n)
    }

    private static int maxi(int a, int b) {
        return Math.max(a, b);
    }

    private static int maxi(int a, int b, int c) {
        return Math.max(Math.max(a, b), c);
    }

    private static void maxRobberyAmountFirstApproachSubOptimal() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] b = new int[n + 1]; // 1-based indexing

        for (int i = 1; i <= n; i++) {
            b[i] = scanner.nextInt();
        }

        // DP arrays to store maximum sum subsets
        int[][] dp = new int[n + 1][4]; // dp[i][2] and dp[i][3]

        // Base cases
        dp[1][2] = dp[1][3] = 0;
        if (n >= 2) {
            dp[2][2] = b[1] + b[2];
        }
        if (n >= 3) {
            dp[3][2] = b[2] + b[3];
            dp[3][3] = b[1] + b[2] + b[3];
        }

        // Fill the DP table
        for (int i = 4; i <= n; i++) {
            // For subset ending at i with last 2 elements included
            int x = b[i] + b[i - 1];
            dp[i][2] = x;
            for (int j = 3; j <= i; j++) {
                if (i - j >= 1) {
                    dp[i][2] = maxi(dp[i][2], x + maxi(dp[i - j][2], dp[i - j][3]));
                }
            }

            // For subset ending at i with last 3 elements included
            int y = b[i] + b[i - 1] + b[i - 2];
            dp[i][3] = y;
            for (int j = 5; j <= i; j++) {
                if (i - j >= 1) {
                    dp[i][3] = maxi(dp[i][3], y + maxi(dp[i - j][2], dp[i - j][3]));
                }
            }
        }

        // Find the maximum sum possible
        int maxSum = 0;
        for (int i = 1; i <= n; i++) {
            maxSum = Math.max(maxSum, Math.max(dp[i][2], dp[i][3]));
        }

        System.out.println(maxSum);
    }

    private static void maxRobberyAmountOptimal() {

        /*
          dp[][]--defines [max amount till that idx][selecting 2/3 element consecutively]
          2- selecting 2 elements  consecutively
          3- selecting 3 elements  consecutively
          dp[i][2]- maximum sum subset that can be picked till index “i”
                    making sure the guy at index “i” is also included in the subset
                    and current & last guys are for sure put in the subset
          dp[i][3]- maximum sum subset that can be picked till index “i”
                    making sure the guy at index “i” is also included in the subset
                    and current & last 2 guys are for sure put in the subset
        * */
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] b = new int[n + 1]; // 1-based indexing

        for (int i = 1; i <= n; i++) {
            b[i] = scanner.nextInt();
        }

        // DP arrays to store maximum sum subsets
        int[][] dp = new int[n + 1][4]; // dp[i][2] and dp[i][3]

        // Base cases
        dp[1][2] = dp[1][3] = 0;
        if (n >= 2) {
            dp[2][2] = b[1] + b[2];
            dp[2][3]=0;
        }
        if (n >= 3) {
            dp[3][2] = Math.max(b[1] + b[2], b[2] + b[3]);
            dp[3][3] = b[1] + b[2] + b[3];
        }
        int[] prefixMax2 = new int[n + 1];
        int[] prefixMax3 = new int[n + 1];

        prefixMax2[1] = dp[1][2];
        prefixMax3[1] = dp[1][3];
        if (n >= 2) {
            prefixMax2[2] = dp[2][2];
            prefixMax3[2] = dp[2][3];
        }
        if (n >= 3) {
            prefixMax2[3] = Math.max(dp[2][2], dp[3][2]);
            prefixMax3[3] = Math.max(dp[2][3], dp[3][3]);
        }

        // Fill prefixMax arrays
        for (int i = 4; i <= n; i++) {
            // as you know you don't have the value of dp[4][2] or dp[4][3],
            // so for now, you will initilize the value with  previous max & later you update it
            prefixMax2[i] = Math.max(prefixMax2[i - 1], dp[i][2]);
            prefixMax3[i] = Math.max(prefixMax3[i - 1], dp[i][3]);
        }
        // Fill the DP table
        for (int i = 4; i <= n; i++) {
            if (i - 3 >= 1) {
                int maxFromPrefix2 = b[i] + b[i - 1] + prefixMax2[i - 3];
                int maxFromPrefix3 = b[i] + b[i - 1] + prefixMax3[i - 3];
                dp[i][2] = Math.max(dp[i][2], Math.max(maxFromPrefix2, maxFromPrefix3));
            }
            int y = b[i] + b[i - 1] + b[i - 2];
            dp[i][3] = y;

            if (i - 5 >= 1)
                dp[i][3] = maxi(dp[i][3], y + maxi(prefixMax2[i - 5], prefixMax3[i - 5]));

            prefixMax2[i] = Math.max(prefixMax2[i-1], dp[i][2]);
            prefixMax3[i] = Math.max(prefixMax3[i-1], dp[i][3]);
        }

        // Find the maximum sum possible
        int maxSum = 0;
        for (int i = 1; i <= n; i++) {
            maxSum = Math.max(maxSum, Math.max(dp[i][2], dp[i][3]));
        }

        System.out.println(maxSum);
    }

    private static void maxRobberyAmountSecondApproachOptimal() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] b = new int[n + 1]; // 1-based indexing

        for (int i = 1; i <= n; i++) {
            b[i] = scanner.nextInt();
        }

        // DP arrays to store maximum sum subsets
        int[][] dp = new int[n + 1][4]; // dp[i][2] and dp[i][3]

        // Base cases
        dp[1][2] = dp[1][3] = 0;
        if (n >= 2) {
            dp[2][2] = b[1] + b[2];
        }
        if (n >= 3) {
            dp[3][2] = b[2] + b[3];
            dp[3][3] = b[1] + b[2] + b[3];
        }

        // Fill the DP table
        for (int i = 4; i <= n; i++) {
            // For subset ending at i with last 2 elements included
            dp[i][2] = b[i] + b[i - 1];
//                for (int j = 3; j <= i; j++) {
            if (i - 4 >= 1) {
                dp[i][2] = dp[i][2] + Math.max(dp[i - 3][2], Math.max(dp[i - 4][3], dp[i - 4][2]));
            }
//                }

            // For subset ending at i with last 3 elements included
            dp[i][3] = b[i] + b[i - 1] + b[i - 2];
//                for (int j = 5; j <= i; j++) {
            if (i - 5 >= 1) {
                dp[i][3] += Math.max(dp[i - 5][2], dp[i - 5][3]);
            }
//                }
        }

        // Find the maximum sum possible
        int maxSum = 0;
        for (int i = 1; i <= n; i++) {
            maxSum = Math.max(maxSum, Math.max(dp[i][2], dp[i][3]));
        }

        System.out.println(maxSum);
    }



}
