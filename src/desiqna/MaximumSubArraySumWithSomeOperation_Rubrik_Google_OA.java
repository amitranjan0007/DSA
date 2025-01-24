package desiqna;

import java.util.Scanner;

public class MaximumSubArraySumWithSomeOperation_Rubrik_Google_OA {
    /*
     Question Link: https://www.linkedin.com/posts/kumark1_rubrik-offers-a-whooping-ctc-of-75-lakhs-activity-7248020684319178752-lyZC/?utm_source=share&utm_medium=member_desktop
     Dock Link: https://docs.google.com/document/d/1m1FyXGdP_y6perxSZ70HZAHIVVbMDNy_kmgVlnYUE7s/edit?tab=t.0
    * */
    public static void main(String[] args) {
        maximumSubArraySumWithSomeOperation_RubrikOA();
        maximumSubArraySumWithSomeOperation_GoogleOA();
    }
    private static void maximumSubArraySumWithSomeOperation_RubrikOA(){
        /*
         dp[i][multiplied] = best kadane’s sum ending at index “i” such that b[i] was multiplied by x;((but what was happening before “i” we are not sure about it -> at max a range [l…..i] could have been multiplied; for now l = i is fixed;)
         dp[i][not_multiplied] =  best kadane’s sum ending at index “i” such that b[i] was not multiplied by x;(but what was happening before “i” we are not sure about it -> som past range from 1 to i-1 could have been multiplied;)
         k[i] = for sure no operation ever happened till index i and the best kadane’s subarray sum ending at index “i”

          dp[i][0]-defines  dp[i][not_multiplied]
          dp[i][1]-defines   dp[i][multiplied]

        * */
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();
        int x=scanner.nextInt();
        int[][] dp=new int[n+1][2];
        int[]b=new int[n+1];
        for(int i=1;i<=n;i++){
            b[i]=scanner.nextInt();
        }

        int[] k=kadenes(b);
        //0->Not Multiply 1-Multiply
        dp[1][0]=maxi(0,b[0]);//
        dp[1][1]=maxi(0,b[1]*x);
        int ans=Integer.MIN_VALUE;
        for(int i=2;i<=n;i++){
            dp[i][1]=maxi(0,b[i]*x,(b[i]*x)+k[i-1],(b[i]*x)+dp[i-1][1]);
            dp[i][0]=maxi(0,b[i],k[i],b[i]+dp[i-1][1],b[i]+dp[i-1][0]);
            ans=maxi(ans,dp[i][1],dp[i][0]);
        }
        System.out.println(ans);
    }

    private static void maximumSubArraySumWithSomeOperation_GoogleOA(){
        /*
         You are given 2 arrays. Choose a subarray from the first array (arr1) and replace it with the
         corresponding subarray from the second array (arr2). After replacing the subarray,
         calculate the maximum consecutive subarray sum of the modified first array.
         Find the maximum subarray sum you can get by doing this operation. You can do this operation only once.
         N is 10^5 and ai [-1e9, 1e9]
         eg:
            b=[-100,-200,10,-100,-1000]
            c=[-100,-200,10,100,-1000]
        * */
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();
        int[][] dp=new int[n+1][2];//dp[0][0]- considering idx 0 , there will be no transfer
        int[]b=new int[n+1];
        int[]c=new int[n+1];
         for(int i=1;i<=n;i++){
             b[i]=scanner.nextInt();
         }
        for(int i=1;i<=n;i++){
            c[i]=scanner.nextInt();
        }
        int[] k=kadenes(b);
        dp[0][0]=Math.max(0,b[0]);//forced to do no transfer
        dp[0][1]=Math.max(0,c[0]);//forced to do transfer
        int ans=Integer.MIN_VALUE;
          for(int i=1;i<=n;i++){
              dp[i][1]=maxi(0,c[i],c[i]+k[i-1],c[i]+dp[i-1][1]);
              dp[i][0]=maxi(0,b[i],k[i],b[i]+dp[i-1][1],b[i]+dp[i-1][0]);
              ans=maxi(ans,dp[i][1],dp[i][0]);
          }
        System.out.println(ans);
    }


    private static int[] kadenes(int[]b){
        int n=b.length;
        int[] dp=new int[n];
        for(int i=1;i<n;i++){
            dp[i]=Math.max(0,Math.max(b[i],b[i]+dp[i-1]));
        }
        return dp;
    }
    private static int maxi(int a,int b){
        return Math.max(a,b);
    }
    private static int maxi(int a,int b,int c){
        return Math.max(Math.max(a,b),c);
    }
    private static int maxi(int a,int b,int c,int d){
        return Math.max(Math.max(a,Math.max(b,c)),d);
    }

    private static int maxi(int a,int b,int c,int d,int e){
        return Math.max(Math.max(Math.max(a,Math.max(b,c)),d),e);
    }
}
