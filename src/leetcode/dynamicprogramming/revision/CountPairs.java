package leetcode.dynamicprogramming.revision;

import java.util.Arrays;
import java.util.Scanner;

public class CountPairs {
    public static void main(String[] args) {
        countTheSumBetweenLToR();
    }
    private static int countTheSumLessThenEqualK(int[]b, int k){
        int n=b.length;
        Arrays.sort(b);
        int count=0;
        for(int i=0,j=n-1;i<n;i++){
            int sum=b[i]+b[j];
            while(sum>k && i<j){
                j--;
                sum=b[i]+b[j];
            }
            if(i==j) break;
            count+=(j-i);
        }
        return count;
    }

    private static void countTheSumBetweenLToR(){
        //L-> sum R->Sum
        /*
           L=2 R=5 means number of pair whose sum is less then 2 && number of pair less then k

           if you want to find in b/1 L to R then

           Lets say R=5 ==> {0,1,2,3,4,5}
                    L=2 ==> {0,1,2} so to calculate L to R we make L one less then that is L=1
                    then R-L= {2,3,4,5} which is b/w L to R

                       n= 8
                        l= 2
                        r= 4
                        b[]=0 1 2 3 4 5 6 7
                        total valid pair sum of 1 is 1; i.e (0,1)
                        total valid pair sum of 4 is 6; i.e (0,4),(0,3),(0,2),(0,1),(1,3),(1,2)
                        but sum between 2 & 4 = 6-1=5 (ans=rCont-lCount)
                        output is 5
        * */

        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();
        //remember l & r is not  range it's a sum
        int l=scanner.nextInt();// l is sum
        int r=scanner.nextInt();//r is sum
        int[]b=new int[n];
        for(int i=0;i<n;i++){
            b[i]=scanner.nextInt();
        }
        int rCount=countTheSumLessThenEqualK(b,r);
        int lCount=countTheSumLessThenEqualK(b,l-1);
        int ans=rCount-lCount;
        System.out.println(ans);
    }
}
