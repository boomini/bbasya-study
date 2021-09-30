package swexpert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Solution_D4보급로 {

	static int dx[] = { 1, -1, 0, 0 };
	static int dy[] = { 0, 0, -1, 1 };

	static class State implements Comparable<State> {

		int x, y, dis;

		State(int x, int y, int dis) {
			this.x = x;
			this.y = y;
			this.dis = dis;
		}

		@Override
		public int compareTo(State o) {

			return dis - o.dis;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int tc = Integer.parseInt(in.readLine());

		for (int t = 1; t <= tc; t++) {
			sb.append("#").append(t).append(" ");
			int n = Integer.parseInt(in.readLine());
			int ans = 0;
			int map[][] = new int[n + 1][n + 1];
			boolean check[][] = new boolean[n + 1][n + 1];

			for (int i = 1; i <= n; i++) {
				String input = in.readLine();

				for (int j = 1; j <= n; j++) {
					map[i][j] = input.charAt(j - 1) - 48;
				}
			}

			PriorityQueue<State> PQ = new PriorityQueue<>();

			PQ.offer(new State(1, 1, 0));

			while (!PQ.isEmpty()) {
				State now = PQ.poll();
				int x = now.x;
				int y = now.y;
				int dis = now.dis;

				if (check[x][y] == false) {
					check[x][y] = true;

					if (x == n && y == n) {
						ans = dis;
						break;
					}

					for (int i = 0; i <= 3; i++) {
						int nx = x + dx[i];
						int ny = y + dy[i];

						if (nx < 1 || nx > n || ny < 1 || ny > n || check[nx][ny] != false)
							continue;

						PQ.offer(new State(nx, ny, dis + map[nx][ny]));

					}

				}

			}
			
			sb.append(ans).append("\n");
			

		}
		
		System.out.println(sb.toString());

	}

}
