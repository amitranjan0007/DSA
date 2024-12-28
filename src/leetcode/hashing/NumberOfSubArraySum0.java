package leetcode.hashing;

import java.util.Arrays;
import java.util.HashMap;

public class NumberOfSubArraySum0 {
    public static void main(String[] args) {
        int[] arr= {1,1,-1,-1,1,-1};
        countSubArraySumEqual0Way1(arr);
        countSubArraySumEqual0Way2(arr);
        countSubArraySumEqualNumberOfXAndY();//Very Important See it . Based on above logic ,how to solve this question
        countSubArraySumEqualNumberOfXAndYWithoutMapping();
    }
    public static void countSubArraySumEqual0Way1(int[]arr) {
        /*
          0 1  2  3 4  5
          1 1 -1 -1 1 -1
       0  1 2  1  0

          j-i+1
        * **/


        int count=0;
        int sum=0;
        HashMap<Integer,Integer> map=new HashMap<>();
        map.put(0,1);// 0 is contributing 1 number of time , this is virtual idx
        for(int j=0;j<arr.length;j++){
            sum+=arr[j];
            if(map.containsKey(sum)){
                count+=map.get(sum);
            }
            map.put(sum,map.getOrDefault(sum,0)+1);
        }
        System.out.println(count);
    }

    public static void countSubArraySumEqual0Way2(int[]arr) {
        /*
          0 1  2  3 4  5
          1 1 -1 -1 1 -1
       0  1 2  1  0

          j-i+1
        * **/
        int count=0;
        int sum=0;
        HashMap<Integer,Integer> map=new HashMap<>();
        for(int j=0;j<arr.length;j++){
            sum+=arr[j];
              if(sum==0) count++;
            if(map.containsKey(sum)){
                count+=map.get(sum);
            }
            map.put(sum,map.getOrDefault(sum,0)+1);
        }
        System.out.println(count);
    }

    public static void countSubArraySumEqualNumberOfXAndY() {
        /*
          Let the array consist of 2 numbers only 1 & 2 .
          Find count of subArray where of number of 1's === number of 2's

          Observation: Number of 1's == Number of 2's :) Great
              x= Number of 1's
              y= Number of 2's
              x-y=0 great :)

              map 1's to 1 and map 2 to -1 and find count subarray sum equals to zero
              {1,2,2,1,1} ==map it=>>> {1,-1,-1,1,1,1}
        * */
        int[] arr={1,1,2,2,1,2,1};
        int[] mappedArray=Arrays.stream(arr).map((num) -> num == 2 ? -1 : 1).toArray();
        countSubArraySumEqual0Way2(mappedArray);
    }

    public static void countSubArraySumEqualNumberOfXAndYWithoutMapping() {
        /*
          Let the array consist of 2 numbers only 1 & 2 .
          Find count of subArray where of number of 1's === number of 2's

          Observation:
               1...........i......j......n

               c1= number of 1's
               c2= number of 2's

               c1[j]= number of 1's till index j
               c2[j]= number of 2's till index j

               Let suppose from s[i...j] we have equal number of 1's & 2's
               as s[i..j] denotes equal number of 1's & 2's

               x=c2[j]=c2[i-1]+s[i..j]
               y=c1[j]=c1[i-1]+s[i..j]
               ......................
               x-y= c2[j]-c1[j]=c2[i-1]-c1[i-1]

               so at nth index you can get the count of subarray have equal number of 1's & 2's

               so at each index calculate c2-c1 means number of 2's-number of 1's till now it found upto that idx
               & store c2-c1 with it's frequency in the map , so while iteration if you found the same difference(c2-c1)
               exist in a map you found that number of subarray
               map={
                     1-->1
                     2-->1
                   }
                        1  1  2 2 1 2
                c2-c1  -1 -2 -1          ==> as you see at idx 2 ,it's -1 & you found in map -1 so there is valid subaaray i.e [1,2]
                c2-c1  -1 -2 -1 0        ===> 0, again you can make subarray start from idx 0; [1,1,2,2]
                c2-c1  -1 -2 -1 0 -1     ==> ohho , we found -1 in map 2 times so two subarray posiable [1,2,2,1] & [2,1]
                c2-c1  -1 -2 -1 0 -1 0   ==> oho 0 ,means one we can get from start of idx & next subbarry start 4. i.e [1,1,2,2,1,2] & [1,2]

               Total 6
        * */
        int[] arr={1,1,2,2,1,2,1};
        int c1=0;
        int c2=0;
        int count=0;
        HashMap<Integer,Integer> map=new HashMap<>();
        map.put(0,1);
         for(int i=0;i<arr.length;i++){
             if(arr[i]==1){
                 c1++;
             }else if(arr[i]==2){
                 c2++;
             }
             int x=c2-c1;
             if(map.containsKey(x)){
                 count+=map.get(x);
             }
             map.put(x,map.getOrDefault(x,0)+1);

         }

        System.out.println(count);
    }

}
