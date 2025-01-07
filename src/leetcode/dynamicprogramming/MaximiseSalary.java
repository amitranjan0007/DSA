package leetcode.dynamicprogramming;

import java.util.Scanner;

public class MaximiseSalary {
    //https://www.desiqna.in/10567/barclays-sde-coding-oa-questions-and-solutions-set-8-2022-dp
    public static void main(String[] args) {
        maxSalary();
    }
    private static int maxi(int a,int b){
        return Math.max(a,b);
    }
    private static int maxi(int a,int b,int c){
        return Math.max(Math.max(a,b),c);
    }
    private static void maxSalary(){
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();
        int[] b=new int[n+1];
        int[] d=new int[n+1];
        int[][] dp=new int[100001][3];
        for(int i=1;i<=n;i++){
            b[i]=scanner.nextInt();
        }
        for(int i=1;i<=n;i++){
            d[i]=scanner.nextInt();
        }
        /*
          0- easy
          1- hard
          2-not doing anything on a day
        * */

        dp[1][0]=b[1];
        dp[1][1]=d[1];
        dp[1][2]=0;


        for(int i=2;i<=n;i++){
          dp[i][0]=b[i]+maxi(dp[i-1][0],dp[i-1][1],dp[i-1][2]);
          dp[i][1]=d[i]+dp[i-1][2];//i select the hard task only if ,I havn't done any task on prev day
          dp[i][2]=maxi(dp[i-1][0],dp[i-1][1],dp[i-1][2]);//consecutively I have not done any task for n days before, or done hard or done easy
        }
        int res=maxi( dp[n][0], dp[n][1], dp[n][2])  ;
        System.out.println(res);
    }
}
