package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Boj17143_낚시왕 {
	// 위 아래 오른쪽 왼쪽(1,2,3,4)
	static int dx[] = { 0, -1, 1, 0, 0 };
	static int dy[] = { 0, 0, 0, 1, -1 };
	static int r, c, m;
	static int map[][];
	static Shark list[];

	static class Shark {
		// 상태가 1이면 살아있는거 0이면 죽은거
		int x, y, s, d, size, state; // 좌표, 속도 , 방향, 크기, 상태

		Shark(int x, int y, int s, int d, int size, int state) {
			this.x = x;
			this.y = y;
			this.s = s;
			this.d = d;
			this.size = size;
			this.state = state;
		}

	}

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		list = new Shark[m + 1];

		map = new int[r + 1][c + 1];

		// r c s d z 위치 , 속력, 방향( 크기 )

		for (int i = 1; i <= m; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());

			map[x][y] = i; // 각 상어의 번호를 맵에 기록해준다.
			Shark now = new Shark(x, y, s, d, z, 1);
			list[i] = now;
		}

		int score = 0;

		for (int i = 1; i <= c; i++) // 각 열을 한칸씩 이동
		{

			for (int j = 1; j <= r; j++) {
				if (map[j][i] != 0)// 맨처음 발견한 상어
				{

					list[map[j][i]].state = 0; // 죽은 상어
					score += list[map[j][i]].size; // 사이즈만큼?
					map[j][i] = 0;
					break;
				}

			}
			move();

		}
		System.out.println(score);

	}

	static void move() {
		for (int i = 1; i <= m; i++) // 살아있으면 움직여준다
		{

			if (list[i].state == 1) {
				int x = list[i].x;
				int y = list[i].y;
				int s = list[i].s;
				int d = list[i].d;
				int size = list[i].size;

				map[x][y] = 0;
				// 각 상어의 순환에 걸리는 이동횟수를 먼저 구한다
				// 위 아래 오른쪽 왼쪽(1,2,3,4)
				int circle = 0;
				if (d == 1 || d == 2) // 위 아래 일경우
				{

					circle = r - 1 + (x - 1) + (r - x);
				}

				else // 좌 우 일경우
				{
					circle = c - 1 + (y - 1) + (c - y);
				}

				int move = s % circle; // 실제 이동해야하는 횟수

				for (int j = 1; j <= move; j++) {

					x = x + dx[d]; // 현재 방향만큼 한칸 이동
					y = y + dy[d];

					if (x > r || x < 1 || y > c || y < 1) // 범위를 넘어간 경우 반대방향으로 바꾼뒤 한칸 이동해준다.
					{
						if (d == 1 || d == 2) // 위아래
						{
							d = 3 - d; // 방향 바꿔주기

							x = x + (dx[d] * 2); // 현재 방향만큼 두칸 이동
							y = y + (dy[d] * 2);

						}

						else {
							d = 7 - d;

							x = x + (dx[d] * 2); // 현재 방향만큼 두칸 이동
							y = y + (dy[d] * 2);
						}

					}

				}

				// 실제로 다 이동후
				// 변경된 방향이나 위치등을 기록

				list[i].x = x; // 좌표와 방향이 변경되었으면 기록
				list[i].y = y;
				list[i].d = d;
			}

		}

		for (int i = 1; i <= m; i++) {
			if (list[i].state == 1) // 살아있으면 맵에 다시 기록
			{
				int x = list[i].x;
				int y = list[i].y;

				int size = list[i].size;

				if (map[x][y] == 0) {
					map[x][y] = i; // 아무도 없으면 내거 기록
				}

				else // 이미 상어가 위치하고 있으면?
				{
					if (list[map[x][y]].size < size) {
						list[map[x][y]].state = 0; // 잡아먹힘
						map[x][y] = i;

					}

					else // 내가 더 작다면?
					{
						list[i].state = 0;
					}

				}

			}

		}

	}

}
