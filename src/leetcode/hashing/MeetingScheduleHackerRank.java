package leetcode.hashing;

public class MeetingScheduleHackerRank {
//https://www.hackerrank.com/contests/amazon/challenges/meeting-schedules/problem
    public static void main(String[] args) {
        // Meeting start and end times in HH:MM format
        String[][] arr = {
                {"16:00", "17:00"},
                {"10:30", "14:30"},
                {"20:45", "22:15"},
                {"10:00", "13:15"},
                {"09:00", "11:00"}
        };
        int[] pSum = new int[1441]; // Time array for 1440 minutes in a day
        int k = 120; // Desired duration of free time slot (in minutes)

        // Populate the pSum array with meeting start and end times
        for (int i = 0; i < arr.length; i++) {
            int start = convert(arr[i][0]);
            int end = convert(arr[i][1]);
            pSum[start]++; // A meeting starts at 'start'
            pSum[end]--;   // A meeting ends at 'end'
        }

        // Calculate the prefix sum to track the number of ongoing meetings at each minute
        for (int i = 1; i < pSum.length; i++) {
            pSum[i] += pSum[i - 1];
        }

        // Find and print available time slots
        int start = 0;
        boolean inFreeSlot = false; // Track if we are currently in a free slot
        for (int i = 0; i < pSum.length; i++) {
            if (pSum[i] == 0 && !inFreeSlot) { // Start of a potential free slot
                start = i;
                inFreeSlot = true;
            } else if (pSum[i] != 0 && inFreeSlot) { // End of a free slot
                if (i - start >= k) {
                    String startTime = convertMinutesToHHMM(start);
                    String endTime = convertMinutesToHHMM(i );
                    System.out.println(startTime + "--" + endTime);
                }
                inFreeSlot = false;
            }
        }

        // Check for a free slot that extends to the end of the day
        if (inFreeSlot && pSum[pSum.length - 1] == 0 && pSum.length - 1 - start >= k) {
            String startTime = convertMinutesToHHMM(start);
            String endTime = convertMinutesToHHMM(pSum.length+1);
            System.out.println(startTime + "--" + endTime);
        }
    }

    // Convert total minutes to HH:MM format
    public static String convertMinutesToHHMM(int minutes) {
        int hours = minutes / 60;
        int remainingMinutes = minutes % 60;
        return String.format("%02d:%02d", hours, remainingMinutes);
    }

    // Convert HH:MM time to total minutes since midnight
    static int convert(String str) {
        String[] tmp = str.split(":");
        return Integer.parseInt(tmp[0]) * 60 + Integer.parseInt(tmp[1]);
    }
}