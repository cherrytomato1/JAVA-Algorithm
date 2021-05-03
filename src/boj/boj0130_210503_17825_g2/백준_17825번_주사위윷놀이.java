package boj.boj0130_210503_17825_g2;

import java.util.*;
import java.io.*;

public class 백준_17825번_주사위윷놀이 {
	private static class Vertex {
		int index;
		int score;
		Vertex next;
		Vertex shortcut;
		public Vertex(int index, int score) {
			this.index = index;
			this.score = score;
		}

		@Override
		public String toString() {
			final StringBuilder sb = new StringBuilder("Vertex{");
			sb.append("index=").append(index);
			sb.append(", score=").append(score);
			sb.append(", next=").append(next == null ? null : next.index);
			sb.append(", shortcut=").append(shortcut == null ? null : shortcut.index);
			sb.append('}');
			return sb.toString();
		}
	}

	private static final int ORDER_MAX = 10;
	private static final int PIECE_MAX = 4;
	private static final int GOAL = -1;
	private static final int FAIL = -2;
	private static List<Vertex> vertexs;
	private static int[] orders;
	private static int centerIdx;
	private static int endIdx;
	private static int idx;
	private static int[] pieces;

	public static void main(String[] args) throws IOException{
		init();
		System.out.println(dfs(0, 0));
	}

	private static void init() throws IOException {
		vertexs = new ArrayList<>();
		orders = new int[ORDER_MAX];
		pieces = new int[PIECE_MAX];

		makeEdge();
		makeShortcut(5, 10, 3, 3);
		makeShortcut(10, 20, 2, 2);
		makeShortcut(15, 29, -1, 3);
		makeShortcut(centerIdx, 25, 5, 2);

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		for (int i = 0; i < ORDER_MAX ; i++) {
			orders[i] = Integer.parseInt(st.nextToken());
		}
	}

	private static void makeEdge() {
		vertexs.add(new Vertex(idx++, 0));
		for (int i = 1; i < 22 ; i++) {
			Vertex vertex = new Vertex(idx, i * 2);
			vertexs.add(vertex);
			vertexs.get(idx++ - 1).next = vertex;
		}
		endIdx = idx - 1;
		vertexs.get(endIdx).score = 0;
		//중앙 고르기
		centerIdx = idx;
		vertexs.add(new Vertex(idx++, 25));
	}


	private static void makeShortcut(int startIdx, int startScore, int raise, int count) {
		vertexs.add(new Vertex(idx++, startScore + raise));
		if(startIdx == centerIdx)   vertexs.get(startIdx).next = vertexs.get(idx - 1);
		else                        vertexs.get(startIdx).shortcut = vertexs.get(idx - 1);

		for (int i = 2; i <= count  ; i++) {
			Vertex vertex = new Vertex(idx, i * raise + startScore);
			vertexs.add(vertex);
			vertexs.get(idx++ - 1).next = vertex;
		}
		if(startIdx == centerIdx)   vertexs.get(idx - 1).next = vertexs.get(endIdx - 1);
		else                        vertexs.get(idx - 1).next = vertexs.get(centerIdx);
	}

	private static int dfs(int orderIdx, int totalScore) {
		if(orderIdx == ORDER_MAX)   return totalScore;

		int ret = 0;
		for (int i = 0; i < PIECE_MAX ; i++) {
			if (pieces[i] == GOAL)  continue;
			int tempIdx = pieces[i];
			int score = move(i, orders[orderIdx]);
			if(score == FAIL)   continue;
			score = score == -1 ? 0 : score;
			ret = Math.max(dfs(orderIdx + 1, totalScore + score), ret);
			pieces[i] = tempIdx;
		}
		return ret;
	}

	private static int move(int targetIdx, int moveCount) {
		Vertex curr = vertexs.get(pieces[targetIdx]);
		if(curr.shortcut != null)   {
			curr = curr.shortcut;
			moveCount--;
		}
		for (; moveCount > 0 ; moveCount--) {
			curr = curr.next;
			if (curr.index != endIdx)   continue;
			pieces[targetIdx] = GOAL;
			return GOAL;
		}

		for (int i = 0; i < PIECE_MAX; i++) {
			if(pieces[i] == curr.index) return FAIL;
		}

		pieces[targetIdx] = curr.index;
		return curr.score;
	}
}
