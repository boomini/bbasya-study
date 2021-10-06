import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class swea4014 {

	static int N, X;
	static int answer;
	static int[][] map;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(in.readLine()); // 테스트케이스 개수

		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= TC; tc++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken()); // 경사로의 길이

			map = new int[N][N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(in.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			answer = 0;

			for (int i = 0; i < N; i++) {
				if (simulation(i, 0, 0)) {
					answer++;
				}
				if (simulation(0, i, 1)) {
					answer++;
				}
			}

			sb.append("#" + tc + " " + answer + "\n");
		}
		System.out.println(sb);
	}

	static boolean simulation(int x, int y, int d) {
		int[] height = new int[N];
		boolean[] visited = new boolean[N]; // 경사로가 이미 놓여있는지 체크

		// 알아보기 쉽게 height 배열에 옮기기
		for (int i = 0; i < N; i++) {
			height[i] = (d == 0) ? map[x][y + i] : map[x + i][y];
		}

		for (int i = 0; i < N - 1; i++) {
			// 높이가 같으면 패스
			if (height[i] == height[i + 1]) {
				continue;
			}

			if (Math.abs(height[i] - height[i + 1]) > 1) {
				return false;
			}

			// 내려가야되는 경우
			if (height[i] - 1 == height[i + 1]) {
				for (int j = i + 1; j <= i + X; j++) {
					// j가 범위를 벗어나거나 높이가 다르거나 이미 경사로가 있는 경우
					if (j >= N || height[i + 1] != height[j] || visited[j] == true) {
						return false;
					}
					visited[j] = true;
				}
			}
			// 올라가야되는 경우
			else if (height[i] + 1 == height[i + 1]) {
				for (int j = i; j > i - X; j--) {
					if (j < 0 || height[i] != height[j] || visited[j] == true) {
						return false;
					}
					visited[j] = true;
				}
			}
		}

		return true;
	}
}
