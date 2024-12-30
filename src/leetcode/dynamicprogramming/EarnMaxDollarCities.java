package leetcode.dynamicprogramming;

import java.util.Scanner;

public class EarnMaxDollarCities {
    /*
      Question: https://www.desiqna.in/16115/google-interview-problem-dynamic-programming-cities-october
      Understanding :-> You are given 2 arrays ; travel from start to end; maximum maximum dollars ;
      whenever you try to jump from Array “A” to Array “B” you make no money :)
          int[] a={5,1,1,1,100};
          int[] b={1,100,2,3,1};
          Output: 203

          int[] a={1,2,3,4,4};
          int[] b={1,2,3,4,4};
          Output: 14

      Analysis :->
         dp[i] = best answer to the question if the size of array was “i”
         we really need information at ith index whether we have take element from array A or B

         -> dp[i][a] = best answer to the question if size of both array were “i” and the element picked at the ith index if for sure from array “a”

         -> dp[i][b] = best answer to the question if size of both array were i and element picked at ith index is from B

            dp[i][a] = max(a[i] + dp[i-1][a] Or a[i] + dp[i-2][b])

            dp[i][b] = max(b[i] + dp[i-1][b] or b[i] + dp[i-2][a])


    * */
    public static void main(String[] args) {
        jumpingTwoCityToMaximizeDollar();
        jumpingThreeCityToMaximizeDollarFollowUp();
    }

    private static void jumpingTwoCityToMaximizeDollar(){
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();
        long[] a=new long[n];
        long[] b=new long[n];
        for(int i=0;i<n;i++){
            a[i]=scanner.nextLong();
        }
        for(int i=0;i<n;i++){
            b[i]=scanner.nextLong();
        }
        long[][] dp=new long[n][2];
        dp[0][0]=a[0];
        dp[0][1]=b[0];
        dp[1][0]=a[0]+a[1];
        dp[1][1]=b[0]+b[1];
        for(int i=2;i<n;i++){
            dp[i][0]=Math.max(a[i]+dp[i-1][0],a[i]+dp[i-2][1]);
            dp[i][1]=Math.max(b[i]+dp[i-1][1],b[i]+dp[i-2][0]);
        }
        long res=Math.max(dp[n-1][0],dp[n-1][1]);
        System.out.println(res);
    }

    private static void jumpingThreeCityToMaximizeDollarFollowUp(){
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();
        long[] a=new long[n];
        long[] b=new long[n];
        long[] c=new long[n];
        for(int i=0;i<n;i++){
            a[i]=scanner.nextLong();
        }
        for(int i=0;i<n;i++){
            b[i]=scanner.nextLong();
        }
        for(int i=0;i<n;i++){
            c[i]=scanner.nextLong();
        }
        long[][] dp=new long[n][3];
        dp[0][0]=a[0];
        dp[0][1]=b[0];
        dp[0][2]=c[0];

        dp[1][0]=a[0]+a[1];
        dp[1][1]=b[0]+b[1];
        dp[1][2]=c[0]+c[1]+c[2];
        for(int i=2;i<n;i++){
            dp[i][0]=max(a[i]+dp[i-1][0],a[i]+dp[i-2][1],a[i]+dp[i-2][2]);
            dp[i][1]=max(b[i]+dp[i-1][1],b[i]+dp[i-2][0],b[i]+dp[i-2][2]);
            dp[i][2]=max(c[i]+dp[i-1][2],c[i]+dp[i-2][0],c[i]+dp[i-2][1]);
        }
        long res=max(dp[n-1][0],dp[n-1][1],dp[n-1][2]);
        System.out.println(res);
    }
    private static long max(long a,long b,long c){
        return Math.max(Math.max(a,b),c);
    }


}
