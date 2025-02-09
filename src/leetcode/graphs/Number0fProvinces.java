package leetcode.graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

//Problem: https://leetcode.com/problems/number-of-provinces/description/
public class Number0fProvinces {
    public static void main(String[] args) {
       int[][] mat={{1,1,0},{1,1,0},{0,0,1}};
        int n=mat.length;
        boolean visited[]=new boolean[n];
        ArrayList<ArrayList<Integer>> adjList= new ArrayList<>();
        for(int i=0;i<n;i++){
            adjList.add(new ArrayList<>());
        }
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(mat[i][j]==1 && i!=j){
                    createEdge(adjList,i,j);
                }
            }
        }
        //int res=findCircleNumDfs(adjList,visited,n);
        int res1=findCircleNumBfs(adjList,visited,n);
        System.out.println(res1);
    }
    public static int findCircleNumDfs(ArrayList<ArrayList<Integer>> adjList,boolean visited[],int n) {
        int count=0;
        for(int i=0;i<n;i++){
            if(!visited[i]){
                count++;
                dfs(i,adjList,visited);
            }
        }
        return count;
    }
    public static int findCircleNumBfs(ArrayList<ArrayList<Integer>> adjList,boolean visited[],int n) {
        int count=0;
        for(int i=0;i<n;i++){
            if(!visited[i]){
                count++;
                bfs(i,adjList,visited);
            }
        }
        return count;
    }
    private static void createEdge(ArrayList<ArrayList<Integer>> adjList,int u,int v){
        adjList.get(u).add(v);
        adjList.get(v).add(u);
    }
    private static void dfs(int src,ArrayList<ArrayList<Integer>> adjList,boolean visited[]){
        visited[src]=true;
        for(int nbr:adjList.get(src)){
            if(!visited[nbr]){
                dfs(nbr,adjList,visited);
            }
        }
    }

    private static void bfs(int src,ArrayList<ArrayList<Integer>> adjList,boolean visited[]){
        Queue<Integer> q=new LinkedList<>();
        q.add(src);
        visited[src]=true;
        while (!q.isEmpty()){
            int currNode=q.poll();
              for(int nbr:adjList.get(currNode)){
                  if(!visited[nbr]){
                      visited[nbr]=true;
                      q.add(nbr);
                  }
              }
        }
    }
}
