package leetcode.nArrayTree;

import java.util.ArrayList;
import java.util.Scanner;

public class MaximiseTheVerticalLength {
    private static int[] nodeValues;
    private static boolean[] visited;
    private static int[] height;
    private static int[] prefixSum;
    private static int[] suffixSum;
    private static  int[] dp;
    public static void main(String[] args) {
        Scanner scanner= new Scanner(System.in);
        int n= scanner.nextInt();
        ArrayList<ArrayList<Integer>> adj=new ArrayList<>();
        nodeValues=new int[n];
        visited=new boolean[n];
        dp=new int[n];
        height=new int[n];
       // dp=new int[n];

        for (int i=0;i<n;i++){
            adj.add(new ArrayList<>());
            nodeValues[i]=scanner.nextInt();
        }


        for(int i=0;i<n-1;i++){//In tree number of edges is always number of nodes-1
            int u=scanner.nextInt();
            int v=scanner.nextInt();
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        int[] parent=new int[n];
        parent[0]=-1;
        heightDfs(0,parent,adj,visited);

       // System.out.println("height of tree "+height[0]);
        visited=new boolean[n];
        parent=new int[n];
        parent[0]=-1;
        verticalPathDfs(0,parent,adj,visited);
        System.out.println("Maximum Square Path: "+dp[0]);
    }

    private static void heightDfs(int node, int[] parent, ArrayList<ArrayList<Integer>> adj, boolean[] visited) {
        visited[node]=true;
        for(int child:adj.get(node)){
            if(!visited[child]){
                parent[child]=node;
                heightDfs(child,parent,adj,visited);
            }
        }

        int h=0;
        for(int nbr:adj.get(node)){
          if(nbr!=parent[node]){
              h=Math.max(h,height[nbr]);
          }
        }
        height[node]=1+h;
    }

    private static void verticalPathDfs(int src, int[] parent, ArrayList<ArrayList<Integer>> adj, boolean[] visited) {
        visited[src]=true;
        for(int nbr:adj.get(src)){
            if(!visited[nbr]){
                parent[nbr]=src;
                verticalPathDfs(nbr,parent,adj,visited);
            }
        }

        ArrayList<Integer> children=new ArrayList<>();
        for(int nbr:adj.get(src)){
            if(nbr!=parent[src]){
                children.add(nbr);
            }
        }

        int n=children.size();
        if(n>0){
            prefixSum=new int[n];
            suffixSum=new int[n];
            prefixSum[0]=dp[children.get(0)];
            suffixSum[n-1]=dp[children.get(n-1)];
            for(int i=1;i<n;i++){
                prefixSum[i]=prefixSum[i-1]+dp[children.get(i)];
            }
            for(int i=n-2;i>=0;i--){
                suffixSum[i]=suffixSum[i+1]+dp[children.get(i)];
            }
            int idx=0;
            for(int nbr:children){
                    int z=0;
                    if(idx-1>=0)z+=prefixSum[idx-1];
                    if(idx+1<=n-1)z+=suffixSum[idx+1];
                    int sum=z;
                    int l=2*height[nbr];
                    dp[src]=Math.max(dp[src],l+1+sum+dp[nbr]);
                idx++;
            }
        }else{
            dp[src]=1;
        }
       // System.out.println(src+"--"+dp[src]);

    }
}
/*
7
0 1 2 3 4 5 6
0 1
0 2
1 4
1 3
2 5
2 6
*
* */