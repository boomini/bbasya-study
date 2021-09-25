import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ17070 {
// 파이프 옮기기1
	static int N, cnt;
	static int arr[][];
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input17070.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		for (int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		if (arr[N-1][N-1]!=1)
			dfs(0, 0, 1);
		System.out.println(cnt);
	}
	private static void dfs(int dir, int r, int c) {
		if (r==N || c==N || arr[r][c]==1) {	// 가로, 세로
			return;
		}
		
		if (dir==2 && (arr[r-1][c]==1 || arr[r][c-1]==1)) {	// 대각
			return;
		}
		
		if (r==N-1 && c==N-1) {
			cnt++;
			return;
		}
		
		if (dir==0) {	// 가로로
			dfs(0, r, c+1);
			dfs(2, r+1, c+1);
		} 
		else if (dir==1) {	// 세로로
			dfs(1, r+1, c);
			dfs(2, r+1, c+1);
		} 
		else if (dir==2) {	// 대각선
			dfs(0, r, c+1);
			dfs(2, r+1, c+1);
			dfs(1, r+1, c);
		}
	}

}
