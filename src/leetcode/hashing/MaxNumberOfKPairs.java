package leetcode.hashing;

import java.util.HashMap;
import java.util.Map;

public class MaxNumberOfKPairs {
    /*
    https://leetcode.com/problems/max-number-of-k-sum-pairs/description/
    Observation:
     arr = 1 1 1 1 2 2 8 8 8 8 8 9 9
     K=10

     Map
      1->4
      2->2
      8->5
      9->2

     Iterate the map
     x=1 y=K-x=10-1=9

     for x=1 the value is 4 and for y=9 the value is 2 ===> so how many pair it form min(4,2)=2,means two number of x can make pair of 2 pair of 9
     so total operation did is 2. & then remove from map,

     x=2 y=k-x=10-2=8
      for x=2 the value is 2 and for y=8 the value is 5 ===> so how many pair it form min(2,5)=2,means two number of x can make pair of two pair of 8
     so total operation did is 2. & then remove from map,

     so total =4

     remember while iterating the map you can't remove so take visited map also





    * */
    public static void main(String[] args) {
        int arr[]={3,1,3,4,3};
        int k=6;
        System.out.println(maxOperationsToCountPairEqualToKWayOptimal(arr,k));
        int arr1[]={1,2,3,4};
        int k1=5;
        System.out.println(maxOperationsToCountPairEqualToKWayOptimal(arr1,k1));
        int arr2[]={1,2,6,6,5};

        //Google Follow Up Question
        maxOperationsWayFollowUpGoogleQuestion(arr2);
    }

    public static void maxOperationsBruteWay(int[] nums, int k) {
        int n=nums.length;
        boolean[] visited=new boolean[n];
        int count=0;
        for(int i=0;i<n-1;i++){
            if(visited[i]) continue;
            for(int j=i+1;j<n;j++){
                if(visited[j]) continue;
                if(nums[i]+nums[j]==k){
                    visited[j]=true;
                    count++;
                    break;
                }
            }
        }
        System.out.println(count);
    }
    public static  int maxOperationsToCountPairEqualToKWayOptimal(int[] nums, int k) {
        int n=nums.length;
        HashMap<Integer,Integer> map=new HashMap<>();
        HashMap<Integer,Boolean> visited=new HashMap<>();
        for(int i=0;i<n;i++){
            map.put(nums[i],map.getOrDefault(nums[i],0)+1);
        }
        int count=0;

        for(Map.Entry<Integer,Integer> enteries:map.entrySet()){
            int x=enteries.getKey();
            int y= k-x;

            if(!visited.containsKey(x) && !visited.containsKey(y)){
                if(x==y){
                    count+=map.get(x)/2;
                }else{
                    count+=Math.min(map.get(x),map.getOrDefault(y,0));
                }
            }

            visited.put(x,true);
            visited.put(y,true);

        }
       // System.out.println(count);
        return count;
    }
    public static void maxOperationsWayFollowUpGoogleQuestion(int[] nums) {
        /*
        https://www.desiqna.in/15068/google-oa-swe-intern-july-2023-solution-by-kumar-k
        You are given an array of size “N”; try to find the maximum number of pairs whose sum is “K”;
         but “K” is unknown. Fix such a K ; that the number of pairs comes as maximum!
        Solution 1:
        You can run the algorithm for K = 1 ;
        You can run the algorithm for K = 2 ;
        .
        .
        .
        .
        .
        .
        .
        ..
        You can run the algorithm for K = sum of two largest numbers(maximum pair sum.)

        Issue
        sum of two largest numbers= 2*10^9 ahhhhh TLE, see constaraint num[i] can go upto 10^9

        Solution 2:
           the possible values of k are only the sums of pairs in array
           1,2,6,6,5
           what is possiable K value of pairs
           (1,2)=3
           (1,6)=7
           (1,5)=6
           (2,6)=8
           (2,5)=7(already above)
           (6,5)=11
        * */
        //tc:O(n^3) it will work, in constraint N is 0<=N<=100
        int n=nums.length;
        int ans=0;
        int maxi=0;
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                int k=nums[i]+nums[j];
                ans = maxOperationsToCountPairEqualToKWayOptimal(nums,k);
                maxi=Math.max(maxi,ans);
            }
        }
        System.out.println(maxi);
    }
}
