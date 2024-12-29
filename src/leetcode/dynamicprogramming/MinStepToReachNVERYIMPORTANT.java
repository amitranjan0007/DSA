package leetcode.dynamicprogramming;

public class MinStepToReachNVERYIMPORTANT {
    public static void main(String[] args) {
        int res=minStep(6);
        System.out.println(res);

        //follow up question from uber
        int res1=minStepFollowUp(5);
        System.out.println(res1);

       // int arr[]= {2,10,8,-5,-10,5};
        int arr[]= {2,-100,8,5,0};
        int res2=minCostFollowUpThird(arr);
        System.out.println(res2);
    }

    private static int minStep(int n){
        /*
        You are given a positive integer 'N’.
        Your task is to find and return the minimum number of steps that 'N' has to take to get reduced to 1.
        You can perform any one of the following 3 steps:
        1) Subtract 1 from it. (n = n - 1) ,
        2) If n is divisible by 2, divide by 2.( if n % 2 == 0, then n = n / 2 ) ,
        3) If n is divisible by 3, divide by 3. (if n % 3 == 0, then n = n / 3 ).

        * */
        int[]dp=new int[n+1];
        dp[1]=0;
        for(int i=2;i<=n;i++){
          int takeStepOne= 1+dp[i-1];
          int takeStepTwo=Integer.MAX_VALUE;
           if(i%2==0){
               takeStepTwo=1+dp[i/2];
           }
          int takeStepThree=Integer.MAX_VALUE;
            if(i%3==0){
                takeStepThree=1+dp[i/3];
            }
          dp[i]=Math.min(Math.min(takeStepOne,takeStepTwo),takeStepThree);
        }
        return dp[n];
    }

    private static int minStepFollowUp(int n){
        /*
        You are given a positive integer 'N’.
        Your task is to find and return the minimum number of steps that 'N' has to take to get reduced to 1.
        You can perform any one of the following 3 steps:
        Reduce n to 1
        If the number is even you can divide it by 2.
        If the number is odd you can do +1 or -1

        Observation:
            even
              dp[i]=1+dp[i/2]
            odd
              dp[i]= {
                      1+dp[i-1]
                      or
                      1+dp[i+1] //this is real problem ,let analyse , if i is odd adding one to it make even
                                // so i+1 is even means it divisable by 2,so one more cost will increase
                                1+(1+dp[(i+1)/2]
                     }


        * */
        int[]dp=new int[n+1];
        dp[1]=0;
        for(int i=2;i<=n;i++){
            int takeStepTwo=Integer.MAX_VALUE;
            if(i%2==0){
                takeStepTwo=1+dp[i/2];
            }
            int takeStepOddPlusOne=Integer.MAX_VALUE;
            int takeStepOddMinusOne=Integer.MAX_VALUE;
            if(i%2!=0){
                takeStepOddMinusOne=1+dp[i-1];
                takeStepOddPlusOne=1+(1+dp[(i+1)/2]);
            }

            dp[i]=Math.min(Math.min(takeStepOddPlusOne,takeStepTwo),takeStepOddMinusOne);
        }
        return dp[n];
    }

    private static int minCostFollowUpThird(int[] arr){

        /*
        An array of costs was given.
        You always start at index 1.
        You can either take two jumps forward or one jump backward. If you land on a particular index,
        you have to add the cost to your total.
        Find the minimum cost needed to cross the array or reach the end of the array.
        You can visit any index for only 1 time.

        b = [ 2,10,8,-5,-10,5 ]

        Observation:
          Observation 1:  You can visit any index for only 1 time. What it means think of it
            Let denote F-> forward
                       B-> backward
            0 1 2 3 4 5 6 7 8 9 10 11 12 13 14
            F F F B F F F B F F F  F  F   B  F

            what you observe from above pattern ?
            we have moved from index 4 to index 3 so idx 3 is visited once ,but we can't go more step behind because index 2 already visited
            similarly from index 8 we moved to index 7 and similarly 13

            Observation 2: minimum cost needed to cross the array or reach the end of the array.
            means you can cross the 'n' also or we can be at 'n' also

            when you cross n? 1,2,3.....n-2,n-1,n
            when you at n-1 , we can cross the boundary by 2 step ,is there any other way , answer is no

            Now....

            1.............i-1,i,i+1.............n
            dp[i]=  minimum cost needed to jump two step ahead or one step back

            How you know from particular point you have reached is from forward or the backward direction?
            ahh :) Let's introduce another variable 0-> Forward 1->Backward

            Moving Forward by 2 direction ahead,at i-2 how you reached either forward or backward

            Equation 1: dp[i][0]=cost[i]+min(dp[i-2][0],dp[i-2][1])

             1.............i-1,i,i+1.............n
             how you can reach i from backward , so you have to be at i+1 then move to i ,and how you reach i+1,from i-1
             Equation 2 :dp[i][1]=cost[i+1]+cost[i]+dp[i-1]


            where your answer be ?
            you can reach 'n' and outside 'n'
            See Observation 1 ,outside boundary is from n-1

            so dp[n] or dp[n-1][



        * */
        int n = arr.length - 1; // Adjust for 1-based indexing
        int[][] dp = new int[n + 1][3];
        int INF = 100000000;
        // Initialize base cases
        dp[1][2] = arr[1]; // Cost of starting at index 1
        dp[1][1] = INF; // Can't move backward from index 1
        dp[2][2] = INF; // Can't jump forward by 2 from index 1
        dp[2][1] = dp[1][2] + arr[2] + arr[3]; // Cost to move back to index 2 from 3

        // Fill the DP table
        for (int i = 3; i <= n - 1; i++) {
            dp[i][2] = arr[i] + Math.min(dp[i - 2][1], dp[i - 2][2]); // Forward by 2
            dp[i][1] = arr[i] + arr[i + 1] + dp[i - 1][2]; // Backward from i+1
        }

        // Handle the last index and beyond
        dp[n][2] = arr[n] + Math.min(dp[n - 2][1], dp[n - 2][2]); // Forward to index n
        dp[n][1] = INF; // Can't go back from beyond n

        // Find the minimum cost
        return Math.min(Math.min(dp[n][2], dp[n - 1][2]), dp[n - 1][1]);

    }
}
