/*  17 .16 The Masseuse: A popular masseuse receives a sequence of back-to-back appointment requests
and is debating which ones to accept. She needs a 15-minute break between appointments and
therefore she cannot accept any adjacent requests. Given a sequence of back-to-back appointment
requests (all multiples of 15 minutes, none overlap, and none can be moved), find the optimal
(highest total booked minutes) set the masseuse can honor. Return the number of minutes.
EXAMPLE
Input: {30, 15, 60, 75, 45, 15, 15, 45}
Output: 180 minutes ({30, 60, 45, 45}). */

/*
1. for every appointment, there are two choices: include this one, OR not
2. for appointment i, if including this appointment, we need to know the max of i+2 to the end; if not including i, we need to know the max of i+1 to the end.
3. we can jump over either one or two appointment: not zero since she needs breaks; not 3 since she can always take the middle one to optimize.
4. so, we can use a recursion to get the max: i + max(i+2) Or max(i+1); Also, we can use DP to avoid duplicative work. Both runtime and space are O(n);
5. The best way is to use iterative approach: runtime O(n) and space O(1) since we only need to remember the max(i+2) and max(i+1).
*/

public class Masseuse{
    int findMaxTime(int[] appointments){
        int max1 = 0, max2 = 0;
        for (int i = appointments.length - 1; i >= 0; i--){ //go backwards for easier understanding
            int withThis = appointments[i] + max2;
            int withoutThis = max1;
            int curr = Math.max(withThis, withoutThis);
            max2 = max1;
            max1 = curr;
        }
        return max1;
    }

    public static void main(String args[]){
        int[] input = {30, 15, 60, 75, 45, 15, 15, 45};
        Masseuse m = new Masseuse();
        int res = m.findMaxTime(input);
        System.out.println(res);
    }
}