import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA8458 {
// SW Expert Academy 8458. 원점으로 집합
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input8458.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int t=1; t<=T; t++) {
			sb.append("#").append(t).append(" ");
			int N=Integer.parseInt(br.readLine());
			long[] arr=new long[N];
			int check=0;
			long result=0;
			for (int i=0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				long x=Long.parseLong(st.nextToken());
				long y=Long.parseLong(st.nextToken());
				arr[i]=Math.abs(x)+Math.abs(y);
				check+=arr[i]%2;
			}
			if (check!=0&&check!=N) {
				result=-1;
			} else {
				for (int i=0; i<N; i++) {
					long temp=(long)(-1+Math.pow(arr[i]*8l, 0.5))/2;
					long maxSum=temp*(temp+1)/2l;
					while(maxSum<arr[i]||maxSum%2!=arr[i]%2) {
						temp++;
						maxSum=temp*(temp+1)/2l;
					}
					
					result=Math.max(temp, result);
				}
				
			}
			
			sb.append(result).append("\n");
		}
		System.out.println(sb);
	}

}
