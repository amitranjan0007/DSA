package leetcode.hashing;

public class SumOfMatchingCharacter {
    /*
     https://docs.google.com/document/d/1Vo7ClwGzUrNtg-J895Gpt5pSkF8C5qxU_MEBw3SrXco/edit?tab=t.0
     s[0]="abccca"
     s[i]="aa"
     s
    * */
    public static void main(String[] args) {
        String[] str={"bbz","zaz","aaa","zaa","zzz"};
        sumOfMatchingCharacterBruteForce(str);
        sumOfMatchingCharacterOptimal(str);
    }



    private static void sumOfMatchingCharacterBruteForce(String[] str){
        int n=str.length;
        int res=0;
        int[] result=new int[n];
        for(int i=n-1;i>=0;i--){
            res=0;
            if(i==n-1) continue;
            else{
                for(int j=i+1;j<n;j++){
                    int count=countMatching(str[i],str[j]);
                    res+=count;
                }
            }
            result[i]=res;
        }
        for(int num:result){
            System.out.print(num+" ");
        }
        System.out.println();
    }

    private static int countMatching(String s1,String s2){
       if(s1.length()>s2.length()) return countMatching(s2,s1);
       int n=s1.length();
       int count=0;
      for(int i=0;i<n;i++){
          if(s1.charAt(i)==s2.charAt(i)) count++;
      }
      return count;
    }


    private static void sumOfMatchingCharacterOptimal(String[] str){
       /*
           We should have to understand this clearly.
           0 1 2 3 4 5
       0   a b c d
       1   a b a f
       2   c c a a d c
       3   a c c f e

       result[]=[0,0,0,0]
       see each character at column wise from bottom to top,
                          0 1 2 3 4
       Let's take s[3] => a c c f e
                  s[2] => c c a a d c
                  s[1] => a b a f
                  for column 0 of s[3] there is 'a' what is count present ? 0
                      column 1 of s[3] there is 'c' what is count present ? 0
                      column 2 of s[3] there is 'c' what is count present ? 0
                      column 3 of s[3] there is 'f' what is count present ? 0
                      column 4 of s[3] there is 'e' what is count present ? 0
                      result[3] = 0

           a b c d e f g h i j k l m ..........z
        0  1
        1      1
        2            1
        3          1

        Let's take        0 1 2 3 4 5
                  s[2] => c c a a d c
                  s[3] => a c c f e
                  for column 0 of s[2] there is 'c' what is count present ? 0
                      column 1 of s[2] there is 'c' what is count present ? 1, after that we add current  c to count means 1 to 2
                      column 2 of s[2] there is 'a' what is count present ? 0
                      column 3 of s[2] there is 'a' what is count present ? 0
                      column 4 of s[2] there is 'd' what is count present ? 0
                      column 5 of s[2] there is 'c' what is count present ? 0
                      result[2] = 1 that's true , only c matches with it's below row

           a b c d e f g h i j k l m ..........z
        0  1   1
        1      2
        2  1         1
        3  1     1 1
        4             1
        5      1
        6
        ..
        10^5


         Let's take        0 1 2 3 4 5
                  s[1] =>  a b a f
                  s[2] =>  c c a a d c
                  s[3] =>  a c c f e
                  for column 0 of s[1] there is 'a' what is count present ? 1
                      column 1 of s[1] there is 'b' what is count present ? 0
                      column 2 of s[1] there is 'a' what is count present ? 1
                      column 3 of s[1] there is 'f' what is count present ? 1
                      result[1] = 3 i.e right a b f see below row present 3 times

           a b c d e f g h i j k l m ..........z
        0  2   1
        1    1  2
        2  2        1
        3  1     1  2
        4             1
        5      1
        6
        ..
        10^5


         Let's take        0 1 2 3 4 5
                  s[0] =>  a b c d
                  s[1] =>  a b a f
                  s[2] =>  c c a a d c
                  s[3] =>  a c c f e
                  for column 0 of s[0] there is 'a' what is count present ? 2
                      column 1 of s[0] there is 'b' what is count present ? 1
                      column 2 of s[0] there is 'c' what is count present ? 0
                      column 3 of s[0] there is 'd' what is count present ? 1
                      result[0] = 4 i.e right a b f see below row present 4 times

           a b c d e f g h i j k l m ..........z
        0  3   1
        1    2  2
        2  2   1     1
        3  1     1    2
        4             1
        5      1
        6
        ..
        10^5


        result[]=[4,3,1,0]
       * */

        int n=str.length;
        int[][] dp=new int[10000][26];
        int[] res=new int[n];

           for(int i=n-1;i>=0;i--){
               int count=0;
               String s=str[i];
                 for(int j=0;j<s.length();j++){
                    int c= s.charAt(j)-'a';
                    count=count+dp[j][c];
                    dp[j][c]=dp[j][c]+1;
                 }
               res[i]=count;
           }
          for(int num:res){
              System.out.print(num+" ");
          }

    }

}
