package desiqna;

import java.util.Arrays;
import java.util.Scanner;

public class MaxNumberOfCluster {
    public static void main(String[] args) {
        //maxNumberCluster();
        maxNumberClusterSelectingFive();
    }
    private static void maxNumberCluster(){
        /*
        Observation:
        Sort the array because max will go on right
          - in group of 3 ,always remember you need second largest element,not worried about that 1st largest element
            Just for observation [5 17 19] [20 29 40] ===> as you see we not worried about 19 & 20 we concerned about 17 & 29

            eg:
             5 17 19 20 29 40

             size of array(n)=6
             group we can make 6/3=2 group

             How you get max median in 3 number of group
             so we select 29 ==> [5,29,40]  ====> max median is 29
              Again we select 19 ==> [17,19,20] ====> max median is 19
              Add 29+119=48 ans
        * */
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();
        int arr[]=new int[n+1];

        for(int i=1;i<=n;i++){
            arr[i]=scanner.nextInt();
        }

        Arrays.sort(arr);
        int groupSize=n/3;
        int count=0;
        int sum=0;
        for(int j=n-1;j>=1;j=j-2){
            sum+=arr[j];
            count++;
            if(count==groupSize){
                break;
            }
        }
        System.out.println(sum);

    }

    private static void maxNumberClusterSelectingFive(){
       //Follow up question of above ,same concept ,select keep select 3 element from back and 2 from front
        //we don't care about front selection,because while iterating we count==groupsize or not
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();
        int arr[]=new int[n+1];

        for(int i=1;i<=n;i++){
            arr[i]=scanner.nextInt();
        }

        Arrays.sort(arr);
        int groupSize=n/5;
        int count=0;
        int sum=0;
        for(int j=n-2;j>=1;j=j-3){
            sum+=arr[j];
            count++;
            if(count==groupSize){
                break;
            }
        }
        System.out.println(sum);

    }
}
