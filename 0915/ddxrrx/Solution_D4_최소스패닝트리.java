package hwalgo19_서울_07반_김사라;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_D4_최소스패닝트리 {
	static int n, m;
	static int[] parents;
	static Edge[] edl;

	static class Edge implements Comparable<Edge> {
		int from, to, w;

		public Edge(int from, int to, int w) {
			super();
			this.from = from;
			this.to = to;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.w, o.w);
		}
	}

	static void make() {
		for (int i = 1; i <= n; i++) {
			parents[i] = i;
		}
	}

	static int findSet(int num) {
		if (parents[num] == num)
			return num;
		return parents[num] = findSet(parents[num]);
	}

	static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if (aRoot == bRoot) // a와 b의 root가 같으면 같은 집합
			return false;

		int min = Math.min(aRoot, bRoot);
		parents[aRoot] = min;
		parents[bRoot] = min;
		return true;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(in.readLine());

		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");

			StringTokenizer st = new StringTokenizer(in.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			edl = new Edge[m];
			parents = new int[n + 1];

			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(in.readLine());
				edl[i] = new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
						Integer.parseInt(st.nextToken()));
			}

			Arrays.sort(edl);

			make();
			long result = 0;
			int cnt = 0;

			for (Edge edge : edl) {
				if (union(edge.from, edge.to)) {
					result += edge.w;
					if (++cnt == n - 1) {
						break;
					}
				}
			}

			sb.append(result).append("\n");
		}
		System.out.println(sb.toString());
	}
}