package leetcode.dynamicprogramming.subarray;

import java.util.Scanner;

public class BurstingBalloon {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();
        int b[]=new int[n+2];
        int dp[][] = new int[n+2][n+2];
        b[0]=1;
        b[n+1]=1;
        for(int i=1;i<=n;i++){
            b[i]=scanner.nextInt();
        }
        int i=1;
        while(i<=n){
            dp[i][i]=b[i-1]*b[i]*b[i+1];
            i++;
        }


        /*************You can neglect the length 2 base condition als0*********/
        i=1;
        while(i<n){
            int j = i + 1;
            dp[i][j] = Math.max(
                    b[i - 1] * b[i] * b[j + 1] + b[i] * b[j] * b[j + 1],  // Burst b[i] first, then b[j]
                    b[i - 1] * b[j] * b[j + 1] + b[i - 1] * b[i] * b[j]   // Burst b[j] first, then b[i]
            );
            i++;
        }
        /*************************************/

        int length = 3;// and start with length 2 also ,if you are removing above base constion of length 2
        while (length <= n) {
             i = 1;
            while (i <= n - length + 1) {
                int j = i + length - 1;
                dp[i][j] = Integer.MIN_VALUE;
                for (int k = i; k <= j; k++) { // k is the last balloon to burst
                    int left = k == i ? 0 : dp[i][k - 1];
                    int right = k == j ? 0 : dp[k + 1][j];
                    int cost = b[i - 1] * b[k] * b[j + 1] + left + right;
                    dp[i][j] = Math.max(dp[i][j], cost);
                }

                i++;
            }
            length++;
        }
        System.out.println(dp[1][n]);
    }

}
