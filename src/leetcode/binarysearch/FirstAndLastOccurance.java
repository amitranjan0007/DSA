package leetcode.binarysearch;

import java.util.Scanner;

public class FirstAndLastOccurance {
    private static Scanner scanner;
    public static void main(String[] args) {
        scanner=new Scanner(System.in);
        int n=scanner.nextInt();
        int target=scanner.nextInt();
        int[]b=new int[n];
        for(int i=0;i<n;i++){
            b[i]=scanner.nextInt();
        }

        firstOccurance(0,n-1,b,target);
        lastOccurance(0,n-1,b,target);
    }

    private static void lastOccurance(int l, int h, int[] b,int target) {
        int ans=-1;
        while(l<=h){
            int mid=(l+h)>>1;
            if(b[mid]==target){
                ans=mid;
                l=mid+1;
            }else if(b[mid]>target){
                h=mid-1;
            }else{
                l=mid+1;
            }
        }
        System.out.println("last occurance "+ans);
    }

    private static void firstOccurance(int l, int h, int[] b,int target) {
        int ans=-1;
        while(l<=h){
            int mid=(l+h)>>1;
            if(b[mid]==target){
                ans=mid;
                h=mid-1;
            }else if(b[mid]>target){
                h=mid-1;
            }else{
                l=mid+1;
            }
        }
        System.out.println("First occurance "+ans);
    }
}
