/* 17.17 Multi Search: Given a string b and an array of smaller strings T, design a method to search b for
each small string in T. */

/*
1. A brute force way to solve this is to iterate T and search for each string in b (O(tsp) where t is the size of T, s is the length of the longest string in T, and b is the length of b)
2. we can build a trie on b and then search each string in T (O(b^2 + ts)) since build a trie on b takes O(b^2) time. this would be better than the first solution if T has a very big size.
3. The optimal way is build a trie on all strings of T and then search for each substring of b. This takes O(st) time to create the trie and O( bs) time to search for all the strings.
*/


import java.util.ArrayList;
import java.util.HashMap;

public class MultiSearch{
    public HashMapList<String,Integer> multiSearch(String b, String[] t){
        HashMapList<String,Integer> res = new HashMapList<>();
        TrieNode root = buildTrie(t).getRoot();

        for (int i = 0; i < b.length(); i++){
            ArrayList<String> strs = searchSubstrings(root,b,i);
            for(String s : strs){
                res.put(s,i); //HashMapList would handle the cases when res doesn't contains the key s
            }
        }
        return res;
    }

    public ArrayList<String> searchSubstrings(TrieNode root, String b, int idx){
        ArrayList<String> res = new ArrayList<>();
        int index = idx;
        while(index < b.length()){
            root = root.getChild(b.charAt(index));
            if (root == null) {
                break;
            }
            if (root.terminates()){ //found a whole string, add it to the arraylist
                res.add(b.substring(idx,index+1));
            }
            index++;
        }
        return res;
    }

    public Trie buildTrie(String[] strs){
        Trie t = new Trie("");
        for(String s : strs){
            t.insertString(s,0);
        }
        return t;
    }

    private class Trie{
        private TrieNode root = new TrieNode();
        public Trie(String s){
            insertString(s,0);
        }

        public void insertString(String s, int index){
            root.insertString(s,index);
        }
        public ArrayList<Integer> search(String s){
            return root.search(s);
        }

        public TrieNode getRoot(){
            return root;
        }
    }

    private class TrieNode{
        private HashMap<Character, TrieNode> children = new HashMap<>();
        private ArrayList<Integer> indexes = new ArrayList<>();
        private char data;

        public void insertString(String s, int index){
            indexes.add(index);
            if (s != null && s.length() > 0){
                data = s.charAt(0);
                TrieNode child = null;
                if (children.containsKey(data)){
                    child = children.get(data);
                }
                else{
                    child = new TrieNode();
                    children.put(data,child);
                }
                String remainder = s.substring(1);
                child.insertString(remainder, index+1);
            }
            else{
                children.put('\0',null); //terminating char
            }
        }

        public ArrayList<Integer> search(String s){
            if (s == null || s.length() == 0){
                return indexes;
            }
            else{
                char chr = s.charAt(0);
                if (children.containsKey(chr)){
                    String remainder = s.substring(1);
                    return children.get(chr).search(remainder);
                }
            }
            return null;
        }

        public TrieNode getChild(char chr){
            return children.get(chr);
        }

        public boolean terminates(){
            return children.containsKey('\0');
        }
    }

    public static void main(String args[]){
        MultiSearch ms = new MultiSearch();
        String b = "Multisearch search";
        String[] t = {"s", "sea","search","multi","ear","arch","ti","earth"};
        HashMapList<String,Integer> res = ms.multiSearch(b,t);
        for (String s : res.keySet()){
            System.out.print(s + ":");
            for (int i : res.get(s)){
                System.out.print(i + ",");
            }
            System.out.print("\n");
        }
    }
}