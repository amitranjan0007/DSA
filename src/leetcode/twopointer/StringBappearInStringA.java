package leetcode.twopointer;

/*
Question: https://www.desiqna.in/17545/google-oa-first-subsequence-13th-july#google_vignette
https://docs.google.com/document/d/1xEhtam-dLx9GABHz0xAphXWq0NaQ6uCYTUYwA3P9R1k/edit?tab=t.0
Question No: 10
Sample Test:
abcbc
cba
output : 3

lhs
rhs
output: -1

daabe
abe
output is: 2
* */
public class StringBappearInStringA {
    static int startIdx=-1;
    public static void main(String[] args) {
        String a="abcbc";
        String b="cba";
//       boolean ans= isStringBAppearInA(a,b);
//        System.out.println(ans);
        int ans=findStringBThereInAOrNotWhileMakingAtMostOneCharacterChange(a,b);
        System.out.println(ans);
    }

    private static boolean isStringBAppearInA(String a,String b){
        /*   i
           a=bcdcd
           b=dcd
             j
        * */
        int n=a.length();
        int m=b.length();
       int i=0;
       int j=0;
       int count=0;
         while(i<n && j<m){
             if(a.charAt(i)==b.charAt(j)){
                 i++;
                 j++;
                 count++;
             }else{
                 i++;
             }
         }
      if(count==m) return true;
      return false;
    }

    private static boolean isStringBAppearInAAlsoTrackTheStartIdx(String a,String b){
        /*   i
           a=bcdcd
           b=dcd
             j
        * */
        int n=a.length();
        int m=b.length();
        int i=0;
        int j=0;
        int count=0;
        while(i<n && j<m){
            if(a.charAt(i)==b.charAt(j)){
                if(startIdx==-1){
                    startIdx=i;
                }
                i++;
                j++;
                count++;
            }else{
                i++;
            }
        }
        if(count==m) return true;
        return false;
    }

    private static int findStringBThereInAOrNotWhileMakingAtMostOneCharacterChange(String a,String b){
        //TC: (n-1)*16*M
        for(int i=1;i<b.length();i++){// As per constraint 0th Letter of B we can't change,so starting with 1st
            for(char c='a';c<='z';c++){// Atlmost 1 chacrter can change in B , so trying every possibilty at ith place
                char[] tmp=b.toCharArray();
                tmp[i]=c;
                String newBString=new String(tmp);
                  if(isStringBAppearInAAlsoTrackTheStartIdx(a,newBString)){
                      return startIdx+1;
                  }
            }
        }
        return -1;
    }
}
