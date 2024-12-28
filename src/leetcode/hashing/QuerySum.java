package leetcode.hashing;
public class QuerySum {
    public static void main(String[] args) {
        basicQuerySum();

    }
    private static void basicQuerySum(){
        int n=10;
        int[] arr=new int[n+1];
        int[][]queries={{4,6},{5,9}};
        for(int[] query:queries){
            rangeSum(arr,query[0],query[1]);
        }
        /*
           0 1 2 3 4 5 6 7 8 9 10
           0 0 0 0 1 1   -1    -1
           0 0 0 0 1 2 2 1 1 1
        * **/
        for(int i=1;i<arr.length-1;i++){
            arr[i]+=arr[i-1];
        }
        for(int i=1;i<arr.length-1;i++){
            System.out.print(arr[i]+" ");
        }
    }

    private static int[] rangeSum(int[] arr,int L,int R){
        arr[L]=1;
        arr[R+1]=-1;
        return arr;
    }

    private static void maximizeEqualSum(int[] arr,int k){

    }
    private static void maximizeEqualSumHelper(int[] arr,int k){
          //-k..x..k
        int[] pSum=new int[arr.length+1];
        for(int i=0;i<arr.length;i++){
            int num=arr[i]; //now this num can be transformed from num-k<=y<= num+k
            int l=num-k;
            int r=num+k;

        }
    }
}
