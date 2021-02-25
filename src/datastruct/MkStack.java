package datastruct;

public class MkStack {
	private final int[] stk;
	private int top;
	private final int size;

	public MkStack(int size){
		this.size = size;
		top = 0;
		stk = new int[size];
	}

	public int peek(){
		return this.stk[top];
	}

	public boolean push(int o){
		if(top == size - 1) return false;
		stk[++top] = o;
		return true;
	}

	public Object pop(){
		if(top == -1)   return null;
		Object O = stk[top];
		stk[top--] = 0;
		return O;
	}

	public boolean isEmpty(){
		return top == 0;
	}
}
