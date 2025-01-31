package leetcode.twopointer;

import java.util.Arrays;
import java.util.HashMap;

public class FindMedianAfterGeneratingAllSubArrayWithDistinctElementCount {

    public static void main(String[] args) {
     int[] b={1,2,1,1,4,5,3,3};
        FindMedianAfterGeneratingAllSubArrayWithDistinctElementCountBrute(b);
        FindMedianAfterGeneratingAllSubArrayWithDistinctElementCountSubOptimal(b);
        FindMedianAfterGeneratingAllSubArrayWithDistinctElementCountOptimal(b);
    }
    private static void FindMedianAfterGeneratingAllSubArrayWithDistinctElementCountBrute(int[]b){
        int n=b.length;
        int totalSubArray=(n*(n+1))/2;
        int[] arr=new int[totalSubArray];
        int count=0;
        for(int i=0;i<n;i++){
            HashMap<Integer,Integer> map=new HashMap<>();
            for(int j=i;j<n;j++){
                map.put(b[j],map.getOrDefault(b[j],0)+1);
                arr[count++]=map.size();
            }
        }

        Arrays.sort(arr);
        int ans= findMiddleElement(arr,0,arr.length-1);
        System.out.println(ans);
    }

    private static int findMiddleElement(int[] arr, int low, int high) {
        int n=arr.length;
        int middleElement=((low+high)/2)+1;
        if(n==1) return arr[0];
        if(n%2==0 && n>1){
            return (arr[middleElement]+arr[middleElement-1])/2;
        }else{
            return arr[middleElement];
        }
    }

    private static void FindMedianAfterGeneratingAllSubArrayWithDistinctElementCountSubOptimal(int[]b){
        int n = b.length;
        long totalSubArrays = (long) n * (n + 1) / 2; // Total number of subarrays
        long medianPosition = (totalSubArrays + 1) / 2; // Position of the median

        // Linear search to find the smallest k such that count >= medianPosition
        int k = 1;
        while (k <= n) {
            int count = countLargestSubArrayLessThenEqualK(b, k);
            if (count >= medianPosition) {
                break;
            }
            k++;
        }

        System.out.println(k); // Output the median
    }

    private static void FindMedianAfterGeneratingAllSubArrayWithDistinctElementCountOptimal(int[]b){
        int n = b.length;
        long totalSubArrays = (long) n * (n + 1) / 2; // Total number of subarrays
        long medianPosition = (totalSubArrays + 1) / 2; // Position of the median

        // Linear search to find the smallest k such that count >= medianPosition
//        int k = 1;
//        while (k <= n) {
//            int count = countLargestSubArrayLessThenEqualK(b, k);
//            if (count >= medianPosition) {
//                break;
//            }
//            k++;
//        }

        long l=1;
        long h=totalSubArrays;
        while(l<=h){
            long mid=(l+h)/2;
            int count = countLargestSubArrayLessThenEqualK(b, mid);
              if(count<medianPosition){
                  l=mid+1;
              }else{
                  h=mid-1;
              }
        }
        System.out.println(l); // Output the median
    }


    private static int countLargestSubArrayLessThenEqualK(int[]b,long k){
        int sum=0;
        HashMap<Integer,Integer> map=new HashMap<>();
        int n=b.length;
        for(int j=0,i=0;j<n;j++){
            map.put(b[j],map.getOrDefault(b[j],0)+1);
            while (map.size()>k){
                map.put(b[i],map.getOrDefault(b[i],0)-1);
                if(map.get(b[i])==0)map.remove(b[i]);
                i++;
            }

            sum=sum+(j-i+1);
        }
        return sum;
    }


}
