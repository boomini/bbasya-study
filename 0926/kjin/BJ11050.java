import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ11050 {
// Baekjoon 11050. 이항계수1
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N=Integer.parseInt(st.nextToken());
		int K=Integer.parseInt(st.nextToken());
		K=Math.min(K, N-K);
		int result=1;
		for (int i=N; i>N-K; i--) {
			result*=i;
		}
		for (int i=2; i<=K; i++) {
			result/=i;
		}
		
		System.out.println(result);
	}

}
