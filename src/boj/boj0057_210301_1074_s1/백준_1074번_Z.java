package boj.boj0057_210301_1074_s1;

import java.util.*;
import java.io.*;

public class 백준_1074번_Z {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int N;
	static int maxSize;
	static int r;
	static int c;

	static void init() throws IOException{
		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		maxSize = (int)Math.pow(2, N);
	}


	static int dfs(int row, int col, int size, int num){
		if(size == 2){
			if(row == r - 1)    num += 2;
			if(col == c - 1)    num ++;
			return num;
		}
		size /= 2;

		if(row + size <= r){
			row += size;
			num += size * size * 2;
		}
		if(col + size <= c){
			col += size;
			num += size * size;
		}

		return dfs(row, col, size, num);
	}

	public static void main(String[] args) throws IOException{
		init();
		System.out.println(dfs(0, 0, maxSize, 0));
	}
}
