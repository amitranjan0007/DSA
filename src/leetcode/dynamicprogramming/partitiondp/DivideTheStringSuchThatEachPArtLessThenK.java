package leetcode.dynamicprogramming.partitiondp;

import java.util.Scanner;

public class DivideTheStringSuchThatEachPArtLessThenK {
    //https://www.desiqna.in/13954/techgig-coding-semifinale-contest-problem-dp
   /*
   Understanding :-> Given a string of integers; divide them ; in such a manner that ; all the parts of that string <=K ; find the number of ways to do it

S = “123” ; K<=1000

Output :- 4.

S = “125” ; K = 24
Output :- 2.

S = “1234” ; K = 1000

Dp[4] = number of ways such that “4” is put in the last box ;
Number of ways such that “34” is put in the last box ;
Number of ways such that “234” is put in the last box ;
  Number of ways such that “1234”  is put in the last box.

= dp[3]
Dp[2]
Dp[1]
0(as 1234>1000 we want partition to be <=1000)


Dp[i] = number of ways such that “i” is in the last box +
Number of ways such that “i + i-1” is in the last box +

Number of ways such that “i-2 + i-1 + i” are in the last box.


Dp[i] = dp[i-1] + dp[i-2] + ……… dp[0]


Special condition is :-> dp[i-1] will only be considered if “i”<=K ;
->dp[i-2] will only be considered if “i-1,i”<=K
->dp[i-3] will only be considered if “i-2,i-1,i”<=K

-> dp[i] = number of ways to divide [1……i] such that it is guaranteed that all the divisions will be <=K
Let's visualise:
1234 and k<1000
dp[1]    dp[2]  dp[3]        dp[4]
1       [1][2]  [1][2][3]    [1][2][3][4]
        [12]    [1 2][3]     [1 2][3][4]
                [1][2 3]     [1] [2 3] [4]
                [1 2 3]      [1 2 3] [4]
                             //fixing last 2 elenents
                             [1][2][3 4]
                             [1 2][3 4]
                             //fixing last 3 elenents
                             [1][2 3 4]
                              //fixing last 4 elenents means whole array
                             [1 2 3 4]----------> this is greater then >1000

                             so dp[4]= answer is 7

   *
   * */
    public static void main(String[] args) {
       // helperSubOptimal();
        //helperOptimal();
        helperOptimalFollowUpExactlyKTimes();
    }

    public static void helperSubOptimal() {
        //O(n^2)
        Scanner scanner = new Scanner(System.in);
        int k = scanner.nextInt();
        String s = scanner.next();
        int n = s.length();
        int[] dp = new int[n + 1];
        dp[0] = 1;  // One way to partition an empty string

        for (int i = 1; i <= n; i++) {
            for (int j = i; j >= 1; j--) {
                String sub = s.substring(j - 1, i);
                int sum=Integer.parseInt(sub);
                if (sum <= k && sub.charAt(0) != '0') {
                    dp[i] += dp[j - 1];
                }
            }
        }
        System.out.println(dp[n]);
    }

    public static void helperOptimal() {
        //O(n*10)
        Scanner scanner = new Scanner(System.in);
        int k = scanner.nextInt();
        String s = scanner.next();
        int n = s.length();
        int[] dp = new int[n + 1];
        dp[0] = 1;  // One way to partition an empty string

        for (int i = 1; i <= n; i++) {
            for (int j = i; j >= Math.max(i-10,1); j--) {//as in question k can go upto 10^9 means 9 partition 0...9= 10
                String sub = s.substring(j - 1, i);
                int sum=Integer.parseInt(sub);
                if (sum <= k && sub.charAt(0) != '0') {
                    dp[i] += dp[j - 1];
                }
            }
        }
        System.out.println(dp[n]);
    }

    public static void helperOptimalFollowUpExactlyKTimes() {
        //O(n*10)
        Scanner scanner = new Scanner(System.in);
        int k = scanner.nextInt();
        int exactlyKBlock = scanner.nextInt();
        String s = scanner.next();
        int n = s.length();
        int[][] dp = new int[n + 1][exactlyKBlock+1];
        dp[0][0] = 1;  // One way to partition an empty string

        for (int i = 1; i <= n; i++) {
            for(int kBlock=1;kBlock<=exactlyKBlock;kBlock++){
                for (int j = i; j >= Math.max(i-10,1); j--) {//as in question k can go upto 10^9 means 9 partition 0...9= 10
                    String sub = s.substring(j - 1, i);
                    int sum=Integer.parseInt(sub);
                    if (sum <= k && sub.charAt(0) != '0') {
                        int g=dp[j - 1][kBlock-1];
                        dp[i][kBlock] += g;
                    }
                }
            }

        }
        System.out.println(dp[n][exactlyKBlock]);
    }
}
