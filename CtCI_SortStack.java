/* 3.5 Sort Stack: Write a program to sort a stack such that the smallest items are on the top. You can use
an additional temporary stack, but you may not copy the elements into any other data structure
(such as an array). The stack supports the following operations: push, pop, peek, and isEmpty. */

/*
1. keep the temporart stack (s2) reversely sorted: pop an element (val) from the original stack (s1), while this element is smaller than the peek of s2, pop the peek from s2 back to s1, push val into s2. 
2. when s1 is empty, pop from s2 and puch back to s1, return.
*/

void sortStack(Stack<Integer> s1){
	Stack<Integer> s2 = new Stack<Integer>();
	while (!s1.isEmpty()){
		int val = s1.pop();
		while (!s2.isEmpty() && s2.peek() > val){
			s1.push(s2.pop());
		}
		s2.push(val);
	}

	while (!s2.isEmpty()){
		s1.push(s2.pop());
	}
}

/*
1. Runtime O(n^2); space O(n). 
*/