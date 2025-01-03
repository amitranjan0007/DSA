package maths;

import java.util.HashMap;
import java.util.Map;

public class PrimeFactorisationOfNumberinArray {
    public static void main(String[] args) {
        int[] arr={6,12,18,24,30,36};
        primeFactorisation(arr);
    }

    private static void primeFactorisation(int[] arr){
        for(int i=0;i<arr.length;i++){
            int num=arr[i];
            HashMap<Integer,Integer> map=new HashMap<>();
            for(int j=2;j<=num;j++){
                while(num%j==0){
                    map.put(j,map.getOrDefault(j,0)+1);
                    num=num/j;
                }
            }
            System.out.println("--"+arr[i]+"--");
           for(Map.Entry<Integer,Integer> entries: map.entrySet()){
               System.out.print(entries.getKey()+"^"+entries.getValue());
               System.out.println();
           }
        }
    }

    private static boolean isPrime(int n){
        for(int i=2;i<=n;i++){
            if(n%i==0) return false;
        }

        return true;
    }
}
