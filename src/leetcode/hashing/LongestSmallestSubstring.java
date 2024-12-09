package leetcode.hashing;

import java.util.HashMap;

public class LongestSmallestSubstring {
    public static void main(String[] args) {
        int arr[] ={3,1,3,-2,2};
        int sum=4;
        int[] res=lenOfLongestSubarr2(arr,sum);
        System.out.println(res[0]);
        System.out.println(res[1]);
    }

    //1-based indexing
    public static int[] lenOfLongestSubarr(int[] arr, int k) {
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
                maxi = Math.max(maxi, j - i); // Subarray length is j - i
                mini=Math.min(mini,j-i);
            }

            // Only store the first occurrence of the prefix sum
            if (!map.containsKey(pSum)) {
                map.put(pSum, j); // Store the 1-based index
            }
        }

        return new int[] {mini,maxi};
    }


    //0-based indexing
    public static int[] lenOfLongestSubarr2(int[] arr, int k) {
        int n = arr.length;
        int pSum = 0, maxi = 0,mini=Integer.MAX_VALUE;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1); // Include prefix sum 0 at "virtual" index 0 for correctness in 0-based indexing

        for (int j = 0; j <n; j++) {
            // Calculate prefix sum
            pSum += arr[j];

            // Find the required prefix to form the subarray sum k
            int x = pSum - k;

            // If found, calculate the length of the subarray
            if (map.containsKey(x)) {
                int i = map.get(x); // Get the 0-based start index
                maxi = Math.max(maxi, j - i); // Subarray length is j - i
                mini=Math.min(mini,j-i);
            }

            // Only store the first occurrence of the prefix sum
            if (!map.containsKey(pSum)) {
                map.put(pSum, j); // Store the 0-based index
            }
        }

        return new int[] {mini,maxi};
    }
}
