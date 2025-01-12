package leetcode.dynamicprogramming.subarray;

import java.util.Scanner;

public class MaxXorScore {
    /*
    Input Format
        7
        5
        0 3
        1 5
        2 4
        2 6
        5 6
        0 7 3 2 8 5 1
* */
    public static void main(String[] args) {
        maxXorInRangeFollowUp();
    }

    private static void maxXorInRangeFollowUp(){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int numberOfQuery = scanner.nextInt();
        int[][] query = new int[numberOfQuery][2];

        //storing query
        for (int i = 0; i < numberOfQuery; i++) {
            int l = scanner.nextInt();
            int r = scanner.nextInt();
            query[i][0] = l;
            query[i][1] = r;
        }

        int[] b = new int[n + 1];
        int[][] answer = new int[n + 1][n + 1];
        int[][] dp = new int[n + 1][n + 1];

        // Read the elements into array b
        for (int i = 1; i <= n; i++) {
            b[i] = scanner.nextInt();
        }

        // Initialize dp for length 1 subarrays (i.e., just the elements themselves)
        for (int i = 1; i <= n; i++) {
            answer[i][i] = b[i]; //dp store the  xor of subArray
            dp[i][i] = b[i]; // dp store the max xor in subArray of subArray
        }

        // Calculate the XOR for subarrays of length 2 and greater
        int length = 2;
        while (length <= n) {
            for (int i = 1; i <= n - length + 1; i++) {
                int j = i + length - 1;
                answer[i][j] = answer[i][j - 1] ^ answer[i + 1][j];
            }
            length++;
        }

        // Calculate the max XOR for subarrays of length 2 and greater
        length = 2;

        //TC:O(N^2) // But Not working for some range
//        while (length <= n) {
//            for (int i = 1; i <= n - length + 1; i++) {
//                int j = i + length - 1;
//                dp[i][j] = Math.max(dp[i][j], Math.max(answer[i][j], Math.max(answer[i][j-1], answer[i+1][j])));
//            }
//            length++;
//        }

        //TC:O(N^3)
        while(length<=n){
            for (int i = 1; i <= n - length + 1; i++) {
                int j = i + length - 1;
                dp[i][j] = answer[i][j];

                // Try all splits to find the maximum XOR
                for (int k = i; k < j; k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][k]); // Left subarray
                    dp[i][j] = Math.max(dp[i][j], dp[k + 1][j]); // Right subarray
                }
            }
            length++;
        }

        // Process the queries
        for (int[] q : query) {
            // Adjust indices because queries are 0-based, and dp uses 1-based indexing
            System.out.println(dp[q[0] + 1][q[1] + 1]);
        }
    }
    private static int maxi(int a,int b,int c){
        return Math.max(a,Math.max(b,c));
    }
}
/*
7
5
0 3
1 5
2 4
2 6
5 6
0 7 3 2 8 5 1



Test case:2
6
3
0 2
1 4
0 5
2 8 4 32 16 1
12
60
27
* */