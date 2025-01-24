package leetcode.dynamicprogramming.partitiondp;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CountNumberOfGoodPartition_GoogleOA {
    public static void main(String[] args) {
        //CountTotalValidPartitionSuchThatEveryBlockOfPartitionIsLessThenKSum();
        //CountTotalValidPartitionSuchThatEveryBlockOfPartitionIsGood();
       // CountTotalValidPartitionSuchThatEveryBlockOfPartitionIsGoodButExactlyKPartWay1();
        CountTotalValidPartitionSuchThatEveryBlockOfPartitionIsGoodButExactlyKPartWay2();
        CountTotalValidPartitionSuchThatEveryBlockOfPartitionIsGoodButExactlyKPartWay2optimalOptimal();
    }
    private static void CountTotalValidPartitionSuchThatEveryBlockOfPartitionIsLessThenKSum(){
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();
        int k=scanner.nextInt();
        int[] b=new int[n+1];
        int[] dp=new int[n+1];
        for(int i=1;i<=n;i++){
            b[i]=scanner.nextInt();
        }
        dp[0]=1;
        dp[1]=b[1]<=k?1:0;
          for(int i=2;i<=n;i++){
              int sum=0;
              for(int j=i;j>=1;j--){
                  sum+=b[i];
                    if(dp[j-1]<=k && sum<=k){
                        dp[i]=dp[i]+dp[j-1];
                    }
              }
          }
        System.out.println(dp[n]);
    }
    private static void CountTotalValidPartitionSuchThatEveryBlockOfPartitionIsGood(){
        Scanner scanner=new Scanner(System.in);
        String s=scanner.next();
        int[] freq=new int[3];
           for(int i=0;i<freq.length;i++){
               freq[i]=scanner.nextInt();
           }
        int n=s.length();
        int[] dp=new int[n+1];
        dp[0]=1;
         for(int i=1;i<=n;i++){
             HashMap<Character,Integer> map=new HashMap<>();
             for (int j=i;j>=1;j--){
                 char c=s.charAt(j-1);
                 map.put(c,map.getOrDefault(c,0)+1);
                 if(isValid(map,freq)){
                        dp[i]=dp[i]+dp[j-1];
                    }
             }
         }
        System.out.println(dp[n]);
    }

    private static void CountTotalValidPartitionSuchThatEveryBlockOfPartitionIsGoodButExactlyKPartWay1(){
        Scanner scanner=new Scanner(System.in);
        String s=scanner.next();
        int[] freq=new int[3];

        for(int i=0;i<freq.length;i++){
            freq[i]=scanner.nextInt();
        }
        int k=scanner.nextInt();
        int n=s.length();
        int[][] dp=new int[n+1][k+1];
        dp[0][0]=1;
        for(int i=1;i<=n;i++){
            for(int j=1;j<=Math.min(k,i);j++) {//this divide in k parts
                HashMap<Character, Integer> map = new HashMap<>();
                for (int j1 = i; j1 >= 1; j1--) {
                    char c = s.charAt(j1 - 1);
                    map.put(c, map.getOrDefault(c, 0) + 1);
                    if (isValid(map, freq)) {
                        dp[i][j]=dp[i][j]+dp[j1-1][j-1];
                    }
                }
            }
        }
        System.out.println(dp[n][k]);
    }

    private static void CountTotalValidPartitionSuchThatEveryBlockOfPartitionIsGoodButExactlyKPartWay2(){
        Scanner scanner=new Scanner(System.in);
        String s=scanner.next();
        int[] freq=new int[3];

        for(int i=0;i<freq.length;i++){
            freq[i]=scanner.nextInt();
        }
        int k=scanner.nextInt();
        int n=s.length();
        int[][] dp=new int[n+1][k+1];
        dp[0][0]=1;
        for(int i=1;i<=n;i++){
                HashMap<Character, Integer> map = new HashMap<>();
                for (int j = i; j >= 1; j--) {
                    char c = s.charAt(j - 1);
                    map.put(c, map.getOrDefault(c, 0) + 1);
                    if (isValid(map, freq)) {
                        for(int part=1;part<=Math.min(k,i);part++) {//this divide in k parts
                            dp[i][part]=dp[i][part]+dp[j-1][part-1];
                        }
                    }
                }
            }

        System.out.println(dp[n][k]);
    }

    private static void CountTotalValidPartitionSuchThatEveryBlockOfPartitionIsGoodButExactlyKPartWay2optimalOptimal(){
        Scanner scanner=new Scanner(System.in);
        String s=scanner.next();
        int[] freq=new int[3];

        for(int i=0;i<freq.length;i++){
            freq[i]=scanner.nextInt();
        }
        int k=scanner.nextInt();
        int n=s.length();

        // Calculate limits for each index
        int[] limits = new int[n+1];
        for(int i=1;i<=n;i++){
            HashMap<Character, Integer> map = new HashMap<>();
            for (int j = i; j >= 1; j--) {
                char c = s.charAt(j - 1);
                map.put(c, map.getOrDefault(c, 0) + 1);
                if (isValid(map, freq)) {
                    limits[i] = j-1;
                    break;
                }
            }
        }

        // Initialize dp and prefix sum arrays
        int[][] dp = new int[n+1][k+1];
        int[][] prefixSum = new int[n+1][k+1];

        // Initialize base case
        dp[0][0] = 1;
        prefixSum[0][0] = 1;

        // Fill dp and prefix sum arrays
        for(int i=1;i<=n;i++){
            for(int p=1;p<=k;p++){
                int limit = limits[i];
                if(limit == 0) continue;
                dp[i][p] += prefixSum[limit][p-1];
                prefixSum[i][p] = prefixSum[i][p] + dp[i][p];
            }
            // Add the case where the current substring is a valid partition
            dp[i][1] += 1;
            prefixSum[i][1] += 1;
        }

        System.out.println(dp[n][k]);
    }

    private static boolean isValid(HashMap<Character,Integer> map,int[] givenFreq) {
        int i = 0;
        for(char c='a';c<='z';c++){
            if(map.containsKey(c)){
                int value=givenFreq[i];
                if(value<map.get(c)) return false;
            }
            i++;
        }
        return true;
    }

//    private static boolean isValid(HashMap<Character,Integer> map,int[] givenFreq) {
//          for(Map.Entry<Character,Integer>  entry:map.entrySet()){
//              int value=givenFreq[entry.getKey()-'a'];
//              if(value<entry.getValue()) return false;
//          }
//           return true;
//    }
}
