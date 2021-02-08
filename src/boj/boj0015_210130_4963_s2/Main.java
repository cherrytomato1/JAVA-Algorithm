package boj.boj0015_210130_4963_s2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[][] map;
    static int row;
    static int col;
    static Queue<Integer[]> bfsQ = new LinkedList<>();


    static int[] dr = {-1, -1, -1, 0, 1, 1, 1, 0};
    static int[] dc = {-1, 0, 1, 1, 1, 0, -1, -1};


    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


    static int dfs(int r, int c){
        map[r][c] = -1;
        int nr;
        int nc;
        for(int i = 0 ; i < 8; i++) {
            nr = r + dr[i];
            nc = c + dc[i];
            if (nr >= row || nr < 0 || nc >= col || nc < 0) {
                continue;
            }

            /** 주변 육지 찾음 */
            if (map[nr][nc] == 1) {
                dfs(nr, nc);
            }
        }

        return 1;
    }

    static int bfs(int r, int c){
        Integer [] pos;
        int nr;
        int nc;
        bfsQ.add(new Integer[]{r, c});
        map[r][c] = -1;

        while(!bfsQ.isEmpty()) {
            pos = bfsQ.poll();
            r = pos[0];
            c = pos[1];


            for (int i = 0; i < 8; i++) {
                nr = r + dr[i];
                nc = c + dc[i];
                if (nr >= row || nr < 0 || nc >= col || nc < 0) {
                    continue;
                }
                if(map[nr][nc] == 1){
                    map[nr][nc] = -1;
                    bfsQ.offer(new Integer[]{nr, nc});
                }
            }
        }
        return 1;
    }

    static Boolean input() throws IOException {
        String[] size = br.readLine().split(" ");
        col = Integer.parseInt(size[0]);
        row = Integer.parseInt(size[1]);

        if(col == 0 && row == 0) return false;
        map = new int[row][col];

        StringTokenizer st;
        for(int r = 0 ; r < row; r++){
            st = new StringTokenizer(br.readLine());
            for(int c = 0 ; c < col ; c++){
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        return true;
    }

    static int solve(){
        int count = 0;

        for(int r = 0 ; r < row ; r ++){
            for(int c = 0 ; c < col ; c++){
                if( map[r][c] == 1){
//                    count += dfs(r, c);
                    count += bfs(r,c);
                }
            }
        }
        return count;
    }

    public static void main(String[] args) throws IOException {
        while(input()) {
            System.out.println(solve());
        }
    }
}
