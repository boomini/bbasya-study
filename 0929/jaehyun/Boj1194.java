package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj1194 {

	static int dx[] = { 1, -1, 0, 0 };
	static int dy[] = { 0, 0, -1, 1 };

	static class State {

		int x, y, cnt, key;

		State(int x, int y, int cnt, int key) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
			this.key = key;
		}
	}

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int n, m; // 세로 가로
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		int ans = -1;
		int map[][] = new int[n + 1][m + 1];
		int check[][][] = new int[1 << 6][n + 1][m + 1];
		int sx = 0, sy = 0;

		for (int i = 1; i <= n; i++) {
			String input = in.readLine();

			for (int j = 1; j <= m; j++) {
				map[i][j] = input.charAt(j - 1);

				if (map[i][j] == '0') {
					sx = i;
					sy = j;
				}

				else if ('A' <= map[i][j] && map[i][j] <= 'F') {
					map[i][j] = map[i][j] - 65; // A는 1번 B는 2번 이런식
				}

			}
		}

		check[0][sx][sy] = 1;
		map[sx][sy] = '.';
		Queue<State> Q = new LinkedList<>();

		Q.add(new State(sx, sy, 0, 0));

		loop: while (!Q.isEmpty()) {
			State now = Q.poll();

			int x = now.x;
			int y = now.y;
			int cnt = now.cnt;
			int key = now.key;
			for (int i = 0; i <= 3; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];

				if (nx < 1 || nx > n || ny < 1 || ny > m || check[key][nx][ny] != 0)
					continue;
				if (map[nx][ny] == '.') // 빈곳일 경우
				{
					check[key][nx][ny] = 1;
					Q.add(new State(nx, ny, cnt + 1, key));
				}

				else if (0 <= map[nx][ny] && map[nx][ny] <= 5) // 문이라면?
				{
					if ((key & (1 << map[nx][ny])) != 0) // 해당 키를 가지고 있다면?
					{
						check[key][nx][ny] = 1;
						Q.add(new State(nx, ny, cnt + 1, key));

					}

				}

				else if ('a' <= map[nx][ny] && map[nx][ny] <= 'f') // 열쇠라면?
				{
					check[key | ((1 << map[nx][ny] - 97))][nx][ny] = 1;
					Q.add(new State(nx, ny, cnt + 1, key | ((1 << map[nx][ny] - 97))));
				}

				else if (map[nx][ny] == '1') {
					ans = cnt + 1;
					break loop;

				}

			}

		}

		System.out.println(ans);

	}

}
