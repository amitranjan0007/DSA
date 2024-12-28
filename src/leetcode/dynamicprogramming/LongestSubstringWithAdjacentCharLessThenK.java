package leetcode.dynamicprogramming;

public class LongestSubstringWithAdjacentCharLessThenK {
    public static void main(String[] args) {
        /*
        https://docs.google.com/document/d/1tCEKs5DZtb-hILaYNgMNN8SSQBcQLWm33g2YDljrQBg/edit?tab=t.0

        *
        **/
        String str="ababbacaabbbb";
        int k=1;
       String res= helper(str,k);
        System.out.println(res);
    }

    private static String helper(String str,int k){
        int n=str.length();
        int dp[]=new int[n];
        dp[0]=1;
        int maxi=0;
        int maxIndex = 0;
        for(int i=1;i<n;i++){
            if(Math.abs(str.charAt(i)-str.charAt(i-1))<=k){
                dp[i]=1+dp[i-1];

            }else{
                dp[i]=1;
            }
            if(maxi<dp[i]){
                maxi=dp[i];
                maxIndex=i;
            }
        }
        int startIdx=maxIndex-maxi+1;
        return str.substring(startIdx,startIdx+maxi);
    }
}
