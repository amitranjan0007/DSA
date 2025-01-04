package leetcode.hashing;

import java.util.*;

public class FourSum {

    /*
    Given an array of size “N”; find -> find the number of unordered quadruplets (i,j,k,l) such that b[i] + b[j] + b[k] + b[l] = 0

    -> [1 2 3 4 -1 -2 -2]

    -> 3 [1,2,5,6]  [1,2,5,7] [1,3,6,7]
    Note: This question will count duplicate also

    *
    **/
    public static void main(String[] args) {
        //solveSubOptimal();
        solveOptimal();
    }

    private static void solveSubOptimal(){
        //TC: O(N^3)
        //-2 -1 0 0 2 1 => {-2,-1,1,2},{-2,0,0,2},{-1,0,0,1} so total count 3;
        /*
        * In this approach we are fixing three element & finding next element from map
        * */
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[] b = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            b[i] = sc.nextInt();
        }

        HashMap<Integer, Integer> u = new HashMap<>();
        long r = 0;

        // Initialize the hashmap for elements in range [4, n]
        // Nested loops to calculate r
        for (int i = 1; i <= n-3; i++) {
            for (int j = i + 1; j <= n-2; j++) {
                for(int p=j+2;p<=n;p++){
                    u.put(b[p], u.getOrDefault(b[p], 0) + 1);
                }
                for (int k = j + 1; k <= n - 1; k++) {

                    int d = -(b[i] + b[j] + b[k]);
                    if(u.containsKey(d)){
                        System.out.println(b[i]+" "+b[j]+" "+b[k]+" "+d);
                        r += u.getOrDefault(d, 0); // count frequency of d in the range [k+1, n]
                    }

                    // Decrement frequency of b[k+1]
                    u.put(b[k+1], u.get(b[k+1]) - 1);
                    if(u.get(b[k+1])==0) u.remove(b[k+1]);
                }
            }
        }

        System.out.println(r);
    }


    private static void solveOptimal(){
        //TC: O(N^2)
        //-2 -1 0 0 2 1 => {-2,-1,1,2},{-2,0,0,2},{-1,0,0,1} so total count 3;
        /*
           Observation: fix i & j and find number of such pair in left i.e k & l such that there sum is 0
           ....i,j.........k,l.......

           arr[i]+arr[j]= sum
           so what we have to find in right to make arr[i]+arr[j]+arr[k]+arr[l]=0
           arr[k]+arr[l]= -sum

           so we precalculate the sum and store in hashmap for all the pair k & l

            In this approach we are fixing 2 element & finding next 2 pair from map

        * */
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            arr[i] = sc.nextInt();
        }
        HashMap<Integer, Integer> map = new HashMap<>();

        for(int k=3;k<=n-1;k++){
            for(int l=k+1;l<=n;l++){
                int sum=arr[k]+arr[l];
                map.put(sum,map.getOrDefault(sum,0)+1);
            }
        }
        long res = 0;

        for(int j=2;j<=n;j++){
            for(int i=1;i<j;i++){
                int sum=arr[i]+arr[j];
                res+= map.getOrDefault(-sum,0);// if found count of pair ,that number of triplet we can form LHS+RHS
            }
            /*
               eg:
               we already calculated for
               (3,4)
               (3,5)
               (3,6)
               ....
               j+1,j+2
               j+1,j+3
               ...
               ...
               ...
            * **/
            for(int p=j+2;p<=n;p++){//remove the pair we have already calculated
                int sum=arr[p]+arr[j+1];
                map.put(sum,map.getOrDefault(sum,0)-1);
                if(map.get(sum)<=0) map.remove(sum);
            }
        }


        System.out.println(res);
    }
}
