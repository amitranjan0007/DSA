package leetcode.twopointer;

public class CountSubarraysWithProductLessThanK {
    public static void main(String[] args) {
        //https://leetcode.com/problems/count-subarrays-with-score-less-than-k/description/
        /*
          Prefix sum array concept comes when you have to find exact sum or K,here only two pointer approach works
        * */
        int[]nums = {2,1,4,3,5};
        long k = 10;
        System.out.println(countSubArrayHelper(nums,k));
    }
    private static long countSubArrayHelper(int[] nums,long k){

        long count=0;
        long pSum=0;
        for(int j=0,i=0;j<nums.length;j++){
            pSum+=nums[j];
            while(pSum * (j-i+1)>=k){
                pSum = pSum-nums[i];
                i++;
            }
            count+=(j-i+1);
        }
        return count;
    }
}
