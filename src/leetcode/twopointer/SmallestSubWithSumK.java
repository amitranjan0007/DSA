package leetcode.twopointer;

public class SmallestSubWithSumK {
    public static void main(String[] args) {
        //https://www.geeksforgeeks.org/problems/smallest-subarray-with-sum-greater-than-x5651/1
        int x = 51;
        int arr[] = {1, 4, 45, 6, 0, 19};
        System.out.println(smallestSubWithSum(x,arr));
    }

    public static int smallestSubWithSum(int x, int[] arr) {
        // Your code goes here
        int sum=0;
        int mini=Integer.MAX_VALUE;
        for(int j=0,i=0;j<arr.length;j++){
            sum+=arr[j];
            while(sum>x){
                mini=Math.min(mini,j-i+1);
                sum-=arr[i];
                i++;
            }
        }
        return mini==Integer.MAX_VALUE?0:mini;
    }
}
