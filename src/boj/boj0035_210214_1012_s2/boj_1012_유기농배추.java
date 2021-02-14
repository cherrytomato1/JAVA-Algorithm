package boj.boj0035_210214_1012_s2;

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

public class boj_1012_유기농배추 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuffer sb = new StringBuffer();

    static final int[][] DIR = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 }};

    static int N;
    static int M;
    static int K;
    static boolean[][] map;
    static Deque<Node> que = new ArrayDeque<>();

    static void input() throws IOException{
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new boolean[N][M];

        for(int i = 0 ; i < K ; i++){
            st = new StringTokenizer(br.readLine());
            int X = Integer.parseInt(st.nextToken());
            int Y = Integer.parseInt(st.nextToken());
            map[Y][X] = true;
        }
    }

    static int solve(){
        int res = 0 ;
        for(int r = 0 ; r < N ; r++){
            for(int c = 0 ; c < M ; c++){
                if(map[r][c]) res += bfs(r, c);
            }
        }
        return res;
    }

    static int bfs(int row, int col){
        que.offer(new Node(row, col));
        while(!que.isEmpty()){
            Node curr = que.poll();

            for(int i = 0 ; i < 4 ; i++){
                int nr = curr.row + DIR[i][0];
                int nc = curr.col + DIR[i][1];

                if(nr >= N || nr < 0 || nc >= M || nc < 0 || !map[nr][nc]) continue;

                map[nr][nc] = false;
                que.offer(new Node(nr, nc));
            }
        }
        return 1;
    }

    public static void main(String[] args) throws IOException{
        int T = Integer.parseInt(br.readLine());

        for(int tc = 1 ; tc <= T ; tc++){
            input();
            sb.append(solve()).append("\n");
        }
        System.out.println(sb);
    }
}
