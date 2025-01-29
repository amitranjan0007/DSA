package leetcode.twopointer;

import java.util.HashMap;
/*
          Google
Question: https://docs.google.com/document/d/1VoGtWvX3URyxl_JU8kHqrjmqxE1XFDBzuRIr-c6QPKc/edit?tab=t.0

            Leetcode
        //https://leetcode.com/contest/biweekly-contest-119/problems/length-of-longest-subarray-with-at-most-k-frequency/
        //https://leetcode.com/problems/length-of-longest-subarray-with-at-most-k-frequency/submissions/1524677949/

Problem:20
 https://drive.google.com/file/d/1o3krfRhUt87ZZktkccegeEV3KQoYekcP/view
* */
public class FindLongestSubArrayWhichHasNumberGAndOccursKTimes {
    public static void main(String[] args) {
        int[]b={5,5,6,6,8,8,6,6,6,5,5,2,2};
        int g1=5;
        int k=2;
        FindLongestSubArrayWhichHasNumberGAndOccursKTimesOptimal(b,g1,k);
        FindLongestSubArrayWhichHasNumberGAndOccursKTimesOptimal2(b,g1,k);

        int g2=6;
        int g3=10;
        FindLongestSubArrayWhichHasNumberGAndOccursKTimesOptimal_Google(b,g1,g2,g3,k);

         //LeetCode Contest
        //https://leetcode.com/contest/biweekly-contest-119/problems/length-of-longest-subarray-with-at-most-k-frequency/
        int[] nums = {1,2,3,1,2,3,1,2};
        int k1 = 2;
        FindLongestSubArrayWhichHasNumberOccursKTimes(nums,k1);
    }
    private static void FindLongestSubArrayWhichHasNumberGAndOccursKTimesOptimal(int[]b,int g,int k){
            int n=b.length;
            int[]p=new int[n];
            p[0]=b[0]==g?1:0;
            for(int i=1;i<n;i++){
                if(b[i]==g){
                    p[i]=p[i-1]+1;
                }else{
                    p[i]=p[i-1];
                }
            }

            HashMap<Integer,Integer> map=new HashMap<>();
            for(int i=0;i<n;i++){
                if(!map.containsKey(p[i])){
                    map.put(p[i],i);
                }
            }
            int maxi=Integer.MIN_VALUE;
            for(int j=0,i=0;j<n;j++){
                if(p[j]>k){
                    int l=map.get(p[j]-k);
                    i=l+1;
                }
                maxi=Math.max(maxi,j-i+1);

            }
            System.out.println(maxi);

        }

    private static void FindLongestSubArrayWhichHasNumberGAndOccursKTimesOptimal2(int[]b,int g,int k){
        int n=b.length;
        int count=0;
        int maxi=Integer.MIN_VALUE;
        for(int j=0,i=0;j<n;j++){
            if(b[j]==g)count++;
              while(count>k && i<=j){
                  if(b[i]==g){
                      count--;
                  }
                  i++;
              }
            maxi=Math.max(maxi,j-i+1);

        }
        System.out.println(maxi);

    }

    private static void FindLongestSubArrayWhichHasNumberGAndOccursKTimesOptimal_Google(int[]b,int g1,int g2,int g3,int k){
        int n=b.length;
        int count1=0;
        int count2=0;
        int count3=0;
        int maxi=Integer.MIN_VALUE;
        for(int j=0,i=0;j<n;j++){
            if(b[j]==g1)count1++;
            if(b[j]==g2)count2++;
            if(b[j]==g3)count3++;
            while((count1>k || count2>k || count3>k)&& i<=j){
                if(b[i]==g1){
                    count1--;
                }
                if(b[i]==g2){
                    count2--;
                }
                if(b[i]==g3){
                    count3--;
                }
                i++;
            }
            maxi=Math.max(maxi,j-i+1);

        }
        System.out.println(maxi);

    }



    private static void FindLongestSubArrayWhichHasNumberOccursKTimes(int[]b,int k){
        int n=b.length;
        HashMap<Integer,Integer> map=new HashMap<>();
        int maxi=Integer.MIN_VALUE;
        for(int j=0,i=0;j<n;j++){
            map.put(b[j],map.getOrDefault(b[j],0)+1);
              while(map.get(b[j])>k && i<=j){
                  map.put(b[i],map.getOrDefault(b[i],0)-1);
                  if(map.get(b[i])==0)map.remove(b[i]);
                  i++;
              }
            maxi=Math.max(maxi,j-i+1);

        }
        System.out.println(maxi);

    }

}
