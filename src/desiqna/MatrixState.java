package desiqna;

import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

public class MatrixState {
    public static void main(String[] args) {
        /**Problem Link:
         *
          https://docs.google.com/document/d/1l7kysN9GKCY-Ti8OLrJGRopA2mPUeEoKAJQj57AzIWY/edit?tab=t.0
         *
         * **/
        countNumberOfChangedState();
    }

    private static void countNumberOfChangedState(){
        Scanner scanner=new Scanner(System.in);
        int row=scanner.nextInt();
        int col=scanner.nextInt();
        long[][]mat=new long[(int)row+1][(int)col+1];
        for(int i=1;i<mat.length;i++){
            for(int j=1;j<mat[0].length;j++){
                long num=scanner.nextLong();
                mat[i][j]=num;
            }
        }

        HashMap<Long,Long> rowMap=new HashMap<>();
        HashMap<Long,Long> colMap=new HashMap<>();
        HashMap<Pair,Long> fMap=new HashMap<>();
        int numberOfQuery=scanner.nextInt();
        for(int i=0;i<numberOfQuery;i++){
            long a=scanner.nextLong();
            long b=scanner.nextLong();
            rowMap.put(a,rowMap.getOrDefault(a,0L)+1);
            colMap.put(b,colMap.getOrDefault(b,0L)+1);
            Pair pair=new Pair(a,b);
            fMap.put(pair,fMap.getOrDefault(pair,0L)+1);
        }

        long changed=0;
        for(int i=1;i<=row;i++){
            for(int j=1;j<=col;j++){

                long g= rowMap.getOrDefault((long)i,0L)-fMap.getOrDefault(new Pair((long)i,(long)j),0L);
                long p= colMap.getOrDefault((long)j,0L)-fMap.getOrDefault(new Pair((long)i,(long)j),0L);
               if((g+p)%2!=0){
                   changed++;
               }
            }
        }
        long res=((long) row *col)-changed;
        System.out.println(res);
    }
    static class Pair{
        long num1;
        long num2;
        Pair(long num1,long num2){
            this.num1=num1;
            this.num2=num2;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return num1 == pair.num1 && num2 == pair.num2;
        }

        @Override
        public int hashCode() {
            return Objects.hash(num1, num2);
        }
    }

}

//4 3
//        1 0 0
//        1 1 1
//        0 1 1
//        0 0 1
//        3
//        1 2
//        4 1
//        3 3