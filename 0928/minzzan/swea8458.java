import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class swea8458 {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());
		StringBuilder sb = new StringBuilder();

		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(in.readLine());

			int[] len = new int[N];
			int sum = 0;
			int cnt = 0;

			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			int x0 = Integer.parseInt(st.nextToken());
			int y0 = Integer.parseInt(st.nextToken());
			len[0] = Math.abs(x0) + Math.abs(y0);

			int max = len[0];

			for (int i = 1; i < N; i++) {
				st = new StringTokenizer(in.readLine(), " ");
				int xi = Integer.parseInt(st.nextToken());
				int yi = Integer.parseInt(st.nextToken());
				len[i] = Math.abs(xi) + Math.abs(yi);

				max = Math.max(max, len[i]);

				if (len[i] % 2 != len[i - 1] % 2) { // 둘의 홀&짝 이 다르면 동시에 원점도착 불가능
					cnt = -1;
				}
			}

			if (cnt != -1) {
				while (true) {
					boolean isE = true;
					if (sum < max || (max - sum) % 2 != 0) {
						isE = false;
					}
					if (isE)
						break;
					sum += ++cnt;
				}
			}

			sb.append("#" + tc + " " + cnt + "\n");

		}
		System.out.println(sb);
	}
}
