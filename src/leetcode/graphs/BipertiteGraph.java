package leetcode.graphs;

import java.util.*;

//Bipertite Graph-> No two adjacent have same color
/*

8 8
1 2
2 3
2 6
3 4
6 5
4 7
7 8
4 5
false

* */
public class BipertiteGraph {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();
        int m=scanner.nextInt();
        List<List<Integer>> adj=new ArrayList<>();


        int[] color=new int[n+1];
        Arrays.fill(color,-1);

        for(int i=0;i<=n;i++){
            adj.add(new ArrayList<>());
        }

        for(int i=0;i<m;i++){
            int u=scanner.nextInt();
            int v=scanner.nextInt();
            createNode(adj,u,v);
        }


//       for(int i=1;i<=n;i++){
//           if(color[i]==-1){
//               if(!isBapertiteBFS(i,adj,color)){
//                   System.out.println("false");
//                   return;
//               }
//           }
//       }

        for(int i=1;i<=n;i++){
            if(color[i]==-1){
                if(!isBapertiteDFS(i,adj,color,0)){
                    System.out.println("false");
                    return;
                }
            }
        }
        System.out.println(true);
    }
    private static boolean isBapertiteDFS(int src,List<List<Integer>> adj,int[] color,int parentColor){
        color[src]=parentColor;
        for(int nbr:adj.get(src)){
            if(color[nbr]==-1){
                if(!isBapertiteDFS(nbr,adj,color,1-parentColor)) return false;
            }else if(color[nbr]==color[src]){
                //cycle
                return false;
            }
        }
        return true;
    }

    private static boolean isBapertiteBFS(int src,List<List<Integer>> adj,int[] color){

        Queue<Integer> q=new LinkedList<>();
        q.add(src);
        color[src]=0;
        while(!q.isEmpty()){
            int currNode=q.poll();
            for(int nbr:adj.get(currNode)){
                if(color[nbr]==-1){
                    q.add(nbr);
                    color[nbr]=1-color[currNode];
                }else if(color[nbr]==color[currNode]){
                    //cycle
                    return false;
                }
            }
        }
        return true;
    }

    private static void createNode(List<List<Integer>> adj,int u,int v){
        adj.get(u).add(v);
        adj.get(v).add(u);
    }
}
