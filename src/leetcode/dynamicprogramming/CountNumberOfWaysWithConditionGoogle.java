package leetcode.dynamicprogramming;

import java.util.Scanner;

public class CountNumberOfWaysWithConditionGoogle {
    public static void main(String[] args) {
      //  countNumberOfWaysBasic();
        countNumberOfWaysFollowUp();
    }

    private static void countNumberOfWaysFollowUp() {
        /*
          find number of ways to reach from index n ,starting at index 1
          The array contains 1 & 2 only ,if array contains 1 then you can make 1 jump, if array contains 2,
          you can make 2 jump
          Through out your journey you can jump utmost 1 backward jump
          and you have to visit idx 1 time only


          Thought Process
          Very important : 1 backward jump of 1/2 through out the journey
                           You have to visit idx 1 time only

          1 2 3 4 5 6 7 8
          you can reach at idx 8 with following condition : Please pay attention
          1)Jump of 2 is not possible ? Why

           1 2 3 4 5 6 7 8
           1 2 2 1 2 2 2 2

           Let suppose you are at idx 7 and decided to take 2 jump,
           but how you reached to idx 7 ? wither from idx 5 or idx 6,right,
           it means either idx 5 or 6 is visited so you come to idx 7

           so from 7 you can't go to 5 by 2 jump. --- This is one of the scenerio when idx 5 is visted
           Now let suppose idx 5 is not visited and we reached 7 via 6 then ?

           so we go 7 to idx 5 then take as idx 5 has 2 so we decide to take 1 or 2 jump
           but we already visited the idx 6 or idx 7 :) so you trapped

          So we concluded that Forward Jump can be either 1 0r 2
          And Backward Jump will be only 1 step and utmost one backward though out the journey
          dp[i]-> Number of ways to reach the idx 'i'

          How you calculate number of ways : Let say you have to reach f , so by below diagram 3 ways

          A->b->c->d->e->f
                         |
          k----l---------|
                         |
          m-----n---p--q-|

          But we need another state so that we can determine in whole journey we have also taken the 1 backward
          steps
          dp[i][0]--- number of ways to reach idx i by forward jump
          do[i][1]-- number of ways to reach idx i by backward jump, if an only if (i-1) previous is not visited


          dp[i][0] -- it's easy to calculate
            1 2 3 4 5 6 7 8
            1 2 2 1 2 2 2 2
            You can reach idx 8 -- either by 7 or either by idx 6(but idx 6 contains 2)
            dp[i][0]= dp[i-1][0] + ((arr[i-2]==2)?dp[i-2][0] : 0)

          But calculating dp[i][1] -- it's not easy to calculate :) So check whole scenerio
          We have to take 1 step backward and then reach to idx 8

          Case: 1
                     ↓-↓
           1 2 3 4 5 6 7 8
           1 2 2 1 2 2 2 2
                     ↓---↑
           7->6 Backward then 6->8
           dp[i][1]= dp[i-1][1]

          Case: 2
                   ↓-↓
           1 2 3 4 5 6 7 8
           1 2 2 1 2 2 2 2
                   ↓---↑-↑
          6->5 backward & from 5 jump to 7 then to 8
          dp[i][1]= dp[i-2][1]

          Case: 3
                     ↓-↑
           1 2 3 4 5 6 7 8
           1 2 2 1 2 2 2 2
                   ↓----↑
                     ↓---↑
          from 5 go to 7 in forward fashion , then make backward jump of 1 ,means 7->6 then 6->8
          dp[i][1]= dp[i-2][1]
          dp[i][1]= dp[i-3][0]+dp[i-1][1]

         that's all
         ans: dp[n][0]+dp[n][1]
        * */
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();
        int b[]=new int[n+1];
        for(int i=1;i<=n;i++){
            b[i]=scanner.nextInt();
        }
        int[][] dp = new int[n + 1][2];
        dp[1][0] = 1;
        dp[1][1] = 0; //there is no number
        dp[2][0]=1;
        dp[2][1]=0;// how you reached idx 2 ,via 1 so 1 is visted already so you can't go back to visited idx again

        for(int i=3;i<=n;i++){
            dp[i][0]= dp[i-1][0] + ((b[i-2]==2)?dp[i-2][0] : 0);
            dp[i][1]= dp[i-1][1];
               if(b[i-2]==2){
                   dp[i][1]=dp[i-2][1];
               }
               if(b[i-3]==3){
                   dp[i][1]=dp[i-3][0]+dp[i-1][1];
               }
        }
        int result=dp[n][0]+dp[n][1];
        System.out.println(result);
    }

    public static int countNumberOfWaysBasic() {
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();
        int b[]=new int[n+1];
        for(int i=1;i<=n;i++){
            b[i]=scanner.nextInt();
        }
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 1;


        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1];
            if (b[i - 2] == 2) {
                dp[i] += dp[i - 2];
            }
        }

        return dp[n];
    }
}
