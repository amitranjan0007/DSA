package leetcode.greedy;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.PriorityQueue;

/*
Question Link: https://www.desiqna.in/17770/atlassian-oa-ctc-75-l-sep-21-kumar-k-q2
https://docs.google.com/document/d/1rFC7PjgJqqh5DJ-gHVfkcZRTm7NhK7AVshCcGJozg9c/edit?tab=t.0
* */
public class MinCostSoThatAllElementDistinct {
    public static void main(String[] args) {
        //int[] num={1,3,3,3,4,4};
//        int[] num={1,1,1,1,1,1,2,2,3,3};
//        int[] cost1={2,3,4,5,6,7,8,9,9,12};
//        int[] num={1,1,5,6};
//        int[] cost1={2,3,4,8};
        int[] num={5,2,5,3,3,8,8};
        int[] cost={1,1,1,1,1,1,1};
        int[] cost1={3,7,8,6,9,4,2};
        MinCostSoThatAllElementDistinct_EasyVersion(num,cost);
        MinCostSoThatAllElementDistinct_HardVersion(num,cost1);
    }
    private static int MinCostSoThatAllElementDistinct_EasyVersion(int[] num,int[] cost){
        int n=num.length;
        Arrays.sort(num);
        int totalCost=0;
        int maxi=1;
        for(int i=1;i<=n;i++){
            if(num[i-1]>maxi){
                maxi=num[i-1];
            }
            totalCost+=(cost[i-1]*Math.abs(num[i-1]-maxi));
            maxi++;
        }
        System.out.println(totalCost);
        return totalCost;
    }
    private static int MinCostSoThatAllElementDistinct_HardVersion(int[] num,int[] cost){
        Pair[] pairs = new Pair[num.length];
        for (int i = 0; i < num.length; i++) {
            pairs[i] = new Pair(num[i], cost[i]);
        }

        Arrays.sort(pairs,(a,b)->a.num-b.num);

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        int totalCost = 0;
        HashMap<Integer, Integer> sumOfCosts = new HashMap<>();

        for (int i = 0; i < pairs.length; i++) {
            int currentNum = pairs[i].num;
            int currentCost = pairs[i].cost;

            // Add cost to the sum of costs for the current number
            sumOfCosts.put(currentNum, sumOfCosts.getOrDefault(currentNum, 0) + currentCost);

            if (i <= 0 || currentNum != pairs[i - 1].num) {

                // If the heap is not empty, remove the highest cost to minimize total cost
                if (!maxHeap.isEmpty()) {
                    totalCost += sumOfCosts.get(pairs[i - 1].num) - maxHeap.poll(); // Removing the highest cost
                       if(pairs[i - 1].num+1!=currentNum)maxHeap.clear();
                }
            }
            maxHeap.add(currentCost); // Add the cost to the max heap
        }

        totalCost+=sumOfCosts.get(pairs[pairs.length-1].num)-maxHeap.poll();
        System.out.println(totalCost);
        return totalCost;
    }
    static class Pair {
        int num;
        int cost;

        public Pair(int num, int cost) {
            this.num = num;
            this.cost = cost;
        }
    }
}
