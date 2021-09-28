import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA6026 {
// SW Expert Academy 6026. 성수의 비밀번호 공격
	static int mod=1000000007;
	static long[] fuc;	// m개만 선택할 경우의 수
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input6026.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int t=1; t<=T; t++) {
			sb.append("#").append(t).append(" ");
			StringTokenizer st = new StringTokenizer(br.readLine());
			int M=Integer.parseInt(st.nextToken());
			int N=Integer.parseInt(st.nextToken());
			fuc=new long[M+1];
			fuc[1]=1l;
			
			sb.append(select(M, N)).append("\n");
		
		}
		System.out.println(sb);
		
	}
	
	// m=5, n=9이면 
	// f(5)=5^9 - { 5C4 * f(4) + 5C3 * f(3) + 5C2 * f(2) + 5C1* f(1) } 
	private static long select(int m, int n) {
		if (fuc[m]!=0) {
			return fuc[m];
		} else {
			long temp=0l;
			for (int i=1; i<m; i++) {
				temp+=select(i,n)%mod*nCr(m,i)%mod;
			}
			
			fuc[m]=(power(m,n)+mod-temp)%mod;	// 음수 방지
			return fuc[m]; 
		}
	}

	private static long nCr(int n, int r) {
		r=Math.min(r, n-r);
		long nn=1L, kk=1L;				// ex) 10, 4
		for (int i=n; i>n-r; i--) {		// 10 9 8 7
			nn=nn*i%mod;
		}
		for (int i=2; i<=r; i++) {		// 2 3 4
			kk=kk*i%mod;
		}
		return nn*power(kk,mod-2)%mod;
	}

	private static long power(long m, int n) {
		long res=1l;
		while(n>0) {
			if (n%2==1) {
				res=res*m%mod;
			}
			n/=2;
			m=m*m%mod;
		}
		return res;
	}

}
