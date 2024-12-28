package leetcode.hashing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class SwapToMakeStringsEquals {

    public static void main(String[] args) {
        String str1="aabbcc";
        String str2="bbacac";
        System.out.println(isSwapMakeStringsEqual(str1,str2));

        isSwapMakeStringsEqualFollowUp();
    }
    private static boolean isSwapMakeStringsEqual(String str1,String str2){
        int[] freqStr1=new int[26];
        int[] freqStr2=new int[26];

          for(int i=0;i<str1.length();i++){
              freqStr1[str1.charAt(i)-'a']++;

          }
        for(int i=0;i<str2.length();i++){
            freqStr2[str2.charAt(i)-'a']++;

        }

        if(Arrays.equals(freqStr1,freqStr2)){
            return  true;
        }
        return  false;
    }
    private static void isSwapMakeStringsEqualFollowUp(){
        /**
         https://www.desiqna.in/13736/arcesium-oa-sde1-jan-2023

         Q:Two strings, a and b, are said to be twins only if they can be made equivalent by performing some number
         of operations on one or both strings. There are two possible operations:

         SwapEven: Swap a character at an even-numbered index with a character at another even-numbered index.
         SwapOdd: Swap a character at an odd-numbered index with a character at another odd-numbered index.
         Example:
         a0 a1 a2 a3 a4 a5
         b0 b1 b2 b3 b4 b5

         As per question :

         Even:
         you can swap a0 a2 a4 (suppose : xzx)
         you can swap b0 b2 b4 (suppose : zxx)
         How you can make equal ? You solved above  isSwapMakeStringsEqual,right

         odd:
         you can swap a1 a3 a5 (suppose : xzx)
         you can swap b1 b3 b5 (suppose : zxx)
         How you can make equal ? You solved above  isSwapMakeStringsEqual,right

         if both are Even & odd are same you are able to swap both the string

         a = ["cdab", "dcba"]
         b = ["abcd", "abcd"]

         cdab-->abcd   output true
         dcba-->abcd   output false

         * */
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();
        ArrayList<String> list1=new ArrayList<>();
        ArrayList<String> list2=new ArrayList<>();
        for(int i=0;i<n;i++){
            list1.add(scanner.next());
        }
        for(int i=0;i<n;i++){
            list2.add(scanner.next());
        }

        for(int i=0;i<n;i++){
            boolean isValid= isSwapMakeStringsEqualFollowUpHelper(list1.get(i),list2.get(i));
            System.out.println(isValid);
        }
    }
    private static boolean isSwapMakeStringsEqualFollowUpHelper(String str1,String str2){
        if(str1.length()!=str2.length()) return false;

        int[] freqStr1Even=new int[26];
        int[] freqStr2Even=new int[26];
        int[] freqStr1Odd=new int[26];
        int[] freqStr2Odd=new int[26];

        for (int i=0;i<str1.length();i++){
            if(i%2==0){
                freqStr1Even[str1.charAt(i)-'a']++;
            }else{
                freqStr1Odd[str1.charAt(i)-'a']++;
            }
        }
        for (int i=0;i<str2.length();i++){
            if(i%2==0){
                freqStr2Even[str2.charAt(i)-'a']++;
            }else{
                freqStr2Odd[str2.charAt(i)-'a']++;
            }
        }

        if(Arrays.equals(freqStr1Even,freqStr2Even) && Arrays.equals(freqStr1Odd,freqStr2Odd)) return true;
        return false;
    }



}
