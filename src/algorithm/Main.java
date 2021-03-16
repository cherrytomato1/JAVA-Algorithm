package algorithm;

import java.io.IOException;

public class Main {
	public static void main(String[] args) throws IOException {
		GraphSearchList.in();
		GraphSearchList.bfs(0);
		GraphSearchList.dfs(0, 0);
	}
}
