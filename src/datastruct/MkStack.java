package datastruct;

public class MkStack <T> {
	private T[] stk;
	private int top;
	private int size;

	public MkStack() {
		super();
		top = -1;
		size = 1;
		stk = (T[])new Object[size];
	}

	public MkStack(int size){
		this();
		this.size = size;
	}

	public T peek(){
		return this.stk[top];
	}

	public boolean push(T t){
		if(top == size - 1) increaseSize();
		stk[++top] = t;
		return true;
	}

	public void increaseSize(){
		T[] newStk = (T[])new Object[size * 2];
		for (int i = 0; i < size ; i++) {
			newStk[i] = stk[i];
		}
		size *= 2;
		stk = newStk;
	}

	public T pop(){
		if(isEmpty())   return null;
		return stk[top--];
	}

	public boolean isEmpty(){
		return top == -1;
	}
}
