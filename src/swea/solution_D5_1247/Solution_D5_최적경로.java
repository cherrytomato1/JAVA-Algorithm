package swea.solution_D5_1247;

import java.io.*;
import java.util.*;

class Node implements Comparable<Node>{
    int row;
    int col;

    public Node(int col, int row){
        this.row = row;
        this.col = col;
    }
    @Override
    public int compareTo(Node o1){
          return this.row - o1.row == 0 ? this.col - o1.col : this.row - o1.row;
    }
}

public class Solution_D5_최적경로 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N;
    static Node house;
    static Node work;
    static Node[] cust;
    static int res;
    
    static void init() throws IOException{
        res = Integer.MAX_VALUE;
        N = Integer.parseInt(br.readLine());
        cust = new Node[N];

        st = new StringTokenizer(br.readLine());
        work = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        house = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        for(int i = 0 ; i < N ; i++){
            cust[i] = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
    }
    
    static void dfs(int idx, int sum, int flag, Node preNode){
        if(idx == N)    {
            res = Math.min(res, sum + getDist(preNode, house));
            return;
        }
        if(sum >= res)      return;

        for(int i = 0 ; i < N ; i++){
            if((flag & 1 << i) != 0)    continue;
            dfs(idx + 1, sum + getDist(preNode, cust[i]), flag | 1 << i, cust[i]);
        }
    }

    static void perm(){
        Arrays.sort(cust);
        do{
            int sum = getDist(work, cust[0]);
            for(int i = 1; i < N ; i++){
                sum += getDist(cust[i - 1], cust[i]);
            }
            res = Math.min(res, sum + getDist(cust[N - 1], house));
        }while(np());
    }

    static boolean np(){
        int lastIdx = N - 1;
        int i = lastIdx;

        while(i > 0 && cust[i - 1].compareTo(cust[i]) >= 0) i--;
        if(i == 0)  return false;

        int j = lastIdx;
        while(cust[i - 1].compareTo(cust[j]) >= 0) j--;
        custSwap(i - 1, j);

        int k = lastIdx;
        while(k > i){
            custSwap(k--, i++);
        }
        return true;
    }

    static void custSwap(int idx1, int idx2){
        Node temp = cust[idx1];
        cust[idx1] = cust[idx2];
        cust[idx2] = temp;
    }


    static int getDist(Node n1, Node n2){
        return Math.abs(n1.row - n2.row) + Math.abs(n1.col - n2.col);
    }

    public static void main(String[] args) throws IOException{
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1 ; tc <= T ; tc++){

            sb.append("#").append(tc).append(" ");
            init();
//            dfs(0, 0, 0, work);
            perm();
            sb.append(res).append("\n");
        }
        System.out.println(sb);
    }
}


