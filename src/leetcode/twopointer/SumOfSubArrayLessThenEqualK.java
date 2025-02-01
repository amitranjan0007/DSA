package leetcode.twopointer;

/*
Problem No: Two Pointers: 27
https://drive.google.com/file/d/1LgbxjlfURk434-Y9OW3Dbt4kzs_IFmgH/view
https://docs.google.com/document/d/1rNsvOfgNl8SPZNZFpI1ZLpQmJqhPcaluNvDfievFs1U/edit?tab=t.0
* */
public class SumOfSubArrayLessThenEqualK {
    public static void main(String[] args) {
        int[]b={1,2,3,4,6};
        int k=5;
        countSubArrayLessThenEqualK(b,k);
        SumOfValidSubArrayLessThenEqualK(b,k);
    }

    private static void countSubArrayLessThenEqualK(int[] b,int k){
        int n=b.length;
        int sum=0;
        int count=0;
        for(int j=0,i=0;j<n;j++){
            sum+=b[j];
            while(sum>k && i<=j){
                sum-=b[i];
                i++;
            }
            count+=(j-i+1);
        }
        System.out.println(count);
    }

    private static void SumOfValidSubArrayLessThenEqualK(int[] b,int k){
        /*
         Observation: we have to check valid substring and calculate the sum
         eg:
            k=5
            idx-> 0 1 2 3 4
            b[]-> 1 2 3 4 6

            (1)         = 1
            (2)(1,2)    =2+3=5
            (3)(2,3)    =3+5=8
            (4)         =4
             total =    18

           i....j = calculating the sum

           if window valid then
           [0..0]
           totalSum=b[0]
           [0..1]
           totalSum=b[1]+(b[0]+b[1])=b[0]+2*b[1] = totalSum+2*b[1]
           [0..2]=>( (3)(2,3)--valid but (1,2,3) is invalid -- so in total we have to substract (1+2+3)==this is prefix sum from 0..2
           totalSum=b[2]+(b[2]+b[1])+(b[2]+b[1]+b[0]= b[0]+2*b[1]+3*b[2]-sum(0..2)=
           b[0]+2*b[1]+3*b[2]-[b[0]+b[1]+b[2]]=1*b[1]+2*b[2]
            or  we can write as below
           b[0]+2*b[1]+3*b[2]-[prefixSum[2]-prefixSum[0]]
           ...
           ...
           so on
        * */
        int n=b.length;
        int[] prefix=new int[n];
        prefix[0]=b[0];
        for(int i=1;i<n;i++){
            prefix[i]=prefix[i-1]+b[i];
        }
        int sum=0;
        int count=0;
        int totalSum=0;
        for(int j=0,i=0;j<n;j++){
            sum+=b[j];
            totalSum=totalSum+(j-i+1)*b[j];
            while(sum>k && i<=j){
                sum-=b[i];
                if(i-1>=0)
                    totalSum=totalSum-(prefix[j]-prefix[i-1]);
                else
                    totalSum=totalSum-prefix[j];
                i++;
            }
            count+=totalSum;
        }
        System.out.println(count);
    }
}
