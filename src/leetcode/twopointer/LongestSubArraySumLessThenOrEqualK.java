package leetcode.twopointer;

import java.util.Deque;
import java.util.LinkedList;

public class LongestSubArraySumLessThenOrEqualK {
    public static void main(String[] args) {
        int[]b={10,1,2,4,7,2};
        int k=5;
        //LongestSubArraySumLessThenOrEqualK(b,k);
        //LongestSubArraySumLessThenOrEqualKWithDiffK(b,k);
        longestSubarrayOptimal(b,k);

    }
    private static void LongestSubArraySumLessThenOrEqualK(int[]b,int k){
        int maxi=0;
        int n=b.length;
        int sum=0;
        for(int j=0,i=0;j<n;j++){
            sum+=b[j];
             while(sum>k){
                 sum-=b[i];
                 i++;
             }
             maxi=Math.max(maxi,j-i+1);
        }
        System.out.println(maxi);
    }
    private static void LongestSubArraySumLessThenOrEqualKWithDiffK(int[] b,int k){
        int maxi=0;
        int n=b.length;
        int sum=0;
        for(int j=0,i=0;j<n;j++){
            sum =b[j]-b[i];
            while(sum>k){
                i++;
                sum=b[j]-b[i];
            }
            maxi=Math.max(maxi,j-i+1);
        }
        System.out.println(maxi);
    }

    public static int longestSubarrayOptimal(int[] nums, int k) {
        // O(n^2)
        int n = nums.length;
        int ans = 0;
        Deque<Integer> maxDeque = new LinkedList<>();
        Deque<Integer> minDeque = new LinkedList<>();
        int i = 0;

        for (int j = 0; j < n; j++) {
            while (!maxDeque.isEmpty() && nums[j] >= nums[maxDeque.peekLast()]) {
                maxDeque.pollLast();
            }
            maxDeque.offerLast(j);

            while (!minDeque.isEmpty() && nums[j] <= nums[minDeque.peekLast()]) {
                minDeque.pollLast();
            }
            minDeque.offerLast(j);

            while (nums[maxDeque.peekFirst()] - nums[minDeque.peekFirst()] > k) {
                if (maxDeque.peekFirst() < minDeque.peekFirst()) {
                    i = Math.max(i, maxDeque.pollFirst() + 1);
                } else {
                    i = Math.max(i, minDeque.pollFirst() + 1);
                }
            }

            ans = Math.max(ans, j - i + 1);
        }
        System.out.println(ans);
        return ans;
    }
}
