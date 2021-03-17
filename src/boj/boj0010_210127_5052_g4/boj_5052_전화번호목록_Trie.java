package boj.boj0010_210127_5052_g4;

import java.io.*;

public class boj_5052_전화번호목록_Trie {
	private static class Trie{
		boolean isEnd;
		Trie[] child;

		Trie(int childNum){
			this.child = new Trie[childNum];
		}

		private boolean addChild(char[] numbers){
			Trie curr = this;
			for(int i = 0 ; i < numbers.length; i++){
				int val = numbers[i] - '0';
				if(curr.child[val] == null)         curr.child[val] = new Trie(10);
				//이미 다른 문자열의 마지막으로 저장되어 있을 때 예외
				else if(curr.child[val].isEnd)      return false;
				//트라이에 자식이 있는데 마지막 문자열일 경우 예외
				else if(i == numbers.length - 1)    return false;
				if(i == numbers.length - 1)         curr.child[val].isEnd = true;
				curr = curr.child[val];
			}
			return true;
		}
	}

	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	private static boolean solve() throws IOException{
		Trie root = new Trie(10);
		int N = Integer.parseInt(br.readLine());
		boolean flag = true;
		for(int i = 0 ; i < N; i++){
			char[] in = br.readLine().toCharArray();
			if(flag && !root.addChild(in))  flag = false;
		}
		return flag;
	}

	public static void main(String[] args) throws IOException{
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int tc = 1 ; tc <= T; tc++){
			sb.append(solve() ? "YES" : "NO").append("\n");
		}
		System.out.println(sb);
	}
}
