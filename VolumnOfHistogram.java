/* 17.21 Volume of Histogram: Imagine a histogram (bar graph). Design an algorithm to compute the
volume of water it could hold if someone poured water across the top. You can assume that each
histogram bar has width 1.
EXAMPLE (Black bars are the histogram. Gray is water.)
Input:{0, 0, 4, 0, 0, 6, 0, 0, 3, 0, 5, 0, 1, 0, 0, 0}
Output: 26
*/

/*
1. first we need to know the tallest bar to ensure that we can hold the water by the second tallest bar on its left side and right side. So, we can recursively find the tallest bar and separate the histogram into two parts and then do the same for the rest.

2. A better way is to think more about the volume we can hold for each bar. To do this, we need to find the corresponding "second tallest" bars for each bar to decide the volume. For example, for the 9th bar with height 3, we need to know that the second tallest bar for it is 5, so we can hold 5-3 = 2 at this bar. To find this "second tallest" bar, imaging we have found the tallest bar for position k on its right side, then the second tallest bar for k is itself or on its left side. So we go from left to right and record the max for each bar and then go from right to left do the same thing, the smaller of these two max is the "second tallest" we need. Then, we just need to sum up the value of second tallest - bar height.
*/

public class VolumeOfHistogram{

    private int findVolumeOfWater(int[] bars){
        int len = bars.length;
        int[] highestFromLeft = new int[len];
        int high = 0;
        for (int i = 0; i < len; i++){ //find the highest from left to right
        if (bars[i] > high){
        high = bars[i];
        }
        highestFromLeft[i] = high;
        }
        high = 0;
        for (int i = len-1; i >= 0; i--){ // find the second highest from right to left
        if (bars[i] > high){
        high = bars[i];
        }
        highestFromLeft[i] = highestFromLeft[i] > high ? high : highestFromLeft[i];
        }

        int res = 0;
        for (int i = 0; i < len; i++){ //find the volume each bar can hold and sum up
        res += highestFromLeft[i] - bars[i];
        }
         return res;
    }

    public static void main(String[] args){
        int[] input = {0, 0, 4, 0, 0, 6, 0, 0, 3, 0, 5, 0, 1, 0, 0, 0};
        VolumeOfHistogram v = new VolumeOfHistogram();
        int res = v.findVolumeOfWater(input);
        System.out.println(res);
    }
}