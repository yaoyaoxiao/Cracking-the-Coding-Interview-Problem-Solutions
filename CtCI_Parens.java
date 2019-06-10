/* 8.9 Parens: Implement an algorithm to print all valid (e.g., properly opened and closed) combinations
of n pairs of parentheses.
EXAMPLE
Input: 3
Output: ((())),(())(), ()(()), ()()(), (()()) */

ArrayList<String> allValidParens(int n){
	ArrayList<String> results = new ArrayList<String>();
	char[] str = new char[n*2];
	allValidParens(str, n, n, results, 0);
	return results;
}

void allValidParens(String str, int leftCount, int rightCount, ArrayList<String> results, int index){
	if (leftCount < 0 || rightCount < leftCount){
		return; //invalid parentheses
	}
	if (leftCount == 0 && rightCount == 0){
		results.add(String.copyValueOf(str));
	}
	else{
		str[index]='(';
		allValidParens(prefix, leftCount-1, rightCount, results, index+1);

		str[index]=')';
		allValidParens(prefix.leftCount, rightCount-1, results, index+1);
	}
	

}

/*
1. Just simply keep track of the number of left and right parentheses allowed. Add '(' or ')' recursively but ignore those invalid cases. When it is invalid? 
- Adding a '(' would always be valid as long as there are still '(' left (leftCount > 0);
- Adding a ')' would be valid when there are more ')' left than '('.
*/

