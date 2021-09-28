import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ10830 {
// Baekjoon 10830. 행렬 제곱
	static int N;
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input10830.txt'"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		long B=Long.parseLong(st.nextToken());
		int arr[][]=new int[N][N];
		int p=1000;
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				arr[i][j]=Integer.parseInt(st.nextToken())%p;
			}
		}
		StringBuilder sb = new StringBuilder();
		int res[][]=power(arr, B, p);
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				sb.append(res[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	private static int[][] power(int arr[][], long b, int p) {
		int res[][] = new int[N][N];
		for (int i=0; i<N; i++) {
			res[i][i]=1;	// 단위 행렬
		}
		
		while(b>0) {
			if (b%2==1) {
				res=multiply(res, arr, p);
			}
			b/=2l;
			arr=multiply(arr, arr, p);
		}
		return res;
		
	}

	private static int[][] multiply(int[][] arr1, int[][] arr2, int p) {
		int res[][] = new int[N][N];
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				int temp=0;
				for (int k=0; k<N; k++) {
					temp+=arr1[i][k]*arr2[k][j]%p;	// 행렬 곱
				}
				res[i][j]=temp%p;
			}
		}
		return res;
	}

}
