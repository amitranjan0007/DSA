package leetcode.binarysearch;

public class FindOneElementLessThenKtimesOccured {
    /*
    Question:8
    https://drive.google.com/file/d/1YL3Km7QhpAoLBdaIDxSqW05WsqRak7yF/view

    * */
    public static void main(String[] args) {
         int k=3;
         int[]b={2,2,2,4,4,4,6,6,6,7,7,8,8,8,9,9,9};
        findElement(b,k);
    }
    private static void findElement(int[]b,int k){
        int l=0;
        int h=b.length-1;
        while (l<=h){
            int mid=(l+h)>>1;
            int l1=lowerBound(b,b[mid]);
            int h1=upperBound(b,b[mid]);
            if(h1-l1+1<k){
                System.out.println(b[mid]);
                return ;
            }else{
                if((h1-l+1)%k==0){
                   l=h1+1;
                }else{
                    h=l1-1;
                }
            }
        }
        System.out.println(-1);
    }

    private static int upperBound(int[] b, int target) {
        int l=0;
        int h=b.length-1;
        int ans=0;
        while (l<=h){
            int mid=(l+h)>>1;
            if(b[mid]<=target){
                ans=mid;
                l=mid+1;
            }else{
                h=mid-1;
            }
        }
        return ans;
    }

    private static int lowerBound(int[] b, int target) {
        int l=0;
        int h=b.length-1;
        int ans=0;
        while (l<=h){
            int mid=(l+h)>>1;
            if(b[mid]<target){
                l=mid+1;
            }else{
                ans=mid;
                h=mid-1;
            }
        }
        return ans;
    }
}
