package boj.boj0039_210217_15686_g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class boj_15686_치킨배달_탐색없이 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st ;

    static int N;
    static int M;
    static int chickenCnt;
    static int houseCnt;

    static Node[] chicken ;
    static Node[] house ;

    static void init() throws IOException{
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        chicken = new Node[N * N];
        house = new Node[N * N];

        for(int r = 0 ; r < N; r++){
            st = new StringTokenizer(br.readLine());
            for(int c = 0 ; c < N ; c++){
                char pos = st.nextToken().charAt(0);
                if(pos == '1')        house[houseCnt++] = new Node(r, c);
                else if(pos == '2')   chicken[chickenCnt++] = new Node(r, c);
            }
        }
    }

    static int check(int[] flag){
        int cnt = 0;

        for(int i = 0; i < houseCnt; i++){
            int row = house[i].row;
            int col = house[i].col;
            int dist = Integer.MAX_VALUE;
            for(int j = 0 ; j < chickenCnt; j++){
                if(flag[j] != 1)    continue;
                dist = Math.min(dist, Math.abs(chicken[j].row - row) + Math.abs(chicken[j].col - col));
            }
            cnt += dist;
        }
        return cnt;
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

class Node {
    int row;
    int col;

    public Node(int row, int col){
        this.row = row;
        this.col = col;
    }
}