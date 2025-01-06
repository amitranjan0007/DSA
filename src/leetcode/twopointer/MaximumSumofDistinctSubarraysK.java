package leetcode.twopointer;

import java.util.HashMap;

public class MaximumSumofDistinctSubarraysK {
    public static void main(String[] args) {
        int[] nums={3,2,4,5,2,6,7,8,9,10};
        findLengthLargestSubArrayOfMaxSum(nums);
        int[] nums1={1,2,1,3,4,3,8,4,11};
        findLengthLargestSubArrayOfMaxSum(nums1);
    }

    private static void findLengthLargestSubArrayOfMaxSum(int nums[]){
        HashMap<Integer,Integer> map=new HashMap<>();
        int n=nums.length;
        int maxi=0;
        for(int j=0,i=0;j<n;j++){
            while(map.containsKey(nums[j])){
                map.remove(nums[i]);
                i++;
            }
            maxi=Math.max(maxi,j-i+1);
            map.put(nums[j],1);

        }
        System.out.println(maxi);
    }

}
