import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ17144 {
// Baekjoon 17144. 미세먼지 안녕!
	static int R, C, T, airCon;
	static int[][] arr;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input17144.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		arr = new int[R][C];
		for (int i=0; i<R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<C; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
			if (arr[i][0]==-1)	airCon=i;
		}
		
		for (int t=0; t<T; t++) {
			spread();
			operCon();
		}
		int result=0;
		for (int i=0; i<R; i++) {
			for (int j=0; j<C; j++) {
				result+=arr[i][j];
			}
		}
		System.out.println(result+2);
				
	}

	private static void spread() {
		int dr[] = {1, -1, 0, 0};
		int dc[] = {0, 0, 1, -1};
		int sprPlus[][] = new int[R][C];
		int sprMinus[][] = new int[R][C];
		for (int i=0; i<R; i++) {
			for (int j=0; j<C; j++) {
				int num=arr[i][j];
				if (num==0 || num==-1)	continue;
				for (int k=0; k<4; k++) {
					int nr = i+dr[k];
					int nc = j+dc[k];
					if (nr>=0&&nr<R&&nc>=0&&nc<C&&arr[nr][nc]!=-1) {
						sprPlus[nr][nc]+=num/5;
						sprMinus[i][j]+=num/5;
					}
					
				}
			}
		}
		
		for (int i=0; i<R; i++) {
			for (int j=0; j<C; j++) {
				arr[i][j]+=sprPlus[i][j]-sprMinus[i][j];
			}
		}
	}
	
	private static void operCon() {
		int r=0, c=0;
		for (r=airCon-2; r>0; r--) {
			arr[r][c]=arr[r-1][c];
		}
		for (c=0; c<C-1; c++) {
			arr[r][c]=arr[r][c+1];
		}
		for (r=0; r<airCon-1; r++) {
			arr[r][c]=arr[r+1][c];
		}
		for (c=C-1; c>1; c--) {
			arr[r][c]=arr[r][c-1];
		}
		arr[r][c]=0;
		
		c=0;
		for (r=airCon+1; r<R-1; r++) {
			arr[r][c]=arr[r+1][c];
		}
		for (c=0; c<C-1; c++) {
			arr[r][c]=arr[r][c+1];
		}
		for (r=R-1; r>airCon; r--) {
			arr[r][c]=arr[r-1][c];
		}
		for (c=C-1; c>1; c--) {
			arr[r][c]=arr[r][c-1];
		}
		arr[r][c]=0;
		
	}

}
