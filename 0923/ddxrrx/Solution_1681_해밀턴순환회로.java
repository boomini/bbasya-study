package hwalgo22_서울_07반_김사라;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1681_해밀턴순환회로 {

	static int N;
	static int[][] map;
	static int[] route;
	static boolean[] isSelected;
	static int min;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		map = new int[N][N];

		route = new int[N];
		isSelected = new boolean[N + 1];
		min = 100 * N;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		route[0] = 1;
		perm(1, 0);
		System.out.println(min);
	}

	private static void perm(int cnt, int sum) {
		if (sum > min)
			return;

		if (cnt == N) {
			if (map[route[N - 1] - 1][0] == 0)
				return;

			sum += map[route[N - 1] - 1][0];
			if (sum < min)
				min = sum;

			return;
		}
		for (int i = 2; i <= N; i++) {
			if (isSelected[i])
				continue; // isSelected에서 시도하려는 수가 사용중이면 다음 수로
			if (map[route[cnt - 1] - 1][i - 1] == 0)
				continue;

			route[cnt] = i; // if 문에 걸리지 않았다면 사용되지 않은 수니까
			isSelected[i] = true;

			perm(cnt + 1, sum + map[route[cnt - 1] - 1][i - 1]);
			isSelected[i] = false;

		}
	}
}
