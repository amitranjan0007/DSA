package leetcode.graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
/*
Problem-10
https://www.desiqna.in/15921/microsoft-oa-september-2023-freshers-hiring-sde1-set-116
https://drive.google.com/file/d/1AFkv-cLfKeXCy3P_sGCvzGz1u7IA3YlS/view

7 5
0 1 2 4 5
1 2 3 5 6
output: 2


7 6
0 1 2 1 4 4
1 2 0 4 5 6
output: 2


Explanation:
0 1 2 4 5
1 2 3 5 6

Graph look like:
  0----1---2----3
  4--5---6

put a level - level will help us to track the time
in first round
node | level
0        0
3        0
4        0
6        0

Processing level 0 will take 1 second

Now visit it's nbr after decreasing the incoming edge :) like Topo Sort but it's not a topological sort(topo sort work in DAG)
if the nbr node become less then <=1 push into a queue for further processing by increasing it's level by 1 with respect to parent

in 2nd round

node | level | parent level was
1        1      0 (after deleting node 0)
2        1      0 (after deleting node 3)
5        1      0 (after deleting node 4)

remember when you delete node 6 then node 5 is already been visited by node 4,so we only decrease it's incoming edge of 5
not push into the queue again
so we check if already visited only decrease the edge ,if not visited then we decrease the edge and edge is less<=1  push
into a queue



* */
public class RequiredTimeToVerticesStopDisappear_TopoSort {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();
        int m=scanner.nextInt();
        ArrayList<ArrayList<Integer>> adjList=new ArrayList<>();
        for (int i=0;i<n;i++){
            adjList.add(new ArrayList<>());
        }

        int[] a=new int[m];
        int[] b=new int[m];
        for(int i=0;i<m;i++){
           a[i]=scanner.nextInt();
        }
        for(int i=0;i<m;i++){
            b[i]=scanner.nextInt();
        }

        int[] in=new int[n];
        for(int i=0;i<m;i++){
            int u=a[i];
            int v=b[i];
            adjList.get(u).add(v);
            adjList.get(v).add(u);
            in[u]++;
            in[v]++;
        }

        boolean[] visited=new boolean[n];
        int timeRequired=0;
        Queue<int[]> q=new LinkedList<>();
        for(int i=0;i<n;i++){
            if(in[i]<=1 && !visited[i]){
                timeRequired=1;
                visited[i]=true;
                q.add(new int[]{i,0});
            }
        }

        int prevLevel=0;
        while(!q.isEmpty()){
            int[] pair=q.poll();
            int src=pair[0];
            int level=pair[1];
            for(int nbr:adjList.get(src)) {
                if (!visited[nbr]) {
                    in[nbr]--;
                    if (in[nbr] <= 1) {
                        if(prevLevel!=level+1){
                            timeRequired++;
                        }
                        prevLevel=level + 1;
                        q.add(new int[]{nbr, level + 1});
                        visited[nbr] = true;
                    }
                }else{
                    in[nbr]--;
                }
            }

        }
        System.out.println(timeRequired);

    }
}
