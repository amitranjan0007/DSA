package leetcode.graphs;

import java.util.*;

public class FindTheShortestPathToReachParticularNode_BFS {
    private static Scanner scanner=new Scanner(System.in);
    public static void main(String[] args) {
        //NumberOfWaysToReachTargetNode();
        MaxNumberOf5PossiableAtAnyIndex();

    }

    private static void NumberOfWaysToReachTargetNode(){
        /*
            Input :
            12 13
            1 2
            2 3
            3 11
            4 5
            5 6
            6 11
            0 7
            7 8
            8 9
            9 10
            10 11
            0 1
            0 4
            11

        output:2
        * */
        int n=scanner.nextInt();
        int m=scanner.nextInt();

        boolean[]visited=new boolean[n];
        int[]level=new int[n];
        int[]way=new int[n];

        List<List<Integer>> graph=new ArrayList<>();
        for(int i=0;i<n;i++){//As node is 0 based indexing
            graph.add(new ArrayList<>());
        }

        for(int i=0;i<m;i++){// create edge and store in graph
            int u=scanner.nextInt();
            int v=scanner.nextInt();
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        int targetNode=scanner.nextInt();

        if(targetNode>=n) {
            System.out.println("Invalid Node");
            return;
        }

        Queue<Integer> q=new LinkedList<>();
        q.add(0);
        visited[0]=true;
        level[0]=0;
        way[0]=1;
            while (!q.isEmpty()){
                int v=q.poll();
                 for(int u:graph.get(v)){
                     if(!visited[u]){
                         visited[u]=true;
                         q.add(u);
                         level[u]=1+level[v];
                         way[u]=way[v];
                     }else{

                         /*
                          I have already visted this node , so shortest path will reach target
                          1->2->3->11
                          4->5->6->11
                          0->7->8->9->10->11
                          to reach 11 the shortest path is 3 and number of ways is 2
                         * */
                         if(1+level[v]==level[u]){
                             way[u]=way[u]+way[v];
                         }
                     }
                 }
            }

        System.out.println(way[targetNode]);
            scanner.close();
    }

    private static void MaxNumberOf5PossiableAtAnyIndex(){

        /*
         Given a graph where each node is 0 and 5 ,source node is 0,
          your task is to find for each node i,shortest length from node 0+max number of 5 possiable in path

            Input :
            12 13
            1 2
            2 3
            3 11
            4 5
            5 6
            6 11
            0 7
            7 8
            8 9
            9 10
            10 11
            0 1
            0 4
0 0 0 5 5 0 5 5 5 5 0 0

OutPut:
0--0
1--0
2--0
3--1
4--1
5--1
6--2
7--1
8--2
9--3
10--3
11--2

Input 2:
            12 13
            1 2
            2 3
            3 11
            4 5
            5 6
            6 11
            0 7
            7 8
            8 9
            9 10
            10 11
            0 1
            0 4
0 5 5 5 5 0 5 5 5 5 0 0

0--0
1--1
2--2
3--3
4--1
5--1
6--2
7--1
8--2
9--3
10--3
11--3

        * */
        int n=scanner.nextInt();
        int m=scanner.nextInt();

        boolean[]visited=new boolean[n];
        int[]level=new int[n];
        int[]count=new int[n];
        int[]values=new int[n];

        List<List<Integer>> graph=new ArrayList<>();
        for(int i=0;i<n;i++){//As node is 0 based indexing
            graph.add(new ArrayList<>());
        }

        for(int i=0;i<m;i++){// create edge and store in graph
            int u=scanner.nextInt();
            int v=scanner.nextInt();
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        for(int i=0;i<n;i++){//As node is 0 based indexing
            values[i]=scanner.nextInt();
        }


        Queue<Integer> q=new LinkedList<>();
        q.add(0);
        visited[0]=true;
        level[0]=0;
        if(values[0]==5)count[0]=1;
        while (!q.isEmpty()){
            int v=q.poll();
            for(int u:graph.get(v)){
                if(!visited[u]){
                    visited[u]=true;
                    q.add(u);
                    level[u]=1+level[v];

                   count[u]=values[u]==5 ? 1+count[v]:count[v];
                }else{
                    if(1+level[v]==level[u]){
                        count[u]=values[u]==5?Math.max(count[u],1+count[v]):Math.max(count[u],count[v]);
                    }
                }
            }
        }

        for(int i=0;i<count.length;i++){
            System.out.println(i+"--"+count[i]);
        }


        // when it will ask to find max path encounted by 5, then from the count array take max
        int maxi=Arrays.stream(count).max().getAsInt();
        System.out.println(maxi);
        scanner.close();
    }

}
