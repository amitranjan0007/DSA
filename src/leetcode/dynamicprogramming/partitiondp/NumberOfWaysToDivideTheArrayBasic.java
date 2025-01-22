package leetcode.dynamicprogramming.partitiondp;

import java.util.Scanner;

public class NumberOfWaysToDivideTheArrayBasic {
    public static void main(String[] args) {
       // NumberOfWaysSimpleVersion();
        NumberOfWaysSimpleVersionFollowUp();
    }
    public static void NumberOfWaysSimpleVersion() {
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();
        int[] dp=new int[n+1];
        dp[0]=1;
        dp[1]=1;
        for(int i=2;i<=n;i++){
            for (int j=i;j>=1;j--){
                dp[i]+=dp[j-1];
            }
        }
        System.out.println(dp[n]);
    }
    public static void NumberOfWaysSimpleVersionFollowUp() {
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();
        int m=scanner.nextInt();
        int[] dp=new int[n+1];
        int[] b=new int[n+1];
          for(int i=1;i<=n;i++)b[i]=scanner.nextInt();
        dp[0]=1;
        dp[1]=1;
        for(int i=2;i<=n;i++){
            int sum=0;
            for (int j=i;j>=1;j--){
                sum+=b[j];
                  if(sum<=m){
                      dp[i]+=dp[j-1];
                  }
            }
        }
        System.out.println(dp[n]);
    }
}
