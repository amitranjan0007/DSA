package leetcode.twopointer;

import java.util.HashMap;
/*
Problem No: Two Pointers: 25
https://docs.google.com/document/d/1qJm45nHXryDn2FPldVQUvFcUtP0c152ubx7CtXeYycw/edit?addon_store&tab=t.0
https://drive.google.com/file/d/1D_PnuM_OsKnExmZ6ipqHF971SngU5sWz/view
* */
public class MinOperationNeedToConvertArrayBToArrayA {
    public static void main(String[] args) {
        int[]a={4,2,3,1,5,6};
        int[]b={3,1,4,6,5,2};
        MinOperationNeedToConvertArrayBToArrayABruteForce(a,b);
        MinOperationNeedToConvertArrayBToArrayAOptimal(a,b);
        MinOperationNeedToConvertArrayBToArrayAMostOptimal(a,b);
    }
    private static void MinOperationNeedToConvertArrayBToArrayABruteForce(int[]a,int[]b){
        int maxi=0;
        int n=b.length;
        //find largest subarray in a such that it's subset in array b
        for(int i=0;i<n;i++){
            for(int j=i;j<n;j++){
                if(check(a,b,i,j)){
                    maxi=Math.max(maxi,j-i+1);
                }
            }
        }
        int ans=(n-maxi);//min number of operation
        System.out.println(ans);
    }
    private static void MinOperationNeedToConvertArrayBToArrayAOptimal(int[]a,int[]b){
        /*
         front unchanged back
         if unchanged part is the longest subarray that exist as a subset of b
         let say subarray 1...4 is longest valid , then it's guarnteed that 1...5 is invalid
         y?
          a=1 2 3 4 9
          b=9 1 2 3 4
            1 2 3 4
        * */
        int maxi=0;
        int n=b.length;
        //find largest subarray in a such that it's subset in array b
        for(int j=0,i=0;j<n;j++){
            while(!check(a,b,i,j)){
               i++;
            }
            maxi=Math.max(maxi,j-i+1);
        }
        int ans=(n-maxi);//min number of operation
        System.out.println(ans);
    }

    private static void MinOperationNeedToConvertArrayBToArrayAMostOptimal(int[]a,int[]b){
        /*
         front unchanged back
         if unchanged part is the longest subarray that exist as a subset of b
         let say subarray 1...4 is longest valid , then it's guarnteed that 1...5 is invalid
         y?
          a=1 2 3 4 9
          b=9 1 2 3 4
            1 2 3 4
        * */
        int maxi=0;
        int n=b.length;
        HashMap<Integer,Integer> map=new HashMap<>();
        int idx=0;
        for(int num:b){
            map.put(num,idx++);
        }
        int count=0;
        //find largest subarray in a such that it's subset in array b
        for(int j=0;j<n;j++){
            if (j == n - 1 || map.get(a[j]) < map.get(a[j + 1])) {
                count++;
                maxi = Math.max(maxi, count + 1);
            } else {
                count = 0;
            }
        }
        int ans=(n-maxi);//min number of operation
        System.out.println(ans);
    }

    private static boolean check(int[] a, int[] b, int i, int j) {
        int n=b.length;
        int k=i;
        int c=0;
        int totalMatch=0;
        while(k<=j && c<n){
            if(a[k]==b[c]){
                totalMatch++;
                k++;
            }else {
                c++;
            }
        }
        return totalMatch==(j-i+1);
    }
}
