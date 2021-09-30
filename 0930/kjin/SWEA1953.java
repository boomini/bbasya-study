import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA1953 {
// SW Expert Academy 1953. 탈주범 검거
// bfs
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input1953.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int t=1; t<=T; t++) {
			sb.append("#").append(t).append(" ");
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N=Integer.parseInt(st.nextToken());
			int M=Integer.parseInt(st.nextToken());
			int R=Integer.parseInt(st.nextToken());
			int C=Integer.parseInt(st.nextToken());
			int L=Integer.parseInt(st.nextToken());
			
			int[][] arr=new int[N][M];
			boolean[][] visited=new boolean[N][M];
			for (int i=0; i<N; i++) {
				st=new StringTokenizer(br.readLine());
				for (int j=0; j<M; j++) {
					arr[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			
			int[][][] dir= {{},{{1,0},{-1,0},{0,1},{0,-1}},{{1,0},{-1,0}},{{0,1},{0,-1}},	// 1, 2, 3
					{{-1,0},{0,1}},{{1,0},{0,1}},{{1,0},{0,-1}},{{-1,0},{0,-1}}};	// 4, 5, 6, 7
			Queue<int[]> queue=new LinkedList<int[]>();
			queue.add(new int[] {R,C,arr[R][C]});
			visited[R][C]=true;
			int cnt=1;
			while(!queue.isEmpty()&&L>1) {
				int size=queue.size();
				for (int i=0; i<size; i++) {
					int[] cur=queue.poll();
					int len=2;	// 1번 제외 2방향
					if (cur[2]==1)	len=4;	// 1번은 4방향 
					for (int k=0; k<len; k++) {
						int nr=dir[cur[2]][k][0]+cur[0];
						int nc=dir[cur[2]][k][1]+cur[1];
						if (nr>=0&&nr<N&&nc>=0&&nc<M&&arr[nr][nc]!=0&&!visited[nr][nc]) {
							int nd=arr[nr][nc];
							boolean flag=false;
							if (nd!=1) {	// 다음관이 1이면 항상 가능
								for (int j=0; j<2; j++) {
									// 다음관이 1이 아니면 현재 관과 연결 가능한지 (dir이 반대 방양이 존재해야함)
									if (dir[nd][j][0]==-dir[cur[2]][k][0]&&dir[nd][j][1]==-dir[cur[2]][k][1]) {
										flag=true;
										break;
									}
								}
								if (!flag) continue;
							}
								
							cnt++;
							queue.add(new int[] {nr,nc,nd});
							visited[nr][nc]=true;
						}
					}
				}
				L--;
			}
			sb.append(cnt).append("\n");
			
		}
		System.out.println(sb);
		br.close();
	}

}
