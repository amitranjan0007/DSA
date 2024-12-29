package leetcode.dynamicprogramming;

import java.util.Scanner;

public class MakeNumberLargeBySomeOperation {
    /*
     Question : https://www.desiqna.in/15782/atlassian-oa-sde-1-freshers-hiring-ctc-75-lac

    Greedy :-> Only perform + and * -> they guarantee to give the best answer in most cases.

    ->”Too much negative energy can be converted to big positive energy in an instant by “N” operation.”



    dp[..........]

    dp_max[i] = maximum energy you are getting by doing some of the first i operations.

    dp_min[i] = minimum energy you are getting by doing some of the first i operations.


    **/
    private static long max(long a,long b,long c){
        return Math.max(Math.max(a,b),c);
    }
    private static long min(long a,long b,long c){
        return Math.min(Math.min(a,b),c);
    }
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int t= scanner.nextInt();
        scanner.nextLine();
        while(t-->0){
            long qCount=scanner.nextLong();
            scanner.nextLine();
            long[]dpMax=new long[(int)(qCount+1)];
            long[]dpMin=new long[(int)(qCount+1)];
            dpMax[0]=1;
            dpMin[0]=1;
               for(long i=1;i<=qCount;i++){
                   String line= scanner.nextLine();
                   char symbol=line.charAt(0);//can be N or symbol
                   long num=0L;
                     if(line.length()>2){
                         num=Long.parseLong(line.substring(2));
                     }
                  /*
                     arr=   1.......i........n
                     dpMax= 1.......i........n
                     dpMin= 1.......i........n
                     dp[i]= include ith idx num+dpMax[i-1],num+dpMin[i-1]
                            Not include include ith idx dpMax[i-1]
                  * **/
                  if(symbol=='+'){
                      dpMax[(int)i]=max(num+dpMax[(int)(i-1)],dpMax[(int)(i-1)],num+dpMin[(int)(i-1)]);
                      dpMin[(int)i]=min(num+dpMin[(int)(i-1)],dpMin[(int)(i-1)],num+dpMax[(int)(i-1)]);
                  }
                   if(symbol=='-'){
                       dpMax[(int)i]=max(num-dpMax[(int)(i-1)],dpMax[(int)(i-1)],num-dpMin[(int)(i-1)]);
                       dpMin[(int)i]=min(num-dpMin[(int)(i-1)],dpMin[(int)(i-1)],num-dpMax[(int)(i-1)]);
                   }
                   if(symbol=='/'){
                       dpMax[(int)i]=max(dpMax[(int)(i-1)]/num,dpMax[(int)(i-1)],dpMin[(int)(i-1)]/num);
                       dpMin[(int)i]=min(dpMin[(int)(i-1)]/num,dpMin[(int)(i-1)],dpMax[(int)(i-1)]/num);
                   }
                   if(symbol=='*'){
                       dpMax[(int)i]=max(num*dpMax[(int)(i-1)],dpMax[(int)(i-1)],num*dpMin[(int)(i-1)]);
                       dpMin[(int)i]=min(num*dpMin[(int)(i-1)],dpMin[(int)(i-1)],num*dpMax[(int)(i-1)]);
                   }
                   if(symbol=='N'){
                       dpMax[(int)i]=max(-1*dpMax[(int)(i-1)],dpMax[(int)(i-1)],-1*dpMin[(int)(i-1)]);
                       dpMin[(int)i]=min(-1*num+dpMin[(int)(i-1)],dpMin[(int)(i-1)],-1*dpMax[(int)(i-1)]);
                   }
               }
            System.out.println(dpMax[(int)qCount]);
        }
    }
}
//2
//        3
//        N
//        - 2
//        N