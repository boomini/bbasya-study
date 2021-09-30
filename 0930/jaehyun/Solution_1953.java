

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_1953 {

	static class State {

		int x, y, time;

		State(int x, int y, int time) {
			this.x = x;
			this.y = y;
			this.time = time;
		}

	}

	// 상 하 좌 우
	static int dx[] = { -1, 1, 0, 0 };
	static int dy[] = { 0, 0, -1, 1 };

	static int type[][] = { { -1, -1, -1, -1 }, { 1, 1, 1, 1 }, { 1, 1, 0, 0 }, { 0, 0, 1, 1 }, { 1, 0, 0, 1 },
			{ 0, 1, 0, 1 }, { 0, 1, 1, 0 }, { 1, 0, 1, 0 } };
	// 각 터널 구조물의 이동가능한 방향

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int TC = Integer.parseInt(in.readLine());

		for (int t = 1; t <= TC; t++) {
			sb.append("#").append(t).append(" ");

			st = new StringTokenizer(in.readLine(), " ");

			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int L = Integer.parseInt(st.nextToken());

			int map[][] = new int[n][m];
			boolean check[][] = new boolean[n][m];

			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(in.readLine(), " ");
				for (int j = 0; j < m; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			int ans = 0; //

			check[r][c] = true;// 방문체크
			Queue<State> Q = new LinkedList<>();
			Q.offer(new State(r, c, 0)); // 초기위치와 시간

			while (!Q.isEmpty()) {
				State now = Q.poll();
				int x = now.x;
				int y = now.y;
				int time = now.time;
				int number = map[x][y]; // 현재 위치한곳의 구조물 타입번호

				if (time > L - 1)
					break;

				else {
					ans++;

					for (int i = 0; i <= 3; i++) // 상 하 좌 우
					{
						if (type[number][i] == 0)
							continue;

						int nx = x + dx[i];
						int ny = y + dy[i];

						if (nx < 0 || nx >= n || ny < 0 || ny >= m || check[nx][ny] == true)
							continue;

						int next = map[nx][ny];

						if (next == 0)
							continue;

						if (i == 0 && type[next][1] == 1) {

							check[nx][ny] = true;
							Q.offer(new State(nx, ny, time + 1));
						}

						else if (i == 1 && type[next][0] == 1) {

							check[nx][ny] = true;
							Q.offer(new State(nx, ny, time + 1));
						}

						else if (i == 2 && type[next][3] == 1) {

							check[nx][ny] = true;
							Q.offer(new State(nx, ny, time + 1));
						}

						else if (i == 3 && type[next][2] == 1) {

							check[nx][ny] = true;
							Q.offer(new State(nx, ny, time + 1));
						}

					}

				}

			}

			sb.append(ans).append("\n");

		}

		System.out.println(sb.toString());

	}
}
