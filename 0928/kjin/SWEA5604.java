import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA5604 {
// SW Expert Academy 5604. 구간합
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input5604.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int t=1; t<=T; t++) {
			sb.append("#").append(t).append(" ");
			StringTokenizer st = new StringTokenizer(br.readLine());
			long A=Long.parseLong(st.nextToken());
			long B=Long.parseLong(st.nextToken());
			
			long result=areaSum(B, (int)Math.log10(B))-areaSum(A-1, (int)Math.log10(A-1));
			sb.append(result).append("\n");
		}
		System.out.println(sb);
	}

	private static long areaSum(long a, int n) {
		if (a<0)	return 0;
		
		long sum=0l;
		int digit=0;
		while(a/10!=0) {
			while(a%10!=9) {
				sum+=digitSum(a)*Math.pow(10, digit);
				a--;
			}
			a/=10;
			sum+=(long)((a+1)*Math.pow(10, digit)*45);
			digit++;
		}
		
		sum+=(long)(a*(a+1)/2*Math.pow(10, digit));
		
		return sum;
		
	}

	private static long digitSum(long a) {
		long sum=a%10;
		while(a/10>0) {
			a/=10;
			sum+=a%10;
		}
		return sum;
	
	}

}
