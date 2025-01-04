package leetcode.hashing;

public class MinCostRehabillation {
    public static void main(String[] args) {
        //https://www.desiqna.in/15920/microsoft-oa-september-2023-freshers-hiring-sde1-set-115

//       int[] arr={4,2,5,4,3,5,1,4,2,7};
//       int x=3;
//       int y=2;
        /*
         Observation: Select exactly x number at a exact distance of y , such that sum of x number is min
        * */
        int[] arr={10,3,4,7};
        int x=2;
        int y=3;
        minCostBruteForce(arr,x,y);
        minCostOptimal(arr,x,y);
    }
    private static void minCostBruteForce(int[] arr,int x,int y){
        int mini=Integer.MAX_VALUE;
        int n=arr.length;
        for(int i=0;i<n;i++){
            int j=i;
            int cost=0;
            int count=0;

              while(j>=0 && count<x){
                  cost+=arr[j];
                  count++;
                  j=j-y;
              }
              if(count==x){
                  mini=Math.min(mini,cost);
              }
        }
        System.out.println(mini);
    }
    private static void minCostOptimal(int[] arr,int x,int y){
        int mini=Integer.MAX_VALUE;
        int n=arr.length;
        int[] pSum=new int[n];
        pSum[0]=arr[0];
        for(int i=1;i<n;i++){
            if(i-y>=0)
             pSum[i]=pSum[i-y]+arr[i];
            else pSum[i]=arr[i];
        }

        for(int i=0;i<n;i++){
            int index = i - (x - 1) * y; // by doing so you have selected exact x number
            System.out.println(index);
            if(index>=0){
                int g= pSum[i];
                if(index-y>=0)
                    g=g-pSum[index-y];// you have to subtract the before sum because till (index) you have all the sum at distance y , you have to select x only

                mini=Math.min(mini,g);
            }
        }
        System.out.println(mini);
    }
}
