package leetcode.dynamicprogramming;

import java.util.Scanner;

public class LIS {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();
        int arr[]=new int[n+1];
        for(int i=1;i<=n;i++){
            arr[i]=scanner.nextInt();
        }
        int[] dp=new int[n+1];
        dp[1]=1;
        for(int i=1;i<=n;i++){
            for(int j=1;j<n;j++){
                if(arr[j]<arr[i]){
                    dp[i]=Math.max(dp[i],dp[j]+1);
                }

            }
        }
        System.out.println(dp[n]);
    }
}
