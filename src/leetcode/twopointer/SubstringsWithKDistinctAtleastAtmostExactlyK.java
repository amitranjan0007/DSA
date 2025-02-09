package leetcode.twopointer;

import java.util.HashMap;

public class SubstringsWithKDistinctAtleastK {
    public static void main(String[] args) {

        int k=18;
        int res=SubstringsWithKDistinctAtleastK("abccdasdweweadadaaa",k);
        SubstringsWithKDistinctAtMostK("abccdasdweweadadaaa",k+1);
        SubstringsWithKDistinctAtMostKOptimal("abccdasdweweadadaaa",k);
        SubstringsWithKDistinctExactlyKOptimal("aba",2);
    }
    private static int SubstringsWithKDistinctAtleastK(String s, int k) {
        // your code here
        int n=s.length();
        HashMap<Character,Integer> map=new HashMap<>();
        int count=0;
        for(int j=0,i=0;j<n;j++){
            char c= s.charAt(j);
            map.put(c,map.getOrDefault(c,0)+1);
            while(map.size()>=k){
               /*
                 0 1 2 3 4 5
                 a b c c a d
                 i   j        ==> As you see abc is valid substring atleast 3.. so future substring is also be valid
                                  (abc)(abcc)(abcca)(abccad)==4
                                  it's nothing but (length-j)=6-2=4
                                  now decrease the window
               * */

                count+=(n-j);
                char c1= s.charAt(i);
                map.put(c1,map.getOrDefault(c1,0)-1);
                if(map.get(c1)==0) map.remove(c1);
                i++;

            }

        }

       return count;
    }

    private static void SubstringsWithKDistinctAtMostK(String s, int k) {
        // your code here
        int n=s.length();
        int totalSubArray=((n*(n+1))/2);
        System.out.println(totalSubArray-SubstringsWithKDistinctAtleastK(s,k));
    }

    private static void SubstringsWithKDistinctAtMostKOptimal(String s, int k) {
        // your code here
        int n=s.length();
        HashMap<Character,Integer> map=new HashMap<>();
        int count=0;
        for(int j=0,i=0;j<n;j++){
            char c= s.charAt(j);
            map.put(c,map.getOrDefault(c,0)+1);
            while(map.size()>k){
                char c1= s.charAt(i);
                map.put(c1,map.getOrDefault(c1,0)-1);
                if(map.get(c1)==0) map.remove(c1);
                i++;

            }
            count=count+(j-i+1);

        }
        System.out.println(count);
    }
    private static void SubstringsWithKDistinctExactlyKOptimal(String s, int k) {
        // your code here
        int count=SubstringsWithKDistinctAtleastK(s,k)-SubstringsWithKDistinctAtleastK(s,k+1);
        System.out.println(count);
    }
}
