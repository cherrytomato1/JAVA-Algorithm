package algorithm;

/*
	트리 구조의 디스 조인트 셋 구현
 */
public class UnionFind {
	private int N;
	private int parents[];

	public UnionFind(int N){
		this.N = N;
		parents = new int[N];
		make(N);
	}
	/*
		크기가 1인 단위 집합을 만든다.
	 */
	private void make(int size){
		for(int i = 0 ; i < size; i++){
			parents[i] = i;
		}
	}
	public int find(int x){
		if(parents[x] == x)    return x;
		//path compression 을 위해 parent[x] 값 갱신
		return parents[x] = find(parents[x]);
	}
	public boolean union(int x, int y){
		int parX = find(x);
		int parY = find(y);
		if(parX == parY)    return false;
		parents[parY] = parX;
		return true;
	}

	public int[] getParents() {
		return parents;
	}
}
