import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class JO1681 {
// Jungol 1681. 해밀턴 순환회로
	static int N, min;
	static int arr[][];
	static int temp[];
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input1681.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		min = 100000;
		arr = new int[N][N];
		temp = new int[N];
		int i, j;
		for (i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (j=0; j<N; j++) {
				int num = Integer.parseInt(st.nextToken());
				if (num==0) {
					arr[i][j]=1000;
				} else {
					arr[i][j] = num;
				}
			}
		}
		
		perm(1, 0, 0);
		System.out.println(min);
	}
	private static void perm(int cnt, int flag, int sum) {
		if (min<=sum) {
			return;
		}
		
		if (cnt==N) {
			sum+=arr[temp[cnt-1]][0];
			if (min>sum) {
				min=sum;

			}
			return;
		}
		
		for (int i=1; i<N; i++) {
			if ((flag&1<<i)!=0)	continue;
			temp[cnt]=i;
			perm(cnt+1, flag|1<<i, sum+arr[temp[cnt-1]][i]);
		}
	}

}
