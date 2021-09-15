import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj2636 {

	static class node {
		int x;
		int y;

		public node(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

	static int[][] map;
	static int[] deltax = { 0, 0, -1, 1 };
	static int[] deltay = { -1, 1, 0, 0 };
	static int N, M;
	static boolean flag;
	static int cheeze = 0;
	static int tempc = 0;

	static LinkedList<node> list;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1) {
					cheeze++;
				}
			}
		}

		int answer = 0;
		while (true) {

			flag = false;
			list = new LinkedList<>(); // 녹일 치즈 들어있는 리스트
			bfs();
			check_melting();
			melting();
			for (int k = 0; k < N; k++) {
				for (int l = 0; l < M; l++) {
					System.out.print(map[k][l] + " ");
				}
				System.out.println();
			}
			System.out.println("*********************************");
			if (flag == true) {
				if (cheeze == 0) {
					break;
				}
				answer++;
			} else {
				break;
			}
		}

		System.out.println(answer + 1);
		System.out.println(tempc);

	}

	static void bfs() {
		Queue<node> queue = new LinkedList<>();
		queue.add(new node(0, 0));

		while (!queue.isEmpty()) {
			int x = queue.peek().x;
			int y = queue.peek().y;
			queue.poll();

			for (int i = 0; i < 4; i++) {
				int nx = x + deltax[i];
				int ny = y + deltay[i];

				if (nx <= -1 || ny <= -1 || ny >= N || nx >= M) {
					continue;
				}
				if (map[ny][nx] != 0) {
					continue;
				}

				if (map[ny][nx] == 0) {
					queue.add(new node(nx, ny));
					map[ny][nx] = -1; // 연결되어있던 공기노출부분 -1로 변경(바깥쪽 공기)
				}

			}

		}

	}

	static void check_melting() {
		for (int i = 1; i < N - 1; i++) {
			for (int j = 1; j < M - 1; j++) {
				if (map[i][j] == 1) {
					int air = 0;
					flag = true;
					for (int k = 0; k < 4; k++) {
						int x = j + deltax[k];
						int y = i + deltay[k];

						if (x <= -1 || y <= -1 || y >= N || x >= M) {
							continue;
						}

						if (map[y][x] == -1) {
							air++;
						}

						if (air >= 1) {
							list.add(new node(j, i));
							break;
						}
					}
				}
			}
		}
	}

	static void melting() {
		tempc = list.size();
		for (int i = 0; i < list.size(); i++) {
			int x = list.get(i).x;
			int y = list.get(i).y;
			map[y][x] = 0;
			cheeze--;
		}

		// 바깥쪽 공기 다시 0으로 바꿔줌
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == -1) {
					map[i][j] = 0;
				}
			}
		}

	}

}
