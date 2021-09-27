import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ11401 {
// Baekjoon 11401. 이항계수 3
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input11401.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N=Integer.parseInt(st.nextToken());
		int K=Integer.parseInt(st.nextToken());
		int mod=1000000007;
		K=Math.min(K, N-K);
		long nn=1L, kk=1L;				// ex) 10, 4
		for (int i=N; i>N-K; i--) {		// 10 9 8 7
			nn=nn*i%mod;
		}
		for (int i=2; i<=K; i++) {		// 2 3 4
			kk=kk*i%mod;
		}
		long result=(nn*power(kk,mod-2,mod)%mod)%mod;
		System.out.println(result);
	}

	private static long power(long x, int y, int p) {	// x^y%p
		long res=1L;
		while(y>0) {
			if (y%2==1) 
				res=(res*x)%p;
			y/=2;
			x=(x*x)%p;
		}
		return res;
	}

}
