package desiqna.codeforce_1018;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

public class WonderfulGloves {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int t=scanner.nextInt();
        while(t-->0){
            int n=scanner.nextInt();
            int k=scanner.nextInt();
            int[]a=new int[n];
            int[]b=new int[n];
              for(int i=0;i<n;i++){
                  a[i]=scanner.nextInt();
              }
            for(int i=0;i<n;i++){
                b[i]=scanner.nextInt();
            }
            solve(a,b,k);
        }
    }

    private static void solve(int[] a, int[] b, int k) {
        int sum=0;
        int n=a.length;
        PriorityQueue<Integer> pq=new PriorityQueue<>(Collections.reverseOrder());
        for(int i=0;i<n;i++){
            if(a[i]>b[i]){
                sum+=a[i];
                pq.add(b[i]);
            }else {
                sum+=b[i];
                pq.add(a[i]);
            }
        }
        while(k-->1 && pq.size()>0){
            sum+=pq.poll();
        }
        sum+=1;

        System.out.println(sum);

    }
}
