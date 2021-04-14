package boj.boj0025_210209_7576_s1;

import java.io.*;
import java.util.*;

public class Main {

    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N;
    static int M;
    static int[][] map;
    static Queue<int[]> q = new ArrayDeque<>();

    static final int[] dr = {-1, 1, 0, 0};
    static final int[] dc = {0, 0, -1, 1};

    static int bfs(){
        int[] pos;
        int res = 0;
        while(!q.isEmpty()){
            for(int size = q.size(); size != 0; size--){
                pos = q.poll();
                for(int i = 0 ; i < 4; i++) {
                    int nr = pos[0] + dr[i];
                    int nc = pos[1] + dc[i];
                    if (nr >= N || nr < 0 || nc >= M || nc < 0 || map[nr][nc] != 0) continue;

                    map[nr][nc] = 1;
                    q.offer(new int[]{nr, nc});
                }
            }
            res ++;
        }
        for(int i = 0 ; i < N ; i++){
            for(int j = 0 ; j < M ; j++){
                if(map[i][j] == 0) return -1;
            }
        }
        //마지막으로 익힌 토마토에서도 주변 탐색을 하기 때문에 날짜가 하나 많으므로 -1 해준다.
        return res - 1;
    }


    public static void main(String[] args) throws IOException{
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < M ; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1) q.offer(new int[]{i, j});
            }
        }
        System.out.println(bfs());
    }
}
