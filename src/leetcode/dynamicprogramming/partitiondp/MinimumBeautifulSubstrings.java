package leetcode.dynamicprogramming.partitiondp;

import java.util.Arrays;

public class MinimumBeautifulSubstrings {
    public static void main(String[] args) {
        //https://leetcode.com/problems/partition-string-into-minimum-beautiful-substrings/
        int ans=minimumBeautifulSubstrings("1011");
        System.out.println(ans);
    }
    public static int minimumBeautifulSubstrings(String s) {
        int n=s.length();
        /*
         As per constraint binary string length can be 15 so 2^15-1=32765 maximum number
         max number divisible by 5 and less then 32765

         5^6=15625
         5^7=78125

         so valid number divisible by 5 is upto 15625
        * */
        int[]dp=new int[n+1];//1 based indexing so 15625+1=15626
        Arrays.fill(dp,16);//cosntaring string length is max 1 to 15
        dp[0] = 0;
        int i = 1;
        while (i <= n) {
            int j = i;
            while (j >= 1) {
                String sub = s.substring(j - 1, i);
                if (s.charAt(j - 1) != '0') {
                    int gValue = convert(sub);
                    if (gValue != 0 && 15625 % gValue == 0) {
                        dp[i] = Math.min(dp[i], dp[j - 1] + 1);
                    }
                }
                j--;
            }
            i++;
        }
        return dp[n] == 16 ? -1 : dp[n];

    }


    private static int convert(String str){

        int num=0;
        int b=0;
        int l=0;
        int r=str.length()-1;
        while(l<=r){
            int ch=str.charAt(r)-'0';
            if(ch==1){
                num+=Math.pow(2,b);
            }
            r--;
            b++;
        }
        return num;
    }
}
