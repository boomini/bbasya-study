import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ws02 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			int N = Integer.parseInt(st.nextToken());
			int[][] D = new int[N][N];

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					int value = Integer.parseInt(st.nextToken());
					if (i != j && value == 0) {
						D[i][j] = 9999;
					} else {
						D[i][j] = value;
					}
				}
			}
			for (int k = 0; k < N; k++) {
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						D[i][j] = Math.min(D[i][k] + D[k][j], D[i][j]);
					}
				}
			}

			int answer = Integer.MAX_VALUE;
			for (int i = 0; i < N; i++) {
				int min = 0;
				for (int j = 0; j < N; j++) {
					min = min + D[i][j];
				}
				answer = Math.min(min, answer);
			}

			sb.append("#" + tc + " " + answer + "\n");
		}
		System.out.println(sb);
	}
}
