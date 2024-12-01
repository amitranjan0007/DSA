package desiqna;

import java.util.HashMap;
import java.util.Scanner;

/*

Example:1
String S="abcdfd";
String R="abcdeg";
output-2

Example:2
abcd
best
output : 3


Example:3
abca
aaad
output : 2

a->2   a->3
b->1   d->1
c->1


Example:5
xyzpq
aabcd
output : 5
x->1  a->2
y->1  b->1
z->1  c->1
p->1  d->1
q->1

Observation :-> Because of swapping you can convert string “S” to
any permutation you want -> 0 cost -> only frequency of characters
matter in string “S.”
> S can be converted to any permutation which has maximum
matching with R in 0
-> Make hashmap s -> storing frequency of all characters in “S”;
-> Make hashmap r -> storing frequency of all characters in “R”
-> char =’a’ to ‘z’
-> if s[char]>r[char]
-> cost = cost + abs(s[char]-r[char])
**/
public class StringTransformationCost {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        System.out.println("(Input Equal Length of String as per constraint)");
        System.out.println("Input First String");
        String str1=scanner.next();
        System.out.println("Input Second String");
        String str2=scanner.next();
        if(str1.length()!=str2.length()) {
            System.out.println("result "+0);
            return;
        }
        int cost=findMinCost(str1,str2);
        System.out.println("result "+cost);

    }
    private static int findMinCost(String str1,String str2){
        int cost=0;
        HashMap<Character,Integer> freqMapA= new HashMap<>();
        HashMap<Character,Integer> freqMapB= new HashMap<>();

        for(char c:str1.toCharArray()){
            freqMapA.put(c,freqMapA.getOrDefault(c,0)+1);
        }

        for(char c:str2.toCharArray()){
            freqMapB.put(c,freqMapB.getOrDefault(c,0)+1);
        }

        for(char c:freqMapA.keySet()){
           int freqA= freqMapA.getOrDefault(c,0);
           int freqB= freqMapB.getOrDefault(c,0);
           if(freqA>freqB)
            cost+=freqA-freqB;
        }
        return cost;

    }
}