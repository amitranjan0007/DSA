package leetcode.dynamicprogramming.partitiondp;

import java.util.HashMap;
import java.util.Scanner;

public class DivideTheArrayInPartitionEachPartIsGood_UberOA {
    /*
    Question: 50
    Question Link: https://www.desiqna.in/17594/uber-oa-sde-intern-fulltime-ctc-60l
    Doc Link: https://docs.google.com/document/d/1klrAqCSjHzMCGWLzmsXNHxeIc_b7OUXGOAwuYYz9tH0/edit?tab=t.0

    Easy Version: Just tell true or false; tell true if it is possible to divide the array in a
     partition such that each part is good.
     [i…………….j] is good if and only if := b[i] = abs(i-j)

     Uber Version:
      n= 8
     3 1 2 3 4 5 6 7

     delete 3 & 7 = 2 operation needed to make as good

     9
    3 5 6 7 8 9 10 2 3 7 9
    delete 3 & 9= 2 operation

    10
    1 2 2 9 10 2 10 11 1 10
    Output shoulbe 0 - no operation needed ,it's already good

    * */
    public static void main(String[] args) {
        // DivideTheArrayInPartitionEachPartIsGoodEasyVersion();//normal
        //DivideTheArrayInPartitionEachPartIsGoodEasyVersionOpt();//used hashmap
        // DivideTheArrayInPartitionEachPartIsGoodEasyVersionMoreOpt();//backword travesal
        DivideTheArrayInPartitionEachPartIsGoodSubOptimal_Uber();
       // DivideTheArrayInPartitionEachPartIsGoodOptimal_Uber();
    }

    private static void DivideTheArrayInPartitionEachPartIsGoodOptimal_Uber() {
          //backward Traversal: i+b[i]=j
          //if you do front traversal => j+b[j]=i
        //we following fronnt traversal
        Scanner scanner = new Scanner(System.in);
            int n = scanner.nextInt();

            int[] b = new int[n + 1];
            for (int i = 1; i <= n; ++i) {
                b[i] = scanner.nextInt();
            }

            int[] dp = new int[n + 2];
            int[] s = new int[n + 2];

            dp[n + 1] = 0;
            dp[n] = 1;
            s[n] = dp[n];

        for(int i=n-1;i>=1;i--){
            int u=1+dp[i+1];
            int v=Integer.MAX_VALUE;
               if(i+b[i]+1<=n+1){
                   v=s[i+b[i]+1];
               }
               dp[i]=Math.min(u,v);
               s[i]=Math.min(dp[i],1+s[i+1]);
        }


            System.out.println(dp[1]);

}
    private static void DivideTheArrayInPartitionEachPartIsGoodSubOptimal_Uber(){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        int[] b = new int[n + 1];
        for (int i = 1; i <= n; ++i) {
            b[i] = scanner.nextInt();
        }

        int[] dp = new int[n + 2];


        /*
           1 2 3 4
           2 7 7 1
           As you can see there is no elements to right at idx=4
           dp[4]=1
           dp[n]=1
        * */
        dp[n + 1] = 0;//true//default we do ,right to calculate the number of ways
        dp[n] = 1;//false
        for (int i = n - 1; i >= 1; i--) {
            int u = 1 + dp[i + 1];

            int l = b[i];
            int v = Integer.MAX_VALUE;
            int y = 0;

            for (int g = i + l + 1; g <= n + 1; g++) {
                v = Math.min(v, y + dp[g]);
                y++;
            }

            dp[i] = Math.min(u, v);
        }

        System.out.println("The result is: " + dp[1]);

    }
    private static void DivideTheArrayInPartitionEachPartIsGoodEasyVersionMoreOpt(){
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();
        boolean[] dp =new boolean[n+2];
        dp[n+1]=true;
        int[] b=new int[n+1];
        for(int i=1;i<=n;i++){
            b[i]=scanner.nextInt();
        }
        for(int i=n;i>=1;i--){
            if(i+b[i]+1<=n+1 && dp[i+b[i]+1]){
                dp[i]=true;
            }

        }
        System.out.println(dp[1]);
    }

    private static void DivideTheArrayInPartitionEachPartIsGoodEasyVersionOpt(){
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();
        boolean[] dp =new boolean[n+1];
        int[] b=new int[n+1];
        for(int i=1;i<=n;i++){
            b[i]=scanner.nextInt();
        }
        dp[0]=true;
        HashMap<Integer,Boolean> map=new HashMap<>();
        map.put(b[1]+1,true);
        for(int i=2;i<=n;i++){
            if(map.containsKey(i)){
                if(map.get(i)){
                    dp[i]=true;
                }else{
                    dp[i]=false;
                }
            }else{
                dp[i]=false;
            }
            if(dp[i-1]){
                map.put(b[i]+i,dp[i-1]);
            }

        }
        System.out.println(dp[n]);
    }
    private static void DivideTheArrayInPartitionEachPartIsGoodEasyVersion(){
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();
        boolean[] dp =new boolean[n+1];
        int[] b=new int[n+1];
           for(int i=1;i<=n;i++){
               b[i]=scanner.nextInt();
           }
       dp[0]=true;
        for(int i=1;i<=n;i++){
            for(int j=i;j>=1;j--){
                if (Math.abs(j - i) == b[j] && dp[j-1]) {//....j.....i...
                    System.out.println(j+" "+" "+i);
                    dp[i] = true;
                }
            }
        }
        System.out.println(dp[n]);
    }
}