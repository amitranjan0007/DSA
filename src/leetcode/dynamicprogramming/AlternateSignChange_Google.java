package leetcode.dynamicprogramming;

import java.util.Scanner;

public class AlternateSignChange_Google {
    //https://www.desiqna.in/17037/google-girl-hackathon-oa-2024-april-2024-set-1
    /*
    Understanding - Divide the array in “k” parts such that the sum of all the parts is maximum.

            Sum of a part [a1 a2 a3 a4……] = a1 -a2 + a3 -a4……..

            What if all the numbers are positive?

            Sum of the array is the answer and we keep each number in separate parts -> Total N parts.


            What if the numbers are negative :- Watch.



            dp[i][+] = best block sum you can get till index “i”  such that + sign enforced on the number b[i](+)

            dp[i][-] =  best block sum you can get till index “i” such that -sign is enforced on the last number b[i](-)

            When you are calculating dp[i][+] = you can always assume that the new block is starting from [b[i] ……….]

            dp[i][+] = b[i] + max(dp[i-1][+],dp[i-1][-])

            dp[i][-] = -(b[i]) + dp[i-1][+] (Why?)

            Because if the current number you are making is negative then it is compulsory to make the previous number positive.


            Final answer - dp[n][+] OR dp[n][-]

    * */
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();
        int[] arr=new int[n+1];
        for(int i=1;i<=n;i++){
            arr[i]=scanner.nextInt();
        }
        int dp[][]=new int[n+1][2];
        dp[1][0]=arr[1];
        dp[1][1]=Integer.MIN_VALUE;
        dp[2][0]=dp[1][0]+arr[2];
        dp[2][1]=arr[1]-arr[2];
        for(int i=3;i<=n;i++){
            dp[i][0]=arr[i]+Math.max(dp[i-1][0],dp[i-1][1]);
            dp[i][1]=dp[i-1][0]-arr[i];
        }
        int res=Math.max(dp[n][0],dp[n][1]);
        System.out.println(res);
    }
}
