
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_8458 {

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st;
		int tc = Integer.parseInt(in.readLine());
		StringBuilder sb = new StringBuilder();

		for (int t = 1; t <= tc; t++) {
			sb.append("#").append(t).append(" ");
			int n = Integer.parseInt(in.readLine());

			int arr[] = new int[n + 1];

			for (int i = 1; i <= n; i++) {
				st = new StringTokenizer(in.readLine(), " ");

				int a = Math.abs(Integer.parseInt(st.nextToken()));
				int b = Math.abs(Integer.parseInt(st.nextToken()));

				arr[i] = a + b;
			}

			int flag = 1;
			int type = -1;
			int max = 0;
			for (int i = 1; i <= n; i++) {
				max = Math.max(max, arr[i]);
				if (arr[i] % 2 == 0) {
					if (type == -1) {
						type = 0;
					} else if (type == 1) {
						flag = 0;
					}
				} else {
					if (type == -1) {
						type = 1;
					} else if (type == 0) {
						flag = 0;
					}
				}
			}
			if (flag == 0) {
				sb.append(-1).append("\n");
			} else if (max == 0) {
				sb.append(0).append("\n");
			} else {
				int index = 1, val = 1;
				while (true) {
					if (max <= val) {
						if (val % 2 == 0 && type == 0) {
							break;
						}
						if (val % 2 == 1 && type == 1) {
							break;
						}
					}
					index++;
					val = val + index;
				}
				sb.append(index).append("\n");
			}
		}
		System.out.println(sb.toString());

	}
}
