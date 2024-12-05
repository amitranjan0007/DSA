package leetcode.twopointer;

import java.util.*;

public class ThreeSum {
    public static void main(String[] args) {
        int [] arr={-1,0,1,2,-1,-4} ;

        List<List<Integer>> result=solveTriplet(arr,0);
        Set<List<Integer>> set = new HashSet<>(result);

        //iterate over the set
        for (List<Integer> integers : set) {
            System.out.println(integers);

        }

          //or we can do like below
//        result=new ArrayList<>(set);
//        for(List<Integer> list:result ){
//            System.out.println(list);
//        }
    }
    private static List<List<Integer>> solveTripletBruteForce(int[] arr){
        List<List<Integer>> result=new ArrayList<>();
        Arrays.sort(arr);
        for(int i=0;i<arr.length;i++){
            for(int j=i+1;j<arr.length;j++){
                for(int k=j+1;k<arr.length;k++){
                    if(arr[i]+arr[j]+arr[k]==0){
                        List<Integer> tmpList=new ArrayList<>();
                        tmpList.add(arr[i]);
                        tmpList.add(arr[j]);
                        tmpList.add(arr[k]);
                        result.add(tmpList);
                    }
                }
            }
        }
        return result;
    }

    private static List<List<Integer>> solveTriplet(int[] arr,int target){
        Arrays.sort(arr);
        int n=arr.length;
        List<List<Integer>> result= new ArrayList<>();
        for(int i=0;i<n-2;i++){
            int newTarget = target-arr[i];
            int[] twoSum=solveTwoSum(arr,i+1,n-1,newTarget);
            if(twoSum.length==2){
                ArrayList<Integer> tmpList=new ArrayList<>();
                tmpList.add(arr[i]);
                tmpList.add(twoSum[0]);
                tmpList.add(twoSum[1]);
                result.add(tmpList);
            }
        }
        return result;
    }
    private static int[] solveTwoSum(int[] arr,int l,int h,int target){
        while(l<h){
            if(arr[l]+arr[h]==target){
                return new int[]{arr[l],arr[h]};
            }else if(arr[l]+arr[h]<target){
                l++;
            }else{
                h--;
            }
        }
        return new int[]{};
    }
}
