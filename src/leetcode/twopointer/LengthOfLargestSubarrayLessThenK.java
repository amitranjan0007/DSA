package leetcode.twopointer;

import java.util.SortedSet;
import java.util.TreeSet;

public class LengthOfLargestSubarrayLessThenK {
    public static void main(String[] args) {
      int [] arr={1,2,3,4,5,6};
      int k=10;
//        largestSubArrayLengthLessThenKBrute(arr,k);
//        largestSubArrayLengthLessThenKOptimal(arr,k);
//        ValidSubstringWhereAnyPairCharacterLessThenK("aabdzd",2);
        SortedSet<Character> sortedSet=new TreeSet<>();
        sortedSet.add('a');
        sortedSet.add('a');
        sortedSet.add('b');
       // sortedSet.remove('a');
        for(Character c:sortedSet){
            System.out.print(c+" ");
        }
       // System.out.println(sortedSet.first()+""+sortedSet.last());

    }

    private static void largestSubArrayLengthLessThenKBrute(int arr[],int k){

       int maxi=0;
       int n=arr.length;
       for(int i=0;i<n;i++){
           int sum=0;
           for(int j=i;j<n;j++){
               sum+=arr[j];
               if(sum>k){
                  break;
               }
               maxi=Math.max(maxi,j-i+1);
           }
       }
        System.out.println(maxi);
    }
    private static void largestSubArrayLengthLessThenKOptimal(int arr[],int k){
        int maxi=0;
        int n=arr.length;
        int sum=0;
        for(int j=0,i=0;j<n;j++){
            sum+=arr[j];
              while(sum>k){
                  sum-=arr[i];
                  i++;
              }
            maxi=Math.max(maxi,j-i+1);
        }
        System.out.println(maxi);
    }
    private static void ValidSubstringWhereAnyPairCharacterLessThenK(String s,int k){
         /*
         To be valid substring max-min<=k
         abcdef let's say k=4
         abcde is valid ,what you did take smallest character & take largest char ,take diff<=k
         but in O(1) time ,how you know the max-min ===> sorted set
        * */
        int n=s.length();
        int maxi=0;
        SortedSet<Character> sortedSet=new TreeSet<>();

        for(int j=0,i=0;j<n;j++){
            sortedSet.add(s.charAt(j));
            while(sortedSet.last()-sortedSet.first()>k){
                sortedSet.remove(s.charAt(i));
                i++;
            }
            maxi=Math.max(maxi,j-i+1);
        }
        System.out.println(maxi);
    }
}
