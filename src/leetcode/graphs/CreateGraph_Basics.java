package leetcode.graphs;

/*

 Graph Looks like this

     1----2-----5
     |    |     |
     3----4------


5 6 ---> 5 node and 6 edges
1 2
1 3
3 4
4 2
2 5
4 5

if it is undirected means opposite also exist

You can create a graph in 2 ways
1. Matrix way  --> if node is 1 based indexing and number of node is let say 5 then
                   we create a matrix of size mat[n+1][n+1]


2. Adjacency List - optimise the space
   0 - []
   1 - [2,3]
   2 - [1,4,5]
   3 - [1,4]
   4 - [3,2,5]
   5 - [2,4]
* */


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CreateGraph_Basics {
    private static Scanner scanner=new Scanner(System.in);
    public static void main(String[] args) {
        CreateGraphUsingMatrix();
        CreateGraphUsingAdjacencyList();
    }

    private static void CreateGraphUsingMatrix(){
        //sc: n*m tc : n
         int n=scanner.nextInt();
         int m=scanner.nextInt();
         int[][] graph=new int[n+1][n+1];

         for(int i=0;i<m;i++){// create edge and store in graph
             int u=scanner.nextInt();
             int v=scanner.nextInt();
             graph[u][v]=1;
             graph[v][u]=1;
         }
    }

    private static void CreateGraphUsingAdjacencyList(){
        //sc: n+m tc : n
        int n=scanner.nextInt();
        int m=scanner.nextInt();
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
    }

}
