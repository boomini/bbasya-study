import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ws01 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());

		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(in.readLine());
			int[] input = new int[N];
			int[] LIS = new int[N];

			StringTokenizer st = new StringTokenizer(in.readLine());
			for (int i = 0; i < N; i++) {
				input[i] = Integer.parseInt(st.nextToken());
			}

			int max = 0; // 전체 중의 최대길이
			for (int i = 0; i < N; i++) {
				LIS[i] = 1;

				for (int j = 0; j < i; j++) { // i-1까지 보기 (j:i의 앞쪽 원소)
					if (input[j] <= input[i] && LIS[i] < LIS[j] + 1) {
						LIS[i] = LIS[j] + 1;
					}
				} // i를 끝으로 하는 최장길이 값 계산 완료
				if (max < LIS[i])
					max = LIS[i];
			}

			System.out.println("#" + tc + " " + max);

		}

	}
}
