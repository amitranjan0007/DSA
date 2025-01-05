package leetcode.hashing;

public class MaxValueOfTripletLeetCodeContest {
    public static void main(String[] args) {
        int[] nums = {1,10,3,4,19};
        maximumTripletValueBruteForce(nums);
        maximumTripletValueOptimal(nums);
    }
    public static long maximumTripletValueBruteForce(int[] nums) {
        int n=nums.length;
        long maxi=0;
        for(int i=0;i<n-2;i++){
            for(int j=i+1;j<n-1;j++){
                for(int k=j+1;k<n;k++){
                    long sum=(nums[i] - nums[j]) * (long)nums[k];
                    maxi=Math.max(maxi,sum);
                }
            }
        }
        System.out.println(maxi);
        return maxi;
    }
    public static void maximumTripletValueOptimal(int[] nums) {
         /*
           sum=(nums[i] - nums[j]) * nums[k];
           to sum to be maximum
           if we maximise this (nums[i] - nums[j]) then we can get max sum because any max * nums[k]= will always be max

           so can we write like this , we have to find  ( max (nums[i] - nums[j]) till 0...k-1)*nums[k]

           0..........k-1,k.............n
           ...max....... *nums[k]

          prefix[0]=0;
          prefix[1]=nums[0] - nums[1];
          prefix[2]=Math.max(prefix[1],nums[0]-nums[2],nums[1]-nums[2])
          prefix[3]=Math.max(prefix[2],nums[0]-nums[3],nums[1]-nums[3],nums[2]-nums[3]
          ...
          ...

          prefix[k]=Math.max(prefix[k-1],nums[k-1]-nums[k],nums[k-2]-nums[k],.....)

         see carefully nums[k-1]-nums[k],nums[k-2]-nums[k]

         nums[k] is constant above equation
         max(prefix[k-1,max(nums[k-1],nums[k-2],...0)-nums[k])

         max(prefix[k-1],prevMaxVal-nums[k])
         if we do max-min = max

        * */
        int n=nums.length;
        long[] prefix = new long[n];
        prefix[1] = nums[0] - nums[1];
        long maxi = 0;
        long val = Math.max(nums[0], nums[1]);

        for (int k = 2; k <= n - 1; k++) {
            prefix[k] = Math.max(prefix[k - 1], val - nums[k]);//calculating the prefix upto k

            long vl = prefix[k - 1] * (long)nums[k];//but we need (max till k-1)*nums[k]
            val = Math.max(val, nums[k]);
            maxi = Math.max(maxi, vl);
        }

        System.out.println(maxi);

    }
}
