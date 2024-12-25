package leetcode.dynamicprogramming;

public class CVacation {
    /*
     Link: https://atcoder.jp/contests/dp/tasks/dp_c

      a1 b1 c1  === day1
      a2 b2 c2  === day2
      a3 b3 c3  === day3
      a4 b4 c4  === day4
      a5 b5 c5  === day5
      .
      .
      an bn cn  === day6

      Taro will choose one of the following activities and do it on the i-th day, have to find the maximum point

      Observation:
      means on day 1
      if he selected to do activity a1, the max point gained is a1
          Or
      if he selected to do activity b1, the max point gained is b1
         Or
      if he selected to do activity c1, the max point gained is c1

      if you have day 2
      if he selected to do activity a2, means he cannot select a1, but he would have select b1 or c1 , but you would take max(b1,c1)
         Or
      if he selected to do activity b2, means he cannot select b1, but he would have select a1 or c1 , but you would take max(a1,c1)
        Or
      if he selected to do activity c2, means he cannot select c1, but he would have select a1 or b1 , but you would take max(a1,b1)

      if you have day 3
      if he selected to do activity a3, means he cannot select a2, but he would have select b2 or c2 , but you would take max(b2,c2)
         Or
      if he selected to do activity b3, means he cannot select b2, but he would have select a2 or c2 , but you would take max(a2,c2)
        Or
      if he selected to do activity c3, means he cannot select c2, but he would have select a2 or b2 , but you would take max(a2,b2)

      dp[i] - Best possible answer till index i such that maximum point earned

      if I ask you,
      what is dpa[1] = Best possible answer till index 1, such that maximum point earned when activity 'A' is selected
      what is dpa[2] = Best possible answer till index 2 such that maximum point earned when activity 'A' is selected

      so let understand
      a1 b1 c1  === day1
      a2 b2 c2  === day2
      a3 b3 c3  === day3
      a4 b4 c4  === day4
      a5 b5 c5  === day5
      .
      .
      an bn cn  === day6

      dpa[1]=a[1] dpb[1]=b[1] dpc[1]=c[1]  ==> one day1 if we select any of the activity what is the max point you gain

      one day2 if we select any of the activity, what is the max point you gain
      dpa[2]=a[2]+max(b[1],c[1]) dpb[2]=b[2]+max(a[1],c[1])  dpc[2]=c[2]+max(a[1],b[1])

      one day3 if we select any of the activity, what is the max point you gain
      dpa[3]=a[3]+max(b[2],c[2]) dpb[3]=b[3]+max(a[2],c[2])  dpc[3]=c[3]+max(a[2],b[2])



      dpa[i]=a[i]+Math.max(dpb[i-1],dpc[i-1]);
      dpb[i]=b[i]+Math.max(dpa[i-1],dpc[i-1]);
      dpc[i]=c[i]+Math.max(dpa[i-1],dpb[i-1]);

      max total point at nth day?
       ans= max(dpa[n],dpb[n],dpc[n])

    * **/
    public static void main(String[] args) {
        int a[]={10,20,30};
        int b[]={40,50,60};
        int c[]={70,80,90};
        int res=maxPoint(a,b,c);
        System.out.println(res);
    }



    private static int maxPoint(int[]a,int[]b,int[]c){
        int n=a.length;
        int dpa[]=new int[a.length];
        int dpb[]=new int[b.length];
        int dpc[]=new int[c.length];

        dpa[0]=a[0];
        dpb[0]=b[0];
        dpc[0]=c[0];
        System.out.println("Day 1: dpa[0]=" + dpa[0] + ", dpb[0]=" + dpb[0] + ", dpc[0]=" + dpc[0]);
           for(int i=1;i<n;i++){
               dpa[i]=a[i]+Math.max(dpb[i-1],dpc[i-1]);
               dpb[i]=b[i]+Math.max(dpa[i-1],dpc[i-1]);
               dpc[i]=c[i]+Math.max(dpa[i-1],dpb[i-1]);
               System.out.println("Day " + (i + 1) + ": dpa[" + i + "]=" + dpa[i] + ", dpb[" + i + "]=" + dpb[i] + ", dpc[" + i + "]=" + dpc[i]);
           }
        return Math.max(dpa[n-1],Math.max(dpb[n-1],dpc[n-1]));
    }
}

/*
try this :
New Question.-> Same as above ; but you are allowed to take a maximum of only 2 consecutive numbers ; not more than that->(3 numbers should not be consecutive.)

* */
