

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_4014활주로건설 {

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int tc = Integer.parseInt(in.readLine());

		for (int t = 1; t <= tc; t++) {

			sb.append("#").append(t).append(" ");
			st = new StringTokenizer(in.readLine(), " ");
			int ans = 0;
			int n = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int map[][] = new int[n + 1][n + 1];
			int tmp[][] = new int[n + 1][n + 1];
			for (int i = 1; i <= n; i++) {
				st = new StringTokenizer(in.readLine(), " ");
				for (int j = 1; j <= n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					tmp[i][j] = map[i][j];
				}
			}
			for (int type = 1; type <= 2; type++) {
				int check[][] = new int[n + 1][n + 1]; 
				if (type == 2) {

					for (int i = 1; i <= n; i++) {
						for (int j = 1; j <= n; j++) {
							map[i][j] = tmp[j][i];

						}
					}
				}

				for (int i = 1; i <= n; i++) {
					boolean possible = true;
					boolean flag = false; 
					int pre = map[i][1];
					int length = 0; 
					for (int j = 2; j <= n; j++) {
						if (flag) 
						{
							if (map[i][j] == pre) 
							{
								check[i][j] = 1;
								length++;

								if (length == x)
								{
									flag = false;
									length = 0; 

								}

							} else 
							{
								possible = false;
								break;
							}
						}
						else {
							if (Math.abs(pre - map[i][j]) >= 2) 
							{

								possible = false;
								break;
							}

							else if (pre - 1 == map[i][j]) 
							{ 

								if (n - j < (x - 1)) 
								{
									possible = false;
									break;
								}
								check[i][j] = 1;
								if (x == 1) 
								{
									pre = map[i][j];
								}
								else 
								{
									pre = map[i][j]; 
									length = 1;
									flag = true; 

								}

							}

							else if (pre + 1 == map[i][j]) 
							{
								if (j - 1 < x) 
								{
									possible = false;
									break;
								}

								if (check[i][j - 1] == 1) // 이미 경사로로 사용되었으면?
								{
									possible = false;
									break;
								}

								check[i][j - 1] = 1;// 경사로로 사용!
								if (x == 1) {
									pre = map[i][j]; // 현재값을 이전값으로!
								}

								else // 경사로가 2이상이여야하는경우 뒤로가면서 확인
								{
									length = 1;
									for (int k = j - 2; k >= 1; k--) {

										if (check[i][k] == 0 && map[i][k] == pre) // 경사로로 쓰인적이 없으면서?
										{
											check[i][k] = 1;
											length++;

											if (length == x)
												break;
										}

										else {

											possible = false;
											break;

										}

									}

									if (length == x) {

										pre = map[i][j];

									} else {
										break;
									}

								}

							}

						}

					}
					if (possible) 
					{
						ans++;
					}

				}

			}
			sb.append(ans).append("\n");

		}

		System.out.println(sb.toString());
	}

}
