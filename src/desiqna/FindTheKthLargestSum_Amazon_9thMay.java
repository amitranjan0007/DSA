package desiqna;

import java.util.*;

public class FindTheKthLargestSum_Amazon_9thMay {
    /*
    Given two arrays A and B of size N;
    -> Consider all possible pairs (A[j],B[i])
    Find the pair with Kth largest sum.


     */
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();
        int k=scanner.nextInt();
        int[] a= new int [n+1];
        int[] b= new int[n+1];
        for(int i=1;i<=n;i++){
            a[i]=scanner.nextInt();
        }
        for(int i=1;i<=n;i++){
            b[i]=scanner.nextInt();
        }

        findKthLargestSumBruteForce(a,b,k,n);
        findKthLargestSumOptimal(a,b,k,n);
    }

    private static void findKthLargestSumBruteForce(int[] a, int[] b, int k, int n) {
        //Generate all the pair
        k--;
        int[] sumArr=new int[(n*n)+1];
        int c=0;
           for(int i=1;i<=n;i++){
               for (int j=1;j<=n;j++){
                   sumArr[c++]=a[i]+b[j];
               }
           }
        Arrays.sort(sumArr);
        System.out.println(sumArr[(sumArr.length)-k-1]);
    }

    private static void findKthLargestSumOptimal(int[] a, int[] b, int k, int n) {
        Arrays.sort(a);
        Arrays.sort(b);
        PriorityQueue<Entry> pq=new PriorityQueue<>((e1,e2)-> e2.sum- e1.sum);
        HashMap<Pair,Boolean> map=new HashMap<>();
        int sum=a[n]+b[n];
        Entry srcEntry=new Entry(sum,new Pair(n,n));
        pq.add(srcEntry);
        map.put(new Pair(n,n),true);

        int ans=-1;
        for(int i=0;i<k;i++){
            Entry topEntry=pq.poll();
            if(topEntry!=null){
                ans=topEntry.sum;
                int i1=topEntry.pair.i;
                int j1=topEntry.pair.j;

                if (i1 - 1 >= 0 && !map.containsKey(new Pair(i1 - 1, j1))) {
                    pq.add(new Entry(a[i1 - 1] + b[j1], new Pair(i1 - 1, j1)));
                    map.put(new Pair(i1 - 1, j1), true);
                }

                if (j1 - 1 >= 0 && !map.containsKey(new Pair(i1, j1 - 1))) {
                    pq.add(new Entry(a[i1] + b[j1 - 1], new Pair(i1, j1 - 1)));
                    map.put(new Pair(i1, j1 - 1), true);
                }
            }
        }
        System.out.println(ans);

    }


}
class Entry{
    int sum;
    Pair pair;
    Entry(int sum,Pair pair){
        this.sum=sum;
        this.pair=pair;
    }
}

class Pair{
    int i;
    int j;
    Pair(int i,int j){
        this.i=i;
        this.j=j;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair pair = (Pair) o;
        return i == pair.i && j == pair.j;
    }

    @Override
    public int hashCode() {
        return Objects.hash(i, j);
    }
}