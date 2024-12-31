package leetcode.hashing;

import java.util.HashMap;

public class FiveSumWithFiveArray {
    public static void main(String[] args) {

        System.out.println("Four Sum Brute Way "+fourSumCountBrute());
        System.out.println("Four Sum suboptimal Way "+fourSumCountSubOptimal());
        System.out.println("Four Sum optimal Way "+fourSumCountOptimal());
        System.out.println("Five Sum optimal Way "+fiveSumCountOptimal());


    }

    public static int fourSumCountBrute() {
        //w+x+y+z=0
        int[] nums1={1,2};
        int[] nums2={-2,1};
        int[] nums3={-1,2};
        int[] nums4={0,2};
        int n=nums1.length;
        int count=0;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                for(int k=0;k<n;k++){
                    for(int l=0;l<n;l++){
                        int sum=nums1[i]+nums2[j]+nums3[k]+nums4[l];
                        if(sum==0) count++;
                    }
                }
            }
        }
        return count;
    }
    public static int fourSumCountSubOptimal() {
        //w+x+y=-z
        int[] nums1={1,2};
        int[] nums2={-2,1};
        int[] nums3={-1,2};
        int[] nums4={0,2};
        int n=nums1.length;
        int count=0;
        HashMap<Integer,Integer> map=new HashMap<>();
        for(int i=0;i<n;i++){
            map.put(nums4[i],map.getOrDefault(nums4[i],0)+1);
        }
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                for(int k=0;k<n;k++){
                    int sum=nums1[i]+nums2[j]+nums3[k];
                    if(map.containsKey(-sum)){
                        count+= map.get(-sum);
                    }
                }
            }
        }
        return count;
    }

    public static int fourSumCountOptimal() {
        //w+x=-(y+z)
        int[] nums1={1,2};
        int[] nums2={-2,1};
        int[] nums3={-1,2};
        int[] nums4={0,2};
        int n=nums1.length;
        int count=0;
        HashMap<Integer,Integer> map=new HashMap<>();
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                int sum=nums3[i]+nums4[j];
                map.put(sum,map.getOrDefault(sum,0)+1);
            }
        }
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                int sum=nums1[i]+nums2[j];
                if(map.containsKey(-sum)){
                    count+= map.get(-sum);
                }
            }
        }
        return count;
    }

    public static int fiveSumCountOptimal() {
        /*
         v+w+x+y+z=0
         v+w=-(x+y+z)
        * */
        int[] nums1 = {2, 5};
        int[] nums2 = {3, 8};
        int[] nums3= {-5, 8};
        int[] nums4 = {5, 10};
        int[] nums5 = {-10, 100};
        int n=nums1.length;
        int count=0;
        HashMap<Integer,Integer> map=new HashMap<>();
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                for(int k=0;k<n;k++){
                    int sum=nums3[i]+nums4[j]+nums5[k];
                    map.put(sum,map.getOrDefault(sum,0)+1);
                }
            }
        }
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                int sum=nums1[i]+nums2[j];
                if(map.containsKey(-sum)){
                    count+= map.get(-sum);
                }
            }
        }
        return count;
    }
}
