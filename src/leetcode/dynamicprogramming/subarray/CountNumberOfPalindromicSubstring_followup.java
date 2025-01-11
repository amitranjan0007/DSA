package leetcode.dynamicprogramming.subarray;

import java.util.Scanner;

public class CountNumberOfPalindromicSubstring_followup {
    public static void main(String[] args) {
        //countNumberOfPalindromicSubstring();
        //maxLengthOfPalindromicSubstring();
        countNumberOfPalindromicSubsequence();
    }
    private static void countNumberOfPalindromicSubstring(){
        Scanner scanner=new Scanner(System.in);
        String s=scanner.next();
        int i=0,j=0,k=0;
        int n=s.length();
        //dp[i][j]-> is substring palindrome which start's at i & end at i
        int dp[][]=new int[n+1][n+1];
        int count=0;

        //1-length
        while(i<n){
            dp[i][i]=1;
            count++;
            i++;
        }

        i=0;
        //2 length substring
        while(i<n-1){
            if(s.charAt(i)==s.charAt(i+1)){
                dp[i][i+1]=1;
                count++;
            }
            i++;
        }

        //find for length 3,4,5 .......n
        /*
           0 1 2 3 4 5
        *  4 7 3 9 6 3
        *        |   |
           6-3+1 ==> start point -> i=n-length+1, i should stop here
           j=i+3-1 ==> i+l-1

        * */
        int length=3;
        while(length<=n){// we have to compute for all the subarray length 3,4,5,...upto n
               i=0;
               while(i<n-length+1){
                   j=i+length-1;
                     if(s.charAt(i)==s.charAt(j) && dp[i+1][j-1]==1){
                         dp[i][j]=1;
                         count++;
                     }
                     i++;
               }
            length++;
        }
        System.out.println(count);
    }

    private static void maxLengthOfPalindromicSubstring(){
        Scanner scanner=new Scanner(System.in);
        String s=scanner.next();
        int i=0,j=0,k=0;
        int n=s.length();
        //dp[i][j]-> is substring palindrome which start's at i & end at i
        int dp[][]=new int[n+1][n+1];
        int maxi=0;

        //1-length
        while(i<n){
            dp[i][i]=1;
            maxi=1;
            i++;
        }

        i=0;
        //2 length substring
        while(i<n-1){
            if(s.charAt(i)==s.charAt(i+1)){
                dp[i][i+1]=1;
                maxi=2;
            }
            i++;
        }

        //find for length 3,4,5 .......n
        /*
           0 1 2 3 4 5
        *  4 7 3 9 6 3
        *        |   |
           6-3+1 ==> start point -> i=n-length+1, i should stop here
           j=i+3-1 ==> i+l-1

        * */
        int length=3;
        while(length<=n){// we have to compute for all the subarray length 3,4,5,...upto n
            i=0;// suppose  string is abbac  length is 3 so abba,bba,bac , for new length reset i=0 suppose length is 4,abba & bbac
            while(i<n-length+1){
                j=i+length-1;
                if(s.charAt(i)==s.charAt(j) && dp[i+1][j-1]==1){
                    dp[i][j]=1;
                    maxi=length;
                }
                i++;
            }
            length++;
        }
        System.out.println(maxi);
    }


    private static void countNumberOfPalindromicSubsequence(){
        /*
           String s=abbaxyz
1          output: 12
        * **/
        Scanner scanner=new Scanner(System.in);
        String s=scanner.next();
        int i=0,j=0,k=0;
        int n=s.length();
        //dp[i][j]-> how many palindromic subsequence are there till i..j
        int dp[][]=new int[n][n];

        //1-length
        while(i<n){
            dp[i][i]=1;
            i++;
        }

        i=0;
        //2 length substring
        while(i<n-1){
            if(s.charAt(i)==s.charAt(i+1)){
                dp[i][i+1]=3; // aa=> a,a,aa
            }else{
                dp[i][i+1]=2;//ab->a,b
            }
            i++;
        }

        //find for length 3,4,5 .......n
        /*
           0 1 2 3 4 5
        *  4 7 3 9 6 3
        *        |   |
           6-3+1 ==> start point -> i=n-length+1, i should stop here
           j=i+3-1 ==> i+l-1


           Observation:

           1) If first and last character are unequal
                0   1 2 3      4
                a...d e f......c


                a d e f
                  d e f c
               so new sequence is a d e f c
               as you see def is colliding twice so we have to remove it one

                 i  i+1  j-1    j
                 a...d e f......c

                 dp[i][j]=dp[i][j-1]+dp[i+1][j]-dp[i+1][j-1]

           2) If first and last character are equal
               Previous will hold true but we add some extra stuff to it,why so this will give the palindromic
                dp[i][j]=dp[i][j-1]+dp[i+1][j]-dp[i+1][j-1]
                but now, if first and last charcter are equal it can form more palindrome eg:
                a b c a =>
                  Previous formula will valid and it generate : a,b,c,a = 4 palindromic subsequence woow :)
                  Now what more extra, see carefully as first and last chacrter are same it generate 1 more palindrome i.e aa
                  Now any more extra ? Yes : aba,aca = 2 more , so if you see carefully if we know dp of bc we just attaching a's

                  so were bc is lying : i+1,j-1

                  so if we write combined formula

                 dp[i][j]=dp[i][j-1]+dp[i+1][j]-dp[i+1][j-1]
                                             +1+dp[i+1][j-1] (Extra 1 for aa & dp[i+1][j-1] as explained above)

                 dp[i][j]=1+dp[i][j-1]+dp[i+1][j]
        * */
        int length=3;
        while(length<=n){// we have to compute for all the subsequence length 3,4,5,...upto n-1
            i=0;// suppose  string is abbac  length is 3 so abba,bba,bac , for new length reset i=0 suppose length is 4,abba & bbac
            while(i<n-length+1){
                j=i+length-1;
                if(s.charAt(i)==s.charAt(j)){//first and last charter are same
                    dp[i][j]=1+dp[i][j-1]+dp[i+1][j];
                }else{//first and last charter are not same
                    dp[i][j]=dp[i][j-1]+dp[i+1][j]-dp[i+1][j-1];
                }
                i++;
            }
            length++;
        }
        System.out.println(dp[0][n-1]);
    }

}
