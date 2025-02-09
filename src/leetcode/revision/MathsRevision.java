package leetcode.dynamicprogramming.revision;

import javafx.scene.transform.Scale;

import java.util.*;

public class MathsRevision {
    public static void main(String[] args) {
        //factorOfNumber();
        //factorOfNumberOpt();
        System.out.println(isPrime(7));
       // primeFactorOfNumberOpt(20);
        //primeFactorOfNumberInArray();
       // primeInRangeSubOptimal(100);
       // primeInRangeOptimal_Seive(100);
        primeInRangeOptimal_SeiveInRange(13,100);
    }
    private static void factorOfNumber(){
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();
        List<Integer> res=new ArrayList<>();
        for(int i=1;i<=n;i++){
            if(n%i==0){
                res.add(i);
            }
        }
        res.forEach(t->{
            System.out.print(t+" ");
        });
    }
    private static void factorOfNumberOpt(){
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();
        List<Integer> res=new ArrayList<>();
        for(int i=1;i<=Math.sqrt(n);i++){
            if(n%i==0){
                if(n/i==i){
                    res.add(i);
                }else{
                    res.add(i);
                    res.add(n/i);
                }
            }

        }
        res.forEach(t->{
            System.out.print(t+" ");
        });
    }
    private static boolean  isPrime(int n){
        for(int i=2;i<n;i++){
            if(n%i==0){

               return false;
            }
        }
        return true;
    }

    private static void primeFactorOfNumberOpt(int n){

        HashMap<Integer,Integer> map=new HashMap<>();
        if(n<0){//-12= -1 * 12
            map.put(-1,1);
            n=-1*n;
        }
        if(n==1) {
            System.out.println(1);
            return;
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
            System.out.println(key+"----"+value);
        }
    }

    private static void primeFactorOfNumberInArray(){
       // int[] b={6,12,18,24,30,36};

        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();
        int[] b=new int[n+1];
        for(int i=1;i<=n;i++){
            b[i]= scanner.nextInt();
        }
        for(int i=1;i<=n;i++){
            System.out.println("--Prime Factorisation of number "+b[i]+"--");
            primeFactorOfNumberOpt(b[i]);

        }
    }

    private static void primeInRangeSubOptimal(int n){
        //Tc:O(n^2)
        List<Integer> res=new ArrayList<>();
        for(int i=2;i<=n;i++){
            boolean prime=isPrime(i);
              if(prime){
                  res.add(i);
              }
        }
        res.forEach(t->{
            System.out.print(t+" ");
        });
    }

    private static List<Integer> primeInRangeOptimal_Seive(int n){
        //Tc:O(nlog(log(n)))
        boolean[]visited=new boolean[n+1];

        List<Integer> res=new ArrayList<>();
        for(int i=2;i<=Math.sqrt(n);i++){
            if(!visited[i]){
                for (int j=i*i;j<=n;j=j+i){
                    visited[j]=true;
                }
            }
        }
        for(int i=2;i<=n;i++){
            if(!visited[i]){
                res.add(i);
            }
        }
//        res.forEach(t->{
//            System.out.print(t+" ");
//        });
        return res;
    }

    private static List<Integer> primeInRangeOptimal_SeiveInRange(int start,int end){
       List<Integer> primes= primeInRangeOptimal_Seive(end);
       List<Integer> result=new ArrayList<>();
       for(int prime:primes){
           if(prime>=start){
               result.add(prime);
           }
       }
        result.forEach(t->{
            System.out.print(t+" ");
        });
       return result;
    }

}
