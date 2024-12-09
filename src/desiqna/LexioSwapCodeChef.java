package desiqna;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LexioSwapCodeChef {
    /*
      Question Link : https://www.codechef.com/problems/LEX?tab=statement

      Observation: If the length of S is less than T, it can never be made equal to T.
      Now, group every A's and B's sequentially in A and B.
      Notice that, in order to
      delete two same consecutive characters, we should have either 'AAAB', or 'BAAA', or 'ABBB' or 'BBBA'.
      These four cases after deleting the second and third characters would be 'AB', 'BA', 'AB' and 'BA' respectively.
      Here we can notice that the count of the majority element has been decreased by 2.
      Hence, we can extend this operation further and always achieve a string X from string Y,
      if the they have same parity of A's and B's sequentially. Like 'AAABBBBA' and 'ABBA' both form 'ABBA' sequentially.
      Now, one edge case is that if all of the elements are either A or B, then we can never perform the allowed operation,
      hence the answer would be NO. Otherwise check for the aforementioned condition.

      count of the majority element has been decreased by 2.
      BBBA -> BA (Count of B is 3 & A is 1 so in answer B becomes 1 ,decreased by 2
      AAAB -> AB
      BAAA->  BA
      ABBB -> AB

      AAABBBBA->ABBBBA-->ABBA
      A->3    A->1     => 3-1=2 (2%2=0)
      B->4    B->2     => 4-2=2 (2%2=0)
      A->1    A->1     => 1-1=0 (0%2=0)

      S=AAABBBBA  T=AABA
      A->3  A->2  =>3-2 (1%2=1)
      B->4  B->1  =>4-1 (3%2=1)
      A->1  A->1  =>1-1 (0%2=0)
      AAABBBBA ->ABBBBA->ABBA(we can make this but we can't make this AABA, if the mod is 1 we cannot make)
      Mod signifies we are decompose the block of string or not


Test Input :
3
13 7
BBBBAAABAAAAA
BBABAAA
6 4
AAABBA
ABAB
10 4
ABBBAAAAAB
ABAB


Output:
YES
NO
YES

    * */
    static class Pair{
        char character;
        long frequency;
        Pair(char character,long frequency){
            this.character=character;
            this.frequency=frequency;
        }
    }

    public static void main (String[] args) throws java.lang.Exception
    {
        Scanner scanner = new Scanner(System.in);
        int testCaseCount = scanner.nextInt();
        while (testCaseCount-- > 0) {
            String result=solve(scanner)?"YES":"NO";
            System.out.println(result);
        }
        scanner.close();

    }

    public static boolean solve(Scanner scanner){
        long n = scanner.nextLong();
        long m = scanner.nextLong();
        String s = scanner.next();
        String t = scanner.next();


        if(n==m){
            return s.equals(t);
        }else if ((n - m) % 2 != 0) {

            return false;
        }

        List<Pair> sList= compressString(s);
        List<Pair> tList= compressString(t);

        if (sList.size() != tList.size() || sList.size() ==1 ) {

            return false;
        }


        for (int i = 0; i < sList.size(); i++) {
            if (sList.get(i).frequency < tList.get(i).frequency || (sList.get(i).frequency - tList.get(i).frequency) % 2 != 0

                    || sList.get(i).character != tList.get(i).character) {

                return false;
            }
        }


        return  true;
    }


    public static List<Pair> compressString(String s){
        List<Pair> list=new ArrayList<>();
        if (s.isEmpty()) {
            return list;
        }
        int count = 0;
        char tmp=s.charAt(0);
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)==tmp){
                count++;
            }else{
                list.add(new Pair(tmp,count));
                tmp=s.charAt(i);
                count=1;
            }
        }
        list.add(new Pair(tmp,count));
        return  list;
    }
}
