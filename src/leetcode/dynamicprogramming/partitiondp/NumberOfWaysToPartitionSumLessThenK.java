package leetcode.dynamicprogramming.partitiondp;

import java.util.Scanner;

public class NumberOfWaysToPartitionSumLessThenK {
    public static void main(String[] args) {
       // NumberOfWaysToPartitionSumLessThenSumEasyVersion();
        NumberOfWaysToPartitionSumLessThenSumExactlyKTimes();
    }
    private static void NumberOfWaysToPartitionSumLessThenSumEasyVersion(){
        /*
         Given an array of size ‘N’; find the number of ways to partition it such that sum of each part is <=M ;
        * **/
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();
        int givenSum=scanner.nextInt();
        int[] b=new int[n+1];
        int[]dp=new int[n+1];
        for(int i=1;i<=n;i++){
            b[i]=scanner.nextInt();
        }

        dp[0]=1;
        dp[1]=1;
        for(int i=2;i<=n;i++){
            //let's fix the last part,always remeber in partition dp we fix the last part
            int j=i;//fix last part
            int sum=b[j];
              while(j>=1 && sum<=givenSum){
                  int g=dp[j-1];
                  dp[i]+=g;
                  j--;
                  sum+=b[j];
              }
        }
        System.out.println(dp[n]);
    }

    private static void NumberOfWaysToPartitionSumLessThenSumExactlyKTimes(){
        /*
         Given an array of size ‘N’; find the number of ways to partition it such that sum of each part is <=M ;
        * **/
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();
        int givenSum=scanner.nextInt();
        int k=scanner.nextInt();
        int[] b=new int[n+1];
        int[][]dp=new int[n+1][k+1];
        for(int i=1;i<=n;i++){
            b[i]=scanner.nextInt();
        }

        dp[0][0]=1;
        dp[1][1]=1;
        for(int i=2;i<=n;i++){
            //let's fix the last part,always remeber in partition dp we fix the last part

            for(int p=1;p<=k;p++){
                int j=i;//fix last part
                int sum=b[j];
                while(j>=1 && sum<=givenSum){
                    int g=dp[j-1][p-1];
                    dp[i][p]+=g;
                    j--;
                    sum+=b[j];
                }
                System.out.println("dp[i][k] "+i+" "+p+" "+dp[i][p]);
            }

        }
        System.out.println(dp[n][k]);
    }
}
