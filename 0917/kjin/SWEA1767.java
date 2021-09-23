import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class SWEA1767 {

// SW Expert Academy 1767. 프로세서 연결하기
	static int N, K, maxCnt, minLen;
	static int core[][];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input1767.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t=1; t<=T; t++) {
			sb.append("#").append(t).append(" ");
			N = Integer.parseInt(br.readLine());
			int[][] arr = new int[N][N];
			core = new int[12][2];
			K=0;
			maxCnt=0;
			minLen=0;
			for (int i=0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j=0; j<N; j++) {
					int num = Integer.parseInt(st.nextToken());
					if (num==1) {
						if (i==0||j==0||i==N-1||j==N-1) {
							arr[i][j]=1;
							continue;
						} else {
							core[K][0]=i;
							core[K++][1]=j;
						}
					}
					arr[i][j]=num;
				}
			}
			
			dfs(0, arr, maxCnt, 0);
			sb.append(minLen).append("\n");
		}
		System.out.println(sb);
	}
	
	private static void dfs(int k, int arr[][], int cnt, int len) {
		if (K-k+cnt<maxCnt) {
			return;
		}
		if (k==K) {
			if (maxCnt<cnt) {
				maxCnt=cnt;
				minLen=len;
			} else if(maxCnt==cnt&&minLen>len) {
				minLen=len;
			}
//			for (int i=0; i<N; i++) {
//				System.out.println(Arrays.toString(arr[i]));
//			}
//			System.out.println(cnt+" "+len);
			return;
		}
		
		int temp[][] = new int[N][N];
		
		
		int d[][] = {{0,1}, {0,-1}, {1,0}, {-1,0}};
		for (int j=0; j<4; j++) {
			for (int i=0; i<N; i++) {
				temp[i]=arr[i].clone();
			}
			if(canConnect(temp, core[k], d[j])) {
				int l = connect(temp, core[k], d[j]);
				dfs(k+1, temp, cnt+1, len+l);
			} else {
				dfs(k+1, temp, cnt, len);
			}
		}
		for (int i=0; i<N; i++) {
			temp[i]=arr[i].clone();
		}
		dfs(k+1, temp, cnt, len);
	}

	private static int connect(int[][] temp, int[] co, int[] dir) {
		int len=0;
		int i=co[0]+dir[0];
		int j=co[1]+dir[1];
		while(i>=0&&i<N&&j>=0&&j<N) {
			temp[i][j]=2;
			i+=dir[0];
			j+=dir[1];
			len++;
		}
		return len;
		
	}

	private static boolean canConnect(int[][] temp, int co[], int[] dir) {
		int i=co[0]+dir[0];
		int j=co[1]+dir[1];
		while(i>=0&&i<N&&j>=0&&j<N) {
			if (temp[i][j]!=0) {
				return false;
			}
			i+=dir[0];
			j+=dir[1];
		}
		return true;
	}

}
