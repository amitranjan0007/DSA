package desiqna;

import java.util.HashMap;
import java.util.Scanner;

public class TwoColors_CodeChef_24_March {
    private static Scanner scanner;
    public static void main(String[] args) {
        scanner=new Scanner(System.in);
        int t=scanner.nextInt();
        while(t-->=1){

            int target=scanner.nextInt();
            int m=scanner.nextInt();
            int b[]=new int[m+1];
               for(int i=1;i<=m;i++){
                   b[i]=scanner.nextInt();
               }
           int res= numberOfPairsSubOptimal(b,target);
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

    private static int numberOfPairsSubOptimal(int[]b,int target){
        HashMap<Integer,Integer> map=new HashMap<>();
        for(int i=1;i<b.length;i++){
            map.put(b[i],map.getOrDefault(b[i],0));
        }

        int[] prefix = new int[target + 2];
        prefix[0] = target;
        prefix[1] = target;
        for (int k =2; k <=target; k++) {
            prefix[k] = prefix[k - 1] - map.getOrDefault(k-1,0);
        }

        int total = 0;
        for (int k = 1; k <= b.length-1; k++) {
           total=total+prefix[target-k];
        }
        return total;
    }

}
