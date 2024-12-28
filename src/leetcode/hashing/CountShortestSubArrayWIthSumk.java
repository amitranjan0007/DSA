package leetcode.hashing;

import java.util.HashMap;

public class CountShortestSubArrayWIthSumk {
    public static void main(String[] args) {
      int[] arr={10,5,8,7,10,4,1,10,5};
      int k=15;
        System.out.println(shortestSubArrayWithSumEqualsK(arr,k));
    }

    private static int shortestSubArrayWithSumEqualsK(int[] arr, int k) {
        int count = 0;
        int n = arr.length;
        int pSum = 0;
        int shortestLength=Integer.MAX_VALUE;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);

        for (int i = 1; i <= n; i++) {
            pSum+=arr[i-1];
            int x=pSum-k;
            if(map.containsKey(x)){
                int currentLength=i-map.get(x)+1;
                if(shortestLength>currentLength){
                    shortestLength=currentLength;
                    count++;
                }else if(shortestLength==currentLength){
                    count++;
                }
            }
            map.put(pSum,i+1);
        }
        return  count;
    }

}