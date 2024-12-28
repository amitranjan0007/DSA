package leetcode.hashing;

import java.util.HashMap;

public class CountSubArrayExactlySumEqualsK {
    public static void main(String[] args) {
   //https://www.geeksforgeeks.org/problems/subarrays-with-sum-k/1
        int[] arr= {10, 2, -2, -20, 10};
        int k = -10;
        System.out.println(countSubarraysHelper(arr,k));
    }

    private static int countSubarraysHelper(int[] arr, int k) {
        int count = 0;
        int n = arr.length;
        int pSum = 0;
        int mini=Integer.MAX_VALUE;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int i = 1; i <= n; i++) {
            pSum+=arr[i-1];
            int x=pSum-k;
            count+=map.getOrDefault(x,0);
            map.put(pSum,map.getOrDefault(pSum,0)+1);
        }
        return  count;
    }
}
