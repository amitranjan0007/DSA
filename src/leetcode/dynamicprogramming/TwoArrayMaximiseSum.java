package leetcode.dynamicprogramming;

import java.util.Scanner;

public class TwoArrayMaximiseSum {
    /*
    > There are two arrays , 'B' and 'C' ; size of both the arrays is 'N' ;

    -> At each index you are supposed to pick up an element either from array 'C' or array 'B'

    ->You have to maximize the final sum of all elements

    ->Condition : You cannot select 3 or more than 3 element consecutively from the same array.
    * */
    public static void main(String[] args) {
        maxSum();
    }
    private static int maxi(int a,int b){
        return Math.max(a,b);
    }
    private static int maxi(int a,int b,int c){
        return Math.max(Math.max(a,b),c);
    }

    private static int maxi(int a,int b,int c,int d){
        return Math.max(Math.max(Math.max(a,b),c),d);
    }

    private static void maxSum(){
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();
        int[] b=new int[n+1];
        int[] d=new int[n+1];
        int[][][] dp=new int[100001][5][5];
          for(int i=1;i<=n;i++){
              b[i]=scanner.nextInt();
          }
        for(int i=1;i<=n;i++){
            d[i]=scanner.nextInt();
        }

        dp[1][1][1]=b[1];// max sum got considering 1 length from array 'b' by selecting two consectuvive, but there is only one element there so 1 element is max that is b[1
        dp[1][2][2]=d[1];
        dp[1][1][2]=b[1];
        dp[1][2][1]=d[2];

          for(int i=2;i<=n;i++){
              dp[i][1][1]=b[i]+b[i-1]+maxi(dp[i-2][2][2],dp[i-2][2][1]);
              dp[i][1][2]=b[i]+d[i-1]+maxi(dp[i-2][1][1],dp[i-2][2][1],dp[i-2][1][2]);
              dp[i][2][2]=d[i]+d[i-1]+maxi(dp[i-2][1][1],dp[i-2][1][2]);
              dp[i][2][1]=d[i]+b[i-1]+maxi(dp[i-2][2][2],dp[i-2][1][2],dp[i-2][2][1]);
          }
        int res=maxi(dp[n][1][1], dp[n][1][2],dp[n][2][2],  dp[n][2][1])  ;
        System.out.println(res);
    }
}
