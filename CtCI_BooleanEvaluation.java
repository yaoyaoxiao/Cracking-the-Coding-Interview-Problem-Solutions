/* 8.14 Boolean Evaluation: Given a boolean expression consisting of the symbols 0 (false), 1 (true), &
(AND), | (OR), and ^ (XOR), and a desired boolean result value result, implement a function to
count the number of ways of parenthesizing the expression such that it evaluates to result. The expression should be fully parenthesized (e.g., (0)^(1) but not extraneously (e.g., (((0))^(1))).

EXAMPLE
countEval("1^0|0|1". false) -> 2
countEval("0&0&0&1^1|0", true) -> 10  */

int countEval(String str, boolean result, HashMap<String,Integer> map){
	if (str.length() == 0) return 0;
	if (str.length() == 1) return stringToBool(s) == result ? 1 : 0;
	if (map.containsKey(result + s)) {
		return map.get(result + s);
	}

	int ways = 0;
	for (int i = 1; i < s.length(); i += 2){
		char c = s.charAt(i);
		String left = s.subString(0,i);
		String right = s.subString(i+1);
		int leftTrue = countEval(left, true, map);
		int leftFalse = countEval(left, false, map);
		int rightTrue = countEval(right, true, map);
		int rightFalse = countEval(right, false, map);
		int total = (leftTrue + leftFalse) * (rightTrue + rightFalse);

		int totalTrue = 0;
		if (c == '^'){
			totalTrue = leftTrue * rightFalse + leftFalse * rightTrue;
		}
		else if(c == '|'){
			totalTrue = leftTrue * rightTrue + leftTrue * rightFalse + leftFalse * rightTrue;
		}
		elseif (c == '&'){
			totalTrue = leftTrue * rightTrue;
		}

		int subWays = result ? totalTrue : total - totalTrue;
		ways += subWays;
	}
	map.put(result+s, ways);
	return ways;
}



