package leetcode.dynamicprogramming.partitiondp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class PrimeString_Google {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.next();

        // Set the range for primes
        int l = 1;
        int r = 1000000 + 3;
        r++;

        // Generate all primes in the range [l, r]
        List<Integer> primes = sieveRange(l, r);
        long[] a = new long[1000070];
        for (Integer prime : primes) {
            a[Math.toIntExact(prime)] = 1;
        }

        int n = s.length();
        long[] dp = new long[n + 1];
        dp[0] = 1; // Base case: empty string is a valid partition

        // Iterate over each character in the string
        for (int i = 1; i <= n; i++) {
            // Check all substrings that end at position i and have length up to 6
            for (int j = i; j >= Math.max(i - 6, 1); j--) {
                String str = s.substring(j-1, i);  // Substring s[j..i]
                if (str.charAt(0) != '0') {  // Ignore numbers with leading zero
                    int num = Integer.parseInt(str);
                    if (a[num] == 1) {  // Check if the number is prime
                        dp[i] += dp[j-1]; // Accumulate valid partitions
                    }
                }
            }
        }

        System.out.println(dp[n]);  // Output the result

    }

    public static List<Integer> sieveRange(int l, int r) {
        boolean[] isPrime = new boolean[r + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;

        for (int i = 2; i * i <= r; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= r; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        List<Integer> primes = new ArrayList<>();
        for (int i = l; i <= r; i++) {
            if (isPrime[i]) {
                primes.add(i);
            }
        }
        return primes;
    }
}
