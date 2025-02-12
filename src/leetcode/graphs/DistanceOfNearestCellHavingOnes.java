package leetcode.graphs;

import java.util.LinkedList;
import java.util.Queue;


//Problem: https://www.geeksforgeeks.org/problems/distance-of-nearest-cell-having-1-1587115620/1
// Observation - put all ones in queue and perform BFS
public class DistanceOfNearestCellHavingOnes {
    public static void main(String[] args) {
        int[][] mat={{1,0,1},{1,1,0},{1,0,0}};
        int[][] res=nearest(mat);
        for(int[] r:res) {
            for (int num : r) {
                System.out.print(num + " ");

            }
            System.out.println();
        }
    }
    public static int[][] nearest(int[][] grid)
    {
        // Code here
        int n=grid.length;
        int m=grid[0].length;
        boolean[][] visited=new boolean[n][m];
        int[][] res=new int[n][m];
        Queue<int[]> q=new LinkedList<>();
        int[] dRow={0,1,0,-1};
        int[] dCol={1,0,-1,0};

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(grid[i][j]==1){
                    q.add(new int[]{i,j,0});
                    visited[i][j]=true;
                }
            }
        }

        while(!q.isEmpty()){
            int[] triplet=q.poll();
            int row=triplet[0];
            int col=triplet[1];
            int level=triplet[2];
            res[row][col]=level;
            for(int i=0;i<4;i++){
                int nRow=row+dRow[i];
                int nCol=col+dCol[i];
                if(isSafe(nRow,nCol,n,m,grid,visited)){
                    visited[nRow][nCol]=true;
                    q.add(new int[]{nRow,nCol,level+1});
                }
            }

        }
        return res;
    }
    static boolean  isSafe(int r,int c,int n,int m, int[][]grid,boolean[][] visited){
        return r>=0 && r<n && c>=0 && c<m && grid[r][c]==0 && !visited[r][c];
    }
}
