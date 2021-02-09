package boj.boj0026_210209_16236_g4;

import java.util.*;
import java.io.*;

public class boj_16236 {

    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st ;

    //                  상 좌  우  하
    static int[] dr = {-1, 0, 0, 1};
    static int[] dc = {0, -1, 1, 0};

    static int N;
    static int fish;
    static int res;
    static int[][] map;
    static Queue<int[]> q = new ArrayDeque<>();

    static boolean[][] visited;

    static void bfs(){
        int cnt = 0;
        int shark = 2;
        int point = 0;
        while(!q.isEmpty() && fish != 0){
            cnt ++;
            int rowGo = Integer.MAX_VALUE;
            int colGo = Integer.MAX_VALUE;
            boolean flag = false;

            for(int size = q.size() ; size != 0 ; size--){
                int[] pos = q.poll();
                for(int i = 0 ; i < 4; i++){
                    int nr = pos[0] + dr[i];
                    int nc = pos[1] + dc[i];

                    if(nr >= N || nr < 0 || nc >= N || nc < 0 )   continue;
                    if(visited[nr][nc] || map[nr][nc] > shark)   continue;

                    if(map[nr][nc] != 0 && map[nr][nc] < shark){
                        flag = true;
                        if(rowGo > nr) {
                            rowGo = nr;
                            colGo = nc;
                        }
                        else if(rowGo == nr){
                            colGo = colGo < nc ? colGo : nc;
                        }
                    }
                    visited[nr][nc] = true;
                    q.offer(new int[]{nr, nc});
                }
            }
            if(flag) {
                if(++point == shark) {
                    shark++;
                    point = 0;
                }
                getFish(cnt, rowGo, colGo);
            }
        }
    }

    static void getFish(int cnt, int r, int c){
        for(int i = 0 ; i < N ; i++){
            Arrays.fill(visited[i], false);
        }
        while(!q.isEmpty())     q.poll();
        fish--;
        res = cnt;
        map[r][c] = 0;

        visited[r][c] = true;
        q.offer(new int[]{r, c});
    }

    public static void main(String[] args) throws IOException{
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visited = new boolean[N][N];

        for(int r = 0 ; r < N ; r++){
            st = new StringTokenizer(br.readLine());
            for(int c = 0 ; c < N ; c++){
                map[r][c] = Integer.parseInt(st.nextToken());
                switch(map[r][c]){
                    case 9 :
                        map[r][c] = 0;
                        q.offer(new int[]{r, c});
                        break;
                    case 6 : case 5 : case 4 :
                    case 3 : case 2 : case 1 :
                        fish++;
                }
            }
        }
        bfs();
        System.out.println(res);
    }
}
