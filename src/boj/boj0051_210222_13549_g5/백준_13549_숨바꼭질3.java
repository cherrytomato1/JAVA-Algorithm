package boj.boj0051_210222_13549_g5;

import java.io.*;
import java.util.*;

class Node{
    int pos;
    int cnt;
    public Node(int pos, int cnt){
        this.pos = pos;
        this.cnt = cnt;
    }
}

public class 백준_13549_숨바꼭질3 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static final int MAX = 100000;
    static int N;
    static int K;
    static int times = Integer.MAX_VALUE;
    static int[] arr;
    static Deque<Node> dq = new ArrayDeque<>();

    static void bfs(Node start){
        dq.offer(start);
        while(!dq.isEmpty()){
            Node curr = dq.poll();
            int cnt = curr.cnt;
            int pos = curr.pos;

            if(pos >= K){
                cnt += pos - K;
                times = times < cnt ? times : cnt;
                continue;
            }

            if(cnt >= arr[pos] || cnt >= times)    continue;
            arr[pos] = cnt;

            if(pos != 0) {
                dq.offer(new Node(pos << 1, cnt));
                dq.offer(new Node(pos - 1, cnt + 1));
            }
            dq.offer(new Node(pos + 1, cnt + 1));

        }
    }

    public static void main(String[] args) throws IOException{
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[MAX + 1];
        Arrays.fill(arr, Integer.MAX_VALUE);
        bfs(new Node(N, 0));
        System.out.println(times);
    }
}
