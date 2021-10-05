import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class JO2577 {
// Jungol 2577. 회전 초밥
// BJ 15961. 회전 초밥
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input2577.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N=Integer.parseInt(st.nextToken());
		int D=Integer.parseInt(st.nextToken());
		int K=Integer.parseInt(st.nextToken());
		int C=Integer.parseInt(st.nextToken());
		int[] arr=new int[N+K];
		for (int i=0; i<N; i++) {
			arr[i]=Integer.parseInt(br.readLine());
		}
		
		for (int i=N; i<N+K; i++) {
			arr[i]=arr[i-N];
		}
		
		int[] num=new int[D+1];
		int result=0, cnt=1;
		num[C]++;
		for (int i=0; i<K; i++) {
			if (num[arr[i]]++==0) {
				cnt++;
			}
		}
		
		for (int i=K; i<N+K-1; i++) {
			if (--num[arr[i-K]]==0) {
				cnt--;
			}
			if (num[arr[i]]++==0) {
				cnt++;
			}
			if (result<cnt) {
				result=cnt;
			}
		}
		
		System.out.println(result);
		br.close();
	}

}
