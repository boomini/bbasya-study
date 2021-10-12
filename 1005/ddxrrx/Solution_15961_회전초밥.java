package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution_15961_회전초밥 {

	static int N, d, k, c;
	static int[] n;
	static ArrayList<Integer> selected;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken()); // 접시의 수
		d = Integer.parseInt(st.nextToken()); // 초밥의 가짓수
		k = Integer.parseInt(st.nextToken()); // 연속해서 먹는 접시의 수
		c = Integer.parseInt(st.nextToken()); // 쿠폰 번호

		n = new int[N];
		selected = new ArrayList<Integer>();

		int start = 0, limit = 0;
		for (int i = 0; i < N; i++) {
			n[i] = Integer.parseInt(in.readLine());
			if (n[i] == c) {
				start = i + 1;
				limit = i + N - 1;
			}
		}

		int idx = 0, ans = 0;
		loop1: while (start != limit) {
			for (int i = start; i < start + k; i++) {
				if (i >= N)
					idx = i - N;
				else
					idx = i;

				if (i == start) {
					selected.add(n[idx]);
					continue;
				} else {
					if (!selected.contains(n[idx])) {
						selected.add(n[idx]);
					}
				}
			}

			if (selected.size() == k && !selected.contains(c)) {
				ans = k + 1;
				break loop1;
			}

			if (selected.size() >= ans) {
				if (selected.contains(c))
					ans = selected.size();
				else
					ans = selected.size() + 1;
			}
			selected.clear();
			start++;
		}

		System.out.println(ans);
	}
}
