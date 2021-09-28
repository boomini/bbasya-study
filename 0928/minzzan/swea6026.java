import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class swea6026 {
	static int M, N;
	static int MOD = 1000000007;

	static long[] fac;

	// 매번 동일한 값이기 때문에 한번만 구해놓고 재사용하자.
	static void pre() {
		fac = new long[101];
		fac[0] = fac[1] = 1;
		for (int i = 2; i < fac.length; i++) {
			fac[i] = (fac[i - 1] * i) % MOD;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());

			pre();
			long answer = solve();
			sb.append("#" + t + " " + answer + "\n");
		}

		System.out.println(sb);
	}

	// 함수의 개수: Σ(-1)^i*kCi*(k-i)^n
	private static long solve() {
		long total = 0;

		for (int i = 0; i <= M; i++) {
			long l1 = i % 2 == 0 ? 1 : -1; // 짝수인지
			long l2 = nCr(i);
			long l3 = pow(M - i, N);
			long result = ((l1 * l2) % MOD * l3) % MOD;
			total = (total + result + MOD) % MOD;
		}

		return total;
	}

	// nCr = (n!/((n-r)! * r!)) % MOD = (n! * ((n-r)! * r!)^(mod-2)) % MOD
	static long nCr(int r) {
		if (r == 0) {
			return 1;
		}
		long l1 = fac[M];
		long l2 = pow(fac[M - r], MOD - 2);
		long l3 = pow(fac[r], MOD - 2);

		return ((l1 * l2) % MOD * l3) % MOD;
	}

	static long pow(long a, int b) {
		if (b == 1) {
			return a;
		}
		long half = pow(a, b / 2);
		if (b % 2 == 0) {
			return (half * half) % MOD;
		} else {
			return ((half * half) % MOD * (a) % MOD) % MOD;
		}
	}
}
