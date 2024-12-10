package desiqna;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Sort_Them_CodeChef {
    /*
      Link:  https://www.codechef.com/problems/SORT_THEM?tab=statement
      Doc Link: https://docs.google.com/document/d/1W8IvCqGMWu75fXgX9Svf_6jJ2_ksxEPquJfk6zOySCQ/edit?tab=t.0
      Observation : You are given a string “S”; each character in string “S” can be changed to some other character

                    -> String P of size 26 is a permutation of ‘a’ - ‘z’.
                    -> By following some process.
                        If S[i]==’a’;
                    -> x = 27 - position of ‘a’ in string P
                    -> x = 27 - 7
                    -> x = 20

                    -> S[i] can become P[20] -> b

                    -> It means that S[i] can become ‘a’ or ‘b’ depending on the test-case explained in vid.

                    -> For each index this process is possible.

                    Your task is to find out if string “S” can get sorted by doing this operation any number of times you want -> If yes, find the minimum number of moves to sort string “S”.

                    -> Deeply analyze what will happen if you keep on doing this operation again and again at the same index.

                    -> S[i]=’a’; after doing operation it become S[i]=b

                    -> If you do the operation again;

                    ->  x = 27 - position of ‘b’ in string P
                    -> x =  27 - 20
                    -> x = 7

                    -> S[i] = P[7] = ‘a’.

                    -> there can only be two letters possible at each index and they will keep changing to each other back to back

    * */
    public static void main (String[] args) throws java.lang.Exception
    {
        // your code goes here
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        while(t-->0){
            int res=solve(scanner);
            System.out.println(res);
        }


    }
    private static int solve(Scanner scanner ){
        int sLen=scanner.nextInt();
        scanner.nextLine();
        String s=scanner.next();
        scanner.nextLine();
        String p=scanner.next();


        HashMap<Character,Integer> pMap=new HashMap<>();
          for(int i=0;i<p.length();i++){
              pMap.put(p.charAt(i),i+1);
          }
          /*
             pMap:
             bcad.......
             b->1
             c->2
             a->3
             d->4
          * */

        HashMap<Character,Character> transformMap=new HashMap<>();

        for(int i=0;i<p.length();i++){
            int x= 27-pMap.get(p.charAt(i));//pMap storing character at i+1
            transformMap.put(p.charAt(i), p.charAt(x-1));//Original position of the char is x-1
        }

        int[][] dp=new int[sLen][2];
        for(int[] nums:dp){
            Arrays.fill(nums,Integer.MAX_VALUE);
        }
        /*
          dp[i]---> min number of moves required to sort the array from 0..i
        * */
        dp[0][0]=1;
        dp[0][1]=1;

        for(int i=1;i<s.length();i++){
           // Performing no transformation at i
            if(s.charAt(i-1)<=s.charAt(i)) dp[i][0]= Math.min(dp[i-1][0],dp[i][0]);
            if(transformMap.get(s.charAt(i-1))>s.charAt(i))dp[i][0]=Math.min(dp[i-1][1],dp[i][0]);

            //performing transformation at i idx
            if(s.charAt(i-1)<=transformMap.get(s.charAt(i))) dp[i][1]= 1+Math.min(dp[i-1][0],dp[i][1]);
            if(transformMap.get(s.charAt(i-1))>transformMap.get(s.charAt(i)))dp[i][1]=Math.min(dp[i-1][1],dp[i][1]);
        }
       int res=Math.min(dp[sLen-1][1],dp[sLen-1][0]);;
        return res==Integer.MAX_VALUE ?-1:res;

    }
}
