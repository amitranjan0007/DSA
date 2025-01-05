package leetcode.hashing;

import java.util.HashMap;
import java.util.Scanner;

public class XometryFourXor {
    /*
      Problem link: https://www.codechef.com/problems/XSQR
      Constraints: A[i] goes upto 10^6
      Find number of bit= log2(10^6-1) =log2(999999)= 19.93 = 20
      so 20 bit can be represented as [0,2^20-1]
      2^20=(2^10)^2=(1024)^2= 1024*1024=1048576-1=1048575
      so we intialise map greater then or equal to this 1048575

      Observation: Find the unordered quadruplets (i,j,k,l) such that ->

                    -> b[i] xor b[j] = b[k] xor b[l]

                    -> b[i] xor b[l] = b[j] xor b[k]


                    b[i]^b[j]^b[k]^b[l] = 0

            After you found the valid quadrlet of size 4 then you multiply it by 24
            why [a,bc,c,d] is valid then it's permutation is still valid :
            4 length permutation is 4*3*2*1=24

    * */
    static int[] map=new int[2000000];
    public static void main(String[] args) {
        findQuadruplet();
    }

    private static void findQuadruplet(){
        Scanner scanner=new Scanner(System.in);
        int t=scanner.nextInt();
        while(t-->0){
            int n=scanner.nextInt();
            int[] arr=new int[n];
             for(int i=0;i<arr.length;i++){
                 arr[i]=scanner.nextInt();
             }
           // findQuadrupletHelper(arr);
            findQuadrupletHelperMoreOptimal(arr);
        }
    }

    private static void findQuadrupletHelper(int[] arr) {
        int n = arr.length;
        long count = 0;
        HashMap<Integer, Integer> map = new HashMap<>();

        // Populate the hashmap for pairs (k, l) with k < l
        for (int k = 2; k < n - 1; k++) {
            for (int l = k + 1; l < n; l++) {
                int numXor = arr[k] ^ arr[l];
                map.put(numXor, map.getOrDefault(numXor, 0) + 1);
            }
        }


        // Iterate over pairs (i, j) with i < j
        for (int j = 1; j < n - 1; j++) {
            for (int i = 0; i < j; i++) {
                int numXor = arr[i] ^ arr[j];
                count += map.getOrDefault(numXor, 0);
            }

            // Update the hashmap for pairs (j+1, l)
            for (int l = j + 1; l < n; l++) {
                int numXor = arr[j + 1] ^ arr[l];
                map.put(numXor, map.getOrDefault(numXor, 0) - 1);
            }
        }

        System.out.println(count*24);
    }

    private static void findQuadrupletHelperMoreOptimal(int[] arr) {
        int n = arr.length;
        long count = 0;


        // Populate the hashmap for pairs (k, l) with k < l
        for (int k = 2; k < n - 1; k++) {
            for (int l = k + 1; l < n; l++) {
                int numXor = arr[k] ^ arr[l];
                map[numXor]++;
            }
        }


        // Iterate over pairs (i, j) with i < j
        for (int j = 1; j < n - 1; j++) {
            for (int i = 0; i < j; i++) {
                int numXor = arr[i] ^ arr[j];
                count += map[numXor];
            }

            // Update the hashmap for pairs (j+1, l)
            for (int l = j + 1; l < n; l++) {
                int numXor = arr[j + 1] ^ arr[l];
                map[numXor]--;
            }
        }

        System.out.println(count*24);
    }
}
