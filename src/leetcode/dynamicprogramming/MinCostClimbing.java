package leetcode.dynamicprogramming;

public class MinCostClimbing {
    public static void main(String[] args) {
        //https://leetcode.com/problems/min-cost-climbing-stairs/description/
     int cost[]={1,100,1,1,1,100,1,1,100,1};
       int res= minCostClimbingStairs(cost);
        System.out.println(res);
    }

    public static int minCostClimbingStairs(int[] cost) {

        int n=cost.length;
        int[] dp=new int[n];
        dp[0]=cost[0];
        //dp[1]=Math.min(cost[1],cost[0]+cost[1]); //when you take min cost[1] is always be min
        dp[1]=cost[1];

        for(int i=2;i<n;i++){
            int oneStep=dp[i-1]+ cost[i];
            int twoStep=dp[i-2]+cost[i];
            dp[i]= Math.min(oneStep,twoStep);
        }
        return Math.min(dp[n-1],dp[n-2]);

    }
}
