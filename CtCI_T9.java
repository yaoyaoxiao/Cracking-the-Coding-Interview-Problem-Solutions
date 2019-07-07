/* 16.20 T9: On old cell phones, users typed on a numeric keypad and the phone would provide a list of words
that matched these numbers. Each digit mapped to a set of 0 - 4 letters. Implement an algorithm
to return a list of matching words, given a sequence of digits. You are provided a list of valid words
(provided in whatever data structure you'd like). The mapping is shown in the diagram below:

1		2		3
		abc		def
4		5		6
ghi		jkl		mno
7		8		9
pqrs	tuv		wxyz
		0


EXAMPLE
Input: 8733
Output:tree, used  */


/*
1. build a number to words hash table which map a sequence of digits to a list of valid words that this number can generate
2. with the above hash table, we can simply look up the key (the digits string) and return the list of words it mapped to.
3. another way but also worse way is to build a Trie so that we can "short circuit" when the prefix is invalid already.
*/


import java.util.ArrayList;
import java.util.HashMap;

public class T9{

    static char[][] DigitToLetters = {{}, {}, {'a','b','c'}, {'d','e','f'}, {'g','h','i'}, {'j','k','l'}, {'m','n','o'}, {'p','q','r','s'}, {'t','u','v'}, {'w','x','y','z'}};

    private static ArrayList<String> getT9Words(String digits, HashMapList<String,String> NumWordsMap){
        return NumWordsMap.get(digits);
    }

    //build the Num-Words map
    private static HashMapList<String,String> buildNumWordsMap(String[] words){
        HashMapList<String,String> res = new HashMapList<>();
        HashMap<Character, Character> letterToDigit = buildLetterToDigitMap();
        for (String w : words){
            String numForWord = getDigitsForWord(w, letterToDigit);
            res.put(numForWord,w);
        }
        return res;
    }

    private static String getDigitsForWord(String w, HashMap<Character, Character> letterToDigit){
        StringBuilder sb = new StringBuilder();
        for(char chr : w.toCharArray()){
            if (letterToDigit.containsKey(chr)){
                sb.append(letterToDigit.get(chr));
            }
        }
        return sb.toString();
    }

    //build the letter-digit hash table
    private static HashMap<Character,Character> buildLetterToDigitMap(){
        HashMap<Character,Character> res = new HashMap<>();
        for (int i = 0; i < DigitToLetters.length; i++){
            char[] chrs = DigitToLetters[i];
            if (chrs != null){
                for (char chr : chrs){
                    res.put(chr, Character.forDigit(i,10));
                }
            }
        }
        return res;
    }

    public static void main(String[] args){
        String[] words = {"tree", "used", "ted","map","cat","hope","upper"};
        HashMapList<String,String> map = buildNumWordsMap(words);
        ArrayList<String> list = getT9Words("8733", map);
        for (String str : list) {
            System.out.println(str);
        }
    }
}
