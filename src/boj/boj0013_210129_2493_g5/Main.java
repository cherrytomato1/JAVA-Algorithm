package boj.boj0013_210129_2493_g5;

import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int[] tower;
    static int[] ans;
    static Deque<Integer> height = new ArrayDeque<>();
    static Deque<Integer> index = new ArrayDeque<>();

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        tower = new int[N];
        ans = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; i ++){
            tower[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void solve() {
        for(int i = N - 1 ; i >= 0 ; i--){
            while(!height.isEmpty() && height.getFirst() <= tower[i]){
                ans[index.pop()] = i + 1;
                height.pop();
            }
            height.push(tower[i]);
            index.push(i);
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < N ; i++){
            sb.append(ans[i]).append(" ");
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }
}
