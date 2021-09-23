package hwalgo20_서울_07반_김사라;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution_9205_맥주 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(in.readLine());

		for (int tc = 1; tc <= T; tc++) {
			int n = Integer.parseInt(in.readLine());
			int[][] dist = new int[n + 2][n + 2];
			boolean[][] d = new boolean[n + 2][n + 2];
			List<int[]> point = new ArrayList<>();

			for (int i = 0; i <= n + 1; i++) {
				st = new StringTokenizer(in.readLine(), " ");
				point.add(new int[] { Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) });
			}

			for (int i = 0; i <= n + 1; i++)
				for (int j = 0; j <= n + 1; j++) {
					int[] p1 = point.get(i), p2 = point.get(j);
					dist[i][j] = Math.abs(p1[0] - p2[0]) + Math.abs(p1[1] - p2[1]);

					if (dist[i][j] <= 50 * 20)
						d[i][j] = true;
				}

			for (int k = 0; k <= n + 1; k++)
				for (int i = 0; i <= n + 1; i++)
					for (int j = 0; j <= n + 1; j++)
						if (d[i][k] & d[k][j])
							d[i][j] = true;

			if (!d[0][n + 1])
				sb.append("sad").append("\n");
			else
				sb.append("happy").append("\n");
		}
		System.out.println(sb.toString());
	}

}