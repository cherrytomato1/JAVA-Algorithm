package programmers.퍼즐조각채우기;

import java.util.*;

class Solution {

	int[][][] table;

	Map<String, List<Integer>> blockMap;
	int[] blockCounter;

	final int[][] DIR = {{1, -1, 0, 0}, {0, 0, 1, -1}};

	int N;

	public int solution(int[][] game_board, int[][] table) {
		N = game_board.length;
		blockMap = new HashMap<>();
		blockCounter = new int[N * N / 2];

		setTableIndex(table);

		makeRotatedTable(table);

		makeBlock();

		return findAtBoard(game_board);
	}

	private void setTableIndex(int[][] table) {
		int idx = 0;

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				table[r][c] -= 2;
			}
		}

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (table[r][c] != -1) {
					continue;
				}
				setTableIndex(r, c, -1, idx++, table);
			}
		}
	}

	private void setTableIndex(int row, int col, int target, int res, int[][] table) {

		if (row < 0 || row >= N || col < 0 || col >= N || table[row][col] != target) {
			return;
		}
		table[row][col] = res;
		for (int i = 0; i < 4; i++) {
			int nr = row + DIR[0][i];
			int nc = col + DIR[1][i];

			setTableIndex(nr, nc, target, res, table);
		}
	}

	private void makeRotatedTable(int[][] table) {
		this.table = new int[4][][];

		for (int i = 0; i < 4; i++) {
			this.table[i] = table;
			table = rotate(table, N);
		}
	}

	private int[][] rotate(int[][] map, int size) {
		int[][] newMap = new int[size][size];
		for (int r = 0; r < size; r++) {
			for (int c = 0; c < size; c++) {
				newMap[c][size - r - 1] = map[r][c];
			}
		}
		return newMap;
	}

	private void makeBlock() {
		for (int i = 0; i < 4; i++) {
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					int idx = table[i][r][c];
					if (idx == -2) {
						continue;
					}

					String key = makeBlock(table[i], r, c, idx);

//					if (blockMap.get(key) == null) {
//						blockMap.put(key, new ArrayList<>());
//					}
					blockMap.computeIfAbsent(key, k -> new ArrayList<>());

					blockMap.get(key).add(idx);
					blockCounter[idx]++;
				}
			}
		}
	}

	private String makeBlock(int[][] table, int row, int col, int target) {
		StringBuilder sb = new StringBuilder();
		List<int[]> list = new ArrayList<>();

		int minRow = row;
		int minCol = col;

		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[]{row, col});
		table[row][col] = -2;

		while (!q.isEmpty()) {
			int[] curr = q.poll();
			list.add(curr);
			minRow = Math.min(curr[0], minRow);
			minCol = Math.min(curr[1], minCol);
			for (int i = 0; i < 4; i++) {
				int nr = curr[0] + DIR[0][i];
				int nc = curr[1] + DIR[1][i];
				if (nr < 0 || nr >= N || nc < 0 || nc >= N || table[nr][nc] != target) {
					continue;
				}

				table[nr][nc] = -2;
				q.offer(new int[]{nr, nc});
			}
		}
		sb.append(list.size()).append(':');
		for (int[] pos : list) {
			sb.append(pos[0] - minRow).append(",").append(pos[1] - minCol).append(" / ");
		}
		return sb.toString();
	}

	public int findAtBoard(int[][] board) {
		int ret = 0;

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (board[r][c] != 0) {
					continue;
				}
				String key = makeBlock(board, r, c, 0);

				List<Integer> val = blockMap.get(key);
				if (val == null) {
					continue;
				}

				System.out.println(key);
				boolean flag = false;
				for (Integer idx : val) {
					if ((blockCounter[idx] -= 4) >= 0) {
						flag = true;
						break;
					}
				}
				ret += flag ? key.charAt(0) - '0' : 0;
			}
		}

		return ret;
	}
}