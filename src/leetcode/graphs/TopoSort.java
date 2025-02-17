package leetcode.graphs;

import java.util.*;

/*
4 5 -- n,m
0 1
1 2
3 2
0 1
1 3

Output: 0 1 3 2
 */
public class TopoSort {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();
        int m=scanner.nextInt();
        int[] in=new int[n];
        List<Integer> ans=new ArrayList<>();
        List<List<Integer>> adjList=new ArrayList<>();
        for(int i=0;i<n;i++){//As node is 1 based indexing
            adjList.add(new ArrayList<>());
        }
        for(int i=0;i<m;i++){// create edge and store in graph
            int u=scanner.nextInt();
            int v=scanner.nextInt();
            adjList.get(u).add(v);
            in[v]++;
        }
        Queue<Integer> q=new LinkedList<>();
        for(int i=0;i<n;i++){
            if(in[i]==0){
                q.add(i);
            }
        }

        while(!q.isEmpty()){
            int src=q.poll();//once you remove the src node ,remove it's edges to nbr
            ans.add(src);
              for(int nbr:adjList.get(src)){
                  in[nbr]--;
                    if(in[nbr]==0){
                        q.add(nbr);
                    }
              }
        }
        ans.forEach((num)->System.out.print(num+" "));
    }
}
