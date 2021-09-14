import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1149 {
// Baekjoon 1149. RGB거리
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input1149.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int rgb[][] = new int[N+1][3];
		StringTokenizer st;
		for (int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			rgb[i][0] = Math.min(rgb[i-1][1], rgb[i-1][2])+r;
			rgb[i][1] = Math.min(rgb[i-1][0], rgb[i-1][2])+g;
			rgb[i][2] = Math.min(rgb[i-1][0], rgb[i-1][1])+b;
		}
		int min = Math.min(rgb[N][0], rgb[N][1]);
		min = Math.min(min, rgb[N][2]);
		System.out.println(min);
	}

}
