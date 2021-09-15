import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ2363 {
// Baekjoon 2636. 치즈
	static int R, C, cnt, sum, total;
	static int[][] arr;
	static boolean[][] outside;
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input2636.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		arr = new int[R][C];
		outside = new boolean[R][C];
		for (int i=0; i<R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<C; j++) {
				arr[i][j]=Integer.parseInt(st.nextToken());
				if (arr[i][j]==1)	total++;
			}
		}
		
		sum=total;
		findOut(0, 0);
		while (total!=0) {
			melt();
			cnt++;
			if (total!=0)
				sum=total;
		}
		System.out.println(cnt);
		System.out.println(sum);
		
				
	}
	
	private static void melt() {
		boolean melted[][] = new boolean[R][C];
		int dr[] = {0, 0, 1, -1};
		int dc[] = {1, -1, 0, 0};
		for (int i=0; i<R; i++) {
			for (int j=0; j<C; j++) {
				if (arr[i][j]==0)	continue;
				for (int k=0; k<4; k++) {
					int nr = i+dr[k];
					int nc = j+dc[k];
					if (nr>=0&&nr<R&&nc>=0&&nc<C&&arr[nr][nc]==0&&outside[nr][nc]) {
						total--;
						melted[i][j]=true;
						break;
					}
				}
			}
		}
		
		for (int i=0; i<R; i++) {
			for (int j=0; j<C; j++) {
				if (melted[i][j]) {
					arr[i][j]=0;
					findOut(i, j);
					
				}
			}
		}
	}

	private static void findOut(int r, int c) {
		Queue<int[]> queue = new LinkedList<int[]>();
		queue.add(new int[]{r, c});
		int dr[] = {0, 0, 1, -1};
		int dc[] = {1, -1, 0, 0};
		while(!queue.isEmpty()) {
			int cur[] = queue.poll();
			outside[cur[0]][cur[1]]=true;
			if (arr[cur[0]][cur[1]]==1)	continue;
			for (int i=0; i<4; i++) {
				int nr = cur[0]+dr[i];
				int nc = cur[1]+dc[i];
				if (nr>=0&&nr<R&&nc>=0&&nc<C&&arr[nr][nc]==0&&!outside[nr][nc]) {
					outside[nr][nc]=true;
					queue.add(new int[] {nr, nc});
					
				}
			}
		}
	}

}
