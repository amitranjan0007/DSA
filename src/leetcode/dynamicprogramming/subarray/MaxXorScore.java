package leetcode.dynamicprogramming.subarray;

import java.util.Scanner;

public class MaxXorScore {
    /*
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

    private static void maxXorInRange(){
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();

        int b[]=new int[n+1];
        int dp[][]=new int[n+1][n+1];
        for(int i=1;i<=n;i++){
            b[i]=scanner.nextInt();
        }

        for(int i=1;i<=n;i++){
            dp[i][i]=b[i];// for the last element we have to leave like this means start with i & end at i the xor will be b[i]
        }

        int length=2;
        while(length<=n){
            for(int i=0;i<n-length+1;i++){
                int j=i+length-1;
                dp[i][j]=dp[i][j-1]^dp[i+1][j];
            }
            length++;
        }
    }

    private static void maxXorInRangeFollowUp(){
        Scanner scanner = new Scanner(System.in);

        // Reading the size of the array (n) and the number of queries
        int n = scanner.nextInt();
        int numberOfQuery = scanner.nextInt();
        int[][] query = new int[numberOfQuery][2];

        // Storing the queries
        for (int i = 0; i < numberOfQuery; i++) {
            int l = scanner.nextInt();
            int r = scanner.nextInt();
            query[i][0] = l;
            query[i][1] = r;
        }

        // Array to hold the elements
        int[] b = new int[n + 1];
        // dp array for calculating XOR
        int[][] dp = new int[n + 1][n + 1];

        // Reading the elements into array b (1-based indexing)
        for (int i = 1; i <= n; i++) {
            b[i] = scanner.nextInt();
        }

        // Initializing the dp array where dp[i][i] = b[i] for all i
        for (int i = 1; i <= n; i++) {
            dp[i][i] = b[i];
        }

        // Fill the dp array with XOR values
        for (int length = 2; length <= n; length++) {  // Length of the subarray
            for (int i = 1; i <= n - length + 1; i++) {  // Starting index of the subarray
                int j = i + length - 1;  // Ending index of the subarray
                dp[i][j] = dp[i][j - 1] ^ dp[i + 1][j];
            }
        }

        // Compute maximum XOR for all subarrays
        for (int length = 2; length <= n; length++) {
            for (int i = 1; i <= n - length + 1; i++) {
                int j = i + length - 1;
                dp[i][j] = Math.max(Math.max(dp[i][j], dp[i][j - 1]), dp[i + 1][j]);
            }
        }

        // Answering the queries
        for (int i = 0; i < numberOfQuery; i++) {
            int l = query[i][0]+1;
            int r = query[i][1]+1;
            System.out.println(dp[l][r]);
        }

        scanner.close();
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