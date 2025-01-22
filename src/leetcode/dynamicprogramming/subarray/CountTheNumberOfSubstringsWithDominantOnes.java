package leetcode.dynamicprogramming.subarray;

public class CountTheNumberOfSubstringsWithDominantOnes {
    //Problem Link : https://leetcode.com/problems/count-the-number-of-substrings-with-dominant-ones/description/
    public static void main(String[] args) {
        String s="101101";
        numberOfSubstringsOptimal(s);
        numberOfSubstringsSubOptimal(s);
        numberOfSubstringsBruteForce(s);

    }
    public static int numberOfSubstringsOptimal(String s) {
        int n = s.length();
        int[] prefix = new int[n];
        int countOnes=0;

        //This will count number of ones
        for(int i=0;i<n;i++){
            if(s.charAt(i)=='1'){
                countOnes++;
            }
            prefix[i]=countOnes;
        }
        int ans=0;
        //we know how to calculate the prefix sum between i...j
        for(int i=0;i<n;i++){
            for(int j=i;j<n;j++){
                int ones=prefix[j];
                if(i!=0) ones-=prefix[i-1];
                int zeroes=(j-i+1)-ones;// length - number of ones

                /*
                    Case 1: either condition not satisfy
                    Case 2: either condition  satisfy
                * */

                if(zeroes*zeroes>ones){
                    /*
                        i     j
                        1 0 0 0 1 1 1
                    * */
                    j+=((zeroes*zeroes)-ones-1);
                }

                if(zeroes*zeroes<=ones){
                    //means number of zeroes are less or equal to number of ones
                    /*   i     j
                         0 1 2 3  4 5
                         1 1 1 1  0 0 ==>n =6

                         when j cross n ==> 6-3-1=2
                         let's say j not cross n then it will be in bound so valid substring add number of valid jump

                    * */
                    ans++;
                    int oneSqrt= (int)Math.sqrt(ones);
                    if(j+oneSqrt-zeroes>=n){
                        ans+=n-j-1;
                    }else{
                        ans+=oneSqrt-zeroes;
                    }
                    j+=oneSqrt-zeroes;
                }
            }
        }
        System.out.println(ans);
        return  ans;
    }
    public static int numberOfSubstringsSubOptimal(String s) {
        //tc:O(n^2)
        int n=s.length();
        int count=0;
        for(int i=0;i<n;i++){
            int[] freq=new int[2];
            for(int j=i;j<n;j++){
                if(s.charAt(j)=='0'){
                    freq[0]++;
                }else{
                    freq[1]++;
                }
                if(freq[1]>=freq[0]*freq[0]){
                    count++;
                }
            }
        }
        System.out.println(count);
        return count;
    }
    public static int numberOfSubstringsBruteForce(String s) {
        //tc:O(n^3)
        int n=s.length();
        int count=0;
        for(int i=0;i<n;i++){
            for(int j=i;j<n;j++){
                if(isValid(s.substring(i,j+1))){
                    count++;
                }
            }
        }
        System.out.println(count);
        return count;
    }

    private static boolean isValid(String sub) {
         int n=sub.length();
        int countOnes=0;
        int countZeroes=0;
         for(int i=0;i<n;i++){
             if(sub.charAt(i)=='0'){
                 countZeroes++;
             }else{
                 countOnes++;
             }
         }

         return countOnes>=Math.pow(countZeroes,2);
    }
}
