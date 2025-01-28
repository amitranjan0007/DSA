package leetcode.twopointer;

import java.util.Arrays;

public class StringBInStringAAfterRemovingOneNumberFromB {
    public static void main(String[] args) {
        int[] A = {1,2,3,10,15,4,8,5};
        int n = A.length;
        int[] B = {1,3,8,4,5};
        int m = B.length;
//        int[] A = {1,2,5,8,8,6,5,4,3};
//        int n = A.length;
//        int[] B = {5,5};
//        int m = B.length;
        check(A, n, B, m);
    }
    static boolean check(int[] A, int n, int[] B, int m) {
        int[] prefB = new int[m];
        Arrays.fill(prefB, -1);
        int[] sufB = new int[m];
        Arrays.fill(sufB, -1);

        int i = 0, j = 0, count = 0;

        // First pass to populate prefB
        while (i < m && j < n) {
            if (A[j] == B[i]) {
                prefB[i] = j;
                i++;
                j++;
                count++;
            } else {
                j++;
            }
        }

        i = m - 1;
        j = n - 1;

        // Second pass to populate sufB
        while (i >= 0 && j >= 0) {
            if (A[j] == B[i]) {
                sufB[i] = j;
                i--;
                j--;
            } else {
                j--;
            }
        }

        int good = 0;

        // Third pass to count 'good' elements
        for (i = 0; i < m-1; i++) {
            int l = (i > 0) ? prefB[i - 1] : -1;  // Handling edge case
            int r = (i < m - 1) ? sufB[i + 1] : n; // Handling edge case
            if (l < r) {
                good++;
            }
        }

        if(prefB[m-1]!=-1) good++;

        System.out.println(good);

        return true; // You can change this return value as per your requirement
    }
}
