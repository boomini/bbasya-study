import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Jol1681 {

	static int N; // 1번 회사 나머지는 방문해야되는 곳
	static List<int[]> loc[];
	static int check[];
	static int cost = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(in.readLine());

		loc = new ArrayList[N + 1];
		check = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			loc[i] = new ArrayList<>();
		}

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 1; j <= N; j++) {
				int val = Integer.parseInt(st.nextToken());

				if (val != 0) {
					loc[i].add(new int[] { j, val }); // j는 연결되어있는 장소와 val 은 비용

				}

			}
		}

		simul(1, 0, 0); // 1번 정점(회사) 에서 시작, 0 장소 방문횟수(n-1)이 되야함 , 0은 cost

		System.out.println(cost);

	}

	static void simul(int now, int cnt, int val) {

		if (cnt == N) // 다 방문했으면?
		{
			cost = Math.min(val, cost);
		}

		else {

			for (int i = 0; i < loc[now].size(); i++) {
				int next = loc[now].get(i)[0];
				int nextval = loc[now].get(i)[1];

				if (next == 1 && cnt != N - 1)
					continue;

				if (check[next] == 0) {
					if (val + nextval > cost)
						continue;

					check[next] = 1;

					simul(next, cnt + 1, val + nextval);

					check[next] = 0;
				}

			}

		}

	}

}
