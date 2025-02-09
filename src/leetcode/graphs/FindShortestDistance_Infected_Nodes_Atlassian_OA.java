package leetcode.graphs;

import java.util.*;
/*
Doc Link: https://docs.google.com/document/d/1WwoegxRueHsX_SBUSRRpSAq1uEQERJ120pIAHOptBsA/edit?tab=t.0
Easy Version Question:
You are given a unweighted undirected graph of N nodes; and M edges;
some nodes have value as 0 and some node have value as 1
Find the shortest distance from node 1 to node N; but you can travel only nodes having value as 1

Easy Version Input:
Graph Input
10 12
1 2
1 4
1 6
2 3
3 10
4 5
5 10
6 7
7 8
8 10
1 9
9 10
1 1 0 0 1 1 1 1 1 1
output: 2

9 10
1 2
1 4
1 6
2 3
3 9
4 5
5 9
6 7
7 8
8 9
1 1 0 0 1 1 1 1 1
output: 4

9 10
1 2
1 4
1 6
2 3
3 9
4 5
5 9
6 7
7 8
8 9
1 1 0 0 1 1 1 1 1
output: Not possiable

Medium Level Question:
 Out of all the nodes having value 0 -> 1 particular node in them has
 been infected with virus of size “K” and all nodes in the vicinity at a
 shortest distance<=K from that node will also get infected;
 now tell the shortest distance from node 1 to Node N;

Medium version Input:
    Follow Up:
    10 12 //n,m
    1 2
    1 4
    1 6
    2 3
    3 10
    4 5
    5 10
    6 7
    7 8
    8 10
    1 9
    9 10
    1 1 0 0 1 1 1 1 0 1
    9 1 //affectedNode, k value

    Output: Not Possiable

Input 2:
9 11
1 2
1 4
1 6
2 3
3 9
4 5
5 9
6 7
7 8
8 9
4 7
1 1 0 0 1 1 1 1 1
4 0
output: 4

Input 3:
10 13
1 2
1 4
1 6
2 3
3 10
4 5
5 10
6 7
7 8
8 10
1 9
9 10
4 7
1 1 0 1 1 1 0 1 1 1
7 2
Not Possiable


Input 4:
10 13
1 2
1 4
1 6
2 3
3 10
4 5
5 10
6 7
7 8
8 10
1 9
9 10
4 7
1 1 0 1 1 1 0 1 1 1
7 1
Output: 2


Hard Version Question:
0 and 1 nodes still exists but G number of nodes(whose value is 0) are having virus of size “K”

Hard Version Input
12 16 ------------------------> number of node, number of edges
1 2
1 4
1 7
2 3
2 10
10 3
10 11
11 12
4 5
5 6
5 8
6 12
7 8
8 9
9 12
3 12
1 1 1 1 0 1 1 1 1 0 1 1
2 5 10 ------------------------------> NumberOfAffectedNode,[which node are damaged,here 5 and 10 are damaged node]
1 -----------------------------------> value of K
output: Not Possiable


12 16  ------------------------> number of node, number of edges
1 2
1 4
1 7
2 3
2 10
10 3
10 11
11 12
4 5
5 6
5 8
6 12
7 8
8 9
9 12
3 12
1 1 1 1 0 1 1 1 1 0 1 1
2 5 10 ------------------------------> NumberOfAffectedNode,[which node are damaged,here 5 and 10 are damaged node]
0  -----------------------------------> value of K
output: 3
* */
public class FindShortestDistance_Infected_Nodes_Atlassian_OA {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);

        //Read n & m
        int n=scanner.nextInt();//number of node
        int m=scanner.nextInt();//number of Edges


        int[] gValue = new int[n+1]; // intialising the graph value
        int[] level =new int[n+1];
        boolean[] visited= new boolean[n+1];

        Arrays.fill(level,Integer.MAX_VALUE); // intialising level

        //Creating the adjacency list
        List<List<Integer>> adjList=new ArrayList<>();
        for(int i=0;i<=n;i++){
            adjList.add(new ArrayList<>());
        }

        // creating the edge for undirected graph
        for(int i=0;i<m;i++){
            int u=scanner.nextInt();
            int v=scanner.nextInt();
            addEdge(adjList,u,v);
        }

        //assigning the value to graph
        for(int i=1;i<=n;i++){
            gValue[i]=scanner.nextInt();
        }

        /*

            //Easy Version
            findShortestDistanceEasyVersion(1,gValue,visited,level,adjList,n);

            //Medium Version
            int effectedNode=scanner.nextInt();
            int k=scanner.nextInt();
            findShortestDistance_FollowUp(effectedNode,k,gValue,visited,level,adjList,n);
        */

        //Hard Version
        int numberOfAffectedNode=scanner.nextInt(); // number of affected node
        int[] damagedNodes= new int[numberOfAffectedNode];
        for(int i=0;i<damagedNodes.length;i++){
            damagedNodes[i]=scanner.nextInt();
            addEdge(adjList,damagedNodes[i],0);//considering 0 node is an universal node and creating the edge
        }

        int k1=scanner.nextInt();
        k1=k1+1;

        findShortestDistance_FollowUp(0,k1,gValue,visited,level,adjList,n); // 0 is my univeral node connecting all the affected nodes

        scanner.close();
    }



    private static void addEdge(List<List<Integer>> adjList,int u,int v){
        adjList.get(u).add(v);
        adjList.get(v).add(u);
    }
    private static void findShortestDistanceEasyVersion(int src,int[] gValue,boolean[] visited,int[] level,  List<List<Integer>> adjList,int n){
        Queue<Integer> q=new LinkedList<>();
        if(gValue[src]==1){
            q.add(src);
            visited[src]=true;
            level[src]=0;
        }

        while(!q.isEmpty()){
            int currNode=q.poll();
            for(int nbr:adjList.get(currNode)){
                if(!visited[nbr]){
                    if(gValue[nbr]==1){
                       q.add(nbr);
                       visited[nbr]=true;
                       level[nbr]=Math.min(level[nbr],level[currNode]+1);
                    }else{
                        level[nbr]=Integer.MAX_VALUE;
                    }
                }
            }
        }

        if (level[n] == Integer.MAX_VALUE) {
            System.out.println("Not Possiable");
        } else {
            System.out.println(level[n]);
        }
    }


    private static void findShortestDistance_FollowUp(int src,int k,int[] gValue,boolean[] visited,int[] level,  List<List<Integer>> adjList,int n){

        Queue<Integer> q=new LinkedList<>();
        if(gValue[src]==0){
            q.add(src);
            visited[src]=true;
            level[src]=0;
        }

        while(!q.isEmpty()){
            int currNode=q.poll();
            if(currNode==10 || currNode==5){
                System.out.println(" ");
            }
            for(int nbr:adjList.get(currNode)){
                if(!visited[nbr]){
                    if((1+level[currNode])<=k){
                        q.add(nbr);
                        visited[nbr]=true;
                        level[nbr]=Math.min(level[nbr],level[currNode]+1);
                        gValue[nbr]=0;
                    }
                }
            }
        }

        //Doing BFS again from src node 1 after the affected node is updated
        visited= new boolean[n+1];
        level=new int[n+1];
        Arrays.fill(level,Integer.MAX_VALUE);
        findShortestDistanceEasyVersion(1,gValue,visited,level,adjList,n);
    }

}