package leetcode.hashing;

public class TripletCount {
    public static void main(String[] args) {
        countTriplet();
        countTripletFollowUp();
    }

    private static void countTriplet(){
        /*
        Given an array of size “N”; find the number of triplets; such that A[i] >A[j]<A[k] such that i < j ;

        * **/
        int[] arr={8,12,11,10,5,15,20,21};
        int[]prefixPair=new int[arr.length];
        int[]suffixPair=new int[arr.length];
        int n=arr.length;

        for(int j=0;j<arr.length;j++){
            int count=0;
            for(int i=j-1;i>=0;i--){
                if(arr[i]>arr[j]){
                    count++;
                }
            }
            prefixPair[j]=count;
        }

        for(int j=n-2;j>=0;j--){
            int count=0;
            for(int k=j+1;k<n;k++){
                if(arr[j]<arr[k]) count++;
            }
            suffixPair[j]=count;
        }
        int count=0;
        for(int i=0;i<n;i++){
            count+=(prefixPair[i]*suffixPair[i]);
        }
        System.out.println(count);
    }


    private static void countTripletFollowUp(){
        /*
        Given an array of size “N”; find the number of triplets; such that A[i]>A[j]<A[k]>A[l] such that i<j<k<l ;

        * **/
        int[] arr={1, 2, 1, 5, 1, 34, 35};
        int[]prefixPair=new int[arr.length];
        int[]suffixPair=new int[arr.length];
        int n=arr.length;

        for(int j=0;j<arr.length;j++){
            int count=0;
            for(int i=j-1;i>=0;i--){
                if(arr[i]>arr[j]){
                    count++;
                }
            }
            prefixPair[j]=count;
        }

        for(int k=n-2;k>=0;k--){
            int count=0;
            for(int l=k+1;l<n;l++){
                if(arr[k]>arr[l]) count++;
            }
            suffixPair[k]=count;
        }
        int count=0;
        for(int j=0;j<n-1;j++){
            for(int k=j+1;k<n;k++){
                if(arr[j]<arr[k])
                  count+=prefixPair[j]*suffixPair[k];
            }
        }
        System.out.println(count);
    }
}
