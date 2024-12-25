package leetcode.hashing;

import java.util.HashMap;

public class LongestPalindromeConcatingString {
    //https://leetcode.com/problems/longest-palindrome-by-concatenating-two-letter-words/description/
    public static void main(String[] args) {
       String[] str={"lc","cl","gg"};
        int res=longestPalindromeHashing(str);
        System.out.println(res);
    }

        /**
         Observation:
         1.in map we store all the string
         2. check current number reverse is present in the map or not , if yes how much freque, you to take min of it
         Case : 1 --- if the two character are diffrent
         lc,lc,lc,lc,cl,cl,cl,cl
         lc->3
         cl->5
         so lc reverse is cl ,and it present so we can take for 3 lc we can pick 3 cl,right -- to form palindrome
         so what is the length ? we picked 3 lc and 3 cl =>>> 6 right --it's nothing but min(map[lc],map[cl])*2

         lc,lc,lc,lc,cl,cl,cl,cl
         lc->3
         cl->5
         -> once you calculated remove from the map

         Case 2-> if the two charcter are similiar
         in map there frequeny can be even or odd
         -> even -- then no problem
         [gg gg  cc cc ] => {gg->2,cc->2} we can form 2*2+2*2=8
         -> odd-- then there is a prblem
         [gg gg gg cc cc cc] =>{gg->3,cc->3} ,let's look how palindrome can be formed

         ggcc----ccgg => so in blank side you can place either one cc or one gg,you can't place ccgg

         so total count 2 gg +2 cc +(either 1 cc or 1 gg)   => 2*2+2*2+2=10

         let's take one flag to calculte (either 1 cc or 1 gg)
         & do then (element found in map-1)*2


         */
        public static int longestPalindromeHashing(String[] words) {
            int length=0,flag=0;
            HashMap<String,Integer> map=new HashMap<>();
            for(String word:words){
                map.put(word,map.getOrDefault(word,0)+1);
            }
            for(String word:words){
                if(word.charAt(0)!=word.charAt(1)){
                    String reversedString = String.valueOf(word.charAt(1))+String.valueOf(word.charAt(0));
                    if(map.containsKey(reversedString)){
                        length+=(Math.min(map.get(word),map.get(reversedString)))*4;
                        map.remove(word);
                        map.remove(reversedString);
                    }

                }else if(word.charAt(0)==word.charAt(1)){
                    if(map.containsKey(word)){
                        if(map.get(word)%2==0){
                            length+= map.get(word)*2;
                        }else{
                            if(flag==0){
                                length+= map.get(word)*2;
                                flag=1;
                            }else{
                                length+= (map.get(word)-1)*2;
                            }
                        }
                        map.remove(word);
                    }
                }
            }
            return length;
        }

}
