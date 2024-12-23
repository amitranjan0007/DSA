package leetcode.hashing;

import java.util.HashMap;

public class LongestSubArraywithSumK {
    public static void main(String[] args) {
        //Longest Sub-Array with Sum K
        int[] arr={10, 5, 2, 7, 1, 9};
        int k=15;
        int res=lenOfLongestSubarr(arr,k);
        System.out.println(res);
    }

    public static  int lenOfLongestSubarr(int[] arr, int k) {
        // code here
        int n=arr.length;
        HashMap<Integer,Integer> map=new HashMap<>();
        int sum=0;
        int maxi=0;
        for(int i=0;i<n;i++){
            sum+=arr[i];
            if(sum==k) maxi=Math.max(maxi,i+1);
            if(map.containsKey(sum-k)){
                int j=map.get(sum-k);
                maxi=Math.max(maxi,i-j);//j-i+1
            }

            if(!map.containsKey(sum)){// to be maximum don't update the idx so that it can lie far
                map.put(sum,i);
            }


        }
        return maxi;
    }
}
