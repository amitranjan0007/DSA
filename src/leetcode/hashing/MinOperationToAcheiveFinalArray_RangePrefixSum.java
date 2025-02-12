package leetcode.hashing;


import java.util.Arrays;

//https://docs.google.com/document/d/1vP8BL7NqX18xi3lxTMIVlGsp3uvf1XW53ZrnYUh6NmQ/edit?tab=t.0
//https://drive.google.com/file/d/1Xo5G3fp8e-Urc96y3OA7jepTDgLU-LVw/view
//https://drive.google.com/file/d/1mJ-qsOdK-k-iWxx_DcKpChl_8eyX47tZ/view
public class MinOperationToAcheiveFinalArray_RangePrefixSum {
    public static void main(String[] args) {
        int[]b={1,5,0,0,1,3};
        int[]c=new int[b.length*2+1];
        int l=2;
       // minOperationToAcheiveFinalArrayBruteForce(b,c,l);
       // minOperationToAcheiveFinalArrayOptimal(b,l);


        int[]b1={4,3,1,6,4,6};
        int m=2;
        minOperationToAcheiveFinalArray_GOOGLE_OA(b1,l,m);
        minOperationToAcheiveFinalArray_GOOGLE_OA_Optimal(b1,l,m);
    }

    private static void minOperationToAcheiveFinalArrayBruteForce(int[] b,int[]c,int l){
        int op=0;
        int n=b.length;
        for(int i=0;i<n;i++){
            if(b[i]>0){
                if(b[i]>c[i]){
                    int diff=Math.abs(b[i]-c[i]);
                    op=op+diff;
                      for(int j=i;j<=i+l-1;j++){
                          c[j]=c[j]+diff;
                      }
                }
            }
        }
        System.out.println(op);
    }
    private static int minOperationToAcheiveFinalArrayOptimal(int[] b, int l) {
        int n = b.length;
        int[] c = new int[n + 1]; // Difference array
        int op = 0; // Number of operations
        int prefixSum = 0; // Prefix sum of the difference array

        for (int i = 0; i < n; i++) {
            prefixSum += c[i]; // Update prefix sum
            if (b[i] > prefixSum) {
                int diff = b[i] - prefixSum; // Calculate the difference
                op += diff; // Add to the number of operations
                prefixSum += diff; // Update prefix sum
                if (i + l < n) {
                    c[i + l] -= diff; // Decrement the difference array at i+l
                }
            }
        }

       return op;
    }

    private static int minOperationToAcheiveFinalArray_GOOGLE_OA(int[] b, int l,int m) {
        int low= Arrays.stream(b).min().getAsInt()-m;
        int high=Arrays.stream(b).max().getAsInt();
        int ans=-1;
        for(int g=low;g<=high;g++){
            if(check(g,b,l,m)){
                ans=g;
                break;
            }
        }
        System.out.println(ans);
        return ans;
    }

    private static int minOperationToAcheiveFinalArray_GOOGLE_OA_Optimal(int[] b, int l,int m) {
        int low= Arrays.stream(b).min().getAsInt()-m;
        int high=Arrays.stream(b).max().getAsInt();
        while(low<=high){
            int mid=(low+high)/2;
            if(check(mid,b,l,m)){
                high=mid-1;
            }else {
                low=mid+1;
            }
        }
        System.out.println(low);
        return low;

    }

    private static boolean check(int g, int[] b, int l, int m) {
        //I want all the value less then <=g
        int n=b.length;
        int[] updatedArr=new int[n];
        for(int i=0;i<n;i++){
            if(b[i]>g){
                updatedArr[i]=b[i]-g;
            }
        }
        int minCost=minOperationToAcheiveFinalArrayOptimal(updatedArr,l);
        return minCost<=m;
    }
}
