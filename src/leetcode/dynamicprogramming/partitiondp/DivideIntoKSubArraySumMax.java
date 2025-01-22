package leetcode.dynamicprogramming.partitiondp;

import java.util.Arrays;
import java.util.Scanner;

public class DivideIntoKSubArraySumMax {
    public static void main(String[] args) {
       // DivideIntoKSubArraySumMaxExactlyKTimes();
       // maximumStrength();
       DivideIntoKSubArraySumMaxExactlyKTimesButYouCanLeaveSpaceInBetween();
       // helper();
    }

    public static void DivideIntoKSubArraySumMaxExactlyKTimes() {
        // int maxi=-1000005;
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();
        int k=scanner.nextInt();
        int[] b=new int[n+1];
        for(int i=1;i<=n;i++){
            b[i]=scanner.nextInt();
        }
        int[][] dp=new int[n+1][k+1];
        dp[1][1]=b[1]*k;

        for(int i=2;i<=n;i++){
            for(int j=1;j<=Math.min(k,i);j++){
                int sum=0;
                int y=k-j+1;
                for(int j1=i;j1>=1;j1--){
                    sum+=b[j1];
                    dp[i][j]=Math.max( dp[i][j],dp[j1-1][j-1]+sum*y);
                }

            }
        }
        System.out.println(dp[n][k]);
    }

    public static long DivideIntoKSubArraySumMaxExactlyKTimesButYouCanLeaveSpaceInBetween() {
        Scanner scanner=new Scanner(System.in);
        long n=scanner.nextLong();
        long k=scanner.nextLong();
        long[] b=new long[(int) (n+1)];
        for(int i=0;i<n;i++){
            b[i]=scanner.nextInt();
        }

        long[][] dp = new long[(int) (n + 1)][(int) (k + 1)];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                dp[i][j] = (long) -1e16;
            }
        }

        long[] p = new long[(int) (n + 1)];
        p[1] = b[0];
        for (int i = 2; i <= n; i++) {
            p[i] = Math.max(b[i - 1] + p[i - 1], b[i - 1]);
        }

        long sum = (long) -1e16;
        for (int i = 1; i <= n; i++) {
            sum = Math.max(sum, p[i]);
            dp[i][1] = sum * k;
        }

//        for (int i = 2; i <= n; i++) {
//            for (int j = 2; j <= Math.min(k, i); j++) {
//                long y = k - j + 1;
//                long g = 0L;
//                for (int j1 = i-1 ; j1 >= 1; j1--) {
//                    g+=b[j1];
//                    dp[i][j] = Math.max(dp[i][j], dp[j1][j - 1] + g * (y));
//                }
//                dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);
//                System.out.println(i+" "+j+" "+dp[i][j]);
//            }
//        }
        System.out.println(dp[(int) 4][(int) 1]);
        return dp[(int) n][(int) k];
    }


    public static long DivideIntoKSubArraySumMaxExactlyKTimesButYouCanLeaveSpaceInBetweenLeetcode() {
        Scanner scanner=new Scanner(System.in);
        long n=scanner.nextLong();
        long k=scanner.nextLong();
        long[] b=new long[(int) (n+1)];
        for(int i=0;i<n;i++){
            b[i]=scanner.nextInt();
        }

        long[][] dp = new long[(int) (n + 1)][(int) (k + 1)];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                dp[i][j] = (long) -1e16;
            }
        }

        long[] p = new long[(int) (n + 1)];
        p[1] = b[0];
        for (int i = 2; i <= n; i++) {
            p[i] = Math.max(b[i - 1] + p[i - 1], b[i - 1]);
        }

        long sum = (long) -1e16;
        for (int i = 1; i <= n; i++) {
            sum = Math.max(sum, p[i]);
            dp[i][1] = sum * k;
        }

        for (int i = 2; i <= n; i++) {
            for (int j = 2; j <= Math.min(k, i); j++) {
                long y = k - j + 1;
//                int j1 = i - 1;
//                long g = b[i - 1];
//                while (j1 >= 1) {
//                    if (j % 2 == 0) {
//                        dp[i][j] = Math.max(dp[i][j], dp[j1][j - 1] - g * (y));
//                    } else {
//                        dp[i][j] = Math.max(dp[i][j], dp[j1][j - 1] + g * (y));
//                    }
//                    g = g + b[j1 - 1];
//                    j1--;
//                }
                long g = 0L;
                for (int j1 = i-1 ; j1 >= 1; j1--) {
                    g+=b[j1];
                    if (j % 2 == 0) {
                        dp[i][j] = Math.max(dp[i][j], dp[j1][j - 1] - g * (y));
                    } else {
                        dp[i][j] = Math.max(dp[i][j], dp[j1][j - 1] + g * (y));
                    }
                }
                dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);
            }
        }
        System.out.println(dp[(int) n][(int) k]);
        return dp[(int) n][(int) k];
    }


    }
