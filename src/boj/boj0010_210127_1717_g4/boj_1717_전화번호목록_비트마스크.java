package boj.boj0010_210127_1717_g4;

import java.util.*;
import java.io.*;

public class boj_1717_전화번호목록_비트마스크 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int N ;
    static Integer[] arr;

    static void init() throws IOException{
        N = Integer.parseInt(br.readLine());
        arr = new Integer[N];

        for(int i = 0 ; i < N ; i++){
            //... 문자열의 공백을 지우고 스트링빌더에 담은 후 뒤집은 다음 다시 문자열로 반환.. 한 것을 int 형으로 배열에 저장...
            arr[i] = Integer.parseInt(new StringBuilder(br.readLine().trim()).reverse().toString());
        }
        Arrays.sort(arr, (o1, o2) -> {
            while(o1 % 10 == o2 % 10 ){
                o1 /= 10;
                o2 /= 10;
                if(o1 == 0) return -1;
                else if(o2 == 0) return 1;
            }
            return o1 % 10 > o2 % 10 ? 1 : -1;
        });
        System.out.println(Arrays.toString(arr));
    }

    public static void main(String[] args) throws IOException{
        int T = Integer.parseInt(br.readLine());

        for(int tc = 1 ; tc <= T ; tc++){
            init();
        }
    }
}
