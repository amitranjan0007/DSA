package leetcode.dynamicprogramming.partitiondp;

import java.util.Arrays;
import java.util.Scanner;

public class GetMaximumSumInSubArrayBySelectingKPartition {
    /*
      4 5 6 -5 8 3
      k=2
      P0 :-> Given an array ; pick “k” subarrays from it which are non-intersecting and their final sum should be maximum!

-> It's not necessary to include all elements of the array

-> 1<=N<=10000
-> 1<=K<=min(N,100)


-> [5 8 -15 3 4 5]

K = 3

Output :-> 25

Analysis :-> dp[...........]

dp[i] = best answer to the question if array is of size “i”

dp[i][j] = best answer to the question if the array is of size “i” and you are allowed to divide in j parts.



Algorithm :->

dp[i][j] = option1 => b[i](jth part) + dp[i-1][j-1]  => putting only the last element in the third part

= option2 => you can put the last 2 elements in the third part
b[i] + b[i-1] + dp[i-2][j-1]

= option3 => dp[i][j] = b[i] + b[i-1] + b[i-2] + dp[i-2][j-1] -> Naman’s law.

= option4 => last 4 elements in the last part

.
.
.
.
.
.

Special Option:-> Option 0:-> when you totally ignore b[i] and dont include in any part -> dp[i][j] = dp[i-1][j]

Final Answer :-> dp[n][k] ->



    * */
    public static void main(String[] args) {
        //GetMaximumSumInSubArrayBySelectingKPartitionSubOptimal();
        GetMaximumSumInSubArrayBySelectingKPartitionOptimal();
    }

    private static void GetMaximumSumInSubArrayBySelectingKPartitionSubOptimal(){
        //tc : O(n*n*k)
        Scanner scanner=new Scanner(System.in);
        long n=scanner.nextInt();
        long k=scanner.nextInt();
        long[] b=new long[(int)n+1];
        long[][] dp=new long[(int)n+1][(int)k+1];
        for(int i=1;i<=n;i++){
            b[i]=scanner.nextInt();
        }
       for(int i=0;i<=n;i++){
           for(int j=0;j<=k;j++){
               dp[i][j]=(long)-1e18;
           }
       }
        dp[0][0]=0;// think if you have k=1 and all the
          for(int i=1;i<=n;i++){
              for(int j=1;j<=k;j++){//parts
                  long v=dp[i-1][j];//not including the ith element
                  int sum=0;
                     for(int j1=i;j1>=1;j1--){
                         sum+=b[j1];
                           long g=sum+dp[j1-1][j-1];//this can go to dp[0][0],so we dclare at 0
                           v=Math.max(v,g);
                     }
                     dp[i][j]=v;
              }
          }
        System.out.println(dp[(int)n][(int)k]);
    }


    private static void GetMaximumSumInSubArrayBySelectingKPartitionOptimal(){
        //tc : O(n*k)
        Scanner scanner=new Scanner(System.in);
        long n=scanner.nextInt();
        long k=scanner.nextInt();
        long[] b=new long[(int)n+1];
        long[][][] dp=new long[(int)n+1][(int)k+1][3];
        for(int i=1;i<=n;i++){
            b[i]=scanner.nextInt();
        }
        for(int i=0;i<=n;i++){
            for(int j=0;j<=k;j++){
                dp[i][j][1]=(long)-1e18;
                dp[i][j][2]=(long)-1e18;
            }
        }
        dp[0][0][1]=0;// think if you have k=1 and all the
        dp[0][0][0]=0;
        for(int i=1;i<=n;i++){
            for(int j=1;j<=k;j++){//parts
               dp[i][j][0]=Math.max(dp[i-1][j][0],dp[i-1][j][1]);
                dp[i][j][1]=Math.max((b[i]+Math.max(dp[i-1][j-1][0],dp[i-1][j-1][1])),b[i]+dp[i-1][j][1]);
            }
        }
        System.out.println(Math.max(dp[(int)n][(int)k][0],dp[(int)n][(int)k][1]));
    }
}
