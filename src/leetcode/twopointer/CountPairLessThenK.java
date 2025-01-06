package leetcode.twopointer;

import java.util.Arrays;

public class CountPairLessThenK {
    //https://docs.google.com/document/d/1BAtrKGHmF-OxSRuHtrdtR_5iG24nIGi1Oa7mjEKaumY/edit?tab=t.0
    /*
     Observation: Count the Pair less then equal K
     sort the array
     ..........i
     0 1 2 3 4 5 6 7
    k = 5

    (0,7) not valid.
    (0,6) not valid.
    (0,5) valid. c=c+5


    Normally now we would check for i = 1

    You would be starting from (1,7)

    But here is the game……..

    Because (0,6) and (0,7) are not valid henceforth (1,6) and (1,7) are automatically invalid.

    Hence you should directly start the searching from (1,5)

    5 :- the place where you last left at (0,5)

    So try (1,5)

    Not valid

    Try (1,4)

    Yes valid c = c + (4-1)


    Try with (2,7) -> Nope.

    You will start with (2,4)

    This saves time. Because j is linearly going from N-1 to 0 without any interruption.

    (2,4) -> not valid.

    (2,3)-> valid.

    c = c + (3-2)

    Algorithm stop

    Because now you will make i = 3

    But j is also 3 so stop

    When i==j stop

    * */
    public static void main(String[] args) {
        int[] arr={0,1,2,3,4,5,6,7};
        int k=5;
        CountPairLessThenKBrute(arr,k);
        CountPairLessThenKOptimal(arr,k);
    }
    private static void CountPairLessThenKBrute(int[] arr,int k){
        /****/
        int n=arr.length;
        Arrays.sort(arr);
        int count=0;
        for(int i=0;i<n-1;i++){
            for(int j=n-1;j>=i+1;j--){
                int sum=arr[i]+arr[j];
                if(sum<=k){
                   count+=j-i;
                   break;
                }
            }
        }
        System.out.println(count);
    }
    private static void CountPairLessThenKOptimal(int[] arr,int k){
        /****/
        int n=arr.length;
        Arrays.sort(arr);
        int count=0;
        for(int i=0,j=n-1;i<n;i++){
            int sum=arr[i]+arr[j];
            while(sum>k && i!=j){
                j--;
                sum=arr[i]+arr[j];
            }
            if(i==j) break;
            count+=(j-i);
        }
        System.out.println(count);
    }
}
