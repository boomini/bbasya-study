import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ4485 {
// Baekjoon 4485. 녹색옷 입은애가 젤다지?
// 다익스트라 
	
	static class Point implements Comparable<Point>{
		int r, c, w;

		public Point(int r, int c, int w) {
			super();
			this.r = r;
			this.c = c;
			this.w = w;
		}

		@Override
		public int compareTo(Point o) {
			return this.w-o.w;
		}
		
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input4485.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int t=1;
		while(true) {
			int N = Integer.parseInt(br.readLine());
			if (N==0)	break;
			int[][] arr = new int[N][N];
			int[][] sum = new int[N][N];
			for (int i=0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j=0; j<N; j++) {
					arr[i][j]=Integer.parseInt(st.nextToken());
					sum[i][j]=100000;
				}
			}
			
			int d[][]= {{1,0},{0,1},{-1,0},{0,-1}};
			PriorityQueue<Point> pq = new PriorityQueue<>();
			sum[0][0]=arr[0][0];
			pq.add(new Point(0, 0, sum[0][0]));
			
			while(!pq.isEmpty()) {
				Point cur=pq.poll();	// 거리가 최소인 애부터 탐색
				for (int k=0; k<4; k++) {
					int nr = cur.r+d[k][0];
					int nc = cur.c+d[k][1];
					if (nr>=0&&nr<N&&nc>=0&&nc<N) {
						int temp=arr[nr][nc]+sum[cur.r][cur.c];
						if (sum[nr][nc]>temp) {
							sum[nr][nc]=temp;
							pq.add(new Point(nr, nc, temp));
						}
					}
				}
			}
			
			sb.append("Problem ").append(t++).append(": ");
			sb.append(sum[N-1][N-1]).append("\n");
		}
		System.out.println(sb);
		br.close();
	}

}
