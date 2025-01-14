package leetcode.dynamicprogramming.partitiondp;

import java.util.Arrays;
import java.util.Scanner;

public class MinimumSubstringsInPartition {
    //https://leetcode.com/problems/minimum-substring-partition-of-equal-character-frequency/description/
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        String s=scanner.next();
        int[] dp =new int[s.length()+1];
        Arrays.fill(dp,100000);///randomally any big number
        dp[0]=0;// always remeber in partition dp we do this base case, when we want to find Min/Max
        dp[1]=1;
        for (int i = 2; i <= s.length(); i++) {//you can start the loop with 1 also
            int[] freq = new int[26];
            for (int j = i; j >= 1; j--) {//fix the j and check i..j is valid or not,count for valid
                int c = s.charAt(j - 1) - 'a';//store the freq of charcter j..i
                freq[c]++;
                if (isValid(freq)) {// check substring from i...j is valid or not,equal number of character
                    dp[i] = Math.min(dp[i], dp[j - 1] + 1);
                }
            }
        }
        System.out.println(dp[s.length()]);
    }

    private static boolean isValid(int[] freq) {
        int num=-1;
         for(int i=0;i<freq.length;i++){
             if(freq[i]!=0){
                 if(num==-1){
                    num= freq[i];
                 }else if(num!=freq[i]){
                    return false;
                 }
             }
         }
         return true;
    }
}
