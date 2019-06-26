/* 3.2 Stack Min: How would you design a stack which, in addition to push and pop, has a function min
which returns the minimum element? Push, pop and min should all operate in O(1) time. */

/* use an extra stack to save mins, update this stack when a new min is pushed in or an exsiting min is poped out */

public class StackWithMin extends Stack<Integer>{
	Stack<Integer> st;
	public StackWithMin(){
		st = new Stack<Integer>():
	}

	public void push(int d){
		super.push(d);
		if(d <= min(){
			st.push(d);
		}
	}
	public int pop(){
		int d = super.pop();
		if (d == min()){
			st.pop();
		}
		return d;
	}

	public int min(){
		if (st.isEmpty()){
			return Integer.MAX_VALUE;
		}
		else{
			return st.peek();
		}
	}
}