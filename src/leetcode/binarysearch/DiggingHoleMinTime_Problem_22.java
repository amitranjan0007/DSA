package leetcode.binarysearch;

import java.util.Arrays;
import java.util.Scanner;


//Problem-22
public class DiggingHoleMinTime_Problem_22 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int m=scanner.nextInt();
        int k=scanner.nextInt();//k part
        int[]b=new int[m];
        for(int i=0;i<m;i++){
            b[i]=scanner.nextInt();
        }
        int l= Arrays.stream(b).max().getAsInt();
        int h= Arrays.stream(b).sum();
        while(l<=h){
            int mid=(l+h)>>1;
            if(isPossiable(b,mid,k)){
                h=mid-1;
            }else{
                l=mid+1;
            }
        }
        System.out.println(h);

    }

    private static boolean isPossiable(int[] b, int capacity, int k) {
        int part=1;
        int sum=0;
        for(int i=0;i<b.length;i++){
            sum+=b[i];
              if(capacity<=sum){
                  part++;
                  sum=b[i];
              }
        }
        return part<=k;
    }
}
