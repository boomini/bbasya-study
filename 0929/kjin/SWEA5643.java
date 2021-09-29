import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA5643 {
// SW Expert Academy 5643. 키 순서
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input5643.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int T=Integer.parseInt(st.nextToken());
		for (int t=1; t<=T; t++) {
			sb.append("#").append(t).append(" ");
			int N=Integer.parseInt(br.readLine().trim());
			int M=Integer.parseInt(br.readLine().trim());
			int[][] arr=new int[N+1][N+1];
			for (int i=0; i<M; i++) {
				st=new StringTokenizer(br.readLine());
				int small=Integer.parseInt(st.nextToken());
				int tall=Integer.parseInt(st.nextToken());
				arr[small][tall]=1;
				arr[tall][small]=-1;
			}
			
			int cnt=0;
			for (int i=1; i<=N; i++) {
				int temp=0;
				temp+=count(i, N, arr, 1)+count(i, N, arr, -1);
				if (temp==N-1) {
					cnt++;
				}
			}
			sb.append(cnt).append("\n");
		}
		System.out.println(sb);
		
		br.close();
	}

	private static int count(int cur, int n, int[][] arr, int flag) {
		int sum=0;
		boolean[] visited=new boolean[n+1];
		visited[cur]=true;
		Queue<Integer> queue=new LinkedList<Integer>();
		queue.add(cur);
		while(!queue.isEmpty()) {
			cur=queue.poll();
			for (int i=1; i<=n; i++) {
				if (!visited[i]&&arr[cur][i]==flag) {
					queue.add(i);
					visited[i]=true;
					sum++;
				}
			}
			
		}
		return sum;
	}

}
