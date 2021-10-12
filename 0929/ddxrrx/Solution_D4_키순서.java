package SWA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_D4_키순서 {

	static int N, M, adj[][];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(in.readLine());

		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");

			N = Integer.parseInt(in.readLine());
			M = Integer.parseInt(in.readLine());
			adj = new int[N + 1][N + 1];

			for (int i = 0; i < M; i++) {
				StringTokenizer st = new StringTokenizer(in.readLine(), " ");
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				adj[from][to] = 1;
			}

			int ans = 0;
			for (int i = 1; i <= N; i++) {
				if (gtBFS(i) + ltBFS(i) == N - 1)
					++ans;
			}
			sb.append(ans).append("\n");
		}
		System.out.println(sb.toString());
	}

	// 자신보다 큰
	private static int gtBFS(int start) {
		Queue<Integer> queue = new LinkedList<Integer>();
		boolean[] visited = new boolean[N + 1];

		queue.offer(start);
		visited[start] = true;

		int cnt = 0;
		while (!queue.isEmpty()) {
			int cur = queue.poll();

			for (int i = 1; i <= N; i++) {
				if (!visited[i] && adj[cur][i] == 1) {
					queue.offer(i);
					visited[i] = true;
					++cnt;
				}
			}
		}
		return cnt;
	}

	// 자신보다 작은
	private static int ltBFS(int start) {
		Queue<Integer> queue = new LinkedList<Integer>();
		boolean[] visited = new boolean[N + 1];

		queue.offer(start);
		visited[start] = true;

		int cnt = 0;
		while (!queue.isEmpty()) {
			int cur = queue.poll();

			for (int i = 1; i <= N; i++) {
				if (!visited[i] && adj[i][cur] == 1) {
					queue.offer(i);
					visited[i] = true;
					++cnt;
				}
			}
		}
		return cnt;
	}

}
