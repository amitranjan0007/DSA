package leetcode.hashing;

import java.util.*;
/*
https://docs.google.com/document/d/1k0mOvFj-PT6LvbUjTrw9O-u7lHAMrAUMu2vE7VLp5F0/edit?tab=t.0
https://drive.google.com/file/d/1cf6N0YrRrP4vvtt8efeLOCrIQtCJubY6/view
Very Important : How to update the map ,
* */
public class DetermineMinStepToMakeAllPilesEquals {
    public static void main(String[] args) {
        Map<Integer, Integer> m = new TreeMap<>();
        int[] b = {5,5,5,4,4,2};

        for (int num : b) {
            m.put(num, m.getOrDefault(num, 0) + 1);
        }

        // Sort the values
        List<Integer> mValue = new ArrayList<>(m.values());

        int curr = 0;
        int prev = 0;
        int total = 0;

        for (int i = mValue.size() - 1; i > 0; i--) {
            curr = prev + mValue.get(i);
            prev = curr;
            total += curr;
        }

        System.out.println(total);
    }
}
