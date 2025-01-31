package desiqna;

/*
Doc Link: https://docs.google.com/document/d/1uiXXTRSmB8Cfox66PQVGRLIJ5_AQyfK2lhtAa_y_Hhk/edit?tab=t.0
*/

public class Advitiya_Security_Vault_CodeChef {
    public static long m = (long) 1e9 + 7;
    public static void main(String[] args) {
        int n= 2;
        int k=3;
        NumberOfArraySizePossibleEasiest(n,k);//if n size array have all zero in it
        NumberOfArraySizePossibleEasier(n,k);//if n size array have all zero in it but reversal count not possiable


        //CodeChef-Contest
        int[] b = {0, 0, 0, 132, 0, 205, 132, 0, 0, 0, 0};
        int k1 = 343;
        NumberOfArraySizePossibleHarder_CodeChef(b,k1);
    }

    //Easiest Version
    private static void NumberOfArraySizePossibleEasiest(int n, int k){
        long totalPalindrome= n%2==0?pow(k,n/2):pow(k,(n/2)-1);
        long total=pow(k,n);
        long totalNonPalindrome=total-totalPalindrome;
        long ans=totalPalindrome+totalNonPalindrome;
        System.out.println(ans);
    }

    //Easier Version
    private static void NumberOfArraySizePossibleEasier(int n,int k){
        //counting number if reverse does not exist in the valid sequences,where all the number in n array size element is 0
        long totalPalindrome= n%2==0?pow(k,n/2):pow(k,(n/2)-1);
        long total=pow(k,n);
        long totalNonPalindrome=total-totalPalindrome;
        long ans=totalPalindrome+(totalNonPalindrome/2);
        System.out.println(ans);
    }

    //Harder Version
    private static void NumberOfArraySizePossibleHarder_CodeChef(int[]b,int k){
       int n=b.length;
       long total=pow(k,n);//total sequences

       int l= (n%2)==0?n/2-1:n/2;
       int i=0;
       long totalPalindrome=1;
       while (i<=l){
           if(b[i]==0 && b[n-i-1]==0){
               totalPalindrome=(totalPalindrome*k)%m;
           }
           else if(b[i] == 0 || b[n - i - 1] == 0){
               totalPalindrome*=1;
           }
           else if(b[i]==b[n-i-1]){
               totalPalindrome*=1;
           }
           else if(b[i]!=b[n-i-1]) {
               totalPalindrome*=0;
               break;
           }
           i++;
       }

       i=0;
       long totalNonPalindromeWhichCannotReversed=1;
       while(i<=l){
           //case 1: when b[i]==0 && b[n-i-1]==0
           if (b[i] == 0 && b[n - i - 1] == 0) {
               totalNonPalindromeWhichCannotReversed = (totalNonPalindromeWhichCannotReversed * (k * (long)k)) % m;
           }
           //case 2: 0-P or P-0
           else if( (b[i]==0 && b[n-i-1]!=0) || (b[i]!=0 && b[n-i-1]==0)){
               totalNonPalindromeWhichCannotReversed=(totalNonPalindromeWhichCannotReversed*(k))%m;
           }
           //case 3: when b[i]!=0 & b[n-i-1]!=0
           else if( b[i]!=b[n-i-1]){
               totalNonPalindromeWhichCannotReversed=(totalNonPalindromeWhichCannotReversed*1)%m;
           }

           i++;
       }
       long totalNonPalindromeWhichCanReversed=(total-totalPalindrome)/2;
        long ans=totalPalindrome+totalNonPalindromeWhichCannotReversed+totalNonPalindromeWhichCanReversed;
        System.out.println(ans);
    }

    private static long pow(long x,long y){
        long result=1;
        while(y>0){
            if(y%2!=0) result=(result*x)%m;
            x=(x*x)%m;
            y=y/2;
        }
        return result;
    }
}
