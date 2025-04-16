package leetcode.binarysearch;

public class FirstOccuranceOfOnes {
    public static void main(String[] args) {
        int[]b={0,0,0,1,1,1,1,1,1,1,1,1,1,1,1};
        firstOccurance(b);
    }
    private static void firstOccurance(int[]b){
        int l=0;
        int h=b.length-1;
        while(l<=h){
            int mid=(l+h)>>1;
            if(b[mid]==1){
                h=mid-1;
            }else{
                l=mid+1;
            }
        }
        System.out.println("firstOccurance is "+l);
    }
}
