package leetcode.graphs;

import java.util.*;

/*
https://www.desiqna.in/16206/paypal-oa-swe-intern-stipend-1-lakh-per-month-december-2023
https://docs.google.com/document/d/1kRBOhjUzUQCHwJ_QuNG3d2V6AEgjzbMuBkZutIsVzcM/edit?tab=t.0


N = 3 ;
M = 3;

-> [0 1 2]
-> [0 2 1]
-> [1 2 0]

  Storing the freq
  0->1 ,1->2
  0->2, 2->2
  1->2, 2->0

  mat==>
    0  1  2
  [
0      1  1
1   1  1  2
2      1
  ]

  mat[0][1]=1
  mat[0][2]=1
  mat[1][0]=1
  mat[1][1]=1
  mat[1][2]=2
  mat[2][1]=1

   mat[0][1]=1==> what it means ? 0 is have fre

5 4
0 1 3 2
0 2 3 1
1 0 3 2
2 1 0 3
0 3 1 2
* */
public class RankTheSong_TopoSort {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();// here n is number of list
        int m = scanner.nextInt();// number of node
        int[][] count = new int[n][m];
        int[] in = new int[m];
        List<Integer> ans = new ArrayList<>();
        int maxNode = -1;
        for (int i = 0; i < n; i++) {
            int[] b = new int[m];
            for (int j = 0; j < m; j++) {
                b[j] = scanner.nextInt();
                //maxNode = Math.max(maxNode, b[j]);
            }
            for (int i1 = 0; i1 < m - 1; i1++) {
                for (int j1 = i1 + 1; j1 < m; j1++) {
                    int x = b[i1];
                    int y = b[j1];
                    count[x][y]++;
                }
            }
        }
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            adjList.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            for (int j = i+1; j < m; j++) {
                if (count[i][j] > n / 2) {
                    adjList.get(i).add(j);
                    in[j]++;
                } else if (n % 2 == 0) {
                    if (count[i][j] == n / 2) {
                        adjList.get(i).add(j);
                        in[j]++;
                    } else {
                        adjList.get(j).add(i);
                        in[i]++;
                    }
                } else {
                    adjList.get(j).add(i);
                    in[i]++;
                }
            }
        }
      // in = new int[]{0, 1, 3, 2};
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < m; ++i) {
            if (in[i] == 0) {
                q.add(i);
            }
        }

        while (!q.isEmpty()) {
            int current = q.poll();
            System.out.print(current + " ");

            for (int neighbor : adjList.get(current)) {
                in[neighbor]--;
                if (in[neighbor] == 0) {
                    q.add(neighbor);
                }
            }
        }
        scanner.close();
    }


}
