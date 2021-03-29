package boj.boj0092_210329_10775_g2;

import java.util.*;
import java.io.*;

public class 백준_10775번_공항 {
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	private static int[] gates;
	private static int G;
	private static int P;

	private static void init() throws IOException {
		G = Integer.parseInt(br.readLine());
		P = Integer.parseInt(br.readLine());
		gates = new int[G + 1];
		for (int i = 1; i <= G ; i++) {
			gates[i] = i;
		}
	}

	private static int solve() throws IOException {
		for (int i = 0; i < P; i++) {
			int gate = Integer.parseInt(br.readLine());
			if(!findGate(gate)) return i;
		}
		return P;
	}

	private static boolean findGate(int index) {
		while(gates[index] != 0){
			if(gates[index] == index){
				gates[index]--;
				return true;
			}
			index = gates[index]--;
		}
		return false;
	}

	public static void main(String[] args) throws IOException{
		init();
		System.out.println(solve());
	}
}
