package swea.solution_D5_1247;

import java.io.*;
import java.util.*;

class Node{
    int row;
    int col;

    public Node(int col, int row){
        this.row = row;
        this.col = col;
    }
    public String toString(){
        return "(" + this.row + ", " + this.col + ")";
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
        for(int i = 0 ; i < N ; i++){
            cust[i] = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        house = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

    }
    
    static void dfs(int idx, int sum, int flag, Node preNode){
        if(idx == N)    {
            System.out.println(sum + getDist(preNode, house));
            res = Math.min(res, sum + getDist(preNode, house));
        }

        if(sum >= res)  {
//            System.out.println(sum);
            return;
        }

        for(int i = 0 ; i < N ; i++){
            if((flag & 1 << i) != 0)    continue;
            dfs(idx + 1, sum + getDist(preNode, cust[i]), flag | 1 << i, cust[i]);
        }
    }

    static int getDist(Node n1, Node n2){
        return Math.abs(n1.row - n2.row) + Math.abs(n1.col - n2.col);
    }

    public static void main(String[] args) throws IOException{
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1 ; tc <= T ; tc++){

            sb.append("#").append(tc).append(" ");
            init();
            dfs(0, 0, 0, work);
            sb.append(res).append("\n");
        }
        System.out.println(sb);
    }
}


