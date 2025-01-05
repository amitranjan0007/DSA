package leetcode.hashing;

public class SumOfAllPossiableValueAmazon {
    public static void main(String[] args) {
        //Question: https://docs.google.com/document/d/1taIf64o4VlUOsPXqt8wG--OpsOSo-xyWE0MupwLf4Ck/edit?tab=t.0
        int[] arr={0,3,14,15};
        SumOfAllPair(arr);
    }

    private static int numberOfDigit(int n){
        return (int)Math.log10(n)+1;
    }
    private static int numberOfBit(int n){
        return (int)(Math.log(n)/Math.log(2))+1;
    }

    private static void SumOfAllPair(int[] arr){
        /*
        * Observation: fix the jth idx
        1       2      3     4    5...........n-1
          ........................j............n-1
         1      20     50   800   50
         *10^2 *10^2 *10^2 *10^2
         +50   +50   +50   +50


        (1*10^2+20*10^2+50*10^2+800*10^2)+50*4
        10^2(1+20+50+800)+50*10^4

        Above one can be represnted as below formula
        (10^numberOfDigit)*(sum of all the element till j-1 idx)+ arr[j]*(j-1)

        * sum of all the element till j-1 idx---> oho means current lement will add at last see the below code  sum+=arr[j];

        * */
        int n=arr.length;
        long sum=0;
        long totalSum=0;
        for(int j=1;j<n;j++){
            int y=numberOfDigit(arr[j]);
            long v1= (long) (j - 1) *arr[j]+(long)Math.pow(10,y)*sum;
            totalSum+=v1;
            sum+=arr[j];
        }
        System.out.println(totalSum);
    }
}
