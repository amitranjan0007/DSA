package leetcode.twopointer;


/*
Problem No: Two Pointers: 26
https://drive.google.com/file/d/1bql5PaSyW-ncHPo7FkmNOFh8Ruql3yiq/view
https://docs.google.com/document/d/1rNsvOfgNl8SPZNZFpI1ZLpQmJqhPcaluNvDfievFs1U/edit?tab=t.0
* */
public class InfiniteArrayFindFirstOccuranceOfOnes {
    public static void main(String[] args) {
       int[]b={0,0,0,0,0,0,0,0,0,1,1,1,1,1};
        InfiniteArrayFindFirstOccuranceOfOnesOptimal(b);
    }

    private static void InfiniteArrayFindFirstOccuranceOfOnesOptimal(int[]arr){
        int start=0;
        int end=1;

        while(end<arr.length && arr[end]==0){
            start=end;
            end=end*2;
        }

        int ans=binarySearch(arr,start,end);
        System.out.println(ans);

    }

    private static int binarySearch(int[] arr, int start, int end) {
        while(start<=end){
            int mid=(start+end)/2;
            if(arr[mid]==0){
                start=start+1;
            }else {
                end=end-1;
            }
        }
        return start;
    }
}
