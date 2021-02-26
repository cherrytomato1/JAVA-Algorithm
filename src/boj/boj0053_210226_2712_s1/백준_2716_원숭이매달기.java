package boj.boj0053_210226_2712_s1;

import java.io.*;

public class 백준_2716_원숭이매달기 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();

	static char[] arr;

	static void init() throws IOException{
		arr = br.readLine().toCharArray();
	}

	static int solve(){
		int depth = 0;
		int maxDepth = 0;
		for(char c : arr){
			if(c == '[') {
				depth++;
				maxDepth = Math.max(maxDepth, depth);
			}else depth --;
		}

		return (int)(Math.pow(2, maxDepth));
	}

	public static void main(String[] args) throws IOException{
		int T = Integer.parseInt(br.readLine());

		for(int tc = 1; tc <= T ; tc++){
			init();
			sb.append(solve()).append("\n");
		}
		System.out.println(sb);
	}
}

class cStack{
	private int size;
	private char[] elements;
	private int top;


	public cStack(int size){
		this.size = size;
		top = 0;
		elements = new char[size];
	}

	public void push(char c){
		elements[top++] = c;
	}

	public char pop(){
		try{
			return elements[top--];
		} catch(ArrayIndexOutOfBoundsException e){
			e.printStackTrace();
		}
		return '0';
	}

	public char peek(){
		return elements[top];
	}

	public boolean isEmpty(){
		return top == 0;
	}


}
