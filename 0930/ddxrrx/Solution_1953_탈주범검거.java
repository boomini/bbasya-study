package hwalgo27_서울_07반_김사라;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_1953_탈주범검거 {

	static int T, N, M, R, C, L, result;
	static int[][] map;
	static int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	static boolean[][] visited;
	static Queue<int[]> q;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		T = Integer.parseInt(in.readLine());

		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");

			StringTokenizer st = new StringTokenizer(in.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken()); // N * M 크기의 지하철 터널
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken()); // 맨홀 뚜껑의 위치 (R, C)
			L = Integer.parseInt(st.nextToken()); // 탈출 후 소요한 시간 L

			map = new int[N][M];
			visited = new boolean[N][M];
			q = new LinkedList<>();
			result = 1;

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(in.readLine());
				for (int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			bfs();

			sb.append(result).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static void bfs() {
		int t = 0;

		q.offer(new int[] { R, C });
		visited[R][C] = true;

		while (!q.isEmpty()) {
			int size = q.size();
			if (++t >= L)
				return;

			for (int i = 0; i < size; ++i) {
				int[] cur = q.poll();
				int type = map[cur[0]][cur[1]];

				for (int d = 0; d < 4; ++d) {
					int r = cur[0] + dir[d][0];
					int c = cur[1] + dir[d][1];

					if (r < 0 || r >= N || c < 0 || c >= M)
						continue;
					if (visited[r][c] || map[r][c] == 0)
						continue;

					int next = map[r][c];

					switch (d) {
					case 0:
						if (type == 1 || type == 2 || type == 4 || type == 7) {
							if (next == 1 || next == 2 || next == 5 || next == 6) {
								visited[r][c] = true;
								q.offer(new int[] { r, c });
								result++;
							}
						}
						break;
					case 1:
						if (type == 1 || type == 2 || type == 5 || type == 6) {
							if (next == 1 || next == 2 || next == 4 || next == 7) {
								visited[r][c] = true;
								q.offer(new int[] { r, c });
								result++;
							}
						}
						break;
					case 2:
						if (type == 1 || type == 3 || type == 6 || type == 7) {
							if (next == 1 || next == 3 || next == 4 || next == 5) {
								visited[r][c] = true;
								q.offer(new int[] { r, c });
								result++;
							}
						}
						break;
					case 3:
						if (type == 1 || type == 3 || type == 4 || type == 5) {
							if (next == 1 || next == 3 || next == 6 || next == 7) {
								visited[r][c] = true;
								q.offer(new int[] { r, c });
								result++;
							}
						}
						break;
					}
				}
			}
		}

	}

}