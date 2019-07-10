/* 17.2 Shuffle: Write a method to shuffle a deck of cards. It must be a perfect shuffle-in other words, each
of the 52! permutations of the deck has to be equally likely. Assume that you are given a random
number generator which is perfect. */

/*
1. while interating through the array, we can use the random number generator to get a random number, k, which represent a position. Then swap the ith element and the kth element.

2. imaging that we have already successfully shuffled 51 cards, to add the last card, we just need to pick a random position and swap the element on that positon and the last one.
*/

public class Shuffle{
    private void shuffleCards(int[] arr){
        for (int i = 0; i < arr.length; i++){
            int r = rand(0,i);
            int temp = arr[i];
            arr[i] = arr[r];
            arr[r] = temp;
        }
    }

    private int rand(int low, int high){
        return low + (int)(Math.random() * (high - low + 1));
    }

    private void printArray(int[] arr){
        for (int i : arr){
            System.out.print(i + " ");
        }
        System.out.println("\n");
    }

    public static void main(String[] args){
        Shuffle s = new Shuffle();
        int[] input = {1,2,3,4,5,6,7,8,9,10};
        s.shuffleCards(input);
        s.printArray(input);
    }
}