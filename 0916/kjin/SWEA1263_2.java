import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA1263_2 {
// SW Expert Academy 1263. 사람 네트워크2
// dp
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input1263.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t=1; t<=T; t++) {
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(t).append(" ");
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int arr[][] = new int[N+1][N+1];
			
			
			for (int i=1; i<=N; i++) {
				for (int j=1; j<=N; j++) {
					int num = Integer.parseInt(st.nextToken());
					if (i==j)	continue;
					if (num==0)	arr[i][j]=10000;
					else	arr[i][j]=num;
				}
			}
			
			int min = Integer.MAX_VALUE;
			for (int k=1; k<=N; k++) {
				for (int i=1; i<=N; i++) {
					if (i==k)	continue;
					for (int j=1; j<=N; j++) {
						if (i==j || j==k) continue;
						arr[i][j] = Math.min(arr[i][j], arr[i][k]+arr[k][j]);
					}
				}
			}
			
			for (int i=1; i<=N; i++) {
				int sum=0;
				for (int j=1; j<=N; j++) {
					sum+=arr[i][j];
				}
				if (min>sum)	min=sum;
			}
			sb.append(min);
			System.out.println(sb);
		}
	}

}
