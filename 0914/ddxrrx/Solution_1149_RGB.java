package hwalgo18_서울_07반_김사라;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1149_RGB {

	static int r = 0;
	static int g = 1;
	static int b = 2;
	static int[][] cost;
	static int[][] DP;

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(in.readLine());
		cost = new int[N][3];
		DP = new int[N][3];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");

			cost[i][0] = Integer.parseInt(st.nextToken());
			cost[i][1] = Integer.parseInt(st.nextToken());
			cost[i][2] = Integer.parseInt(st.nextToken());
		}

		DP[0][0] = cost[0][0];
		DP[0][1] = cost[0][1];
		DP[0][2] = cost[0][2];

		System.out.println(Math.min(calcCost(N - 1, 0), Math.min(calcCost(N - 1, 1), calcCost(N - 1, 2))));
	}

	static int calcCost(int N, int color) {
		if (DP[N][color] == 0) {
			if (color == 0) {
				DP[N][0] = Math.min(calcCost(N - 1, 1), calcCost(N - 1, 2)) + cost[N][0];
			} else if (color == g) {
				DP[N][1] = Math.min(calcCost(N - 1, 0), calcCost(N - 1, 2)) + cost[N][1];
			} else {
				DP[N][2] = Math.min(calcCost(N - 1, 0), calcCost(N - 1, 1)) + cost[N][2];
			}
		}

		return DP[N][color];
	}
}