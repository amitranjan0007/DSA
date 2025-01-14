package leetcode.dynamicprogramming;

import java.util.Scanner;

public class LargestSubArrayLength_Google {
    public static void main(String[] args) {
        //LargestSubArrayLengthEasyBrute();
        //LargestSubArrayLengthOptimal();
        LargestSubArrayLengthFollowUp();
    }

    private static void LargestSubArrayLengthEasyBrute(){
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();
        int b[]=new int[n+1];
        for(int i=1;i<=n;i++){
            b[i]=scanner.nextInt();
        }
       //10
        //1 2 3 4 1 5 6 7 8 9

        int maxi=Integer.MIN_VALUE;
        for(int i=1;i<=n;i++){
            int l=1;
            for(int j=i-1;j>=1;j--){
                if(b[j]<b[j+1]){
                    l++;
                }else break;
            }
            maxi=Math.max(maxi,l);
        }

        System.out.println(maxi);
    }
    private static void LargestSubArrayLengthOptimal(){
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();
        int b[]=new int[n+1];
        int dp[]=new int[n+1];
        dp[1]=1;
        for(int i=1;i<=n;i++){
            b[i]=scanner.nextInt();
        }
        //10
        //1 2 3 4 1 5 6 7 8 9

        int maxi=Integer.MIN_VALUE;
        for(int i=2;i<=n;i++){
            if(b[i-1]<b[i]){
                dp[i]=dp[i-1]+1;
            }else{
                dp[i]=1;
            }
        }
        for(int i=1;i<=n;i++){
            maxi=Math.max(maxi,dp[i]);
        }

        System.out.println(maxi);
    }

    private static void LargestSubArrayLengthFollowUp() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] b = new int[n + 1];
        int[] dp = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            b[i] = scanner.nextInt();
        }

        dp[1] = 1;
        int maxi = Integer.MIN_VALUE;

        for (int i = 2; i <= n; i++) {
            if (b[i] > b[i - 1]) {
                dp[i] = dp[i - 1] + 1;
            } else {
                dp[i] = 1;
            }
            maxi = Math.max(maxi, dp[i]);
        }

        for (int i = 2; i < n; i++) {
            if (b[i] > b[i - 1] && b[i] - 1 > b[i + 1]) {
                int left = dp[i - 1];
                int right = (i + 2 <= n) ? dp[i + 2] : 0;
                maxi = Math.max(maxi, left + 1 + right);
            }
        }

        System.out.println(maxi);
    }
}
