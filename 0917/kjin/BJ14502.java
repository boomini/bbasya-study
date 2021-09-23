import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ14502 {
// Baekjoon 14502. 연구소

	static int N, M, P, V, max;		// 행, 열, 평지, 바이러스
	static int[][] arr;
	static int[][] plain = new int[64][2];
	static int[][] virus = new int[10][2];
	static int[] wall = new int[3];
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input14502.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<M; j++) {
				int num = Integer.parseInt(st.nextToken());
				if (num==0) {
					plain[P][0]=i;
					plain[P++][1]=j;
				} else if (num==2) {
					virus[V][0]=i;
					virus[V++][1]=j;
				}
				arr[i][j] = num;
			}
		}
		
		comb(0, 0);
		System.out.println(max);
			
	}
	
	private static void comb(int start, int cnt) {
		if (cnt==3) {
			spread();
			return;
		}
		
		for (int i=start; i<P; i++) {
			wall[cnt]=i;
			comb(i+1, cnt+1);
		}
	}

	private static void spread() {
		int temp[][] = new int[N][M];
		for (int i=0; i<N; i++) {
			temp[i] = arr[i].clone();
		}
		for (int i=0; i<3; i++) {
			temp[plain[wall[i]][0]][plain[wall[i]][1]] = 1;
		}
		
		int v=0;
		int d[][] = {{0,1}, {0,-1}, {1,0}, {-1,0}};
		boolean visited[][] = new boolean[N][M];
		Queue<int[]> queue = new LinkedList<int[]>();
		for (int i=0; i<V; i++) {	
			queue.add(new int[] {virus[i][0], virus[i][1]});	
		}
		
		while(!queue.isEmpty()) {
			int cur[] = queue.poll();
			visited[cur[0]][cur[1]]=true;
			for (int i=0; i<4; i++) {
				int nr = cur[0]+d[i][0];
				int nc = cur[1]+d[i][1];
				if (nr>=0&&nr<N&&nc>=0&&nc<M&&temp[nr][nc]==0&&!visited[nr][nc]) {
					queue.add(new int[] {nr, nc});
					visited[nr][nc]=true;
					temp[nr][nc]=2;
					v++;
				}
			}
		}
		
		int sum = P-3-v;
		if (max<sum)	max=sum;
	}

}
