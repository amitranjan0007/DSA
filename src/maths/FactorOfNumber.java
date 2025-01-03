package maths;

import java.util.ArrayList;
import java.util.Collections;

public class FactorOfNumber {
    public static void main(String[] args) {
        printFactorOfNumberBruteForce(100);
        printFactorOfNumberOptimal(100);
    }
    public static void printFactorOfNumberBruteForce(int n) {
        /*
          Tc: O(n)
          sc:O(1) //neglecting saving answer into array
        * */
        ArrayList<Integer> res=new ArrayList<>();
        for(int i=1;i<=n;i++){
            if(n%i==0){
                res.add(i);
            }
        }
        for(int num:res){
            System.out.print(num+" ");
        }
        System.out.println();
    }

    public static void printFactorOfNumberOptimal(int n) {
        /*
        we have to add one time when i^2=n
           i*i=n
           i=n/i

           have to calculate upto sqrt(n)=sqrt(100)=10
           1 2 4 5 10 . . . . . .
        * */
        ArrayList<Integer> res=new ArrayList<>();
        for(int i=1;i<=Math.sqrt(n);i++){
            if(n%i==0){
                if(n/i==i){ //100/10=10
                    res.add(i);
                }else{
                    res.add(i);
                    res.add(n/i);
                }

            }
        }
        Collections.sort(res);
        for(int num:res){
            System.out.print(num+" ");
        }
    }
}
