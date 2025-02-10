package leetcode.graphs;

import java.util.LinkedList;
import java.util.Queue;


//Problem Link: https://leetcode.com/problems/rotting-oranges/
public class OrangeRotting {
    public static void main(String[] args) {
       int[][]mat={{2,1,1},{1,1,0},{0,1,1}};
        int res=orangesRotting(mat);
        System.out.println(res);
    }

    public static int orangesRotting(int[][] grid) {
        int n=grid.length;
        int m=grid[0].length;
        boolean[][] visited=new boolean[n][m];

        int[] dRow={0,1,0,-1};
        int[] dCol={1,0,-1,0};
        Queue<int[]> q = new LinkedList<>();

        int countFresh=0;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(grid[i][j]==2){
                    q.add(new int[]{i,j,0});
                }else if(grid[i][j]==1){
                    countFresh++;
                }
            }
        }

        if(countFresh==0) return 0;

        int currTime=0;
        while(!q.isEmpty()){
            int[] pair=q.poll();
            int row=pair[0];
            int col=pair[1];
            currTime=pair[2];
            for(int k=0;k<4;k++){
                int nRow=row+dRow[k];
                int nCol=col+dCol[k];
                if(isSafe(nRow,nCol,n,m,grid)){
                    q.add(new int[]{nRow,nCol,currTime+1});
                    grid[nRow][nCol]=2;
                    countFresh--;
                }
            }
        }
        return countFresh==0?currTime:-1;
    }


    public static boolean isSafe(int r,int c,int n,int m,int[][] grid){
        return r>=0 && r<n && c>=0 && c<m && grid[r][c]==1;
    }

}
