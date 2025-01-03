package maths;

import java.util.*;

public class PrimeFactorisationOfNumber {
    /**
       Always Remember:
     Any number in the world can be represented as prime factor power :-
     -> y = p1^y1 * p2^y2 * ……………………………..
     -> It is the most basic and fundamental representation. -> It breaks the number in as many parts as possible.
       eg: 12 => 2*2*3=> 2*2*3=>2^2 * 3^1
           18 => 2*9 => 2*3*3 => 2^1 * 3^2

     How you approach the problem in brute force way ,You already know how to calculate the Factor Of Number
     * **/
    public static void main(String[] args) {
        printPrimeFactorisationOfNumber(18);

    }
    public static void printPrimeFactorisationOfNumber(int n) {
        /*
           TC: root(n)
        * */
        if (n == 1) {
            System.out.println("1 has no prime factors.");
            return;
        }


        HashMap<Integer,Integer> map=new HashMap<>();

        // Handle negative numbers
        if (n < 0) {
            map.put(-1, 1);
            n = -n;
        }


        for(int i=2;i<=n;i++){
            while(n%i==0){
                map.put(i,map.getOrDefault(i,0)+1);
                n=n/i;
            }
        }

        for(Map.Entry<Integer,Integer> entry:map.entrySet()){
            int key=entry.getKey();
            int value=entry.getValue();
            System.out.println(key+"^"+value);
        }
    }


}
