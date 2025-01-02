package leetcode.hashing;

import java.util.Arrays;
import java.util.HashMap;

public class CountArrayDivideThreeEqualHalf {
    public static void main(String[] args) {
        countToDivideArrayEqualsHalf();
        countToDivideArrayEqualsOptimal();
        countToDivideArrayThreeEqualsHalf();
    }
    private static void countToDivideArrayEqualsHalf(){
        int arr[]={5,0,0,5,0};
        int n=arr.length;
        int[] lSum=new int[n];
        int[] rSum=new int[n];
        lSum[0]=arr[0];
        rSum[n-1]=arr[n-1];
        for(int i=1;i<n;i++){
            lSum[i]=arr[i]+lSum[i-1];
        }

        for(int i=n-2;i>=0;i--){
            rSum[i]=arr[i]+rSum[i+1];
        }

        int count=0;
        for(int i=1;i<n;i++){
            if(lSum[i-1]==rSum[i])count++;
        }
        System.out.println(count);
    }

    private static void countToDivideArrayEqualsOptimal(){
        /*
          x+y=total
          .....i.......
          can we know LHS sum of i ...yes :) Game over
          you know LHS sum & you know total , just check for RHS
        * */
        int arr[]={5,0,0,5,0};
        int total= Arrays.stream(arr).sum();
        int LSum=0;
        int RSum=0;
        int count=0;
        for(int i=0;i<arr.length;i++){
            LSum+=arr[i];
            RSum=total-LSum;
            if(LSum==RSum)count++;
        }

        System.out.println(count);
    }

    private static void countToDivideArrayThreeEqualsHalf(){
        /*
        arr={1,2,0,0,1,2,3}

        totalSum=9;
        9%3=0 yes we can divide into 3 equals half
        arr= 1  2  0  0  1  2  3
        LSum=1  3  3  3  4  6  9
        Rsum=8  6  6  6  5  3  0

        to divide in 3 equal half
           .........i.........
           <----2y--><---y---> =3y

           3y=total => y= total/3

           we know y ,so rhs is fixed , we worry aboyt lhs=2y => so how we have to find ,how many way we divide lhs in 2 equal half
           lhs=2y && rhs=y

           suppose : 1 1 0 0 1 1 ,how many way you can divide this in 2 equal half?
                     1 2 2 2 3 4  => 4/2=2 ===> how many 2's there => 3 , you can use map so 3
                     map={
                        1->1
                        2->3
                        3->1
                        4->1
                     }
                     what are they 1 1 | 0 0 1 1
                                   1 1 0 | 0 1 1
                                   1 1 0 0 | 1 1

                    finally you are trying to find : countOfPairDividedInTwoEqualHalf=map[current_sum/2]

        **/
        int arr[]={1,2,0,0,1,2,3};
        int n=arr.length;
        int totalSum=0;
        int count=0;
          for(int i=0;i<arr.length;i++){
              totalSum+=arr[i];
          }
          if(totalSum%3!=0){
              System.out.println(count);
              return;
          }
        int LSum=0;
        int RSum=0;
        int currSum=0;
        HashMap<Integer,Integer>map=new HashMap<>();
        for(int i=0;i<n;i++){
            currSum+=arr[i];
            LSum=LSum+arr[i];
            RSum=totalSum-LSum;
            int y=totalSum/3;
            if(2*y==LSum && RSum==y){
                count+=(currSum%2)==0?map.getOrDefault((currSum/2),0):0;//how many such pair can contribute to rSum
            }
            map.put(currSum,map.getOrDefault(currSum,0)+1);
        }
        System.out.println(count);
    }
}
