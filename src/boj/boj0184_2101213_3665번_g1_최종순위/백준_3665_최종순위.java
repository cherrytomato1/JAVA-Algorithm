package boj.boj0184_2101213_3665번_g1_최종순위;


import java.util.*;
import java.io.*;

public class 백준_3665_최종순위 {
	private static class Vertex implements Comparable<Vertex>{
		int index;
		int inDegrees;
		Set<Vertex> childSet;

		public Vertex(int inDegrees, int index) {
			this.index = index;
			this.inDegrees = inDegrees;
			childSet = new HashSet<>();
		}

		@Override
		public int compareTo(Vertex v) {
			return Integer.compare(this.index, v.index);
		}
	}

	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws IOException {
		int tc = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < tc; i++) {
			List<Vertex> vertexList = new ArrayList<>();
			int n = init(vertexList);
			sb.append(solve(n, vertexList)).append("\n");
		}
		System.out.println(sb);

	}

	private static int init(List<Vertex> vertexList) throws IOException {
		int n = Integer.parseInt(br.readLine());

		String[] tokens = br.readLine().split(" ");

		for (int i = tokens.length - 1; i >= 0 ; i--){
			Vertex newVertex = new Vertex(0, Integer.parseInt(tokens[i]));
			newVertex.childSet.addAll(vertexList);
			vertexList.add(newVertex);
		}
		vertexList.add(new Vertex(Integer.MAX_VALUE, 0));
		Collections.sort(vertexList);

		int m = Integer.parseInt(br.readLine());

		for (int i = 0; i < m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			Vertex from = vertexList.get(Integer.parseInt(st.nextToken()));
			Vertex to = vertexList.get(Integer.parseInt(st.nextToken()));

			if (from.childSet.contains(to)) {
				Vertex temp = to;
				to = from;
				from = temp;
			}

			Vertex finalFrom = from;
			Vertex finalTo = to;
			vertexList.forEach(v -> {
				if (!v.equals(finalFrom) && v.childSet.contains(finalTo) && !finalFrom.childSet.contains(v)) {
					v.childSet.add(finalFrom);
				}
			});

			to.childSet.remove(from);
			from.childSet.add(to);
		}
		for (int i = 1; i <= n; i++) {
			Vertex vertex = vertexList.get(i);
			vertex.childSet.forEach(v -> v.inDegrees++);
		}
		return n;
	}

	private static String solve(int n, List<Vertex> vertexList) {
		StringBuilder sb = new StringBuilder();
		Queue<Vertex> q = new ArrayDeque<>();

		for (int i = 1; i <= n ; i++) {
			Vertex vertex = vertexList.get(i);
			if (vertex.inDegrees == 0) {
				q.offer(vertex);
			}
		}

		for (int i = 0; i < n ; i++) {
			if (q.size() == 0) {
				return "IMPOSSIBLE";
			}

			if (q.size() > 1) {
				return "?";
			}

			Vertex vertex = q.poll();
			sb.append(vertex.index).append(" ");
			vertex.childSet.forEach( v -> {
				if (--v.inDegrees == 0) {
					q.offer(v);
				}
			});
		}

		return sb.substring(0, sb.length() - 1);
	}
}
