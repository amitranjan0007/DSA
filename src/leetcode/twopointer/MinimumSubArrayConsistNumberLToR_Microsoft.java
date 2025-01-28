package leetcode.twopointer;

import java.util.HashMap;

public class MinimumSubArrayConsistNumberLToR_Microsoft {
    //https://docs.google.com/document/d/1cdFabtpzkEJ9wgdWc3e73Ov7600pte_X9HuSlbjGkEs/edit?tab=t.0
    //Question No. 12
    public static void main(String[] args) {
        int[]b={5,2,5,5,5,1,3,4,8,8,2,3,4,7};
        int L=2;
        int R=4;
        helperBruteForce(b,L,R);
        helperBruteOptimal(b,L,R);
    }

    private static void helperBruteForce(int[]b,int L,int R){
        int n=b.length;
        int mini=Integer.MAX_VALUE;
        if(R-L+1>=n) {
            System.out.println("Not possiable");
            return;
        }
        for(int i=0;i<n;i++){
            HashMap<Integer,Integer> map=new HashMap<>();
            for(int j=i;j<n;j++){
                if(b[j]>=L && b[j]<=R){
                    map.put(b[j],map.getOrDefault(b[j],0)+1);
                }
                if(map.size()==R-L+1){
                    int length=j-i+1;
                    mini=Math.min(mini,length);
                }
            }
        }
        System.out.println(mini);
    }

    private static void helperBruteOptimal(int[]b,int L,int R){
        int n=b.length;
        int mini=Integer.MAX_VALUE;
        if(R-L+1>=n) {
            System.out.println("Not possiable");
            return;
        }
        HashMap<Integer,Integer> map=new HashMap<>();
        for(int j=0,i=0;j<n;j++){
            if(b[j]>=L && b[j]<=R){
                map.put(b[j],map.getOrDefault(b[j],0)+1);
            }
            while(map.size()==R-L+1){
                //valid substring --- so stop j and search for i+1 to j
                int length=j-i+1;
                mini=Math.min(mini,length);
                if(map.containsKey(b[i])){
                    map.put(b[i],map.getOrDefault(b[i],0)-1);
                    if(map.get(b[i])==0) map.remove(b[i]);
                }
                i++;
            }

        }
        System.out.println(mini);
    }

}
