package leetcode.hashing;

import java.util.HashMap;

public class CountNumberOfLongestSubArray {
    /*
      Observation : We have already learnt how to find the Longest/smallest substring
      if we know the longest subarray length . Then can't we apply sliding window and check how many such subarray
      possiable of window size let say m
    * */
    public static void main(String[] args) {
       // int[] arr={2,2,2,3,1,2,1,-3,6};
        int[] arr={3,2,1,3,2,6};
        int k=6;
        int[] res=lenOfLongestSubArr(arr,k);
        int windowLengthForLongestSubArr=res[1];
        int windowLengthForSmallestSubArr=res[0];
       int countForLongestSubArray= countSubArray(arr,k,windowLengthForLongestSubArr);
        int countForSmallestSubArray= countSubArray(arr,k,windowLengthForSmallestSubArr);
        System.out.println(countForLongestSubArray+" "+countForSmallestSubArray);
    }

    public static int[] lenOfLongestSubArr(int[] arr, int k) {
        int n = arr.length;
        int pSum = 0, maxi = 0,mini=Integer.MAX_VALUE;
        HashMap<Integer, Integer> map = new HashMap<>();

        //VVI
        map.put(0, 0); // Include prefix sum 0 at "virtual" index 0 for correctness in 1-based indexing

        for (int j = 1; j <= n; j++) {
            // Calculate prefix sum
            pSum += arr[j - 1];

            // Find the required prefix to form the subarray sum k
            int x = pSum - k;

            // If found, calculate the length of the subarray
            if (map.containsKey(x)) {
                int i = map.get(x); // Get the 1-based start index
                //maxi = Math.max(maxi, j - i); // Subarray length is j - i
                if(maxi<j-i){
                   // System.out.println(i+" "+j);//1 based indexing
                    maxi = j-i;
                }
                mini=Math.min(mini,j-i);
            }

            // Only store the first occurrence of the prefix sum
            if (!map.containsKey(pSum)) {
                map.put(pSum, j); // Store the 1-based index
            }
        }

        return new int[] {mini,maxi};
    }

    //fixed size window template
    private static  int countSubArray(int[] arr,int targetSum,int windowSize){
        int sum=0,count=0;
        for(int j=0;j<windowSize;j++){
            sum+=arr[j];
        }

        if(sum==targetSum) count++;

        for(int j=windowSize;j<arr.length;j++){
             sum+=arr[j];//include the Jth element
             sum-=arr[j-windowSize];// remove the ith element 3-3=0 4-3=1 5-3=2
            if(sum==targetSum) count++;

        }
        return  count;
    }
}
