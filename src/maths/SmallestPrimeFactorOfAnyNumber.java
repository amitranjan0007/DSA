package maths;

public class SmallestPrimeFactorOfAnyNumber {
    /*
       n=9
        2 3 4 5 6 7 8 9
        2 3 2 5 2 7 2 3  =>>> print this as a ouput
    */
    public static void main(String[] args) {
        generateSmallestPrimeFactor(9);
    }
    private static void generateSmallestPrimeFactor(int n){
        int[] spf=new int[n+1];
        for(int i=2;i<=n;i++){
            spf[i]=i;
        }
        for(int i=2;i<Math.sqrt(n);i++){
            if(spf[i]==i){
                for(int j=i*i;j<=n;j=j+i){
                    spf[j]=i;
                }
            }
        }

        for(int i=2;i<=n;i++){
            System.out.print(spf[i]+" ");
        }
    }


}
