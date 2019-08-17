/* 17.18 Shortest Supersequence: You are given two arrays, one shorter (with all distinct elements) and one
longer. Find the shortest subarray in the longer array that contains all the elements in the shorter
array. The items can appear in any order.
EXAMPLE
Input: {1, 5, 9} | {7, 5, 9, 0, 2, 1, 3, 5, 7, 9, 1, 1, 5, 8, 8, 9, 7}
Output: [7, 10] */

/*
1. one way to solve this is, for each elements of the shorter array, to interate the longer array (backwards) and record the 'next' position where each element would show. Take the maximum among all the elements (shorter array) as the closure for each column, then calculate the difference between the closure and its corresponding index, the pair with the minimum difference is the start and end position of the result.
Runtime O(SL), space is O(L) where S is the length of the shorter array and L is the length of the longer array.

2. A better way is to use queues to record all the positions that each element of the shorter array shows in the longer array. Initially, we take the min of each queue and add them into a min-heap (priority queue) based on their position in the longer array. One thing needs to be noticed here is that the elements in the heap shoule hold both the position in the shorter array (to know which element it is) and the position in the longer array (to calculate the distance). The min and max position is the start and end position of the subarray containing all elements. Next, we remove the min from the heap and add the next min from its queue which is the next position this element shows, check the distance and update the result if it is shorter than the previous one. Runtime O(L log S) -- going through L elements, and O( log 5) time to insert/remove from the heap).
*/

import java.util.*;

public class ShortestSupersequence{
    private int[] findShortestSupersequence(int[] shorter, int[] longer){
        //build the queues for each element
        ArrayList<Queue<Integer>> positionsInLonger = findPositionsInLonger(shorter, longer);
        return findShortestSubarray(positionsInLonger);
    }

    //for each element in shorter, find all the positions this element shows in longer
    private ArrayList<Queue<Integer>> findPositionsInLonger(int[] shorter, int[] longer){
        HashMap<Integer,Queue<Integer>> map = new HashMap<>();
        for (int i : shorter){
            Queue<Integer> q = new LinkedList<>();
            map.put(i,q);
        }
        for (int j = 0; j < longer.length; j++){
            if (map.containsKey(longer[j])){
                map.get(longer[j]).add(j);
            }
        }
        ArrayList<Queue<Integer>> res = new ArrayList<>();
        res.addAll(map.values());
        return res;
    }

    //find shortest subarray
    private int[] findShortestSubarray(ArrayList<Queue<Integer>> positionsInLonger){
        if (positionsInLonger == null){
            return null;
        }
        int[] res = new int[2]; // record the start and end position
        PriorityQueue<HeapNode> pq = new PriorityQueue<>(new HeapNodeComparator());
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < positionsInLonger.size(); i++){ //add the first of each queue into the heap
            int next = positionsInLonger.get(i).remove();
            pq.add(new HeapNode(i,next));
            max = Math.max(max, next);
        }

        int min = pq.peek().posInLonger;
        res[0] = min;
        res[1] = max;

        while(true){
            //remove the current min and get its queue for the next position
            HeapNode node = pq.poll();
            Queue<Integer> q = positionsInLonger.get(node.posInShorter);

            //check if we found a shorter subarray
            min = node.posInLonger;
            if (max - min < res[1] - res[0]){
                res[0] = min;
                res[1] = max;
            }

            if (q.size() == 0){
                break;
            }
            node.posInLonger = q.remove(); //update to the next position
            pq.add(node);
            max = Math.max(max, node.posInLonger);
        }
        return res;
    }

    private class HeapNode{
        public int posInShorter;
        public int posInLonger;

        public HeapNode(int s, int l){
            posInShorter = s;
            posInLonger = l;
        }
    }

    private class HeapNodeComparator implements Comparator<HeapNode>{
        public int compare(HeapNode n1, HeapNode n2){
            return n1.posInLonger - n2.posInLonger;
        }
    }

    public static void main(String args[]){
        ShortestSupersequence ss = new ShortestSupersequence();
        int[] s = {1,5,9};
        int[] l = {7, 5, 9, 0, 2, 1, 3, 5, 7, 9, 1, 1, 5, 8, 8, 9, 7};

        int[] res = ss.findShortestSupersequence(s,l);
        for (int i : res){
            System.out.println(i);
        }
    }
}

