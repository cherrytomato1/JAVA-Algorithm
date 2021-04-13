package swea.solution_SW_4014;
import java.util.*;
import java.io.*;

public class SWEA_4014_활주로건설 {
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;

	private static int N;
	private static int X;
	private static int[][] map;

	private static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N ; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}

	private static int solve(){
		int ret = 0;
		for (int i = 0; i < N ; i++) {
			if(check(i, true)) ret++;
			if(check(i, false)) ret++;
		}
		return ret;
	}

	private static boolean check(int idx, boolean type){
		boolean flag = false;
		int cnt = 1;
		int prev;
		int curr = type ? map[idx][0] : map[0][idx] ;
		for (int i = 1 ; i < N; i++) {
			prev = curr;
			curr = type ? map[idx][i] : map[i][idx];
			if(Math.abs(prev - curr) >= 2)             return false;
			if(prev == curr){
				cnt++;
				if(!flag || cnt != X)   continue;
				flag = false;
				cnt = 0;
				continue;
			}
			if((flag || curr > prev) && cnt < X)      return false;
			if(!flag && curr < prev)                  flag = true;
			cnt = 1;
		}
		return !flag;
	}

	public static void main(String[] args) throws IOException{
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= T ; tc++) {
			init();
			sb.append("#").append(tc).append(" ").append(solve()).append("\n");
		}
		System.out.println(sb);
	}
}
