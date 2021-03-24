package algorithm;

public class DynamicProgramming {

	private static int[] d;

	public DynamicProgramming(int d){
		this.d = new int[d + 1];
		this.d[0] = 0;
	}

	public int DPgo(int res){
		dp(res);
		return d[res];
	}

	private int dp(int n){
//		System.out.println(n);
		if(n == 0)      return 0;
		if(d[n] != 0)   return d[n];
		int res = Integer.MAX_VALUE;
		if(n >= 6)      res = Math.min(dp(n - 6) + 1, res);
		if(n >= 4)      res = Math.min(dp(n - 4) + 1, res);
		return d[n] = Math.min(res, dp(n - 1) + 1);
	}

}
