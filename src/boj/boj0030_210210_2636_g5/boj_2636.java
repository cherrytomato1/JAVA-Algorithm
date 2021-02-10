package boj.boj0030_210210_2636_g5;

import java.util.*;
import java.io.*;

public class boj_2636 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static final int[] dr = {-1, 1, 0, 0};
    static final int[] dc = {0, 0, -1, 1};

    static int R;
    static int C;
    static int cheese;
    static boolean[][] map;
    static boolean[][] visited;
    static Queue<int[]> q = new LinkedList<>();
    static Queue<int[]> q2 = new LinkedList<>();

    static int f(){
        int times = 0;
        while(cheese != 0){
            times++;
            while(!q.isEmpty()){
                int pos[] = q.poll();
                for(int i = 0 ; i < 4 ; i ++){
                    int nr = pos[0] + dr[i];
                    int nc = pos[1] + dc[i];

                    if(nr >= R || nr < 0 || nc >= C || nc < 0 || visited[nr][nc]) continue;

                    visited[nr][nc] = true;
                    if(map[nr][nc]){
                        q2.offer(new int[]{nr, nc});
                        cheese--;
                        continue;
                    }
                    q.offer(new int[]{nr, nc});
                }
            }
            while(!q2.isEmpty())    q.offer(q2.poll());
        }
        return times;
    }

    public static void main(String[] args) throws IOException{
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new boolean[R][C];
        visited = new boolean[R][C];

        for(int r = 0 ; r < R ; r++){
            st = new StringTokenizer(br.readLine());
            for(int c = 0 ; c < C ; c++){
                if(map[r][c] = st.nextToken().equals("1")) cheese++;
                else if(r == 0 || c == 0) q.offer(new int[]{r, c});
            }
        }
        sb.append(f()).append("\n").append(q.size());
        System.out.println(sb);
    }
}
