package desiqna;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
/*
  Reference Link For Question:
  https://docs.google.com/document/d/1ZCCWy2qKpUqkEqHRJ1PdzxNHyXejRFWFsHG9hSMwdj0/edit?tab=t.0

  Understanding:
  You are given a binary matrix
  find the row with maximum frequency - print that maximum frequency

  Approach:
  You can change the rows <= K -> in 1 operation you can flip the row.
  If you do it optimally now what will be the maximum frequency of a particular row?
  Make hashmap of string storing each row with its frequency
  Each string in the hashmap try to make it the maximum answer and see what maximum answer you can achieve.

  Example 1:

  input:
  1 0 1 0
  1 0 1 0
  0 1 0 1
  0 1 0 1
  0 1 0 1

  output- 5

    map : 1010- 2
          0101- 3

    K=2
    so when we flip first row 1010->0101,but 0101 frequency is 3 & we have K=2 so atmost 2 row we can flip

    K<=flippedStringFreq  =>> frequency+K
    K>flippedStringFreq ==>frequency+flippedStringFreq

    iteration 1: max = 2+2=4
    iteration 2: we flip row 2(i.e 0101) to 1010 max = 3+2=5

     so o/p 3+2=5

 */

/*
 * TC: O(n*m)
 * Sc: O(n*m)
 * */
public class StringFlipping_GoogleOA_30Dec {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter number of Flipping allowed");
        int K=scanner.nextInt();//
        System.out.println("Enter number of rows");
        int n=scanner.nextInt();//row
        System.out.println("Enter number of columns");
        int m=scanner.nextInt();//col
        int[][] matrix=new int[n][m];

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                matrix[i][j]=scanner.nextInt();
            }
        }
        int maxRow=findMaximumRow(matrix,K);
        System.out.println("result "+maxRow);

    }

    private static int findMaximumRow(int[][] matrix,int K){
        int maxi=Integer.MIN_VALUE;
        StringBuilder stringBuilder;
        HashMap<String,Integer> freqMap=new HashMap<>();
        for(int i=0;i<matrix.length;i++){
            stringBuilder=new StringBuilder();
            for(int j=0;j<matrix[0].length;j++){
                stringBuilder.append(matrix[i][j]);
            }
            String key = stringBuilder.toString();
            freqMap.put(key,freqMap.getOrDefault(key,0)+1);
        }

        for(Map.Entry<String,Integer> entry:freqMap.entrySet()){
            String originalString=entry.getKey();
            int frequency=entry.getValue();
            String flipped = flippedString(originalString);
            int flippedStringFreq=freqMap.getOrDefault(flipped,0);

            if(K<=flippedStringFreq){
                maxi=Math.max(maxi,frequency+K);
            }else{
                maxi=Math.max(maxi,frequency+flippedStringFreq);
            }
        }
        return maxi;
    }

    private static String flippedString(String originalStr){
        StringBuilder sb=new StringBuilder();

        for(char c:originalStr.toCharArray()){
            char character= c=='0'?'1':'0';
            sb.append(character);
        }
        return sb.toString();
    }

}
