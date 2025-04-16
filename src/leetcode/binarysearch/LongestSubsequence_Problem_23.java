package leetcode.binarysearch;

import java.util.Arrays;
import java.util.Scanner;

/*problem-23
https://docs.google.com/document/d/1cUYKbpG1Xi_VvVtZYQlZ4paRVhh5mUg9QYQNXk4GKlo/edit?tab=t.0
https://drive.google.com/file/d/1Ulp8sTECSNax9asSt3I7JfaokqZ8deIr/view
* */
public class LongestSubsequence_Problem_23 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();
        int m=scanner.nextInt();
        int[]b=new int[n];
        for(int i=0;i<n;i++){
            b[i]=scanner.nextInt();
        }
        int l=1;
        int h=n;
        while(l<=h){
            int mid=(l+h)>>1;
              if(isPossiable(b,mid,m)){
                  l=mid+1;
              }else{
                  h=mid-1;
              }
        }
        //r r r r w w w w
        System.out.println(h);
    }

    private static boolean isPossiable(int[] b, int k, int m) {
        int[] arr=new int[b.length];
        for(int i=1;i<=b.length;i++){
            arr[i-1]=i*k+b[i-1];
        }

        Arrays.sort(arr);
        int sum=0;
        for(int i=0;i<k;i++){
            sum+=arr[i];
        }
        return sum<=m;
    }
}
