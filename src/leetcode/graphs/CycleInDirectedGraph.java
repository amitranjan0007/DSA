package leetcode.graphs;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/*
10 11
1 2
2 3
3 4
4 5
5 6
3 7
7 5
8 9
9 10
10 8
8 2

have cycle
* */
public class CycleInDirectedGraph {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();
        int m=scanner.nextInt();
        List<List<Integer>> adjList=new ArrayList<>();
        for(int i=0;i<=n;i++){//As node is 1 based indexing
            adjList.add(new ArrayList<>());
        }
        for(int i=0;i<m;i++){// create edge and store in graph
            int u=scanner.nextInt();
            int v=scanner.nextInt();
            adjList.get(u).add(v);
        }
        boolean[] visited=new boolean[n+1];
        boolean[] path=new boolean[n+1];
        for (int i=1;i<=n;i++){
            if(!visited[i]){
                if(dfs(i,visited,path,adjList)){
                    System.out.println("have cycle");
                    return;
                }
            }
        }
        System.out.println("no cycle");
    }
    private static boolean dfs(int src,boolean[] visited,boolean[] path, List<List<Integer>> adjList){
        visited[src]=true;
        path[src]=true;
        for(int nbr:adjList.get(src)){
            if(!visited[nbr]){
                if(dfs(nbr,visited,path,adjList)) return true;
            }else if(path[nbr]=true) return true;
        }
        path[src]=false;//very important ,when you go back then mark path as false
        return false;
    }
}
