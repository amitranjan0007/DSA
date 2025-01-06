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
        Observation: Divide the arr in 3 e
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
