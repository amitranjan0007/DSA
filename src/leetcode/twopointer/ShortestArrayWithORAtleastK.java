package leetcode.twopointer;

public class ShortestArrayWithORAtleastK {
    /*
      Two Pointers
      Leetcode Question: https://leetcode.com/problems/shortest-subarray-with-or-at-least-k-ii/
      Problem No: 14
    * */
    public static void main(String[] args) {
       int[]b={1,2,3};
       int k=2;
       // ShortestArrayWithORAtleastKBruteForce(b,k);
        ShortestArrayWithORAtleastKOptimal(b,k);
    }
    public static int ShortestArrayWithORAtleastKBruteForce(int[] nums, int k) {
        int mini=Integer.MAX_VALUE;
        for(int i=0;i<nums.length;i++){
            int sum=0;
            for(int j=i;j<nums.length;j++){
                sum=sum|nums[i]|nums[j];
                if(sum>=k){
                    mini=Math.min(mini,j-i+1);
                }
            }
        }

        return mini==Integer.MAX_VALUE?-1:mini;
    }
    private static void ShortestArrayWithORAtleastKOptimal(int[]b,int k){
        int n=b.length;
        int bSet[][]=new int[31][n];
        int[][] prefix=new int[31][n];
           for(int i=0;i<31;i++){
               for(int j=0;j<n;j++){
                   //if ith bit is set or not
                   bSet[i][j]=(b[j] & (1<<i))>0 ? 1:0;
               }
           }

        for(int i=0;i<31;i++){
            for(int j=0;j<n;j++){
                //if ith bit is set or not
                if(j==0)prefix[i][j]=bSet[i][j];
                else prefix[i][j]=prefix[i][j-1]+bSet[i][j];
            }
        }

           int mCount=0;
           int mini=Integer.MAX_VALUE;
           for(int j=0,i=0;j<n;j++){
              mCount= countSum(prefix,i,j);
                 while(mCount>=k && i<=j){
                     mini=Math.min(mini,j-i+1);

                     i++;
                     mCount=countSum(prefix,i,j);

                 }
           }
        System.out.println(mini);
    }

    private static int countSum(int[][]p,int i,int j){
        int num=0;
        for(int l=0;l<31;l++){
           int number= i==0?p[l][j]: p[l][j]-p[l][i-1];
             if(number>=1){
                 num=num+(1<<l);
             }
        }
        return num;
    }
}
