import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA5656 {
// SW Expert Academy 5656. 벽돌 깨기
// 중복 순열, bfs
	static int N,W,H,min;
	static int[] temp;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input5656.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T=Integer.parseInt(br.readLine());
		for (int t=1; t<=T; t++) {
			sb.append("#").append(t).append(" ");
			StringTokenizer st = new StringTokenizer(br.readLine());
			N=Integer.parseInt(st.nextToken());
			W=Integer.parseInt(st.nextToken());
			H=Integer.parseInt(st.nextToken());
			min=10000;
			int[][] arr=new int[H][W];
			temp=new int[N];
			for (int i=0; i<H; i++) {
				st=new StringTokenizer(br.readLine());
				for (int j=0; j<W; j++) {
					arr[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			
			perm(0, arr);	// 부분 집합
			sb.append(min).append("\n");
		}
		System.out.println(sb);
		br.close();
	}
	
	private static void perm(int cnt, int[][] arr) {
		if (cnt==N) {
			int sum=0;
			for (int i=0; i<H; i++) {
				for (int j=0; j<W; j++) {
					if (arr[i][j]!=0) {
						sum++;
					}
				}
			}
			
			if (min>sum) {
				min=sum;
			}
			
			return;
		}
		
		for (int i=0; i<W; i++) {
			temp[cnt]=i;
			int[][] clone=new int[H][W];
			for (int j=0; j<H; j++) {
				clone[j]=arr[j].clone();
			}
			shoot(clone, i);
			perm(cnt+1, clone);
		}
	}

	private static void shoot(int[][] arr, int c) {
		Queue<int[]> queue=new LinkedList<int[]>();
		
		// 열 중 가장 위에 칸 찾기
		for (int i=0; i<H; i++) {
			if (arr[i][c]!=0) {
				queue.add(new int[] {i, c, arr[i][c]});
				arr[i][c]=0;
				break;
			}
		}
		
		int[][] d= {{1,0},{-1,0},{0,1},{0,-1}};
		while(!queue.isEmpty()){	
			int[] cur=queue.poll();
			if (cur[2]==1)	continue;
			for (int i=1; i<cur[2];i++) {	// 숫자 길이 만큼 
				for (int k=0; k<4; k++) {
					int nr=cur[0]+d[k][0]*i;
					int nc=cur[1]+d[k][1]*i;
					if (nr>=0&&nr<H&&nc>=0&&nc<W&&arr[nr][nc]!=0) {
						queue.add(new int[] {nr, nc, arr[nr][nc]});
						arr[nr][nc]=0;
					}
				}
			}
		}
		
		// 아래로 내리기
		for (int j=0; j<W; j++) {
			int index=H-1;
			for (int i=H-1; i>=0; i--) {
				if (arr[i][j]!=0) {
					int tmp=arr[i][j];
					arr[i][j]=0;
					arr[index][j]=tmp;
					index--;
				}
			}
		}

	}

}
