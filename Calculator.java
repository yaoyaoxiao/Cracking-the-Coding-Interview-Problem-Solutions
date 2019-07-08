/* 16.26 Calculator: Given an arithmetic equation consisting of positive integers, +, -, * and / (no parentheses),
compute the result.
EXAMPLE
Input: 2*3+5/6*3+15
Output: 23.5 */

import java.util.ArrayList;
import java.util.Stack;

public class Calculator{
    private class Operator{
        char charVal;
        int level;
        public Operator(char c){
            charVal = c;
            level = (c == '+' || c == '-') ? 1 : 2;
        }
        public double compute(double left, double right){
            if (charVal == '+') { return left + right; }
            if (charVal == '-') { return left - right; }
            if (charVal == '*') { return left * right; }
            if (charVal == '/') { return left / right; }
            return right;
        }
    }

    private double calculate(String str){
        if (str == null) { return 0; }

        ArrayList<Double> nums = new ArrayList<>();
        ArrayList<Operator> ops = new ArrayList<>();
        parseString(str, nums, ops); //separate numbers and operators into two ArrayLists

        // assuming that the size of two Arraylists are good: nums.size() == ops.size() + 1)
        Stack<Double> numStack = new Stack<>();
        Stack<Operator> opStack = new Stack<>();

        int i = 0, j = 0;
        numStack.push(nums.get(j++));
        while (i < ops.size() && j < nums.size()){
            Operator op = ops.get(i++);
            if (op.level == 2){
                double cur = op.compute(numStack.pop(), nums.get(j++));
                numStack.push(cur);
            }
            else{
                opStack.push(op);
                numStack.push(nums.get(j++));
            }
        }

        //level one operators
        double res = 0;
        if (!numStack.isEmpty()) { res = numStack.pop(); }
        while (opStack.size() > 0) {
            Operator op = opStack.pop();
            res = op.compute(numStack.pop(), res);
        }
        return res;
    }

    // assuming that the given string doesn't have characters other than digits and operators
    private void parseString(String str, ArrayList<Double> nums, ArrayList<Operator> ops){
        char[] chrs = str.toCharArray();
        double num = 0;
        for (char c : chrs){
            if(c != '+' && c != '-' && c != '*' && c != '/'){
                num = num * 10 + Character.getNumericValue(c);
            }
            else{
                nums.add(num);
                num = 0;
                ops.add(new Operator(c));
            }
        }
        nums.add(num); //add the last num
    }

    public static void main(String[] args){
        Calculator c = new Calculator();
        String sequence = "2*3+5/6*3+15";
        double result = c.calculate(sequence);
        System.out.println(result);
    }
}