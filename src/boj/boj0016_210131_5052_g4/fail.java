package boj.boj0016_210131_5052_g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class fail {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static List<String> list;
    static int t ;
    static int N ;

    public static String solve() throws IOException {
        N = Integer.parseInt(br.readLine());

        list = new ArrayList<>();
        for(int i = 0 ; i < N ; i++){
            String str = br.readLine();

            for(int j = 0 ; j < list.size(); j++){
                String temp;
                if(str.length() <= list.get(j).length()){
                    temp = list.get(j).substring(0,str.length());
                    if(temp.equals(str)){
                        return "NO";
                    }
                } else{
                    temp = str.substring(0,list.get(j).length());
                    if(temp.equals(list.get(j))){
                        return "NO";
                    }
                }
            }
            list.add(str);
        }
        return "YES";
    }

    public static void main(String[] args) throws IOException {
        t = Integer.parseInt(br.readLine());

        for(int i = 0 ; i < t ; i++){
            System.out.println(solve());
        }

    }
}
