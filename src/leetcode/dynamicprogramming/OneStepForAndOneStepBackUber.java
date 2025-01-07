package leetcode.dynamicprogramming;

public class OneStepForAndOneStepBackUber {
    public static void main(String[] args) {
        solve(5);
    }
    private static void solve(int n){
        int[] dp=new int[n+1];
        dp[1]=0;
        for(int i=2;i<=n;i++){
            int costForEven=Integer.MAX_VALUE;
            int costForOdd=Integer.MAX_VALUE;
            if((i%2)==0){
                costForEven=1+dp[i/2];
            }else{//odd
                /*
                 Here I move i step forward & 1 step backward
                 backward we know the value : 1+dp[i-1],but not forward
                 odd+1=even
                 ahh : so cost added is dp[i+1]=1+(1+dp[(i+1)/2])
                * */
                costForOdd=Math.min(1+dp[i-1],1+(1+dp[(i+1)/2]));
            }
            dp[i]=Math.min(costForEven,costForOdd);
        }
        System.out.println(dp[n]);
    }
}
