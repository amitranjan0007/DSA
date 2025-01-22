package leetcode.dynamicprogramming.partitiondp;

import java.util.Scanner;

public class FindNumberOfWaysEachContigiousPartHasAtlestOneNegativeNumber {
    public static void main(String[] args) {
        /*
        [-2 1 -3 -4]
        output: 6
          [-2 1 -3] [-4]=valid               [-2 1] [-3 -4]=valid             [-2 ] [1 -3 -4]=valid       [-2 1 -3 -4]=valid
          [-2 1] [-3] [-4]=valid             [-2 ] [1 ][-3 -4] = invalid
          [-2] [1 -3] [-4]=valid

        * */
      //  solveSubOptimal_First();
      //  solveSubOptimal_Second();

        solveSubOptimal();
    }

    public static void solveSubOptimal() {
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();
        int[] dp=new int[n+1];
        int[] b=new int[n+1];
        for(int i=1;i<=n;i++)b[i]=scanner.nextInt();

        int prev=-1;
        for(int i=1;i<=n;i++){
            if(b[i]>0){
                dp[i]=dp[i-1];
            }else{
                if(prev!=-1){
                    /*
                        1 2 3 4 5 6 7
                        - + + + + + -  => (7-1)+1=7 =>count= i-prev+1

                            -         +   +     +     +      +      -
                      idx=> 10      11    12    13    14     15     16
                            dp[i]  dp[i] dp[i] dp[i] dp[i]  dp[i]  dp[i]=dp[i-1],dp[i] only
                            total = 7*dp[i]==> count*dp[i]
                    * */
                    int total= i-prev+1;
                    dp[i]=dp[i-1]*total;//dp[i]= dp[i-1]//same vslue as prev
                }else {
                    dp[i]=1;// if we encounter first time -ve then only one way [2 2 2 2 -1]

                }
                prev=i;//keep track of last previous
            }
        }
        System.out.println(dp[n]);
    }

    public static void solveSubOptimal_Second() {
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();
        int[] dp=new int[n+1];
        int[] b=new int[n+1];
        for(int i=1;i<=n;i++)b[i]=scanner.nextInt();
        dp[0]=1;
        for(int i=1;i<=n;i++){
            int c=0;
            for (int j=i;j>=1;j--){
                if(b[j]<0) c++;

                if(c>=1){
                    dp[i]+=dp[j-1];
                }
            }
        }
        System.out.println(dp[n]);
    }

    public static void solveSubOptimal_First() {
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();
        int[] dp=new int[n+1];
        int[] b=new int[n+1];
        for(int i=1;i<=n;i++)b[i]=scanner.nextInt();
        dp[0]=1;
        for(int i=1;i<=n;i++){
            for (int j=i;j>=1;j--){
                if(isValid(b,j,i)){
                    dp[i]+=dp[j-1];
                }
            }
        }
        System.out.println(dp[n]);
    }

    private static boolean isValid(int[] b, int start, int end) {
         for(int k=start;k<=end;k++){
             if(b[k]<0) return true;
         }
         return false;
    }



}
