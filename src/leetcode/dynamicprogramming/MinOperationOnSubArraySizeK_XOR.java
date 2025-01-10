package leetcode.dynamicprogramming;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MinOperationOnSubArraySizeK_XOR {
    /*
     Observation :
       - At any idx we can change the number in between 0...2^6-1 i.e 0...63
       -  minimum number of operations to make sure all subarrays of size "K" in the array have their xor value as 0

       a1 a2 a3 a4 a5 a6 a7 a8 a9 a10

       Suppose K=3
        a1 a2 a3 a4 a5 a6 a7 a8 a9 a10

        a1^a2^a3=0
        a2^a3^a4=0
        ..........
        a1^a4=0 ================> a1 = a4

        a2^a3^a4=0
        a3^a4^a5=0
        ..........
        a2^a5=0

        a3^a4^a5=0
        a4^a5^a6=0
        ..........
        a3^a6=0

       ....................................
       Next window of length K=3
        a4^a5^a6=0
        a5^a6^a7=0
       ............
       a4^a7=0 ==============> oho means a4 = a7

       so when you try to find
       a7^a8^a9=0
       a8^a9^10=0
       ..............
       a7^a10=0 ==============> oho means a7=a10

      1. so we conclude a1=a4=a7=a10 form one cycle ,so to have xor to be 0 they must be equal :) Great
      2.  similarly we can say, a2=a5=a8 form one cycle ,so to have xor to be 0 they must be equal :) Great
      3.  similarly we can say, a3=a7=a9 form one cycle ,so to have xor to be 0 they must be equal :) Great
       So there are total 3 cycle.

       In one shot, how you know number of cycle ?
          Number of Cycle= total/k;

     1 2 3 4 5 6 7 8 9 10
     1 2 3 2 2 2 2 2 2 1

     At idx i=1 , how many operation we have to make such that a[1]=1
     so we check the cycle of idx 1---> i.e 1,4,7,10
                                            1 2 2  1 ====> These are the values of above idx, so total-freq(a[1])=4-2= 2 operation needed

     But we can change the number also at idx=1 ,let say
     At idx i=1 , how many operation we have to make such that a[1]=3
     currently a[1]=1 & have to make a[1]=3
      so we check the cycle of idx 1---> i.e 1,4,7,10
                                             1 2 2  1 ====> These are the values of above idx, so total-freq(a[3])=4-0= 4 operation needed


     so at particular idx we have to try all the number 0..63


     dp[i][goal_xor]-----> first i elements of the array have been decided and their xor is goal_xor
                               -> minimum changes in whole array needed to achieve it

                               minimum changes needed to make sure -> till index “i” xor of all elements is = goal_xor and the first “i” elements have been decided.

        i-> 1 to K
        Cuurent_xor = 0 till 63.
        (fix b[i] = last_element = 0…..63.)
        -> dp[i][goal_xor] = dp[i-1][goal_xor^last_element] + total number of elements in the i cycle - frequency of last_element in the cycle.

        -> either you can put “0” at index “i” in that case answer will be :->
                  dp[i-1][goal_xor] + total number of elements in the i cycle - frequency of “0” in the cycle.

        -> put “1” at index i -> dp[i-1][goal_xor^1] + total number of elements in the i cycle - frequency of “1” in the cycle.

        ->
        .
        .
        .
        .
        .
        .
        .
        .
        .
        .
        -> put “63” at the ith index.


        -> final answer = dp[k][0] -> first K elements have been decided and their xor = 0 -> minimum changes to be done in whole array to achieve it


    * */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();
        long k = sc.nextLong();

        Map<Long, Map<Long, Long>> map = new HashMap<>();

        for (long i = 0; i < k + 1; i++) {
            map.put(i, new HashMap<>());
        }

        for (long i = 1; i <= n; i++) {
            long num = sc.nextLong();
            long p = i % k;
            map.get(p).put(num, map.get(p).getOrDefault(num, 0L) + 1);
        }

        // Declare and initialize dp array
        long[][] dp = new long[(int) (k + 1)][64];



        for(long[] numArray:dp){
            Arrays.fill(numArray,Integer.MAX_VALUE);
        }

        dp[0][0]=0;// zero element xor and make xor as 0 , cost us 0

        for(long i=1;i<=k;i++){
            for(int goalXor=0;goalXor<=63;goalXor++){
                //AP series a+(total-1)d<=n ==> (total-1)*k = n-a =>total= ((n-a)/k)+1
                long total=((n-i)/k)+1;
                for(int lastFixed=0;lastFixed<=63;lastFixed++){
                    if(dp[(int)i-1][goalXor^lastFixed]!=Long.MAX_VALUE){
                        dp[(int)i][goalXor]=
                                Math.min(dp[(int)i][goalXor],
                                        dp[(int)i-1][goalXor^lastFixed]+total-map.get((long)i%k).getOrDefault((long)lastFixed,0L)
                                );
                    }

                }
            }
        }
        System.out.println(dp[(int)k][0]);

    }
  static void check(){
      Scanner sc = new Scanner(System.in);
      long n = sc.nextLong();
      long k = sc.nextLong();

      // Input array and modulo group hash map
      long[] b = new long[(int) n + 1];
      Map<Long, Map<Long, Long>> g = new HashMap<>();

      for (long i = 0; i < k + 1; i++) {
          g.put(i, new HashMap<>());
      }

      for (long i = 1; i <= n; i++) {
          b[(int) i] = sc.nextLong();
          long p = i % k;
          g.get(p).put(b[(int) i], g.get(p).getOrDefault(b[(int) i], 0L) + 1);
      }

      // Declare and initialize dp array
      long[][] dp = new long[(int) (k + 1)][64];
      for (long[] row : dp) {
          Arrays.fill(row, Long.MAX_VALUE);
      }
      dp[0][0] = 0; // Base case: 0 elements and XOR 0 costs 0

      // Populate dp table
      for (int i = 1; i <= k; i++) {
          for (int goalXor = 0; goalXor <= 63; goalXor++) {
              long total = (n - i) / k + 1;
              for (int lastElement = 0; lastElement <= 63; lastElement++) {
                  if (dp[i - 1][goalXor ^ lastElement] != Long.MAX_VALUE) {
                      dp[i][goalXor] = Math.min(dp[i][goalXor],
                              dp[i - 1][goalXor ^ lastElement] + total - g.get((long) (i % k)).getOrDefault((long) lastElement, 0L));
                  }
              }
          }
      }

      // Output result
      System.out.println(dp[(int) k][0]);

      sc.close();
  }

}


