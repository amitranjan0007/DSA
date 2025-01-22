package leetcode.dynamicprogramming.partitiondp;

import java.util.Scanner;
import java.util.stream.IntStream;

public class SubArrayGoodOrBad {
    /*

    * **/
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();
        int[] b=new int[n+1];
        int[] dp=new int[n+1];
           for(int i=1;i<=n;i++){
               b[i]=scanner.nextInt();
           }
           dp[0]=1;
           for(int i=2;i<=n;i++){
               for(int j=i;j>=1;j--){
                   if (check(b, j, i)) {
                       if(dp[j-1]>=1)
                          dp[i] += dp[j - 1];
                   }
               }
           }
        System.out.println(dp[n]);
    }

    static boolean check(int[] arr, int start, int end) {
        int maxi=IntStream.range(start,end+1).map(i->arr[i]).max().getAsInt();
        return arr[end]!=maxi;
    }
}
