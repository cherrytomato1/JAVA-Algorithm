package boj.boj0174_210825_9376_p5_탈옥;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class 백준_9376번_탈옥실패2 {

	private static class Node implements Comparable<Node>{
		int row;
		int col;
		int wallCount;
		int numberOfPrisoner;

		public Node(int row, int col, int wallCount, int numberOfPrisoner) {
			this.row = row;
			this.col = col;
			this.wallCount = wallCount;
			this.numberOfPrisoner = numberOfPrisoner;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.wallCount, o.wallCount);
		}

		@Override
		public String toString() {
			final StringBuilder sb = new StringBuilder("Node{");
			sb.append("row=").append(row);
			sb.append(", col=").append(col);
			sb.append(", wallCount=").append(wallCount);
			sb.append('}');
			return sb.toString();
		}
	}

	private static char[][] map;
	private static int[][][] wallCounter;

	private static int N;
	private static int M;
	private static int remainPrisonerBits;
	private static boolean isContacted;
	private static int result;


	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws IOException{
		int t = Integer.parseInt(br.readLine());
		for (int i = 0; i < t ; i++) {
			Node[] prisoners = init();
			System.out.println(solve(prisoners));
		}
	}

	private static Node[] init() throws IOException {

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		Node[] prisoners = new Node[2];

		map = new char[N][M];
		wallCounter = new int[N][M][2];
		remainPrisonerBits = 0b11;
		isContacted = false;
		result = 0;

		int prisonerNumber = 0;
		for (int r = 0; r < N ; r++) {
			char[] cols = br.readLine().toCharArray();
			for (int c = 0; c < M ; c++) {
				map[r][c] = cols[c];
				wallCounter[r][c][0] = Integer.MAX_VALUE;
				wallCounter[r][c][1] = Integer.MAX_VALUE;
				if (cols[c] != '$') {
					continue;
				}
				prisoners[prisonerNumber] = new Node(r, c, 0, prisonerNumber++);
			}
		}
		return prisoners;
	}

	private static int solve(Node[] prisoners) {
//		Queue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[2] - o2[2]);
//		Queue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[2]));

//		System.out.println(prisoners[0]);
//		System.out.println(prisoners[1]);

		final int[][] DIR = {{-1, 1, 0, 0}, {0, 0, -1, 1}};
		final int ROW = 0;
		final int COL = 1;


		Queue<Node> pq = new PriorityQueue<>();
		pq.offer(prisoners[0]);
		pq.offer(prisoners[1]);


		while (!pq.isEmpty()) {
			Node curr = pq.poll();
			if(isContacted && curr.numberOfPrisoner == 1)   continue;
			System.out.println(pq.size());
			System.out.println(curr);
			for (int i = 0; i < 4; i++) {
				Node next = new Node(curr.row + DIR[ROW][i], curr.col + DIR[COL][i], curr.wallCount, curr.numberOfPrisoner);
				if (!move(pq, next)) {
					continue;
				}
				if (remainPrisonerBits == 0) {
					System.out.println(result + " " + next.wallCount);
					return result + next.wallCount;
				}
			}
		}
		return -1;
	}

	private static boolean move(Queue<Node> pq, Node prisoner) {
		int row = prisoner.row;
		int col = prisoner.col;
		if (row < 0 || row >= N || col < 0 || col >= M) {
			remainPrisonerBits &= ~remainPrisonerBits | 1 << prisoner.numberOfPrisoner;
			return true;
		}

		if (!isMovable(prisoner)) {
			return false;
		}
		wallCounter[row][col][prisoner.numberOfPrisoner] = prisoner.wallCount;
		if (!isContacted && checkContacted(prisoner)) {
			System.out.println(" is contacted" + prisoner);
			isContacted = true;
			result += prisoner.wallCount;
			remainPrisonerBits &= ~remainPrisonerBits | 0b10;
			return false;
		}
		if (map[row][col] == '#') {
			wallCounter[row][col][prisoner.numberOfPrisoner]++;
			prisoner.wallCount++;
		}


		pq.offer(prisoner);
		return false;
	}

	private static boolean isMovable(Node prisoner) {
		if (map[prisoner.row][prisoner.col] == '*') {
			return false;
		}
		return wallCounter[prisoner.row][prisoner.col][prisoner.numberOfPrisoner] > prisoner.wallCount;
	}

	private static boolean checkContacted(Node prisoner) {
		int row = prisoner.row;
		int col = prisoner.col;
		int targetPrisonerNumber = (prisoner.numberOfPrisoner + 1) % 2;
		return wallCounter[row][col][targetPrisonerNumber] != Integer.MAX_VALUE;
	}

}

/*

1
5 9
****#****
*..#.#..*
****.****
*$#.#.#$*
*********
 */