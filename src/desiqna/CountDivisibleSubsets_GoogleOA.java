package desiqna;

import java.util.Scanner;


/*
Enter the value of M
3
Enter the value of K
2
Enter the length of array
4
Enter the 4 Number
1
2
3
6
result--2 => [{1,2},{3,6}]
* */

/*
Real-Given “N” coins; each ranging from 0 to N-1 -> find the number of ways to pick a subset of size “K” so that the sum is divisible by “M”.

-> dp[i][j][k] = number of ways to pick a subset from the first “i” element such that the sum when divided by M leaves a remainder j. And the size of subset is K

* */
public class CountDivisibleSubsets_GoogleOA {
    public static void main(String[] args) {
        Scanner scanner= new Scanner(System.in);
        System.out.println("Enter the value of M");
        int m=scanner.nextInt();

        System.out.println("Enter the value of K");
        int k=scanner.nextInt();

        System.out.println("Enter the length of array");
        int n= scanner.nextInt();

        int num[]=new int[n];
        System.out.println("Enter the "+n+" Number");
        for(int i=0;i<n;i++){
            num[i]=scanner.nextInt();
        }

        int result1=countSubsetSumEqualK(num,k);
        int result2= countSubsetDivisibleByM(num,m);
        int result3 = countSubsetExactlyKSize(num,m,k);
        System.out.println("count SubsetSum Equal to K is "+result1);
        System.out.println("count SubsetSum Divsiable by K is "+result2);
        System.out.println("count Subset Exactly K Size is "+result3);

    }

    private static int countSubsetSumEqualK(int[] num, int k) {
        int n=num.length;
        int[][] dp=new int[n+1][k+1];
        dp[0][0]=1;
        for(int i=1;i<=n;i++){
            for(int j=0;j<=k;j++){
                dp[i][j]+=dp[i-1][j];//if not pick ith element

                if(j>=num[i-1])
                  dp[i][j]+=dp[i-1][j-num[i-1]];
            }
        }
        return dp[n][k];
    }

    private static int countSubsetDivisibleByM(int[] num, int m) {
        /*
          Approach: dp[i][j] = number of ways to pick a subset from the first “i” element such that the sum when divided by M leaves a remainder j. //RRRRR

        * */
        int n=num.length;
        int[][] dp=new int[n+1][m];
        dp[0][0]=1;//empty subset is always divisiable by M , so 1 subset always possiable
        for(int i=1;i<=n;i++){
            for(int j=0;j<m;j++){
               dp[i][j]+=dp[i-1][j];//if not pick ith element
               int v1= num[i-1]%m;
               int l=(j + m - v1) % m;
               dp[i][j]+=dp[i-1][l];
            }
        }
        return dp[n][0];
    }

    private static int countSubsetExactlyKSize(int[] num, int m,int K) {

        /*
         dp[i][j][k] = number of ways to pick a subset from the first “i” element such that the sum when divided by M leaves a remainder j. And the size of subset is K
        * */
        int n=num.length;
        int[][][] dp=new int[n+1][m][K+1];
        dp[0][0][0]=1;//empty subset is always divisiable by M , so 1 subset always possiable
        for(int i=1;i<=n;i++){
            int v1= num[i-1]%m;
            for(int j=0;j<m;j++){
              for(int k=0;k<=K;k++){
                  dp[i][j][k]+=dp[i-1][j][k];//if not pick ith element
                  if(k>0){
                      int l=(j + m - v1) % m;
                      dp[i][j][k]+=dp[i-1][l][k-1];//if we picked
                  }

              }
            }
        }
        return dp[n][0][K];
    }
}