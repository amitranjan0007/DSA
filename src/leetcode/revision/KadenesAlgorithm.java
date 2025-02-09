package leetcode.dynamicprogramming.revision;

import java.util.Scanner;

public class KadenesAlgorithm {
    public static void main(String[] args) {
       // largestLengthSubArray();
        largestLengthSubArraySpaceOptimise_Kadenes_Algo();//kadenes
    }
    private static void largestLengthSubArray(){
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();
        int[] b=new int[n+1];
        for(int i=1;i<=n;i++){
            b[i]=scanner.nextInt();
        }
        int maxLength=1;
        int[] dp=new int[n+1];
        dp[1]=1;
        for(int i=2;i<=n;i++){
           if(b[i]>b[i-1]){
               dp[i]=1+dp[i-1];
           }else{
               dp[i]=1;
           }
            maxLength=Math.max(maxLength,dp[i]);
        }
       // int maxLength=Arrays.stream(dp).max().getAsInt();
        System.out.println(maxLength);
    }

    private static void largestLengthSubArraySpaceOptimise_Kadenes_Algo(){
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();
        int[] b=new int[n+1];
        for(int i=1;i<=n;i++){
            b[i]=scanner.nextInt();
        }
        int currSum=0;
        int prevSum=1;
        int maxLength=1;
        for(int i=2;i<=n;i++){
            if(b[i]>b[i-1]){
                currSum=1+prevSum;
            }else{
                currSum=1;
            }
            maxLength=Math.max(maxLength,currSum);
            prevSum=currSum;
        }
        System.out.println(maxLength);
    }


    private static int maxi(int a,int b,int c){
        return Math.max(a,Math.max(b,c));
    }
}
