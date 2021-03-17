package boj.boj0010_210127_5052_g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

class Linker{
    Linker link;
//    Linker(Linker link){
//        this.link = link;
//    }
    @Override
    public String toString(){
        return Integer.toString(hashCode());
    }
}

public class Fail_timeout {
    static int N;
    static int M;
    static Linker[] linker;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static Scanner sc = new Scanner(System.in);

    static void solve() throws IOException{
        int input;
        int a;
        int b;
        int temp;
        Linker tempLinker;
        Linker tempLinker2;

        for (int i = 0; i < M; i++) {
//            input = br.read() - '0';
//            a = br.read() - '0';
//            b = br.read() - '0';
            input = sc.nextInt();
            a = sc.nextInt();
            b = sc.nextInt();
//            System.out.println(input);
            if( a > b ){
                temp = a;
                a = b;
                b = temp;
            }
//            System.out.println("for running");
            if(input == 0) {
//                if ( linker[a] == null){
//                    linker[a].link = linker[a];
//                }
//                && tempLinker.link != tempLinker
                tempLinker = linker[b];
                while (tempLinker.link != null) {
//                    System.out.println("0 running");
                    tempLinker = tempLinker.link;
                }
//                System.out.println();
                tempLinker.link = linker[a];

            } else if(input == 1) {
                tempLinker = linker[a];
                while (tempLinker.link != null && tempLinker.link != tempLinker) {
//                    System.out.println("1 - 1 running" + tempLinker + tempLinker.link);
                    tempLinker = tempLinker.link;
                }

                tempLinker2 = linker[b];
                while (tempLinker2.link != null && tempLinker2.link != tempLinker2) {
//                    System.out.println("1 - 2 running " + tempLinker + tempLinker.link);
                    tempLinker2 = tempLinker2.link;
                }

                if(tempLinker == tempLinker2 && tempLinker != null ){
                    System.out.println("YES");
                } else {
                    System.out.println("NO");
                }

            }
        }
    }

    public static void main(String[] args) throws IOException {

//        N = br.read() - '0';
//        M = br.read() - '0';
        N = sc.nextInt();
        M = sc.nextInt();
//        System.out.println(N + M);
        linker = new Linker[N + 1];
        for(int i = 0 ; i <= N ; i++){
            linker[i] = new Linker();
        }
        solve();
    }
}
