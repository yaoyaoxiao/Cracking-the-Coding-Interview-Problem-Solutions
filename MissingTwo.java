/* 17.19 Missing Two: You are given an array with all the numbers from 1 to N appearing exactly once,
except for one number that is missing. How can you find the missing number in O (N) time and
O(1) space? What if there were two numbers missing? */

/*
1. It is easy to find the missing one in an array without dupplicates. We just need to sum all the element up and return the difference between the sum of 1 to N, n *(n+1)/2, and the sum of the array.

2. Since there are missing two (x and y), we need two equations to solve this problem. 1. x + y = sumDiff; 2. x^2 + y^2 = suqareSumDiff. ax^2 +bx +c = 0 ==> x = (-b +- sqrt(b^2 - 4ac))/2a. In this problem, we only care positive nums.
*/

public class MissingTwo{
    int[] findMissingTwo(int[] arr){
        int n = arr.length + 2;
        int sum = n * (n+1) /2;
        int suqareSum = 0;

        for (int i = 1; i <= n; i++){
            suqareSum += Math.pow(i,2);
        }

        for (int j : arr){
            sum -= j;
            suqareSum -= Math.pow(j,2);
        }

        //solve the equations
        int a = 2, b = -2 * sum, c = sum * sum - suqareSum;
        int[] res = new int[2];
        res[0] = (int)(-1*b + Math.sqrt(b*b -4*a*c))/(2*a);
        res[1] = sum-res[0];

        return res;
    }

    public static void main(String args[]){
        int[] input = {1,2,3,5,6,7,8,9,10,11};
        MissingTwo mt = new MissingTwo();
        int[] res = mt.findMissingTwo(input);
        for(int i : res){
            System.out.println(i);
        }
    }
}