package boj.boj0062_210308_14715_s1;
import java.io.*;

public class 백준_14715번_전생했더니 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	static int recur(int n, int cnt){
		if(n == 2)  return cnt;
		int max = (int)Math.sqrt(n);
		int ret = Integer.MAX_VALUE;

		for(int j = 2; j <= max ; j++ ){
			if(n % j != 0 )     continue;
			int val = Math.max(recur(n/j, cnt + 1), recur(j, cnt + 1));
			ret = Math.min(val, ret);
		}
		return ret == Integer.MAX_VALUE ? cnt : ret;
	}

	public static void main(String[] args) throws IOException{
		int K = Integer.parseInt(br.readLine());
		System.out.println(recur(K, 0));
	}
}
