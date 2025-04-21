package desiqna.codeforce_1018;

import java.util.Arrays;
import java.util.Scanner;

public class Wonderful_Stick {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int t=scanner.nextInt();
        while(t-->0){
            int n=scanner.nextInt();
            int[]b=new int[n];
            String str= scanner.next();
            solve(b,str);
        }
    }

    private static void solve(int[] b, String str) {
         int n=str.length();
         int min=0;
         int max=0;
         b[0]=0;
         int i=0;

         while(i<n){
            if(str.charAt(i)=='>'){
                max++;
                b[i+1]=max;
            }else{
                min--;
                b[i+1]=min;
            }

            i++;
         }

         if(min<0){
             min=min*-1;
         }
         min=min+1;

         i=0;
         while(i<=n){
             b[i]=b[i]+min;
             i++;
         }
         Arrays.stream(b).forEach(num-> System.out.print(num+" "));
        System.out.println();

    }
}
