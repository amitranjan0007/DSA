package leetcode.dynamicprogramming.partitiondp;

import java.util.Scanner;

public class LongestSubstringTwoOccurance_MicrosoftOA {
    /*
    https://www.desiqna.in/15248/microsoft-oa-sde-1-freshers-hiring-2023-43-lakhs-ctc

    Understanding :-> Given a string s(only consists of “a” and “b”) ; find the largest substring such that there are at max only 2 continuous occurrences of “a” and “b”

-> “aabbaaa” -> Whole string is not valid ; but part of the string->substring -> “aabbaa” -> this is valid -> length is 6.


They are saying whenever “a” comes; in continuous fashion it should only come for maximum 2 times ; similarly when “b” comes; in continuous fashion it should only come for maximum 2 times.

-> Brute Force:-> Consider all the substrings ; check if it is valid ; if yes note down its length ; the largest length will be the answer

->[0………..N-1]

I = Start of the substring
J = End point of the substring
-> First consider all the substring starting at index 0
I = 0
J = 0………..N-1]
Substring : [i……j]

-> Consider all the substrings starting at index 1
I = 1
J = 1 to  n-1

-> Consider all the substring …….. 2

-> 3

-> 4
.
.
.
.
.
.
-> N - 1
Pseudo code:->  https://ideone.com/Bg1xMq
TC : O(N^3)
SC : O(1) Space.


Let's discuss the interesting Dynamic Programming Solution which uses the DP partition trick -> Benefit of doing this is that you don't have to check much corner cases as you would have to do in two pointers.

-> Try to feel the question; have feeling for the questions;

DP partition. ->

-> dp[................................................] ->N

-> dp[i] = best answer to the questions if the substring was from [0…….i]

-> dp[0]..dp[1]....dp[2]....by observation.

-> once you do this observation ; try to make general formula of dp[i] in terms of dp[i-1] ork dp[i-2] ork……..

-> now using a for loop calculate dp[i] from 0 to N - 1 ; generally dp[n] is the final answer
DP partition trick says : if you are making parts of the array and you want some kind of answer ;

dp[i] = dp[i-1] + fix the last part of the string “i”
OR
dp[i] = dp[i-2] + fix the last part of string “i-1 i”
OR
dp[i] = dp[i-3] + fix the last part of the string “...........”

.
.
.
You pick the best choice!


dp[i] = longest substring which ends at index “i” = it should strictly include the ith element

Final answer = max(dp[0],dp[1],dp[2],........dp[N-1])

But these states are not enough.

dpa1[i] = means the best answer such that the last element is having a single “a”

dpa2[i] = means the best answer such that the last element is having “aa”
dpb1[i] = means the best answer such that the last element is “b”

dpb2[i] = means the best answer such that the last element is “b” and second last is also “b”

if(s[i]==’b’){
dp1b[i] = 1 + max(dp1a[i-1] ;dp2a[i-1])
}

if(s[i]==’b’ && s[i-1]==’b’){
dp2b[i] = 2 + max(dp2a[i-2];dp1a[i-2])
}




if(s[i]==’a’){
dp1a[i] = 1 + max(dp1b[i-1] ;dp2b[i-1])
}

if(s[i]==’a’ && s[i-1]==’a’){
dp2a[i] = 2 + max(dp2b[i-2];dp1b[i-2])
}


    * */
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        String s=scanner.nextLine();
        //helperBruteForce(s);
        helperOptimal(s);
    }

    private static void helperBruteForce(String s){
        int n=s.length();
        int maxLength=1;
        for(int i=0;i<n;i++){
            for(int j=i;j<n;j++){
                String substring=s.substring(i,j+1);
                   if(isValid(substring)){
                       maxLength=Math.max(maxLength,j-i+1);
                   }

            }
        }
        System.out.println(maxLength);
    }

    private static void helperOptimal(String s){
        int n=s.length();
        int[] dpa1=new int[n];
        int[] dpa2=new int[n];
        int[] dpb1=new int[n];
        int[] dpb2=new int[n];


        dpa1[0]=s.charAt(0)=='a'?1:0;
        dpb1[0]=s.charAt(0)=='b'?1:0;
        int maxLength=1;
        int result=1;

       for(int i=1;i<n;i++){
           if(s.charAt(i)=='a'){
               dpa1[i]=1+Math.max(dpb1[i-1],dpb2[i-1]);
                  if(i>=2){
                      dpa2[i]=2+Math.max(dpb1[i-2],dpb2[i-2]);
                  }else{
                      dpa2[i]=2;
                  }
           }else if(s.charAt(i)=='b'){
               dpb1[i]=1+Math.max(dpa1[i-1],dpa2[i-1]);
               if(i>=2){
                   dpb2[i]=2+Math.max(dpa1[i-2],dpa2[i-2]);
               }else{
                   dpb2[i]=2;
               }
           }
           maxLength=max(dpa1[i],dpa2[i],dpb1[i],dpb2[i]);
           result=Math.max(maxLength,result);
       }


        System.out.println(result);
    }

    private static boolean isValid(String s){
        int l=0;
        int r=s.length()-1;
        int ca=0;
        int cb=0;
        while(l<=r){
            if(s.charAt(l)=='a'){
                ca++;
                cb=0;
            }else{
                cb++;
                ca=0;
            }
            if(ca>2 || cb>2) return false;
            l++;
        }
        return true;
    }

    private static int max(int a,int b){
        return Math.max(a,b);
    }
    private static int max(int a,int b,int c,int d){
        return Math.max(Math.max(Math.max(a,b),c),d);
    }


}
