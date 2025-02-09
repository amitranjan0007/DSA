package leetcode.graphs;

import java.util.LinkedList;
import java.util.Queue;


//Problem Link: https://www.geeksforgeeks.org/problems/find-the-number-of-islands/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=find_the_number_of_islands
public class NumberOfIsland {
    public static void main(String[] args) {
        char[][]grid = {{'0','1'},{'1','0'},{'1','1'},{'1','0'}};
        int res=numIslands(grid);
        System.out.println(res);
    }
    public static int numIslands(char[][] grid) {
        // Code here
        int n=grid.length;
        int m=grid[0].length;
        boolean[][]visited=new boolean[n][m];


        int count=0;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(grid[i][j]=='1' && !visited[i][j]){
                    count++;
                    //bfs(i,j,grid,visited,n,m);
                    dfs(i,j,grid,visited,n,m);
                }
            }
        }
        return count;

    }
    private static void bfs(int i,int j,char[][] grid,boolean[][]visited,int n,int m){
        Queue<int[]> q=new LinkedList<>();
        q.add(new int[]{i,j});
        visited[i][j]=true;
        while(!q.isEmpty()){
            int[]pair=q.poll();
            int row=pair[0];
            int col=pair[1];
            for(int delRow=-1;delRow<=1;delRow++){
                for(int delCol=-1;delCol<=1;delCol++){
                    int nRow=row+delRow;
                    int nCol=col+delCol;
                    if(isSafe(n,m,nRow,nCol,grid,visited)){
                        visited[nRow][nCol]=true;
                        q.add(new int[]{nRow,nCol});
                    }
                }
            }
        }
    }

    private static void dfs(int row,int col,char[][] grid,boolean[][]visited,int n,int m){
        visited[row][col]=true;
        for(int delRow=-1;delRow<=1;delRow++) {
            for (int delCol = -1; delCol <= 1; delCol++) {
                int nRow=row+delRow;
                int nCol=col+delCol;
                if(isSafe(n,m,nRow,nCol,grid,visited)){
                    if(!visited[nRow][nCol]){
                        dfs(nRow,nCol,grid,visited,n,m);
                    }
                }
            }
          }
    }
    private static boolean  isSafe(int row,int col,int nRow,int nCol,char[][] grid,boolean[][]visited){
        return nRow>=0 && nRow<row && nCol>=0 && nCol<col && grid[nRow][nCol]=='1' && !visited[nRow][nCol];
    }
}
