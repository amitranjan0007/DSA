package leetcode.dynamicprogramming.revision;

import java.util.HashMap;
import java.util.Scanner;

public class LongestSubArrayHaveAllDistinctNumber {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();
        int[]b=new int[n];
        for(int i=0;i<n;i++){
            b[i]=scanner.nextInt();
        }

        HashMap<Integer,Integer> map=new HashMap<>();
        int maxi=Integer.MIN_VALUE;
        for(int j=0,i=0;j<n;j++){
            while (map.containsKey(b[j])){
                map.remove(b[i]);

                i++;
            }
            map.put(b[j],1);
            maxi=Math.max(maxi,map.size());
        }
        System.out.println(maxi);
    }
}
