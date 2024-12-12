package leetcode.hashing;

public class MaxSubArraySum_KadnesAlgo {
    /*
    https://leetcode.com/problems/maximum-subarray/
    * */
    public static void main(String[] args) {

        int[] arr= {-2,1,-3,4,-1,2,1,-5,4};
        maximumSuArraySumAtAnyIndex(arr);
        maximumSuArraySum(arr);
        maxSubArraySol2(arr);
    }
    private static void maximumSuArraySumAtAnyIndex(int[] arr){
        int[] prefix = new int[arr.length+1];
        for(int i=1;i<prefix.length;i++){
            //max at any point can be,adding current element to previous max shown so far or current element itself
            prefix[i]=Math.max(prefix[i-1]+arr[i-1],arr[i-1]);
        }
        for(int i=1;i<prefix.length;i++){
            System.out.print(prefix[i]+" ");
        }
        System.out.println("");
    }

    /*
        Kadanes Algorithm
    * */
    private static void maximumSuArraySum(int[] arr){
        int prev=0;
        int curr=0;
        int maxi=Integer.MIN_VALUE;
        for(int i=1;i<=arr.length;i++){

            /*
               this is calculation we did above, but it tells maximum subarray at any point
               prefix[i]=Math.max(prefix[i-1]+arr[i-1],arr[i-1]);

               what kadnes say

               //assuming 0 based indexing
               [-1 -2 -3] , what is maximum subarray posiiable at idx=2
               -3 = -3
               -3 -2 = -5
               -3 -2 -1 = -6
               all are negative so they are dump so we say max till now is 0

                  prefix[i]=Math.max(Math.max(prefix[i-1]+arr[i-1],arr[i-1],0));
                  Let calculate on the fly without prefix sum

                   prefix[i] ---> current element
                   prefix[i-1]---> previous element

                   so replace
                   curr=Math.max(Math.max(prev+arr[i-1],arr[i-1],0));
                   till curr idx you know which is maximum
                   for whole array you have to check which is maximum

                   maxi=Math.max(maxi,curr);
                   return maxi


            * */
            curr=Math.max(Math.max(prev+arr[i-1],arr[i-1]),0);
            maxi=Math.max(maxi,curr);
            prev=curr;
        }
        System.out.print("Maximum SubArray Sum="+maxi);
    }

    public  static int maxSubArraySol2(int[] nums) {

        //Kadnes Algo
        int prev=0;
        int maxi=nums[0];

        for(int i=0;i<nums.length;i++){
            //try to write below as 0-based indexing
            //curr=Math.max(prev+nums[i-1],nums[i-1])
            prev=Math.max(prev+nums[i],nums[i]);
            maxi=Math.max(maxi,prev);
            //  prev=nums[i]; already have ans stored in prev
        }
        return maxi;
    }
}
