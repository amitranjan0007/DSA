package desiqna;

import java.util.HashMap;
import java.util.Scanner;

public class TwoColors_CodeChef_24_March {
    private static Scanner scanner;
    public static void main(String[] args) {
        scanner=new Scanner(System.in);
        int t=scanner.nextInt();
        while(t-->=1){

            int n=scanner.nextInt();
            int m=scanner.nextInt();
            int b[]=new int[m+1];
               for(int i=1;i<=m;i++){
                   b[i]=scanner.nextInt();
               }
           int res= numberOfPairsSubOptimal(b,n,m);
            System.out.println(res);
        }
    }

    private static int numberOfPairsBruteForce(int[]b,int target){
        int count=0;
        int n=b.length-1;
           for(int i=1;i<=n;i++){
               for(int j=1;j<=n;j++){
                   if(i!=j){
                       for(int left=1 ;left<=b[i];left++){
                           int right=target-left;
                             if(right>=1 && right<=b[j]){
                                 count++;
                             }
                       }
                   }
               }
           }
        return count;
    }

    private static int numberOfPairsSubOptimal(int[]b,int n ,int m){
        HashMap<Integer,Integer> map=new HashMap<>();
        for(int i=1;i<b.length;i++){
            map.put(b[i],map.getOrDefault(b[i],0));
        }

        int[] prefix = new int[n+1];
        prefix[0] = n;
        prefix[1] = n;
        for (int k =2; k <=n; k++) {
            prefix[k] = prefix[k - 1] - map.getOrDefault(k-1,0);
        }

        int total = 0;

        for(int j=1;j<=n;j++){
            for(int right=1;right<=b[j];right++){
               if(m-right>=0) total=total+prefix[m-right];
            }
        }

        return total;
    }

}
/*
3
5 2
2 4
5 2
3 4
12 3
5 9 8

* */