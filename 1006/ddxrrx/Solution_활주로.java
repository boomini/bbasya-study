package hwalgo30_서울_07반_김사라;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_활주로 {
	static int N, L;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(in.readLine()); // 테스트 케이스 개수 T

		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");

			StringTokenizer st = new StringTokenizer(in.readLine());
			N = Integer.parseInt(st.nextToken()); // 지도 크기 N
			L = Integer.parseInt(st.nextToken()); // 경사로의 길이 L

			map = new int[N][N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(in.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			int cnt = 0;
			for (int i = 0; i < N; i++) {
				if (build(i, 0, 0)) {
					cnt++;
				}

				if (build(0, i, 1)) {
					cnt++;
				}
			}
			sb.append(cnt).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static boolean build(int x, int y, int dir) {
		boolean[] visited = new boolean[N];
		int[] height = new int[N];

		for (int i = 0; i < N; i++) {
			height[i] = (dir == 0) ? map[x][y + i] : map[x + i][y];
		}

		for (int i = 0; i < N - 1; i++) {
			if (height[i] == height[i + 1]) {
				continue;
			}

			if (Math.abs(height[i] - height[i + 1]) > 1) {
				return false;
			}

			if (height[i] - 1 == height[i + 1]) {
				for (int j = i + 1; j <= i + L; j++) {
					if (j >= N || visited[j] || height[j] != height[i + 1]) {
						return false;
					}
					visited[j] = true;
				}
			} else if (height[i] + 1 == height[i + 1]) {
				for (int j = i; j > i - L; j--) {
					if (j < 0 || visited[j] || height[j] != height[i]) {
						return false;
					}
					visited[j] = true;
				}
			}

		}
		return true;
	}
}