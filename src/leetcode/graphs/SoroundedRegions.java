package leetcode.graphs;

public class SoroundedRegions {
    public static void main(String[] args) {
        char[][] mat={{'X','X','X','X'},{'X','O','O','X'},{'X','X','O','X'},{'X','O','X','X'}};
        solve(mat);
    }
    public static void solve(char[][] board) {
        int n= board.length;
        int m=board[0].length;
        int left=0;
        int right=m-1;
        int top=0;
        int bottom=n-1;
        boolean[][] visited=new boolean[n][m];
        int[] dRow={0,1,0,-1};
        int[] dCol={1,0,-1,0};

        //visit first row
        while(left<=right){
            if(board[top][left]=='O'){
                dfs(top,left,board,visited,dRow,dCol);
            }
            left++;
        }

        left=0;
        //visit last row
        while(left<=right){
            if(board[bottom][left]=='O'){
                dfs(bottom,left,board,visited,dRow,dCol);
            }
            left++;
        }

        //visit first col
        left=0;
        while(top<=bottom){
            if(board[top][left]=='O'){
                dfs(top,left,board,visited,dRow,dCol);
            }
            top++;
        }

        top=0;
        //visit last col
        while(top<=bottom){
            if(board[top][right]=='O'){
                dfs(top,right,board,visited,dRow,dCol);
            }
            top++;
        }


        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(board[i][j]=='O' && !visited[i][j]){
                    board[i][j]='X';
                }
            }
        }

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }
    }

    private static void dfs(int row,int col,char[][] board,boolean[][] visited,int[]dRow,int[]dCol){
        int n= board.length;
        int m=board[0].length;
        visited[row][col]=true;
        for(int i=0;i<4;i++){
            int nRow=row+dRow[i];
            int nCol=col+dCol[i];
            if(isSafe(nRow,nCol,n,m,board,visited)){
                dfs(nRow,nCol,board,visited,dRow,dCol);
            }
        }
    }
    private static  boolean isSafe(int r,int c,int n,int m,char[][] board,boolean[][] visited){
        return r>=0 && r<n && c>=0 && c<m && board[r][c]=='O' && !visited[r][c];
    }
}
