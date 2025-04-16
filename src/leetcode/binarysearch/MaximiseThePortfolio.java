package leetcode.binarysearch;

import java.util.Arrays;
import java.util.Scanner;


//https://www.desiqna.in/9717/de-shaw-oa-sde-set-55-kumar-k
//https://drive.google.com/file/d/1l7Qah1-fYKAQnetP0K4PSMYSEzjjSgVI/view
//Question:20-Deshaw
public class MaximiseThePortfolio {

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();
        int k=scanner.nextInt();//defines k distinct element in group
        int[]b=new int[n];

        for(int i=0;i<n;i++){
            b[i]=scanner.nextInt();
        }
        Arrays.sort(b);
        int l=0;
        int h= Arrays.stream(b).sum();
        int ans=-1;
        while(l<=h){
            int mid=(l+h)>>1;
            if(isPossiable(mid,b,k)){
                ans=mid;
                l=mid+1;
            }else{
                h=mid-1;
            }
        }
        System.out.println(ans);
    }

    private static boolean isPossiable(int g, int[] b, int k) {
       int t=g*k;
       for(int i=0;i<b.length;i++){
           if(b[i]>g){
               t=t-g;
           }else{
               t=t-b[i];
           }
       }
       return t<=0;
    }

}
/*
6
6
10 8 5 5 5 5
output: 5

3
2
3 3 3
Output: 4

* */