package leetcode.twopointer;

public class MoveZeroToRightAndFollowUp {
    public static void main(String[] args) {
        moveZerosToEnd();
        moveZeroToFirst();


        //follow up question
        moveEvenToRightAndOddToLeft();
    }

    static void moveZerosToEnd() {
        int arr[]={2,3,4,0,9,0,0,2,0};
        int j=-1;
        int n=arr.length;
        for(int i=0;i<n;i++){
            if(arr[i]==0){
                j=i;
                break;
            }
        }
        if(j==-1) return;
        for(int i=j+1;i<n;i++){
            if(arr[i]!=0){
               swap(arr,i,j);
                j++;
            }
        }

        for(int num:arr) System.out.print(num+" ");
        System.out.println();
    }

    static void moveZeroToFirst(){
        int arr[]={2,3,4,0,0,0,9,0,0,2,0,0};
        int j=-1;
        int n= arr.length;
        for (int i=n-1;i>=0;i--){
            if(arr[i]==0){
                j=i;
                break;
            }
        }
        if(j==-1) return;

        for(int i=j-1;i>=0;i--){
            if(arr[i]!=0){
                swap(arr,i,j);
                j--;
            }
        }
        for (int num:arr) System.out.print(num+" ");
        System.out.println();
    }
    static void moveEvenToRightAndOddToLeft() {
        int arr[]={1,2,3,4,5,6,7,8,12};
        int j=-1;
        int n=arr.length;

       for(int i=0;i<arr.length;i++){
           if(arr[i]%2==0){
               j=i;
               break;
           }
       }
       if(j==-1) return;
       for(int i=j+1;i<n;i++){
            if(arr[i]%2!=0){
                swap(arr,i,j);
                j++;
            }
       }

       for(int num:arr){
           System.out.print(num+" ");
       }
    }


    private static void swap(int[] arr, int i, int j) {
        int tmp=arr[j];
        arr[j]=arr[i];
        arr[i]=tmp;
    }
}
