package maths;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SieveToGeneratePrimeNumber {
    public static void main(String[] args) {
        //Generating all the prime number between given numbers
        /*
           Brute Force take : n*sqrt(n)
          Sieve take
        * TC: O(n⋅ln(ln(n)))
        * SC: O(n)

          Let compare when n =10^8
          Brte Fooce 10^8 * sqrt(10^8)= 10^12
          seive take : 10^8⋅ln(ln(10^8))== 10^8⋅ln(8) = 10^8*1=10^8 (ln(8)==.9891 => approx 1)
        * */
        sieveRange(1,97);
        System.out.println();
        bruteForceWay(97);
    }
    public static void bruteForceWay(int n) {
        List<Integer> ans=new ArrayList<>();
        for(int i=2;i<=n;i++){
            if(isPrime(i)){
                ans.add(i);
            }
        }

        for(int num:ans){
            System.out.print(num+" ");
        }
        System.out.println();
    }

    private static boolean isPrime(int num) {
         for(int i=2;i<=Math.sqrt(num);i++){
             if(num%i==0) return false;
         }
         return true;
    }

    //printAllPrimeNumberUptoNUsingSieveOfEratosthenes
    public static List<Integer> sieve(int n) {
        //Sieve of Eratosthenes. To generate all prime numbers from 1 to N.
        boolean[] visited=new boolean[n+1];//if n=5 0 1 2 3 4 5 so n+1
        Arrays.fill(visited,true);
        //0 & 1 can't be prime factorisation
        visited[0]=false;
        visited[1]=false;
        int limit= (int)Math.sqrt(n);
        for(int i=2;i<=limit;i++){
            if(visited[i]){
                for(int j=i*i;j<=n;j+=i){//have to jump every i step,why i*i suppose i =3, if we do normally we should visit 6 but 6 already visited by 2 earlier
                    visited[j]=false;
                }
            }
        }


        ArrayList<Integer> res=new ArrayList<>();
        for(int i=2;i<=n;i++){
            if(visited[i]){
                res.add(i);
            }
        }
        return res;
//          res.forEach(item->System.out.print(item+" "));
    }

    public static void sieveRange(int start, int end) {
        List<Integer> primes = sieve(end);
        List<Integer> rangePrimes = new ArrayList<>();

        for (int prime : primes) {
            if (prime >= start) {
                rangePrimes.add(prime);
            }
        }

        rangePrimes.forEach(item->System.out.print(item+" "));
    }
}
