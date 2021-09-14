import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ1463_DP {
// baekjoon 1463. 1로 만들기 
// dp
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input1463.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int dp[] = new int[N+1];
		for (int i=2; i<=N; i++) {
			dp[i]=Integer.MAX_VALUE;
		}
		for (int i=1; i<N; i++) {
			if (i*3<=N)
				dp[i*3] = Math.min(dp[i*3], dp[i]+1);
			if (i*2<=N)
				dp[i*2] = Math.min(dp[i*2], dp[i]+1);
			dp[i+1] = Math.min(dp[i+1], dp[i]+1);
		}
		System.out.println(dp[N]);
	}

}
