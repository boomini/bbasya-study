import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ1194 {
// Baekjoon 1194. 달이 차오른다
	static class Point{
		int r, c, cnt, mask;

		public Point(int r, int c, int cnt, int mask) {
			super();
			this.r = r;
			this.c = c;
			this.cnt = cnt;
			this.mask = mask;
		}
		
	}
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input1194.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N=Integer.parseInt(st.nextToken());
		int M=Integer.parseInt(st.nextToken());
		char[][] arr=new char[N][M];
		boolean[][][] visited=new boolean[N][M][64];
		Point cur=null;
		for (int i=0; i<N; i++) {
			arr[i]=br.readLine().toCharArray();
			for (int j=0; j<M; j++) {
				if (arr[i][j]=='0') {
					cur=new Point(i, j, 0, 0);
				}
			}
		}
		
		int result=-1;
		int[][] d= {{1,0},{-1,0},{0,1},{0,-1}};
		Queue<Point> queue=new LinkedList<Point>();
		queue.add(cur);
		visited[cur.r][cur.c][cur.mask]=true;
		
		loop:
		while(!queue.isEmpty()) {
			cur=queue.poll();
			for (int k=0; k<4; k++) {
				int nr=d[k][0]+cur.r;
				int nc=d[k][1]+cur.c;
				if (nr>=0&&nr<N&&nc>=0&&nc<M&&!visited[nr][nc][cur.mask]) {
					char temp=arr[nr][nc];
					if (temp=='#')	continue;
					if (temp=='1') {	// 문 찾으면 while문 나오기
						result=cur.cnt+1;
						break loop;
					} else if (temp>='a'&&temp<='f') {
						int mask=cur.mask|1<<(temp-'a');	// 현재 마스크에 찾은거 더하기
						visited[nr][nc][mask]=true;
						queue.add(new Point(nr, nc, cur.cnt+1, mask));
						
					} else if (temp>='A'&&temp<='F') {	// 97-65=32
						int mask=1<<(temp-'A');
						if ((cur.mask&mask)>0) {	// 키를 가지고 있으면
							visited[nr][nc][cur.mask]=true;
							queue.add(new Point(nr, nc, cur.cnt+1, cur.mask));
						}
					} else if (temp=='0'||temp=='.') {
						visited[nr][nc][cur.mask]=true;
						queue.add(new Point(nr, nc, cur.cnt+1, cur.mask));
					}
				}
			}
		}
		
		System.out.println(result);
		
		br.close();
	}

}
