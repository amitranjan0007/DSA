package leetcode.hashing;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MaxSumSubarrayOfK {
    //https://www.geeksforgeeks.org/problems/max-sum-subarray-of-size-k5313/1
    public static void main(String[] args) {
        int arr[]={5,5,5,10,12};
        int k=3;
        int res=maximumSumSubarray(arr,k);
        long res1=maxSumWithDistinctKUsingMap(arr,k);
        long res2=maxSumWithDistinctKUsingSet(arr,k);
       // System.out.println(res);
        System.out.println(res1);
        System.out.println(res2);

    }
    public static int maximumSumSubarray(int[] arr, int k) {
        // Code here
        int maxi=0;
        int sum=0;
        for(int i=0;i<k;i++){
            sum+=arr[i];
            maxi=Math.max(maxi,sum);
        }
        for(int j=k,i=0;j<arr.length;j++){
            sum+=arr[j];
            sum-=arr[i];
            maxi=Math.max(maxi,sum);
            i++;
        }

        return maxi;
    }
    public static long maxSumWithDistinctKUsingMap(int[] nums, int k) {
        //https://leetcode.com/problems/maximum-sum-of-distinct-subarrays-with-length-k/submissions/1477190553/
        /*
          Observation :
          As it's asking for the valid sum of window size of k with distinct , so use hashmap
          when map hit the size k then only you have valid sum,so calculate maxi each time
          Use Window Sliding Concept+Hashing

          TC: O(n)
          SC:O(K)
        * */
        HashMap<Integer,Integer> map=new HashMap<>();
        long sum =0;
        long maxi=0;
        for(int i=0;i<k;i++){
            sum+=nums[i];
            map.put(nums[i],map.getOrDefault(nums[i],0)+1);
        }
        if(map.size()==k){
            maxi=Math.max(maxi,sum);
        }
        for(int j=k,i=0;j<nums.length;j++){
            sum-=nums[i];
            if(map.containsKey(nums[i])){
                map.put(nums[i],map.getOrDefault(nums[i],0)-1);
                if(map.get(nums[i])==0){
                    map.remove(nums[i]);
                }
            }

            map.put(nums[j],map.getOrDefault(nums[j],0)+1);
            sum+=nums[j];
            if(map.size()==k){
                maxi=Math.max(maxi,sum);
            }
            i++;
        }
        return maxi;
    }


    public static long maxSumWithDistinctKUsingSet(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        long sum = 0, maxi = 0;

        for (int j = 0, i = 0; j < nums.length; j++) {
            sum += nums[j];
            while (set.contains(nums[j]) || set.size() >= k) {
                sum -= nums[i];
                set.remove(nums[i++]);
            }
            set.add(nums[j]);
            if (set.size() == k) maxi = Math.max(maxi, sum);
        }
        return maxi;
    }
}
