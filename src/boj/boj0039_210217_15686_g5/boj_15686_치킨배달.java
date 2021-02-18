package boj.boj0039_210217_15686_g5;

import java.util.*;
import java.io.*;

public class boj_15686_치킨배달 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st ;

    static final int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    static int N;
    static int M;
    static int chickenCnt;
    static int houseCnt;

    static char[][] map;
    static boolean[][] visited;

    static Node[] chicken ;
    static Node[] house ;

    static Deque<Node> q;

    static void init() throws IOException{
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        chicken = new Node[N * N];
        house = new Node[N * N];

        map = new char[N][N];

        for(int r = 0 ; r < N; r++){
            st = new StringTokenizer(br.readLine());
            for(int c = 0 ; c < N ; c++){
                map[r][c] = st.nextToken().charAt(0);
                if(map[r][c] == '1')        house[houseCnt++] = new Node(r, c);
                else if(map[r][c] == '2')   chicken[chickenCnt++] = new Node(r, c);
            }
        }
    }

    static int check(int[] flag){
        int cnt = 0;
        for(int i = 0 ; i < chickenCnt; i++){
            if(flag[i] == 1) map[chicken[i].row][chicken[i].col] = '3';
        }

        for(int i = 0; i < houseCnt; i++){
            cnt += bfs(i);
        }

        for(int i = 0 ; i < chickenCnt; i++){
            if(flag[i] == 1) map[chicken[i].row][chicken[i].col] = '2';
        }
        return cnt;
    }

    static int bfs(int idx){
        q = new ArrayDeque<>();
        visited = new boolean[N][N];

        q.offer(house[idx]);
        while(!q.isEmpty()){
            Node curr = q.poll();
            for(int i = 0 ; i < 4; i++){
                int nr = curr.row + dir[i][0];
                int nc = curr.col + dir[i][1];

                if(nr >= N || nr < 0 || nc >= N || nc < 0 || visited[nr][nc]) continue;

                if(map[nr][nc] == '3') return Math.abs(nr - house[idx].row) + Math.abs(nc - house[idx].col);

                visited[nr][nc] = true;
                q.offer(new Node(nr, nc));
            }
        }
        return -100;
    }

    static int solve(){
        int[] flag = new int[chickenCnt];
        int min = Integer.MAX_VALUE;
        int cnt ;
        for(int i = chickenCnt - 1; i >= chickenCnt - M ; i--){
            flag[i] = 1;
        }

        do{
            cnt = check(flag);
            min = Math.min(cnt, min);
        }while(np(flag));
        return min;
    }

    static boolean np(int[] flag){
        int lastIdx = chickenCnt - 1;
        int i = lastIdx;
        while( i > 0 && flag[i - 1] >= flag[i]) i--;
        if(i == 0)  return false;

        int j = lastIdx;
        while(flag[i - 1] >= flag[j]) j--;
        idxSwap(flag, i - 1, j);

        int k = lastIdx;
        while(k > i){
            idxSwap(flag, k--, i++);
        }
        return true;
    }

    static void idxSwap(int[] flag, int idx1, int idx2){
        int temp = flag[idx1];
        flag[idx1] = flag[idx2];
        flag[idx2] = temp;
    }

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(solve());
    }
}

//class Node {
//    int row;
//    int col;
//
//    public Node(int row, int col){
//        this.row = row;
//        this.col = col;
//    }
//}