package desiqna;

import java.util.Arrays;
import java.util.Scanner;
/*
Doc Link :  https://docs.google.com/document/d/1uCxkybzWGEynChSBZHXxLiJ0BHi--7nLVUBkxGV2Vgg/edit?tab=t.0
Doc Video: https://drive.google.com/file/d/1Lbru2gb8MBSu6CkCQDMuF8lbR2nY-HAr/view
Understanding :-> Given a string :-> remove any 1 char and put it at any other position; final string should be lexicographically the smallest

String is lexicographically the smallest only when its starting characters are as small as possible.

-> “s” and “ideal” = sort(s)
-> travel both from left to right -> when you find id = s[i]!=ideal[i]

-> you need to make sure that put ideal[i] at ith position. Search for char z = ideal[i] -> in the range [i……n-1] and pick it up

* */
public class Flipkart_OA_14April {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int t=scanner.nextInt();
        System.out.println("Enter the String");
        String[]outputs=new String[t];
        int c=0;
        while(t-->0){
            String str=scanner.next();
            String ans=getLexoString(str);
            outputs[c++]=ans;
        }
        System.out.println("Outputs:");
        Arrays.stream(outputs).forEach((res)-> System.out.println(res));

        scanner.close();
    }
    private static String getLexoString(String s){
        if(s==null || s.length()==0) return "";
        int n=s.length();

        char[] ch=s.toCharArray();
        Arrays.sort(ch);
        String sortedStr=new String(ch);

        int idx=-1;
        char mismatch=' ';
        boolean isNextCharSame=false;
        for(int i=0;i<n;i++){
            if(s.charAt(i)!=sortedStr.charAt(i)){
                idx=i;
                mismatch=sortedStr.charAt(i);
                 if(i+1<n && s.charAt(i+1)==sortedStr.charAt(i)) isNextCharSame=true;
                break;
            }
        }

        int newIdx=-1;
        for(int i=idx;i<n;i++){
            if(s.charAt(i)==mismatch){
                newIdx=i;
            }
        }

        ch=s.toCharArray();
        if(idx != -1 && newIdx !=-1){
            char c=ch[idx];
            ch[idx]=ch[newIdx];
            ch[newIdx]=c;
        }
        String ans1=new String(ch);

        if(!isNextCharSame){
            return ans1;
        }

        char[] ch2=s.toCharArray();

           if(idx != -1 && isNextCharSame){
              for(int i=idx;i<n-1;i++){
                  if(ch2[i]>=ch2[i+1]){
                      swap(ch2,i,i+1);
                  }else {
                      break;
                  }
              }
           }
        String ans2=new String(ch2);
        return ans2;

    }
    public static void swap(char[] ch,int i,int j){
        char tmp=ch[i];
        ch[i]=ch[j];
        ch[j]=tmp;
    }
}
