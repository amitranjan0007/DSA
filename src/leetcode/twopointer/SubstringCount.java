package leetcode.twopointer;

import java.util.HashMap;
import java.util.Scanner;

public class SubstringCount {
   /*
   Given a string s , consisting only of characters 'a' , 'b' , 'c'.
   Find the number of substrings that contain at least one occurrence of all these characters 'a' , 'b' , 'c'.

   Example 1: aaabcbc output=9


   0 1 2 3 4 5 6
   a a a b c b c

   i       j
   0 1 2 3 4    5 6
   a a a b c    b c

   aaabc is one of the valid substring, and how many more we can find using aaabc
   0 1 2 3 4 5   6
   a a a b c b   c   => aaabcb

   0 1 2 3 4 5 6
   a a a b c b c   => aaabcbc

   ----2 more substring we can find ,total 3 substring when i=0 & j=4

   now shrink the window, increase i=1 & j remains at same point i.e j=4

     i     j
   0 1 2 3 4    5 6
   a a a b c    b c

   valid substring is aabc
   and how many more we can find using aabc
     i     j
   0 1 2 3 4 5  6
   a a a b c b  c  => aabcb

       i     j
   0 1 2 3 4 5 6
   a a a b c b c  => aabcbc

   -----again 3 more substring we can find
   till now 6 substring we found(window starting from 0-4 & 1-4)
   Now again reduce the window i.e i=2 & j remains same
       i   j
   0 1 2 3 4    5 6
   a a a b c    b c

   abc is one of the valid substring & how many more we can find?
       i   j
   0 1 2 3 4 5  6
   a a a b c b  c ==> abcb

       i   j
   0 1 2 3 4 5 6
   a a a b c b c ==> abcbc

   again 3 more we found
   so total 9 we found

   Observation : count+=totalLength of substring - j , where j stay at first valid window



   * */
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter the string");
        String str= scanner.next();
       int  result= totalSubstring(str);
        System.out.println(result);
    }

    private static int totalSubstring(String str){
        HashMap<Character,Integer> map=new HashMap<>();
        int count=0;
        int n=str.length();
        for(int i=0,j=0;j<str.length();j++){
            char c= str.charAt(j);
              map.put(c,map.getOrDefault(c,0)+1);
            while(map.size()==3){
                count+=n-j;
                c=str.charAt(i);
                map.put(c,map.getOrDefault(c,0)-1);
                if(map.get(c)==0) map.remove(c);
                i++;
            }
        }
        return count;
    }
}
