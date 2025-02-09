package leetcode.graphs;


import java.util.*;

/*
 Graph
         1
      2     6
    3   4 7   9
       5----8

   n=9 m= 9
   9 9 --- is n & m
   1 2
   1 6
   2 3
   2 4
   6 7
   6 9
   4 5
   7 8
   5 8
1 2 3 4 5 8 7 6 9
* */
public class TraversalTechnique_BFS_DFS_Basics {
    private static Scanner scanner=new Scanner(System.in);
    public static void main(String[] args) {
        int n=scanner.nextInt();//no. of node
        int m=scanner.nextInt();//no. of edges

        List<Integer> answer=new ArrayList<>();
        List<List<Integer>> graph= CreateGraphUsingAdjacencyList(n,m);
        boolean[] visited=new boolean[n+1];
       // bfs(visited,graph,answer);
        dfs(1,visited,graph,answer); // we are doing 1 based indexing over here considering node from 1 to n
        answer.forEach((num)->System.out.print(num+" "));
    }

    public static void bfs( boolean[] visited,List<List<Integer>> graph, List<Integer> answer) {
        //TC: O(n)+O(2E)
        Queue<Integer> q=new LinkedList<>();
        q.add(1);
        visited[1]=true;

        while(!q.isEmpty()){
            int currNode=q.poll();
            answer.add(currNode);

            List<Integer> nbList= graph.get(currNode);
            for(int i=0;i<nbList.size();i++){
                int nbNode= nbList.get(i);
                if(!visited[nbNode]){
                    q.add(nbNode);
                    visited[nbNode]=true;
                }
            }
        }

       // answer.forEach(num->System.out.print(num+" "));

    }
    private static void dfs(int currNode,boolean[] visited,List<List<Integer>> graph, List<Integer> answer){
        visited[currNode]=true;
        answer.add(currNode);
        for(int nbr:graph.get(currNode)){
            if(!visited[nbr]){
                dfs(nbr,visited,graph,answer);
            }
        }
    }
    private static List<List<Integer>> CreateGraphUsingAdjacencyList(int n,int m){
        //sc: 2E tc : n
        List<List<Integer>> graph=new ArrayList<>();
        for(int i=0;i<=n;i++){//As node is 1 based indexing
            graph.add(new ArrayList<>());
        }
        for(int i=0;i<m;i++){// create edge and store in graph
            int u=scanner.nextInt();
            int v=scanner.nextInt();
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        return graph;
    }
}
