package leetcode.dynamicprogramming;

public class MinCostToReduceTo1 {
    /*
    Given an integer “N” ; find the minimum cost to reduce it to 1
    The operations you can do are as follows.->

    i) Reduce the number by 1 in “y” dollars;

    ii) Reduce the number by /7 in “x” dollars.(if number is divisible by 7)

    iii) Reduce the number by /3 in “z” dollars;(if number is divisible by 3)

    iv) Reduce the number by /5 in “b” dollars;(if number is divisible by 5)
    Y = 100
    X = 1
    Z = 50
    B = 85
    N=15

    15---->/3->5 ->>>/5-->1
    50+85=135

    Ans is 135

    (1<=n<=100000)
    dp[i]= best minimum cost to reduce it to 1, for target i
    dp[1]= 0  what is is the minimum cost to reduce to 1 from for target 1 i.e 0, no operation needed
    dp[2]= what is is the minimum cost to reduce to 1 from for target 2
    **/
    public static void main(String[] args) {
        int Y = 100;
        int X = 1;
        int Z = 50;
        int B = 85;
        int res=helper(15,Y,X,Z,B);
        System.out.println(res);
    }

    private static int helper(int n,int Y,int X,int Z,int B){
        int dp[]=new int[n+1];
        dp[1]=0;//target 1 is calculated
        for(int i=2;i<=n;i++){//start from target 2 and go upto desired target
             int numReduceBy1= Y+dp[i-1];

             int numReduceBy3= Integer.MAX_VALUE;
             if((i)%3==0){
                 numReduceBy3=Z+dp[i/3];
             }
            int numReduceBy5= Integer.MAX_VALUE;
            if(i%5==0){
                numReduceBy5=B+dp[i/5];
            }
            int numReduceBy7= Integer.MAX_VALUE;
            if(i % 7 == 0){
                numReduceBy7=X+dp[i/7];
            }
            dp[i]=Math.min(Math.min(Math.min(numReduceBy1,numReduceBy3),numReduceBy5),numReduceBy7);;
        }
        return dp[n];//ans lie on last target
    }
}
