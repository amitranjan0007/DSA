package leetcode.dynamicprogramming;

import java.util.Scanner;

public class CountNumberOfWays {
    public static void main(String[] args) {
        int res=countSequences();
        System.out.println(res);
    }
    public static int countSequences() {
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();
        int b[]=new int[n+1];
        for(int i=1;i<=n;i++){
            b[i]=scanner.nextInt();
        }
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 1;


        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1];
            if (b[i - 2] == 2) {
                dp[i] += dp[i - 2];
            }
        }

        return dp[n];
    }
}
