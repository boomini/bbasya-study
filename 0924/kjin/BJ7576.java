import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ7576 {
// Baekjoon 7576. 토마토
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input7576.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int arr[][] = new int[N][M];
		Queue<int[]> queue = new LinkedList<int[]>();
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<M; j++) {
				int num = Integer.parseInt(st.nextToken());
				if (num==1) {
					queue.add(new int[] {i, j});
				}
				arr[i][j]=num;
			}
		}
		int cnt=-1;
		boolean visited[][] = new boolean[N][M];
		int d[][] = {{1,0},{-1,0},{0,1},{0,-1}};
		while(!queue.isEmpty()) {
			int size=queue.size();
			for (int i=0; i<size; i++) {
				int cur[] = queue.poll();
				visited[cur[0]][cur[1]]=true;
				for (int k=0; k<4; k++) {
					int nr=cur[0]+d[k][0];
					int nc=cur[1]+d[k][1];
					if (nr>=0&&nr<N&&nc>=0&&nc<M&&arr[nr][nc]==0&&!visited[nr][nc]) {
						queue.add(new int[] {nr, nc});
						visited[nr][nc]=true;
						arr[nr][nc]=1;
					}
				}
			}
			cnt++;
		}
		
		loop:
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				if (arr[i][j]==0) {
					cnt=-1;
					break loop;
				}
			}
		}
		
		System.out.println(cnt);
	}

}
