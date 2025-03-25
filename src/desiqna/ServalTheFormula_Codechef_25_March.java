package desiqna;

import java.util.Scanner;
//https://codeforces.com/contest/2085/problem/C
public class ServalTheFormula_Codechef_25_March {
    private static Scanner scanner;
    public static void main(String[] args) {
        scanner=new Scanner(System.in);
        int t=scanner.nextInt();
        while(t-->0){
            int x=scanner.nextInt();
            int y=scanner.nextInt();
            findTheValueOfK(x,y);
        }
    }

    private static void findTheValueOfK(int x, int y) {
        long k=-1;
        long value=x&y;
        if(value==0){
            k=0;
        }else if(x==y){
            k=-1;
        }else{
            //x!=y
            if(x>y){
                long msbBitPosition=findMSBBit(x);
                long nextMsbBitPosition=(int)Math.pow(2,msbBitPosition+1);
                k=nextMsbBitPosition-x;
            }else{
                long msbBitPosition= findMSBBit(y);
                long nextMsbBitPosition=(int)Math.pow(2,msbBitPosition+1);
                k=nextMsbBitPosition-y;
            }
        }

        System.out.println(k);
    }

    private static long findMSBBit(int num) {
        if (num == 0) return 0;
        long pos=-1;
        while(num!=0){
            num=num>>1;
            pos++;
        }
        return pos;
    }
}
