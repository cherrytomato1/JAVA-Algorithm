package boj.boj0169_210724_1655_g2;

import java.util.*;
import java.io.*;

public class 백준_1655번_가운데를말해요 {
	public static void main(String[] args) throws IOException{
		System.out.println(solve());
	}

	private static String solve() throws IOException {
		InputStream in = System.in;
		BufferedReader br = new BufferedReader(new InputStreamReader(in));

		int N = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		//최대 힙과 최소 힙으로 설정, 중간 값은 최대 힙의 탑
		Queue<Integer> minHeap = new PriorityQueue<>(N);
		Queue<Integer> maxHeap = new PriorityQueue<>(N, Comparator.reverseOrder());

		//첫 번째 값은 최대힙에 넣기
		while (N-- > 0) {
			//값 받기
			int val = Integer.parseInt(br.readLine());
			maxHeap.offer(val);

			//힙 간 옮기기, 이로 인해 최대 힙은 최소 힙보다 1개 많거나 같아짐
			while (maxHeap.size() > minHeap.size()) {
				minHeap.offer(maxHeap.poll());
			}
			while (maxHeap.size() < minHeap.size()) {
				maxHeap.offer(minHeap.poll());
			}
			sb.append(maxHeap.peek()).append("\n");
		}

		return sb.toString();
	}
}
