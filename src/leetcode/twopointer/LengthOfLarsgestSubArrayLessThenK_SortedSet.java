package leetcode.twopointer;

import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;

public class LengthOfLarsgestSubArrayLessThenK_SortedSet {
    public static void main(String[] args) {
//        SortedSet<CustomWrapper> sortedSet=new TreeSet<>();
//        sortedSet.add(new CustomWrapper('c',0));
//        sortedSet.add(new CustomWrapper('b',1));
//        sortedSet.add(new CustomWrapper('c',2));
//        sortedSet.add(new CustomWrapper('d',3));
//        sortedSet.add(new CustomWrapper('a',4));
//        for(CustomWrapper wrapper:sortedSet){
//            System.out.print(wrapper.c+" ");
//        }
        largestSubArrayLessThenOrEqualK();
    }
   private static void largestSubArrayLessThenOrEqualK(){
        /*
         k=1
         abbbacaabbbb
         output: 6
        * */
       SortedSet<CustomWrapper> sortedSet=new TreeSet<>();
       Scanner scanner=new Scanner(System.in);
       int k=scanner.nextInt();
       String s=scanner.next();
       int n=s.length();
       int first=-1;
       int last=-1;
       int maxi=Integer.MIN_VALUE;
           for(int j=0,i=0;j<n;j++){
               sortedSet.add(new CustomWrapper(s.charAt(j),j));
                 while(sortedSet.last().c-sortedSet.first().c>k){//max-min
                     sortedSet.remove(new CustomWrapper(s.charAt(i),i));
                     i++;
                 }
                 if(maxi<j-i+1){
                     System.out.println();
                     first=i;
                     last=j;
                     maxi=j-i+1;
                 }

           }
           String longestSubstring=s.substring(first,last+1);
       System.out.println("The longest substring is "+longestSubstring+" of lenegth is "+maxi );
   }

}
class CustomWrapper implements Comparable<CustomWrapper>{
    char c;
    int idx;
    public CustomWrapper(char c,int idx){
        this.c=c;
        this.idx=idx;
    }
    @Override
    public int compareTo(CustomWrapper o) {
        if(c==o.c){
            return idx-o.idx;
        }else{
            return c-o.c;
        }
    }
}