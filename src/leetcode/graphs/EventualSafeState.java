package leetcode.graphs;

import java.util.ArrayList;
import java.util.List;


//Question : https://leetcode.com/problems/find-eventual-safe-states/description/
public class EventualSafeState {
    public static void main(String[] args) {
        int[][] graph={{1,2,3,4},{1,2},{3,4},{0,4},{}};
        int[][] graph1={{1,2},{2,3},{5},{0},{5},{},{}};
        List<Integer> ans=eventualSafeNodes(graph1);
        for(int num:ans){
            System.out.print(num+" ");
        }
    }
    public static List<Integer> eventualSafeNodes(int[][] graph) {
        int n=graph.length;

        boolean[] visited =new boolean[n];
        boolean[] pathVisited =new boolean[n];
        boolean[] check=new boolean[n];
        List<Integer> ans=new ArrayList<>();
        for(int i=0;i<n;i++){
            if(!visited[i]){
                dfs(i,visited,pathVisited,graph,check);
            }
        }

        for(int i=0;i<n;i++){
            if(check[i])ans.add(i);
        }
        return ans;
    }

    public static boolean dfs(int src,boolean[] visited,boolean[] pathVisited,int[][] graph,boolean[] check){
        visited[src]=true;
        pathVisited[src]=true;
        check[src]=false;
        for(int nbr: graph[src]){
            if(!visited[nbr]){
                if(dfs(nbr,visited,pathVisited,graph,check))return true;
            }else if(pathVisited[nbr]){
                return true;
            }
        }
        pathVisited[src]=false;
        check[src]=true;
        return false;
    }
}
