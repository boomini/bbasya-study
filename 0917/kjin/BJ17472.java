import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ17472 {
// Baekjoon 17472. 다리 만들기2
// 섬들사이의 최소길이 먼저 구한 뒤 최소 신장 트리
	
	static int N, M;
	static int[][] arr;
	static class island{
		int pos[][] = new int[100][2];
		int size=0;
	}
	static island[] islands = new island[6];
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input17472.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		boolean used[][] = new boolean[N][M];
		int d[][] = {{1,0}, {-1,0}, {0,1}, {0,-1}};
		int is=0;	// island size
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				Queue<int[]> queue = new LinkedList<int[]>();
				if (arr[i][j]==1&&!used[i][j]) {
					islands[is] = new island();
					queue.add(new int[] {i, j});
					used[i][j]=true;
					
					// 한 섬을 이루는 위치들 찾기
					while(!queue.isEmpty()) {
						int[] cur=queue.poll();
						islands[is].pos[islands[is].size][0]=cur[0];
						islands[is].pos[islands[is].size][1]=cur[1];
						islands[is].size++;
						for (int k=0; k<4; k++) {
							int nr = cur[0]+d[k][0];
							int nc = cur[1]+d[k][1];
							if (nr>=0&&nr<N&&nc>=0&&nc<M&&!used[nr][nc]&&arr[nr][nc]==1) {
								queue.add(new int[] {nr, nc});
								used[nr][nc]=true;
							}
						}
					}
					is++;
				}
			}
		}
		
		
		int[][] islArr= new int[is][is];
		for (int i=0; i<is; i++) {
			for (int j=i+1; j<is; j++) {
				// 두 섬 사이의 거리 찾기
				int dis = distance(i, j);
				islArr[i][j] = dis;
				islArr[j][i] = dis;
			}
		}
		
		// 최소 신장트리
		int result=0;
		int mst[] = new int[is];
		for (int i=0; i<is; i++) {
			mst[i]=10000;
		}
		mst[0]=0;
		boolean visited[] = new boolean[is];
		for (int i=0; i<is; i++) {
			int min = 10000;
			int minIdx = 0;
			for (int j=0; j<is; j++) {
				if (!visited[j]&&min>mst[j]) {
					minIdx=j;
					min=mst[j];
				}
			}
			
			visited[minIdx]=true;
			result+=min;
			for (int j=0; j<is; j++) {
				int temp = islArr[minIdx][j];
				if (!visited[j]&&temp!=0&&temp<mst[j]) {
					mst[j]=temp;
				}
			}
			
		}
		if (result>=10000)	result=-1;
		System.out.println(result);
		
		
	}
	
	private static int distance(int a, int b) {
		island ai = islands[a];
		island bi = islands[b];
		int min = 10000;
		
		for (int i=0; i<ai.size; i++) {
			for (int j=0; j<bi.size; j++) {
				int dis = Integer.MAX_VALUE;
				if (ai.pos[i][0]==bi.pos[j][0]&&!isColBlocked(ai.pos[i][1], bi.pos[j][1], ai.pos[i][0])) {
					dis = Math.abs(ai.pos[i][1]-bi.pos[j][1])-1;
				} else if (ai.pos[i][1]==bi.pos[j][1]&&!isRowBlocked(ai.pos[i][0], bi.pos[j][0], ai.pos[i][1])) {
					dis = Math.abs(ai.pos[i][0]-bi.pos[j][0])-1;
				}
				if (dis==0||dis==1) continue;
				if (min>dis)	min=dis; 
			}
		}
		if (min==10000 || min<=1)	return 0;
		return min;
	}

	// 섬과 섬사이에 다른 섬이 있는 경우 배제
	private static boolean isRowBlocked(int r1, int r2, int c) {
		int b = Math.max(r1, r2);
		int s = Math.min(r1, r2);
		for (int i=s+1; i<b; i++) {
			if (arr[i][c]==1) return true;
		}
		return false;
	}

	private static boolean isColBlocked(int c1, int c2, int r) {
		int b = Math.max(c1, c2);
		int s = Math.min(c1, c2);
		for (int i=s+1; i<b; i++) {
			if (arr[r][i]==1) return true;
		}
		return false;
	}
}
