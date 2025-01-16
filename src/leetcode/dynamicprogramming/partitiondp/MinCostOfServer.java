package leetcode.dynamicprogramming.partitiondp;

import java.util.Arrays;
import java.util.Scanner;

public class MinCostOfServer {
    /*
Question Link: https://www.desiqna.in/15165/google-oa-software-engineering-intern-july-21-2023-kumar-k
CostA=3 5 2 1 9
CostB=1 1 10 5 3
Output=12


dp→[N]

dp[i] = means the best answer to the question if size of the array was “i”

This trick is mainly used when you have to divide your array into small subarrays. + give the best answer


dp[i] = dp[i-1] + fix the last part “i” of the array

OR

dp[i] = dp[i-2] + fix the last part “i-1 i” of the array

OR
.
.
.
.
.
dp[i] = dp[1] + fix the last part “2……..i” of the array

OR

dp[i] = dp[0] + fix the whole last part(“1……i”) of the array

Min/Max of all the choices will be your answer

Understanding :-> You are given an array ; divide that array into subarrays ; total cost of doing this should be minimum.

Whenever the subarray size >=2 ; cost of the subarray [i….j] = b[i] + b[i+1] + ……..b[j]

Whenever the subarray size = 1 ; cost of the subarray is [i…i] = a[i]

Solution!:->

dp[i] =  minimum cost to divide the array of size i

a-> [ 3 5 2 1 9]
b->[1 1 10 5 3]
dp[0] = 0
dp[1] = a[1] = 3

dp[2] = Option 1 => [1] [2] => a[1] + a[2] => 8
Option 2 => [1 2] => b[1] + b[2] = 2

Minimum = 2.

dp[3] =
Option 1 => dp[2] + fix the last part “3” => dp[2] + a[3]
Option 2 =>dp[1] + fix the last part “2 and 3” => dp[1] + (b[2]+b[3])
Option 3 => dp[0]  + fix the last part “1 2 3” => dp[0] + (b[1] + b[2] + b[3])

Minimum of all the options is your answer :)

Let's make the general formula!

dp[i]=min(dp[i-1]+a[i],dp[i-2]+b[i-1]+b[i],......................,dp[0]+(b[1] + b[2] +........+b[i-2]+b[i-1]+b[i]))


    * */
   private static int MAX_VALUE=100000;
    public static void main(String[] args) {
       // helperSubOptimal();
        helperOptimal();
    }
    private static void helperSubOptimal(){
        //TC: O(n^2) Sc:O(n)
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();
        int[]a=new int[n+1];
        int[]b=new int[n+1];
        int[]dp=new int[n+1];
        for (int i=1;i<=n;i++){
            a[i]=scanner.nextInt();
        }
        for (int i=1;i<=n;i++){
            b[i]=scanner.nextInt();
        }
        Arrays.fill(dp,MAX_VALUE);
        dp[0]=0;
        for(int i=1;i<=n;i++){
            int curr_sum=0;
            int ans=MAX_VALUE;
            for (int j=i;j>=1;j--){// fixing the ith element
                curr_sum+=b[j];
                if(j==i){
                    ans=a[j]+dp[j-1];
                }else{
                    ans=Math.min(ans,curr_sum+dp[j-1]);
                }
            }
            dp[i]=ans;
        }
        System.out.println(dp[n]);
    }


        public static void helperOptimal() {
            Scanner sc = new Scanner(System.in);
            int n = sc.nextInt();
            long[] a = new long[n + 1];
            long[] b = new long[n + 1];
            long[][] dp = new long[n + 1][3];

            for (int i = 1; i <= n; i++) {
                a[i] = sc.nextLong();
            }

            for (int i = 1; i <= n; i++) {
                b[i] = sc.nextLong();
            }

            dp[1][0] = a[1];
            dp[1][2] = Long.MAX_VALUE;
            dp[1][1] = b[1];

            for(int i=2;i<=n;i++){
                for(int j=i;j>=1;j--){
                    long g=a[i]+Math.min(dp[i-1][0],dp[i-1][2]);
                    long v=b[i]+b[i-1]+Math.min(dp[i-2][0],dp[i-2][1]);
                    long w=b[i]+Math.min(dp[i-1][0],dp[i-1][1]);
                    dp[i][0]=g;
                    dp[i][1]=w;
                    dp[i][2]=v;
                }
            }

            System.out.println(Math.min(dp[n][0], dp[n][2]));

            sc.close();
        }


    private  static  int min(int a,int b,int c){
        return Math.min(a,Math.min(b,c));
}

}
