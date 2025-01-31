package leetcode.twopointer;

public class MoveAllZeroesToEnd {
    public static void main(String[] args) {
        int[]b={3,4,0,0,1,1,2,1};
        MoveAllZeroesToEndMOptimal(b);
        MoveAllZeroesToStartMOptimal(b);
    }


    private static void MoveAllZeroesToEndMOptimal(int[]b){
        int i=0;
        int j=0;
        int n=b.length;
        while(i<n && j<n){
            if(b[j]!=0){
                swap(i,j,b);
                i++;
                j++;
            }else {
                j++;
            }
        }

        while(i<n){
            b[i]=0;
            i++;
        }
        for(int num:b){
            System.out.print(num+" ");
        }
        System.out.println();
    }
    private static void MoveAllZeroesToStartMOptimal(int[]b){
        int n=b.length;
        int i=n-1;
        int j=n-1;
        while(i>=0 && j>=0){
            if(b[j]!=0){
                swap(i,j,b);
                i--;
                j--;
            }else {
                j--;
            }
        }
        while(i>=0){
            b[i]=0;
            i--;
        }
        for(int num:b){
            System.out.print(num+" ");
        }

    }
    private static void swap(int i,int j,int[]b){
        int tmp=b[i];
        b[i]=b[j];
        b[j]=tmp;
    }
}
