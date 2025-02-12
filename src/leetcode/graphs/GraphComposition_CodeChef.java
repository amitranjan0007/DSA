package leetcode.graphs;

import java.util.*;
/*
https://codeforces.com/contest/2060/problem/E
https://drive.google.com/file/d/1yN_B7mlj8IAqPmsaIxoItf9lRTGqqy3W/view
* */
public class GraphComposition_CodeChef {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        while (t-- > 0) {
            int n = scanner.nextInt();
            int m1 = scanner.nextInt();//edge in F
            int m2 = scanner.nextInt();//edge in G
            List<int[]> fGraphEdge = new ArrayList<>();//hold the edge of F
            List<List<Integer>> adjF = new ArrayList<>();
            List<List<Integer>> adjG = new ArrayList<>();
            boolean[] visitedF = new boolean[n + 1];
            boolean[] visitedG = new boolean[n + 1];

            for (int i = 0; i <= n; i++) {
                adjF.add(new ArrayList<>());
                adjG.add(new ArrayList<>());
            }

            //Hold the Edge of F
            for (int i = 0; i < m1; i++) {
                int u = scanner.nextInt();
                int v = scanner.nextInt();
                fGraphEdge.add(new int[]{u, v});
            }

            //Create a Graph of G
            for (int i = 0; i < m2; i++) {
                int u = scanner.nextInt();
                int v = scanner.nextInt();
                createEdge(adjG, u, v);
            }

            //Fill The Node with Component Belongs To
            int[] componentG = new int[n + 1];
            //   Arrays.fill(componentG,-1);
            int count = 0;
            for (int i = 1; i <= n; i++) {
                if (!visitedG[i]) {
                    count++;
                    dfs(i, adjG, visitedG, componentG, count);
                }
            }

            int numberOfOperation = 0;
            for (int[] edge : fGraphEdge) {
                int u = edge[0];
                int v = edge[1];
                if (componentG[u] == componentG[v]) {
                    createEdge(adjF, u, v);
                } else {
                    numberOfOperation++;
                }
            }

            HashMap<Integer, Integer> map = new HashMap<>();
            for (int i = 1; i <= n; i++) {
                if (!visitedF[i]) {
                    int fNodeComponent = componentG[i];
                    map.put(fNodeComponent, map.getOrDefault(fNodeComponent, 0) + 1);
                    dfs(i, adjF, visitedF);
                }
            }

            for (int value : map.values()) {
                if (value >= 1) {
                    numberOfOperation += (value - 1);
                }
            }
            System.out.println(numberOfOperation);


        }


    }
    private static void dfs ( int src, List<List<Integer>>adj,boolean[] visited, int[] gNodeComponent, int cId){
        visited[src] = true;
        gNodeComponent[src] = cId;
        for (int nbr : adj.get(src)) {
            if (!visited[nbr]) {
                dfs(nbr, adj, visited, gNodeComponent, cId);
            }
        }
    }

    private static void dfs ( int src, List<List<Integer>>adj,boolean[] visited){
        visited[src] = true;
        for (int nbr : adj.get(src)) {
            if (!visited[nbr]) {
                dfs(nbr, adj, visited);
            }
        }
    }
    private static void createEdge (List < List < Integer >> adj,int u, int v){
        adj.get(u).add(v);
        adj.get(v).add(u);
    }
}
