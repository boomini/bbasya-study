import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class jo1681 {
	static int[][] dist;
	static int N, min;
	static boolean visit[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());

		dist = new int[N][N];
		visit = new boolean[N];
		min = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < N; j++) {
				dist[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		visit[0] = true;
		dfs(0, 0, 0);

		System.out.println(min);
	}

	static void dfs(int now, int sel, int cost) {
		if (sel == N - 1) {
			if (dist[now][0] == 0)
				return;
			cost += dist[now][0];
			min = Math.min(cost, min);
			return;
		}
		if (cost > min)
			return;

		for (int i = 0; i < N; i++) {

			if (visit[i])
				continue;
			if (dist[now][i] == 0)
				continue;

			visit[i] = true;
			dfs(i, sel + 1, cost + dist[now][i]);
			visit[i] = false;
		}
	}
}
