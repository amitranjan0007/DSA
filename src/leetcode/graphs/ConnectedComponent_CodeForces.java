package leetcode.graphs;

import java.util.*;
/*
Problem: 7
https://drive.google.com/file/d/1U8J1tsXZvwQwjMr8DppCulP2rOKa_DgL/view
https://docs.google.com/document/d/1ZQ-42PSbmLRXeKRYpuTye11CqJvjKF_avIkK7WrWr0Y/edit?tab=t.0
Maximize the largest component
Easy version :- You can replace any cell by “#” utmost once. Please tell what will be the length of the largest component in that case.


Hard version :-Each row can be replaced by all “#”


**/
public class ConnectedComponent_CodeForces {
    private static int[] dRow={1,-1,0,0};
    private static int[] dCol={0,0,1,-1};
    public static void main(String[] args) {
        //char[][] mat={{'#','#','.'},{'.','.','.'},{'#','#','#'}};
        char[][] mat={{'#','#','.','#'},{'#','#','.','#'},{'.','.','.','#'},{'#','#','#','#'}};
        int n=mat.length;
        int m=mat[0].length;
        boolean[][] visited=new boolean[n][m];
        int[][] component=new int[n][m];


        int componentId=0;

        List<int[]> hashedEdgesContainsDot=new ArrayList<>();


        for(int i=0;i<n;i++){
            for (int j=0;j<m;j++){
                if(mat[i][j]=='.'){
                    hashedEdgesContainsDot.add(new int[]{i,j});
                }
            }
        }

        HashMap<Integer,Integer> storeComponentFreqMap=new HashMap<>();
        for(int i=0;i<n;i++){
            for (int j=0;j<m;j++){
                if(!visited[i][j] && mat[i][j]=='#'){
                    componentId++;
                    dfs(i,j,mat,visited,component,componentId,storeComponentFreqMap);
                }
            }
        }
        System.out.println(storeComponentFreqMap.values());



        for(int i=0;i<n;i++){
            for (int j=0;j<m;j++){
                System.out.print(component[i][j]+" ");
            }
            System.out.println();
        }


       int maxi=0;
        for(int i=0;i<n;i++){
            for (int j=0;j<m;j++){
                HashMap<Integer,Integer> countMap=new HashMap<>();
                if(component[i][j]==0){
                    for(int z=0;z<4;z++){
                        int nRow=i+dRow[z];
                        int nCol=j+dCol[z];
                        if(isSafe2(nRow,nCol,n,m,component)){
                            if(component[nRow][nCol]!=0){
                                int num=component[nRow][nCol];
                                int freq=storeComponentFreqMap.getOrDefault(num,0);
                                if(!countMap.containsKey(num)){
                                    countMap.put(num,freq);
                                }
                            }
                        }
                    }
                    maxi=Math.max(maxi,countMap.values().stream().mapToInt(Integer::intValue).sum()+1);
                }
            }
        }
        System.out.println(maxi);
    }

    private static void dfs(int row, int col,char[][] mat,boolean[][] visited,int[][] component,int componentId,HashMap<Integer,Integer> map){
          visited[row][col]=true;
          component[row][col]=componentId;
          map.put(componentId,map.getOrDefault(componentId,0)+1);
            int n=mat.length;
            int m=mat[0].length;

          for(int i=0;i<4;i++){
              int r=row+dRow[i];
              int c=col+dCol[i];
                if(isSafe(r,c,n,m,mat,visited)){
                    dfs(r,c,mat,visited,component,componentId,map);
                }
          }
    }

    static boolean isSafe(int r,int c,int n,int m,char[][] mat, boolean[][] visited){
        return r>=0 && r<n && c>=0 && c<m && mat[r][c]=='#' && !visited[r][c];
    }
    static boolean isSafe2(int r,int c,int n,int m,int[][] mat){
        return r>=0 && r<n && c>=0 && c<m && mat[r][c]!=0;
    }
}
