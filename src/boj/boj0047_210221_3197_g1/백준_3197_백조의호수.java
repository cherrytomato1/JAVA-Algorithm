package boj.boj0047_210221_3197_g1;

import java.util.*;
import java.io.*;

class Node{
    int row;
    int col;

    public Node(int row, int col){
        this.row = row;
        this.col = col;
    }
}
public class 백준_3197_백조의호수 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st ;

    static final int[][] DIR = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    static int R;
    static int C;
    // 맵
    static boolean[][] map;
    // 방문 0 = 물, 1 = 백조
    static boolean[][][] visited ;

    static Node[] bj;
    //큐 0 = 현재 탐색 물, 1 = 내일 탐색 물, 2 = 백조, 3 =내일 탐색 백조
    static Deque<Node>[] dq ;

    static void init() throws IOException{
        st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new boolean[R][C];
        visited = new boolean[2][][];
        visited[0] = new boolean[R][C];
        visited[1] = new boolean[R][C];

        dq = new Deque[4];
        bj = new Node[2];
        for(int i = 0 ; i < 4; i++){
            dq[i] = new ArrayDeque<>();
        }

        for(int r = 0, i = 0; r < R; r++){
            char[] in = br.readLine().toCharArray();
            for(int c = 0; c < C ; c++){
                map[r][c] = in[c] == 'X';
                if(!map[r][c]){
                    dq[0].offer(new Node(r, c));
                    visited[0][r][c] = true;
                }
                if(in[c] == 'L')    bj[i++] = new Node(r, c);
            }
        }
        dq[3].offer(bj[0]);
    }
    static int times;
    static boolean bfs(boolean type){
        //queue 의 타입은 뮬일 때 0, 1 그리고 백조일 때 2,3 입니다.
        int dqType = type ? 0 : 2;
        //방문배열의 타입입니다. 물과 백조입니다.
        int visitedType = type ? 0 : 1;

        //물과 백조의 탐색에서 각각 다음날 탐색해야 하는 좌표들을 오늘 탐색하는 큐로 가져옵니다.
        while(!dq[dqType + 1].isEmpty()) dq[dqType].offer(dq[dqType + 1].poll());
        Node curr;
        //오늘 탐색해야 하는 큐가 빌 때까지 탐색합니다.
        while(!dq[dqType].isEmpty()){
            curr = dq[dqType].poll();
            for(int i = 0 ; i < 4; i++){
                int nr = curr.row + DIR[i][0];
                int nc = curr.col + DIR[i][1];

                if(nr < 0 || nr >= R || nc < 0 || nc >= C || visited[visitedType][nr][nc])  continue;
                visited[visitedType][nr][nc] = true;

                //얼음을 만났을 경우 물 탐색때 얼음을 지워주고, 백조와 물일 때 둘다 내일 탐색해야 하는 큐로 넘깁니다.
                if(map[nr][nc]) {
                    if(type)    map[nr][nc] = false;
                    dq[dqType + 1].offer(new Node(nr, nc));
                }
                // 백조로 탐색할 때 다른 백조를 만나면 종료합니다.
                else if(!type && nr == bj[1].row && nc == bj[1].col) return true;
                // 탐색한 좌표가 물이면 오늘 탐색할 좌표에 추가합니다.
                else            dq[dqType].offer(new Node(nr, nc));
            }
        }
        return false;
    }

    static int solve(){
        do{
            bfs(true);
            times ++;
        } while(!bfs(false));
        return times;
    }


    public static void main(String[] args) throws IOException{
        init();
        System.out.println(solve());
    }
}
