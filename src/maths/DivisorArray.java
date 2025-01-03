package maths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import java.util.*;

public class DivisorArray {

    // Constants
    private static final int MAXN = 1000000;
    private static final int MOD = 1000000007;

    // Array to store smallest prime factor for each number
    private static long[] spf = new long[MAXN + 1];

    // Function to compute the Smallest Prime Factor (SPF) for every number
    private static void computeSPF() {
        // Initialize spf for every number to itself
        for (int i = 2; i <= MAXN; i++) {
            spf[i] = i;
        }

        // Start the sieve process
        for (int i = 2; i * i <= MAXN; i++) {
            if (spf[i] == i) { // i is prime
                for (int j = i * i; j <= MAXN; j += i) {
                    if (spf[j] == j) { // Update spf[j] to the smallest prime factor
                        spf[j] = i;
                    }
                }
            }
        }
    }

    // Function to get the prime factorization of a number
    private static Map<Long, Long> primeFactorization(long val) {
        Map<Long, Long> factors = new HashMap<>();
        while (val != 1) {
            long divisor = spf[(int) val];
            factors.put(divisor, factors.getOrDefault(divisor, 0L) + 1);
            val /= divisor;
        }
        return factors;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read input
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        scanner.nextLine();

        // Initialize the map to store the prime factor counts for M!
        Map<Long, Long> primeFactorCounts = new HashMap<>();

        // Compute SPF for all numbers from 2 to MAXN
        computeSPF();

        // Calculate the prime factorization of all numbers from 2 to m and accumulate the factors
        for (int i = 2; i <= m; i++) {
            Map<Long, Long> factors = primeFactorization(i);
            for (Map.Entry<Long, Long> entry : factors.entrySet()) {
                primeFactorCounts.put(entry.getKey(),
                        primeFactorCounts.getOrDefault(entry.getKey(), 0L) + entry.getValue());
            }
        }

        // Process each element in the array A (read from input)
        for (int i = 0; i < n; i++) {
            long num = scanner.nextLong();

            // Copy the prime factors from M!
            Map<Long, Long> factorCountCopy = new HashMap<>(primeFactorCounts);

            // Factorize the current element A[i] and merge with M!'s factor counts
            Map<Long, Long> numFactors = primeFactorization(num);
            for (Map.Entry<Long, Long> entry : numFactors.entrySet()) {
                factorCountCopy.put(entry.getKey(),
                        factorCountCopy.getOrDefault(entry.getKey(), 0L) + entry.getValue());
            }

            // Calculate the number of divisors using the formula
            long result = 1;
            for (long power : factorCountCopy.values()) {
                result = (result * (power + 1)) % MOD;
            }

            // Output the result for this A[i]
            System.out.print(result + " ");
        }

        scanner.close();
    }
}
