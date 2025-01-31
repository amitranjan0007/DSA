package leetcode.twopointer;

import java.util.Arrays;
import java.util.PriorityQueue;

public class CountNumberOfPairsSuchThatAIGreaterThenBI {
    public static void main(String[] args) {
        int[]a={5,7,3,1};
        int[]b={6,2,8,2};
        CountNumberOfPairsSuchThatAIGreaterThenBIBrute(a,b);
        CountNumberOfPairsSuchThatAIGreaterThenBIOptimal(a,b);
    }
    private static void CountNumberOfPairsSuchThatAIGreaterThenBIBrute(int[]a,int[]b){
        int n=a.length;
        int m=b.length;
        boolean[]visited=new boolean[m];
        int count=0;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(a[i]>b[j] && !visited[j]){
                    count++;
                    visited[j]=true;
                }
            }
        }
        System.out.println(count);
    }
    private static void CountNumberOfPairsSuchThatAIGreaterThenBIOptimal(int[]a,int[]b){
        int n=a.length;
        int m=b.length;
        Arrays.sort(a);//space take log(n)internally
        Arrays.sort(b);
        int i=0;
        int j=0;
        int count=0;
           while(i<n && j<m){
               if(a[i]>b[j]){
                   count++;
                   i++;
                   j++;
               }else{
                   i++;
               }
           }
        System.out.println(count);
    }
}
