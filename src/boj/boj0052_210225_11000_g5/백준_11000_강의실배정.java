package boj.boj0052_210225_11000_g5;

import java.util.*;
import java.io.*;

class Lecture implements Comparable<Lecture>{
	int start;
	int end;

	public Lecture(int start, int end){
		this.start = start;
		this.end = end;
	}

	@Override
	public int compareTo(Lecture o){
		int val = this.start - o.start;
		return val != 0 ? val : this.end - o.end;
	}
}

public class 백준_11000_강의실배정 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int N;

	static Lecture[] lec;
	static PriorityQueue<Integer> room = new PriorityQueue<>();

	static void init() throws IOException{
		N = Integer.parseInt(br.readLine());

		lec = new Lecture[N];
		for(int i = 0 ; i < N ; i++){
			st = new StringTokenizer(br.readLine());
			lec[i] = new Lecture(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		Arrays.sort(lec);
	}

	static int solve(){
		int maxClass = Integer.MIN_VALUE;

		for(int i = 0 ; i < N ; i++ ){
			while(!room.isEmpty() && room.peek() <= lec[i].start){
				room.poll();
			}
			room.offer(lec[i].end);

			maxClass = Math.max(maxClass, room.size());
		}
		return maxClass;
	}

	public static void main(String[] args) throws IOException{
		init();
		System.out.println(solve());
	}
}
