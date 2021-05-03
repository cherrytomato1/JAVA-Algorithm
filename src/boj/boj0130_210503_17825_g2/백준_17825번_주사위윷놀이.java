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

	//길 만들기
	private static void makeEdge() {
		vertexs.add(new Vertex(idx++, 0));
		for (int i = 1; i < 22 ; i++) {
			Vertex vertex = new Vertex(idx, i * 2);
			vertexs.add(vertex);
			vertexs.get(idx++ - 1).next = vertex;
		}
		//도착 설정하기
		endIdx = idx - 1;
		vertexs.get(endIdx).score = 0;
		//중앙 고르기
		centerIdx = idx;
		//중앙 만들기
		vertexs.add(new Vertex(idx++, 25));
	}

	//지름길 만들기
	private static void makeShortcut(int startIdx, int startScore, int raise, int count) {
		//지름길 첫 정점 만들기
		vertexs.add(new Vertex(idx++, startScore + raise));
		//중앙에서 가는 건 지름길이 아니라 유일한 길
		if(startIdx == centerIdx)   vertexs.get(startIdx).next = vertexs.get(idx - 1);
		else                        vertexs.get(startIdx).shortcut = vertexs.get(idx - 1);

		//지름길 개수만큼 반복하며 점수 설정 및 연결하기
		for (int i = 2; i <= count  ; i++) {
			Vertex vertex = new Vertex(idx, i * raise + startScore);
			vertexs.add(vertex);
			vertexs.get(idx++ - 1).next = vertex;
		}
		//중앙에서 시작하면 도착지점과 연결해야함 아닌 경우 중앙과 연결
		if(startIdx == centerIdx)   vertexs.get(idx - 1).next = vertexs.get(endIdx - 1);
		else                        vertexs.get(idx - 1).next = vertexs.get(centerIdx);
	}

	private static int dfs(int orderIdx, int totalScore) {
		if(orderIdx == ORDER_MAX)   return totalScore;

		int ret = 0;
		for (int i = 0; i < PIECE_MAX ; i++) {
			//이미 도착한 말 검사안함
			if (pieces[i] == GOAL)  continue;
			//재귀할 때 되돌려 놓기 위해 임시 저장
			int tempIdx = pieces[i];
			//점수 받아오기
			int score = move(i, orders[orderIdx]);
			//실패 시 스킵
			if(score == FAIL)   continue;

			//재귀하여 결과 값이 가장 큰놈으로
			ret = Math.max(dfs(orderIdx + 1, totalScore + score), ret);
			//말 위치 되돌려 놓기
			pieces[i] = tempIdx;
		}
		return ret;
	}

	private static int move(int targetIdx, int moveCount) {
		//출발 인덱스에 해당하는 정점 받아오기
		Vertex curr = vertexs.get(pieces[targetIdx]);
		//출발 정점에 지름길이 있다!?!
		if(curr.shortcut != null)   {
			//지름길로 이동하기
			curr = curr.shortcut;
			moveCount--;
		}
		//이동~~
		while(moveCount-- > 0) {
			curr = curr.next;
			//도착할 경우 도착으로 바꾸고 반환하기
			if (curr.index != endIdx)   continue;
			pieces[targetIdx] = GOAL;
			return 0;
		}

		//중복 말 검사
		for (int i = 0; i < PIECE_MAX; i++) {
			if(pieces[i] == curr.index) return FAIL;
		}

		pieces[targetIdx] = curr.index;
		return curr.score;
	}
}
