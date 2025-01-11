package leetcode.dynamicprogramming.subarray;

import java.util.Scanner;

public class SelectTwoAdjacentToMakeCostMin {
    /*
      Find the minimum cost to do this  : In one operation ; you must select two adjacent numbers ;
      and merge them to 1 number ; the cost of doing so is the sum of both numbers ;
      do this till the array has only 1 number left. Find the min cost to do please.

        [5,5,8] = [10,8] = [18] = cost = 10 + 18
        [5,5,8] = [5,13] = [18] = cost = 13 + 18

        Answer is 10 + 18 as it's the way with min cost.

        Observation 0 :- Final number in the array will always be the sum of all the numbers.




            Ex : dp[i][j] = best answer to merge the numbers from index [i….j].
            5 8 2 10
            [1 2 3 4]


            Dp[1][1] = 0
            Dp[2][2] = 0
            Dp[3][3] = 0
            Dp[4][4] = 0

            [5,8]
            Dp[1][2] = 13
            Dp[2][3] = 10
            Dp[3][4] = 12

            [5 8 2 10]

            5 8 2 →
            way1=====10+15 = 25
            way2===13 + 15 = 28

            Ans = min(way1,way2)

            Dp[1][3] = 25

            → (5) (8 2)
            → (5 8) (2)



            Dp[2][4] = cost of merging (2,2) + cost of merging (3,4) + cost of the last joining
                          = dp[2][2] + dp[3][4] + sum(2……4)
                          = 0 + dp[3][4] + sum(2…..4)

            OR

            cost of merging (4,4) + cost of merging (2,3) +  cost of the last joining
                          = dp[4][4] + dp[2][3] + sum(2……4)
                          = 0 + dp[2][3] + sum(2…..4)

            Minimum of both of them is your answer :)

            No matter whatever  we do, in final 2 will left and we merge them at final


            Dp[1][4] = option1-> dp[1][1] + dp[2][4] + sum(1……4)

                         = option2-> dp[1][2] + dp[3][4] + sum(1…..4)
                         = option3-> dp[1][3] + dp[4][4] + sum(1….4)

                         = min of all is the answer

            Algorithm:-> First calculate all the dp for subarray of size 1 -> dp[1][1]; dp[2][2]; dp[3][3]........................................................

            -> Calculate for all the subarrays of size 2 :- dp[1][2]; dp[2][3];.........
            .
            .
            .
            .
            .
            .
            .
            .
            -> Finally calculate dp[1][n].


    * */
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();
        int[]prefixSum=new int[n+1];
        int[][]dp=new int[n+1][n+1];

        int[] b=new int[n+1];
        for(int i=1;i<=n;i++){
            int num=scanner.nextInt();
            prefixSum[i]=prefixSum[i-1]+num;
            b[i]=num;
        }

        int i=1;
        while (i<=n){
            dp[i][i]=0;
            i++;
        }

         i=1;
        while (i<n){
            dp[i][i+1]=b[i]+b[i+1];
            i++;
        }


        int length=3;
        while(length<=n){
            i=1;
            while(i<=n-length+1){
                int j=i+length-1;
                /*
                  2 3 4
                  8 2 10 =>
                  dp[2][4]=dp[2][2]+dp[3][4]+sum[2...4] = [8][2 10]
                              or
                          dp[2][3]+d[[4][4]+sum[2..4] [8 2][10]



                 1 2 3 4
                 1 8 2 10
                 dp[1][4]=dp[1][1]+dp[2][4]+sum(1...4)   [1][8 2 10]
                            or
                          dp[1][2]+dp[3][4]+sum(1...4)    [1 8][2 10]
                             or
                          dp[1][3]+dp[4][4]+sum(1...4)    [1 8 2] [10]


                  where k= start from i and go to <j
                 dp[i][j]=dp[i][k]+dp[k+1][j]+(prefixSum[j]-prefixSum[i-1]
                 dp[2][4]=dp[2][2]+dp[3][4]+sum[2...4]
                          dp[2][3]+d[[4][4]+sum[2..4]


                * */
                  int k=i;
                  int ans=Integer.MAX_VALUE;
                    while(k<j){
                        ans=Math.min(ans,dp[i][k]+dp[k+1][j]+(prefixSum[j]-prefixSum[i-1]));
                        k++;
                    }
                    dp[i][j]=ans;
                i++;
            }
            length++;
        }
        System.out.println(dp[1][n]);
    }
}
