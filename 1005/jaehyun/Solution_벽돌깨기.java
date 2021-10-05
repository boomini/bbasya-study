package swexpert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_벽돌깨기 {

	
	static class State {
		int x, y, length;

		State(int x, int y, int length) {
			this.x = x;
			this.y = y;
			this.length = length;
		}
	}

	static int dx[] = { 1, -1, 0, 0 };
	static int dy[] = { 0, 0, 1, -1 };

	static int n, w, h,ans=0;
	static int map[][];

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int tc = Integer.parseInt(in.readLine());

		for (int t = 1; t <= tc; t++) {

			sb.append("#").append(t).append(" ");
			st = new StringTokenizer(in.readLine(), " ");
          ans=0;
			n = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
          int count=0;
			map = new int[h + 1][w + 1];

			for (int i = 1; i <= h; i++) {
				st = new StringTokenizer(in.readLine(), " ");
				for (int j = 1; j <= w; j++) {
					
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j]!=0)  count++;
				}
			}

			simul(0, map, 0); // 구슬 발사횟수, map,벽돌 제거 횟수
          
			sb.append(count-ans).append("\n");
		
		}

		System.out.println(sb.toString());
	}

	static void simul(int cnt, int tmp[][], int del) {
		ans=Math.max(ans,del);
		if (cnt == n) {

		
			ans=Math.max(ans,del);
			return ;
			
		}

		else {

			for (int i = 1; i <= w; i++) // 1부터 w중에 하나를 선택
			{
				int count = 0; // 벽돌 파괴횟수 (해당 벽돌을 파괴했을경우)
				int targetx = 0;
				boolean flag = false;
				for (int j = 1; j <= h; j++) {
					if (tmp[j][i] != 0) // 제거할 벽돌이 밑에 있다
					{
						targetx = j;
						flag = true;
						break;

					}
				}

				if (flag) {
					int tmp2[][] = new int[h + 1][w + 1]; // 원래 맵은 다음경우를 위해 유지해야하니
															// 복사본을 사용

					for (int j = 1; j <= h; j++) {
						for (int k = 1; k <= w; k++) {
							tmp2[j][k] = tmp[j][k]; // 복사
						}
					}

					int x = targetx;
					int y = i; // 부술 벽돌

					Queue<State> Q = new LinkedList<>();

					if (tmp2[x][y] >= 2)
						Q.offer(new State(x, y, tmp2[x][y] - 1));

					else {
						tmp2[x][y] = 0;
						count++;
					}

					while (!Q.isEmpty()) {
						State now = Q.poll();
						x = now.x;
						y = now.y;
						int length = now.length;

						if (tmp2[x][y] != 0) {
							tmp2[x][y] = 0;
							count++;
						}

						// 값-1 만큼의 길이

						for (int k = 1; k <= length; k++) {

							for (int j = 0; j <= 3; j++) // 방향대로
							{
								int nx = x + dx[j] * k;
								int ny = y + dy[j] * k;

								if (nx < 1 || nx > h || ny < 1 || ny > w)
									continue;

								if (tmp2[nx][ny] != 0) {
									if (tmp2[nx][ny] == 1) {
										tmp2[nx][ny] = 0;
										count++;
									}

									else {

										Q.offer(new State(nx, ny, tmp2[nx][ny] - 1));

									}

								}

							}

						}

					}

					for(int j=1; j<=w; j++)
					{
						
						
						for(int k=h-1; k>=1; k--)
						{
							if(tmp2[k][j]!=0) //현재 위치한게 벽돌이라면? 하나씩 내려준다 (밑에 빈공간이 존재한다면?)
							{
								int val=tmp2[k][j];
								
								int size=0;
								while(true)
								{

									if(tmp2[k+(size+1)][j]==0)
									{
										tmp2[k+size][j]=0;
									   size++;
								
									   if((k+size)==h) break;
									}
									
									
									else {
										break;
									}
	
								}
								
								tmp2[k+size][j]=val;
							}
							
							
						}

					}

					simul(cnt+1,tmp2,del+count);

					// 벽돌의 값이 1 이상인 애들을 발견하면 큐에 넣어준다

				}

			}

		}

	}

}
