package boj.boj0118_210419_1938_g2;

import java.util.*;
import java.io.*;

public class 백준_1938번_통나무옮기기 {
	/*
		row : row좌표
		col : col좌표
		isTurned : 가로인지 세로인지
	 */
	private static class Log{
		int row;
		int col;
		boolean isTurned;

		public Log(int row, int col, boolean isTurned) {
			super();
			this.row = row;
			this.col = col;
			this.isTurned = isTurned;
		}

		//target과 통나무 비교용 equals 오버라이딩
		@Override
		public boolean equals(Object o) {
			if (o == null || getClass() != o.getClass()) return false;
			Log log = (Log) o;
			return row == log.row && col == log.col && isTurned == log.isTurned;
		}

		//비트마스킹용 정수값 반환
		public int isTurnedToInteger() {
			return isTurned ? 1 : 0;
		}
	}

	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static final int ROW = 0;
	private static final int COL = 1;
	private static final int[][] DIR = {{-1, 1, 0, 0}, {0, 0, -1, 1}};

	private static boolean[][] map;
	//가로인지 세로인지에 맞게 방문체크 하기 위해 비트마스킹 -> int배열
	private static int[][] visited;
	private static int N;
	private static Log start;
	private static Log target;

	private static void init() throws IOException {
		N = Integer.parseInt(br.readLine());
		map = new boolean[N][N];
		visited = new int[N][N];

		//타겟과 시작위치 생성용
		int[][] tempStart = new int[2][2];
		int[][] tempTarget = new int[2][2];
		int startCnt = 0;
		int targetCnt = 0;

		for (int i = 0; i < N ; i++) {
			char[] in = br.readLine().toCharArray();
			for (int j = 0; j < N ; j++) {
				map[i][j] = in[j] == '1';
				//통나무와 목표지점 좌표 저장 및 생성
				if (in[j] == 'B' && startCnt < 2) {
					tempStart[startCnt][ROW] = i;
					tempStart[startCnt][COL] = j;
					if(++startCnt == 2)   makeLog(tempStart, true);
				} else if (in[j] == 'E' && targetCnt < 2) {
					tempTarget[targetCnt][ROW] = i;
					tempTarget[targetCnt][COL] = j;
					if(++targetCnt == 2)   makeLog(tempTarget, false);
				}
			}
		}
	}

	private static void makeLog(int[][] currPos, boolean isStart) {
		//row가 다 같으면 가로
		boolean isTurned = currPos[0][ROW] == currPos[1][ROW];
		//가운데 좌표만 저장
		if(isStart)   start = new Log(currPos[1][ROW], currPos[1][COL], isTurned);
		else        target = new Log(currPos[1][ROW], currPos[1][COL], isTurned);
	}

	private static int bfs() {
		int ret = 0;
		Queue<Log> q = new ArrayDeque<>();
		q.offer(start);
		//시작지점 시작 방향 방문체크
		visited[start.row][start.col] |= 1 << start.isTurnedToInteger();
		while (!q.isEmpty()) {
			ret++;
			for (int i = 0, size = q.size(); i < size ; i++) {
				Log curr = q.poll();
				for (int j = 0; j < 5 ; j++) {
					//방향에 맞게 이동시킴(방향 전환 포함)
					Log next = move(curr, j);
					//이동 못한경우
					if(next == null)        continue;
					//도착~!!!~!
					if(next.equals(target)) return ret;
					//방문체크
					visited[next.row][next.col] |= 1 << next.isTurnedToInteger();
					q.offer(next);
				}
			}
		}
		return 0;
	}

	/*
		@움직이기 메서드
		@params curr : 움직이기 전 좌표
		@params dir : 움직일 방향 (4 == 방향바꾸기)
		@return ret : 움직이기 성공시 새로운 통나무 위치(실패시 null)
	 */
	private static Log move(Log curr, int dir) {
		//현재 통나무 위치 복사
		Log ret = new Log(curr.row, curr.col, curr.isTurned);
		//방향 돌리기
		if (dir == 4) {
			//방향바꾸기
			ret.isTurned = !ret.isTurned;
			//방향바꾸기 가능하면 반환
			if(!isTurnAble(ret))   return null;
			return ret;
		}
		//방향에 맞게 새로운 좌표 할당
		ret.row += DIR[ROW][dir];
		ret.col += DIR[COL][dir];
		//움직이기 가능한지 검사 및 가능하면 새로운좌표, 못하면 null
		if(!isMovable(ret, dir)) return null;
		return ret;
	}

	private static boolean isTurnAble(Log curr) {
		//방향돌리기 검사, 중앙 좌표로부터 인접 8칸 모두 검사
		for (int i = -1; i < 2 ; i++) {
			for (int j = -1; j < 2 ; j++) {
				int tr = curr.row + i;
				int tc = curr.col + j;
				//새로 할당한 좌표가 가능한지 검사, 중앙일 경우 방문체크 검사
				if(!isValid(new Log(tr, tc, curr.isTurned), i == 0 && j == 0)) return false;
			}
		}
		return true;
	}

	private static boolean isMovable(Log curr, int dir) {
		//가로일 경우 col +-1 검사, 세로면 row +-1 검사
		for (int i = -1; i < 2 ; i++) {
			int row = curr.row + (curr.isTurned ? 0 : i);
			int col = curr.col + (curr.isTurned ? i : 0) ;
			//새로 할당한 좌표가 가능한지 검사, 중앙일 경우 방문체크 검사
			if(!isValid(new Log(row, col, curr.isTurned), i == 0))  return false;
		}
		return true;
	}

	private static boolean isValid(Log curr, boolean isCenter) {
		int r = curr.row;
		int c = curr.col;
		//중앙 비트마스크 검사용 변수
		int t = curr.isTurnedToInteger();

		//벽 및 경계 검사
		if (r >= N || r < 0 || c >= N || c < 0 || map[r][c])    return false;
		//중앙좌표가 아니면 true, 중앙좌표면 방문체크
		return !isCenter || (visited[r][c] & 1 << t) == 0;
	}

	public static void main(String[] args) throws IOException{
		init();
		System.out.println(bfs());
	}
}
