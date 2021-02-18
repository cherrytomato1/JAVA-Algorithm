package jungol.jungol_1828_냉장고;

import java.util.*;
import java.io.*;

class Matter{
    int start;
    int end;
    public Matter(int start, int end){
        this.start = start;
        this.end = end;
    }
    @Override
    public String toString(){
        return start + ", " + end;
    }
}

public class Main_1828_냉장고 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N;
    static Matter[] mt;

    static void init() throws IOException{
        N = Integer.parseInt(br.readLine());
        mt = new Matter[N];

        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            mt[i] = new Matter(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
//        Arrays.sort(mt, (o1, o2) -> o1.end - o2.end);
        Arrays.sort(mt, Comparator.comparingInt(o -> o.end));
    }

    static int solve(){
        int cnt = 1;
        Matter curr = mt[0];
        for(int i = 1 ; i < N ; i++){
            if(curr.end >= mt[i].start) continue;
            cnt++;
            curr = mt[i];
        }
        return cnt;
    }

    public static void main(String[] args) throws IOException{
        init();
        System.out.println(solve());
    }


}
