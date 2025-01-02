package leetcode.hashing;

public class IsValidTriplet {
    /*
      Observation:
         1) Brute Force is Easy ,just need to find A[i]<A[j]<A[k]
         2)Optimisation

           ..............i...................
           ..............5...................

           At index i there is a 5 ,who want to become valid triplet so
           you need to find on it's left, number greater then 5
                 and
           you need to find on it's right, number greater then 5

           so on left if you want to find number greater then 5, think it's hard :)
           so on left find the max
                and
           on right find the min

           if you satisfy the condition then it find valid triplet

           eg: 18  5  4  3  2  1  8  10
           min 18  5  4  3  2  1  1  1
           max 18  10 10 10 10 10 10 10

           Now I ask you can 4 become valid triplet ? No
           in it's left all the number is greater then 4

           Now Can 3 ? No

           can 8? Yes Yes
           as you see one step back of 8 i.e i-1 you find 1 is small & on it;s right 10 is big so it can be valid triplet

    * **/
    public static void main(String[] args) {
        int[] arr= {18 ,5 ,4 ,3 ,2 ,1 ,8,10 };
        isTripletValid(arr);
        isTripletValidSpaceOpt(arr);
    }
    public static void isTripletValid(int[] arr) {
        /*
           TC: 3*O(n)
           Sc: 2*O(n)

           can you optimise the space ? Yes
           when you calculating pMin ,you need the min on left ,can't you store min in variable
        * */
         int n= arr.length;
         if(n==1 || n==2){
             System.out.println("false");
             return;
         }
         int[] pMin=new int[n];
         int[] pMax=new int[n];
         pMin[0]=arr[0];
         pMax[n-1]=arr[n-1];
        for(int i=1;i<n;i++){
            if(pMin[i-1]>arr[i]){
                pMin[i]=arr[i];
            }else{
                pMin[i]=pMin[i-1];
            }
        }
        for(int i=n-2;i>=0;i--){
            if(pMax[i+1]<arr[i]){
                pMax[i]=arr[i];
            }else{
                pMax[i]=pMax[i+1];
            }
        }

        for(int i=1;i<n-1;i++){
            if(pMin[i-1]<arr[i] && pMax[i+1]>arr[i]){
                System.out.println("true");
               return;
            }
        }
        System.out.println("false");

    }

    public static void isTripletValidSpaceOpt(int[] arr) {
        /*
           TC: 3*O(n)
           Sc: 2*O(n)

           can you optimise the space ? Yes
           when you calculating pMin ,you need the min on left ,can't you store min in variable
        * */
        int n= arr.length;
        if(n==1 || n==2){
            System.out.println("false");
            return;
        }
        int min=Integer.MAX_VALUE;
        int[] pMax=new int[n];
        pMax[n-1]=arr[n-1];

        for(int i=n-2;i>=0;i--){
            if(pMax[i+1]<arr[i]){
                pMax[i]=arr[i];
            }else{
                pMax[i]=pMax[i+1];
            }
        }

        for(int i=0;i<n-1;i++){
            if(min<arr[i] && pMax[i+1]>arr[i]){
                System.out.println("true");
                return;
            }
            min=Math.min(min,arr[i]);
        }
        System.out.println("false");

    }
}
