package leetcode.dynamicprogramming;

public class MaxSumJump {
    /*
    You are given an array of size “N” ; you have to start your journey at index “1” and
    you need to end your journey at index “N”.
    You can make jumps of size -> 1 or 3 or 5

    In the array positive as well as negative numbers can be available.

    Please find the maximum sum of journey

    Example: [5 8 3 100 -5 -5 5 10]
    Output: 131

    what is

     dp[i]= maximum sum till the ith idx
     dp[0]=arr[0] ===what is the maximum sum till index 0

     */
    public static void main(String[] args) {
       int arr[]={5,8,3,100,-5,-5,5,10};
        int res=helper(arr);
        System.out.println(res);
    }

    private static int helper(int[] arr){
        int n=arr.length;
        int[] dp=new int[n];
        int maxi=0;
        dp[0]=arr[0];
          for(int i=1;i<n;i++){
              int takeStepOne= arr[i]+dp[i-1];
              int takeStepThree= 0;
                if(i>=3){
                    takeStepThree=arr[i]+dp[i-3];
                }
              int takeStepFive=0;
              if(i>=5){
                    takeStepFive=arr[i]+dp[i-5];
                }

              maxi=Math.max((Math.max(Math.max(maxi,takeStepOne),takeStepThree)),takeStepFive);
              dp[i]=maxi;
          }

          return dp[n-1];

    }
}
