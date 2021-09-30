import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class SWEA1249 {
// SW Expert Academy 1249.보급로
// 다익스트라
// 우선순위 큐
	static class Point implements Comparable<Point>{
		int r, c, t;

		public Point(int r, int c, int t) {
			super();
			this.r = r;
			this.c = c;
			this.t = t;
		}

		@Override
		public int compareTo(Point o) {
			return this.t-o.t;
		}
		
	}
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input1249.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb=new StringBuilder();
		for (int t=1; t<=T; t++) {
			sb.append("#").append(t).append(" ");
			int N=Integer.parseInt(br.readLine());
			char[][] arr=new char[N][N];
			int[][] sum=new int[N][N];
			for (int i=0; i<N; i++) {
				arr[i]=br.readLine().toCharArray();
				for (int j=0; j<N; j++) {
					sum[i][j]=1000000;
				}
			}
			
			int[][] d= {{1,0},{-1,0},{0,1},{0,-1}};
			sum[0][0]=arr[0][0]-'0';
			PriorityQueue<Point> pq=new PriorityQueue<>();
			pq.add(new Point(0, 0, sum[0][0]));
			while(!pq.isEmpty()) {
				Point cur=pq.poll();
				for (int k=0; k<4; k++) {
					int nr=d[k][0]+cur.r;
					int nc=d[k][1]+cur.c;
					if (nr>=0&&nr<N&&nc>=0&&nc<N) {
						int temp=cur.t+arr[nr][nc]-'0';
						if (sum[nr][nc]>temp) {
							sum[nr][nc]=temp;
							pq.add(new Point(nr, nc, temp));
						}
					}
				}
			}
			sb.append(sum[N-1][N-1]).append("\n");
		}
		System.out.println(sb);
		br.close();
	}

}
