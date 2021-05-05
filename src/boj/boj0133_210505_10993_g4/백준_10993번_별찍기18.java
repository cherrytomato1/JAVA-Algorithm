package boj.boj0133_210505_10993_g4;

import java.io.*;

public class 백준_10993번_별찍기18 {

	public static void main(String[] args) throws IOException{
		System.out.println(solve(init()));
	}

	private static int init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		return Integer.parseInt(br.readLine());
	}

	private static String solve(int N) {
		//시작 조건 N == 1 -> *
		char[][] arr = new char[1][1];
		arr[0][0] = '*';

		//상향식 재귀로 배열 만들어오기
		arr = recur(1, 1, N, 1, arr);
		//만들어진 배열을 스트링빌더에 넣기
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < arr.length ; i++) {
			for (int j = 0; j < arr[i].length ; j++) {
				//개행문자로 대치되었다면 * 하나 추가하고 개행
				if(arr[i][j] == '\n'){
					sb.append("*\n");
					break;
				}
				//*이면 * 아니면 공백
				sb.append(arr[i][j] == '*' ? arr[i][j] : ' ');
			}
		}
		return sb.toString();
	}

	/*
		@params prevWidth   : 이전 삼각형의 가로길이
		@params prevHeight  : 이전 삼각형의 세로길이
		@params N           : 입력 값 N(기저조건)
		@params depth       : 삼각형을 만든 재귀 깊이
		@params prevArr     : 지금까지 만들어진 삼각형 배열
	 */
	private static char[][] recur(int prevWidth, int prevHeight, int N, int depth, char[][] prevArr) {
		if(depth == N)  {
			//기저조건 도착 시 *이 끝나는 지점에 개행문자 삽입 후 반환
			makeNewLineCharacter(prevArr, prevWidth, prevHeight, (depth - 1) % 2 == 0);
			return prevArr;
		}
		//새 삼각형 세로 => 이전 길이 + 2
		int height = prevWidth + 2;
		//새 삼각형 가로 => 세로 * 2 - 1
		int width = height * 2 - 1;
		//짝수 depth인지 홀수 depth인지 -> 짝수 true
		boolean type = depth % 2 == 1;

		//가로 및 세로에 맞게 새로 만들 삼각형 배열 생성
		char[][] arr = new char[height][width];
		//가로 세로에 맞게 새 삼각형 테두리 그리기
		drawLine(arr, width, height, type);
		//이전까지 만들어진 삼각형 위치에 맞게 복사하기
		copyPrev(prevArr, arr, prevHeight, type);
		//재귀
		return recur(width, height, N, depth + 1, arr);
	}

	//완성된 모양의 *이 끝나는 지점에 개행문자를 삽입, depth가 짝수인지 홀수인지에 따라 시작 위치 결정
	private static void makeNewLineCharacter(char[][] arr, int width, int height, boolean type) {
		int startRow = type ? 0 : (height - 1);
		for (int r = startRow, c = width/2 ; r < height && r >= 0; r += type ? 1 : -1, c++) {
			arr[r][c] = '\n';
		}
	}

	//테두리 그리기
	//dethp의 홀수 짝수 여부에 따라 그릴 위치 결정
	private static void drawLine(char[][] arr, int width, int height, boolean type) {
		for (int low = 0, high = width - 1; low <= high ; low++, high--) {
			//짝수면 맨 윗줄, 아니면 맨 아랫줄에 직선 그리기
			int rowPos = type ? 0 : (height - 1);
			arr[rowPos][low] = '*';
			arr[rowPos][high] = '*';

			//한칸씩 증가하며 테두리를 그릴 위치를 짝수홀수로 결정
			rowPos = type ? low : (height - 1 - low);
			arr[rowPos][low] = '*';
			arr[rowPos][high] = '*';
		}
	}

	//복사하기
	private static void copyPrev(char[][] prev, char[][] arr, int prevHeight, boolean type) {
		int startRow = type ? 1 : prevHeight;
		int startCol = prevHeight + 1;
		for (int i = 0; i < prev.length ; i++) {
			for (int j = 0; j < prev[i].length ; j++) {
				arr[i + startRow][j + startCol] = prev[i][j];
			}
		}
	}


}
