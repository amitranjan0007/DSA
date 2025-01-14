package leetcode.dynamicprogramming.partitiondp;
import java.util.Scanner;

public class MinimiseCostOfContest_LinkdinOA {
    /*
    https://www.linkedin.com/posts/kumark1_kumark-coding-activity-7248721276163215360-HyaV/?utm_source=share&utm_medium=member_desktop
6
2
1 5 8 10 15 18
19


5
3
1000 500 2000 8000 1500
9500

Understanding - Given an array - divide the array into K-parts.
G1 = maximum element of the first partition.

G2 = maximum element of the second partition.

.
.
.
.
.
GK = maximum element of the last partition.

Make sure (G1+G2+G3+G4+G5+........GK) is minimized.

Partition DP -

[..........................................................................................................]

-> dp[i] = count of ways to divide the first “i” elements.

-> fix the last element “i” + dp[i-1]

-> fix the last 2 elements “i i-1” + dp[i-2]

->
.
.
.
.
.
.


-> dp[i] = dp[i-1] + dp[i-2] + ……………………….dp[0]


—---------------------------------------------------------------------


-> dp[i] -> best way to divide the array of size “i” in k-parts; such that the sum of the maximum of each part is minimized.

How will we make the formula? -> Formula is not able to keep track of the k-partitions we need.

-> Necessity gives birth to a new dp state.

-> dp[i][k] = best way to divide an array of size “i” in k parts such that the sum of the maximum of each part is minimum.

-> dp[i][k] = b[i] + dp[i-1][k-1]



               = max(b[i],b[i-1]) + dp[i-2][k-1]
               =
               .
               .


-> Minimum of all possibilities is the final answer


TC - O(N^2*K) -> Optimal

    *
    **/
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        int[] b = new int[n+1];
        for (int i = 1; i <= n; i++) {
            b[i] = scanner.nextInt();
        }

        /*
        dp[i][k] = best way to divide an array of size “i” in k parts such that the sum of the maximum of each part is minimum.
        * */
        int[][] dp = new int[n + 1][k + 1];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= k; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }

       // dp[0][0] = 0;
        dp[1][0] = 0;
        dp[1][1] = b[1];
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= Math.min(i, k); j++) {
                int currentMax = 0;
                for (int l = i; l >= 1; l--) {
                    currentMax = Math.max(currentMax, b[l]);
                    if(dp[l - 1][j - 1]!=Integer.MAX_VALUE){
                        dp[i][j] = Math.min(dp[i][j], currentMax + dp[l - 1][j - 1]);
                    }

                }
            }
        }
        System.out.println(dp[n][k]);
    }
}
